package minigames.fruit;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;  

import main.BackToSchool;
import main.Day;
import main.Player;
import main.Battle.Sound;

public class Fruit_Game extends JPanel implements Runnable{  
	private Sound backgroundSong;
	BufferedImage basket;
	BufferedImage banana;
	BufferedImage bomb;
	BufferedImage apple;
	BufferedImage orange;
	String currentItem;
	Image background;
	BufferedImage bf;
	int level;
	int bombsHit;
	int cordX = 200;
	int cordY = 300;
	int X=0;
	int Y=0;
	int totalScore;
	JLabel score;
	JLabel earnedPercentageLabel;
	double earnedPercentage;
	Graphics graphics;
	Timer timer;
	int life;
	JButton exit;
	static int totalFruits;
	int counter;
	private int className;
	private BackToSchool frame;
	private Player student;
	private Day day;
	
	
	public Fruit_Game(Player player, Day current, BackToSchool frame) {
		backgroundSong = new Sound("sounds/Background/POL-across-woodland-short.wav");
		backgroundSong.playSound();
		student = player;
		this.frame = frame;
		day = current;
		
		timer = null;
		this.setPreferredSize(new Dimension(550, 450));
		this.setFocusable(true);   // Allow this panel to get focus.
		totalScore=0;// used to keep the score
		earnedPercentage=0;
		this.setBackground(Color.white);
		currentItem="apple";
		bombsHit=0;
		counter=0;
		life=3;
		X=0;
		Y=0;
		
		int day = current.getDay();
		if(day>0 && day<=3){
			level = 1;// 1-3 depending on the level of the player
			totalFruits=15;
		}
		else if(day>=4 && day<=7){
			level = 2;
			totalFruits=20;
		}
		else if(day>=8){
			level = 3;
			totalFruits=30;
		}
		
		InputMap myInputMap = new InputMap();
		ActionMap myActionMap = new ActionMap();
		left left = new left();
		right right = new right();
		
		myInputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
		
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
		
		myActionMap = this.getActionMap();
		
		myActionMap.put("left", left);
		myActionMap.put("right", right);
		
		setLayout(null);
		loadImages();// load the images for the game
		
		score = new JLabel();
		score.setBounds(220,0,300,50);
		score.setFont(new Font("Serif", Font.PLAIN, 20));
		
		earnedPercentageLabel = new JLabel();
		earnedPercentageLabel.setBounds(200,150,300,50);
		earnedPercentageLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		
		exit = new JButton(new ImageIcon("art/buttons/exit_btn.jpg"));
		exit.setBounds(250,210,100,30);
		exit.addActionListener(new exitButtonListener());
		exit.setVisible(false);
		
		this.add(score,BorderLayout.SOUTH);
		this.add(earnedPercentageLabel);
		this.add(exit);
		this.setVisible(true);
		startAnimation();
	}
	
	public void startAnimation(){
		// if timer hasnt started
		Random r = new Random();
		X=r.nextInt(400);
		Y=0;
		// create new timer
		timer = new Timer(50, new TimerListener());
		timer.start();
//		if(timer==null)
//		{
//			Y=0;
//			// create new timer
//			timer = new Timer(50, new TimerListener());
//			timer.start();
//		}
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
			
			String pathApple = "art/fruit/apple.png";
			apple = ImageIO.read(new File(pathApple));
			
			String pathOrange = "art/fruit/orange.png";
			orange = ImageIO.read(new File(pathOrange));
			
			String pathBomb = "art/fruit/bomb.png";
			bomb = ImageIO.read(new File(pathBomb));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		//associate the keyboard listener with this JFrame
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawImage(apple, X, 700, this);
		g.drawImage(banana, X, 700, this);
		g.drawImage(bomb, X, 700, this);
		g.drawImage(orange, X, 700, this);
		g.drawImage(background, 0, 0, this);
		g.drawImage(basket, cordX, cordY, this);
		
		if(life>0 && counter<totalFruits){
			if(currentItem.equals("apple")){
				g.drawImage(apple, X, Y, this);
			}
			else if(currentItem.equals("banana")){
				g.drawImage(banana, X, Y, this);
			}
			else if(currentItem.equals("bomb")){
				g.drawImage(bomb, X, Y, this);
			}
			else if(currentItem.equals("orange")){
				g.drawImage(orange, X, Y, this);
			}
		}
		else{
			// will draw brackground at the end of the game
			g.drawImage(background, 0, 0, this);
		}
	}
	
