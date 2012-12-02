package main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/*
 * MiniSplashPane: layout container for MiniSplash
 */

public class MiniSplashPane extends JLayeredPane implements ActionListener
{
	BackToSchool frame;
	private BufferedImage backgroundImg;
	private MiniSplash splash;
	
	private JButton default_exit; //for testing day structure, etc
	
	public MiniSplashPane(BackToSchool frame, Player student, Day day)
	{
		/* NOTE:
		 * needs to be instantiated with frame as parameter, 
		 * so that components can switch between the screens in CardLayout
		 * Will return null with sendFrame() method
		 */
		
		setup();
		this.frame = frame;
		
		splash = new MiniSplash(frame);
		splash.setDay(day); // pass day for access of day statistics
		splash.getPlayer(student); // get player for statistics
		
		JPanel miniSplash = splash;
		
		// STRICTLY FOR TESTING, WILL BE REMOVED 
		//Button used for now to get through day-> weeks
		
		default_exit = new JButton(new ImageIcon("art/buttons/exit_btn.jpg"));
		this.add(default_exit);
		default_exit.setBounds(700,0, 100, 30);
		default_exit.addActionListener(this);
		default_exit.setVisible(true);
		
		
		miniSplash.setBounds(50,50, 500, 300);
		this.add(miniSplash);
		this.moveToFront(miniSplash);
		
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();

		if(src == default_exit)
		{
			frame.switchPanel(BackToSchool.Screen.CAMPUS);
			
		}

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
	
}
