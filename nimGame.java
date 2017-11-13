/*
 * nimGame.java
 * Seshan, Randy, Rain, Joshua
 * 11/2/2017
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * * *6344552546424nn
 * This program implements the Game of Nim, a game that involves 2 players taking turns taking 1-3 rocks from a pile. The player to take the last rock looses.
 * There are 3 modes in the program: Player v. Easy A.I, Player v. Hard A.I, and Player v. Player.
 * 
 */
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit; //for the sleep/delay stuff.
import java.util.concurrent.ThreadLocalRandom; //fancy randomizer
import java.util.ArrayList; //SO MUCH BETTER THAN NORMAL ARRAYS
class nimGame {
  
  static Scanner scan = new Scanner(System.in);
  static Random rand = new Random();
  
  //declare global variables
  // my rational for global variables is that since this program is 1 file, and fairly simple, global variables just make things eaiser becuase of the methods.
  static String p1Name = "", p2Name = "";
  static int modeSelect = 0; //0 is AI, 1 is Player.
  static int numStones = 0;
  static int playerTurn = 0;
  static int whoWon = 0; //0 for p1, 1 for p2
  static int aiSelect = 0;
  
  // players array
  // I used an ArrayList as opposed to a normal Array since it can grow dynamicly (and we don't know how many players will play)
  static ArrayList<String> players = new ArrayList<String>();
  
  
  public static void main(String args[])
  {
    
    // call the gameMenu method.
    gameMenu();
    
    // main game loop
    while(true){
      
      
      // this if statement seperates the AI game and the PvP game.
      if(modeSelect == 0){
        
        //Hard AI
        if(aiSelect == 1){
          p2Name = "Hard Computer A.I.";
          //player picked AI Game
          System.out.println("----------------------------------------------------");
          System.out.println("What is your name? ");
          p1Name = scan.nextLine();
          
          // game loop (Player v. AI)
          System.out.println ("There are now " + numStones + " stones in the pile.");
          while(true){
            while (numStones > 0) // loop that repeats player and computers turn
            {
              int numStonesTaken = 0;
              
              System.out.println(p1Name + ", how many stones do you take from the pile (1-3): ");
              
              while(true){
                numStonesTaken = nextIntSafe();
                if(numStonesTaken > 0 && numStonesTaken < 4){ // 1-3
                  if(numStonesTaken > numStones){
                    System.out.println("There are not that many stones in the pile! Pick Again:");
                  }else{
                    break;
                  }
                }else{
                  System.out.println("That is not a valid choice! (Pick a number 1, 2 or 3): ");
                }
                
              }
              if(numStones - numStonesTaken <= 0){
                System.out.println("Oh no! " + p1Name + ", you lose the game of Nim! Congrats " + p2Name + " on winning!");
                whoWon = 1;
                break;
              }
              numStones = numStones - numStonesTaken;
              
              playerTurn = 1; //set it to Player 2's Turn
              
              for(int i = 0; i < 50; i++){System.out.print("\n");}
              System.out.println("----------------------------------------------------");
              
              if (numStones == 4) // If statements that help the computer from making mistakes that will cost it the game
              {
                System.out.println ("There are 4 stones. The computer takes 3 stones.");
                numStones = numStones - 3;
                System.out.println ("There are now " + numStones + " stones in the pile.");
              }
              else if (numStones == 3)
              {
                System.out.println ("There are 3 stones. The computer takes 2 stones.");
                numStones= numStones-2 ;
                System.out.println ("There are now " + numStones + " stones in the pile.");
              }
              else if (numStones == 2)
              {
                System.out.println ("There are 2 stones. The computer takes 1 stones.");
                numStones= numStones- 1;
              }
              else if (numStones == 1)
              {
                System.out.println ("There are 1 stones. The computer takes 1 stones. ");
                // the only condition where the computer loses.
                numStones= numStones- 1;
                System.out.println ("There are now " + numStones + " stones in the pile.");
                System.out.println (p1Name + " Wins!");
                whoWon = 0;
                break; 
              }
              else // computer picking random numbers to take
              {
                int  n = rand.nextInt(3) + 1;
                System.out.println ("There are " + numStones + " stones. The computer takes " + n + " stones. ");
                numStones= numStones- n;
                System.out.println ("There are now " + numStones + " stones in the pile.");
              }
              
            }
            break;
          }
        }else if(aiSelect == 0){ //Easy AI
          
          p2Name = "Easy Computer A.I.";
          //player picked AI Game
          System.out.println("----------------------------------------------------");
          System.out.println("What is your name? ");
          p1Name = scan.nextLine();
          
          System.out.println ("There are " + numStones + " stones in the pile.");
          while(true){
            int numStonesTaken = 0;
            System.out.println(p1Name + ", how many stones do you take from the pile (1-3): ");
            
            while(true){
              numStonesTaken = nextIntSafe();
              if(numStonesTaken > 0 && numStonesTaken < 4){ // 1-3
                if(numStonesTaken > numStones){
                  System.out.println("There are not that many stones in the pile! Pick Again:");
                }else{
                  break;
                }
              }else{
                System.out.println("That is not a valid choice! (Pick a number 1, 2 or 3): ");
              }
            }
            if(numStones - numStonesTaken <= 0){
              System.out.println("Oh no! " + p1Name + ", you lose the game of Nim! Congrats " + p2Name + " on winning!");
              whoWon = 1;
              break;
            }
            numStones = numStones - numStonesTaken;
            
            playerTurn = 1; //set it to Player 2's Turn
            
            for(int i = 0; i < 50; i++){System.out.print("\n");}
            System.out.println("----------------------------------------------------");
            int aiRandChoice  = ThreadLocalRandom.current().nextInt(1, 4);
            while(true){
              if(aiRandChoice > numStones){ //this is kind of a bruteforce way to do it but whatever.
                aiRandChoice  = ThreadLocalRandom.current().nextInt(1, 4);
              }else{
                break; 
              }
            }
            
            System.out.println ("There are " + numStones + " stones. The computer takes " + aiRandChoice + " stones. ");
            
            numStones= numStones- aiRandChoice;
            if(numStones <= 0){
              System.out.println("Oh no! " + p2Name + ", you lose the game of Nim! Congrats " + p1Name + " on winning!");
              whoWon = 0;
              break;
            }
            System.out.println ("There are now " + numStones + " stones in the pile.");
          }
        }
        
      } else if(modeSelect == 1){
        System.out.println("----------------------------------------------------");
        //player picked PvP Game.
        System.out.println("What is your name? ");
        p1Name = scan.nextLine();
        
        System.out.println("What is Player 2's name? ");
        p2Name = scan.nextLine();
        // game loop (Player v. Player)
        while(true){
          //local varibles
          int numStonesTaken = 0;
          
          //clear the screen
          for(int i = 0; i < 50; i++){System.out.print("\n");}
          System.out.println("----------------------------------------------------");
          
          System.out.println("There are " + numStones + " stones in the pile.");
          
          if(playerTurn  == 0){ //it is player 1's turn.
            System.out.println(p1Name + ", how many stones do you take from the pile (1-3): ");
            
            while(true){
              numStonesTaken = nextIntSafe();
              if(numStonesTaken > 0 && numStonesTaken < 4){ // 1-3
                if(numStonesTaken > numStones){
                  System.out.println("There are not that many stones in the pile! Pick Again:");
                }else{
                  break;
                }
              }else{
                System.out.println("That is not a valid choice! (Pick a number 1, 2 or 3): ");
              }
            }
            if(numStones - numStonesTaken <= 0){
              System.out.println("Oh no! " + p1Name + ", you lose the game of Nim! Congrats " + p2Name + " on winning!");
              whoWon = 1;
              break;
            }
            numStones = numStones - numStonesTaken;
            playerTurn = 1; //set it to Player 2's Turn
          }else{ //it is player 2's turn.
            System.out.println(p2Name + ", how many stones do you take from the pile (1-3): ");
            
            while(true){
              numStonesTaken = nextIntSafe();
              if(numStonesTaken > 0 && numStonesTaken < 4){ // 1-3
                if(numStonesTaken > numStones){
                  System.out.println("There are not that many stones in the pile! Pick Again:");
                }else{
                  break;
                }
              }else{
                System.out.println("That is not a valid choice! (Pick a number 1, 2 or 3): ");
              }
            }
            if(numStones - numStonesTaken <= 0){
              System.out.println("Oh no! " + p2Name + ", you lose the game of Nim! Congrats " + p1Name + " on winning!");
              whoWon = 0;
              break;
            }
            numStones = numStones - numStonesTaken;
            playerTurn = 0; //set it to Player 2's Turn
          }
        }
      }
      //Enter the score into the ArrayList
      if(whoWon == 0){
        //Player 1 won
        players.add(p1Name + " vs. " + p2Name + ": " + p1Name + " was the Winner!");
      }else{
        players.add(p1Name + " vs. " + p2Name + ": " + p2Name + " was the Winner!");
      }
      
      System.out.println("*********************************************************************");
      System.out.println("Wow! That was an intense game. Do you want to play again? (y/N)");
      String keepPlaying = scan.nextLine();
      if(keepPlaying.equals("y") || keepPlaying.equals("Y")){
        
      }else{
        System.out.println("Okay, goodbye! ");
        break;
      }
      for(int i = 0; i < 50; i++){System.out.print("\n");}
      gameMenu();
    }
    // game has ended!
    for(int i = 0; i < 50; i++){System.out.print("\n");}
    System.out.println("----------------------------------------------------");
    System.out.println("Thank you for playing the Game of Nim! Here are the games played:");
    //print the items in the arraylist
    //LOOK AT HOW NICE ARRAYLISTS ARE!!!
    for (String element : players) {
      System.out.println("** " + element + "\n");
    }
    System.out.println("\nGame of Nim - ICS3U Edition was brought to you by:");
    System.out.println("* Seshan Ravikumar (Creator of Bugs)");
    System.out.println("* Joshua Kuan (waste of time)");
    System.out.println("* Randy Tang (Some Swordsman)");
    System.out.println("* Rain Wei (Emoji Master)");
    System.out.println("----------------------------------------------------");
    
  }
  
