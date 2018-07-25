/* Luokka grafiikan toteuttamiseen
 * */

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ForthGraphs{
  
  private Point A;       // Koordinaattiparit
  private Point B;
  private Point C;
  private JFrame jframe; // JFrame-ikkuna
  
  // Konstruktori
  protected ForthGraphs(){    
  }
  
  // Metodi FRame-ikkunan luomiseen
  // Luodaan ensimmäistä kertaa grafiikka-metodeja kutsuttaessa
  protected void frame() {
    jframe = new JFrame("ForthGraphics");
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Ikkunan sulkeutuessa lopettaa ohjelman
    jframe.setSize(1000, 600);
    jframe.setResizable(false);

    jframe.setLocationRelativeTo(null); 
    jframe.setVisible(true);   
  }
  
  
    /* Metodi pisteen piirtämiseen
     * @.pre
     * @.post
     * 
     * */ 
  protected void point(Object x1, Object y1, Object väri){    
    Point A = new Point((int)x1,(int)y1);
    Point B = new Point(0,0);
    Point C = new Point(0,0);
    
    if (jframe == null){                     // Luodaan ikkuna jos ei vielä ole
      frame();
    }
    
    Kehys k = new Kehys();                   // Kehys kuvion piirtämiseen
    k.param(A, B, C, (String)väri, "point"); // Parametrit
    jframe.add(k);   
    jframe.setVisible(true);                 // Kuvio näkyviin
  }
    /* Metodi viivan piirtämiseen
     * @.pre
     * @.post
     * 
     * */
  protected void line(Object x1, Object y1, Object x2, Object y2, Object väri){    
    Point A = new Point((int)x1,(int)y1);
    Point B = new Point((int)x2,(int)y2);
    Point C = new Point(0,0);
    
    if (jframe == null){                      // Luodaan ikkuna jos ei vielä ole
      frame();
    }
    Kehys k = new Kehys();                    // Kehys kuvion piirtämiseen
    k.param(A, B, C, (String)väri, "line");   // Parametrit
    jframe.add(k);   
    jframe.setVisible(true);                  // Kuvio näkyviin
  }
  /* Metodi ympyrän piirtämiseen
   * @.pre
   * @.post
   * 
   * */
  protected void circle(Object x1, Object y1, Object x3, Object väri){    
    Point A = new Point((int)x1,(int)y1);
    Point B = new Point((int)x3,(int)x3);
    Point C = new Point(0,0);
    
    if (jframe == null){                        // Luodaan ikkuna jos ei vielä ole
      frame();
    }
     
    Kehys k = new Kehys();                      // Kuviota varten Kehys-olio
    k.param(A, B, C, (String)väri, "circle");   // Parametrit
    jframe.add(k);   
    jframe.setVisible(true);                    // Kuvio näkyviin
  }
  /* Metodi kolmion piirtämiseen
   * @.pre
   * @.post
   * 
   * */
  protected void triangle(Object x1, Object y1, Object x2, Object y2, Object x3, Object y3, Object väri){
    Point A = new Point((int)x1,(int)y1);
    Point B = new Point((int)x2,(int)y2);
    Point C = new Point((int)x3,(int)y3);
    
    if (jframe == null){                        // Luodaan ikkuna jos ei vielä ole
      frame();
    }
    
    Kehys k = new Kehys();                      // Kuviota varten Kehys-olio
    k.param(A, B, C, (String)väri, "triangle"); // Parametrit
    jframe.add(k);   
    jframe.setVisible(true);                    // Kuvio näkyviin
  }
  
    /* Metodi piirtoalueen tyhjennykseen
   * */
  protected void clear(){
    Point A = new Point(0,0);
    Point B = new Point(0,0);
    Point C = new Point(0,0);
    
    if (jframe == null){
      frame();                       // Luodaan ikkuna jos ei vielä ole
    }  
    
    Kehys k = new Kehys();           // Kuviota varten Kehys-olio
    k.param(A, B, C, "", "clear");   // Parametrit
    jframe.add(k);   
    jframe.setVisible(true);         // Kuvio näkyviin
  }

}