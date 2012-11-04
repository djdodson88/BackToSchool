package minigames.tiles;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.*;

public class PuzzlePanel extends JPanel
{
	private ImageIcon[][] puzzle;
	private ImageIcon one, two, three, four, five, six, seven, eight, nine;
	private Rectangle oneR, twoR, threeR, fourR, fiveR, sixR, sevenR, eightR, nineR;
	private static int PWIDTH=500, PHEIGHT=400, TILE=100, XPAD=35,YPAD=15;
	private boolean finished;
	
	public PuzzlePanel()
	{
		puzzle = new ImageIcon[3][3];
		one = new ImageIcon("art/tilePuzzle/1.png");
		two = new ImageIcon("art/tilePuzzle/2.png");
		three = new ImageIcon("art/tilePuzzle/3.png");
		four = new ImageIcon("art/tilePuzzle/4.png");
		five = new ImageIcon("art/tilePuzzle/5.png");
		six = new ImageIcon("art/tilePuzzle/6.png");
		seven = new ImageIcon("art/tilePuzzle/7.png");
		eight = new ImageIcon("art/tilePuzzle/8.png");
		nine = new ImageIcon("art/tilePuzzle/blank.png");
	
		Point p = new Point(XPAD,YPAD);
		oneR = new Rectangle(p.x,p.y,TILE,TILE);
		twoR = new Rectangle(p.x+TILE,p.y,TILE,TILE);
		threeR = new Rectangle(p.x+2*TILE,p.y,TILE,TILE);
		fourR = new Rectangle(p.x,p.y+TILE,TILE,TILE);
		fiveR = new Rectangle(p.x+TILE,p.y+TILE,TILE,TILE);
		sixR = new Rectangle(p.x+2*TILE,p.y+TILE,TILE,TILE);
		sevenR = new Rectangle(p.x,p.y+2*TILE,TILE,TILE);
		eightR = new Rectangle(p.x+TILE,p.y+2*TILE,TILE,TILE);
		nineR = new Rectangle(p.x+2*TILE,p.y+2*TILE,TILE,TILE);

		newPuzzle();
		finished = false;
		
		addMouseListener(new PuzzleListener());
		setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
		setBackground(Color.gray);
		requestFocus();
	}
	
	private void newPuzzle()
	{
		LinkedBlockingQueue<ImageIcon> queue = new LinkedBlockingQueue<ImageIcon>();
		queue.add(one);
		queue.add(two);
		queue.add(three);
		queue.add(four);
		queue.add(five);
		queue.add(six);
		queue.add(seven);
		queue.add(eight);
		queue.add(nine);

		for (int i=0; i<3; i++)
		{	for (int j=0; j<3; j++)
			{
				int skip = (int)((Math.random())*10);
				for (int k=skip; k>0; k--)
					if (queue.size()>1)
						queue.add(queue.poll());

				puzzle[j][i] = queue.poll();	
			}
		}
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, PWIDTH, PHEIGHT);
		
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				puzzle[i][j].paintIcon(this, g, (i*TILE)+XPAD, (j*TILE)+YPAD);
		
		if (finished)
		{
			g.setColor(Color.RED);
			g.drawString("CONGRATULATIONS", 40, PHEIGHT-25);
			g.drawString("Click anywhere to play again", 25, PHEIGHT-10);
		}
	}
	
	private boolean isSolved()
	{
		if (puzzle[0][0]==one && puzzle[1][0]==two && puzzle[2][0]==three &&
			puzzle[0][1]==four && puzzle[1][1]==five && puzzle[2][1]==six &&
			puzzle[0][2]==seven && puzzle[1][2]==eight && puzzle[2][2]==nine)
			return true;
		else
			return false;
	}
	
	private class PuzzleListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			if (!finished)
			{
				int x = e.getX();
				int y = e.getY();
				
				boolean tileClicked = true;
				int xIndex = 0, yIndex = 0;
				
				if (oneR.contains(x,y))
				{	xIndex = 0;
					yIndex = 0;
				}
				else if (twoR.contains(x,y))
				{	xIndex = 1;
				 	yIndex = 0;
				}
				else if (threeR.contains(x,y))
				{	xIndex = 2;
			 		yIndex = 0;
				}
				else if (fourR.contains(x,y))
				{	xIndex = 0;
		 			yIndex = 1;
				}
				else if (fiveR.contains(x,y))
				{	xIndex = 1;
	 				yIndex = 1;
				}
				else if (sixR.contains(x,y))
				{	xIndex = 2;
	 				yIndex = 1;
				}
				else if (sevenR.contains(x,y))
				{	xIndex = 0;
	 				yIndex = 2;
				}
				else if (eightR.contains(x,y))
				{	xIndex = 1;
	 				yIndex = 2;
				}
				else if (nineR.contains(x,y))
				{	xIndex = 2;
	 				yIndex = 2;
				}
				else
					tileClicked = false;
				
				if (tileClicked)
				{				
					ImageIcon tmp;
					if (xIndex>0 && puzzle[xIndex-1][yIndex] == nine)
					{	tmp = puzzle[xIndex-1][yIndex];
						puzzle[xIndex-1][yIndex] = puzzle[xIndex][yIndex];
						puzzle[xIndex][yIndex] = tmp;
					}
					else if (xIndex<2 && puzzle[xIndex+1][yIndex] == nine)
					{	tmp = puzzle[xIndex+1][yIndex];
						puzzle[xIndex+1][yIndex] = puzzle[xIndex][yIndex];
						puzzle[xIndex][yIndex] = tmp;
					}
					else if (yIndex>0 && puzzle[xIndex][yIndex-1] == nine)
					{	tmp = puzzle[xIndex][yIndex-1];
						puzzle[xIndex][yIndex-1] = puzzle[xIndex][yIndex];
						puzzle[xIndex][yIndex] = tmp;	
					}
					else if (yIndex<2 && puzzle[xIndex][yIndex+1] == nine)
					{	tmp = puzzle[xIndex][yIndex+1];
						puzzle[xIndex][yIndex+1] = puzzle[xIndex][yIndex];
						puzzle[xIndex][yIndex] = tmp;	
					}
					
					if (isSolved())
						finished = true;
					repaint();
				}	
			}
			else // isFinished
			{
				newPuzzle();
				finished = false;
			}
		}
	}
}
