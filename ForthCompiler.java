import java.util.Stack;
import java.util.Scanner;

class ForthCompiler{
  public static void main(String[] args){
    
    // String sequence = "15 7 1 1 + - / 3 * 2 1 1 + + - .";  // Testisyöte, tulos 5
    
    Scanner sc = new Scanner(System.in);
    boolean running=true;
    boolean input;
    boolean read;
    String cmd;
    
    KeyListener kl = new KeyListener();
    
    System.out.println("Commands:");
    System.out.println("Manual input: input\nRead from file: read\nExit: exit\nEnd input type: end");
    
    while(running){
      cmd = sc.nextLine();
      
      if(cmd.equals("input")){
        input = true;
        System.out.println("Manual input chosen");
        while(input){
          kl.listen(sc.nextLine());
          input = false;
        } 
      }
      
      else if(cmd.equals("read")){
        System.out.println("Read from file chosen");
      }
      else if(cmd.equals("exit")){
        running = false;
        System.out.println("Ending.");
      }
      else{
        System.out.println("Input not recognized.");
      }
    }
  }
  
}