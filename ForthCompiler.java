import java.util.Scanner;

/* Syöttöpaneeli kääntäjälle (main-metodi). 
 * Syötteet komentorivistä tai tiedostosta.
 * 
 * 
 * TO DO esim:
 * Jossain vaiheessa graafinen käyttöliittymä? (esim swing)
 * Tietorakenne, rekisteri ja datapaikat muuttujille (Hashmap?)
 * Tiedostosta luku? oma read-metodi tälle.
 * Metodit
 * Piirto (Rami)
 * Ei vielä toteutettu lauseiden erottelua : ; merkein
 * 
 * Testisyötteitä: 
 * "15 7 1 1 + - / 3 * 2 1 1 + + - ."  (Tulos 5)
 * "5 6 < ." (false)
 * "true true and ." (true)
 * 
 * */


class ForthCompiler{
  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    boolean running=true;   // Kääntäjä toteutuksessa. False--> lopetetaan ohjelma
    boolean input;          // Manuaalinen syöte (komentorivi)
    boolean read;           // Tiedostosta luku
    String cmd;             // Komento (input, read, exit, end), muu syöte -> virhe.
    
    KeyListener kl = new KeyListener(); // Olio komentorivisyötteelle
    
    System.out.println("\nCommands:");
    System.out.println("Manual input: input\nRead from file: read\nEnd input type: end\nRun program: run");
    System.out.println("Exit: exit\n");
    
    while(running){
      cmd = sc.nextLine();
      
      /* Valitaan komentorivisyöte 
       * end --> lopetetaan. */
      if(cmd.equals("input")){
        input = true;
        System.out.println("Manual input chosen. Type commands and 'run'.");
        while(input){
          cmd = sc.nextLine();
          if(cmd.equals("end")){
            System.out.println("Manual input stopped.");
              input = false; 
          } else {
          kl.listen(cmd);
          }
        } 
      }
      // Tiedostosta luku
      // Vielä vaiheessa..
      else if(cmd.equals("read")){
        System.out.println("Read from file chosen");
        fileRead();
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
  // Read to-do
  static void fileRead(){}
}