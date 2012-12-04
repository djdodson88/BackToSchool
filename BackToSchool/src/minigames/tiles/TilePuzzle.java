package minigames.tiles;
import java.awt.*;
import javax.swing.*;
import main.Player;
import main.Day;

public class TilePuzzle 
{
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Tile Puzzle");
		frame.setContentPane(new PuzzlePanel(new Player(), new Day(1)));
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
