package minigames.sudoku;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

public class SudokuGame extends JPanel implements ActionListener{

	private BufferedImage fourByfour_grid;
	private BufferedImage colorSq;


	// Game Related
	private int[][] currentAnswer;
	private int[][] setup;
	private SudokuSol gameSol;
	private String gameStatus; //in progress, right, wrong

	// Game Booleans
	private boolean isRunning;
	private boolean fourxfour;


	private Timer timer;
	private Clock gameTimer;
	private long timeLeft;

	// Coordinates
	private int sq_x = 20;
	private int sq_y = 13;




	public SudokuGame(int day)
	{

		InputMap myInputMap = new InputMap();
		ActionMap myActionMap = new ActionMap();
		
		oneKey oneKey = new oneKey();
		twoKey twoKey = new twoKey();
		threeKey threeKey = new threeKey();
		fourKey fourKey = new fourKey();
		fiveKey fiveKey = new fiveKey();
		sixKey sixKey = new sixKey();
		sevenKey sevenKey = new sevenKey();
		eightKey eightKey = new eightKey();
		nineKey nineKey = new nineKey();
		
		enter enter = new enter();
		up up = new up();
		down down = new down();
		left left = new left();
		right right = new right();

		myInputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);

		//Number Keys
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "1");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, false), "2");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, false), "3");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0, false), "4");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0, false), "5");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0, false), "6");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0, false), "7");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0, false), "8");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0, false), "9");

		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0, false), "enter");

		// Arrow Keys
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");

		myActionMap = this.getActionMap();
		myActionMap.put("1", oneKey);
		myActionMap.put("2", twoKey);
		myActionMap.put("3", threeKey);
		myActionMap.put("4", fourKey);
		myActionMap.put("5", fiveKey);
		myActionMap.put("6", sixKey);
		myActionMap.put("7", sevenKey);
		myActionMap.put("8", eightKey);
		myActionMap.put("9", nineKey);
		
		myActionMap.put("up", up);
		myActionMap.put("down", down);
		myActionMap.put("left", left);
		myActionMap.put("right", right);

		// Miscellaneous
		myActionMap.put("enter", enter);

		setup();
		gameSol = new SudokuSol(day);
		gameStatus = "In Progress"; // Default until <Enter> Pressed

		setup = gameSol.gridToShow();

		setBackground(new Color(58,54,55));


		setFocusable(true);
		isRunning = true;

		if(day <= 6)
		{
			// FOUR BY FOUR GRID (LVL 1&2)
			fourxfour = true;
			if(day >=3)
			{
				gameTimer = new Clock(20);
			}
			else
			{
				gameTimer = new Clock(40);
			}
			
			currentAnswer = new int[4][4];
		}
		else
		{
			// NINE BY NINE GRID (LVL 3)
			
			gameTimer = new Clock(90);
			currentAnswer = new int[9][9];
		}
		
		gameTimer.start();
		gameLoop();

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if(fourxfour)
		{
			g.drawImage(fourByfour_grid, 0, 0, null);
			g.drawImage(colorSq, sq_x, sq_y, null);

			g.setColor(new Color(255,254,215));
			g.fillRect(415, 60, 120, 300);

			timeLeft = gameTimer.timeRemaining();
			Font font = new Font("Arial", Font.BOLD, 50);
			Font markup = new Font("Arial", Font.PLAIN, 50);
			Font gameTxt = new Font("Arial", Font.PLAIN, 18);

			g.setFont(gameTxt);
			g.setColor(Color.BLACK);
			g.drawString("Timer", 420, 80);

			if(timeLeft == 0)
			{
				isRunning = false;
				gameStatus = "Game Over";
			}
			g.drawString(String.valueOf(timeLeft), 420, 100);

			g.drawString("Status", 420, 200);
			g.drawString(gameStatus, 420, 220);


			g.setFont(font);

			int dx = 0;
			int posx = 50;
			int posy = 80;
			int dy = 0;

			// draw for setup
			for(int row = 0; row < 4; row++)
			{
				for(int col = 0; col < 4; col++)
				{	
					g.setColor(Color.BLACK);


					if(setup[row][col] == 0)
					{
						g.drawString("", posx+dx, posy+dy);
					}
					else
					{
						currentAnswer[row][col] = setup[row][col];
						g.drawString(String.valueOf(setup[row][col]), posx+dx, posy+dy);
					}
					dx += 95;
				}
				dx = 0;
				dy += 100;
			}

			dx = 0;
			dy = 0;

			// draw for updates


			for(int row = 0; row < 4; row++)
			{
				for(int col = 0; col < 4; col++)
				{
					if(setup[row][col] == 0 && currentAnswer[row][col] != 0)
					{
						g.setFont(markup);
						g.setColor(Color.RED);
						g.drawString(String.valueOf(currentAnswer[row][col]), posx+dx, posy+dy);
					}
					dx += 95;
				}
				dx = 0;
				dy += 100;

			}
		}

	}


	public void gameLoop()
	{
		timer = new Timer();
		int fps = 80;
		timer.schedule(new Loop(), 0, 1000/fps);

	}

	public class Loop extends java.util.TimerTask
	{
		public void run()
		{
			repaint(); // render

			if(!isRunning)
			{
				timer.cancel();
			}
		}
	}

	private void setup()
	{
		// Image Setup
		try {

			fourByfour_grid = ImageIO.read(new File("art/sudoku/4x4grid.jpg"));
			colorSq = ImageIO.read(new File("art/sudoku/highlight_sq.jpg"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private int[] getIndex()
	{
		int index_i = 0;
		int index_j = 0;

		if(fourxfour)
		{
			switch(sq_x)
			{
			case 20:
				index_j = 0;
				break;
			case 115:
				index_j = 1;
				break;
			case 210:
				index_j = 2;
				break;
			case 305:
				index_j = 3;
				break;
			default:
				break;
			}

			switch(sq_y)
			{
			case 13:
				index_i = 0;
				break;
			case 114:
				index_i = 1;
				break;
			case 215:
				index_i = 2;
				break;
			case 315:
				index_i = 3;
				break;
			default:
				break;
			}
		}

		int[] arrayIndex = new int[2];
		arrayIndex[0] = index_i;
		arrayIndex[1] = index_j;

		return arrayIndex;
	}

	private class oneKey extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{

			if(timeLeft != 0)
			{
				currentAnswer[getIndex()[0]][getIndex()[1]] = 1;
				repaint();
			}

		}
	}
	private class twoKey extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(timeLeft != 0)
			{
				currentAnswer[getIndex()[0]][getIndex()[1]] = 2;
				repaint();

			}
		}
	}
	private class threeKey extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(timeLeft != 0)
			{
				currentAnswer[getIndex()[0]][getIndex()[1]] = 3;
				repaint();

			}
		}
	}
	private class fourKey extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(timeLeft != 0)
			{
				currentAnswer[getIndex()[0]][getIndex()[1]] = 4;
				repaint();

			}
		}
	}
	private class fiveKey extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(timeLeft != 0 && !fourxfour)
			{
				currentAnswer[getIndex()[0]][getIndex()[1]] = 5;
				repaint();

			}
		}
	}
	private class sixKey extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(timeLeft != 0 && !fourxfour)
			{
				currentAnswer[getIndex()[0]][getIndex()[1]] = 6;
				repaint();

			}
		}
	}
	private class sevenKey extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(timeLeft != 0 && !fourxfour)
			{
				currentAnswer[getIndex()[0]][getIndex()[1]] = 7;
				repaint();

			}
		}
	}
	private class eightKey extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(timeLeft != 0 && !fourxfour)
			{
				currentAnswer[getIndex()[0]][getIndex()[1]] = 8;
				repaint();

			}
		}
	}
	private class nineKey extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(timeLeft != 0 && !fourxfour)
			{
				currentAnswer[getIndex()[0]][getIndex()[1]] = 9;
				repaint();

			}
		}
	}

	private class enter extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(isRunning)
			{
				if(gameSol.compareAnswer(currentAnswer))
				{
					gameStatus = "Success!";
					gameTimer.timeStop();
					isRunning = false;
				}
				else
				{
					gameStatus = "Try Again!";
					if(gameTimer.timeRemaining() == 0)
					{
						isRunning = false;
					}
				}
			}
		
		}
	}

	private class up extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(sq_y != 13)
			{
				if(sq_y == 113)
				{
					sq_y -= 100;
				}
				else
				{
					sq_y -= 101;
				}
			}
			
		}
	}
	private class down extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(sq_y != 315)
			{
				if(sq_y == 215)
				{
					sq_y += 100;
				}
				else
				{
					sq_y += 101;
				}
			}
			
		}
	}
	private class left extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(sq_x != 20)
			{
				sq_x -= 95;
			}
			
		}
	}
	private class right extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(sq_x != 305)
			{
				sq_x += 95;
			}
					}
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
