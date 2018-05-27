import java.util.Scanner;

/* Sy�tt�paneeli k��nt�j�lle (main-metodi). 
 * Sy�tteet komentorivist� tai tiedostosta.
 * 
 * 
 * TO DO esim:
 * Jossain vaiheessa graafinen k�ytt�liittym�? (esim swing)
 * Tietorakenne, rekisteri ja datapaikat muuttujille (Hashmap?)
 * Tiedostosta luku? oma read-metodi t�lle.
 * Metodit
 * Piirto (Rami)
 * Ei viel� toteutettu lauseiden erottelua : ; merkein
 * 
 * Testisy�tteit�: 
 * "15 7 1 1 + - / 3 * 2 1 1 + + - ."  (Tulos 5)
 * "5 6 < ." (false)
 * "true true and ." (true)
 * 
 * */


class ForthCompiler{
  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    boolean running=true;   // K��nt�j� toteutuksessa. False--> lopetetaan ohjelma
    boolean input;          // Manuaalinen sy�te (komentorivi)
    boolean read;           // Tiedostosta luku
    String cmd;             // Komento (input, read, exit, end), muu sy�te -> virhe.
    
    KeyListener kl = new KeyListener(); // Olio komentorivisy�tteelle
    
    System.out.println("\nCommands:");
    System.out.println("Manual input: input\nRead from file: read\nEnd input type: end\nRun program: run");
    System.out.println("Exit: exit\n");
    
    while(running){
      cmd = sc.nextLine();
      
      /* Valitaan komentorivisy�te 
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
      // Viel� vaiheessa..
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