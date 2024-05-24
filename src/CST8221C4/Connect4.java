package CST8221C4;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class represents the View of the Connect 4 GUI. This class implements the look of the game, by adding the necessary components,
 * labels, layout managers, menu items etc. This class also includes the implementation of the timer, and splash screen. This class also
 * contains action listeners which are used to handle interactions with menu items and dropping token.
 *
 */

public class Connect4 {
	private ControllerConnect4 controller;
	private ModelConnect4 model;
	private JFrame frame;
	private JButton[][] buttons;
	private int player1=0;
	private int player2=0;


	/*Start of main function*/
	public static void main(String[] args) {
		Connect4 connect4 = new Connect4();
		connect4.controller = new ControllerConnect4(connect4);
		connect4.WaitScreen();

		connect4.createAndShowGUI();
	/*	ServerConnect4 server = new ServerConnect4(); - to delte only delete the /*
	    try {
	        server.startServer(5003); // Start the server on port 5002
	        System.out.println("Server started. Waiting for clients...");

	        // Connect the client
	        ClientConnect4 client = new ClientConnect4();
	        client.connectToServer("localhost", 5003, "PlayerName"); // Connect to the server running on localhost:5002
	        System.out.println("Connected to server at localhost on port 5002");

	        // Implement further game logic and interaction here
	        // For example: starting the game loop, handling user input, etc.
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	//	 ServerConnect4 server = new ServerConnect4();
	/*	    try {
		        server.startServer(5000); // Start the server on port 5000
		        System.out.println("Server started. Waiting for clients...");

		        // Connect the client
		        ClientConnect4 client = new ClientConnect4();
		        client.connectToServer("localhost", 5000, "player name"); // Connect to the server running on localhost:5000
		        System.out.println("Connected to server.");

		        // Implement further game logic and interaction here
		        // For example: starting the game loop, handling user input, etc.
		    } catch (IOException e) {
		        e.printStackTrace();
		    } */
	/*	 ServerConnect4 server = new ServerConnect4();      // will come back
	        try {
	            server.startServer(5000); // Start the server on port 5000
	            System.out.println("Server started. Waiting for clients...");

	            // Connect the client
	            ClientConnect4 client = new ClientConnect4();
	            client.connectToServer("localhost", 5000); // Connect to the server running on localhost:5000
	            System.out.println("Connected to server.");

	            // Implement further game logic and interaction here
	            // For example: starting the game loop, handling user input, etc.
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    */

	}

	/**
	 * Default constructor which initializes the buttons of the game board.
	 */
	public Connect4() {
		setButtons(new JButton[6][7]); 
	}

	/**
	 * Setter for the model
	 * @param m, model
	 */
	public void setModel(ModelConnect4 m) {
		model = m;
	}

	/**
	 * Getter for the model
	 * @return the buttons
	 */
	public JButton[][] getButtons() {
		return buttons;
	}

	/**
	 * Setter for the model
	 * @param buttons, sets buttons
	 */
	public void setButtons(JButton[][] buttons) {
		this.buttons = buttons;
	}

	/**
	 * This method implements the Splash Screen which will be shown before the game is executed. 
	 */
	public void WaitScreen() {
	    JWindow window = new JWindow();
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    Icon i = new ImageIcon(this.getClass().getResource("/image/please-wait.gif"));
	    JLabel label = new JLabel(i);
	    window.getContentPane().add(label);
	    window.pack();
	    window.setLocationRelativeTo(null); // Center the window on the screen
	    window.setVisible(true);

	    try {
	        Thread.sleep(3000);
	    } catch(InterruptedException e) {
	        e.printStackTrace();
	    }

	    window.setVisible(false);
	    window.dispose();
	}


