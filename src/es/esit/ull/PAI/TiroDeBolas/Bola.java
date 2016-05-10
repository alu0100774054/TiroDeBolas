package es.esit.ull.PAI.TiroDeBolas;

import java.awt.Color;

public class Bola {
  private int centroX;
  private int centroY;
  private final int RADIO;
  private Color fondo;
  
  public Bola(int centroX, int centroY, int radio, Color fondo) {
    this.centroX = centroX;
    this.centroY = centroY;
    this.RADIO = radio;
    this.fondo = fondo;
  }

  public int getCentroX() {
    return centroX;
  }

  public void setCentroX(int centroX) {
    this.centroX = centroX;
  }

  public int getCentroY() {
    return centroY;
  }

  public void setCentroY(int centroY) {
    this.centroY = centroY;
  }

  public Color getFondo() {
    return fondo;
  }

  public void setFondo(Color fondo) {
    this.fondo = fondo;
  }

  public int getRADIO() {
    return RADIO;
  }
  
}
