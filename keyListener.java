import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;

class KeyListener{
  
  private String input;
  private ArrayList<Object> chars; // V‰liaikainen tila k‰skyille ennen k‰skyjen pinoamista ja suoritusta (RUN)
  private Compute comp;       // Laskutoimitukset, vertailu, logiikka
  private Graphics graph;      // Kontrollivuo (IF, ELSE, DO, LOOP)
  private Object temp;
  private String temp2;
  private Stack<Object> ds; // Datapino
  private Stack<Object> cs; // K‰skypino
  private Object n1;        // Pinon 2 p‰‰llimm‰ist‰ arvoa n1, n2
  private Object n2;
  private boolean do_flag;     // Kontrollivuohon boolean flagit
  private boolean if_flag;
  
  //Grafiikkaominaisuuksia varten lis‰parametrit
  private Object n3;
  private Object n4;
  private Object n5;
  private Object n6;
  private Object n7;
  
  
  // Konstruktori
  protected KeyListener(){
    chars = new ArrayList<Object>();
    ds = new Stack<Object>();
    cs = new Stack<Object>();
    comp = new Compute();
    boolean do_flag = false;
    boolean else_flag = false;
  }

  public void listen(String input){
    this.input = input;
    
    if (input.equalsIgnoreCase("RUN")){
      
      /* ArrayList --> komentopino (cs)
      * Listan viimeinen arvo k‰skypinon pohjimmaiseksi
      * Tarkastatetaan tyyppi (int, boolean, string)  */
      for (int i = chars.size()-1 ; 0 <= i ; i-- ){
        if (isInteger((String)chars.get(i))){
          cs.push((Integer) Integer.parseInt((String)chars.get(i)));
        } else if (chars.get(i).equals("false") || chars.get(i).equals("true")){
          switch ((String)chars.get(i)) {
            case "true":
              cs.push((boolean) true);
              break;
            case "false":
              cs.push((boolean) false);
              break;
          }
        } else {
          cs.push((String) chars.get(i));
          }
      }
      System.out.println("Suoritetaan :\n" + chars.toString());
      chars.clear(); // Tyhjennet‰‰n lista
      decode(cs,ds);
    }
    
    // show ja empty pinojen n‰ytt‰miseen/tyhjent‰miseen testimieless‰
    else if (input.equalsIgnoreCase("show")){
      System.out.println("Printing..");
      System.out.println("Datastack: " + ds.toString() + "\nCommandStack: " + cs.toString());
    }
    else if (input.equalsIgnoreCase("empty")){
      System.out.println("Emptying stacks..");
      
      System.out.println("Datastack: " + ds.toString() + "\nCommandStack: " + cs.toString());
    } 
    else { 
      String[] temp = input.split(" "); // erottelee k‰skyt ja yksitt‰isiksi listan alkioiksi temp[] arrayhyn
      Collections.addAll(chars,temp);
      System.out.println("Input values: "+ chars.toString());
    }
  }
      //Testi onko alkio kokonaisluku
      public boolean isInteger(String input){ 
        try{
          Integer.parseInt(input);
          return true;
        } catch(NumberFormatException e) {
        return false;
        }
      }
      
