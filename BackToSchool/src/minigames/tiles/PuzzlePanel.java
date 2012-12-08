package minigames.tiles;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.*;
import main.BackToSchool;
import main.Player;
import main.Day;

public class PuzzlePanel extends JPanel implements ActionListener
{
	private ImageIcon[][] puzzle;
	private ImageIcon one, two, three, four, five, six, seven, eight, nine, ten, 
		eleven, twelve, thirteen, fourteen, fifteen, sixteen, blank;
	private Rectangle oneR, twoR, threeR, fourR, fiveR, sixR, sevenR, eightR, nineR, tenR, 
		elevenR, twelveR, thirteenR, fourteenR, fifteenR, sixteenR;
	private int indexI, indexJ, xPad, yPad;
	private static int PWIDTH=550, PHEIGHT=450, TILE=100;
	private boolean finished;
	private Player player;
	private Day day;
	private BackToSchool frame;
	
	private Clock gameTimer;
	private Timer timer;
	
	private int startTime;
	private boolean gameOver;
	
	private JButton exit;
	
	public PuzzlePanel(Player student, Day current, BackToSchool frame)
	{
		player = student;
		day = current;
		this.frame = frame;
		startTime = 0;
		
		// LOAD IN ALL IMAGES
		one = new ImageIcon("art/tilePuzzle/1.png");
		two = new ImageIcon("art/tilePuzzle/2.png");
		three = new ImageIcon("art/tilePuzzle/3.png");
		four = new ImageIcon("art/tilePuzzle/4.png");
		five = new ImageIcon("art/tilePuzzle/5.png");
		six = new ImageIcon("art/tilePuzzle/6.png");
		seven = new ImageIcon("art/tilePuzzle/7.png");
		eight = new ImageIcon("art/tilePuzzle/8.png");
		nine = new ImageIcon("art/tilePuzzle/9.png");
		ten =  new ImageIcon("art/tilePuzzle/10.png");
		eleven =  new ImageIcon("art/tilePuzzle/11.png");
		twelve =  new ImageIcon("art/tilePuzzle/12.png");
		thirteen =  new ImageIcon("art/tilePuzzle/13.png");
		fourteen =  new ImageIcon("art/tilePuzzle/14.png");
		fifteen =  new ImageIcon("art/tilePuzzle/15.png");
		sixteen =  new ImageIcon("art/tilePuzzle/16.png");
		blank = new ImageIcon("art/tilePuzzle/blank.png");
	
		// SET UP BOUNDS BASED ON DIFFICULTY
		int dayNum = day.getDay();
		if (dayNum <= 3)
		{
			startTime = 60;
			indexI = indexJ = 3;
			eleven = blank;
		}
		else if (dayNum > 3 && dayNum <=6)
		{	
			startTime = 75;
			indexI = 4;
			indexJ = 3;
			twelve = blank;
		}
		else
		{
			startTime = 90;
			indexI=indexJ = 4;
			sixteen = blank;
		}
		
		startTime = 5; //debug
		gameTimer = new Clock(startTime);
		
		puzzle = new ImageIcon[indexI][indexJ];
		xPad = (PWIDTH-(TILE*indexI))/2;
		yPad = (PHEIGHT-(TILE*indexJ))/2;
		
		
		Point p = new Point(xPad,yPad);
		oneR = new Rectangle(p.x,p.y,TILE,TILE);
		twoR = new Rectangle(p.x+TILE,p.y,TILE,TILE);
		threeR = new Rectangle(p.x+2*TILE,p.y,TILE,TILE);
		fourR = new Rectangle(p.x+3*TILE, p.y,TILE,TILE);
		
		fiveR = new Rectangle(p.x,p.y+TILE,TILE,TILE);
		sixR = new Rectangle(p.x+TILE,p.y+TILE,TILE,TILE);
		sevenR = new Rectangle(p.x+2*TILE,p.y+TILE,TILE,TILE);
		eightR = new Rectangle(p.x+3*TILE,p.y+TILE,TILE,TILE);
		
		nineR = new Rectangle(p.x,p.y+2*TILE,TILE,TILE);
		tenR = new Rectangle(p.x+TILE,p.y+2*TILE,TILE,TILE);
		elevenR = new Rectangle(p.x+2*TILE,p.y+2*TILE,TILE,TILE);
		twelveR = new Rectangle(p.x+3*TILE,p.y+2*TILE,TILE,TILE);
		
		thirteenR = new Rectangle(p.x,p.y+3*TILE,TILE,TILE);
		fourteenR = new Rectangle(p.x+TILE,p.y+3*TILE,TILE,TILE); 
		fifteenR = new Rectangle(p.x+2*TILE,p.y+3*TILE,TILE,TILE);
		sixteenR = new Rectangle(p.x+3*TILE,p.y+3*TILE,TILE,TILE); 

		newPuzzle();
		finished = false;
		gameTimer.start();
		
		// set up exit button
		setLayout(null);
		exit = new JButton(new ImageIcon("art/buttons/exit_btn.jpg"));
		exit.setBounds(PWIDTH-120, PHEIGHT-40, 100,30);
		this.add(exit);
		exit.addActionListener(this);
		exit.setVisible(false);
		
		addMouseListener(new PuzzleListener());
		setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
		setBackground(Color.gray);
		requestFocus();
		
		gameLoop();
	}
	
