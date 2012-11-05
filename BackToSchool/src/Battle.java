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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Battle extends JPanel {
	JButton button1;
	ImageIcon student;
	ImageIcon boss;
	Graphics graphics;
	int studentX;
	int studentY;
	int bossX;
	int bossY;
	int xSpeed;
	Timer timer;
	JLabel bossHealth;
	JLabel playerHealth;
	JLabel creativityLabel;
	JLabel quantReasoningLabel;
	JLabel scientRigorLabel;
	JLabel bossType;
	JLabel bossName;
	int health;
	boolean attackPressed;

	public Battle(Player player){
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		this.setBackground(Color.white);// color of background
		
		health=100;
		attackPressed=false;
		
		//adding the attack button
		button1 = new JButton("Attack");
		button1.addActionListener(new ButtonListener());
		
		setLayout(null);
		
		// loading the images
		student = new ImageIcon("art/battle/student.jpg");
		boss = new ImageIcon("art/battle/humboss.png");
		
		studentX=600;// x coordinate for student
		studentY=200;// y coordinate for student
		xSpeed=5;// speed for movement
		bossX=0;// x coordinate for boss
		bossY=0;// y coordinate for boss
		
		bossHealth = new JLabel(health+"%");
		playerHealth = new JLabel(health+"%");
		
		creativityLabel = new JLabel("Cretivity: "+player.getCreativity());
		quantReasoningLabel = new JLabel("Quantative Reasoning: "+player.getQuantReasoning());
		scientRigorLabel = new JLabel("Scientific Rigor: "+player.getSciRigor());
		bossName = new JLabel("Boss: Shakespeare");
		bossType = new JLabel("Type: Humanities");
		
		// buttons for attack
		button1.setBounds(340,400,100,30);
		
		// boss information
		bossHealth.setBounds(50,340,100,100);
		bossName.setBounds(50,420,140,30);
		bossType.setBounds(50,460,100,30);
		
		// players statistics
		playerHealth.setBounds(600,340,100,100);
		creativityLabel.setBounds(600,380,150,100);
		quantReasoningLabel.setBounds(600,420,150,100);
		scientRigorLabel.setBounds(600,460,150,100);
		
		this.add(bossName);
		this.add(bossType);
		this.add(button1);
		this.add(bossHealth);
		this.add(playerHealth);
		this.add(creativityLabel);
		this.add(quantReasoningLabel);
		this.add(scientRigorLabel);
		
		setVisible(true);
	}		

	public void update() {
		studentX -= xSpeed;
		
		// if student touches the boss , tell him to go the other direction
		if (!(studentX > 600) && studentX < 200) 
		{
			health-=10;
			bossHealth.setText(health+"%");
			xSpeed = -xSpeed;
		}
		// if the student reaches to the origin, make him stop
		// make the student stop at 599
		else if(studentX > 599 && !(studentX<200))
		{
			timer.stop();
			studentX=600;
			xSpeed=5;
			attackPressed=false;
		}
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
			System.out.println("Pressed Attack");
			ActionListener updateTask = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					update();   // update the (x, y) position
					repaint();  // Refresh the JFrame, callback paintComponent()
				}
			};
			// Allocate a Timer to run updateTask's actionPerformed() after every delay msec
			if(health>0 && !attackPressed){
				attackPressed=true;
				timer = new Timer(50, updateTask);
				timer.start();
			}
		}
		
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Back To School: Battle Mode");
		Battle battle = new Battle(new Player());
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(battle);
		frame.setContentPane(battle);
		frame.pack();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Battle(new Player()); // Let the constructor do the job
			}
		});
		frame.setVisible(true);
	}

}
