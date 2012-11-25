
package minigames.beerpong;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.*;

/**
 *
 * @author Cagatay Sahin
 */

public class TablePanel extends JPanel{
    
    
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
    
    ArrayList cupList;
    
    Sprite ball;
    
    Image img;
    ImageIcon originalBallIcon;
    
    public TablePanel( ArrayList _cupList, Sprite _ball ){
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
        
        img = new ImageIcon("art/beerpong/table.png").getImage();
        setLayout(null);
        this.setBackground(Color.RED);
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
        
        g.setColor(Color.BLACK);
        
        
        g.clearRect(0, 0, WIDTH, HEIGHT);
  
        g.drawImage(img, 0, 0, this); 
        
        if( BeerPong.gameState == BeerPong.GS_INIT ) 
        {
            g.drawLine(BeerPong.INIT_BALL_CX, BeerPong.INIT_BALL_CY,
                        BeerPong.INIT_BALL_CX + ball.getSpeedX() / 500, BeerPong.INIT_BALL_CY + ball.getSpeedY() / 500 );
        }     
        
        if( !(BeerPong.gameState == BeerPong.GS_WON || BeerPong.gameState == BeerPong.GS_LOST) )
        {
//            //Draw the ball
//             g.drawOval( ball.getCenterX() - ball.getRadius(), 
//                    ball.getCenterY() - ball.getRadius(), 
//                    ball.getRadius() * 2, ball.getRadius() * 2);
              
             l_ball.setIcon( new ImageIcon( originalBallIcon.getImage().getScaledInstance(ball.getRadius() * 2, 
                                            ball.getRadius() * 2, 
                                            Image.SCALE_SMOOTH) ) );
        }
        
        
        if( ball.getCenterZ() == 0)
        {
            l_ball.setIcon( new ImageIcon("art/beerpong/ballGreen.png"));
            //g.setColor(Color.red);
        }
        
        for( int i = 0; i < cupList.size(); i++)
        {
//            if( !((Sprite) cupList.get(i)).getHit() )
//            g.drawOval(  ((Sprite) cupList.get(i)).getCenterX() -   ((Sprite) cupList.get(i)).getRadius(), 
//                         ((Sprite) cupList.get(i)).getCenterY() -   ((Sprite) cupList.get(i)).getRadius(), 
//                         ((Sprite) cupList.get(i)).getRadius() * 2, ((Sprite) cupList.get(i)).getRadius() * 2);
        }
    }
    
    
    
}//End of class TablePanel
