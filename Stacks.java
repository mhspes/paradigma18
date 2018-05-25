import java.util.Stack;
import java.util.Scanner;

class Stacks{
 
  private Stack<String> cs; // komentopino
  private Stack<String> ds; // datapino
  
  //Konstruktori
  public Stacks(){
  Stack<String> cs = new Stack<String>();
  Stack<String> ds = new Stack<String>();  
  }
  
  //Metodit push, pop, peek
  public void pushCS(String val){
    cs.push((String) val);
  }
  public void pushDS(String val){
    ds.push((String) val);
  }
  public String popCS(){
    return cs.pop();
  }
  public String popDS(){
   return ds.pop(); 
  }
  public String peekCS(){
    return cs.peek();
  }
  public String peekDS(){
    return ds.peek();
  }
  public Stack<String> getCS(){
    return cs;
  }
  public Stack<String> getDS(){
    return ds;
  }
}