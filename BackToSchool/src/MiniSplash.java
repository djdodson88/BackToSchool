import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MiniSplash extends JPanel implements ActionListener
{
	private boolean nxtScreen, finished, kill;
	private BufferedImage binderBg, bulletpoint;
	private int game, numScreen;
	private JButton button, previous;

	public MiniSplash(int type)
	{
		/* Game Key
		 * 1 = Fruit
		 * 2 = Warehouse
		 * 3 = Sudoku
		 * 4 = Tile Puzzle
		 * 5 = Beer Pong
		 */
		game = type;

		switch(game)
		{
		case 1:
			button = new JButton("Start Game");
			break;
		case 2:
			button = new JButton("Start Game");
			break;
		case 3:
			button = new JButton("Next Screen");
			numScreen = 1;
			break;
		case 4:
			button = new JButton("Start Game");
			break;
		}

		finished = false;
		kill = false;

		setup();
		this.setLayout(null);
		this.add(button);
		button.setBounds(200, 250, 100, 30);
		button.addActionListener(this);
		this.setVisible(true);


	}

	private void drawString(Graphics g, String text, int x, int y)
	{
		for(String line: text.split("\n"))
		{
			int bulletPos = y + g.getFontMetrics().getHeight();

			if(line.contains(";li"))
			{
				line = line.replace(";li","");

				if(line.contains(";indent"))
				{
					line = line.replace(";indent", "");
					g.drawString(line, x+50, y+=g.getFontMetrics().getHeight());
					g.drawImage(bulletpoint, x+30, bulletPos-10, null);	
					continue;
				}
				else
				{
					g.drawImage(bulletpoint, x-20, bulletPos-10, null);	
				}

			}
			else
			{
				if(line.contains(";indent"))	
				{
					line = line.replace(";indent","");
					g.drawString(line, x+50, y+=g.getFontMetrics().getHeight());
				}
				else
				{
					g.drawString(line, x, y+=g.getFontMetrics().getHeight());

				}
				continue;
			}
			g.drawString(line, x, y+=g.getFontMetrics().getHeight());

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
			bulletpoint = ImageIO.read(new File("art/classroom/bulletpoint.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	private String setInstructions()
	{
		String instructions = "";
		String gameTitle = "";

		if(game == 1)
		{
			gameTitle = "Fruit Game";
			instructions += gameTitle + "\n\n";
			instructions += "Instructions: \n\n";
			instructions += ";liUse the <left> and <right> arrow keys to catch the falling fruit\n";
			instructions += ";li;indent Each fruit missed will decrease life points\n";
			instructions += ";li;indent Catch as many fruit as you can before life points is 0 \n";
			instructions += ";li;indent Avoid the bombs! Bombs will decrease points";
		}
		else if(game == 2)
		{
			gameTitle = "Warehouse Keeper";
			instructions += gameTitle + "\n";
			instructions += "Instructions: \n";
			instructions += ";liUse the <left>, <right>, <up>, <down> keys to move the player\n";
			instructions += ";liMove the boxes to the pink dots\n";
			instructions += ";liYou may use the UNDO stack 10 times\n";
			
		}
		else if(game == 3)
		{
			gameTitle = "Sudoku";
			instructions += gameTitle + "\n";

			if(numScreen == 1)
			{
				instructions += ";li Sudoku is a puzzle-game, which involves \nfilling in the grid with the correct number sequence ";
				instructions += "\n;liEach column, row, and subgrid must contain the number sequence \nof the n x n grid \n";
				instructions += ";li;indente.g. A 4x4 grid must be filled in by numbers 1-4";

			}
			else if(numScreen == 2)
			{

				instructions = "Instructions:\n";
				instructions += "\n \n ;liNavigate the grid by moving highlight square with arrow keys \n";
				instructions += ";liFill in the grid with numbers with number keys \n";
				instructions += ";liUse the <delete> key to clear cell entry,\n;li;indent you may also replace current cell\n;indent with another number key\n";
				instructions += ";liPress enter with attempted solution to see if correct \n";
				instructions += ";liFinish within time limit, and stats are rewarded accordingly";

			}
		}
		else if(game == 4)
		{
			gameTitle = "Tile Puzzle";
			instructions += gameTitle + "\n";
			instructions += "Instructions: \n";
			instructions += ";li Click the mouse to move the tile over\n";
			instructions += ";li Arrange the puzzle into the correct order.";
			
		}
		return instructions;
	}

	public boolean isDone()
	{
		return kill;
	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if(src == button)
		{
			if(game == 1)
			{
				this.setVisible(false);
				kill = true;
			}
			else if(game == 2)
			{
				this.setVisible(false);
				kill = true;
			}
			else if(game == 3)
			{
				if(!finished)
				{
					numScreen++;
					finished = true;

					button.setText("Start Game");

					repaint();
				}
				else
				{
					this.setVisible(false);
					kill = true;
				}
			}
			else if(game == 4)
			{
				this.setVisible(false);
				kill = true;
			}
			

		}
	}

}
