package es.esit.ull.PAI.TiroDeBolas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sun.awt.IconInfo;

public class Interfaz extends JFrame {
  private JButton informacion;
  private final int ANCHO_VENTANA = 600;
  private final int ALTO_VENTANA = 600;
  private ImageIcon info;
  private ImageIcon erik;
  private TiroBolasGrafico tiroBolasGrafico;
  private final String TITU = "Información acerca del programa:";
  private final String UNI = "\n\nUniversidad de La Laguna.";
  private final String FACUL = "\nEscuela Superior de Ingeniería Técnica.";
  private final String GRAD = "\nGrado en Ingeniería Informática.";
  private final String FECHA = "\nDía 9 de Mayo de 2016";
  private final String AUTOR = "\n\nErik Andreas Barreto de Vera";
  private final String CONTACTO = "\nalu0100774054@ull.edu.es";
  
  public Interfaz() {
    iniciarInterfaz();
    iniciarComponentes();
    iniciarOyentes();
  }

  private void iniciarOyentes() {
    getInformacion().addActionListener(new InfoOyente());
  }

  private void iniciarComponentes() {
    // Iniciar Atributos.
    setTiroBolasGrafico(new TiroBolasGrafico());
    setInfo(new ImageIcon("info.png"));
    setErik(new ImageIcon("Erik.png"));
    setInformacion(new JButton(getInfo()));
    
    getInformacion().setBorder(null);
    
    add(getInformacion(), BorderLayout.PAGE_END);
    add(getTiroBolasGrafico(), BorderLayout.CENTER);
  }

  private void iniciarInterfaz() {
    setLayout(new BorderLayout());
    setSize(new Dimension(getANCHO_VENTANA(), getALTO_VENTANA()));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Tiro de bolas");
  }

  class InfoOyente implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      JOptionPane.showMessageDialog(null, getTITU() + getUNI() + getFACUL() + getGRAD() + getFECHA() + getAUTOR() + getCONTACTO(), "Acerca del autor", JOptionPane.INFORMATION_MESSAGE, getErik());
    }
    
  }
  
  public ImageIcon getErik() {
    return erik;
  }

  public void setErik(ImageIcon erik) {
    this.erik = erik;
  }

  public String getFECHA() {
    return FECHA;
  }

  public String getTITU() {
    return TITU;
  }

  public String getUNI() {
    return UNI;
  }

  public String getFACUL() {
    return FACUL;
  }

  public String getGRAD() {
    return GRAD;
  }

  public String getAUTOR() {
    return AUTOR;
  }

  public String getCONTACTO() {
    return CONTACTO;
  }

  public TiroBolasGrafico getTiroBolasGrafico() {
    return tiroBolasGrafico;
  }

  public void setTiroBolasGrafico(TiroBolasGrafico tiroBolasGrafico) {
    this.tiroBolasGrafico = tiroBolasGrafico;
  }

  public void setInfo(ImageIcon info) {
    this.info = info;
  }

  public ImageIcon getInfo() {
    return info;
  }

  public int getANCHO_VENTANA() {
    return ANCHO_VENTANA;
  }

  public int getALTO_VENTANA() {
    return ALTO_VENTANA;
  }

  public JButton getInformacion() {
    return informacion;
  }

  public void setInformacion(JButton informacion) {
    this.informacion = informacion;
  }
  
}
