
package minigames.beerpong;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

import main.BackToSchool;
import main.Day;
import main.Player;
import minigames.sudoku.Clock;
import minigames.sudoku.SudokuGame.Sound;
/**
 *
 * @author Cagatay Sahin
 * 
 * Use arrow keys to set the x-y velocities of the ball
 * Use ctrl+arrow keys to the x-z velocities of the ball
 * Use shift+arrow keys to the z coordinate of the ball
 * Then go SPACE!
 */

public class BeerPong extends JPanel{
    
	private Sound backgroundSong;
    final static int  GS_INIT = 1;
    final static int  GS_MOTION = 2;
    final static int  GS_HIT = 3;
    final static int  GS_MISS = 4;
    final static int  GS_WON = 5;
    final static int  GS_LOST = 6;
    final static int INIT_BALL_RADIUS = 10;
    final static int INIT_BALL_CX = INIT_BALL_RADIUS * 2;
    final static int INIT_BALL_CY = TopViewPanel.HEIGHT / 2;
    final static int INIT_BALL_CZ = 0;
    
    static int gameState;
    static int missCount;
    static int hitCount;
    
    int ball_prev_z_speed;
    int ball_prev_z_coordinate;
    int level;
    
    JButton exit;
    
    JPanel p_buttons;
    TopViewPanel p_top_view;
    SideViewPanel p_side_view;
    BeerPanel p_beer;
    
    ArrayList<Cup> cupList;
    Sprite ball;
    
    private Player student;
    private BackToSchool frame;
    private Day day;
    
