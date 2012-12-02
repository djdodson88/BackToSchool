/**
 *
 * @author Cagatay Sahin
 */

package minigames.warehouse;
 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.colorchooser.ColorSelectionModel;


public class WareHouseKeeper extends JPanel
{   
	
	Timer t;
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
    JLabel timerLabel;
    JLabel resultLabel;
    
    ImageIcon ground;
    ImageIcon wall;   
    ImageIcon box;
    ImageIcon guy;
    ImageIcon groundDot;
    ImageIcon boxDot;
    ImageIcon guyDot;
    
    boolean isGameOver;
    int currentLevel;
    int timeRemains;
    
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
    
    private main.Player student;
    private int className;
    
    public WareHouseKeeper(int day){
    	//setMaximumSize(new Dimension(550,450));
    	
    	int delay = 1000; //milliseconds
    	ActionListener taskPerformer = new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	
	        	timeRemains--;
	        	timerLabel.setText("Time: " + timeRemains);
	        	if(timeRemains == 0)
	        	{
	        		t.stop();
	        		isGameOver = true;
	        		resultLabel.setText("Time over :(");
	            	resultLabel.setVisible(true);
	        	}
	        	controlPanel.repaint();
	            controlPanel.validate();
	            
	        }
    	};
    	t = new Timer(delay, taskPerformer);
    	
    	InputMap myInputMap = new InputMap();
		ActionMap myActionMap = new ActionMap();
    	
    	up up = new up();
		down down = new down();
		left left = new left();
		right right = new right();
		comma comma = new comma();
		myInputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
		
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA,0,false), "comma");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
		
		myActionMap = this.getActionMap();
		
		myActionMap.put("up", up);
		myActionMap.put("down", down);
		myActionMap.put("left", left);
		myActionMap.put("right", right);
		myActionMap.put("comma", comma);
    	
    	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        startScreen = new JLabel( new ImageIcon("art/warehouse/start.jpg") );
        
        
        welldone = new JLabel(  );
        welldone.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                welldone.setVisible(false);
                if(currentLevel < MAX_LEVELS )
                  initLevel(++currentLevel);
            }
        });
        
        levelLabel = new JLabel(  );
        levelLabel.setHorizontalTextPosition(JLabel.CENTER);
        levelLabel.setVerticalTextPosition(JLabel.CENTER);
        levelLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        levelLabel.setForeground(Color.black);
        
        timerLabel = new JLabel(  );
        timerLabel.setHorizontalTextPosition(JLabel.CENTER);
        timerLabel.setVerticalTextPosition(JLabel.CENTER);
        timerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        timerLabel.setForeground(Color.black);
        
        resultLabel = new JLabel(  );
        resultLabel.setHorizontalTextPosition(JLabel.CENTER);
        resultLabel.setVerticalTextPosition(JLabel.CENTER);
        resultLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        resultLabel.setForeground(Color.black);
        
        restart = new JLabel(  );
        restart.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 initLevel(currentLevel);
            }
        });
        restart.setVisible(false);
        
        undo = new JLabel ("Undo") ;
        undo.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        undo.setForeground(Color.black);

        
        countLabel = new JLabel(  );
        countLabel.setForeground(Color.black);
        countLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
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
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        
        controlPanel.setBackground( new Color(193,141,88));
        
        controlPanel.setPreferredSize( new Dimension (75,450));
        controlPanel.setMaximumSize( new Dimension (75,450));
        controlPanel.setMinimumSize( new Dimension (75,450));
        
        controlPanel.add(Box.createVerticalGlue() );
        controlPanel.add(levelLabel);
        //controlPanel.add(restart);
        controlPanel.add( Box.createRigidArea( new Dimension(5,25)) );
        //controlPanel.add(welldone);
        controlPanel.add(countLabel);
        controlPanel.add( Box.createRigidArea( new Dimension(5,25)) );
        controlPanel.add(undo);        
        controlPanel.add( Box.createRigidArea( new Dimension(5,25)) );
        controlPanel.add(timerLabel);
        controlPanel.add( Box.createRigidArea( new Dimension(5,25)) );
        controlPanel.add(resultLabel);
        controlPanel.add(Box.createVerticalGlue() );
        
        controlPanel.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                 undo.setForeground( Color.green); 
                 undo.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
             	 controlPanel.setBackground( new Color(193,161,108));
                 controlPanel.repaint();
 	             controlPanel.validate();
            }
            @Override
            public void mouseExited(MouseEvent e){
            	undo.setForeground( Color.black);
            	undo.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
            	controlPanel.setBackground( new Color(193,141,88));
            	controlPanel.repaint();
	            controlPanel.validate();
            	
            }
            public void mousePressed(MouseEvent e){
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
        
        currentLevel = day;
        setBackground( new Color(199,188,136));      

        initLevel(currentLevel);
//        KeyListener listener = new MyKeyListener();    
//        addKeyListener(listener );
        setPreferredSize(new Dimension(550,450));
        
        add(Box.createHorizontalGlue() );
        add(p);
        add(Box.createHorizontalGlue() );
        add(controlPanel);
        setFocusable(true);
        
    
    
    }//End of the default constructor
    
	public void getPlayer(main.Player student)
	{
		this.student = student;
	}
	
	public void getClassSubject(int type)
	{
		className = type;
	}
	
	
	public void increaseStats()
	{
		switch(className-1)
		{
		case 0:
			
			student.increaseSciRigor(0);
			break;
		case 1:
			student.increaseCreativit(0);
			break;
		case 2:
			student.increaseQuantReasoning(0);
			break;
		}
		
		
	}
    
    
    private void initLevel(int lvl){
    	isGameOver = false;
    	resultLabel.setVisible(false);
    	timeRemains = 60;       
    	timerLabel.setText("Time: " + timeRemains);
        t.start();
    	
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
        p.setMaximumSize(new Dimension(xLen*25, yLen*25));
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
            
           //Player wins 
           if(hitCount == numBoxes)
           {
        	   isGameOver = true;
        	   t.stop();
        	   resultLabel.setText("You won!");
        	   resultLabel.setVisible(true);
           }
           
//	        Enables to go to the next level            
//          if(hitCount == numBoxes)
//          {                
//               welldone.setVisible(true);
//          }
            
        }
        
        p.repaint();
        p.validate();
        levelLabel.setText("Level: " + currentLevel);
        countLabel.setText("Moves: " + moveCount);
        
        controlPanel.repaint();
        controlPanel.validate();
        
        
    }
    
    private class comma extends AbstractAction{
    	public void actionPerformed(ActionEvent e)
    	{
    		if(!isGameOver)
    			initLevel(9);
    	}
    }
    
    private class up extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(!isGameOver)
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
				 
				 reDraw();
			}
		}
    }
    private class down extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(!isGameOver){
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
				
				reDraw();
			}
		}
    }
    private class left extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(!isGameOver)
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
				 reDraw();
			}
		}
    }
    private class right extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(!isGameOver)
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
				reDraw();
			}
		}
    }
    
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
        WareHouseKeeper game = new WareHouseKeeper(1);
        JFrame fr = new JFrame();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.add(game);
        fr.pack();
        fr.setFocusable(false);
       
    }
}
