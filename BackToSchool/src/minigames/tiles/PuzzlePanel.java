package minigames.tiles;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import javax.swing.*;

import main.BackToSchool;
import main.Player;
import main.Day;

public class PuzzlePanel extends JPanel implements ActionListener
{	
	private Sound backgroundSong;
	private ImageIcon[][] puzzle;
	private ImageIcon one, two, three, four, five, six, seven, eight, nine, ten, 
		eleven, twelve, thirteen, fourteen, fifteen, sixteen, blank;
	private Rectangle oneR, twoR, threeR, fourR, fiveR, sixR, sevenR, eightR, nineR, tenR, 
		elevenR, twelveR, thirteenR, fourteenR, fifteenR, sixteenR;
	private int indexI, indexJ, xPad, yPad, shuffles;
	private static int PWIDTH=550, PHEIGHT=450, TILE=100;
	private boolean finished, gameOver;
	private Player player;
	private Day day;
	private BackToSchool frame;
	
	private Clock gameTimer;
	private Timer timer;
	private int startTime;
	
	private JButton exit;
	private JLabel endLabel;
	
	public PuzzlePanel(Player student, Day current, BackToSchool frame)
	{
		player = student;
		day = current;
		this.frame = frame;
		
		backgroundSong = new Sound("sounds/Background/POL-water-world-short.wav");
		backgroundSong.playSound();
		
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
			startTime = 30;
			indexI = indexJ = 3;
			eleven = blank;
			shuffles = 12;
		}
		else if (dayNum > 3 && dayNum <=6)
		{	
			startTime = 50;
			indexI = 4;
			indexJ = 3;
			twelve = blank;
			shuffles = 15;
		}
		else
		{
			startTime = 90;
			indexI = indexJ = 4;
			sixteen = blank;
			shuffles = 20;
		}
		
		puzzle = new ImageIcon[indexI][indexJ];
		xPad = (PWIDTH-(TILE*indexI))/2;
		yPad = (PHEIGHT-(TILE*indexJ))/2;
		
		Point p = new Point(xPad,yPad);
		oneR = new Rectangle(p.x,p.y,TILE,TILE);
		twoR = new Rectangle(p.x+TILE,p.y,TILE,TILE);
		threeR = new Rectangle(p.x+2*TILE,p.y,TILE,TILE);
		fourR = new Rectangle(-1 , -1, 0, 0);
		
		fiveR = new Rectangle(p.x,p.y+TILE,TILE,TILE);
		sixR = new Rectangle(p.x+TILE,p.y+TILE,TILE,TILE);
		sevenR = new Rectangle(p.x+2*TILE,p.y+TILE,TILE,TILE);
		eightR = new Rectangle(-1, -1, 0, 0);
		
		nineR = new Rectangle(p.x,p.y+2*TILE,TILE,TILE);
		tenR = new Rectangle(p.x+TILE,p.y+2*TILE,TILE,TILE);
		elevenR = new Rectangle(p.x+2*TILE,p.y+2*TILE,TILE,TILE);
		twelveR = new Rectangle(-1, -1, 0, 0);
		
		thirteenR = fourteenR = fifteenR = sixteenR = new Rectangle(-1, -1, 0, 0);

		newPuzzle();
		finished = false;
		
		// set up exit button
		exit = new JButton(new ImageIcon("art/buttons/exit_btn.jpg"));
		//exit.setBounds(PWIDTH-120, PHEIGHT-40, 100,30);
		exit.setBounds(250,210,100,30);
		exit.addActionListener(this);
		exit.setVisible(false);
	    endLabel = new JLabel();
		endLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		endLabel.setVisible(false);

		add(endLabel, BorderLayout.SOUTH);
		add(exit);
		addMouseListener(new PuzzleListener());
		setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
		setBackground(Color.gray);
		requestFocus();
		
