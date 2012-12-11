
package minigames.beerpong;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.*;

import main.Day;
import minigames.sudoku.Clock;

/**
 *
 * @author Cagatay Sahin
 */

public class TopViewPanel extends JPanel{
    
    
    JLabel l_ball;
    JLabel cup1;
    JLabel cup2;
    JLabel cup3;
    JLabel cup4;
    JLabel cup5;
    JLabel cup6;
    ArrayList<JLabel> labelList;
    final static int WIDTH = 550;
    final static int HEIGHT = 275;
    Clock gameTimer;
    int initialTime;
    int timeLeft;
    
    ArrayList cupList;
    
    Sprite ball;
    
    Image img;
    ImageIcon originalBallIcon;
    
    public TopViewPanel( ArrayList _cupList, Sprite _ball , Day day){
        labelList = new ArrayList<JLabel>();
                
        originalBallIcon = new ImageIcon("art/beerpong/ball.png"); 
        l_ball = new JLabel(originalBallIcon);
        
        cup1 = new JLabel(new ImageIcon("art/beerpong/cup.png"));
        cup2 = new JLabel(new ImageIcon("art/beerpong/cup.png"));
        cup3 = new JLabel(new ImageIcon("art/beerpong/cup.png"));
        cup4 = new JLabel(new ImageIcon("art/beerpong/cup.png"));
        cup5 = new JLabel(new ImageIcon("art/beerpong/cup.png"));
        cup6 = new JLabel(new ImageIcon("art/beerpong/cup.png"));
        
        labelList.add(cup1);
        labelList.add(cup2);
        labelList.add(cup3);
        labelList.add(cup4);
        labelList.add(cup5);
        labelList.add(cup6);
        
        add(l_ball);
        add(cup1);
        add(cup2);
        add(cup3);
        add(cup4);
        add(cup5);
        add(cup6);
        
        cupList = _cupList;
        ball = _ball;
        
        if(day.getDay() <= 6)
		{
			if(day.getDay() >=3)
			{
				initialTime = 20;
				gameTimer = new Clock(initialTime);
			}
			else
			{
				initialTime = 40;
				gameTimer = new Clock(initialTime);
			}
		}
		else
		{
			initialTime = 3;
			gameTimer = new Clock(initialTime); //testing
		}
		gameTimer.start();
        
        img = new ImageIcon("art/beerpong/table.png").getImage();
        setLayout(null);
      
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    //Sets all labels visible
    public void setCupsVisible()
    {
        for(int i = 0; i < labelList.size(); i++)
            labelList.get(i).setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	//super.paintComponent(g);
    	timeLeft = gameTimer.timeRemaining();
		Font font = new Font("Arial", Font.BOLD, 50);
		Font markup = new Font("Arial", Font.PLAIN, 50);
		Font gameTxt = new Font("Arial", Font.PLAIN, 18);

		//System.out.println(timeLeft);
		
		g.setColor(new Color(255,254,215));
		g.fillRect(415, 60, 120, 300);


		g.setFont(gameTxt);
		g.setColor(Color.BLACK);
		g.drawString("Timer", 420, 80);

		if(timeLeft == 0)
		{
//			isRunning = false;
//			gameStatus = "Game Over";
			
//			if(!statsUpdated)
//			{
//				increaseStats(0); // Lose
//			}
//			exit.setVisible(true);

		}
		g.drawString(String.valueOf(timeLeft), 420, 0);
    	
        g.setColor(Color.BLACK);
        
        g.clearRect(0, 0, WIDTH, HEIGHT);
  
        g.drawImage(img, 0, 0, this); 
        
        if( BeerPong.gameState == BeerPong.GS_INIT ) 
        {
            g.drawLine(BeerPong.INIT_BALL_CX, BeerPong.INIT_BALL_CY,
                        BeerPong.INIT_BALL_CX + ball.getSpeedX() / 700, BeerPong.INIT_BALL_CY + ball.getSpeedY() / 700 );
        }     
        
        if( !(BeerPong.gameState == BeerPong.GS_WON || BeerPong.gameState == BeerPong.GS_LOST) )
        {
            //Draw the ball
              
             l_ball.setBounds( ball.getCenterX() - ball.getRadius(), 
                     ball.getCenterY() - ball.getRadius(), 
                     ball.getRadius() * 2, ball.getRadius() * 2);
             
             l_ball.setIcon( new ImageIcon( originalBallIcon.getImage().getScaledInstance(ball.getRadius() * 2, 
                                            ball.getRadius() * 2, 
                                            Image.SCALE_SMOOTH) ) );
             
        }
        
    }
    
    
    
}//End of class TablePanel
