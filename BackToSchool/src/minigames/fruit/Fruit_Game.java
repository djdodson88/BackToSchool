package minigames.fruit;

import java.awt.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;  

public class Fruit_Game extends JPanel implements KeyListener,Runnable
{  
	int level, cordX = 200, cordY = 300, bananaX=0, bananaY=0, totalScore;
	static int totalBananas=10, counter;
	BufferedImage basket, banana, bf;
	Image background;
	JLabel score;
	Graphics graphics;
	Timer timer;

	public Fruit_Game(int l) {
		setPreferredSize(new Dimension(550, 450));
		setFocusable(true);   // Allow this panel to get focus.
		addKeyListener(this);
		//this.setSize(550,450);
		setVisible(true);
		setBackground(Color.white);
		bananaX=0;
		bananaY=0;
		level = l;// 0-3 depending on the level of the player
		totalScore=0;// used to keep the score
		loadImages();// load the images for the game
		score = new JLabel("0");
		add(score,BorderLayout.SOUTH);
		startAnimation();
	}
	
	public void startAnimation(){
		// if timer hasnt started
		Random r = new Random();
		bananaX=r.nextInt(300);
		if(timer==null)
		{
			//System.out.println("Start");
			bananaY=0;
			// create new timer
			timer = new Timer(50, new TimerListener());
			timer.start();
		}
		//sSystem.out.println("Running");
	}

	public void loadImages() {
		try {
			//path for image files
			String backPath = "art/fruit/Kitchen background_s_wm.jpg";
			background = ImageIO.read(new File(backPath));

			String pathBasket = "art/fruit/basket.png";
			basket = ImageIO.read(new File(pathBasket));

			String pathBanana = "art/fruit/banana.png";
			banana = ImageIO.read(new File(pathBanana));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		//associate the keyboard listener with this JFrame
		addKeyListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g){
		// buffer for smooth moving
		super.paintComponent(g);
		bf = new BufferedImage( this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_RGB);

		try{
			//animation(bf.getGraphics());
			bf.getGraphics().setColor(Color.black);
			bf.getGraphics().drawString("Score", 10, 425);
			bf.getGraphics().drawImage(background, 0, 0, this);// draw background image
			bf.getGraphics().drawImage(basket, cordX, cordY, this);// draw basket image
			bf.getGraphics().drawImage(banana, bananaX, bananaY, this);// draw basket image
			graphics = bf.getGraphics();
			g.drawImage(bf,0,0,null);
		}catch(Exception ex){

		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {	
		switch (ke.getKeyCode()) {
		//if the right arrow in keyboard is pressed...
		case KeyEvent.VK_RIGHT: {
			if(cordX<400)
				cordX+=10;
		}
		break;
		//if the left arrow in keyboard is pressed...
		case KeyEvent.VK_LEFT: {
			if(cordX>0)
				cordX-=10;
		}
		break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
	}
	
	public void wait(int n){
	    long t0, t1;
	    t0 = System.currentTimeMillis();

	    do {
	        t1 = System.currentTimeMillis();            
	    } while ((t1 - t0) < n);
	}   
	
	private class TimerListener implements ActionListener
    {	
        public void actionPerformed(ActionEvent e)
        {
        	bananaY+=10;
        	repaint();
        	//System.out.println("repaint");
        	if(bananaY>400)
        	{
        		counter++;
        		timer.stop();
        		timer=null;
        		checkHit();
        		if(totalBananas>counter)
        			startAnimation();
        	}
        	
        	return;
    	}
    }
	
	public void checkHit(){
		if(cordX-70<bananaX && cordX+70>bananaX)
		{
			totalScore++;
		}
		score.setText(Integer.toString(totalScore)+"/"+counter);
	}

	@Override
	public void run() 
	{	
	}
	
	/** main program (entry point) */
	   public static void main(String[] args) {
	      // Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
	      javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            // Set up main window (using Swing's Jframe)
	            JFrame frame = new JFrame("Fruit Catching Game");
	            Fruit_Game fg = new Fruit_Game(1);
	            frame.setSize(550,450);
	            frame.setBackground(Color.green);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setContentPane(fg);
	            frame.pack();
	            frame.setVisible(true);
	            System.out.println(counter);
	         }
	      });
	   }
	
}










