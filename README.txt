Model Class: ModelConnect 4
Changes:
- 2 variables added: Added to represents the rows and columns of the game board.
	- static final int R=6; 
	 - static final int C=7; 

- Added method named initializationBoard(), used to re-initialize the game board everytime user wants to play again.
- Datatypes of methods CheckWinner() and CheckDraw() changed to boolean.
- GameOver() method deleted because when game is won or in a state of draw message is already provided to player indicating the state of the game, and game is also over.

Controller Class: ControllerConnect4
Changes:
- 3 new variables added:
	private ModelConnect4 model : Added to call methods from the Model 
	private Connect4 view: Added to call methods from the View
	private ResourceBundle resourceBundle

- Overloaded contructor added to initialize the model and view variables.
- ValidatePlay() method datatype changed to boolean, to use to perfom  if-else statements.
- Added loadLanguage(String language)  methods to attempt changing the language from english to persian, using a file that contains key-value pair translations.


View Class: Connect4
Changes:
- Re-structured the Connect4(View) class, all methods were in previously under the main function, now restructured into private methods to organize class.
	- addLeftPanel() : All implemententation in the west of GUI are found here, includes timer, player labels etc.
	- addRightPanel():  All implemententation in the east  of GUI are found here , includes message box, send button etc.
	- addCenterPanel():  All implemententation in the west of GUI are found here , includes game board and tokens.
	- addMenuBar():  All implemententation in the regarding the menu are found here .

- Added new  variables: Used for switching and resetting the player labels.
	private int player1=0; 
	private int player2=0;

- Added method for splash screen to appear before game execution named WaitScreen(), shows before game execution.
- Changed displayWinner() to displayWinner(int player).
- Renamed updateCurrentPlayer() to updatePlayerLabel(), because fits better for method implementation.
- Changed name of menu item "load" to "reset", when user interacts with menu item reset, the board and player label should be resetted.
- Added 4 new methods corresponding to board and tokens management: 
	- handleButtonClick(int column) : handles the interactions with buttons. Checks if user performed a valid play by calling validatePlay() in the controller class. Also update/increments the moves each player
				performs.
	- updateBoard(int[][] gameBoard): Updates the board to show the tokens being placed on the board.
	- createTokenIcon(int player): Creates graphics and size of tokens, alternating tokens between the players.
	- playerTokenColor(int currentPlayer): Manages the color of tokens for each player.
	
