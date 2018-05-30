import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;

class KeyListener{
  
  private String input;
  private ArrayList<Object> chars; // V‰liaikainen tila k‰skyille ennen k‰skyjen pinoamista ja suoritusta (RUN)
  private Compute comp;       // Laskutoimitukset, vertailu, logiikka
  private Controls cont;      // Kontrollivuo (IF, ELSE, DO, LOOP)
  private Object temp;
  private String temp2;
  private Stack<Object> ds; // Datapino
  private Stack<Object> cs; // K‰skypino
  private Object n1;          // Pinon 2 p‰‰llimm‰ist‰ arvoa
  private Object n2;
  
  
  // Konstruktori
  protected KeyListener(){
    chars = new ArrayList<Object>();
    ds = new Stack<Object>();
    cs = new Stack<Object>();
    comp = new Compute();
    cont = new Controls();
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
          
          // Luku --> pinoon
          if (temp instanceof Integer){
            ds.push((int) temp);     
          }
          if (temp instanceof Boolean){
            ds.push((boolean) temp);
          }
          // Tulostus 
          else if (temp.equals(".")) System.out.println("Tuloste :" + ds.peek());  

          // Aritmeettiset operaatiot
          else if ( temp.equals("+") ||temp.equals("-") ||temp.equals("*") ||temp.equals("/")){
            n1 = ds.pop();
            n2 = ds.pop();
            ds.push((int) comp.compute(n2,n1,(String)temp)); // Tulos takaisin datapinoon
          } 
          // Vertailut
          else if (temp.equals("==") ||temp.equals("<") ||temp.equals(">") ||temp.equals("!=")){
            n1 = ds.pop();
            n2 = ds.pop();
            ds.push((boolean) comp.compare(n2,n1,(String)temp));
          }
          // Logiikka
          // (Kys. konektiivijoukko riitt‰‰, muut voidaan toteuttaa n‰ill‰)
          
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
            System.out.println("Tulos: \n" + ds.toString());
            // Kontrollik‰skyt
            // In progress
          } else if (temp.equals("LOOP")||temp.equals("IF")||temp.equals("DO")||temp.equals("ELSE")){
            switch((String)temp){
              case "LOOP":
                break;
              case "IF":
                break;
              case "DO":
                break;
              case "ELSE":
                break;
            }
          }
          
          
        }catch (Exception e){ System.err.println("Error, check the format.\n" + e);
          break;
        }
        
      }
        
    } else if (input.equalsIgnoreCase("show")){
     System.out.println("Printing..");
     System.out.println("Datastack: " + ds.toString() + "\nCommandStack: " + cs.toString());
     
    } else { 
      String[] temp = input.split(" ");
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
    }*/