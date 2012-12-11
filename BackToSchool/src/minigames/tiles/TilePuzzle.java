package minigames.tiles;
import java.awt.*;

import javax.swing.*;

import main.BackToSchool;
import main.Player;
import main.Day;

public class TilePuzzle 
{
	
	public static void main(String[] args)
	{
		CardLayout layout = new CardLayout();
		JPanel cards = new JPanel(layout);
		BackToSchool frame = new BackToSchool(layout, cards);
		Day day = new Day(3);
		day.attendClass();
		frame.setContentPane(new PuzzlePanel(new Player(), day, frame));
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
