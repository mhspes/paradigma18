import java.util.Scanner;

class ForthCompiler{
  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    boolean running = true; // Kääntäjä toteutuksessa. False--> lopetetaan ohjelma
    boolean input;          // Manuaalinen syöte (komentorivi)
    boolean read;           // Tiedostosta luku
    String cmd;             // Komento (input, read, exit, end), muu syöte -> virhe.
    
    KeyListener kl = new KeyListener(); // Olio komentorivisyötteelle
    
    help();
    
    while(running){
      cmd = sc.nextLine();
      
      /* Valitaan komentorivisyöte 
       * end --> lopetetaan. */
      if(cmd.equals("input")){
        input = true;
        System.out.println("Manual input chosen. Type commands and 'run'.");
        while(input){
          cmd = sc.nextLine();
          if(cmd.equals("help")){
            help();
          }
          else if(cmd.equals("end")){
            System.out.println("Manual input stopped.");
            input = false; 
          } else {
          kl.listen(cmd);
          }
        } 
      }
      // Tiedostosta luku
      // In progress..
      else if(cmd.equals("read")){
        read = true;
        System.out.println("Read from file chosen"); 
        System.out.println("Anna tiedoston nimi.");
        while(read){
          cmd = sc.nextLine();
          
          // Tähän filereadit ym
          
          if (cmd.equals("end")){
            System.out.println("Read from file stopped.");
            read = false;   
          }
        }
      }
      else if (cmd.equals("help")) help();
      else if(cmd.equals("exit")){
        running = false;
        System.out.println("Ending.");
      }
      else{
        System.out.println("Input not recognized.");
      }
    }
  }

  static void help(){
    System.out.println("###########################################\nCommands:");
    System.out.println("Manual input: input\nRead from file: read\nEnd input type: end\nRun program: run");
    System.out.println("Print result: .\nExit: exit\nShow stacks: show\n");
    System.out.println("Show commands: help\n###########################################\n");
  }
}