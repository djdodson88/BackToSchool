package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import minigames.fruit.Fruit_Game;
import minigames.tiles.PuzzlePanel;
import minigames.sudoku.SudokuGame;
import minigames.beerpong.BeerPanel;
import minigames.warehouse.WareHouseKeeper;

/*
 * MiniSplashPane: layout container for MiniSplash
 */

public class MiniSplashPane extends JLayeredPane
{
	BackToSchool frame;
	private BufferedImage backgroundImg;
	private JPanel subPanel;
	private JButton default_exit; //for testing day structure, etc
	private Day day;
	private Player student;

	public MiniSplashPane(Player player, Day current)
	{
		day = current;
		student = player;

		try {
			backgroundImg = ImageIO.read(new File("art/classroom/classroom.jpg"));
		} catch (IOException e) {	
			e.printStackTrace();
		}

		// STRICTLY FOR TESTING, WILL BE REMOVED 
		//Button used for now to get through day-> weeks

		/*
		default_exit = new JButton(new ImageIcon("art/buttons/exit_btn.jpg"));
		this.add(default_exit);
		default_exit.setBounds(700,0, 100, 30);
		default_exit.setVisible(true);
		default_exit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{	
						// DEBUG PURPOSES
						Random random = new Random();
						int winStat = random.nextInt(4);
						double percentage = 0;
						
						switch(winStat)
						{
						case 0:
							percentage = 0.5;
							break;
						case 1:
							percentage = 0.4;
							break;
						case 2:
							percentage = 0.3;
							break;
						case 3:
							percentage = 0.1;
							break;
						}
						
						switch(day.getCourse())
						{
						case SCIENCE:
							student.increaseSciRigor(percentage);
							break;
						case HUMANITIES:
							student.increaseCreativit(percentage);
							break;
						case MATH:
							student.increaseQuantReasoning(percentage);
							break;
						}
						//--- END ----
						
						if(day.isTranscript())
						{
							frame.switchPanel(BackToSchool.Screen.TRANSCRIPT);
						}
						else
						{
							frame.switchPanel(BackToSchool.Screen.CAMPUS);
						}
					}
				});
		 		*/
		subPanel = new MiniSplash();
		subPanel.setBounds(50,60,500,300);
		add(subPanel);
		moveToFront(subPanel);

		setVisible(true);	
	}

	protected void switchPanels(JPanel panel)
	{
		remove(subPanel);
		subPanel = panel;
		add(subPanel);
		moveToFront(subPanel);
		repaint();
	}

