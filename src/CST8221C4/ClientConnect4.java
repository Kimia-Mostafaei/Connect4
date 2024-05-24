package CST8221C4;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnect4 {

    Socket socket;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    String serverAddress;
    String opponentName;
    ModelConnect4 model;
    ControllerConnect4 connect;
    BufferedReader in;
    Connect4 connect1;

    public String getClientName() {
        return opponentName;
    }

    public Socket connectToServer(String serverAddress, int portNumber, String playerName) {
        try {
            this.serverAddress = serverAddress; // Initialize server address
            socket = new Socket(serverAddress, portNumber);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Attempting to connect to server at " + serverAddress + " on port " + portNumber);

            // Send player's name to the server
            sendName(playerName);

            // Start a thread to receive messages from the server
            Thread receiveThread = new Thread(this::receiveMessages);
            receiveThread.start();
            System.out.println("Connected to server at " + serverAddress + " on port " + portNumber);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to connect to the server at " + serverAddress + " on port " + portNumber);

        }
        return socket;
    }

    private void receiveMessages() {
        try {
            in = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                // Receive messages from the server and handle them accordingly
                String message = in.readLine();
                // Handle received message
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void handleMessage(String message) {
        if (message.startsWith("[Game state]:")) {
            // Parse the game state from the message and update the client UI accordingly
            String gameStateString = message.substring("[Game state]:".length()).trim();
            int[][] gameState = parsingStateOfGame(gameStateString);
            connect1.updateBoard(gameState);
        } else if (message.startsWith("[Player turn]:")) {
            // Update the client UI to indicate whose turn it is
            String playerTurn = message.substring("[Player turn]:".length()).trim();
            // Example: connect4.updatePlayerTurn(playerTurn);
        } else if (message.startsWith("[Name]:")) {
            // Parse the player name from the message
            String playerName = message.substring("[Name]:".length()).trim();
            // Example: connect4.displayPlayerName(playerName);
        } else {
            // Handle other types of messages or display them as chat messages on the client UI
            // Example: connect4.displayChatMessage(message);
        }
    }

    private void sendName(String playerName) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("[Player name]: " + playerName);
            out.flush(); // Make sure to flush the output stream to ensure data is sent immediately
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMove(int column) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("[Player move]: " + column); // Send the move to the server
            out.flush(); // Flush the output stream
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 // This method is for receiving data from the server , updating view from the server 
 	//using methods like receiveGameState(), receiveWinner(), and receiveTurn(). Updates the view based on the data that we have
 	private void gameLoop() {
 		if(receiveTurn()) {
 			connect1.updatePlayerLabels();
 			model.alternatePlayers();
 		} else {
 			receiveGameState();
 			connect.updateView();
 		}
 	
 	}
 	
 	private boolean receiveTurn() {
		try {
			String turn = in.readLine();
		//	boolean clientTurn = turn.equals("Player 1 turn");
			if(turn.equals("Player 1 turn")) {
				connect1.updatePlayerLabels();
			}
		}catch (IOException e) {
	        e.printStackTrace();
	    }
		return true;
	}
 	
 	private void receiveGameState() {
		try {
			String gameState = in.readLine();
			connect1.updateBoard(parsingStateOfGame(gameState));
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	
 	private int[][] parsingStateOfGame(String gameState){
		int[][] gameBoard =new int[6][7];
		
		String[] rows = gameState.split(";");
		for(int row =0; row<rows.length; row++) {
			String[] cols = rows[row].split(",");
			for(int col=0; col<cols.length; col++) {
				gameBoard[row][col]= Integer.parseInt(cols[col]);
				
				
			}
		}
		return gameBoard;
	}
	
    // Implement other methods like gameLoop, receiveGameState, receiveTurn, etc.

    private void disconnect() {
        try {
            // Close the input stream
            if (inputStream != null) {
                inputStream.close();
            }
            // Close the output stream
            if (outputStream != null) {
                outputStream.close();
            }
            // Close the socket
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
