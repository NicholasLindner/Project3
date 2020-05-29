import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GuiFrame extends JFrame {
	
	//INSTANCE VARIABLES
	private static final long serialVersionUID = 7462992397950068538L;
	private JButton rock;
	private JButton paper;
	private JButton scissors;
	private JButton nextRound;
	private JButton quit;
	private JButton leaderboard;
	private RPSGame game;
	private JLabel p1ScoreLabel;
	private JLabel p2ScoreLabel;
	private JLabel p1Score;
	private JLabel p2Score;
	private JLabel turn;
	private JLabel p1ChoiceLabel;
	private JLabel p2ChoiceLabel;
	private JLabel p1Choice;
	private JLabel p2Choice;
	private JLabel roundResult;
	private JLabel scoreResettingText;
	
	
	 /*
	 * CONSTRUCTOR: This constructor initializes the game
	 * instance variable and sets up the entire GUI frame
	 * using absolute positioning to put everything in the
	 * correct place.
	 */
	public GuiFrame(RPSGame game) {
	
		this.game = game;
		
		setLayout(null);
		
		turn = new JLabel(game.getIfSinglePlayer() ? "Player's Turn " : "Player 1's Turn ");
		turn.setBounds(12, 1, 200, 50);
		add(turn);
		
		rock = new JButton(loadImage("Images/rock.png"));
		rock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onRockClicked();
			}
		});
		rock.setBounds(12,40, 110, 110);
		add(rock);
		
		paper = new JButton(loadImage("Images/paper.png"));
		paper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onPaperClicked();
			}
		});
		paper.setBounds(132, 40, 110, 110);
		add(paper);

		
		scissors = new JButton(loadImage("Images/scissors.png"));
		scissors.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onScissorsClicked();
			}
		});
		scissors.setBounds(252, 40, 110, 110);
		add(scissors);
		
		
		nextRound = new JButton("Next Round");
		nextRound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onNextClicked();
			}
		});

		nextRound.setBounds(252, 167, 100, 40);
		add(nextRound);
		nextRound.setEnabled(false);
		
		roundResult = new JLabel ("", SwingConstants.CENTER);
		roundResult.setFont(new Font("Arial", Font.BOLD, 20));
		roundResult.setForeground(Color.BLUE);
		roundResult.setBounds(0, 230, 380, 50);
		add(roundResult);
		
		scoreResettingText = new JLabel ("The computer won so your win streak is resetting.", SwingConstants.CENTER);
		scoreResettingText.setFont(new Font("Arial", Font.PLAIN, 14));
		scoreResettingText.setForeground(Color.RED);
		scoreResettingText.setBounds(0, 260, 380, 50);
		scoreResettingText.setVisible(false);
		add(scoreResettingText);
		
		p1ChoiceLabel = new JLabel (game.getIfSinglePlayer() ? "Player's Choice: " : "Player 1's Choice: ");
		p1ChoiceLabel.setBounds(12, 140, 120, 60);
		add(p1ChoiceLabel);
		
		p1Choice = new JLabel ("");
		p1Choice.setBounds(142, 140, 120, 60);
		add(p1Choice);
		
		
		p2ChoiceLabel = new JLabel (game.getIfSinglePlayer() ? "Computer's Choice: " : "Player 2's Choice: ");
		p2ChoiceLabel.setBounds(12, 180, 120, 60);
		add(p2ChoiceLabel);
	
		p2Choice = new JLabel ("");
		p2Choice.setBounds(142, 180, 120, 60);
		add(p2Choice);
		
		
		
		p1ScoreLabel = new JLabel(game.getIfSinglePlayer() ? "Player's Score: " : "Player 1's Score: ");
		p1ScoreLabel.setBounds(12, 340, 100, 20);
		add(p1ScoreLabel);
		
		p1Score = new JLabel("0");
		p1Score.setBounds(142, 340, 100, 20);
		add(p1Score);
		
		p2ScoreLabel = new JLabel(game.getIfSinglePlayer() ? "Computer's Score: " : "Player 2's Score: ");
		p2ScoreLabel.setBounds(12, 380, 140, 20);
		add(p2ScoreLabel);
		
		p2Score = new JLabel("0");
		p2Score.setBounds(142, 380, 100, 20);
		add(p2Score);
		
		
		leaderboard = new JButton("Leader Board");
		leaderboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onLeaderboardClicked();
			}
		});
		
		leaderboard.setBounds(12, 450, 150, 50);
		leaderboard.setEnabled(game.getIfSinglePlayer());
		add(leaderboard);
		
		quit = new JButton("Exit");
		quit.setForeground(Color.RED);
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onQuitClicked();
			}
		});

		quit.setBounds(252, 450, 100, 50);
		add(quit);
		
		
		setResizable(false);
		setSize(380, 580);
		setVisible(true);
		setTitle("Rock Paper Scissors!");
		
	}
	
	/*
	 * This method loads a given image into the game
	 * and scales it.
	 */
	private ImageIcon loadImage(String imagePath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	
		return new ImageIcon(scaled);
	}

	/*
	 * This method sets the choice to rock and 
	 * updates the game.
	 */
	private void onRockClicked() {
		
		game.setChoice("Rock");
		update();
		
	}
	
	/*
	 * This method sets the choice to paper and 
	 * updates the game.
	 */
	private void onPaperClicked() {
			
		game.setChoice("Paper");
		update();

	}
	
	/*
	 * This method sets the choice to scissors and 
	 * updates the game.
	 */
	private void onScissorsClicked() {
		
		game.setChoice("Scissors");
		update();

	}
	
	/*
	 * This method gets called when the 
	 * user user clicks the next button. It 
	 * starts a new round and updates the game.
	 */
	private void onNextClicked() {
		
		game.newRound();
		update();
		
	}
	
	/*
	 * This method gets called when the 
	 * user clicks the leaderboard button and
	 * opens up the leaderboard.
	 */
	private void onLeaderboardClicked() {
		
		showLeaderboard();
		
	}
	
	/*
	 * This method gets called when the
	 * user clicks the quit button and closes the program.
	 */
	private void onQuitClicked() {
		
		System.exit(0);
		
	}
	
	/*
	 * This method updates the game. It does this by checking if the round is 
	 * over, checking who's turn it is depending on if the game is in single player mode
	 * or 2 player mode, and displaying the necessary information to the player such as the choices of 
	 * each player, the scores, and once the winner of the round is known, it displays who won.
	 * It also displays the score-resetting-text depending on if both the game is in 
	 * single player mode and player 2 won. Finally it updates the leaderboard.
	 */
	private void update() {
		
		boolean isRoundOver = !game.getP2Choice().isEmpty();
		
		if(game.getCurrentTurn() == RPSGame.Turn.Player1) {
			turn.setText(game.getIfSinglePlayer() ? "Player's Turn" : "Player 1's Turn");
		}
		else {
			turn.setText(game.getIfSinglePlayer() ? "Computer's Turn" : "Player 2's Turn");
		}
		
		p1ChoiceLabel.setText(game.getIfSinglePlayer() ? "Player's Choice: " : "Player 1's Choice: ");
		p2ChoiceLabel.setText(game.getIfSinglePlayer() ? "Computer's Choice: " : "Player 2's Choice: ");
		
		if(!game.getIfSinglePlayer() && !isRoundOver && game.getCurrentTurn() == RPSGame.Turn.Player2) {
			p1Choice.setText("HIDDEN");
		}
		else {
			p1Choice.setText(game.getP1Choice());

		}
		p2Choice.setText(game.getP2Choice());
		
		
		p1ScoreLabel.setText(game.getIfSinglePlayer() ? "Player's Score: " : "Player 1's Score: ");
		p2ScoreLabel.setText(game.getIfSinglePlayer() ? "Computer's Score: " : "Player 2's Score: ");
		
		p1Score.setText(Integer.toString(game.getP1Score()));
		p2Score.setText(Integer.toString(game.getP2Score()));
		
		nextRound.setEnabled(isRoundOver);
		rock.setEnabled(!isRoundOver);
		paper.setEnabled(!isRoundOver);
		scissors.setEnabled(!isRoundOver);
		
		if(!isRoundOver) {
			roundResult.setText("");
		}
		else if (game.theWinner() == RPSGame.Turn.None) {
			roundResult.setText("IT IS A TIE!");
		}
		else if (game.theWinner() == RPSGame.Turn.Player1) {
			roundResult.setText(game.getIfSinglePlayer() ? "THE PLAYER WINS!" : "PLAYER 1 WINS!");
		}
		else if (game.theWinner() == RPSGame.Turn.Player2) {
			roundResult.setText(game.getIfSinglePlayer() ? "THE COMPUTER WINS!" : "PLAYER 2 WINS!");
		}
		
		if(isRoundOver && game.theWinner() == RPSGame.Turn.Player2 && game.getIfSinglePlayer()) {
			scoreResettingText.setVisible(true);
		}
		else {
			scoreResettingText.setVisible(false);
		}

		if (isRoundOver) {
			boolean computerWon = game.getIfSinglePlayer() && game.theWinner() == RPSGame.Turn.Player2;
			if (computerWon)
				game.updateLeaderboard();
		}
	}
	
	/*
	 * This sets up a panel which displays the
	 * leaderboard information. The leaderboard also 
	 * has an OK button which will allow the user to close the 
	 * leaderboard panel.
	 */
	private void showLeaderboard() {
		
		Integer[] leaderboard = game.getLeaderboard();
		
		String[] options = {"OK"};
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(leaderboard.length, 3));
		
		for(int i = 0; i < leaderboard.length; i++) {
			String score = Integer.toString(leaderboard[i]);
			if (score.equals("0"))
				score = "---";
			panel.add(Box.createHorizontalGlue());
			panel.add(new JLabel(Integer.toString(i + 1) + ".      " + score));
			panel.add(Box.createHorizontalGlue());
		}

		JOptionPane.showOptionDialog(null, panel, "Top 5 Player Scores", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options , options[0]);
		
	}
	
}