      /* Metodi Forth-k‰skyjen tulkintaan
       * @.pre
       * @.post
       * 
       * 
       * */
      protected void decode(Stack<Object> cs, Stack<Object> ds){
        this.cs = cs;
        this.ds = ds;
      /* Alkioiden luku ja tulkinta
       * Literaali --> datapino
       * 
       * Eli t‰m‰n while silmukan sis‰‰n kaikki k‰skyn ohjaukset
       * Esin k‰skypinosta luetaan drop --> datapinosta literaali tai muuttuja
       * ja syˆtet‰‰n drop-k‰skylle luotuun metodiin.
       * */
      while (!cs.empty()){
        try{
          temp = cs.pop();
          
          // Kokonaisluku / totuusarvo --> pinoon
          if (temp instanceof Integer){
            ds.push((int) temp);     
          }
          if (temp instanceof Boolean){
            ds.push((boolean) temp);
          }
          /* On String, vertaillaan k‰skyihin
           * tai tutkitaan onko alustettu arvo
           * */
           // Tulostusk‰sky
          else if (temp.equals(".")) System.out.println("Tuloste :" + ds.peek());  

          // Aritmeettiset operaatiot
          // Pinoon tulos (Integer)
          else if ( temp.equals("+") ||temp.equals("-") ||temp.equals("*") ||temp.equals("/") ||temp.equals("mod")){
            n1 = ds.pop();
            n2 = ds.pop();
            ds.push((int) comp.compute(n2,n1,(String)temp)); // Tulos takaisin datapinoon
          } 
          // Vertailut
          // Pinoon boolean
          else if (temp.equals("==") ||temp.equals("<") ||temp.equals(">") ||temp.equals("!=")){
            n1 = ds.pop();
            n2 = ds.pop();
            ds.push((boolean) comp.compare(n2,n1,(String)temp));
          }
          // Logiikka
          // (Kys. konektiivijoukko riitt‰‰, muut voidaan toteuttaa and, or ja not -operaatioilla)
          // Pinoon boolean
          else if (temp.equals("and") || temp.equals("or")){
            n1 = ds.pop();
            n2 = ds.pop();
            ds.push((boolean) comp.connective(n2,n1,(String)temp));
          }
          else if (temp.equals("not")){
            n1 = ds.pop();
            ds.push((boolean) comp.connective(n1));
          }
          // Manipulointik‰skyt
          // Vaikuttaa pinon p‰‰llimm‰iseen 1-3 alkioon
          else if (temp.equals("dup")||temp.equals("drop")||temp.equals("swap")||temp.equals("over")||temp.equals("nip")||temp.equals("tuck")||temp.equals("rot")){

            switch ((String)temp){
              case "drop":
                ds.remove(ds.size()-1);
                break;
              case "dup":
                n2 = ds.peek();
                ds.push(n2);
                break;
              case "swap":
                n1 = ds.pop();
                n2 = ds.pop();
                ds.push(n1);
                ds.push(n2);
                break;
              case "over":
                ds.push(ds.get(ds.size()-2));
                break;
              case "nip":
                ds.remove(ds.size()-2);
                break;
              case "tuck":
                n1 = ds.pop();
                n2 = ds.pop(); 
                ds.push(n1);
                ds.push(n2);
                ds.push(n1);
                break;
              case "rot":
                n1 = ds.get(ds.size()-3);
                ds.remove(ds.size()-3);
                ds.push(n1);
                break;
            }
            System.out.println("Tulos: \n" + ds.toString());
            
            /* Kontrollik‰skyt - IN PROGRESS!
             * Syntaksi:
             *  : <raja-arvo> <indeksi> DO <operaatiot> LOOP ;
             *  : <ehto> IF <tosi> ELSE  <ep‰tosi> THEN ..koodi jatkuu ;
             * */
          } else if (temp.equals("LOOP")||temp.equals("IF")||temp.equals("DO")||temp.equals("ELSE")||temp.equals("THEN")){
            switch((String)temp){
              case "DO":
                n1 = ds.pop(); // indeksi
                n2 = ds.pop(); // raja-arvo
                do_flag = true;
                break;
              case "LOOP":
                if (do_flag){
                //Setit
                
                do_flag = false;
              } else {
              // Ei ole havaittu DO-k‰sky‰; formaatti siis v‰‰r‰‰..
                
              }
                
                break;
                
              case "IF":
                n1 = ds.pop();
                if (!(n1 instanceof Boolean)){
                  // Arvo ei boolean, virheellinen syntaksi..
                  // Toimenpiteet
                  break;
                }
                // If-ehto ei toteudu (n1=false), poistetaan k‰skypinon alkiot ELSE-k‰skyyn asti
                if (!(boolean)n1){
                  System.out.println("Ep‰tosi");
                  while (!ds.peek().equals("ELSE")){
                    n1 = cs.pop();
                    System.out.print("Poistetaan: " + n1);
                  }
                } else {
                  System.out.println("Tosi, suoritetaan if, poistetaan ELSE");
                  if_flag = true; // If-ehto 
                }
                break;
              case "ELSE":
                if (if_flag){
                while (!ds.peek().equals("THEN")){
                  n1 = cs.pop();
                  System.out.println("Poistetaan: " + n1);
                  
                }
                if_flag = true; // If lause ei toteutunut - toteutetaan ELSE flag = true
              } else {
                continue;
              }
                break;
              case "THEN":
                break;
            }
          }
          // Jos flag = false , poistetaan alkio pinosta (IN PROGRESS)
          
          
          //Grafiikkak‰skyt
          else if (temp.equals("point")||temp.equals("line")||temp.equals("circle")||temp.equals("triangle")||temp.equals("clear")){
            
            switch ((String)temp){
              case "point":
                n1 = ds.pop();
                n2 = ds.pop();
                n3 = ds.pop();
                graph.point(n3,n2,n1);
                break;
              case "line":
                n1 = ds.pop(); // v‰ri
                n2 = ds.pop(); // koordinaatit..
                n3 = ds.pop();
                n4 = ds.pop();
                n5 = ds.pop();
                graph.line(n5,n4,n3,n2,n1);
                break;
              case "circle":
                n1 = ds.pop();
                n2 = ds.pop();
                n3 = ds.pop();
                n4 = ds.pop();
                graph.circle(n4,n3,n2,n1);
                break;
              case "triangle":
                n1 = ds.pop();
                n2 = ds.pop();
                n3 = ds.pop();
                n4 = ds.pop();
                n5 = ds.pop();
                n6 = ds.pop();
                n7 = ds.pop();
                graph.triangle(n7,n6,n5,n4,n3,n2,n1);
                break;
              case "clear":
                graph.clear();
                break;
            }
            
            
          }
  
          // Poikkeus, syntaksi v‰‰r‰‰
        }catch (Exception e){ System.err.println("Error, check the format.\n" + e);
          break;
        }
        
      } // T‰h‰n loppuu FORTH k‰skyjen tulkinta
      }
}
  
      /* Syntaksin tarkitus
       *   // Katsotaan onko alussa ":"
      if ( !input.charAt[0].equals(':')){
        System.out.println("Syntax error, \":\" not found"); 
      }
      else {
        if ( input.charAt[input.length()-1] 
              for (int i = 0 ; i < input.length() ; i++ ){
          if( input.charAt[i])
        }
            }*/