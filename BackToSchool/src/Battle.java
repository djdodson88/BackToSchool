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

public class Battle extends JPanel {
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
	JLabel bossType;
	JLabel bossName;
	boolean bossTurn;
	

	public Battle(Player player, String classSubject){
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		this.setBackground(Color.white);// color of background
		
		bossSubject=classSubject;
		this.player = player;
		playerHealth=100;
		bossHealth=100;
		background = new ImageIcon("art/battle/battle.jpg");
	
		//adding the attack button
		button1 = new JButton("Attack");
		button1.addActionListener(new ButtonListener());
		button1.setBounds(400,510,100,30);
		
		//attacking menu
		defaultAttackLabel = new JLabel("Default Attack");
		defaultAttackLabel.setBounds(450,405,100,30);
		specializedAttackLabel = new JLabel("Special Attack");
		specializedAttackLabel.setBounds(450,455,160,30);
		
		setLayout(null);
		
		//----------------------Player Variables--------------------------------//
		student = new ImageIcon("art/battle/student.jpg"); // loading image
		studentX=600;// x coordinate for student
		studentY=200;// y coordinate for student
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
			boss = new ImageIcon("art/battle/humboss.png");
		}
		else if(bossSubject.equals("Science"))
		{
			bossName = new JLabel("Boss: Frogerhut");
			attack = new ImageIcon("art/battle/scalpels.png");
			bossType = new JLabel("Type: Science");
			boss = new ImageIcon("art/battle/science_boss.png");
		}
		else if(bossSubject.equals("Math"))
		{
			bossName = new JLabel("Boss: Number of Doom");
			bossType = new JLabel("Type: Math");
			boss = new ImageIcon("art/battle/math_boss.png");
		}
		
		//setting location of statistics
		bossHealthLabel.setBounds(100,340,100,100);
		bossName.setBounds(100,420,200,30);
		bossType.setBounds(100,460,100,30);
		
		//--------------------- End of Boss Variables-----------------------------//
		
		// adding components to the jpanel
		this.add(bossName);
		this.add(bossType);
		this.add(button1);
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
		// -------------------Science boss atack-----------------------//
		if(bossSubject.equals("Science"))
		{
			if(bossHealth>0){
				attackX+=xSpeed;

				if(attackX>700)
				{
					playerHealth-=10;
					playerHealthLabel.setText(playerHealth+"%");// inflict damage on players health
					bossTimer.stop();
					bossTurn=false;
				}
			}
		}
		//-------------------- end of Science boss attack-----------------//
	}
	
	public void movePlayer(){
		studentX -= xSpeed;

		// if student touches the boss , tell him to go the other direction
		if (!(studentX > 600) && studentX < 200) 
		{
			if(bossSubject=="Science")
				bossHealth-=player.getCreativity()*10;
			else if(bossSubject=="Math")
				bossHealth-=player.getQuantReasoning()*10;
			else if(bossSubject=="Humanities")
				bossHealth-=player.getSciRigor()*10;
			
			bossHealthLabel.setText(bossHealth+"%");// inflict damage on boss's health
			xSpeed = -xSpeed;
		}
		
		// if the student reaches to the origin, make him stop
		else if(studentX > 599 && !(studentX<200))
		{
			timer.stop();
			studentX=600;
			xSpeed=5;
			attackPressed=false;
			bossTurn=true;
			ActionListener updateTask = new ActionListener() {
				@Override
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
		
		if(bossTurn && bossSubject.equals("Science"))
		{
			attack.paintIcon(this,g,attackX,attackY);
		}
	}


	// action listener for the attack button
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//System.out.println("Pressed Attack");
			ActionListener updateTask = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					update();   // update the (x, y) position
					repaint();  // Refresh the JFrame, callback paintComponent()
				}
			};
			// Allocate a Timer to run updateTask's actionPerformed() after every delay msec
			if(playerHealth>0 && !attackPressed && !bossTurn){
				attackPressed=true;
				timer = new Timer(10, updateTask);
				timer.start();
			}
		}
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Back To School: Battle Mode");
		Battle battle = new Battle(new Player(),"Science");
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(battle);
		frame.setContentPane(battle);
		frame.pack();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Battle(new Player(),"Science"); // Let the constructor do the job
			}
		});
		frame.setVisible(true);
	}

}
