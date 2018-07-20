import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Kehys extends JPanel{
		int a;
		int b;
		
		public Kehys() {}
		
		//jotta pinoa ei tarvitse lukea niin pitkään kerralla, muodostaa parista x, y Point-tyypin
		protected Point muodostaKoordinaatti(Object n1, Object n2){
			n1 = (int)a;
			n2 = (int)b;
			Point A = new Point(a, b);
			return A;
		}
		
		public void paint (Graphics g){
			
			//kun väri on asetettu, kaikki tämän jälkeen piirrettävät tulee sillä värillä
			g.setColor(Color.RED);
			Point A = new Point(10, 10);
			Point B = new Point(500, 200);
			Point C = new Point(100, 80);
			int numero = 2;
			switch (numero) {
		      case 3: //viiva
		    	  	g.drawLine(A.x, A.y, B.x, B.y);
		    	  	break;
		        case 1: //ympyrä
		        	g.drawOval(A.x, A.y, 50, 50); // koska on ympyrä, voidaan käyttää ovaalia mutta säteen (sade) on oltava sama sekä korkeudelle että leveydelle
		        	break;
		        case 2: //piste
		        	g.drawOval(A.x, A.y, 10, 10);
		        	g.fillOval(A.x, A.y, 10, 10); //täytetään ympyrää värillä, jotta sen näkisi
		        	break;
		        case 4: //kolmio
		        	g.drawLine(A.x, A.y, B.x, B.y);
					g.drawLine(B.x, B.y, C.x, C.y);
					g.drawLine(C.x, C.y, A.x, A.y);
					break;
		        case 5: //clear
		        	g.fillRect(0, 0, 1000, 1000);
		        	break;
			}
		}
		
		
}
