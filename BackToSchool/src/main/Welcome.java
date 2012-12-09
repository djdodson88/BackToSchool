package main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.CampusPanel.Sound;

public class Welcome extends JPanel implements ActionListener
{

	BufferedImage bg;
	JButton start, about;
	BackToSchool frame;
	private Sound testSong;
	
	public Welcome()
	{
		setup();
		start = new JButton(new ImageIcon("art/buttons/begin_btn.jpg"));
		about = new JButton(new ImageIcon("art/buttons/about_btn.jpg"));
		
		testSong = new Sound("sounds/dw1world.mid");
		testSong.playSound();
		
		setLayout(null);
		this.add(start);
		start.setBounds(240,300, 300,49);
		start.setVisible(true);
		start.addActionListener(this);
		this.add(about);
		about.setBounds(240, 360, 300, 49);
		about.setVisible(true);
		about.addActionListener(this);
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(bg, 0, 0, null);
	}
	
	public class Sound // Holds one audio file
	{
	  private AudioClip song; // Sound player
	  private URL songPath; // Sound path

	  Sound(String filename){
	     try
	     {
	    	// System.out.println("file:" + System.getProperty("user.dir") + "\\" + filename);
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
	
	private void setup()
	{
		try {
			bg = ImageIO.read(new File("art/school/welcome.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void sendFrame(BackToSchool frame) 
	{
		this.frame = frame;
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object src = e.getSource();
		
		if(src == start)
		{
			testSong.stopSound();
			frame.switchPanel(BackToSchool.Screen.CAMPUS);
		}
		else if(src == about)
		{
			frame.switchPanel(BackToSchool.Screen.ABOUT);
		}
	}
}