  //game menu method
  public static void gameMenu(){
    //game intro
    System.out.println("--------------------------------------------------------");
    System.out.println("|             Welcome to the Game of Nim!              |");
    System.out.println("--------------------------------------------------------");
    System.out.println("| Do you want to play (0) or learn how to play Nim (1) |");
    System.out.println("--------------------------------------------------------");
    
    int learnPlay = nextIntSafe();
    //int learnPlay = nextIntSafe();
    
    // if the player wants to learn how to play
    if(learnPlay == 1){
      for(int i = 0; i < 50; i++){System.out.print("\n");}
      System.out.println("----------------------------------------------------");
      System.out.println("Nim is a fairly simple game involving a pile of stones, heres how to play: ");
      System.out.println("    The game of Nim starts with a random number of stones between 15 and 30. \n    Two players alternate taking turns and on each turn take either 1, 2, or 3 stones from the pile. \n    The player forced to take the last stone loses.");
      
      
      System.out.println("\n");
      System.out.println("Let's see a snippet of the game in action...");
      System.out.println("----------------------------------------------------");
      try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      System.out.println ("There are 6 stones. The computer takes 2 stones. ");
      System.out.println ("There are now 4 stones in the pile.");
      try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      System.out.println();
      System.out.println("Seshan, how many stones do you take from the pile (1-3):");
      try{TimeUnit.SECONDS.sleep(1);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      System.out.println("2\n");
      try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      System.out.println ("There are 2 stones. The computer takes 1 stones. \n");
      try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      System.out.println ("There is now 1 stone in the pile.");
      try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      System.out.println("Seshan, how many stones do you take from the pile (1-3):");
      try{TimeUnit.SECONDS.sleep(1);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      System.out.println("1\n");
      try{TimeUnit.SECONDS.sleep(1);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      System.out.println("Oh no! Seshan, you lose the game of Nim! Congrats Computer A.I on winning!");
      try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      System.out.println("----------------------------------------------------");
      
      System.out.println("And thats it! ;D");
      while(true){
        System.out.println("Ready to start playing? (y/N)");
        
        String readyToPlay = scan.nextLine();
        if(readyToPlay.equals("y") || readyToPlay.equals("Y")){
          break;
        }else{
          System.out.println("Ok... I'll wait!");
        }
      }
    }else if(learnPlay != 0){
      System.out.println("Invalid option!");
      System.out.println("| Do you want to play (0) or learn how to play Nim (1) |");
      while(true){
        learnPlay = nextIntSafe();
        System.out.println("| Do you want to play (0) or learn how to play Nim (1) |");
        if(learnPlay == 1){
          for(int i = 0; i < 50; i++){System.out.print("\n");}
          System.out.println("----------------------------------------------------");
          System.out.println("Nim is a fairly simple game involving a pile of stones, heres how to play: ");
          System.out.println("    The game of Nim starts with a random number of stones between 15 and 30. \n    Two players alternate taking turns and on each turn take either 1, 2, or 3 stones from the pile. \n    The player forced to take the last stone loses.");
          
          System.out.println("\n");
          System.out.println("Let's see a snippet of the game in action...");
          System.out.println("----------------------------------------------------");
          try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
          System.out.println ("There are 6 stones. The computer takes 2 stones. ");
          System.out.println ("There are now 4 stones in the pile.");
          try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
          System.out.println();
          System.out.println("Seshan, how many stones do you take from the pile (1-3):");
          try{TimeUnit.SECONDS.sleep(1);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
          System.out.println("2\n");
          try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
          System.out.println ("There are 2 stones. The computer takes 1 stones. \n");
          try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
          System.out.println ("There is now 1 stone in the pile.");
          try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
          System.out.println("Seshan, how many stones do you take from the pile (1-3):");
          try{TimeUnit.SECONDS.sleep(1);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
          System.out.println("1\n");
          try{TimeUnit.SECONDS.sleep(1);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
          System.out.println("Oh no! Seshan, you lose the game of Nim! Congrats Computer A.I on winning!");
          try{TimeUnit.SECONDS.sleep(2);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
          System.out.println("----------------------------------------------------");
          
          System.out.println("And thats it! ;D");
          while(true){
            System.out.println("Ready to start playing? (y/n)");
            
            String readyToPlay = scan.nextLine();
            if(readyToPlay.equals("y") || readyToPlay.equals("Y")){
              break;
            }else{
              System.out.println("Ok... I'll wait!");
            }
          }
        }else if(learnPlay != 0){
          System.out.println("Invalid option!");
        }else{
          break;
        }
        
      }
    }
    System.out.println("Awesome! Let's go...");
    // pause for 1 seconds before continuing.
    try        
    {
      TimeUnit.SECONDS.sleep(1);
    } 
    catch(InterruptedException ex) 
    {
      Thread.currentThread().interrupt();
    }
    
    for(int i = 0; i < 50; i++){System.out.print("\n");}
    System.out.println("----------------------------------------------------");
    // ask the user if they want PvAI or PvP
    System.out.println("Do you want to play against an A.I. (0) or another Player (1)?");
    while(true){
      modeSelect = nextIntSafe();
      if(modeSelect == 0 || modeSelect == 1){
        break;
      }else{
        System.out.println("That is not a valid choice! (Pick 0 or 1): ");
      }
    }
    
    
    // ask if they want to use an easy or hard AI.
    
    if(modeSelect == 0){
      System.out.println("Easy AI (0) or Hard AI (1)?");
      while(true){
        aiSelect = nextIntSafe();
        if(aiSelect == 0 || aiSelect == 1){
          break;
        }else{
          System.out.println("That is not a valid choice! (Pick 0 or 1): ");
        }
      }
    }
    
    
    //clear the screen
    for(int i = 0; i < 50; i++){System.out.print("\n");}
    
    //init the number of stones.
    numStones  = ThreadLocalRandom.current().nextInt(15, 30);
  }
  
  // This method is a drop in replacement for scan.nextInt(). It uses the nextLine to safely handle all input, and attempts to parse it
  public static int nextIntSafe(){
    int parsedInt = 0;
    while(true){
      String intString = scan.nextLine();
      try{
        parsedInt = Integer.parseInt(intString);
        break;
      }catch (NumberFormatException error){
        System.out.println("That is not a valid integer. Try Again:");
      }
    }
    return parsedInt;
  }
  
}
//done!
