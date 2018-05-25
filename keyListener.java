import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

class KeyListener{
  
  private String input;
  private ArrayList<String> chars;
  protected Stacks stacks;
  protected Compute comp;
  private String temp_int;
  
  public KeyListener(){
    chars = new ArrayList<String>();
    stacks = new Stacks();
  }
  
  public void listen(String input){
    this.input = input;
    
   /* if (input.equals("end")){ 
      System.out.println("Manual input stopped.");

    } */
      
    if (input.equals("RUN")){
      System.out.println("Testiajo:");
      for (int i = chars.size()-1 ; 0 <= i ; i-- ){
        stacks.pushCS(chars.get(i));
      }
      temp_int = comp.compute(stacks.getCS());
      stacks.pushDS(temp_int);
      System.out.println("Tulos: " + stacks.peekDS());
      chars.clear();
    }
  
  String[] temp = input.split(" ");
  Collections.addAll(chars,temp);
  System.out.println("Input values: " + chars); //ArrayLIst testprint 
  }
}
  
  
  
  
  /*
   *   // Katsotaan onko alussa ":"
  if ( !input.charAt[0].equals(':')){
    System.out.println("Syntax error, \":\" not found"); 
    }
  else {
    if ( input.charAt[input.length()-1] 
    for (int i = 0 ; i < input.length() ; i++ ){
      if( input.charAt[i])
    }*/