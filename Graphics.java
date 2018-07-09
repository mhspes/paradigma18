import java.util.ArrayList;

class Graphics{
  
  private String v‰ri;
  private int x1;
  private int y1;
  private int x2;
  private int y2;  
  private int x3;
  private int y3;
  
  private boolean format;
  private String[] v‰rit;
  
  // Konstruktori
  protected Graphics(){
    String[] v‰rit = {"red","blue","green","black","white","yellow","brown","purple"}; 
// lis‰‰ tarvittaessa
  }
  
  /* Metodi pisteen piirt‰miseen
   * @.pre
   * @.post
   * 
   * */
  protected void point(Object x1, Object y1, Object v‰ri){
    this.x1= (int)x1;
    this.y1= (int)y1;
    this.v‰ri = (String)v‰ri;
    
    // Toiminnallisuus t‰h‰n
  }
  /* Metodi viivan piirt‰miseen
   * @.pre
   * @.post
   * 
   * */
  protected void line(Object x1, Object y1, Object x2, Object y2, Object v‰ri){
    this.x1= (int)x1;
    this.y1= (int)y1;
    this.x2= (int)x2;
    this.y2= (int)y2;
    this.v‰ri = (String)v‰ri;
  
  }
  /* Metodi ympyr‰n piirt‰miseen
   * @.pre
   * @.post
   * 
   * */  
  protected void circle(Object x1, Object y1, Object x3, Object v‰ri){
    this.x1= (int)x1;
    this.y1= (int)y1;
    this.x3= (int)x3; //s‰de
    this.v‰ri = (String)v‰ri;
  }
  /* Metodi kolmion piirt‰miseen
   * @.pre
   * @.post
   * 
   * */
  protected void triangle(Object x1, Object y1, Object x2, Object y2, Object x3, Object y3, Object v‰ri){
    this.x1= (int)x1;
    this.y1= (int)y1;
    this.x2= (int)x2;
    this.y2= (int)y2;
    this.x3= (int)x3;
    this.y3= (int)y3;
    this.v‰ri = (String)v‰ri;
  
  }
    /* Metodi piirtoalueen tyhjennykseen
   * @.pre
   * @.post
   * 
   * */
  protected void clear(){
  }
  
  /* Metodi joka tarkistaa formaatin 
   * esim luvut jollain tietyll‰ v‰lill‰
   * v‰ri tunnistettava 
   * Jtn alku- ja loppuehtoja?
   * @.pre 
   * @.post
   * 
   */
  protected boolean checkFormat(int n1, int n2, int n3, int n4, int n5, int n6, String v‰ri2){
    boolean correctFormat = true;
    
    // Testailut
    
   return correctFormat;
  }
  
}