	/**
	 * Displays that a winner has been identified after system observes a winning condition, and displays who won in a pop up window.
	 * @param player, represents the player 
	 */
	public void displayWinner(int player) {
		if (model.checkWinner()) {
			String message = (player == 1) ? "Player 1 wins!" : "Player 2 wins!";
			JOptionPane.showMessageDialog(frame, message, "Winner", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Displays that a draw state has been identified by the system, and displays a draw condition message in a pop up window.
	 */
	public void displayDraw() {
		if (model.checkDraw()) {
			String message = "Draw!";
			JOptionPane.showMessageDialog(frame, message, "Draw", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Displays instructions on how to win and play in a message pop up window.
	 * @param message, takes message defined in Model as input
	 */
	public void displayInstructions(String message) {
		JOptionPane.showMessageDialog(frame, message, "Instructions", JOptionPane.INFORMATION_MESSAGE);

	}

	private JLabel player1Label2 = new JLabel("0"); 
	private JLabel player2Label2 = new JLabel("0");

	private JLabel leftLabel = new JLabel("Player 1 Turn");
	private boolean p1=true;

	/**
	 * This method will reset the labels and move point score when user interacts with reset menu item.
	 */
	public void resetLabels() {
		player1=0;
		player2=0;
        player1Label2.setText("0");
        player2Label2.setText("0");
        leftLabel.setText("Player 1 Turn");
        p1 = true; 
    }
	/**
	 * This method is responsible for updating the player labels, depending on whose turn it is.
	 */
	public void updatePlayerLabels() // boolean p1 added parameter
	{
		if (p1) {
			leftLabel.setText("Player 2 Turn");
		}else {
			leftLabel.setText("Player 1 Turn");
		}
		p1=!p1;
		
	}    

	/**
	 * This method contains all GUI implementations on the left panel (West side of frame).
	 * This method also includes the implementation of the timer.
	 * @param frame, regards to the JFrame
	 */
	private void addLeftPanel(JFrame frame) {

		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBackground(new Color(255, 199, 250));
		leftPanel.setPreferredSize(new Dimension(300, 300));


		leftLabel.setFont(new Font("Aptos (Body)", Font.BOLD, 25));
		leftPanel.add(leftLabel, BorderLayout.NORTH);
		leftLabel.setHorizontalAlignment(JLabel.CENTER);

		JPanel playersPanel = new JPanel(new GridLayout(2, 1));
		playersPanel.setBackground(new Color(216, 138, 232));

		JLabel player1Label = new JLabel("Player 1 ");

		JLabel player2Label = new JLabel("Player 2");
		

		player1Label.setFont(new Font("Aptos (Body)", Font.ROMAN_BASELINE, 25));
		player2Label.setFont(new Font("Aptos (Body)", Font.ROMAN_BASELINE, 25));

		/* Adding icon images */
		try {
			ImageIcon player1Icon = new ImageIcon(ImageIO.read(new File("player1.png")));
			player1Label.setIcon(player1Icon);
			ImageIcon opponentIcon = new ImageIcon(ImageIO.read(new File("player2.png")));
			player2Label.setIcon(opponentIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}

		playersPanel.add(player1Label);
		playersPanel.add(player1Label2);

		playersPanel.add(player2Label);
		playersPanel.add(player2Label2);

		leftPanel.add(playersPanel, BorderLayout.CENTER);

		JTextField timer = new JTextField();
		timer.setPreferredSize(new Dimension(220, 120));
		timer.setFont(new Font("Times New Roman", Font.BOLD, 50));
		timer.setBackground(new Color(255, 192, 203));

		Timer gameTimer = new Timer(1000, new ActionListener() {
			int seconds = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				seconds++;
				int min = seconds / 60;
				int remainingTime = seconds % 60;
				timer.setText(String.format("%02d:%02d", min, remainingTime));
			}
		});
		gameTimer.start();

		JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		timerPanel.setBackground(new Color(216, 138, 232));
		timerPanel.add(timer, BorderLayout.CENTER);
		timer.setHorizontalAlignment(JTextField.CENTER);
		leftPanel.add(timerPanel, BorderLayout.SOUTH);

		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
	}


	/**
	 * This method is used to handle the user interaction with the button. Calls the validPlay method from the Controller class to check if play respects the game logic.
	 * If so, it increments the player moves.
	 * @param column, represents column on game board
	 */
	private void handleButtonClick(int column) {
		if ( controller.validatePlay(column)){
			int player=model.getCurrentPlayer();
			if (player==2) {
				player1++;
				player1Label2.setText(player1+"");
			}else if(player==1)  {
				player2++;
				player2Label2.setText(player2+"");

			}    
			
		}
		
	}

	/**
	 * This method contains all GUI implementations on the center panel ( center side of frame).
	 * This method includes implementation of the board, and implements an action listener to handle user interactions with the buttons.
	 * @param frame, regards to the JFrame
	 */
	private void addCenterPanel(JFrame frame) {
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel gameGrid = new JPanel(new GridLayout(6, 7));
		setButtons(new JButton[6][7]);

		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				getButtons()[row][col] = new JButton();
				getButtons()[row][col].setPreferredSize(new Dimension(80, 80)); 
				getButtons()[row][col].setUI(new ButtonComponent());
				int column = col;
				getButtons()[row][col].addActionListener(e -> handleButtonClick(column));
				gameGrid.add(getButtons()[row][col]);
			}
		}

		JLabel titleLabel = new JLabel("Connect 4");
		titleLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 75));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setVerticalAlignment(JLabel.CENTER);
		titleLabel.setBackground(Color.black);

		centerPanel.add(gameGrid, BorderLayout.CENTER);
		centerPanel.add(titleLabel, BorderLayout.NORTH);
		centerPanel.setPreferredSize(new Dimension(240, 280));

		frame.getContentPane().add(centerPanel);
	}

	/**
	 * Updates the board with the tokens from the players.
	 * @param gameBoard, represents the 2D array used for board
	 */
	public void updateBoard(int[][] gameBoard) {
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				int player = gameBoard[row][col];
				if (player != 0) {
					buttons[row][col].setBackground(PlayerTokenColor(player));
					buttons[row][col].setIcon(createTokenIcon(player));
				} else {
					buttons[row][col].setBackground(Color.WHITE);
					buttons[row][col].setIcon(null);
				}
			}
		}
	}

