// Main-metodi Forth- tulkin toiminnalle
// Suoritus ilman parametreja ( java ForthCompiler )

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

class ForthCompiler{
  public static void main(String[] args){

    Scanner sc = new Scanner(System.in); 
    boolean running = true;      
    boolean input = false;
    String cmd;
    String line;      
    Decoder kl = new Decoder();      // Olio syötteiden tulkintaan

    help();
    
    while(running){
      cmd = sc.nextLine();

      if( cmd.length() > 4 && cmd.substring(cmd.length()-4,cmd.length()).equalsIgnoreCase(".txt")){
        
        try (BufferedReader br = new BufferedReader(new FileReader(cmd))) {            
          line = br.readLine();
          while (line != null){
            if(line.equals("") || line.substring(0,1).equals("?")){ // Jätetään tyhjät rivit ja kommentit huomiotta
            } else {
              kl.listen(line);                                      // Syötetään hyväksytyt rivit keyListener-oliolle
            }               
            line = br.readLine();
          }
          br.close();
          System.out.println("File loaded.");
        } catch (Exception f){System.out.println(f);}
      }
      
      // Manuaalinen syöte
      else if(cmd.equalsIgnoreCase("input")){
        System.out.println("Manual input. Type 'run' to execute, 'end' to end input.");
        input = true; 
        while (input){
          if (cmd.equals("end")){
            System.out.println("Manual input stopped.");
            kl.reset();
            input = false;
            break;
          }
          else if(cmd.equalsIgnoreCase("reset")){
            kl.reset();   
            System.out.println("Stacks cleared.");
          }
          cmd = sc.nextLine();
          kl.listen(cmd);         
        }
      }
      
      // Koodin toteutus
      else if(cmd.equalsIgnoreCase("run")){
        kl.listen(cmd);   
      }
      // Pinojen tulostus (testausmielessä)
      else if(cmd.equalsIgnoreCase("show")){
        kl.listen(cmd);   
      }
      // Ladatun koodin & pinojen resetointi
      else if(cmd.equalsIgnoreCase("reset")){
        kl.reset();   
        System.out.println("File & stacks cleared.");
      }

      else if (cmd.equalsIgnoreCase("help")) help();

      else if(cmd.equalsIgnoreCase("exit")){
        running = false;
        System.out.println("Ending.");
      }
      else{
        System.out.println("Input not recognized.");
      }
    }
  }
  // Syöttöpaneelin käyttöohje
  static void help(){
    System.out.println("################# COMMANDS #################");
    System.out.println("Read from txt-file: <name>.txt\nRemove loaded file and clear stacks: reset\nManual input: input");
    System.out.println("Run program: run\nStop manual input: end\nExit: exit\nShow commands: help\n############################################\n");
  }
}