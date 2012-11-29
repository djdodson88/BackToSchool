package main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Welcome extends JPanel implements ActionListener
{

	BufferedImage bg;
	JButton start, about;
	BackToSchool frame;
	
	public Welcome()
	{
		setup();
		start = new JButton(new ImageIcon("art/buttons/begin_btn.jpg"));
		about = new JButton(new ImageIcon("art/buttons/about_btn.jpg"));
		
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
			frame.switchPanel(BackToSchool.Screen.CAMPUS);
		}
	}
}
