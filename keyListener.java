import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.lang.StringBuffer;

class KeyListener{
  
  private String input;
  private ArrayList<Object> chars;   // V‰liaikainen tila k‰skyille ennen datapinoon siirtoa ja suoritusta
  private ArrayList<Variable> vars;  // Lista muuttujille
  private Compute comp;              // Laskutoimitukset, vertailu, logiikka
  private Graphics graph;            // Grafiikka
  private Object temp;
  private String temp2;
  private Stack<Object> ds;          // Datapino
  private Stack<Object> cs;          // K‰skypino
  private Object n1;                 // Pinon 2 p‰‰llimm‰ist‰ arvoa n1 & n2
  private Object n2;
  
  //Lis‰parametrit grafiikkaominaisuuksille
  private Object n3;
  private Object n4;
  private Object n5;
  private Object n6;
  private Object n7;
  
  //Kontrollivuon parametrit
  private Stack<Object> ts;        // V‰liaikainen stack (DO .. LOOP lohkolle)
  private boolean do_flag;         // Kontrollivuo -flagit
  private boolean if_flag;

  
  // Konstruktori
  protected KeyListener(){
    chars = new ArrayList<Object>();
    vars = new ArrayList<Variable>();
    comp = new Compute();
    graph = new Graphics();
    ds = new Stack<Object>();
    cs = new Stack<Object>();
    boolean do_flag = false;
    boolean if_flag = false;
  }
  /* Metodi .txt-tiedoston merkkijonojen tulkintaan ja parsimiseen komennoiksi
   * K‰skyt siirret‰‰n ArrayList-olioon (chars), josta k‰skypinoon (cs)
   * */
  protected void listen(String input){
    this.input = input;
    
    if (input.equalsIgnoreCase("run")){
      
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
      System.out.println("Running:");
      for (int i = 0 ; i < chars.size(); i++){  // Printtaa toteutettavan koodin ennen toteutusta
        System.out.print(chars.get(i) + " ");
        if (chars.get(i).equals(";")){
          System.out.println("");
        }

      }
      System.out.println("Output:\n");
      chars.clear();              // Tyhjennet‰‰n lista
      decode(cs,ds,0);            // K‰skypino toteutukseen
    }
    
    // Pinojen n‰ytt‰miseen testimieless‰
    else if (input.equalsIgnoreCase("show")){
      System.out.println("Printing..");
      System.out.println("Datastack: " + ds.toString() + "\nCommandStack: " + cs.toString());
    }
    else { 
      String[] temp = input.split(" "); // erottelee k‰skyt temp[]-listaan
      Collections.addAll(chars,temp);   // Siirt‰‰ em. listan ArrayList-olioon
    }
  }
  
  //Testi onko alkio kokonaisluku
  protected boolean isInteger(String input){ 
    try{
      Integer.parseInt(input);
      return true;
    } catch(NumberFormatException e) {
      return false;
    }
  }
  // Pinojen resetointi
  protected void reset(){
    chars.clear();
    ds.clear();
    cs.clear();
  }
  