	/**
	 * Creates the graphics and sets the size for the tokens. 
	 * @param player, represents user.
	 * @return returns icon of tokens
	 */
	private Icon createTokenIcon(int player) {
		int diameter = 50; 
		BufferedImage image;
		image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics_2d = image.createGraphics();
		graphics_2d.setColor(PlayerTokenColor(player));
		graphics_2d.fillOval(0, 0, diameter, diameter);
		graphics_2d.dispose();
		return new ImageIcon(image);
	}

	/**
	 * This method implements the token colors for each player, setting player 1 with pink tokens , and player 2 with black tokens.
	 * @param currentPlayer, represents the current player
	 * @return returns the color of tokens for players
	 */
	private Color PlayerTokenColor(int currentPlayer) {
		if (currentPlayer ==1) {
			return Color.PINK;
		} else {
			return Color.BLACK;
		}
	}

	/**
	 * This method contains all GUI implementations on the menu bar (north side of frame).
	 * This method includes implementation of menu items and contains action listener for each menu item, and calls the handleMenuEvents in the controller class
	 * to handle the events. 
	 * @param frame, regards to the JFrame
	 */
	private void addMenuBar(JFrame frame) {
		// Menu bar implementation remains unchanged
		JMenuBar jb = new JMenuBar();

		JMenu file = new JMenu("File");
		file.setFont(new Font("Times New Roman", Font.BOLD, 15));
		JMenu game = new JMenu("Game");
		game.setFont(new Font("Times New Roman", Font.BOLD, 15));
		JMenu language = new JMenu("Language");
		language.setFont(new Font("Times New Roman", Font.BOLD, 15));
		JMenu network = new JMenu("Network");
		network.setFont(new Font("Times New Roman", Font.BOLD, 15));
		JMenu help = new JMenu("Help");
		help.setFont(new Font("Times New Roman", Font.BOLD, 15));

		JMenuItem load = new JMenuItem("Reset");
		load.addActionListener(e -> controller.handleMenuEvents("Reset"));
		file.add(load);

		JMenuItem pause = new JMenuItem("Pause");
		pause.addActionListener(e -> controller.handleMenuEvents("Pause"));
		game.add(pause);

		JMenuItem persian = new JMenuItem("Persian");
		persian.addActionListener(e -> controller.handleMenuEvents("Persian"));
		language.add(persian);
		
		JMenuItem english = new JMenuItem("English");
		english.addActionListener(e -> controller.handleMenuEvents("English"));
		language.add(english);

		JMenuItem singlePlayer = new JMenuItem("Single Player"); // may need to take out
		
		JMenuItem twoPlayer = new JMenuItem("Two Player"); // take out
		
		JMenuItem host = new JMenuItem("Host");
		host.addActionListener(e -> controller.handleMenuEvents("Host"));
		JMenuItem connect = new JMenuItem("Connect");
		connect.addActionListener(e -> controller.handleMenuEvents("Connect"));
		JMenuItem disconnect = new JMenuItem("Disconnect");
		disconnect.addActionListener(e -> controller.handleMenuEvents("Disconnect"));
		
		network.add(host);
		network.add(connect);
		network.add(disconnect);
		network.add(singlePlayer); // take out 
		network.add(twoPlayer); // take out

		JMenuItem instructions = new JMenuItem("Instruction");
		instructions.addActionListener(e-> controller.handleMenuEvents("Instructions"));
		help.add(instructions);


		jb.add(file);
		jb.add(game);
		jb.add(language);
		jb.add(network);
		jb.add(help);

		frame.setJMenuBar(jb);
	}

