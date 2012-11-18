import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MiniSplash extends JPanel
{
	boolean finished;
	BufferedImage binderBg;
	
	public MiniSplash(int game)
	{
		/* Game Key
		 * 1 = Fruit
		 * 2 = Warehouse
		 * 3 = Sudoku
		 * 4 = Tile Puzzle
		 * 5 = Beer Pong
		 */
		
		finished = false;
		JButton button = new JButton("Finished");
		this.setSize(new Dimension(400,300));
		setup();
		this.add(button);
		this.setVisible(true);
		
		
	}
	
	private void drawString(Graphics g, String text, int x, int y)
	{
		for(String line: text.split("\n"))
		{
			g.drawString(line, x, y+= g.getFontMetrics().getHeight()+4);
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(binderBg, 0, 0, null);
		Font font = new Font("Arial", Font.PLAIN, 12);
		drawString(g, setInstructions(), 60, 42);
		
	}
	
	private void setup()
	{
	
			try {
				binderBg = ImageIO.read(new File("art/classroom/splash.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	private String setInstructions()
	{
		String instructions = "";
		String gameTitle = "Sudoku";
		
		instructions += gameTitle + "\n";
		instructions += "Sudoku is a puzzle-game, which involves \n filling in the grid with the correct number sequence ";
		instructions += "\n \n Each column, row, and subgrid must contain \n the number sequence of the n x n grid \n";
		instructions += "e.g. A 4x4 grid must be filled in by numbers 1-4";
		
		return instructions;
	}
	
}
