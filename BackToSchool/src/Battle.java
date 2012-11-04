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
	int health;
	boolean attackPressed;

	public Battle(){
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		this.setBackground(Color.white);// color of background
		
		health=100;
		attackPressed=false;
		
		//adding the attack button
		button1 = new JButton("Attack");
		button1.setBounds(500,500,100,30);
		button1.addActionListener(new ButtonListener());
		add(button1);
		setLayout(null);
		
		// loading the images
		student = new ImageIcon("images/student.jpg");
		boss = new ImageIcon("images/humboss.png");
		
		studentX=500;// x coordinate for student
		studentY=200;// y coordinate for student
		xSpeed=5;// speed for movement
		bossX=0;// x coordinate for boss
		bossY=0;// y coordinate for boss
		
		bossHealth = new JLabel(health+"%");
		playerHealth = new JLabel(health+"%");
		
		bossHealth.setBounds(100,300,100,100);
		this.add(bossHealth);
		
		playerHealth.setBounds(545,300,100,100);
		this.add(playerHealth);
		
		setVisible(true);
	}		

	public void update() {
		studentX -= xSpeed;
		
		// if student touches the boss , tell him to go the other direction
		if (!(studentX > 600 - 50) && studentX < 200) 
		{
			health-=10;
			bossHealth.setText(health+"%");
			xSpeed = -xSpeed;
		}
		// if the student reaches to the origin, make him stop
		else if(studentX > 600 - 50 && !(studentX<200))
		{
			timer.stop();
			studentX=550;
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
		Battle battle = new Battle();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(battle);
		frame.setContentPane(battle);
		frame.pack();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Battle(); // Let the constructor do the job
			}
		});
		frame.setVisible(true);
	}

}
