import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class CampusPanel extends JPanel 
{
	private int playerX, playerY, screenX, screenY;
	private static int PWIDTH=800, PHEIGHT=600, TILE=50, TILESX=16, TILESY=12, PADX=4, PADY=3;
	private boolean animating;
	private enum Direction{LEFT, UP, RIGHT, DOWN};
	private Image player;
	private Image[][] tiles;
	private Thread animate;
	private Campus campus;
	
	public CampusPanel()
	{
		//TODO: more elaborate instatiation
		
		campus = new Campus();
		tiles = new Image[TILESX][TILESY];
		for (int i=0; i<TILESX; i++)
			for (int j=0; j<TILESY; j++)
			{	Tile tile = campus.getTile(screenX+i, screenY+j);
				if (tile != null)
					tiles[i][j] = Tiles.get(tile);
			}
			
		try { 
			player = ImageIO.read(new File("art/school/student.png"));
		} catch(IOException e){};
		playerX = playerY = 5;
		screenX = screenY = 0;
		
		addKeyListener(new CampusListener());
		setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
		setBackground(Color.RED);
		setFocusable(true);
		requestFocus();
		
		animating = true;
		AnimateThread animate = new AnimateThread();
		//animate.start();
		
	}
	
	private class AnimateThread extends Thread
	{	
		public void run()
		{
			while (animating)
			{
				try {
					sleep(500);
					repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void updateTiles(Direction dir)
	{
		switch (dir) 
		{
			case DOWN:
				shiftDown();
				break;
			case LEFT:
				shiftLeft();
				break;
			case RIGHT:
				shiftRight();
				break;
			case UP:
				shiftUp();
				break;
		}
	}

	private void shiftDown() 
	{
		// switch tiles over
		for (int i=0; i<(TILESX); i++)
			for (int j=0; j<(TILESY-1); j++)
				tiles[i][j] = tiles[i][j+1];
		// adjust screen location (within campus)
		screenY++;
		// load in new tiles from campus
		for (int i=0; i<TILESX; i++)
			tiles[i][TILESY-1] = Tiles.get(campus.getTile(i+screenX, (TILESY-1)+screenY));
	}

	private void shiftLeft() 
	{
		if (screenX>0)
		{
			for (int i=TILESX-1; i>0; i--)
				for (int j=0; j<(TILESY); j++)
					tiles[i][j] = tiles[i-1][j];
		
			screenX--;
		
			for (int j=0; j<TILESY; j++)
				tiles[0][j] = Tiles.get(campus.getTile(screenX, j+screenY));
		}
	}
	
	private void shiftUp()
	{
		if (screenY > 0)
		{
			for (int i=0; i<(TILESX); i++)
				for (int j=TILESY-1; j>0; j--)
					tiles[i][j] = tiles[i][j-1];

			screenY--;

			for (int i=0; i<TILESX; i++)
				tiles[i][0] = Tiles.get(campus.getTile(i+screenX,screenY));
		}
	}
	
	private void shiftRight()
	{
		for (int i=0; i<TILESX-1; i++)
			for (int j=0; j<(TILESY); j++)
				tiles[i][j] = tiles[i+1][j];
		
		screenX++;
		
		for (int j=0; j<TILESY; j++)
			tiles[TILESX-1][j] = Tiles.get(campus.getTile((TILESX-1)+screenX, j+screenY));
	}
	
	public void paintComponent(Graphics g)
	{
		//TODO: more elaborate rendering
		g.setColor(Color.LIGHT_GRAY);
		for (int i=0; i<TILESX; i++)
			for (int j=0; j<TILESY; j++)
			{	if (tiles[i][j]!=null)
					g.drawImage(tiles[i][j], i*TILE, j*TILE, TILE, TILE, this);//  tiles[i][j].paintIcon(this, g, i*TILE, j*TILE);
				else
					g.fillRect(i*TILE, j*TILE, TILE, TILE);
			}
				
		g.drawImage(player, playerX*TILE, playerY*TILE, TILE, TILE, this); //player.paintIcon(this, g, playerX, playerY);
	}
	
	public class CampusListener extends KeyAdapter
	{	
		//TODO: take user input to control player
		public void keyPressed(KeyEvent e)
		{	
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				if (campus.isTraversable(screenX+playerX, screenY+playerY-1))
				{	
					int cHeight = campus.getHeight();
					if ((playerY-1>PADY-1 && playerY-1<(TILESY-PADY)) || screenY == 0
							|| ((screenY == cHeight-TILESY) && playerY-1>=(TILESY-PADY)) )
						playerY--;
					else
						updateTiles(Direction.UP);
					repaint();
				}
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{	
				if (campus.isTraversable(screenX+playerX-1, screenY+playerY))
				{	
					int cWidth = campus.getWidth();
					if ((playerX-1>PADX-1 && playerX-1<(TILESX-PADX)) || screenX == 0
							|| ((screenX == cWidth-TILESX) && playerX-1>=(TILESX-PADX)) )
						playerX--;
					else
						updateTiles(Direction.LEFT);
					repaint();
				}
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{	
				if (campus.isTraversable(screenX+playerX, screenY+playerY+1))
				{	
					int cHeight = campus.getHeight();
					if ((playerY+1>PADY && playerY+1<(TILESY-PADY)) || screenY == (cHeight-TILESY)
							|| ((screenY == 0) && playerY+1<=PADY) )
						playerY++;
					else
						updateTiles(Direction.DOWN);
					repaint();
				}
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{	
				if (campus.isTraversable(screenX+playerX+1, screenY+playerY))
				{	
					int cWidth = campus.getWidth();
					if ((playerX+1>PADX && playerX+1<(TILESX-PADX)) || screenX == (cWidth-TILESX)
							|| ((screenX == 0) && playerX+1<=PADX) )
						playerX++;
					else
						updateTiles(Direction.RIGHT);
					repaint();
				}
			}
		}
	}
	
	private class CampusMouseListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent e) 
		{
			Point click = e.getPoint();
			int xIndex = click.x/TILE;
			int yIndex = click.y/TILE;
			
			Tile clicked = campus.getTile(xIndex, yIndex);
			
		}
	}
}