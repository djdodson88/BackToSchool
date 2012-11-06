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
	
	// Boss variables
	ImageIcon boss;
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
		playerHealth=100;
		bossHealth=100;
	
		//adding the attack button
		button1 = new JButton("Attack");
		button1.addActionListener(new ButtonListener());
		button1.setBounds(340,400,100,30);
		
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
		playerHealthLabel.setBounds(600,340,100,100);
		creativityLabel.setBounds(600,380,150,100);
		quantReasoningLabel.setBounds(600,420,150,100);
		scientRigorLabel.setBounds(600,460,150,100);
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
		bossHealthLabel.setBounds(50,340,100,100);
		bossName.setBounds(50,420,140,30);
		bossType.setBounds(50,460,100,30);
		
		
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
		
		setVisible(true);
	}		

	public void moveBoss(){
		if(bossHealth>0){
			bossX+=xSpeed;

			if(bossX>400 && !(bossX<0))
			{
				playerHealth-=10;
				playerHealthLabel.setText(playerHealth+"%");// inflict damage on players health
				xSpeed=-xSpeed;
			}
			else if((bossX<5)){
				bossTimer.stop();
				bossX=0;
				xSpeed=5;
				bossTurn=false;
			}
		}
	}
	
	public void movePlayer(){
		studentX -= xSpeed;

		// if student touches the boss , tell him to go the other direction
		if (!(studentX > 600) && studentX < 200) 
		{
			bossHealth-=10;
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
			bossTimer = new Timer(10, updateTask);
			bossTimer.start();
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
		student.paintIcon(this, g, studentX, studentY);
		boss.paintIcon(this, g, bossX, bossY);
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