	private void increaseStats(double score)
	{	
		switch (day.getCourse())
		{
			case HUMANITIES:
				player.increaseCreativit(score);
				break;
			case MATH:
				player.increaseQuantReasoning(score);
				break;
			default:
				player.increaseSciRigor(score);
				break;
		}	
	}
	
	private double scorePerformance()
	{
		int endTime = gameTimer.timeRemaining();
		double percentage = 0;
		if(endTime >= startTime * .75)
		{
			// 100-75
			percentage = 0.5;
		}
		else if(endTime >= startTime * .50)
		{
			// 75-50
			percentage = 0.4;
		}
		else if(endTime <= startTime * .30)
		{
			// 50-30
			percentage = 0.3;
		}
		else
		{
			// 30-0
			percentage = 0.1;
		}
		
		return percentage;
	}
	
	private void newPuzzle()
	{	
		LinkedBlockingQueue<ImageIcon> queue = new LinkedBlockingQueue<ImageIcon>();
		queue.add(one);
		queue.add(two);
		queue.add(three);
		queue.add(five);
		queue.add(six);
		queue.add(seven);
		queue.add(nine);
		queue.add(ten);
		queue.add(eleven);

		indexI=indexJ=2;
		
		if (day.getDay() > 3)
		{	queue.add(four);		
			queue.add(eight);
			queue.add(twelve);
			indexI = 3;
		}
		if (day.getDay() > 6)
		{
			queue.add(thirteen);		
			queue.add(fourteen);
			queue.add(fifteen);
			queue.add(sixteen);
			indexJ=3;
		}
		
		for (int i=0; i<=indexI; i++)
		{	for (int j=0; j<=indexJ; j++)
			{
				int skip = (int)((Math.random())*10);
				for (int k=skip; k>0; k--)
					if (queue.size()>1)
						queue.add(queue.poll());

				puzzle[i][j] = queue.poll();	
			}
		}
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();

		if(src == exit)
		{
			
			if((day.getDay() == 4 || day.getDay() == 7) && !day.isTranscriptShow())
			{
				frame.switchPanel(BackToSchool.Screen.TRANSCRIPT);
			}
			else
			{
				frame.switchPanel(BackToSchool.Screen.CAMPUS);
			}
		}

	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, PWIDTH, PHEIGHT);
		
		for (int i=0; i<=indexI; i++)
		{
			for (int j=0; j<=indexJ; j++)
			{
				puzzle[i][j].paintIcon(this, g, (i*TILE)+xPad, (j*TILE)+yPad);
			}
		}
		
		g.setColor(Color.RED);
		Font timeStyle = new Font("Arial", Font.BOLD, 30);
		Font gameMessage = new Font("Arial", Font.BOLD, 16);
		
		g.setFont(timeStyle);
		String time = String.valueOf(gameTimer.timeRemaining());
		g.drawString(time, 20, 30);
		
		g.setFont(gameMessage);
		
