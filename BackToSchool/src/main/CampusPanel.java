package main;

import javax.imageio.ImageIO;
import javax.swing.*;

import main.BackToSchool.Screen;
import main.Day.Course;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;


public class CampusPanel extends JPanel 
{
	private int playerX, playerY, screenX, screenY;
	private final static int PWIDTH=800, PHEIGHT=600, TILE=50, TILESX=16, TILESY=12, PADX=4, PADY=3, MOVERATE=750;
	private enum Direction{LEFT, UP, RIGHT, DOWN};
	private Image[][] tiles;
	private Timer moveTimer, animate;
	
	// BackToSchool classes
	private BackToSchool frame;
	private Campus campus;
	private Tiles tileFactory;
	private Day day;
	private Player student;
	private Pedestrian pedestrian;
	private Point destination;
	private Image player, playerUp, playerDown, playerLeft, playerRight;
	private Sound testsong;
	
	public CampusPanel()
	{
		//TODO: more elaborate instatiation
		
		campus = new Campus();
		tiles = new Image[TILESX][TILESY];
		tileFactory = new Tiles();
			
		try 
		{	playerUp = ImageIO.read(new File("art/characters/student_backside.png"));
			playerDown = ImageIO.read(new File("art/characters/student.png"));
			playerLeft = ImageIO.read(new File("art/characters/student_leftside.png"));
			playerRight = ImageIO.read(new File("art/characters/student_rightside.png"));	
		} 
		catch(IOException e){};
		
		student = new Player();
		player = playerDown;
		day = new Day(1);
		
		testsong = new Sound("sounds/dw1world.mid");
		
		moveTimer = new Timer(MOVERATE, new PedestrianListener());
		addKeyListener(new CampusListener());
		addMouseListener(new CampusMouseListener());
		setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
		setBackground(Color.RED);
		setFocusable(true);
		requestFocus();
		
		//animate = new Timer(100, new ActionListener() {);
		//animate.start();
	}
	
	protected void sendFrame(BackToSchool frame)
	{
		this.frame = frame;
	}
	
	public void continueClasses() 
	{
		if(day.getDay() == 10)
		{
			frame.addPanel(new FinalBattle(student), Screen.FINALBATTLE);
			frame.switchPanel(Screen.FINALBATTLE);
		}
		
		testsong.playSound();
		ArrayList<Point> doors;
		
		if (day.getNextCourse().ordinal() == 0) // first class (could hardcode as HUMANITIES)
		{
			playerX = playerY = 5;
			screenX = screenY = 0;
			destination = null;
			
			doors = campus.getDoors(true);
			
			for (int i=0; i<TILESX; i++)
			{	for (int j=0; j<TILESY; j++)
				{	Tile tile = campus.getTile(screenX+i, screenY+j);
					if (tile != null)
						tiles[i][j] = tileFactory.get(tile);
				}
			}
		}
		else
			doors = campus.getDoors(false);
		
		//ArrayList<Point> doors = campus.getDoors(true);
		if (destination != null)
			doors.remove(destination);	// remove last used door (no two classes in same place in a row)
		
		destination = doors.get((int)(Math.random()*doors.size()));
		campus.addSign(destination, day.getNextCourse());
		
		// Update any sign tiles currently onscreen
		Rectangle window = new Rectangle(screenX, screenY+1, TILESX, TILESY);
		for (Point p : doors)
			if (window.contains(p)) 
				tiles[p.x-screenX][p.y-1-screenY] = tileFactory.get(campus.getTile(p.x, p.y-1));
		
		//* PEDESTRIAN TEST
		Point point = new Point(-1,-1);
		while (!campus.isTraversable(point.x,point.y))
		{
			int x = (int)(Math.random()*TILESX);
			int y = (int)(Math.random()*TILESY);
			point.setLocation(x,y);
		}
		Point dest = new Point(destination.x, destination.y+1);
		pedestrian = new Pedestrian(point, dest, campus);
		moveTimer.start();
		//*/
	}
	
