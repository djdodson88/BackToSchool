package minigames.beerpong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideViewPanel extends JPanel {
	
	Sprite ball;
	Image img;
	JLabel l_ball;
	ImageIcon originalBallIcon;
	
	public SideViewPanel(Sprite _ball){
		ball = _ball;
		originalBallIcon = new ImageIcon("art/beerpong/ball.png"); 
        l_ball = new JLabel(originalBallIcon);
        
		img = new ImageIcon("art/beerpong/a.jpg").getImage();
		setPreferredSize( new Dimension(TopViewPanel.WIDTH, 125) );
		setBackground( new Color(222,215,173));
		add(l_ball);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this); 
		if( BeerPong.gameState == BeerPong.GS_INIT ) 
        {
	    	g.drawLine(BeerPong.INIT_BALL_CX, 125 - ball.getRealRadius() - ball.getCenterZ(),
	    			BeerPong.INIT_BALL_CX + ball.getSpeedX() / 700, 125 - ball.getCenterZ() - ball.getRealRadius() - ball.getSpeedZ() / 500 );
        }
		
        if( !(BeerPong.gameState == BeerPong.GS_WON || BeerPong.gameState == BeerPong.GS_LOST) )
        {
            //Draw the ball
              
             l_ball.setBounds(ball.getCenterX() - ball.getRealRadius(), 
          		 	125 - ball.getRealRadius() * 2 - ball.getCenterZ(), 
          		 	 ball.getRealRadius() * 2, ball.getRealRadius() * 2  );
        }
        
        
	}
	
}