		timer = new Timer();
		int fps = 80;
		timer.schedule(new Loop(), 0, 1000/fps);
		gameTimer = new Clock(startTime);
		gameTimer.start();
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
		else if(endTime <= startTime * .4)
		{
			// 50-25
			percentage = 0.3;
		}
		else
		{
			// 25-0
			percentage = 0.2;
		}
		return percentage;
	}
	
	private void newPuzzle()
	{	
		Point p = new Point(xPad,yPad);
		
		puzzle[0][0]=one;
		puzzle[1][0]=two;
		puzzle[2][0]=three;
		puzzle[0][1]=five; 
		puzzle[1][1]=six;
		puzzle[2][1]=seven;
		puzzle[0][2]=nine; 
		puzzle[1][2]=ten; 
		puzzle[2][2]=eleven;
		indexI=indexJ=2;
		Point current = new Point(2,2);
		
		if (day.getDay()>3)
		{	puzzle[3][0]=four;
			puzzle[3][1]=eight;
			puzzle[3][2]=twelve;
			indexI = 3;
			current = new Point(3,2);
			fourR = new Rectangle(p.x+3*TILE, p.y,TILE,TILE);
			eightR = new Rectangle(p.x+3*TILE,p.y+TILE,TILE,TILE);
			twelveR = new Rectangle(p.x+3*TILE,p.y+2*TILE,TILE,TILE);
		}
		
		if (day.getDay()>6) 
		{	puzzle[0][3]=thirteen;
			puzzle[1][3]=fourteen;
			puzzle[2][3]=fifteen; 
			puzzle[3][3]=sixteen;
			indexI = indexJ = 3;
			current = new Point(3,3);
			thirteenR = new Rectangle(p.x,p.y+3*TILE,TILE,TILE);
			fourteenR = new Rectangle(p.x+TILE,p.y+3*TILE,TILE,TILE); 
			fifteenR = new Rectangle(p.x+2*TILE,p.y+3*TILE,TILE,TILE);
			sixteenR = new Rectangle(p.x+3*TILE,p.y+3*TILE,TILE,TILE); 
		}
		
		ArrayList<Point> swaps = new ArrayList<Point>();
		Point next = current, last = null;
		for (int s=0; s < shuffles; s++)
		{
			System.out.println("Current:" + current);
			swaps.clear();
			if (current.x-1 >= 0)
				swaps.add(new Point(current.x-1, current.y));
			if (current.x+1 <= indexI)
				swaps.add(new Point(current.x+1, current.y));
			if (current.y-1 >= 0)
				swaps.add(new Point(current.x, current.y-1));
			if (current.y+1 <= indexJ)
				swaps.add(new Point(current.x, current.y+1));
			
			System.out.println("Options:" + swaps);
			
			if (last != null)
			{	while (next.equals(last) || next.equals(current))
				{	
					int choice = (int)(Math.random()*swaps.size());
					next = swaps.get(choice);
				}
			}
			else
				next = swaps.get((int)(Math.random()*swaps.size()));
			
			System.out.println("Next:" + next);

			ImageIcon tmp = puzzle[current.x][current.y];
			puzzle[current.x][current.y] = puzzle[next.x][next.y];
			puzzle[next.x][next.y] = tmp;
		
			last = current;
			current = next;
		}	
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		Object src = e.getSource();
		backgroundSong.stopSound();
		if(src == exit)
		{		
			if(day.isTranscript())
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
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, PWIDTH, PHEIGHT);
		
		for (int i=0; i<=indexI; i++)
		{
			for (int j=0; j<=indexJ; j++)
			{
				try {
					puzzle[i][j].paintIcon(this, g, (i*TILE)+xPad, (j*TILE)+yPad);
				} catch (Exception e){
					System.out.println("ASdsadsa");
				}
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
			if (gameTimer.timeRemaining() > 0)
			{
				increaseStats(scorePerformance());
				endLabel.setText("Congrats! You earned "+scorePerformance()+ " exp");
				endLabel.setVisible(true);
				exit.setVisible(true);
			}
			else		
			{
				increaseStats(.1);
				endLabel.setText("Out of time... You earned .1 exp");
				endLabel.setVisible(true);
				exit.setVisible(true);
			}
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
			if (!finished && !gameOver)
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
					}
					repaint();
				}	
			}
		}
	}

	public class Loop extends java.util.TimerTask
	{
		public void run()
		{
			repaint();
			
			if(gameTimer.timeRemaining() == 0)
			{
				gameOver = true;
				gameTimer.timeStop();
				timer.cancel();
			}
			
			if (gameOver)
			{
				if (finished)
				{
					increaseStats(scorePerformance());
					endLabel.setText("Congrats! You earned "+scorePerformance()+ " exp");
					endLabel.setVisible(true);
					exit.setVisible(true);
				}
				else		
				{
					increaseStats(.1);
					endLabel.setText("Out of time... You earned .1 exp");
					endLabel.setVisible(true);
					exit.setVisible(true);
				}
				timer.cancel();
			}
		}
	}
	
	public class Sound // Holds one audio file
	{
	  private AudioClip song; // Sound player
	  private URL songPath; // Sound path

	  Sound(String filename){
	     try
	     {
	    	 System.out.println("file:" + System.getProperty("user.dir") + "\\" + filename);
	    	 songPath = new URL ("file:" + System.getProperty("user.dir") + "\\" + filename);
	    	 song = Applet.newAudioClip(songPath);
	    	// playSound();
	     }catch(Exception e){
	         e.printStackTrace();
	         //e.getMessage();
	     } // Satisfy the catch
	  }
	  
	  public void playSound(){
	     song.loop(); // Play 
	  }
	  public void stopSound(){
	     song.stop(); // Stop
	  }
	  public void playSoundOnce(){
	     song.play(); // Play only once
	  }
	}
}
