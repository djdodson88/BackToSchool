package minigames.sudoku;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

public class SudokuGame extends JPanel implements KeyListener {

	private BufferedImage fourByfour_grid;
	private BufferedImage colorSq;


	// Game Related
	private int[][] currentAnswer;
	private int[][] setup;
	private SudokuSol gameSol;
	private String gameStatus; //in progress, right, wrong

	// Game Booleans
	private boolean isRunning;
	private boolean right, left, up, down; // key input
	private boolean fourxfour;


	private Timer timer;
	private Clock gameTimer;
	private long timeLeft;

	// Coordinates
	private int sq_x = 20;
	private int sq_y = 13;

	public SudokuGame(int day)
	{
		setup();
		gameSol = new SudokuSol(day);
		gameStatus = "In Progress"; // Default until <Enter> Pressed

		setup = gameSol.gridToShow();
		currentAnswer = new int[4][4];


		addKeyListener(this);
		setBackground(new Color(58,54,55));
		setFocusable(true);
		isRunning = true;
		gameTimer = new Clock(40);
		gameTimer.start();
		gameLoop();

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

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

	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = true;
		}

	}

	public void addAnswer(KeyEvent e)
	{
		if(sq_x == 20)
		{
			if(sq_y == 13)
			{
				// [0][0]


				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[0][0] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[0][0] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[0][0] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[0][0] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[0][0] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}


			}
			else if(sq_y == 114)
			{
				// [1][0]

				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[1][0] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[1][0] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[1][0] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[1][0] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[1][0] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}

			}
			else if(sq_y == 215)
			{
				// [2][0]

				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[2][0] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[2][0] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[2][0] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[2][0] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[2][0] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}

			}
			else if(sq_y == 315)
			{
				// [3][0]

				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[3][0] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[3][0] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[3][0] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[3][0] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[3][0] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}

		}
		else if(sq_x == 115)
		{
			if(sq_y == 13)
			{
				// [0][1]

				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[0][1] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[0][1] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[0][1] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[0][1] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[0][1] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}

			}
			else if(sq_y == 114)
			{
				// [1][1]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[1][1] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[1][1] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[1][1] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[1][1] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[1][1] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
			else if(sq_y == 215)
			{
				// [2][1]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[2][1] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[2][1] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[2][1] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[2][1] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[2][1] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
			else if(sq_y == 315)
			{
				// [3][1]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[3][1] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[3][1] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[3][1] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[3][1] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[3][1] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
		}
		else if(sq_x == 210)
		{
			if(sq_y == 13)
			{
				// [0][2]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[0][2] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[0][2] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[0][2] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[0][2] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[0][2] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
			else if(sq_y == 114)
			{
				// [1][2]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[1][2] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[1][2] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[1][2] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[1][2] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[1][2] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
			else if(sq_y == 215)
			{
				// [2][2]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[2][2] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[2][2] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[2][2] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[2][2] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[2][2] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
			else if(sq_y == 315)
			{
				// [3][2]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[3][2] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[3][2] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[3][2] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[3][2] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[3][2] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
		}
		else if(sq_x == 305)
		{
			if(sq_y == 13)
			{
				// [0][3]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[0][3] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[0][3] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[0][3] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[0][3] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[0][3] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
			else if(sq_y == 114)
			{
				// [1][3]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[1][3] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[1][3] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[1][3] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[1][3] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[1][3] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
			else if(sq_y == 215)
			{
				// [2][3]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[2][3] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[2][3] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[3][3] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[2][3] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[2][3] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
			else if(sq_y == 315)
			{
				// [3][3]
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					currentAnswer[3][3] = 0;
				}
				else if(e.getKeyCode() == KeyEvent.VK_1)
				{
					currentAnswer[3][3] = 1;

				}
				else if(e.getKeyCode() == KeyEvent.VK_2)
				{
					currentAnswer[3][3] = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3)
				{
					currentAnswer[3][3] = 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_4)
				{
					currentAnswer[3][3] = 4;
				}

				if(!fourxfour)
				{
					if(e.getKeyCode() == KeyEvent.VK_5)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_6)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_7)
					{

					}
					else if(e.getKeyCode() == KeyEvent.VK_8)
					{

					}
				}
			}
		}

	}


	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
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
			return;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(left)
			{

				if(sq_x != 20)
				{
					sq_x -= 95;
				}
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(right)
			{
				if(sq_x != 305)
				{
					sq_x += 95;
				}
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if(down)
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
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			if(up)
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
		else
		{
			addAnswer(e);
			repaint();
			
		}

		setFalse();

	}



	public void keyTyped(KeyEvent e) {



	}

	private void setFalse()
	{
		right = false;
		left = false;
		up = false;
		down = false;
	}
}
