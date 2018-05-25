import java.util.Stack;
import java.lang.NumberFormatException;
import java.util.EmptyStackException;

class Compute{

  // Oletus: arvoina vain kokonaislukuja tai symboli joukosta { + - * / }
  private String sequence; 
  private Stack<Integer> tempDS;
  private Stack<String> tempCS;
  private boolean running;
  
  //Laskutoimitukset
  protected String compute(Stack<String> cs){
    tempCS = cs;
    tempDS = new Stack<Integer>();
    int result;
    int n1;
    int n2;

    while (!tempDS.empty()){
   
      if (isInteger(tempCS.peek())){
        tempDS.push((Integer) Integer.parseInt(tempCS.peek()));     
      }
      
      else {
        try {
        switch (tempCS.peek()) {
        case "+":
          n1 = tempDS.pop();
          n2 = tempDS.pop();
          result = n2 + n1;
          tempDS.push((Integer) result);
          System.out.println(n2 + " + " + n1 + "= " + result);
          break;
        case "-":
          n1 = tempDS.pop();
          n2 = tempDS.pop();
          result = n2 - n1;      
          tempDS.push((Integer) result);
          System.out.println(n2 + " - " + n1 + "= " + result);
          break;
        case "*":
          n1 = tempDS.pop();
          n2 = tempDS.pop();
          result = n2 * n1;
          tempDS.push((Integer) result);
          System.out.println(n2 + " * " + n1 + "= " + result);
          break;    
        case "/":
          n1 = tempDS.pop();
          n2 = tempDS.pop();
          result = n2 / n1;
          tempDS.push((Integer) result);
          System.out.println(n2 + " / " + n1 + "= " + result);
          break;
        case ".":
          System.out.println(tempDS.peek());
          break;
        }
        } catch (EmptyStackException e ) { System.err.println("Virhe:" + e.getMessage()); }
      }
    }
    result = tempDS.pop(); // Pinon tulisi olla nyt tyhj‰
    return Integer.toString(result);  // Palautetaan laskettu arvo
    
  }
  // Testi onko symboli luku vai bin‰‰rioperaattori
  public boolean isInteger(String input) {
    try {
        Integer.parseInt(input);
        return true;
    }
    catch(NumberFormatException e) {
        return false;
    }
  }
}