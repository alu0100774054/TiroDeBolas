package es.esit.ull.PAI.TiroDeBolas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TiroBolasGrafico extends JPanel {
  private Bola bolaTiro;
  private ArrayList<Bola> bolasJuego;
  private ArrayList<Point> trazaTiro;
  private Timer timer;
  private final int DELAY = 10;
  private boolean iniciado = false;
  private final int NUM_BOLAS = 10;
  private Random generadorAleatorios;
  private ArrayList<Color> coloresJuego;
  private Point raton;
  private Point coordenadasBolaTiro;
  private boolean cambiaRaton = false;
  private boolean disparado = false;
  private boolean mostrarTraza = false;
  
  public TiroBolasGrafico() {
    bolaTiro = null;
    bolasJuego = new ArrayList<Bola>();
    trazaTiro = new ArrayList<Point>();
    coloresJuego = new ArrayList<Color>();
    timer = new Timer(getDELAY(), new TimerListener());
    generadorAleatorios = new Random();
    raton = new Point();
    coordenadasBolaTiro = new Point();
    establecerEstilo();
    generarColores();
    iniciarOyentes();
  }

  private void iniciarOyentes() {
    addMouseMotionListener(new RatonListener());
    addMouseListener(new LanzarListener());
  }

  private void establecerEstilo() {
    setBackground(new Color(153, 204, 255));
  }

  private void generarColores() {
    getColoresJuego().add(Color.BLUE);
    getColoresJuego().add(Color.CYAN);
    getColoresJuego().add(Color.GREEN);
    getColoresJuego().add(Color.MAGENTA);
    getColoresJuego().add(Color.ORANGE);
    getColoresJuego().add(Color.PINK);
    getColoresJuego().add(Color.RED);
    getColoresJuego().add(Color.YELLOW);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!isIniciado()) {
      dibujarBolas(g);
      setIniciado(true);
    } else {
      if (isCambiaRaton()) {
        dibujarBolas(g);
        dibujarFlecha(g);
      } else {
        dibujarBolas(g);
      }
      if (isDisparado()) {
        dibujarBolas(g);
        dibujarFlecha(g);
        dibujarTraza(g);
      }
    }
  }
  
  private void dibujarTraza(Graphics g) {
    if (isMostrarTraza()) {
      for (int i = 0; i < getTrazaTiro().size(); i++) {
        g.setColor(getBolaTiro().getFondo());
        g.fillOval((int)getTrazaTiro().get(i).getX() - getBolaTiro().getRADIO() / 2, 
            (int) getTrazaTiro().get(i).getY() - getBolaTiro().getRADIO() / 2, 
            (int) getBolaTiro().getRADIO(), 
            (int) getBolaTiro().getRADIO());
      }
    } else {
      g.setColor(getBolaTiro().getFondo());
      g.fillOval((int)getTrazaTiro().get(getTrazaTiro().size() - 1).getX() - getBolaTiro().getRADIO() / 2, 
          (int) getTrazaTiro().get(getTrazaTiro().size() - 1).getY() - getBolaTiro().getRADIO() / 2, 
          (int) getBolaTiro().getRADIO(), 
          (int) getBolaTiro().getRADIO());
    }
  }

  private void dibujarFlecha(Graphics g) {
    Graphics2D g2d = (Graphics2D)g;
    g.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(5.0F));
    System.out.println((int) getBolaTiro().getCentroX() + ", " +  (int) getBolaTiro().getCentroY() + "--- " + (int) getRaton().getX() + ", " + (int) getRaton().getY());
    g2d.drawLine((int) getBolaTiro().getCentroX(), (int) getBolaTiro().getCentroY(), (int) getRaton().getX(), (int) getRaton().getY());
  }

  private void dibujarBolas(Graphics g) {
    if(!isIniciado()) {
      generarBolas();
    }
    
    // Bolas del juego.
    for (int i = 0; i < getNUM_BOLAS(); i++) {
      g.setColor(getBolasJuego().get(i).getFondo());
      g.fillOval((int) (getBolasJuego().get(i).getCentroX() - getBolasJuego().get(i).getRADIO() / 2), 
          0, 
          getBolasJuego().get(i).getRADIO(), 
          getBolasJuego().get(i).getRADIO());
    }
    
    // Bolas del usuario.
    if(!isDisparado()) {
    g.setColor(getBolaTiro().getFondo());
    g.fillOval(getBolaTiro().getCentroX() - getBolaTiro().getRADIO() / 2, 
        getBolaTiro().getCentroY() - getBolaTiro().getRADIO() / 2, 
        getBolaTiro().getRADIO(), 
        getBolaTiro().getRADIO());
    }
  }
  
  private void generarBolas() {
    // Bolas del juego.
    int radio = this.getWidth() / getNUM_BOLAS();
    int inicioX = 0;
    int inicioY = 0;
    for (int i = 0; i < getNUM_BOLAS(); i++) {
      int colorAleatorio = getGeneradorAleatorios().nextInt(getColoresJuego().size() - 1);
      getBolasJuego().add(new Bola(inicioX + radio / 2, inicioY, radio, getColoresJuego().get(colorAleatorio)));
      inicioX += radio;
    }
    
    // Bola del usuario.
    int colorAleatorio = getGeneradorAleatorios().nextInt(getColoresJuego().size() - 1);
    setCoordenadasBolaTiro(new Point(this.getWidth() / 2 - radio / 2, this.getHeight() - radio / 2));
    setBolaTiro(new Bola((int) getCoordenadasBolaTiro().getX(),(int) getCoordenadasBolaTiro().getY(), radio, getColoresJuego().get(colorAleatorio)));
  }
  
  private void lanzar() {
    
  }
  class TimerListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      repaint();
    }
    
  }
  
  class RatonListener implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      setRaton(new Point(e.getX(), e.getY()));
      cambiaRaton = true;
      repaint();
    }
    
  }
  
  class LanzarListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
      lanzar();
      setDisparado(true);
      repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }
    
  }
  
  
  
  
  
  public boolean isMostrarTraza() {
    return mostrarTraza;
  }

  public void setMostrarTraza(boolean mostrarTraza) {
    this.mostrarTraza = mostrarTraza;
  }

  public boolean isDisparado() {
    return disparado;
  }

  public void setDisparado(boolean disparado) {
    this.disparado = disparado;
  }

  public boolean isCambiaRaton() {
    return cambiaRaton;
  }

  public void setCambiaRaton(boolean cambiaRaton) {
    this.cambiaRaton = cambiaRaton;
  }

  public Point getCoordenadasBolaTiro() {
    return coordenadasBolaTiro;
  }

  public void setCoordenadasBolaTiro(Point coordenadasBolaTiro) {
    this.coordenadasBolaTiro = coordenadasBolaTiro;
  }

  public Point getRaton() {
    return raton;
  }

  public void setRaton(Point raton) {
    this.raton = raton;
  }

  public ArrayList<Color> getColoresJuego() {
    return coloresJuego;
  }

  public void setColoresJuego(ArrayList<Color> coloresJuego) {
    this.coloresJuego = coloresJuego;
  }

  public Random getGeneradorAleatorios() {
    return generadorAleatorios;
  }

  public void setGeneradorAleatorios(Random generadorAleatorios) {
    this.generadorAleatorios = generadorAleatorios;
  }

  public int getNUM_BOLAS() {
    return NUM_BOLAS;
  }

  public boolean isIniciado() {
    return iniciado;
  }

  public void setIniciado(boolean iniciado) {
    this.iniciado = iniciado;
  }

  public Bola getBolaTiro() {
    return bolaTiro;
  }

  public void setBolaTiro(Bola bolaTiro) {
    this.bolaTiro = bolaTiro;
  }

  public ArrayList<Bola> getBolasJuego() {
    return bolasJuego;
  }

  public void setBolasJuego(ArrayList<Bola> bolasJuego) {
    this.bolasJuego = bolasJuego;
  }

  public ArrayList<Point> getTrazaTiro() {
    return trazaTiro;
  }

  public void setTrazaTiro(ArrayList<Point> trazaTiro) {
    this.trazaTiro = trazaTiro;
  }

  public Timer getTimer() {
    return timer;
  }

  public void setTimer(Timer timer) {
    this.timer = timer;
  }

  public int getDELAY() {
    return DELAY;
  }
  
  
}