    Timer t;
    public BeerPong(Player player, Day current, BackToSchool frame){
		backgroundSong = new Sound("sounds/Background/POL-stable-boy-short.wav");
		//backgroundSong.playSound();
		
    	this.frame = frame;
		student = player;
		day = current;
        ball = new Sprite();
        
        cupList = new ArrayList<Cup>();
       
        cupList.add( new Cup(465, TopViewPanel.HEIGHT / 2 - 20, 0 ) );
        cupList.add( new Cup(500, TopViewPanel.HEIGHT / 2 - 40, 0 ) );
        cupList.add( new Cup(500, TopViewPanel.HEIGHT / 2, 0 ) );
        cupList.add( new Cup(500, TopViewPanel.HEIGHT / 2 + 40, 0) );
        cupList.add( new Cup(465, TopViewPanel.HEIGHT / 2 + 20, 0) );
        cupList.add( new Cup(430, TopViewPanel.HEIGHT / 2, 0 ) );
               
        p_buttons = new JPanel();
        p_beer = new BeerPanel();
        p_top_view = new TopViewPanel( cupList, ball, day);
        p_side_view = new SideViewPanel( ball);        		
        
        setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
        
        p_buttons.setPreferredSize(new Dimension(TopViewPanel.WIDTH, 125));
        p_buttons.setBackground(Color.GREEN);       
        
        add(p_beer);
        add(p_top_view);
        add(p_side_view);
        
        // Adding KeyBindings
        InputMap myInputMap = new InputMap();
		ActionMap myActionMap = new ActionMap();
		
		up up = new up();
		down down = new down();
		left left = new left();
		right right = new right();
		ctrl_up ctrl_up = new ctrl_up();
		ctrl_down ctrl_down = new ctrl_down();
		ctrl_left ctrl_left = new ctrl_left();
		ctrl_right ctrl_right = new ctrl_right();
		shift_up shift_up = new shift_up();
		shift_down shift_down = new shift_down();
		R r = new R();
		Space space = new Space();
		
		myInputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_MASK), "ctrl_up");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_MASK), "ctrl_down");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.CTRL_MASK), "ctrl_left");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.CTRL_MASK), "ctrl_right");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.SHIFT_MASK), "shift_up");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.SHIFT_MASK), "shift_down");	
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, 0, false), "k");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, false), "r");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0, false), "x");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, false), "z");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "space");
		
		myActionMap = this.getActionMap();
		myActionMap.put("up", up);
		myActionMap.put("down", down);
		myActionMap.put("right", right);
		myActionMap.put("left", left);
		myActionMap.put("r", r);
		myActionMap.put("ctrl_up", ctrl_up);
		myActionMap.put("ctrl_down", ctrl_down);
		myActionMap.put("ctrl_left", ctrl_left);
		myActionMap.put("ctrl_right", ctrl_right);
		myActionMap.put("shift_up", shift_up);
		myActionMap.put("shift_down", shift_down);
		myActionMap.put("space", space);

        setPreferredSize(new Dimension(550, 450));
       // KeyListener listener = new MyKeyListener();    
       // this.addKeyListener(listener );
        setFocusable(true);

        int delay = 10; //milliseconds
        t = new Timer(delay, onEachFrame);
        t.start();
        resetGame();
    }
    
    //Reset the game
    public void resetGame(){           
        p_top_view.setCupsVisible();
        gameState = GS_INIT;
        missCount = 0;
        hitCount = 0;
        p_beer.p_scala.setVisibleAll(false);
        p_top_view.repaint();
        p_top_view.revalidate();
        
        p_side_view.repaint();
        p_side_view.revalidate();
        
        resetBall();
        resetCups();
    }
    
    
    //Reset all the ball attributes to the default
    private void resetBall(){
    	ball_prev_z_speed = 10000;
    	ball_prev_z_coordinate = INIT_BALL_CZ;
    	ball.setRadius(10);
    	ball.setSpeedX( 50000 );
        ball.setSpeedY( 0 );
        ball.setSpeedZ( 25000 );
        ball.setAccelerationZ(-250);
        initBall();
    }
    
    //Set the ball to the position where 
    //it has just before been thrown
    //(Keeps the speed in all directions and 
    // Keeps the z coordinate)
    
    private void initBall(){
        ball.setSpeedZ( ball_prev_z_speed );
    	ball.setCenterX(INIT_BALL_CX);
        ball.setCenterY(INIT_BALL_CY);
        ball.setCenterZ(ball_prev_z_coordinate);
        
        //Set the ball status as 
        //within the boundaries of the table
        ball.setOut(false);
    }
    
    private void resetCups(){
       
        (cupList.get(0)).reset();
        (cupList.get(1)).reset();
        (cupList.get(2)).reset();
        (cupList.get(3)).reset();
        (cupList.get(4)).reset();
        (cupList.get(5)).reset();
        
        (cupList.get(0)).setSpeedY(-10000);
        (cupList.get(1)).setSpeedY(-10000);
        (cupList.get(2)).setSpeedX(-10000);
        (cupList.get(3)).setSpeedY(10000);
        (cupList.get(4)).setSpeedY(10000);
        (cupList.get(5)).setSpeedX(-10000);
        
   }
    
    
    
    ActionListener onEachFrame = new ActionListener() 
    {
        public void actionPerformed(ActionEvent evt) 
        {
        	danceCups();
        	
            if( gameState == GS_INIT)
            {
               
            }
            
            if( gameState == GS_MOTION)
            {
                ball.move();
                
                
               //Check any foul (if the ball bounces before the line)
               if(ball.getCenterZ() <= 0 && ball.getCenterX() < TopViewPanel.WIDTH / 2)
        	   {
            	   missCount++;
            	   gameState = GS_MISS;
        	   }
               
               else
               {	
            	   //Check any hit 
                   for( int i = 0; i < cupList.size() ; i++)
                   {
                       if( (ball.getCenterZ() <= 0) 
                        && !((Cup) cupList.get(i)).getHit()  
                        && ((Cup) cupList.get(i)).getDistance(ball) < ((Cup) cupList.get(i)).getRadius() * 1.5 )
                       {
                           ((Cup) cupList.get(i)).setHit(true);
                           hitCount++;
                           gameState = BeerPong.GS_HIT;
                           break;
                       }
                   }
                   
                   //Check if ball left the table boundaries
                   if( ball.getOut())
                   {   
                       missCount++;
                       gameState = GS_MISS;
                       ball.setOut(false);
                   }
               }                            
            
            }
            
            //if the ball hits any cup 
            if( gameState == GS_HIT)
            {               
            	//if the ball hits all the cups
                if( hitCount == 6 )
                {
                   gameState = GS_WON;
                }
                else
                {
                    initBall();
                    gameState = GS_INIT;
                }
            }
            
            if( gameState == GS_MISS)
            {
                p_beer.p_scala.setVisibleCount(missCount);
                p_beer.repaint();
                p_beer.revalidate();
                
                if(missCount == 10)
                {
                    gameState = GS_LOST;
                }
                else
                {
                    initBall();
                    gameState = GS_INIT;
                }
            }
            
            if( gameState == GS_WON)
            {
            	t.stop();
            	increaseStats();
              // 	System.out.println("WIN");
            }
            
            if( gameState == GS_LOST)
            {
            	t.stop();
            	increaseStats();
               // System.out.println("LOST");
            }
            
            
            for( int i = 0; i < cupList.size(); i++)
            {
                if( !((Cup) cupList.get(i)).getHit() )
                (p_top_view.labelList.get(i)).setBounds(  ((Cup) cupList.get(i)).getCenterX() -   ((Cup) cupList.get(i)).getRadius(), 
                            ((Cup) cupList.get(i)).getCenterY() -   ((Cup) cupList.get(i)).getRadius(), 
                            ((Cup) cupList.get(i)).getRadius() * 2, ((Cup) cupList.get(i)).getRadius() * 2);
                else
                (p_top_view.labelList.get(i)).setVisible(false);    
            }
            
            p_top_view.repaint();
            p_top_view.revalidate();
            
            p_side_view.repaint();
            p_side_view.revalidate();
        }
    };
    
    //Moves the cups as the player gets more drunk
    private void danceCups(){
    	
    	(cupList.get(0)).move();
    	(cupList.get(1)).move();
    	(cupList.get(2)).move();
    	(cupList.get(3)).move();
    	(cupList.get(4)).move();
    	(cupList.get(5)).move();
    	
        if( (cupList.get(0)).getInitCenterY() - (cupList.get(0)).getCenterY() >= 50 )
    	{
        	(cupList.get(0)).setSpeedY(10000);
            (cupList.get(1)).setSpeedY(10000);
            (cupList.get(2)).setSpeedX(10000);
            (cupList.get(3)).setSpeedY(-10000);
            (cupList.get(4)).setSpeedY(-10000);
            (cupList.get(5)).setSpeedX(+10000);
        	
    	}
        
        if( (cupList.get(0)).getInitCenterY() - (cupList.get(0)).getCenterY() <= 0 )
        {
        	(cupList.get(0)).setSpeedY(-10000);
            (cupList.get(1)).setSpeedY(-10000);
            (cupList.get(2)).setSpeedX(-10000);
            (cupList.get(3)).setSpeedY(10000);
            (cupList.get(4)).setSpeedY(10000);
            (cupList.get(5)).setSpeedX(-10000);        	
        }
        
    }
    
    public void increaseStats(){
    	double stats=0;
    	
    	if(gameState == GS_WON)
    		stats=0.5;
    	else if(gameState == GS_LOST)
    		stats=0.1;
    	
    	switch(day.getCourse())
		{
		case SCIENCE: //day 3 resets to nextDay to 1, so 1-1 = 0
			student.increaseSciRigor(stats);
			break;
		case HUMANITIES:
			student.increaseCreativit(stats);
			break;
		default:
			student.increaseQuantReasoning(stats);
			break;
		}
    	backgroundSong.stopSound();
    	
    	if(day.isTranscript())
		{
			frame.switchPanel(BackToSchool.Screen.TRANSCRIPT);
		}
		else
		{
			frame.switchPanel(BackToSchool.Screen.CAMPUS);
		}
    }
    public static void main(String[] args){
        
    	CardLayout layout = new CardLayout();
		JPanel cards = new JPanel(layout);
		BackToSchool b2sFrame = new BackToSchool(layout, cards);     
        BeerPong b = new BeerPong(new Player(), new Day(1), b2sFrame);
        
        JFrame f = new JFrame();
        f.setLocation(WIDTH / 4, HEIGHT / 4);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        f.add(b);
        f.pack();
    }
    
    private class up extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{	
			if(gameState == GS_INIT){
				ball.setSpeedY( ball.getSpeedY() - 1000 );
			}			
		}
	}
    
    private class down extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(gameState == GS_INIT){
				ball.setSpeedY( ball.getSpeedY() + 1000 );
			}
		}	
    }
    
    private class right extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(gameState == GS_INIT){
				ball.setSpeedX( ball.getSpeedX() + 1000 );
			}			
		}		
    }
    
    private class left extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(gameState == GS_INIT){
				 ball.setSpeedX( ball.getSpeedX() - 1000 );
			}
		}
    }
    
    private class ctrl_up extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(gameState == GS_INIT){
				ball.setSpeedZ( ball.getSpeedZ() + 500 );
			}
		}
    }
    
    private class ctrl_down extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(gameState == GS_INIT){
				ball.setSpeedZ( ball.getSpeedZ() - 500 );
			}
		}
    }
    
    private class ctrl_left extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(gameState == GS_INIT){
				 ball.setSpeedX( ball.getSpeedX() - 1000 );
			}
		}
    }
    
    private class ctrl_right extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(gameState == GS_INIT){
				 ball.setSpeedX( ball.getSpeedX() + 1000 );
			}
		}
    }
    
    private class shift_up extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(gameState == GS_INIT){
				ball.setCenterZ(ball.getCenterZ() + 5);
			}
		}
    }
    
    private class shift_down extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(gameState == GS_INIT){
				ball.setCenterZ(ball.getCenterZ() - 5);
			}
		}
    }
    
    private class R extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			resetGame();
		}
    }
    
  
    private class Space extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(gameState == GS_INIT)
			{
				ball_prev_z_speed = ball.getSpeedZ();
				ball_prev_z_coordinate = ball.getCenterZ();
				if( ball.getSpeedX() != 0 || ball.getSpeedY() != 0 )
                    gameState = GS_MOTION;
			}
			
			else if( gameState == GS_HIT )
			{
				initBall();
				gameState = GS_INIT;				
			}

			else if( gameState == GS_MISS )
			{
				initBall();
				gameState = GS_INIT;
				missCount++;
				
				if(missCount == 10)
					gameState = GS_LOST;

				repaint();
				revalidate();
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
	    	 //System.out.println("file:" + System.getProperty("user.dir") + "\\" + filename);
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