	private void updateTiles(Direction dir)
	{
		switch (dir) 
		{
			case DOWN:
				shiftDown();
				break;
			case LEFT:
				shiftLeft();
				break;
			case RIGHT:
				shiftRight();
				break;
			case UP:
				shiftUp();
				break;
		}
	}

	private void shiftDown() 
	{
		// switch tiles over
		for (int i=0; i<(TILESX); i++)
			for (int j=0; j<(TILESY-1); j++)
				tiles[i][j] = tiles[i][j+1];
		// adjust screen location (within campus)
		screenY++;
		// load in new tiles from campus
		for (int i=0; i<TILESX; i++)
			tiles[i][TILESY-1] = tileFactory.get(campus.getTile(i+screenX, (TILESY-1)+screenY));
	}

	private void shiftLeft() 
	{
		if (screenX>0)
		{
			for (int i=TILESX-1; i>0; i--)
				for (int j=0; j<(TILESY); j++)
					tiles[i][j] = tiles[i-1][j];
		
			screenX--;
		
			for (int j=0; j<TILESY; j++)
				tiles[0][j] = tileFactory.get(campus.getTile(screenX, j+screenY));
		}
	}
	
	private void shiftUp()
	{
		if (screenY > 0)
		{
			for (int i=0; i<(TILESX); i++)
				for (int j=TILESY-1; j>0; j--)
					tiles[i][j] = tiles[i][j-1];

			screenY--;

			for (int i=0; i<TILESX; i++)
				tiles[i][0] = tileFactory.get(campus.getTile(i+screenX,screenY));
		}
	}
	
	private void shiftRight()
	{
		for (int i=0; i<TILESX-1; i++)
			for (int j=0; j<(TILESY); j++)
				tiles[i][j] = tiles[i+1][j];
		
		screenX++;
		
		for (int j=0; j<TILESY; j++)
			tiles[TILESX-1][j] = tileFactory.get(campus.getTile((TILESX-1)+screenX, j+screenY));
	}
	
	public void paintComponent(Graphics g)
	{
		// render campus
		g.setColor(Color.LIGHT_GRAY);
		for (int i=0; i<TILESX; i++)
			for (int j=0; j<TILESY; j++)
			{	if (tiles[i][j]!=null)
					g.drawImage(tiles[i][j], i*TILE, j*TILE, TILE, TILE, this);//  tiles[i][j].paintIcon(this, g, i*TILE, j*TILE);
				else
					g.fillRect(i*TILE, j*TILE, TILE, TILE);
			}
			
		// render player
		g.drawImage(player, playerX*TILE, playerY*TILE, TILE, TILE, this); //player.paintIcon(this, g, playerX, playerY);
		
		//* render pedestrian (currently even if off screen)
		if (pedestrian.hasMove())
		{	int pedX = pedestrian.getLocation().x-screenX;
			int pedY = pedestrian.getLocation().y-screenY;
			g.drawImage(pedestrian.getImage(), pedX*TILE, pedY*TILE, TILE, TILE, this);
		}
		//*/
		
		// render interface 
		//TODO: draw arrow to class
		
		g.setColor(Color.RED);
		g.drawString("Day: "+day.getDay(), 50, 15);
		g.drawString("Class: "+day.getNextCourseName(), 100, 15);
		g.drawString("Destination: "+destination.x+","+destination.y, 14*50, 15);		
	}
	
	
	public void printStudentStats()
	{
		System.out.println("Stats:");
		System.out.println("Math: " + student.getQuantReasoning());
		System.out.println("Science: " + student.getSciRigor());
		System.out.println("Creativity: " + student.getCreativity());
	}
	