	public void setEarnedPercentage(){
		if(level==1)
		{
			//score.setText(Double.toString(totalScore));
			if(totalScore<5 && life==0) // lose and caught only 1-4 fruits
				earnedPercentage=0.1;
			else if(totalScore>=5 && totalScore<10) // lose but caught 5 to 9 fruits
				earnedPercentage=0.2;
			else if(totalScore>=10 && totalScore<14) // lose but caught 10-14 fruits
				earnedPercentage=0.3;
			else if(totalScore>=14 && life<3) // win but lost lifes
				earnedPercentage=0.4;
			else if(totalScore>14 && life>2) // perfect score
				earnedPercentage=0.5;
		}
		else if(level==2)
		{
			//score.setText(Double.toString(totalScore));
			if(totalScore<7 && life==0) //lose and caught only 1-6 fruits
				earnedPercentage=0.1;
			else if(totalScore>=7 && totalScore<14) //lose and caught 7-13 fruits
				earnedPercentage=0.2;
			else if(totalScore>=14 && totalScore<19) //lose and caught 14-19 fruits
				earnedPercentage=0.3;
			else if(totalScore>=19 && life<3) //win and lost lifes
				earnedPercentage=0.4;
			else if(totalScore>19 && life>2)// perfect score
				earnedPercentage=0.5;
		}
		else if(level==3)
		{
			//score.setText(Double.toString(totalScore));
			if(totalScore<10 && life==0)
				earnedPercentage=0.1;
			else if(totalScore>=10 && totalScore<20)
				earnedPercentage=0.2;
			else if(totalScore>=20 && totalScore<29)
				earnedPercentage=0.3;
			else if(totalScore>=29 && life<3)
				earnedPercentage=0.4;
			else if(totalScore>29 && life>2)
				earnedPercentage=0.5;
		}
	}
	
	public double getEarnedPercentage(){
		return earnedPercentage;
	}
		
	public void increaseStats()
	{
		switch(day.getCourse())
		{
		case SCIENCE:
			student.increaseSciRigor(earnedPercentage);
			break;
		case HUMANITIES:
			student.increaseCreativit(earnedPercentage);
			break;
		default:
			student.increaseQuantReasoning(earnedPercentage);
			break;
		}	
	}

	private class left extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(cordX>0)
				cordX-=15;
		}
	}
	
	public class right extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			if(cordX<400)
				cordX+=15;
		}
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
    		if(level==1)
    			Y+=10;
    		else if(level==2)
    			Y+=15;
    		else if(level==3)
    			Y+=18;
        
        	repaint();
        	//System.out.println("repaint");
        	if(Y>400)
        	{
        		timer.stop();
        		timer=null;
        		checkHit();
        		if(totalFruits+1>counter && life>0){
        			Random r = new Random();
        			int option = r.nextInt(4);
        			
        			if(option==0)
        				currentItem="apple";
        			else if(option==1)
        				currentItem="bomb";
        			else if(option==2)
        				currentItem="banana";
        			else if(option==3)
        				currentItem="orange";
        			
        			startAnimation();
        		}
        	}
        	
        	return;
    	}
    }
	
	private class exitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			increaseStats();
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
	}
	
	public void checkHit(){
		if(cordX-70<X && cordX+70>X)
		{
			if(currentItem.equals("bomb")){
    			life--;
			}
    		else{
    			totalScore++;
    			counter++;
    		}
			
		}
		else if(!(currentItem.equals("bomb"))){
			life--;// this will mean that the fruit fell and you were not able to catch it
			counter++;
		}
		
		score.setText("Score: "+Integer.toString(totalScore)+"/"+totalFruits+"  Life:"+(life));
		
		if(!(life>0)){
			setEarnedPercentage();
			score.setVisible(false);
			earnedPercentageLabel.setText(" You earned: "+earnedPercentage+" exp");
			exit.setVisible(true);
		}
		else if(!(totalFruits>counter)){
			setEarnedPercentage();
			score.setVisible(false);
			earnedPercentageLabel.setText(" You earned: "+earnedPercentage+" exp");
			exit.setVisible(true);
		}
			
	}

	
	public void run() 
	{	
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

	/** main program (entry point) */
	   public static void main(String[] args) {
	      // Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
	      javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            // Set up main window (using Swing's Jframe)
	            JFrame frame = new JFrame("Fruit Catching Game");

	            CardLayout layout = new CardLayout();
				JPanel cards = new JPanel(layout);
	    		BackToSchool b2sFrame = new BackToSchool(layout, cards);       
	            Fruit_Game fg = new Fruit_Game(new Player(), new Day(1), b2sFrame);
	            frame.setSize(550,450);
	            frame.setBackground(Color.green);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setContentPane(fg);
	            frame.pack();
	            frame.setVisible(true);
	           // System.out.println(counter);
	         }
	      });
	   }
	
}










