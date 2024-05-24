package CST8221C4;                                                                                                                                                                                     

import javax.swing.JButton;                                                                                                                                                                            
/**
 *  The ModelConnect4 class represents the model class for the connect 4 game. This class implements the game logic, 
 *  such as the allowing the user to drop their token, identifying the winner of the game by checking the board state,
 *  and identifying draw state by checking board state. This class also includes the logic of alternating players and 
 *  initializing the board, by clearing tokens off the board game.                                                                                                                                                                                                  
 */
public class ModelConnect4 {                                                                                                                                                                           
	private static final int R=6;                                                                                                                                                                      
	private static final int C=7;                                                                                                                                                                      

	int gameBoard[][];                                                                                                                                                                                 
	int currentPlayer;                                                                                                                                                                                 
	int opponent;                                                                                                                                                                                      


	/**                                                                                                                                                                                                
	 * This function checks if a winning state has occurred within the game. A winning state only occurs if one player has                                                                               
	 * 4 consecutive token on the game board. This function identifies/checks if the player has a winning state horizontally, vertically,                                                              
	 * diagonally ascending or diagonally descending. Else returns no winning state, game continues as regular.                                                                                        
	 * @return false, no winning state                                                                                                                                                                 
	 */                                                                                                                                                                                                
	public boolean checkWinner() {                                                                                                                                                                                                                                                                                                                 
		for (int row = 0; row < R; row++) {                                                                                                                                                            
			for (int col = 0; col < C; col++) {                                                                                                                                                        
				int currentPlayer = gameBoard[row][col];                                                                                                                                               
				if (currentPlayer != 0) {                                                                                                                                                              
					// Check horizontally                                                                                                                                                              
					if (col + 3 < C && gameBoard[row][col + 1] == currentPlayer && gameBoard[row][col + 2] == currentPlayer && gameBoard[row][col + 3] == currentPlayer) {                             
						return true;                                                                                                                                                                   
					}                                                                                                                                                                                  
					// Check vertically                                                                                                                                                                
					if (row + 3 < R && gameBoard[row + 1][col] == currentPlayer && gameBoard[row + 2][col] == currentPlayer && gameBoard[row + 3][col] ==currentPlayer ) {                             
						return true;                                                                                                                                                                   
					}                                                                                                                                                                                  
					// Check diagonally (ascending)                                                                                                                                                    
					if (row - 3 >= 0 && col + 3 < C && gameBoard[row - 1][col + 1] == currentPlayer && gameBoard[row - 2][col + 2] == currentPlayer && gameBoard[row - 3][col + 3] == currentPlayer ) {
						return true;                                                                                                                                                                   
					}                                                                                                                                                                                  
					// Check diagonally (descending)                                                                                                                                                   
					if (row + 3 < R && col + 3 < C && gameBoard[row + 1][col + 1] == currentPlayer && gameBoard[row + 2][col + 2] == currentPlayer && gameBoard[row + 3][col + 3] == currentPlayer ) { 
						return true;                                                                                                                                                                   
					}                                                                                                                                                                                  
				}                                                                                                                                                                                      
			}                                                                                                                                                                                          
		}                                                                                                                                                                                              
		return false;                                                                                                                                                                                  


	}                                                                                                                                                                                                  
	/**                                                                                                                                                                                                
	 * This functions checks if a draw has occurred within the game board, a draw state only occurs if there is no more space for tokens in                                                            
	 * the columns.                                                                                                                  
	 * @return true, game is a draw                                                                                                                                                                    
	 */                                                                                                                                                                                                                                                                                                                                                                      
	public boolean checkDraw() {                                                                                                                                                                       
		for(int row = 0; row < R; row++) {                                                                                                                                                         
			for(int col=0; col <C; col++) {                                                                                                                                                        
				if(gameBoard[row][col]==0) {                                                                                                                                                       
					return false; // game not a draw                                                                                                                                               
				}                                                                                                                                                                                  
			}                                                                                                                                                                                      
		}                                                                                                                                                                                          
		return true;                                                                                                                                                                               
	}                                                                                                                                                                                              

	/**
	 * This function allows the user to drop a token at the preferred column chosen by the player. If column is full, then 
	 * does not allow more tokens to be played at that specified column.
	 * @param column, represents column in game board
	 * @return false, indicating column is full
	 */
	public boolean dropPiece(int column) {        
		if (currentPlayer == 1 || currentPlayer == 2) {
		for(int row = R-1; row >=0; row--) {                                                                                                                                                           
			if(gameBoard[row][column]==0) {                                                                                                                                                            
				gameBoard[row][column]=currentPlayer;                                                                                                                                                                                                                                                                                                                   
				return true;                                                                                                                                                                           
			} 
		}
		}                                                                                                                                                                                              
		return false; // column is full                                                                                                                                                                

	}            
	/**
	 * Getter for currentPlayer
	 * @return currentPlayer
	 */
	public int getCurrentPlayer(){                                                                                                                                                                     
                                                                                                                                            
		return currentPlayer;                                                                                                                                                                          

	}                                                                                                                                                                                                  
	/**                                                                                                                                                                                                
	 * This method is used to alternate the players, so each player only gets one play each. This function manages the turns                                                                           
	 * of the players.                                                                                                                                                                                 
	 */                                                                                                                                                                                                
	public void alternatePlayers() {                                                                                                                                                                   
		currentPlayer = (currentPlayer==1)?2:1;                                                                                                                                                        
		                                                                                                                                    
	}                                                                                                                                                                                                  
	                                                                                                                                                                                             
	/**                                                                                                                                                                                                
	 * This function is used to initialize the board so player can play as many times as they would like. Also sets the current player                                                                 
	 * has the player with the first move.                                                                                                                                                             
	 */                                                                                                                                                                                                
	public void initializationBoard() {                                                                                                                                                                
		gameBoard =new int[R][C];                                                                                                                         

		for (int row = 0; row < R; row++) {                                                                                                                                                           
			for (int col = 0; col < C; col++) {                                                                                                                                                    
				gameBoard[row][col] = 0;                                                                                                                             
			}                                                                                                                                                                                      
		}                                                                                                                                                                                          
		currentPlayer = 1;                                                                                                                                                                             
		opponent = 2;                                                                                                                                                                                  
	}                                                                                                                                                                                                  
	/**                                                                                                                                                                                                
	 * This function is the getter for the gameBoard                                                                                                                                                   
	 * @return gameBoard, 2 dimensional array                                                                                                                                                          
	 */                                                                                                                                                                                                
	public int[][] getGameBoard() {                                                                                                                                                                    
		return gameBoard;                                                                                                                                                                              
	}                                                                                                                                                                                                  

}      