      /* Metodi Forth-k‰skyjen tulkintaan
       * Literaali tai muuttuja --> datapino. K‰skyn tullessa poimitaan tarvittava m‰‰r‰ parametreja datapinosta.
       * Poikkeus heitet‰‰n jos parametreja liian v‰h‰n, niiden tyyppi on v‰‰r‰ tai k‰sky‰ ei tunnisteta.
       * @.pre
       * @.post
       * 
       * 
       * */
  protected void decode(Stack<Object> cs, Stack<Object> ds,int loop_index){
    this.cs = cs;
    this.ds = ds;
    ArrayList<Object> temp3 = new ArrayList<Object>();                // ajonaikainen lista looppeja varten  
    Integer ind1 = new Integer(0);                                    // DO ..LOOP - lohkon indeksit
    Integer ind2 = loop_index;                                        // L‰hinn‰ vain "I" -k‰sky‰ varten
    Stack<Object> ts = new Stack<Object>();                           // Ajonaikainen pino DO ..LOOP -lohkoille
    
    int temp_ind2 = 0;                                                // Indeksin talteenotto sis‰kk‰isiss‰ loopeissa
    if (ind2!=0){
      temp_ind2 = ind2;
    }
        
    while (!cs.empty()){
      try{
        temp = cs.pop();
          
        // Kokonaisluku / totuusarvo --> pinoon
        if (temp instanceof Integer){
          ds.push((int)temp);     
        }
        else if (temp instanceof Boolean){
          ds.push((boolean)temp);
        }
        
        // Literaali, testataan onko " "-merkit (eli onko merkkijono)
        else if (temp instanceof String && ((String)temp).length() >=3 && ((String)temp).substring(0,1).equals("\"") && 
                 ((String)temp).substring(((String)temp).length()-1,((String)temp).length()).equals("\"")){
          StringBuffer temp2 = new StringBuffer(((String)temp));
          temp = temp2.substring(1,((String)temp).length()-1);
          ds.push((String)temp);
        }
          
        // Muuttujan m‰‰ritys
        else if (temp.equals("VARIABLE")){
          vars.add(new Variable<String,Integer>((String)cs.pop(),0));
        }
        // : ja ; merkkien poisto
        else if (temp.equals(":") || temp.equals(";")){
          continue;         
        }
        // P‰‰tt‰‰ ohjelman, poistuu silmukasta, tyhjent‰‰ pinot
        else if (temp.equals("STOP")){
          ds.clear();
          cs.clear();
          return;
        }
        // Tulostus, ei poista pinosta
        else if (temp.equals("print")) System.out.println(ds.peek());  
        
        // Kysyy k‰ytt‰j‰lt‰ merkkijonoa, voi olla useampi --> datapinoon
        else if (temp.equals("read")){
          Scanner reader = new Scanner(System.in);
          String value = reader.nextLine();                
          
          Object[] temp = value.split(" "); // erottelee k‰skyt yksitt‰isiksi listan alkioiksi temp[] arrayhyn
          for (int i = 0 ; i < temp.length ; i++){
            if (isInteger((String)temp[i])){
              ds.push((Integer) Integer.parseInt((String)temp[i]));
            } else if (temp[i].equals("false") || temp[i].equals("true")){
              switch ((String)temp[i]) {
                case "true":
                  ds.push((boolean) true);
                  break;
                case "false":
                  ds.push((boolean) false);
                  break;
              }
            } else {
              ds.push((String) chars.get(i));
            }
          }          
        }
        
        // Aritmeettiset operaatiot, tulos (integer) datapinoon
        else if ( temp.equals("+") ||temp.equals("-") ||temp.equals("*") ||temp.equals("/") ||temp.equals("mod")){
          n1 = ds.pop();
          n2 = ds.pop();
          ds.push((int) comp.compute(n2,n1,(String)temp));
        } 
        // Vertailut datapinoon tulos (boolean)
        else if (temp.equals("==") ||temp.equals("<") ||temp.equals(">") ||temp.equals("!=")){
          n1 = ds.pop();
          n2 = ds.pop();
          ds.push((boolean) comp.compare(n2,n1,(String)temp));
        }
        
        // Logiikka (and, or, not), datapinoon tulos (boolean)
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
        }
        // Palauttaa DO ..LOOP laskurin arvon
        else if (temp.equals("I")){
          ds.push(ind2);
        }

        // Kontrollik‰skyt 
        // int_1 int_2 DO ... LOOP & boolean IF <tosi> ELSE <ep‰tosi> THEN ...
        else if (temp.equals("LOOP")||temp.equals("IF")||temp.equals("DO")||temp.equals("ELSE")||temp.equals("THEN")){
          
          switch((String)temp){
            case "DO":
              ind1 = (int)ds.pop(); // raja-arvo
              ind2 = (int)ds.pop(); // indeksi
              do_flag = true;             
              
              int amount_loop = 0;
              int amount_do = 1;
              // Ker‰t‰‰n komentopinosta k‰skyt (LOOP-komentoon asti) v‰liaikaiseen listaan
              while(amount_loop < amount_do){
                
                if (cs.peek().equals("LOOP")){         
                  amount_loop++;
                  if (amount_loop < amount_do){
                    temp3.add(cs.pop());
                  }
                }
                else if (cs.peek().equals("DO")){         
                  amount_do++;
                  temp3.add(cs.pop());
                }else{
                  temp3.add(cs.pop());
                }
              }
              
              break;
              
            case "LOOP":
              if (do_flag){
              while (ind2 <= ind1){
                /* Suoritetaan k‰skyt ind2-ind1+1 kertaa
                * Joka kierroksella lista --> v‰liaikainen data-stack, joka
                * syˆtet‰‰n decode-metodiin (usealle loopille rekursiivisesti)
                * */
                for (int j = temp3.size()-1 ; j >= 0 ; j--){
                  if (temp3.get(j) instanceof Integer){
                    ts.push(temp3.get(j));
                  }
                  else if (temp3.get(j).equals("false") || temp3.get(j).equals("true")){
                    switch ((String)temp3.get(j)){
                      case "true":
                        ts.push((boolean) true);
                        break;
                      case "false":
                        ts.push((boolean) false);
                        break;
                    }
                  }
                  else { ts.push(temp3.get(j)); }
                }
                decode(ts,ds,ind2);   // Syˆtet‰‰n v‰laikainen k‰skypino decode-metodille
                ind2 = ind2 + 1;      // Kasvatetaan indeksi‰           
              }
              
              
              // Loopattu n2-n1 kertaa, temp3 tyhj‰ksi ja do_flag=false
              temp3.clear();
              do_flag = false;
            } else {
              throw new IllegalArgumentException("Faulty format, LOOP, without DO"); // Ei ole havaittu DO-k‰sky‰; formaatti siis v‰‰r‰‰.
            }
            ind2 = temp_ind2; // Syˆtet‰‰n ulommalle loopille sen oma indeksi
            break;
            
            case "IF":
              n1 = ds.pop();
              if (!(n1 instanceof Boolean)){
                throw new IllegalArgumentException("Error: Should be '<boolean> IF ...' ");
              }
              // If-ehto ei toteudu (n1=false), poistetaan k‰skypinon alkiot ELSE-k‰skyyn asti
              if (!(boolean)n1){
                while (!cs.peek().equals("ELSE")){
                  cs.pop();
                }
              } else {
                // If-ehto toteutuu, jatketaan koodia
                if_flag = true;
              }
              break;
            case "ELSE":
              if (if_flag){                           // If-lause oli tosi, poistetaan else - then k‰skyt
              while (!cs.peek().equals("THEN")){
                  n1 = cs.pop();            
                }
                if_flag = false;                      // Lohko toteutettu
              } else {
              }
              break;
            case "THEN":                              // THEN - jatketaan normaalisti
              break;
          }
        }

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
        } else {

          for (int i = 0 ; i < vars.size() ; i++){            // Tutkitaan, onko merkkijono jokin muuttujista
            if (temp.equals(vars.get(i).name())){
              if(cs.peek().equals("!")){                      // Muuttujan arvon m‰‰ritys? (n1 PARAM !)
                vars.get(i).setValue((int)ds.pop());
                cs.remove(cs.size()-1);                       // Poistetaan ! jonosta
                break;
              } else {
                ds.push(vars.get(i).value());                 // Pelkk‰ muuttuja -> datapinoon
                break;
              }
            } // Komento ei ollut mik‰‰n k‰skyist‰, eik‰ muuttujista --> virhe
            else if (i == vars.size()-1 ) { throw new IllegalArgumentException("Komentoa " + temp + "ei tunnistettu");
            }
          } 
        }
        // Poikkeukset
      } catch (EmptyStackException es){ System.err.println("K‰skyll‰ liian v‰h‰n parametreja:\n" + es );
        break;
      } catch (Exception e){ System.err.println("Tarkista formaatti:\n" + e);
        break;
      }
      
    } // T‰h‰n loppuu FORTH k‰skyjen tulkinta
  } // decode-metodin loppu
}