package minigames.tiles;
import java.awt.*;
import javax.swing.*;

public class TilePuzzle 
{
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Tile Puzzle");
		frame.setContentPane(new PuzzlePanel());
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
