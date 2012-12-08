package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Transcript extends JPanel{

	private BufferedImage background;
	private BufferedImage seal;
	private BufferedImage star;
	
	private boolean starCreativ;
	private boolean starMath;
	private boolean starSci;

	boolean drawnCreativStar;
	boolean drawnSciStar;
	boolean drawnMathStar;
	
	private int week;
	private JButton exit;

	private String[] feedback;
	private double[] skills;
	private String[] achievements;
	
	private String creativFeedback;
	private String sciFeedback;
	private String mathFeedback;
	
	private String nextWeek;
	
	private ImageIcon star1;
	private ImageIcon star2;
	private ImageIcon star3;
	
	private Player student;

	Day day;
	BackToSchool frame;
	
	public Transcript(Player player, Day day){
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		setLayout(null);
		setup(day);
		this.day = day;
		student = player;
		
		
		skills = new double[3];
		skills[0] = student.getCreativity();
		skills[1] = student.getSciRigor();
		skills[2] = student.getQuantReasoning();
		
		exit = new JButton(new ImageIcon("art/buttons/exit_btn.jpg"));
		exit.setBounds(650,550, 100, 30);
		
		star1 = new ImageIcon("art/transcript/star_bullet.png");
		star2 = new ImageIcon("art/transcript/star_bullet.png");
		star3 = new ImageIcon("art/transcript/star_bullet.png");
		
		
		exit.addActionListener(new exitButtonListener());
		this.add(exit);
	}
	
	private void setup(Day day)
	{
		try {
			background = ImageIO.read(new File("art/transcript/transcript_bg.jpg"));
			seal = ImageIO.read(new File("art/transcript/seal.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		starCreativ = false;
		starSci = false;
		starMath = false;
		
		drawnCreativStar = false;
		drawnSciStar = false;
		drawnMathStar = false;
		
		week = 0;

		if(day.getDay() <= 4)
		{
			week = 1;
			nextWeek = "Prepare for this week, 'tis the midterm season!";
		}
		else if(day.getDay() <= 7)
		{
			// Midterm
			week = 2;
			nextWeek = "Finals are this Week! Good Luck!";
		}
		else
		{
			// Final Transcript
			week = 3;
			nextWeek = "";
		
		}
		
	}
	
	private class exitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			day.setTranscriptState(true);
			frame.switchPanel(BackToSchool.Screen.CAMPUS);
			
		}	
	}
	
	protected void sendFrame(BackToSchool frame) 
	{
		this.frame = frame;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		g.drawImage(seal, 625, 20, null);
		
		Font title = new Font("Courier", Font.PLAIN, 30);
		Font weekFont = new Font("Courier", Font.BOLD, 25);
		Font defaultFont = new Font("Courier", Font.PLAIN, 16);
		
		g.setFont(title);
		g.drawString("Transcript", 20,80);
		g.drawLine(20, 100, 600, 100);
		
		// Week 
		g.setFont(weekFont);	
		String weekString = "";
		int nextWeek_X = 0;
		int nextWeek_Y = 370;
		
		switch(week)
		{
		case 1:
			weekString = "One";
			nextWeek_X = 320;
			break;
		case 2:
			weekString = "Two";
			nextWeek_X = 450;
			break;
		case 3:
			weekString = "Three";
			nextWeek_X = 400;
			break;
		}
		
		g.drawString("Week " + weekString, 20, 150);
		
		g.setFont(defaultFont);
		g.drawString(nextWeek, nextWeek_X, nextWeek_Y);
		g.setFont(weekFont);	
		
		// Achievements:
		g.drawString("Achievements:", 20, 400);
		g.setFont(defaultFont);
		
		generateAchievements();

		if(achievements[0].equals("")
				&& achievements[1].equals("") 
				&& achievements[2].equals(""))
		{		
			g.drawString("None", 20, 430);
		}
		else
		{
			int yCoord = 430;
			
			int count = 0;
			
			for(int i = 0; i < achievements.length; i++)
			{
				
				if(!achievements[i].equals(""))
				{
					g.drawString(achievements[i], 60, yCoord);
					
					yCoord += 40;
					count++; // keeps track of how  much stars to paint
				}
				
			
			}
			

			if(count >= 1)
			{
				star1.paintIcon(this, g, 20, 410);
			}
			
			if(count >= 2)
			{
				star2.paintIcon(this, g, 20, 450);
			}
			
			if(count == 3)
			{
				star3.paintIcon(this, g, 20, 490);
			}
		}
		

		
		//Feedback
		generateFeedback();
		
		// Stats:
		String creativity = "Creativity: " + student.getCreativity();
		String science = "Science: " + student.getSciRigor();
		String math = "Math: " + student.getQuantReasoning();
		
		g.drawString(creativity, 50, 200);
		g.drawString(science, 50, 250);
		g.drawString(math, 50, 300);
		
		g.drawString(" - ", 250,200);
		g.drawString(feedback[0], 325, 200);
		g.drawString(" - ", 250,250);
		g.drawString(feedback[1], 325, 250);
		g.drawString(" - ", 250,300);
		g.drawString(feedback[2], 325, 300);
		
		
	}

	
	private void generateAchievements()
	{
		achievements = new String[3];
		
		String[] type = new String[3];
		type[0] = "Creativity";
		type[1] = "Science";
		type[2] = "Math";	
		
		// empty for now
		for(int i = 0; i < achievements.length; i++)
		{
			achievements[i] = "";
			
		}
		
		for(int j = 0; j < achievements.length; j++)
		{
			starCreativ = true;
			starSci = true;
			starMath = true;
			
			if(skills[j] == 4.0)
			{
				achievements[j] = "UNLOCKED " + type[j] + " Skill : FREEZE";
				
			}
			else if(skills[j] >= 2.5)
			{
				achievements[j] = "UNLOCKED " + type[j] + " Skill : SPECIAL ATTACK";
			}
			else
			{
				if(type[j].equals("Creativity"))
				{
					starCreativ = false;
				}
				else if(type[j].equals("Science"))
				{
					starSci = false;
				}
				else if(type[j].equals("Math"))
				{
					starMath = false;
				}
			}
		}
		
	}
	
	private void generateFeedback()
	{
		feedback = new String[3];
		feedback[0] = creativFeedback;
		feedback[1] = sciFeedback;
		feedback[2]	= mathFeedback;
		
		
		for(int i = 0; i < feedback.length; i++)
		{
			String comment = "";
			if(skills[i] <= 1.5)
			{
				comment = "Get to Class";
			}
			else if(skills[i] <= 2.5)
			{
				comment = "Good";
			}
			else if(skills[i] <= 3.5)
			{
				comment = "Great! Keep it up!";
			}
			else if(skills[i] == 4.0)
			{
				comment = "EXCELLENT! Ace those finals!";
			}
			
			feedback[i] = comment;
		}
	}
	
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Back To School: Transcript");
		Transcript transcript = new Transcript(new Player(), new Day(3));
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setContentPane(transcript);
		frame.pack();

		frame.setVisible(true);
	}

}
