import java.util.Stack;
import java.lang.NumberFormatException;
import java.util.EmptyStackException;
    
class Compute{

  private int n1;  // Pinon p‰‰llimm‰iset alkiot
  private int n2;  
  private int result0;
  private boolean result1;

  /* Oletuskonstruktori riitt‰‰, arvot luodaan ajon aikana
  *
  * Aritmettiset operaatiot 
  * n1 pinon p‰‰llimm‰isin, n2 seuraava arvo ja operaatiot n2 <operaatio> n1 
  * Metodi suorittaa yhden operaation ja paluuarvo pinon p‰‰llimm‰iseksi 
  */
  protected int compute(int n2, int n1, String operand){
    this.n2 = n2;
    this.n1 = n1; 
    System.out.println("Operandi : " + operand + " Arvot: " + n2 + " ja " + n1);
    switch (operand) {
      case "+":
          result0 = n2 + n1;
          System.out.println("Lasketaan :" + n2 + "+" + n1 + "=" + result0);
          break;
        case "-":
          result0 = n2 - n1;
          System.out.println("Lasketaan :" + n2 + "-" + n1 + "=" + result0);
          break;
        case "*":
          result0 = n2 * n1;
          System.out.println("Lasketaan :" + n2 + "*" + n1 + "=" + result0);
          break;
        case "/":
          result0 = n2 / n1;
          System.out.println("Lasketaan :" + n2 + "/" + n1 + "=" + result0);
          break;
        }
    return result0;
  }
  
  // Vertailut
  protected boolean compare(int n1, int n2, String operand){
    this.n2 = n2;
    this.n1 = n1;
    result1 = false;
    
    switch (operand) {
      case "==":
          result1 = n2 == n1;
          break;
        case "<":
          result1 = n2 < n1;      
          break;
        case ">":
          result1 = n2 > n1;
          break;
        case "!=":
          result1 = n2 != n1;
          break;    
        }
      
    return result1;   
  }
  
  protected boolean connective(int n1, int n2, String operand){
    this.n2 = n2;
    this.n1 = n1;
    result1 = false;
    
    switch (operand) {
      case "or":
          result1 = n2 == n1;
          break;
        case "and":
          result1 = n2 < n1;      
          break;
        case "not":
          result1 = n2 > n1;
          break;  
        }
    if (result1){ 
      System.out.println(n2 + " " + operand + " " + n1 + " is true "); 
    } else {
      System.out.println(n2 + " " + operand + " " + n1 + " is false ");
    }
    return result1;   
  }
}