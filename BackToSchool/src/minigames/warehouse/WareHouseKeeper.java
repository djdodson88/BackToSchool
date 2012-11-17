/**
 *
 * @author Cagatay Sahin
 */

package minigames.warehouse;
 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class WareHouseKeeper extends JFrame
{    
    JPanel p;
    JPanel controlPanel;
    
    int [][] nums;   
    int [][] dots;
    
    JLabel welldone;
    JLabel levelLabel;
    JLabel restart;
    JLabel undo;
    JLabel countLabel;
    JLabel startScreen;
    
    ImageIcon ground;
    ImageIcon wall;   
    ImageIcon box;
    ImageIcon guy;
    ImageIcon groundDot;
    ImageIcon boxDot;
    ImageIcon guyDot;
    
    boolean isGameStarted;
    int currentLevel;
    
    UndoStack stack;
    final int MAX_LEVELS = 10;
    final int MAX_UNDO = 10;
    int hitCount;
    int numBoxes;
    int yerX;
    int yerY;
    int moveCount;
    int spriteX;
    int spriteY;
    
    
    public WareHouseKeeper(){
        setLayout( new FlowLayout());
       
        startScreen = new JLabel( new ImageIcon("art/warehouse/start.jpg") );
        
        
        welldone = new JLabel( new ImageIcon("art/warehouse/welldone.jpg") );
        welldone.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                welldone.setVisible(false);
                if(currentLevel < MAX_LEVELS )
                  initLevel(++currentLevel);
            }
        });
        
        levelLabel = new JLabel( new ImageIcon("art/warehouse/level.jpg") );
        levelLabel.setHorizontalTextPosition(JLabel.CENTER);
        levelLabel.setVerticalTextPosition(JLabel.CENTER);
        
        restart = new JLabel( new ImageIcon("images/restart.jpg") );
        restart.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 initLevel(currentLevel);
            }
        });
        
        undo = new JLabel( new ImageIcon("art/warehouse/undo.jpg") );
        undo.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!stack.isEmpty())
                {
                    nums = stack.pop(); 
                    moveCount--;
                    reDraw();
                }
           for( int i = 0; i < nums.length; i++ )
                for( int j = 0; j < nums[i].length; j++ )
                if(nums[i][j] == 3)
                {
                    spriteX = i;
                    spriteY = j;
                }
            }
        });
        
        countLabel = new JLabel( new ImageIcon("art/warehouse/level.jpg") );
        countLabel.setHorizontalTextPosition(JLabel.CENTER);
        countLabel.setVerticalTextPosition(JLabel.CENTER);
        
        
        ground = new ImageIcon("art/warehouse/ground.jpg");
        wall = new ImageIcon("art/warehouse/wall.jpg");
        box =  new ImageIcon("art/warehouse/box.jpg");
        guy =  new ImageIcon("art/warehouse/guy.jpg");
        groundDot = new ImageIcon("art/warehouse/groundDot.jpg");
        boxDot = new ImageIcon("art/warehouse/boxDot.jpg");
        guyDot = new ImageIcon("art/warehouse/guyDot.jpg");
        
        p = new JPanel();
        controlPanel = new JPanel();
        controlPanel.setBackground(Color.yellow);
        controlPanel.setPreferredSize( new Dimension (170,500));
        controlPanel.add(levelLabel);
        controlPanel.add(restart);
        controlPanel.add(welldone);
        controlPanel.add(undo);
        controlPanel.add(countLabel);
        
        
        isGameStarted = false;
        currentLevel = 1;
        setBackground(Color.red);      


        KeyListener listener = new MyKeyListener();    
        addKeyListener(listener );

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        add(p);
        add(controlPanel);
        p.addKeyListener(listener);
        
        setFocusable(true);
        initLevel(currentLevel);
    
    
    }//End of the default constructor
    
    private void initLevel(int lvl){        
        welldone.setVisible(false);
        int yLen = (LevelConstants.getNums(lvl)).length;
        int xLen = (LevelConstants.getNums(lvl))[0].length;
        //Create and initialize the nums array for the level
        nums = new int[yLen][xLen];
        for(int i = 0; i < yLen; i++ )
            System.arraycopy((LevelConstants.getNums(lvl))[i], 0, nums[i], 0, xLen);
        stack = new UndoStack(MAX_UNDO, nums.length, nums[0].length);        
        moveCount = 0;
        dots =  LevelConstants.getDots(lvl);
        numBoxes = LevelConstants.getNumBoxes(lvl);
        spriteX = LevelConstants.getSpriteX(lvl);
        spriteY = LevelConstants.getSpriteY(lvl);
        p.setLayout( new GridLayout(yLen,xLen) );
        reDraw();
    }
    
    private void reDraw()
    {   
        hitCount = 0;
        p.removeAll();
        
        for(int i = 0; i < nums.length; i++)
        {
            
            for(int j = 0; j < nums[0].length; j++ )
            {
                if( nums[i][j] == 0)
                    p.add( dots[i][j] == 0 ? (new JLabel (ground)) : (new JLabel (groundDot)));
                else if( nums[i][j] == 1)
                    p.add( new JLabel(wall));
                else if( nums[i][j] == 2)
                {
                    if(dots[i][j] == 0)
                        p.add( new JLabel(box ));
                    else
                    {
                        p.add( new JLabel(boxDot));                        
                        hitCount++;
                    }
                }
                else
                    p.add( dots[i][j] == 0 ? (new JLabel(guy)) : (new JLabel(guyDot)));
            }
            
            if(hitCount == numBoxes)
            {                
                 welldone.setVisible(true);
            }
        }
        
        p.repaint();
        p.validate();
        levelLabel.setText("Level: " + currentLevel);
        countLabel.setText("Moves: " + moveCount);
       
        controlPanel.repaint();
        controlPanel.validate();
        pack();
        
    }
    
    private class MyKeyListener implements KeyListener{
            
            
            public void keyPressed(KeyEvent e) {
              
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    if( spriteY > 0 && nums[spriteX][spriteY - 1] == 0 )
                    {
                        stack.push(nums);
                        nums[spriteX][spriteY] = 0;
                        nums[spriteX][spriteY-1] = 3;
                        spriteY--;
                        moveCount++;

                    }
                    else if ( spriteY > 1 && nums[spriteX][spriteY - 1] == 2 && nums[spriteX][spriteY - 2] == 0 )
                    {
                        stack.push(nums);
                        nums[spriteX][spriteY] = 0;
                        nums[spriteX][spriteY-1] = 3;
                        nums[spriteX][spriteY-2] = 2;
                        spriteY--; 
                        moveCount++;

                    }                    
                }                                        
               
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    if( spriteY < nums[0].length-1 && nums[spriteX][spriteY + 1] == 0 )
                    {
                        stack.push(nums);
                        nums[spriteX][spriteY] = 0;
                        nums[spriteX][spriteY + 1] = 3;
                        spriteY = spriteY + 1;
                        moveCount++;

                    }
                    else if( spriteY < nums[0].length-2 && nums[spriteX][spriteY + 1] == 2 && nums[spriteX][spriteY + 2] == 0 )
                    {
                        stack.push(nums);
                        nums[spriteX][spriteY] = 0;
                        nums[spriteX][spriteY + 1] = 3;
                        nums[spriteX][spriteY + 2] = 2;
                        spriteY++;
                        moveCount++;

                    }                
                }                              
                
                
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    if( spriteX > 0 && nums[spriteX - 1][spriteY] == 0 )
                    {
                        stack.push(nums);
                        nums[spriteX][spriteY] = 0;
                        nums[spriteX - 1][spriteY] = 3;
                        spriteX--;
                        moveCount++;

                    }
                    else if( spriteX > 1 && nums[spriteX - 1][spriteY] == 2 && nums[spriteX - 2][spriteY] == 0 )
                    {
                        stack.push(nums);
                        nums[spriteX][spriteY] = 0;
                        nums[spriteX - 1][spriteY] = 3;
                        nums[spriteX - 2][spriteY] = 2;
                        spriteX--;
                        moveCount++;

                    }
                }
                
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    if( spriteX < nums.length-1 && nums[spriteX + 1][spriteY] == 0 )
                    {
                        stack.push(nums);
                        nums[spriteX][spriteY] = 0;
                        nums[spriteX + 1][spriteY] = 3;
                        spriteX++;
                        moveCount++;

                    }
                    else if( spriteX < nums.length-2 && nums[spriteX + 1][spriteY] == 2 && nums[spriteX + 2][spriteY] == 0 )
                    {
                        stack.push(nums);
                        nums[spriteX][spriteY] = 0;
                        nums[spriteX + 1][spriteY] = 3;
                        nums[spriteX + 2][spriteY] = 2;
                        spriteX++;
                        moveCount++;

                    }
                }
                
                if(e.getKeyCode() == KeyEvent.VK_COMMA)
                    initLevel(9);
                reDraw();
            }//End of method keyPressed
            
            
            public void keyTyped(KeyEvent e) {              
            }

            
            public void keyReleased(KeyEvent e) {                
            }
    };
    
    private void printNums(){
        System.out.println();
        for(int i = 0; i < nums.length; i++)
        {
            for(int j = 0; j < nums[1].length; j++)
                System.out.print( " " + nums[i][j]);
            System.out.println();
        }
    }
    
    private void printDots(){
        System.out.println();
        for(int i = 0; i < dots.length; i++)
        {
            for(int j = 0; j < dots[1].length; j++)
                System.out.print( " " + dots[i][j]);
            System.out.println();
        }
    }
    
    public static void main (String[] args){
        System.out.println("test");   
        WareHouseKeeper mainFrame = new WareHouseKeeper();
       
    }
}
