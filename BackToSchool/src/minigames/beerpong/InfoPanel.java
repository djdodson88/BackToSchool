package minigames.beerpong;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class InfoPanel extends JPanel{
	public InfoPanel(){
		setPreferredSize( new Dimension(TopViewPanel.WIDTH, 125) );
		setBackground(Color.DARK_GRAY);       
        
	}
}