	public void sendFrame(BackToSchool frame)
	{
		this.frame = frame;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, null);
	}

	private class MiniSplash extends JPanel
	{
		private boolean nxtScreen, finished;
		private BufferedImage binderBg, bulletpoint;
		private int game, numScreen;
		private JButton button, previous, skipButton;
		private JPanel minigame;
		private ImageIcon start, skip, exit, next;

		public MiniSplash()
		{			
			start = new ImageIcon("art/buttons/start_btn.jpg");
			skip = new ImageIcon("art/buttons/skip_btn.jpg");
			exit = new ImageIcon("art/buttons/exit_btn.jpg"); //eventually will be removed
			next = new ImageIcon("art/buttons/next_btn.jpg");
			skipButton = new JButton(skip);

			try {
				binderBg = ImageIO.read(new File("art/classroom/splash.jpg"));
				bulletpoint = ImageIO.read(new File("art/classroom/bulletpoint.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			/* Game Key
			 * 1 = Fruit
			 * 2 = Warehouse
			 * 3 = Sudoku
			 * 4 = Tile Puzzle
			 * 5 = Beer Pong
			 */

			Random generator = new Random();
			game = generator.nextInt(5) + 1; // 1-5

			//game = 3; //testing
			switch(game)
			{
			case 1:
				button = new JButton(start);
		
				break;
			case 2:
				button = new JButton(start);
		
				break;
			case 3:
				nxtScreen = true;
				button = new JButton(next);
				numScreen = 1;
	
				break;
			case 4:
				button = new JButton(start);
			
				break;
			case 5:
				button = new JButton(start);
				
				break;
			}
			finished = false;

			setLayout(null);
			add(button);

			if(nxtScreen)
			{
				add(skipButton);
				button.setBounds(150, 250, 100, 30);
				skipButton.setBounds(250, 250, 100, 30);
				skipButton.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e) 
					{	switchPanels();
					}
				});				
			}
			else
			{
				button.setBounds(200, 250, 100, 30);
			}
			button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{	if(game == 3 && !finished)
				{	
					numScreen++;
					finished = true;
					button.setIcon(start);
					repaint();
				}
				else
					switchPanels();
				}
			});

			setBounds(26, 32, 550, 450);
			setVisible(true);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(binderBg, 0, 0, null);
		
			drawString(g, setInstructions(), 60, 42);
		}

		private String setInstructions()
		{
			String instructions = "";
			String gameTitle = "";

			if(game == 1)
			{
				gameTitle = "Fruit Game";
				instructions += gameTitle + "\n\n";
				instructions += "Instructions: \n\n";
				instructions += ";liUse the <left> and <right> arrow keys to\n";
				instructions += "catch the falling fruit\n";
				instructions += ";li;indent Each fruit missed will decrease life points\n";
				instructions += ";li;indent Catch as many fruit as you can before life points is 0 \n";
				instructions += ";li;indent Avoid the bombs! Bombs will decrease points";
			}
			else if(game == 2)
			{
				gameTitle = "Warehouse Keeper";
				instructions += gameTitle + "\n\n";
				instructions += "Instructions: \n";
				instructions += ";liUse the <left>, <right>, <up>, <down> keys to move the player\n";
				instructions += ";liMove the boxes to the pink dots\n";
				instructions += ";liYou may use the UNDO stack 10 times\n";
			}
			else if(game == 3)
			{
				gameTitle = "Sudoku";
				instructions += gameTitle + "\n";

				if(numScreen == 1)
				{
					instructions += ";li Sudoku is a puzzle-game, which involves \nfilling in the grid with the correct number sequence ";
					instructions += "\n;liEach column, row, and subgrid must contain the number sequence \nof the n x n grid \n";
					instructions += ";li;indente.g. A 4x4 grid must be filled in by numbers 1-4";
				}
				else if(numScreen == 2)
				{
					instructions = "Instructions:\n";
					instructions += "\n ;liNavigate the grid by moving highlight square with arrow keys \n";
					instructions += ";liFill in the grid with numbers with number keys \n";
					instructions += ";liUse the <delete> key to clear cell entry,\n;li;indent you may also replace current cell\n;indent with another number key\n";
					instructions += ";liPress enter with attempted solution to see if correct \n";
					instructions += ";liFinish within time limit, and stats are rewarded accordingly";
				}
			}
			else if(game == 4)
			{
				gameTitle = "Tile Puzzle";
				instructions += gameTitle + "\n";
				instructions += "Instructions: \n";
				instructions += ";li Click the mouse to move the tile over\n";
				instructions += ";li Arrange the puzzle into the correct order.";

			}
			else if (game == 5)
			{
				gameTitle = "Beer Pong";
				instructions += gameTitle + "\n";
				instructions += "Instructions: \n";
				instructions += ";li Press <S> key for hiding/showing the Side View \n";
				instructions += ";li Use <up>, <down>, <left>, <right> keys to set X, Y speeds\n";
				instructions += " of the ball (Best for the Top View)\n";
				instructions += ";li Use <ctrl + up> <ctrl + down> <ctrl + left> <ctrl + right> \n";
				instructions += " to set X, Z speeds of the ball\n";
				instructions += ";li Use <shift + up> <shift + down> to set Z position of the ball\n";
				instructions += ";li Press <space> to fire the ball into the cups\n";
				instructions += ";li The game ends if the player makes all the cups and wins\n";
				instructions += " without getting drunk or if the player loses by getting drunk.";

			}
			return instructions;
		}	

		private void drawString(Graphics g, String text, int x, int y)
		{
			for(String line: text.split("\n"))
			{
				int bulletPos = y + g.getFontMetrics().getHeight();

				if(line.contains(";li"))
				{
					line = line.replace(";li","");

					if(line.contains(";indent"))
					{
						line = line.replace(";indent", "");
						g.drawString(line, x+50, y+=g.getFontMetrics().getHeight());
						g.drawImage(bulletpoint, x+30, bulletPos-10, null);	
						continue;
					}
					else
					{
						g.drawImage(bulletpoint, x-20, bulletPos-10, null);	
					}
				}
				else
				{
					if(line.contains(";indent"))	
					{
						line = line.replace(";indent","");
						g.drawString(line, x+50, y+=g.getFontMetrics().getHeight());
					}
					else
					{
						g.drawString(line, x, y+=g.getFontMetrics().getHeight());
					}
					continue;
				}
				g.drawString(line, x, y+=g.getFontMetrics().getHeight());
			}
		}

		private void switchPanels()
		{		
			frame.addPanel(new ClassroomPanel(student, game, day, frame), BackToSchool.Screen.CLASS);
			frame.switchPanel(BackToSchool.Screen.CLASS);
		}
	}
}
