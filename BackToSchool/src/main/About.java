package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class About extends JPanel implements ActionListener {

	private BufferedImage bg;
	private JButton back,nextScreen, prevButton;
	private BackToSchool frame;
	
	private int[] next_Scn; // 0 = start, 1 = end
	private int[] currentScreen;
	private int[] prevScreen;

	
	public About()
	{
		setup();
		setLayout(null);

		currentScreen = new int[2];
		currentScreen[0] = 0;
		currentScreen[1] = 16;

		next_Scn = new int[2];
		next_Scn[0] = 17;
		next_Scn[1] = 33;

		prevScreen = new int[2];

		nextScreen = new JButton(new ImageIcon("art/buttons/next_btn.jpg"));
		prevButton = new JButton(new ImageIcon("art/buttons/prevButton.jpg"));

		this.setPreferredSize(new Dimension(800,600));

		back = new JButton(new ImageIcon("art/buttons/back_btn.jpg")); // for now

		this.add(back);
		back.setBounds(250,500, 300,49);
		back.setVisible(true);
		back.addActionListener(this);

		this.add(nextScreen);
		nextScreen.setBounds(200,430, 100,30);
		nextScreen.setVisible(true);
		nextScreen.addActionListener(this);

		this.add(prevButton);
		prevButton.setBounds(150,430, 100, 30);
		prevButton.setVisible(false);
		prevButton.addActionListener(this);

	}

	public void paintComponent(Graphics g)
	{
		g.drawImage(bg, 0, 0, null);

		Font title = new Font("Courier", Font.BOLD, 30);
		Font defaultFont = new Font("Courier", Font.PLAIN, 11);
		Font bold = new Font("Courier", Font.BOLD, 12);
		
		g.setColor(Color.WHITE);
		g.fillRect(20, 70, 450, 400);
		g.fillRect(500, 70, 250, 400);

		g.setColor(Color.black);
		
		g.setFont(title);
		
		g.drawString("About the Game", 20, 60);
		g.setFont(defaultFont);
		
		int yOffset = 0;

		for(int i = 0; i < createdBy().length; i++)
		{
			g.drawString(createdBy()[i], 510, 90 + yOffset);

			if(i%2 == 0 && i < 10)
			{
				g.drawLine(510, 95+yOffset, 740, 95+yOffset);
			}
			else if(i == 10 || i == 11)
			{
				g.drawLine(510, 95+yOffset, 740, 95+yOffset);
			}
			else if(i > 13)
			{
				g.drawLine(510, 95+yOffset, 740, 95+yOffset);
			}
			yOffset += 20;
		}

		yOffset = 0;
		for(int j = currentScreen[0]; j <= currentScreen[1]; j++)
		{
			if(j == 0)
			{
				g.setFont(bold);
				g.drawString(instructions()[j], 30, 90+yOffset);

			}
			else
			{
				g.setFont(defaultFont);
				g.drawString(instructions()[j], 30, 90 + yOffset);
			}
			yOffset += 20;
		}



	}

	
	private void setup()
	{
		try {
			bg = ImageIO.read(new File("art/school/about_screen.jpg"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String[] createdBy()
	{
		String[] created = new String[16];

		created[0] = "Programmed by:";	
		created[1] = "Keith Hims:";
		created[2] = "Programmer/ UI";
		created[3] = "DJ Dodson:";
		created[4] = "Programmer / AI";
		created[5] = "Jackie Chu:";
		created[6] = "Programmer / 2D Graphics";
		created[7] = "Cagatay Shahin:";
		created[8] = "Programmer";
		created[9] = "\n";
		created[10] = "CS 113";
		created[11] = "Fall 2012";
		created[12] = "\n";
		created[13] = "Credits to...";
		created[14] = "Sound Bible";
		created[15] = "Java Swing Library";
		
		return created;
	}

	private String[] instructions()
	{
		String[] instructions = new String[50];

		// One Screen
		instructions[0] = "Instructions:";
		instructions[1] = "There are five views:";
		instructions[2] = "\n";
		instructions[3] = "Campus";
		instructions[4] = "Classroom";
		instructions[5] = "Exams (Midterms/Final)";
		instructions[6] = "Library";
		instructions[7] = "Transcript";
		instructions[8] = "\n";
		instructions[9] = "On the Open Campus, use the <arrow keys> to navigate.";
		instructions[10] = "The goal is to get to classroom, before time runs out.";
		instructions[11] = "An arrow will guide you to your next destination.";
		instructions[12] = "\n";
		instructions[13] = "In the classroom, a mini-game must be played before class,";
		instructions[14] = "so the student(you) can stimulate their mind,";
		instructions[15] =  "so they can stay awake in the game.  GPA points are";
		instructions[16] = "rewarded, based on the performance of the minigame.";

		// Second Screen
		instructions[17] = "A variety of points are only rewarded if you attend class.";
		instructions[18] = "If you miss the class, you will need to attend the next one.";
		instructions[19] = "Attending class will give you an advantage for the exams.";
		instructions[20] = "\n";
		instructions[21] = "The quarter lasts for 3 weeks, each week being 3 days";
		instructions[22] = "Week 2 is when midterms start. Day 10 will be finals,";
		instructions[23] = "where all three subject bosses will be faced.";
		instructions[24] = "\n";
		instructions[25] = "During Exams (Midterm/Finals), use the <mouse> to choose";
		instructions[26] = "from your available attacks.  Your attacks and defense";
		instructions[27] = "are based on your statistics in the respective course.";
		instructions[28] = "Select which boss to attack, and what type of attack to do.";
		instructions[29] = "Certain skills will unlock based on your GPA of that course";
		instructions[30] = "You will see if they are activated once you choose the boss.";
		instructions[31] = "There is a miss and hit probability for all attacks";
		instructions[32] = "Bosses can cause high or low hit points, based on probability";
		instructions[33] = "Even with low stats, there is a chance of you still winning!";
		
		
		// Third Screen
		instructions[34] = "The library is also an option for you to raise your statistics.";
		instructions[35] = "The library will be open once per day, and if you arrive to it,";
		instructions[36] = "you can study a specific course to raise your stats.";
		instructions[37] = "The library may be closed, or you may not have enough time";
		instructions[38] = "to go to class AND the library.  Go to the library wisely.";
		instructions[39] = "\n";
		instructions[40] = "A weekly transcript will be shown to show your current";
		instructions[41] = "statistics.  Make sure that you increase your stats,";
		instructions[42] = "by attending classes, doing well in the mini games, and";
		instructions[43] = "occasionally going to the library.";
		instructions[44] = "\n";
		instructions[45] = "Once you finish the final, a final transcript will show your";
		instructions[46] = "overall score (GPA).  Play multiple times to set higher and";
		instructions[47] = "higher scores.";
		instructions[48] = "\n";
		instructions[49] = "Good luck and earn a fantastic GPA!!";
		
		return instructions;
	}


	protected void sendFrame(BackToSchool frame) 
	{
		this.frame = frame;
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object src = e.getSource();

		if(src == back)
		{
			frame.switchPanel(BackToSchool.Screen.WELCOME);
		}
		else if(src == nextScreen)
		{
			if(currentScreen[0] == 0 && currentScreen[1] == 16)
			{
				prevScreen[0] = currentScreen[0];
				prevScreen[1] = currentScreen[1];

				currentScreen[0] = 17;
				currentScreen[1] = 33;

				nextScreen.setBounds(260, 430, 100, 30);
				nextScreen.setVisible(true);
				prevButton.setVisible(true);

			}
			else if(currentScreen[0] == 17 && currentScreen[1] == 33)
			{
				prevScreen[0] = currentScreen[0];
				prevScreen[1] = currentScreen[1];
				
				currentScreen[0] = 34;
				currentScreen[1] = 49;
				
				// No next screen
				next_Scn[0] = 0;
				next_Scn[1] = 1;
				
				nextScreen.setVisible(false);
				prevButton.setBounds(200, 430, 100, 30);
			}
			
			

		}
		else if(src == prevButton)
		{
			if(currentScreen[0] == 17 && currentScreen[1] == 33)
			{
				prevScreen[0] = 0;
				prevScreen[1] = 0;

				currentScreen[0] = 0;
				currentScreen[1] = 16;

				next_Scn[0] = 17;
				next_Scn[1] = 33;
				prevButton.setVisible(false);
				nextScreen.setBounds(200,430,100,30);
		
			}
			else if(currentScreen[0] == 34 && currentScreen[1] == 49)
			{
				next_Scn[0] = 34;
				next_Scn[1] = 49;
				
				currentScreen[0] = 17;
				currentScreen[1] = 33;
				
				prevScreen[0] = 0;
				prevScreen[1] = 16;
				
				prevButton.setBounds(150, 430, 100, 30);
				nextScreen.setVisible(true);
			}

		}

		repaint();
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Back To School: Library");
		About about = new About();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(about);
		frame.setVisible(true);
		frame.pack();
	}
}

