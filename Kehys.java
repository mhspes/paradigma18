/* JPanel-luokan toteuttava luokka kuvion piirtämistä varten
 * */

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

class Kehys extends JPanel{
  protected Point A2;        // Koordinaattiparit
  protected Point B2;
  protected Point C2;
  protected String colour;
  protected String shape;
  
  /* Metodi koordinaattien, värin ja muodon 
   * siirtämiseksi Kehys-oliolle
   * */
  public void param(Point A2, Point B2, Point C2, String colour, String shape){
    this.A2=A2;
    this.B2=B2;
    this.C2=C2;
    this.colour=colour;
    this.shape=shape;
  }
  /* Metodi kuvion piirtämiseksi JFrame-ikkunaan
   * 
   * */
  protected void paintComponent(Graphics g){
    
    // Värin valinta
    switch (colour){
      case "red":
        g.setColor(Color.RED);
        break;
      case "blue":
        g.setColor(Color.BLUE);
        break;
      case "green":
        g.setColor(Color.GREEN);
        break;
      case "black":
        g.setColor(Color.BLACK);
        break;
      case "yellow":
        g.setColor(Color.YELLOW);
        break;
    }
    // Kuvion valinta
    switch(shape){
      case "point":
        g.drawOval(A2.x, A2.y, 5, 5);
        g.fillOval(A2.x, A2.y, 5, 5); 
        break;
      case "line":
        g.drawLine(A2.x, A2.y, B2.x, B2.y);
        break;
      case "circle":
        g.drawOval(A2.x, A2.y, B2.x, B2.x);
        break;
      case "triangle":
        g.drawLine(A2.x, A2.y, B2.x, B2.y);
        g.drawLine(B2.x, B2.y, C2.x, C2.y);
        g.drawLine(C2.x, C2.y, A2.x, A2.y);
        break;
      case "clear":
        g.clearRect(0, 0, 1000, 1000);
        break;
    }
  }
}