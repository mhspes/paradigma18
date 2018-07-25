/* Luokka aritmettisille operaatioille, logiikalle ja vertailuille.
 * KeyListener-oliosta alkiot n2 ja n1 tai vain n1, suoritetaan metodi,
 * paluuarvo KeyListener-oliolle datapinon p‰‰llimm‰iseksi.
 * */

import java.util.Stack;
import java.lang.Math;    
class Compute{

  private int n1;            // Pinon p‰‰llimm‰iset alkiot
  private int n2;
  private boolean b1;        // Pinon p‰‰llimm‰iset (boolean) arvot
  private boolean b2;
  private String s1;
  private String s2;
  private Object result;
  private Math math;
  
  /* Bin‰‰rioperaatiot +,-,*, /, mod
   * @.pre 
   * @.post
   * 
   * */
  protected int compute(Object num2, Object num1, String operand){
    n2 = (int)num2;
    n1 = (int)num1; 
    switch (operand) {
      case "+":
        result = n2 + n1;
        break;
      case "-":
        result = n2 - n1;
        break;
      case "*":
        result = n2 * n1;
        break;
      case "/":
        result = n2 / n1;
        break;
      case "mod":
        try{
        result = math.floorMod(n2,n1);
      } catch (ArithmeticException e) { System.err.println("Error - zero divisor");}
      break;
    }
    return (int)result;
  }
  
  /* Vertailut ==, <, >, !=
   * ( == ja != toimivat myˆs merkkijonoille )
   * @.pre 
   * @.post
   * 
   * */
  protected boolean compare(Object num2, Object num1, String operand){
    
    // 
    if (num2 instanceof String && num1 instanceof String && operand.equals("==")){
      s1 = (String)num1;
      s2 = (String)num2;
      if(s1.equals(s2)){
        result = true;
      } else {
        result = false;
      }
    } else {
      
      n2 = (int)num2;
      n1 = (int)num1;
      result = false;
      
      switch (operand) {
        case "==":
          result = (n2 == n1);
          break;
        case "<":
          result = (n2 < n1);      
          break;
        case ">":
          result = (n2 > n1);
          break;
        case "!=":
          result = (n2 != n1);
          break;    
      } 
    }
    return (boolean)result;   
  }
  
  /* Logiikka (and, or)
   * @.pre
   * @.post
   * 
   * */
  protected boolean connective(Object bool2, Object bool1, String operand){
    b2 = (boolean)bool2;
    b1 = (boolean)bool1;
    result = false;
    
    switch (operand) {
      case "or":
        result = b2 || b1;
        break;
      case "and":
        result = b2 && b1;      
        break;
    }
    return (boolean)result;   
  }
  /* Logiikka, not
   * @.pre
   * @.post
   * */
  protected boolean connective(Object bool1){
    b1 = (boolean)bool1;
    return !(boolean)bool1;
  }
}