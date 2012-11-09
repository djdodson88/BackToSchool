import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class FinalBattle extends JPanel {	
	// global variables
	JButton button1;
	boolean attackPressed;
	ImageIcon background;

	Graphics graphics;

	// Student variables
	ImageIcon student;
	int studentX;
	int studentY;
	int playerHealth;
	JLabel playerHealthLabel;
	JLabel creativityLabel;
	JLabel quantReasoningLabel;
	JLabel scientRigorLabel;
	Timer timer;
	Player player;

	// Attack Menu variables
	JLabel defaultAttackLabel;
	JLabel specializedAttackLabel;

	// Boss variables
	ImageIcon humBoss;
	ImageIcon sciBoss;
	ImageIcon mathBoss;
	ImageIcon attack;
	int attackX;
	int attackY;
	int humBossX;
	int humBossY;
	int sciBossX;
	int sciBossY;
	int mathBossX;
	int mathBossY;
	int xSpeed;
	int humBossHealth;
	int sciBossHealth;
	int mathBossHealth;
	String bossSubject;
	Timer bossTimer;
	JLabel humHealthLabel;
	JLabel mathHealthLabel;
	JLabel sciHealthLabel;
	JLabel humBossType;
	JLabel humBossName;
	JLabel mathBossType;
	JLabel mathBossName;
	JLabel sciBossType;
	JLabel sciBossName;
	boolean anyBossTurn;
	boolean humBossTurn;
	boolean sciBossTurn;
	boolean mathBossTurn;


	public FinalBattle(Player player)
	{
		this.player = player;
	}
	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Back To School: Battle Mode");
		FinalBattle fBattle= new FinalBattle(new Player());
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(battle);
		frame.setContentPane(fBattle);
		frame.pack();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FinalBattle(new Player()); // Let the constructor do the job
			}
		});
		frame.setVisible(true);
	}


}
