
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/*
 * MiniSplashPane: layout container for MiniSplash
 */

public class MiniSplashPane extends JLayeredPane
{
	BackToSchool frame;
	private BufferedImage backgroundImg;
	private MiniSplash splash;
	
	public MiniSplashPane(BackToSchool frame, int day)
	{
		/* NOTE:
		 * needs to be instantiated with frame as parameter, 
		 * so that components can switch between the screens in CardLayout
		 * Will return null with sendFrame() method
		 */
		
		setup();
		
		
		splash = new MiniSplash(frame);
		splash.setDay(day); //set difficulty
		
		JPanel miniSplash = splash;
		
		
		miniSplash.setBounds(50,50, 500, 300);
		this.add(miniSplash);
		this.moveToFront(miniSplash);
		
		this.setVisible(true);
		
	}
	
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, null);

	}

	private void setup()
	{
		try {
			backgroundImg = ImageIO.read(new File("art/classroom/classroom.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void sendFrame(BackToSchool frame) 
	{
		this.frame = frame;
	}
}
