
package minigames.beerpong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Cagatay Sahin
 * 
 * Use arrow keys to set the x-y direction of the ball
 * Use "x-z" keys to set the z direction of the ball
 */

public class BeerPong extends JPanel{
    
    final static int  GS_INIT = 1;
    final static int  GS_MOTION = 2;
    final static int  GS_HIT = 3;
    final static int  GS_MISS = 4;
    final static int  GS_WON = 5;
    final static int  GS_LOST = 6;
    final static int INIT_BALL_RADIUS = 10;
    final static int INIT_BALL_CX = INIT_BALL_RADIUS * 2;
    final static int INIT_BALL_CY = TablePanel.HEIGHT / 2;
    final static int INIT_BALL_CZ = 0;
    
    static int gameState;
    static int missCount;
    static int hitCount;
    
    JPanel p_buttons;
    TablePanel p_table;
    BeerPanel p_beer;
    
    ArrayList<Sprite> cupList;
    Sprite ball;
    
    Timer t;
    public BeerPong(){
        
        ball = new Sprite();
        
        cupList = new ArrayList<Sprite>();
        cupList.add( new Sprite() );
        cupList.add( new Sprite() );
        cupList.add( new Sprite() );
        cupList.add( new Sprite() );
        cupList.add( new Sprite() );
        cupList.add( new Sprite() );
        System.out.println(cupList.size());
        
        p_buttons = new JPanel();
        p_beer = new BeerPanel();
        p_table = new TablePanel( cupList, ball);
        
        setLayout( new FlowLayout());
        
        p_buttons.setPreferredSize(new Dimension(TablePanel.WIDTH, 125));
        p_buttons.setBackground(Color.WHITE);
       
        
        add(p_beer);
        add(p_table);
        add(p_buttons);
        
        setPreferredSize(new Dimension(550, 450));
        KeyListener listener = new MyKeyListener();    
        this.addKeyListener(listener );
        setFocusable(true);
        
        int delay = 10; //milliseconds
        t = new Timer(delay, onEachFrame);
        t.start();
        resetGame();
    }
    
    //Reset the game
    public void resetGame(){           
        p_table.setCupsVisible();
        gameState = GS_INIT;
        initBall();
        initCups();
        missCount = 0;
        hitCount = 0;
        p_beer.p_scala.setVisibleAll(false);
        p_table.repaint();
        p_table.revalidate();
    }
    
    private void initBall(){
        System.out.println("INIt BALL");
        ball.setRadius(10);
        ball.setCenterX(INIT_BALL_CX);
        ball.setCenterY(INIT_BALL_CY);
        ball.setCenterZ(INIT_BALL_CZ);
        ball.setSpeedX( 0 );
        ball.setSpeedY( 0 );
        ball.setSpeedZ( 0 );
        ball.setAccelerationZ(-100);
        ball.setOut(false);
    }
    
    private void initCups(){
       
        (cupList.get(0)).setRadius(20);
        (cupList.get(0)).setCenterX(465);
        (cupList.get(0)).setCenterY(TablePanel.HEIGHT / 2 - 20);
        (cupList.get(0)).setCenterZ(0);
        (cupList.get(0)).setHit(false);
        
        (cupList.get(1)).setRadius(20);
        (cupList.get(1)).setCenterX(500);
        (cupList.get(1)).setCenterY(TablePanel.HEIGHT / 2 - 40);
        (cupList.get(1)).setCenterZ(0);
        (cupList.get(1)).setHit(false);
        
        (cupList.get(2)).setRadius(20);
        (cupList.get(2)).setCenterX(500);
        (cupList.get(2)).setCenterY(TablePanel.HEIGHT / 2 );
        (cupList.get(2)).setCenterZ(0);
        (cupList.get(2)).setHit(false);
        
        (cupList.get(3)).setRadius(20);
        (cupList.get(3)).setCenterX(500);
        (cupList.get(3)).setCenterY(TablePanel.HEIGHT / 2 + 40);
        (cupList.get(3)).setCenterZ(0);
        (cupList.get(3)).setHit(false);
        
        (cupList.get(4)).setRadius(20);
        (cupList.get(4)).setCenterX(465);
        (cupList.get(4)).setCenterY(TablePanel.HEIGHT / 2 + 20);
        (cupList.get(4)).setCenterZ(0);
        (cupList.get(4)).setHit(false);
        
        (cupList.get(5)).setRadius(20);
        (cupList.get(5)).setCenterX(430);
        (cupList.get(5)).setCenterY(TablePanel.HEIGHT / 2 );
        (cupList.get(5)).setCenterZ(0);
        (cupList.get(5)).setHit(false);
    }
    