	/**
	 * This method contains all GUI implementations on the right panel (east side of frame).
	 * This method includes implementation of the chat area. 
	 * @param frame, regards to the JFrame
	 */
	private void addRightPanel(JFrame frame) {
		// Right panel implementation remains unchanged
		JPanel rightPanel = new JPanel(new BorderLayout());

		JTextArea area = new JTextArea();
		area.setFont(new Font("Arial", Font.BOLD, 20));
		area.setLineWrap(true); // Enable line wrapping
	    area.setWrapStyleWord(true); // Wrap at word boundaries
		area.setEditable(false);

		JScrollPane scroll = new JScrollPane(area);
		scroll.setPreferredSize(new Dimension(300, 550));

		
		JTextField textField = new JTextField(); 
		textField.setPreferredSize(new Dimension(220, 90));
		textField.setBackground(new Color(255, 192, 203));
	//	textField.setLineWrap(true); // Enable line wrapping
	 //       textField.setWrapStyleWord(true); // Wrap at word boundaries

		JButton button = new JButton("Send");
		button.setPreferredSize(new Dimension(80, 30));
		button.setBackground(new Color(198, 219, 255));

		 button.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String message = textField.getText().trim();
	                if (!message.isEmpty()) {
	                    area.append("Player 1: " + message + "\n"); // should alternate between players
	            		area.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 15));

	                    textField.setText(""); // Clear the text field after sending the message
	                }
	               
	            }
	        });
		JPanel chatArea = new JPanel(new BorderLayout());
		chatArea.add(scroll, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(textField, BorderLayout.CENTER);
		buttonPanel.add(button, BorderLayout.EAST);

		chatArea.add(buttonPanel, BorderLayout.SOUTH);
		rightPanel.add(chatArea, BorderLayout.EAST);

		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
	}




	/**
	 * Sets the layout and dimension of the game, and adds the panels in the frame.
	 */
	public void createAndShowGUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().setBackground(new Color(198, 200, 255));

		addLeftPanel(frame);
		addCenterPanel(frame);
		addMenuBar(frame);
		addRightPanel(frame);

		frame.pack();
		frame.setSize(new Dimension(1350, 700));
		frame.setResizable(false);
		frame.setVisible(true);
	}
}


class ButtonComponent extends javax.swing.plaf.basic.BasicButtonUI {
	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics2D graphics_2d = (Graphics2D) g.create();
		graphics_2d.setColor(Color.WHITE);
		graphics_2d.fill(new Ellipse2D.Double(5, 5, c.getWidth() - 10, c.getHeight() - 10));
		graphics_2d.dispose();
		super.paint(g, c);
	}
}