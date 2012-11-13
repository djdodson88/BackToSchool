import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Battle extends JPanel {
	// global variables
	JButton button1;
	boolean attackPressed;
	ImageIcon background;

	Graphics graphics;

	// Student variables
	ImageIcon student;
	ImageIcon backpack;
	int backpackX;
	int backpackY;
	int studentX;
	int studentY;
	int playerHealth;
	JLabel playerHealthLabel;
	JLabel creativityLabel;
	JLabel quantReasoningLabel;
	JLabel scientRigorLabel;
	Timer timer;
	Player player;
	boolean setDown;

	// Attack Menu variables
	JLabel defaultAttackLabel;
	JLabel specializedAttackLabel;
	JButton optionAButton;
	JButton optionBButton;
	ImageIcon scribble;
	ImageIcon A;
	ImageIcon B;
	boolean optionA;
	boolean optionB;
	boolean specialAttack;

	// Boss variables
	ImageIcon boss;
	ImageIcon attack;
	int attackX;
	int attackY;
	int bossX;
	int bossY;
	int xSpeed;
	int bossHealth;
	String bossSubject;
	Timer bossTimer;
	JLabel bossHealthLabel;
	JLabel bossSpecialAttackLabel;
	JLabel bossSpecialDefenseLabel;
	JLabel bossType;
	JLabel bossName;
	//JLabel bossStory;
	boolean bossTurn;

	public Battle(Player player, String classSubject){
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		this.setBackground(Color.white);// color of background

		bossSubject=classSubject;
		this.player = player;
		playerHealth=100;
		bossHealth=100;
		background = new ImageIcon("art/battle/battle.jpg");
		specialAttack=false;
		setDown=false;

		//adding the attack button
		button1 = new JButton("Attack");
		button1.addActionListener(new AttackButtonListener());
		button1.setBounds(400,510,100,30);

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

		//attacking menu
		defaultAttackLabel = new JLabel("Default Attack");
		defaultAttackLabel.setBounds(450,405,100,30);
		specializedAttackLabel = new JLabel("Special Attack");
		specializedAttackLabel.setBounds(450,455,160,30);
		scribble = new ImageIcon("art/battle/scribble_sprite.png");
		// by default make option A
		optionA=true;
		optionB=false;

		setLayout(null);

		//----------------------Player Variables--------------------------------//
		student = new ImageIcon("art/characters/student_leftside.png"); // loading image
		backpack = new ImageIcon("art/battle/backpack.png");
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


		//setting location of statistics
		playerHealthLabel.setBounds(670,340,100,100);
		creativityLabel.setBounds(620,380,150,100);
		quantReasoningLabel.setBounds(620,420,150,100);
		scientRigorLabel.setBounds(620,460,150,100);
		//---------------------End of Player Variables---------------------------//

		//----------------------- Boss Variables----------------------------------//
		bossTurn=false;
		xSpeed=5;// speed for movement
		bossX=0;// x coordinate for boss
		bossY=0;// y coordinate for boss

		bossHealthLabel = new JLabel(bossHealth+"%");

		if(bossSubject.equals("Humanities")){
			bossName = new JLabel("Boss: Shakespeare's Ghost");
			bossType = new JLabel("Type: Humanities");
			bossSpecialAttackLabel = new JLabel("Special Attack: Artistic Squeeze");
			bossSpecialDefenseLabel = new JLabel("Special Defense: Long Live the King!");
			boss = new ImageIcon("art/battle/humboss.png");
			//bossStory = new JLabel("Hello");
		}
		else if(bossSubject.equals("Science"))
		{
			bossName = new JLabel("Boss: Froggerhut");
			attack = new ImageIcon("art/battle/scalpels.png");
			bossType = new JLabel("Type: Science");
			bossSpecialAttackLabel = new JLabel("Special Attack: Shooting Scalpels");
			bossSpecialDefenseLabel = new JLabel("Special Defense: Preservation");
			boss = new ImageIcon("art/battle/science_boss.png");
			//bossStory = new JLabel("");
		}
		else if(bossSubject.equals("Math"))
		{
			bossName = new JLabel("Boss: Number of Doom");
			bossType = new JLabel("Type: Math");
			boss = new ImageIcon("art/battle/math_boss.png");
			bossSpecialAttackLabel = new JLabel("Special Attack: Number Cruncher");
			bossSpecialDefenseLabel = new JLabel("Special Defense: Bias Data");
			//bossStory = new JLabel("");
		}

		//setting location of statistics
		bossHealthLabel.setBounds(100,340,100,100);
		bossName.setBounds(100,420,200,30);
		bossType.setBounds(100,460,100,30);
		bossSpecialAttackLabel.setBounds(100,500,200,30);
		bossSpecialDefenseLabel.setBounds(100,540,200,30);
		//bossStory.setBounds(100,500,100,10);

		//--------------------- End of Boss Variables-----------------------------//

		// adding components to the jpanel
		this.add(bossName);
		this.add(bossType);
		this.add(bossSpecialAttackLabel);
		this.add(bossSpecialDefenseLabel);
		//this.add(bossStory);
		this.add(button1);
		this.add(optionAButton);
		this.add(optionBButton);
		this.add(bossHealthLabel);
		this.add(playerHealthLabel);
		this.add(creativityLabel);
		this.add(quantReasoningLabel);
		this.add(scientRigorLabel);
		this.add(specializedAttackLabel);
		this.add(defaultAttackLabel);

		setVisible(true);
	}		

	public void moveBoss(){
		if(bossHealth>0){
			// -------------------Science boss atack-----------------------//
			if(bossSubject.equals("Science"))
			{
				attackX+=xSpeed;

				if(attackX>700)
				{
					playerHealth-=(11-player.getSciRigor());

					playerHealthLabel.setText(playerHealth+"%");// inflict damage on players health
					bossTimer.stop();
					bossTurn=false;
				}
			}
			else if(bossSubject.equals("Math"))
			{
				bossX += xSpeed;
				// if student touches the boss , tell him to go the other direction
				if (bossX > 500) 
				{
					playerHealth-=(11-player.getQuantReasoning());
					playerHealthLabel.setText(playerHealth+"%");
					xSpeed = -xSpeed;
				}
				// if the student reaches to the origin, make him stop
				else if(bossX < 4)
				{
					bossTimer.stop();
					bossX=0;
					xSpeed=5;
					bossTurn=false;
				}
			}
		}
		//-------------------- end of Science boss attack-----------------//
	}

	public void movePlayer(){
		backpackX -= xSpeed;
		backpackY -= 1;

		//if the student reaches to the origin, make him stop
		if(backpackX < 190)
		{
			if(specialAttack){
				if(bossSubject=="Science")
					bossHealth-=player.getCreativity()*10;
				else if(bossSubject=="Math")
					bossHealth-=player.getQuantReasoning()*10;
				else if(bossSubject=="Humanities")
					bossHealth-=player.getSciRigor()*10;
			}
			else
				bossHealth-=5;

			bossHealthLabel.setText(bossHealth+"%");// inflict damage on boss's health
			timer.stop();
			setDown=false;
			backpackX=600;
			backpackY=200;
			xSpeed=5;
			attackPressed=false;
			bossTurn=true;
			ActionListener updateTask = new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					update();   // update the (x, y) position
					repaint();  // Refresh the JFrame, callback paintComponent()
				}
			};
			bossTimer = new Timer(5, updateTask);
			bossTimer.start();
			attackX=60;
			attackY=240;
		}
	}

	public void update() {
		if(bossTurn)
			moveBoss();
		else
			movePlayer();
	}

	// paint the images and graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics=g;
		background.paintIcon(this,g,0,0);
		student.paintIcon(this, g, studentX, studentY);
		boss.paintIcon(this, g, bossX, bossY);

		if(attackPressed)
			backpack.paintIcon(this, g, backpackX,backpackY);

		if(optionA)
			scribble.paintIcon(this, g, 395, 400);
		else if(optionB)
			scribble.paintIcon(this,g,395,452);

		if(bossTurn && bossSubject.equals("Science"))
		{
			attack.paintIcon(this,g,attackX,attackY);
		}
	}


	// action listener for the attack button
	private class AttackButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event){
			ActionListener updateTask = new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					update();   // update the (x, y) position
					repaint();  // Refresh the JFrame, callback paintComponent()
				}
			};
			// Allocate a Timer to run updateTask's actionPerformed() after every delay msec
			if(playerHealth>0 && !attackPressed && !bossTurn){
				attackPressed=true;
				timer = new Timer(20, updateTask);
				timer.start();
			}
		}

	}

	// action listener for the Option A Button
	private class AButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed){
				//System.out.println("Pressed A");
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
				//System.out.println("Pressed B");
				optionA=false;
				optionB=true;
				specialAttack=true;
				repaint();
			}
		}
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Back To School: Battle Mode");
		Battle battle = new Battle(new Player(),"Math");
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(battle);
		frame.setContentPane(battle);
		frame.pack();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Battle(new Player(),"Math"); // Let the constructor do the job
			}
		});
		frame.setVisible(true);
	}

}
