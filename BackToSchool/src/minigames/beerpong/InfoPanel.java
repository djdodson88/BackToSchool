package minigames.beerpong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
	JLabel timerLabel;
	JLabel wordLabel;
	JLabel winLabel;
	JLabel pointLabel;

	public InfoPanel(){
		setPreferredSize( new Dimension(TopViewPanel.WIDTH, 125) );
		setBackground(Color.DARK_GRAY);       
		
		timerLabel = new JLabel("Time");
		wordLabel  = new JLabel("Press S for Side View");
		
		winLabel = new JLabel("winLabel");
		winLabel.setVisible(false);
		
		pointLabel = new JLabel("pointLabel");
		winLabel.setVisible(false);
		
		timerLabel.setOpaque(true);
		winLabel.setOpaque(true);
		pointLabel.setOpaque(true);
		wordLabel.setOpaque(true);
		
		add(timerLabel);
		add(winLabel);
		add(pointLabel);
		add(wordLabel);
		
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		timerLabel.setBounds(100,50,70,20);
		wordLabel.setBounds(200,50,130,20);
		winLabel.setBounds(300,50,70,20);
		pointLabel.setBounds(400,50,70,20);
	}
}
