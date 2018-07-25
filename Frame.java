import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame{ //extends JFrame
  
  Kehys k;
 
  public Frame() {
    JFrame jframe = new JFrame("ForthGraphics");
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.setSize(500, 500);
    jframe.setResizable(false);

    jframe.setLocationRelativeTo(null);
    jframe.setLayout(new GridLayout(1, 1, 0, 0));
  
    k = new Kehys();
    jframe.add(k);
    jframe.setVisible(true);
  
  }
 
  public static void main(String[] args) {
    new Frame();
 }
 
}