	private class PedestrianListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e) 
		{
			if (pedestrian.hasMove())
				pedestrian.move();
			repaint();
		}	
	}
	
	private class CampusListener extends KeyAdapter
	{	
		public void keyPressed(KeyEvent e)
		{	
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				player = playerUp;
				if ((screenX+playerX==destination.x) && (screenY+playerY-1==destination.y))
				{	// destination door reached
					System.out.println("FOUND CLASS");
					testsong.stopSound();
					if (day.isMidtermNext())
					{	System.out.println("MIDTERM");
						frame.addPanel(new Battle(student, day.getNextCourseName()), BackToSchool.Screen.BATTLE);
						frame.switchPanel(BackToSchool.Screen.BATTLE);
					}
					else
					{	System.out.println("CLASS");
						MiniSplashPane miniSplash = new MiniSplashPane(student, day);
						
						frame.addPanel(miniSplash, BackToSchool.Screen.MINISPLASH);
						frame.switchPanel(BackToSchool.Screen.MINISPLASH);
						
					}
					day.attendClass();
					
					// Transcript will only show at end of week 1 and 2
					if((day.getDay() == 4 || day.getDay() == 6 || day.getDay() == 9) && !day.isTranscriptShow())
					{
						System.out.println("Transcript made");
						Transcript transcript = new Transcript(student, day);
						frame.addPanel(transcript, BackToSchool.Screen.TRANSCRIPT);	
					}
					
					if(day.getDay() == 5)
					{
						day.setTranscriptState(false);
					}
					
					
					printStudentStats();
					
				}
				
				if (campus.isTraversable(screenX+playerX, screenY+playerY-1))
				{	
					int cHeight = campus.getHeight();
					if ((playerY-1>PADY-1 && playerY-1<(TILESY-PADY)) || screenY == 0
							|| ((screenY == cHeight-TILESY) && playerY-1>=(TILESY-PADY)) )
						playerY--;
					else
						updateTiles(Direction.UP);
				}
				repaint();
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{	
				player = playerLeft;
				if (campus.isTraversable(screenX+playerX-1, screenY+playerY))
				{	
					int cWidth = campus.getWidth();
					if ((playerX-1>PADX-1 && playerX-1<(TILESX-PADX)) || screenX == 0
							|| ((screenX == cWidth-TILESX) && playerX-1>=(TILESX-PADX)) )
						playerX--;
					else
						updateTiles(Direction.LEFT);
				}
				repaint();
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{	
				player = playerDown;
				if (campus.isTraversable(screenX+playerX, screenY+playerY+1))
				{	
					int cHeight = campus.getHeight();
					if ((playerY+1>PADY && playerY+1<(TILESY-PADY)) || screenY == (cHeight-TILESY)
							|| ((screenY == 0) && playerY+1<=PADY) )
						playerY++;
					else
						updateTiles(Direction.DOWN);
				}
				repaint();
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{	
				player = playerRight;
				if (campus.isTraversable(screenX+playerX+1, screenY+playerY))
				{	
					int cWidth = campus.getWidth();
					if ((playerX+1>PADX && playerX+1<(TILESX-PADX)) || screenX == (cWidth-TILESX)
							|| ((screenX == 0) && playerX+1<=PADX) )
						playerX++;
					else
						updateTiles(Direction.RIGHT);
				}
				repaint();
			}
			else if(e.getKeyCode() == KeyEvent.VK_B)
			{
				
			}
			else if(e.getKeyCode() == KeyEvent.VK_C)
			{
				
			}
			
			// debug statements
			//System.out.println("Player: "+playerX+","+playerY);
			//System.out.println("Screen: "+screenX+","+screenY);
			
		}
	}
	
	private class CampusMouseListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent e) 
		{
			Point click = e.getPoint();
			int xIndex = click.x/TILE;
			int yIndex = click.y/TILE;
			
			Tile clicked = campus.getTile(xIndex, yIndex);
			//System.out.println("Clicked: " + xIndex + "," + yIndex);
		}
	}
	
	public class Sound // Holds one audio file
	{
	  private AudioClip song; // Sound player
	  private URL songPath; // Sound path

	  Sound(String filename){
	     try
	     {
	    	// System.out.println("file:" + System.getProperty("user.dir") + "\\" + filename);
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