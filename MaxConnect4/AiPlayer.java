import java.util.*;

/**
 * This is the AiPlayer class.  It simulates a minimax player for the max
 * connect four game.
 * The constructor essentially does nothing. 
 * 
 * @author james spargo
 *
 */

public class AiPlayer 
{
    /**
     * The constructor essentially does nothing except instantiate an
     * AiPlayer object.
     *
     */
	int depthLimit=99999;
	int pieceCount=0;
	int player;
	int opponent;
    public AiPlayer(int depth) 
    {
	this.depthLimit=depth;
    }

    private int max(int v1,int v2)
    {
    	return (v1>v2)?v1:v2;
    }
    
    private int min(int v1,int v2)
    {
    	return (v1<v2)?v1:v2;
    }
    
    private int miniMax(GameBoard currentGame,int[][] playBoard, int depth,int alpha,int beta, boolean b) {
    	//currentGame.printGameBoard();
    	//System.out.println("pieceCount"+this.pieceCount);
    	
if(this.pieceCount==42){
    		
    		//String[] s=currentGame.getScore(this.player,this.opponent,depth).split("-");
   		 int player1=currentGame.getScore(this.player);
   		 int player2=currentGame.getScore(this.opponent);
   		// System.out.println("Score:"+player1+"  "+player2);
   		 return player1-player2;
    	}
    	
    	if(this.depthLimit==depth){
    		 String[] s=currentGame.eval(this.player,this.opponent,depth).split("-");
    		 int player1=Integer.parseInt(s[0]);
    		 int player2=Integer.parseInt(s[1]);
    		 //System.out.println("Score:"+player1+"  "+player2);
    		 return player1-player2;
    	}
    	
    	
    	
    		
    	int pieceCount=this.pieceCount;
    	if (b)
        {
    		//System.out.println("Max");
    		//currentGame.printGameBoard();
    		int max=-999999;
    		for(int j=0;j<7;j++)
    		{
    			if(playBoard[0][j] == 0 && pieceCount>1)
    			{
    				for(int k=5;k>=0;k--)
    				{
    					if(playBoard[k][j]==0){
    								playBoard[k][j]= this.player;
    								this.pieceCount++;
    			//					System.out.println("Max_alpha:"+alpha+"_"+depth);
    				//				System.out.println("Max_beta:"+beta+"_"+depth);
    								
    								max=max(max,miniMax(currentGame,playBoard,depth+1,alpha,beta,false));	
    								
    								
    								
    								playBoard[k][j]= 0;
    								this.pieceCount--;
    								if (max >= beta) 
    									return max;
    								alpha = max(alpha, max);
    					//			System.out.println("Max_value:"+max);
    						//		System.out.println("Max_alpha:"+alpha+"_"+depth);
    							//	System.out.println("Max_beta:"+beta+"_"+depth);
    								break;
    								
    								
    					}
    					
    				}
    			}
    		}
    		return max;
        }
    	else
    	{
    	//System.out.println("Min");
    		//currentGame.printGameBoard();
    		int min=999999;
    		for(int j=0;j<7;j++)
    		{
    			if(playBoard[0][j] == 0)
    			{
    				for(int k=5;k>=0;k--)
    				{
    					if(playBoard[k][j]==0){
    								playBoard[k][j]= this.opponent;
    								this.pieceCount++;
    								//System.out.println("Max_alpha:"+alpha+"_"+depth);
    								//System.out.println("Max_beta:"+beta+"_"+depth	);
    								
    								
    								min=min(min,miniMax(currentGame,playBoard,depth+1,alpha,beta,true));	
    							//	System.out.println("Min value:"+min);   
    								//System.out.println("Min_alpha:"+alpha+"_"+depth);
    								//System.out.println("Min_beta:"+beta+"_"+depth);
    								playBoard[k][j]= 0;
    								this.pieceCount--;
    								if (min <= alpha) 
    									return min;
    								beta = min(beta, min);
    								break;
    								
    					}
    					
    				}
    			}
    		}
    		
    		return min;
    	}
    	
		//return 0;
	}
    
    /**
     * This method plays a piece randomly on the board
     * @param currentGame The GameBoard object that is currently being used to
     * play the game.
     * @return an integer indicating which column the AiPlayer would like
     * to play in.
     */
    
    public int findBestPlay( GameBoard currentGame) 
    {
    	//System.out.println(this.depthLimit);
    	int[][] playBoard= currentGame.getGameBoard();
    	this.pieceCount=currentGame.getPieceCount();
    	this.player=currentGame.getCurrentTurn();
    	this.opponent=(currentGame.getCurrentTurn()==1)?2:1;
    	
    	int alpha=-999999;
    	int beta=999999;
    	int max=-999999;
    	int value;
    	//for(int i = 0; i < 6; i++)
    		//playBoard[i] = currentGame.getGameBoard()[i].clone();
    		// start random play code
    	int playChoice=99;
    	int depth=0;
    	
    	//if CPU starts it begins from center
    	if(this.pieceCount==0 || (this.pieceCount==1 && playBoard[5][3]==0)){
    		
    		return 3;
    	}
    		for(int j=0;j<7;j++)
    		{
    			if(playBoard[0][j] == 0)
    			{
    				for(int k=5;k>=0;k--)
    				{
    					if(playBoard[k][j]==0){
    								playBoard[k][j]= this.player;
    								this.pieceCount++;
    								value=miniMax(currentGame,playBoard,depth,alpha,beta,false);	
    								
    					//			System.out.println("max_main:"+value	);
    								playBoard[k][j]= 0;
    								this.pieceCount=currentGame.getPieceCount();
    								
    								
    								alpha = max(alpha, value);
    						//		System.out.println("max_alpha:"+alpha	);
    								if(value>max){
    									max=value;
    									playChoice=j;
    								}
    								
    								break;
    								
    					}
    					
    				}
    			}
    		}
    		
	
	// end random play code
	
	return playChoice;
    }

	
}