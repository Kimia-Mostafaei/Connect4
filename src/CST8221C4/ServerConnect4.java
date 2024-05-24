package CST8221C4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class ServerConnect4 {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String opponentName;
    private ModelConnect4 model; // Instance of the Model class
    private List<String> chatHistory;

    public ServerConnect4() {
        this.chatHistory = new ArrayList<>();
        this.model = new ModelConnect4(); // Initialize Model object during server creation
    }

    public void startServer(int portNumber) throws IOException {
        if (portNumber < 0 || portNumber > 65535) {
            throw new IllegalArgumentException("Invalid port number");
        }
        serverSocket = new ServerSocket(portNumber);
        System.out.println("Server started on port: " + portNumber);
    }

    public void waitForClientConnection() throws IOException {
        clientSocket = serverSocket.accept();
        System.out.println("Client connected!");
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
        opponentName = receiveString();
        sendName(getUsername()); // Replace getUsername() with your method to get server name
    }

    public void gameLoop() throws IOException {
        while (true) {
            int move = receiveMove();
            // Use Model class for game logic
            if (!model.dropPiece(move)) {
                // Inform client the move is invalid (column full)
                sendInvalidMove();
                continue; // Skip to next turn
            }

            if (model.checkWinner()) {
                sendWinner(model.getCurrentPlayer());
                break;
            }

            sendGameState();
           // sendTurn(model.alternatePlayers()); // Send opponent's turn
        }
    }

    private void sendInvalidMove() throws IOException {
        // Implement logic to send a message indicating invalid move
        outputStream.writeUTF("Invalid move! Column is full.");
        outputStream.flush();
    }

    private int receiveMove() throws IOException {
        return inputStream.readInt();
    }

    private void sendMove(int column) throws IOException {
        outputStream.writeInt(column);
        outputStream.flush();
    }

    private void sendGameState() throws IOException {
        int[][] gameState = model.getGameBoard(); // Convert board to String
       // outputStream.write(gameState);
        outputStream.flush();
    }

    private void sendWinner(int winner) throws IOException {
        outputStream.writeInt(winner);
        outputStream.flush();
    }

    private void sendTurn(Object object) throws IOException {
        outputStream.writeBoolean((boolean) object);
        outputStream.flush();
    }

    private String receiveString() throws IOException {
        return inputStream.readUTF();
    }

    private void sendName(String name) throws IOException {
        outputStream.writeUTF(name);
        outputStream.flush();
    }

    public String getUsername() {
        // Replace this with your logic to get server name
        return "Server";
    }

    public void sendChatMessage(String message) throws IOException {
        outputStream.writeUTF(message);
        outputStream.flush();
        chatHistory.add(message); // Add message to chat history
    }

    public void closeConnection() throws IOException {
        if (clientSocket != null) {
            clientSocket.close();
        }
        if (serverSocket != null) {
            serverSocket.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }
}