		if (finished)
		{
			gameTimer.timeStop();
			g.drawString("CONGRATULATIONS", PWIDTH/2 - 80, 20);
			exit.setVisible(true);

		}
		else if (gameTimer.timeRemaining() == 0 && !finished)
		{
			gameTimer.timeStop();
			g.drawString("GAME OVER", PWIDTH/2 - 50, 20);
			exit.setVisible(true);
		}
	}
	
	private boolean isSolved()
	{
		if (puzzle[0][0]==one && puzzle[1][0]==two && puzzle[2][0]==three &&
			puzzle[0][1]==five && puzzle[1][1]==six && puzzle[2][1]==seven &&
			puzzle[0][2]==nine && puzzle[1][2]==ten && puzzle[2][2]==eleven)
		{	if (day.getDay() <= 3)
				return true;
			else if ((day.getDay()>3 && day.getDay()<=6) && (puzzle[3][0]==four && puzzle[3][1]==eight && puzzle[3][2] == twelve))
				return true;
			else if ((day.getDay()>6) && puzzle[0][3]==thirteen && puzzle[1][3]==fourteen && puzzle[2][3]==fifteen && puzzle[3][3]==sixteen)
				return true;
		}
		return false;
	}
	
	private class PuzzleListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			if (!finished)
			{
				int x = e.getX();
				int y = e.getY();
				
				boolean tileClicked = true;
				int xIndex = 0, yIndex = 0;
				
				if (oneR.contains(x,y))
				{	xIndex = 0;
					yIndex = 0;
				}
				else if (twoR.contains(x,y))
				{	xIndex = 1;
				 	yIndex = 0;
				}
				else if (threeR.contains(x,y))
				{	xIndex = 2;
			 		yIndex = 0;
				}
				else if (fourR.contains(x,y))
				{	xIndex = 3;
		 			yIndex = 0;
				}
				else if (fiveR.contains(x,y))
				{	xIndex = 0;
	 				yIndex = 1;
				}
				else if (sixR.contains(x,y))
				{	xIndex = 1;
	 				yIndex = 1;
				}
				else if (sevenR.contains(x,y))
				{	xIndex = 2;
	 				yIndex = 1;
				}
				else if (eightR.contains(x,y))
				{	xIndex = 3;
	 				yIndex = 1;
				}
				else if (nineR.contains(x,y))
				{	xIndex = 0;
	 				yIndex = 2;
				}
				else if (tenR.contains(x,y))
				{	xIndex = 1;
	 				yIndex = 2;
				}
				else if (elevenR.contains(x,y))
				{	xIndex = 2;
	 				yIndex = 2;
				}
				else if (twelveR.contains(x,y))
				{	xIndex = 3;
	 				yIndex = 2;
				}
				else if (thirteenR.contains(x,y))
				{	xIndex = 0;
	 				yIndex = 3;
				}
				else if (fourteenR.contains(x,y))
				{	xIndex = 1;
	 				yIndex = 3;
				}
				else if (fifteenR.contains(x,y))
				{	xIndex = 2;
	 				yIndex = 3;
				}
				else if (sixteenR.contains(x,y))
				{	xIndex = 3;
	 				yIndex = 3;
				}
				else
					tileClicked = false;
				
				if (tileClicked)
				{				
					ImageIcon tmp;
					if (xIndex>0 && puzzle[xIndex-1][yIndex] == blank)
					{	tmp = puzzle[xIndex-1][yIndex];
						puzzle[xIndex-1][yIndex] = puzzle[xIndex][yIndex];
						puzzle[xIndex][yIndex] = tmp;
					}
					else if (xIndex<indexI && puzzle[xIndex+1][yIndex] == blank)
					{	tmp = puzzle[xIndex+1][yIndex];
						puzzle[xIndex+1][yIndex] = puzzle[xIndex][yIndex];
						puzzle[xIndex][yIndex] = tmp;
					}
					else if (yIndex>0 && puzzle[xIndex][yIndex-1] == blank)
					{	tmp = puzzle[xIndex][yIndex-1];
						puzzle[xIndex][yIndex-1] = puzzle[xIndex][yIndex];
						puzzle[xIndex][yIndex] = tmp;	
					}
					else if (yIndex<indexJ && puzzle[xIndex][yIndex+1] == blank)
					{	tmp = puzzle[xIndex][yIndex+1];
						puzzle[xIndex][yIndex+1] = puzzle[xIndex][yIndex];
						puzzle[xIndex][yIndex] = tmp;	
					}
					
					if (isSolved())
					{
						finished = true;
						gameTimer.timeStop();
						gameOver = true;
					}
					repaint();
				}	
			}
			else // isFinished
			{
				
				//newPuzzle();
				gameOver = true;
				gameTimer.timeStop();
				increaseStats(scorePerformance());
				
			
				
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

			if(gameOver)
			{
				timer.cancel();
			}
		}
	}
}
