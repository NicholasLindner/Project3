import java.util.Arrays;
import java.util.Collections;

public class RPSGame {

	//INSTANCE VARIABLES
	private boolean isSinglePlayer;
	private String p1Choice = "";
	private String p2Choice = "";
	private int p1Score = 0;
	private int p2Score = 0;
	private Integer[] leaderboardArr = {0, 0, 0, 0, 0};
	private Turn curTurn = Turn.Player1;
	
	//GETTERS
	public int getP1Score() {return p1Score;}
	public int getP2Score() {return p2Score;}
	public String getP1Choice() {return p1Choice;}
	public String getP2Choice() {return p2Choice;}
	public Integer[] getLeaderboard() {return leaderboardArr;}
	public Turn getCurrentTurn() {return curTurn;}
	public boolean getIfSinglePlayer() {return isSinglePlayer;}


	//CONSTRUCTOR
	public RPSGame(boolean onePlayerMode){
		
		isSinglePlayer = onePlayerMode;
		
	}

	public void setChoice(String choice) {
		
		if(curTurn == Turn.Player1) {
			p1Choice = choice;
			curTurn = Turn.Player2;
			if(getIfSinglePlayer() == true) {
				setChoice(computersChoice());
			}
		}
		else {
			p2Choice = choice;
			scoreCounter();
		}
		
	}
	
	/* These are constants. They represent player1,
	 * player2, and nobody.
	 */
	enum Turn{
		Player1, 
		Player2,
		None
	}
	
	/*
	 * This method randomly generates a number between 0
	 * and 2 (inclusive) and depending on the number which
	 * was generated, it either returns "Rock", "Paper"
	 * or "Scissors". This simulates the computer choosing 
	 * either rock, paper, or scissors.
	 */
	private String computersChoice() {
		
		int choice = (int)(Math.random() * 3);

		switch(choice) {
		
			case 0: return "Rock";
			case 1: return "Paper";
			case 2: return "Scissors";
			default: return "";
		}
		
	}
	
	/*
	 * This method sets up and begins a new
	 * round.
	 */
	public void newRound() {
		
		curTurn = Turn.Player1;
		if(getIfSinglePlayer() && theWinner() == Turn.Player2){
			p1Score = 0;
			p2Score = 0;
		}
		p1Choice = "";
		p2Choice = "";
		
		
	}
	
	/*
	 * This method returns who won depending on the
	 * choices that were chosen.
	 */
	public Turn theWinner() {
		
		if(p1Choice.equals("Rock") && p2Choice.equals("Scissors")) {
			return Turn.Player1;
		}
		else if(p1Choice.equals("Paper") && p2Choice.equals("Rock")) {
			return Turn.Player1;
		}
		else if(p1Choice.equals("Scissors") && p2Choice.equals("Paper")) {
			return Turn.Player1;
		}
		else if(p1Choice.equals("Rock") && p2Choice.equals("Rock")) {
			return Turn.None;
		}
		else if(p1Choice.equals("Paper") && p2Choice.equals("Paper")) {
			return Turn.None;
		}
		else if(p1Choice.equals("Scissors") && p2Choice.equals("Scissors")) {
			return Turn.None;
		}
		return Turn.Player2;
	
	}
	
	/*
	 * This method adds one to the score counter
	 * of either player1 or player2 depending
	 * on who won.
	 */
	public void scoreCounter() {
		
		Turn winner = theWinner();
		
		if(winner == Turn.Player1) {
			p1Score++;
		}
		else if (winner == Turn.Player2) {
			p2Score++;
		}
	}
	
	/*
	 * This method updates the leaderboard so that the 
	 * leaderboard has the correct information in the correct 
	 * locations.
	 */
	public void updateLeaderboard() {
		int newScore = p1Score;
		if (!isSinglePlayer && p2Score > newScore)
			newScore = p2Score;
		
		if (newScore >= leaderboardArr[leaderboardArr.length - 1]) {
			leaderboardArr[leaderboardArr.length - 1] = newScore;
			Arrays.sort(leaderboardArr, Collections.reverseOrder());
		}
	}
	
}
