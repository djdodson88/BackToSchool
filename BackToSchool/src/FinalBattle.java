import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	ImageIcon backpack;
	int studentX;
	int studentY;
	int backpackX;
	int backpackY;
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
	JButton optionAButton;
	JButton optionBButton;
	ImageIcon A;
	ImageIcon B;
	boolean optionA;
	boolean optionB;
	boolean specialAttack;
	String bossChosen;

	// Boss variables
	ImageIcon humBoss;
	ImageIcon sciBoss;
	ImageIcon mathBoss;
	ImageIcon attack;
	ImageIcon scribble;
	JButton humBossButton;
	JButton sciBossButton;
	JButton mathBossButton;
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
	Timer bossTimer;
	JLabel bossHealthLabel;
	JLabel bossTypeLabel;
	JLabel bossNameLabel;
	boolean anyBossTurn;
	boolean humBossTurn;
	boolean sciBossTurn;
	boolean mathBossTurn;

	public FinalBattle(Player player)
	{
		this.player = player;
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		this.setBackground(Color.white);// color of background
		background = new ImageIcon("art/battle/battle.jpg");
		playerHealth=100;
		mathBossHealth=100;
		sciBossHealth=100;
		humBossHealth=100;
		optionA=true;
		optionB=false;
		bossChosen="";
		xSpeed=5;// speed for movement

		//adding the attack button
		button1 = new JButton("Attack");
		//button1.addActionListener(new ButtonListener());
		button1.setBounds(400,510,100,30);
		button1.addActionListener(new AttackButtonListener());

		//attacking menu
		defaultAttackLabel = new JLabel("Default Attack");
		defaultAttackLabel.setBounds(450,405,100,30);
		specializedAttackLabel = new JLabel("Special Attack");
		specializedAttackLabel.setBounds(450,455,160,30);
		scribble = new ImageIcon("art/battle/scribble_sprite.png");

		//adding option A button
		optionAButton = new JButton();
		optionAButton.addActionListener(new AButtonListener());
		optionAButton.setIcon(new ImageIcon("art/battle/A_sprite.png"));
		optionAButton.setBounds(388,398,50,40); 
		optionAButton.setBackground(null);
		optionAButton.setOpaque(false);
		optionAButton.setBorder(null);

		// adding option B button
		optionBButton = new JButton();
		optionBButton.addActionListener(new BButtonListener());
		optionBButton.setIcon(new ImageIcon("art/battle/B_sprite.png"));
		optionBButton.setBounds(388,450,50,40);
		optionBButton.setBackground(null);
		optionBButton.setOpaque(false);
		optionBButton.setBorder(null);

		setLayout(null);

		//----------------------Player Variables--------------------------------//
		student = new ImageIcon("art/characters/student_leftside.png"); // loading image
		studentX=600;// x coordinate for student
		studentY=200;// y coordinate for student
		backpackX=600;
		backpackY=200;
		attackPressed=false;

		// initializing variables
		playerHealthLabel = new JLabel(playerHealth+"%");
		creativityLabel = new JLabel("Creativity: "+player.getCreativity());
		quantReasoningLabel = new JLabel("Quantative Reasoning: "+player.getQuantReasoning());
		scientRigorLabel = new JLabel("Scientific Rigor: "+player.getSciRigor());
		backpack = new ImageIcon("art/battle/backpack.png");

		//setting location of statistics
		playerHealthLabel.setBounds(670,340,100,100);
		creativityLabel.setBounds(620,380,150,100);
		quantReasoningLabel.setBounds(620,420,150,100);
		scientRigorLabel.setBounds(620,460,150,100);
		//---------------------End of Player Variables---------------------------//

		//---------------------Bosses Variables------------------------------------//
		humBoss = new ImageIcon("art/battle/Finalhumboss.png");
		humBossButton = new JButton();
		humBossButton.addActionListener(new HumButtonListener());
		humBossButton.setIcon(humBoss);
		humBossButton.setBounds(0,0,140,140); 
		humBossButton.setBackground(null);
		humBossButton.setOpaque(false);
		humBossButton.setBorder(null);

		sciBoss = new ImageIcon("art/battle/Finalscience_boss.png");
		sciBossButton = new JButton();
		sciBossButton.addActionListener(new ScienceButtonListener());
		sciBossButton.setIcon(sciBoss);
		sciBossButton.setBounds(0,110,130,140); 
		sciBossButton.setBackground(null);
		sciBossButton.setOpaque(false);
		sciBossButton.setBorder(null);

		mathBoss = new ImageIcon("art/battle/Finalmath_boss.png");
		mathBossButton = new JButton();
		mathBossButton.addActionListener(new MathButtonListener());
		mathBossButton.setIcon(mathBoss);
		mathBossButton.setBounds(0,250,130,140); 
		mathBossButton.setBackground(null);
		mathBossButton.setOpaque(false);
		mathBossButton.setBorder(null);
		
		bossHealthLabel = new JLabel("Choose");
		bossTypeLabel = new JLabel("to attack");
		bossNameLabel = new JLabel("a boss");
		bossHealthLabel.setBounds(100,340,100,100);
		bossNameLabel.setBounds(100,420,200,30);
		bossTypeLabel.setBounds(100,460,100,30);
		//--------------------End of Bosses Variables

		// adding components to the jpanel
		this.add(bossNameLabel);
		this.add(bossTypeLabel);
		this.add(bossHealthLabel);
		this.add(button1);
		this.add(optionAButton);
		this.add(optionBButton);
		this.add(humBossButton);
		this.add(sciBossButton);
		this.add(mathBossButton);
		this.add(bossHealthLabel);
		this.add(playerHealthLabel);
		this.add(creativityLabel);
		this.add(quantReasoningLabel);
		this.add(scientRigorLabel);
		this.add(specializedAttackLabel);
		this.add(defaultAttackLabel);

		setVisible(true);
	}

	// paint the images and graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics=g;
		background.paintIcon(this,g,0,0);
		student.paintIcon(this, g, studentX, studentY);
		
		if(attackPressed)
			backpack.paintIcon(this, g, backpackX,backpackY);

		if(optionA)
			scribble.paintIcon(this, g, 395, 400);
		else if(optionB)
			scribble.paintIcon(this,g,395,452);
	}
	
	public void update() {
		movePlayer();
	}
	
	public void movePlayer(){
		if(bossChosen.equals("Science"))
			backpackY -= 0.5;
		else if(bossChosen.equals("Math"))
			backpackY += 1;
		else if(bossChosen.equals("Humanities"))
			backpackY -= 2;
		
		backpackX -= xSpeed;
		

		//if the student reaches to the origin, make him stop
		if(backpackX < 190)
		{
			if(specialAttack)
			{
				if(bossChosen.equals("Science"))
					sciBossHealth-=player.getCreativity()*10;
				else if(bossChosen.equals("Math"))
					mathBossHealth-=player.getQuantReasoning()*10;
				else if(bossChosen.equals("Humanities"))
					humBossHealth-=player.getSciRigor()*10;
			}
			else
			{
				if(bossChosen.equals("Science"))
					sciBossHealth-=5;
				else if(bossChosen.equals("Math"))
					mathBossHealth-=5;
				else if(bossChosen.equals("Humanities"))
					humBossHealth-=5;
			}

		
			if(bossChosen.equals("Science"))
				bossHealthLabel.setText(sciBossHealth+"%");// inflict damage on boss's health
			else if(bossChosen.equals("Math"))
				bossHealthLabel.setText(mathBossHealth+"%");// inflict damage on boss's health
			else if(bossChosen.equals("Humanities"))
				bossHealthLabel.setText(humBossHealth+"%");// inflict damage on boss's health
			
			//repaint();
			timer.stop();
			backpackX=600;
			backpackY=200;
			xSpeed=5;
			attackPressed=false;
			anyBossTurn=true;
//			ActionListener updateTask = new ActionListener() {
//
//				public void actionPerformed(ActionEvent evt) {
//					update();   // update the (x, y) position
//					repaint();  // Refresh the JFrame, callback paintComponent()
//				}
//			};
//			bossTimer = new Timer(5, updateTask);
//			bossTimer.start();
			attackX=60;
			attackY=240;
		}
	}

	// action listener for the Humanities Boss Button
	private class HumButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed){
				humBossButton.setBounds(100,0,140,140); 
				sciBossButton.setBounds(0,110,130,140); 
				mathBossButton.setBounds(0,250,130,140);  
				bossChosen="Humanities";
				bossHealthLabel.setText(humBossHealth+"%");
				bossTypeLabel.setText("Type: Humanities");
				bossNameLabel.setText("Name: Shakespeare's Ghost");
			}
		}
	}

	// action listener for the Science Boss Button
	private class ScienceButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed){
				humBossButton.setBounds(0,0,140,140); 
				sciBossButton.setBounds(100,110,130,140); 
				mathBossButton.setBounds(0,250,130,140);  
				bossChosen="Science";
				bossHealthLabel.setText(sciBossHealth+"%");
				bossTypeLabel.setText("Type: Science");
				bossNameLabel.setText("Name: Froggerhut");
			}
		}
	}

	// action listener for the Math Boss Button
	private class MathButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed){
				humBossButton.setBounds(0,0,140,140); 
				sciBossButton.setBounds(0,110,130,140); 
				mathBossButton.setBounds(100,250,130,140); 
				bossChosen="Math";
				bossHealthLabel.setText(mathBossHealth+"%");
				bossTypeLabel.setText("Type: Math");
				bossNameLabel.setText("Name: Number of Doom");
			}
		}
	}

	// action listener for the Option A Button
	private class AButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed){
				optionA=true;
				optionB=false;
				specialAttack=false;
				repaint();
			}
		}
	}

	// action listener for the Option B Button
	private class BButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed){
				optionA=false;
				optionB=true;
				specialAttack=true;
				repaint();
			}
		}
	}
	
	// action listener for the Attack Button
	private class AttackButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!bossChosen.equals("")){
				ActionListener updateTask = new ActionListener() {

					public void actionPerformed(ActionEvent evt) {
						update();   // update the (x, y) position
						repaint();  // Refresh the JFrame, callback paintComponent()
					}
				};
				// Allocate a Timer to run updateTask's actionPerformed() after every delay msec
				if(playerHealth>0 && !attackPressed){
					attackPressed=true;
					timer = new Timer(20, updateTask);
					timer.start();
				}
			}
		}
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
			public void run() {
				new FinalBattle(new Player()); // Let the constructor do the job
			}
		});
		frame.setVisible(true);
	}


}
