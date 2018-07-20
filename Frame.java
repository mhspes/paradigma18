import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{

	Kehys k;
	
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setResizable(false);

		init();
		
	}
		
		
	public void init() {
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1, 1, 0, 0));
		
		k = new Kehys();
		add(k);
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Frame();
	}
	
}