    ActionListener onEachFrame = new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent evt) 
        {
            if( gameState == GS_INIT)
            {
               
            }
            if( gameState == GS_MOTION)
            {
                ball.move();
               
               //Check any hit 
                for( int i = 0; i < cupList.size() ; i++)
                {
                    if( (ball.getCenterZ() == 0) 
                     && !((Sprite) cupList.get(i)).getHit()  
                     && ((Sprite) cupList.get(i)).getDistance(ball) < ((Sprite) cupList.get(i)).getRadius() )
                    {
                        ((Sprite) cupList.get(i)).setHit(true);
                        hitCount++;
                        gameState = BeerPong.GS_HIT;
                        break;
                    }
                }
                
                if( ball.getOut())
                {   
                    missCount++;
                    gameState = GS_MISS;
                    ball.setOut(false);
                }            }
            
            if( gameState == GS_HIT)
            {               
                System.out.println("HIT:" + hitCount);
                System.out.println("MISS:" + missCount );
               
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
                System.out.println("HIT:" + hitCount);
                System.out.println("MISS:" + missCount );
                
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
                System.out.println("WON");
            }
            
            if( gameState == GS_LOST)
            {
                System.out.println("LOST");
            }
            
            
            //Draw the ball
            p_table.l_ball.setBounds( ball.getCenterX() - ball.getRadius(), 
                    ball.getCenterY() - ball.getRadius(), 
                    ball.getRadius() * 2, ball.getRadius() * 2);
            
        
            for( int i = 0; i < cupList.size(); i++)
            {
                if( !((Sprite) cupList.get(i)).getHit() )
                (p_table.labelList.get(i)).setBounds(  ((Sprite) cupList.get(i)).getCenterX() -   ((Sprite) cupList.get(i)).getRadius(), 
                            ((Sprite) cupList.get(i)).getCenterY() -   ((Sprite) cupList.get(i)).getRadius(), 
                            ((Sprite) cupList.get(i)).getRadius() * 2, ((Sprite) cupList.get(i)).getRadius() * 2);
                else
                (p_table.labelList.get(i)).setVisible(false);    
            }
            
            p_table.repaint();
            p_table.revalidate();
        }
    };
    
    public static void main(String[] args){
        
        BeerPong b = new BeerPong();
        
        JFrame f = new JFrame();
        f.setLocation(WIDTH / 4, HEIGHT / 4);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        f.add(b);
        f.pack();
    }
    
     private class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if( e.getKeyCode() == KeyEvent.VK_K )
            {
                System.out.println("K");
            }
            
            if( e.getKeyCode() == KeyEvent.VK_R || gameState == GS_LOST || gameState == GS_WON  )
            {
                resetGame();
            }
            
            if( gameState == GS_INIT )
            {
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    ball.setSpeedX( ball.getSpeedX() - 2000 );
                    System.out.println( "Ball_x: " + ball.getSpeedX() );

                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    ball.setSpeedX( ball.getSpeedX() + 2000 );
                    System.out.println( "Ball_x: " + ball.getSpeedX() );
                    System.out.println( "X: " + ball.getCenterY() );
                }
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    ball.setSpeedY( ball.getSpeedY() - 2000 );
                    System.out.println( "Ball_y: " + ball.getSpeedY() );
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    ball.setSpeedY( ball.getSpeedY() + 2000 );
                    System.out.println( "Ball_y: " + ball.getSpeedY() );
                }

                if(e.getKeyCode() == KeyEvent.VK_X)
                {
                    ball.setSpeedZ( ball.getSpeedZ() + 2000 );
                    System.out.println( "Ball_z: " + ball.getSpeedZ() );
                }

                if(e.getKeyCode() == KeyEvent.VK_Z)
                {
                    ball.setSpeedZ( ball.getSpeedZ() - 2000 );
                    System.out.println( "Ball_z: " + ball.getSpeedZ() );
                }

                if(e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    gameState = GS_MOTION;
                }
            }
            
            if( gameState == GS_HIT )
            {
                
                if(e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    initBall();
                    gameState = GS_INIT;
                }
            }
            
            if( gameState == GS_MISS )
            {
                if(e.getKeyCode() == KeyEvent.VK_SPACE)
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
            System.out.println(gameState);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
