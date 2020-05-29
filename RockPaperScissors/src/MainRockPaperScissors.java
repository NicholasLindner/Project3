import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainRockPaperScissors {
	
	/*
	 * This is the main method of this program.
	 * It starts the programs execution. 
	 * It also gives you the option to choose between 
	 * single player and two player mode.
	 */
	public static void main(String[] args) {
		int mode = chooseGameMode("                     CHOOSE GAME MODE");
		if(mode == -1) {
			return;
		}
		RPSGame game = new RPSGame(mode == 0);
		new GuiFrame(game);

	}
	
	/*
	 * This method sets up a new JPanel which has 
	 * a single player option and a two player
	 * option. It return the option that the user chose.
	 */
	public static int chooseGameMode(String title){
		String[] options = {"SINGLE PLAYER MODE", "TWO PLAYER MODE"};
		JPanel panel = new JPanel();
		return JOptionPane.showOptionDialog(null, panel, title, JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options , options[0]);
		
			
	}

}
