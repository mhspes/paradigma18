import java.util.Stack;
import java.lang.Math;    
class Compute{

  private int n1;  // Pinon päällimmäiset alkiot
  private int n2;
  private boolean b1; // Pinon päällimmäiset (boolean) arvot
  private boolean b2;
  private Object result;
  private Math math;

  /* Oletuskonstruktori riittää, arvot luodaan ajon aikana
  *
  * Aritmettiset operaatiot 
  * n1 pinon päällimmäisin, n2 seuraava arvo ja operaatiot n2 <operaatio> n1 
  * Metodi suorittaa yhden operaation ja paluuarvo pinon päällimmäiseksi 
  */
  protected int compute(Object num2, Object num1, String operand){
    n2 = (int)num2;
    n1 = (int)num1; 
    System.out.println("Operandi : " + operand + " Arvot: " + n2 + " ja " + n1);
    switch (operand) {
      case "+":
          result = n2 + n1;
          System.out.println("Lasketaan :" + n2 + "+" + n1 + "=" + result);
          break;
      case "-":
          result = n2 - n1;
          System.out.println("Lasketaan :" + n2 + "-" + n1 + "=" + result);
          break;
      case "*":
          result = n2 * n1;
          System.out.println("Lasketaan :" + n2 + "*" + n1 + "=" + result);
          break;
      case "/":
          result = n2 / n1;
          System.out.println("Lasketaan :" + n2 + "/" + n1 + "=" + result);
          break;
      case "mod":
        try{
        result = math.floorMod(n2,n1);
        System.out.println("Lasketaan :" + n2 + "mod(" + n1 + ") =" + result);
      } catch (ArithmeticException e) { System.err.println("Virhe - nollajakaja");}
          break;
        }
    return (int)result;
  }
  
  // Vertailut
  protected boolean compare(Object num2, Object num1, String operand){
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
    System.out.println(n2 + " " + operand + " " + n1 + " equals: " + result);  
    return (boolean)result;   
  }
  // Totuurarvo and, or
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
    if ((boolean)result){ 
      System.out.println(b2 + " " + operand.toUpperCase() + " " + b1 + " is true "); 
    } else {
      System.out.println(b2 + " " + operand.toUpperCase() + " " + b1 + " is false ");
    }
    return (boolean)result;   
  }
  // Tootuusarvo not
  protected boolean connective(Object bool1){
   b1 = (boolean)bool1;
   System.out.println("Value is negated: " + bool1);
   return !(boolean)bool1;
  }
}