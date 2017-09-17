import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author James Spargo
 * This class controls the game play for the Max Connect-Four game. 
 * To compile the program, use the following command from the maxConnectFour directory:
 * javac *.java
 *
 * the usage to run the program is as follows:
 * ( again, from the maxConnectFour directory )
 *
 *  -- for interactive mode:
 * java MaxConnectFour interactive [ input_file ] [ computer-next / human-next ] [ search depth]
 *
 * -- for one move mode
 * java maxConnectFour.MaxConnectFour one-move [ input_file ] [ output_file ] [ search depth]
 * 
 * description of arguments: 
 *  [ input_file ]
 *  -- the path and filename of the input file for the game
 *  
 *  [ computer-next / human-next ]
 *  -- the entity to make the next move. either computer or human. can be abbreviated to either C or H. This is only used in interactive mode
 *  
 *  [ output_file ]
 *  -- the path and filename of the output file for the game.  this is only used in one-move mode
 *  
 *  [ search depth ]
 *  -- the depth of the minimax search algorithm
 * 
 *   
 */

public class maxconnect4
{
  public static void main(String[] args) 
  {
    // check for the correct number of arguments
    if( args.length != 4 ) 
    {
      System.out.println("Four command-line arguments are needed:\n"
                         + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                         + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

      exit_function( 0 );
     }
	HashMap<Integer,Integer> columns=new HashMap<Integer,Integer>();
	
    // parse the input arguments
    String game_mode = args[0].toString();				// the game mode
    String input = args[1].toString();					// the input game file
    int depthLevel = Integer.parseInt( args[3] );  		// the depth level of the ai search
		
    // create and initialize the game board
    GameBoard currentGame = new GameBoard( input );
    
    // create the Ai Player
    AiPlayer calculon = new AiPlayer(depthLevel);
		
    //  variables to keep up with the game
    int playColumn = 99;				//  the players choice of column to play
    boolean playMade = false;			//  set to true once a play has been made

    if( !game_mode.equalsIgnoreCase( "interactive" ) &&  !game_mode.equalsIgnoreCase( "one-move" )) 
    {
    	 System.out.println( "\n" + game_mode + " is an unrecognized game mode \n try again. \n" );
         return;
    } 
	
    ///initialise hashmap for user check for columns values
     for(int i=1;i<=7;i++)
    	 columns.put(i, i);
    

    /////////////   one-move mode ///////////
    // get the output file name
    String output = args[2].toString();				// the output game file
    
    System.out.print("\nMaxConnect-4 game\n");
    System.out.print("game state before move:\n");
    
    //print the current game board
    currentGame.printGameBoard();
    
    
    if( currentGame.getPieceCount() > 42 )
    {
    	System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
    }
   int currentTurn=currentGame.getCurrentTurn();
   int opponentTurn=(currentTurn==1)?2:1;
   if(game_mode.equalsIgnoreCase( "interactive" ))
   {
	   
	   if(output.equalsIgnoreCase("computer-next"))
	   {
		// print the current scores
		    System.out.println( "Score: CPU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
					", You("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
		   while(currentGame.getPieceCount() <= 42)
		   {
			   
			// AI play - random play
			   if(currentGame.getPieceCount()==42)
			   {
				   System.out.println("---------------GAME OVER-------------");
				   System.out.println("---------------Final Game State-------------");
				   currentGame.printGameBoard();
				   if(currentGame.getScore( 1 )==currentGame.getScore( 2 ))
				   {
					   String winner= "Game Tied";
					   System.out.println( "Score: CPU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
								", You("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
					   System.out.println(winner);
					   break;
				   }
				   else{
					   String winner= (currentGame.getScore( currentTurn ) > currentGame.getScore( opponentTurn ))?"CPU":"You";
					   System.out.println( "Score: CPU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
								", You("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
					   System.out.println(winner+ " Win");
					   break;
				   }
			   }
				playColumn = calculon.findBestPlay( currentGame ); 
				
				// play the piece
				currentGame.playPiece( playColumn ,game_mode, 1);
				currentGame.printGameBoard();
				System.out.println( "Score: CPU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
						", You("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
				
				if(currentGame.getPieceCount()==42)
				   {
					System.out.println("---------------GAME OVER-------------");
					System.out.println("---------------Final Game State-------------");
					currentGame.printGameBoard();
					   if(currentGame.getScore( 1 )==currentGame.getScore( 2 ))
					   {
						   String winner= "Game Tied";
						   System.out.println( "Score: CPU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
									", You("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
						   System.out.println(winner);
						   break;
					   }
					   else{
						   String winner= (currentGame.getScore( currentTurn ) > currentGame.getScore( opponentTurn ))?"CPU":"You";
						   System.out.println( "Score: CPU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
									", You("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
						   System.out.println(winner+ " Win");
						   break;
					   }
				   }
				
				while(true){
				//prompt user to play
				System.out.println("Choose ur move between [1 2 3 4 5 6 7]:");
				Scanner sc = new Scanner(System.in);
				playColumn=sc.nextInt();
				
				if(columns.containsKey(playColumn) && currentGame.getGameBoard()[0][playColumn-1]==0)
				{
					//play user
					currentGame.playPiece( playColumn-1 ,game_mode, 2);
					break;
				}
				else
				{
					System.out.println("Not a valid input or the column is full");
					
					
				}
							
				}
		   }
	   }
		   else if(output.equalsIgnoreCase("human-next"))
		   {
			// print the current scores
			   System.out.println( "Score: YOU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
						", CPU("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
			   while(currentGame.getPieceCount() <= 42)
			   {
				   if(currentGame.getPieceCount()==42)
				   {
					System.out.println("---------------GAME OVER-------------");
					System.out.println("---------------Final Game State-------------");
					currentGame.printGameBoard();
					   if(currentGame.getScore( 1 )==currentGame.getScore( 2 ))
					   {
						   String winner= "Game Tied";
						   System.out.println( "Score: YOU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
									", CPU("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
						   System.out.println(winner);
						   break;
					   }
					   else{
						   String winner= (currentGame.getScore( currentTurn ) > currentGame.getScore( opponentTurn ))?"You":"CPU";
						   System.out.println( "Score: YOU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
									", CPU("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
						   System.out.println(winner + "Wins");
						   break;
					   }
				   }
				   
					
					
					while(true){
						//prompt user to play
						System.out.println("Choose ur move between [1 2 3 4 5 6 7]:");
						Scanner sc = new Scanner(System.in);
						playColumn=sc.nextInt();
						
						if(columns.containsKey(playColumn) && currentGame.getGameBoard()[0][playColumn-1]==0)
						{
							//play user
							currentGame.playPiece( playColumn-1 ,game_mode, 1);
							break;
						}
						else
						{
							System.out.println("Not a valid input or the column is full");
							
							
						}
									
						}
					
					
					if(currentGame.getPieceCount()==42)
					   {
						System.out.println("---------------GAME OVER-------------");
						System.out.println("---------------Final Game State-------------");
						currentGame.printGameBoard();
						   if(currentGame.getScore( 1 )==currentGame.getScore( 2 ))
						   {
							   String winner= "Game Tied";
							   System.out.println( "Score: YOU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
										", CPU("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
							   System.out.println(winner);
							   break;
						   }
						   else{
							   String winner= (currentGame.getScore( currentTurn ) > currentGame.getScore( opponentTurn ))?"You":"CPU";
							   System.out.println( "Score: YOU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
										", CPU("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
							   System.out.println(winner+ " Win");
							   break;
						   }
					   }
					
				   // AI play - random play
					playColumn = calculon.findBestPlay( currentGame ); 
					
					// play the piece
					currentGame.playPiece( playColumn ,game_mode, 2);
					currentGame.printGameBoard();
					System.out.println( "Score: YOU("+currentTurn+") = " + currentGame.getScore( currentTurn ) +
							", CPU("+opponentTurn+") = " + currentGame.getScore(opponentTurn ) + "\n " );
					//prompt user to play
					
				
				   
			   }
		   }
		   else
			   System.out.println("Arg[3]- computer-next or human-text mentioned incorrectly");
	   
	   
   }
   else{
    
	  
    // ****************** this chunk of code makes the computer play
    if( currentGame.getPieceCount() < 42 ) 
    {
        int current_player = currentGame.getCurrentTurn();
	// AI play - random play
	playColumn = calculon.findBestPlay( currentGame ); 
	
	// play the piece
	currentGame.playPiece( playColumn , game_mode, 0);
        
        currentGame.printGameBoard();
    
        
        
        currentGame.printGameBoardToFile( output );
       
    } 
    else 
    {
	System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
    }
   }
    //************************** end computer play
			
    
    return;
    
} // end of main()
	
  /**
   * This method is used when to exit the program prematurly.
   * @param value an integer that is returned to the system when the program exits.
   */
  private static void exit_function( int value )
  {
      System.out.println("exiting from MaxConnectFour.java!\n\n");
      System.exit( value );
  }
} // end of class connectFour