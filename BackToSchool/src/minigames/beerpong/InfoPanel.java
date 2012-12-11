package minigames.beerpong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InfoPanel extends JPanel{
	
	JLabel wordLabel;
	JLabel winLabel;
	JLabel pointLabel;
	JLabel exitLabel;

	public InfoPanel(){
		setPreferredSize( new Dimension(TopViewPanel.WIDTH, 125) );
		setBackground(Color.DARK_GRAY);       
		
		wordLabel  = new JLabel("Press S for Side View");
		wordLabel.setForeground(Color.LIGHT_GRAY);
		
		exitLabel = new JLabel("EXIT");
		exitLabel.setOpaque(true);
		exitLabel.setForeground(Color.BLACK);
		exitLabel.setBackground(Color.orange);
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		winLabel = new JLabel("result");
		winLabel.setOpaque(true);
		winLabel.setForeground(Color.BLACK);
		winLabel.setBackground(Color.orange);
		winLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		pointLabel = new JLabel("pointLabel");
		pointLabel.setOpaque(true);
		pointLabel.setForeground(Color.BLACK);
		pointLabel.setBackground(Color.orange);
		pointLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		add(winLabel);
		add(pointLabel);
		add(wordLabel);
		add(exitLabel);
		
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		wordLabel.setBounds(400,10,130,20);
		winLabel.setBounds(20,25,130,40);
		pointLabel.setBounds(20,65,130,40);
		exitLabel.setBounds(200,50, 130, 40);
	}
}
