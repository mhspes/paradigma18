import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

/* Forth - k‰skyj‰ mm. 
* Pinon ylin arvo/arvot (ennen -- j‰lkeen) 
*  dup ( a -- a a )
ï drop ( a -- )
ï swap ( a b -- b a )
ï over ( a b -- a b a )
ï rot ( a b c -- b c a ) 
 * */

class KeyListener{
  
  private String input;
  private ArrayList<Object> chars; // V‰liaikainen tila k‰skyille ennen k‰skyjen pinoamista ja suoritusta (RUN)
  protected Compute comp;
  private Object temp;
  private String temp2;
  protected Stack<Object> ds; // Datapino
  protected Stack<Object> cs; // K‰skypino
  private int n1;          // Pinon 2 p‰‰llimm‰ist‰ arvoa
  private int n2;
  private boolean b1;     // -||- totuusarvoina
  private boolean b2;
  
  
  // Konstruktori
  public KeyListener(){
    chars = new ArrayList<Object>();
    ds = new Stack<Object>();
    cs = new Stack<Object>();
    comp = new Compute();
  }
  /*
   * 
   * 
   * */
  public void listen(String input){
    this.input = input;
      
    if (input.equalsIgnoreCase("RUN")){
      
      // ArrayList --> komentopino (cs)
      // Listan vika arvo ponon pohjimmaiseksi
      for (int i = chars.size()-1 ; 0 <= i ; i-- ){
        if (isInteger((String)chars.get(i))){
          cs.push((Integer) Integer.parseInt((String)chars.get(i)));
          System.out.println("Pinoon kokonaisluku: " + chars.get(i)); // Testiprinttaukset
        } else {
        cs.push((String) chars.get(i));
        System.out.println("Pinoon merkki: " + chars.get(i));
        }
      }
      chars.clear(); // Tyhjennet‰‰n lista
      
      /* Alkioiden luku ja tulkinta
       * Literaali --> datapino
       * 
       * Eli t‰m‰n while silmukan sis‰‰n kaikki k‰skyn ohjaukset
       * Esin k‰skypinosta luetaan drop --> datapinosta literaali tai muuttuja
       * ja syˆtet‰‰n drop-k‰skylle luotuun metodiin.
       * */
      while (!cs.empty()){
        temp = cs.pop();
        
        // Luku --> pinoon
        if (temp instanceof Integer){
          ds.push((int) temp);     
        }
        // Tulostus 
        else if (temp.equals(".")) System.out.println("Tuloste :" + ds.peek());  

        // Aritmeettiset operaatiot
        else if ( temp.equals("+") ||temp.equals("-") ||temp.equals("*") ||temp.equals("/")){
          n1 = (int)ds.pop();
          n2 = (int)ds.pop();
          ds.push((int) comp.compute(n2,n1,(String)temp)); // Tulos takaisin datapinoon
        } 
        // Vertailut
        else if (temp.equals("=") ||temp.equals("<") ||temp.equals(">")){
          n1 = (int)ds.pop();
          n2 = (int)ds.pop();
          ds.push((boolean) comp.compare(n2,n1,(String)temp));
        }
        // Logiikka
        // (Kys. konektiivijoukko riitt‰‰, muut voidaan toteuttaa n‰ill‰)
        else if (temp.equals("and") || temp.equals("or") || temp.equals("not")){
          b1 = (boolean)ds.pop();
          b2 = (boolean)ds.pop();
          ds.push((boolean) comp.connective(n2,n1,(String)temp));
        }
      }
        
      } else {
        // 
      String[] temp = input.split(" ");
      Collections.addAll(chars,temp);
      System.out.println("Input values: " + chars); //ArrayLIst testprint
    }
  }
      //Testi onko alkio kokonaisluku
      public boolean isInteger(String input){ 
        try {
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