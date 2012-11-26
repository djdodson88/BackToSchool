import java.util.ArrayList;
import java.awt.Point;

public class Campus 
{
	private Tile[][] campus;
	private int WIDTH = 30, HEIGHT = 20;
	
	public Campus()
	{
		//TODO: create tool to make campus design easier
		campus = new Tile[WIDTH][HEIGHT];
		
		for (int i=0; i<30; i++)
			for (int j=0; j<20; j++)
				campus[i][j] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		
		// building and path
		campus[0][0] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[0][1] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[0][2] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[0][1] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[1][0] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[1][1] = new Tile(Tile.Type.ROADH, Tile.Direction.UP);
		campus[1][2] = new Tile(Tile.Type.ROADV, Tile.Direction.UP);
		campus[0][3] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[1][3] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[2][3] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[3][3] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[4][3] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[0][2] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[1][2] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[2][2] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[3][2] = new Tile(Tile.Type.SIGN, Tile.Direction.LEFT);
		campus[4][2] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[1][1] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[2][1] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[3][1] = new Tile(Tile.Type.TREE, Tile.Direction.LEFT);
		campus[4][1] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[3][4] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[3][0] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[2][0] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[4][0] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		
		// copied
		campus[5][0] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[5][1] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[5][2] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[5][1] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[6][0] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[6][1] = new Tile(Tile.Type.ROADH, Tile.Direction.UP);
		campus[6][2] = new Tile(Tile.Type.ROADV, Tile.Direction.UP);
		campus[5][3] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[6][3] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[7][3] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[8][3] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[9][3] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[5][2] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[6][2] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[7][2] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[8][2] = new Tile(Tile.Type.SIGN, Tile.Direction.LEFT);
		campus[9][2] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[6][1] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[7][1] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[8][1] = new Tile(Tile.Type.TREE, Tile.Direction.LEFT);
		campus[9][1] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[8][4] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[8][0] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[7][0] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[9][0] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		
		// copied
		campus[5][5] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[5][6] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[5][7] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[5][6] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[6][5] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[6][6] = new Tile(Tile.Type.ROADH, Tile.Direction.UP);
		campus[6][7] = new Tile(Tile.Type.ROADV, Tile.Direction.UP);
		campus[5][8] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[6][8] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[7][8] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[8][8] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[9][8] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[5][7] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[6][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[7][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[8][7] = new Tile(Tile.Type.SIGN, Tile.Direction.LEFT);
		campus[9][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[6][6] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[7][6] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[8][6] = new Tile(Tile.Type.TREE, Tile.Direction.LEFT);
		campus[9][6] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[8][9] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[8][5] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[7][5] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		campus[9][5] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
	}
	
	public Tile getTile(int xIndex, int yIndex)
	{
		return campus[xIndex][yIndex];
	}
	
	public int getWidth()
	{
		return WIDTH;
	}
	
	public int getHeight()
	{
		return HEIGHT;
	}
	
	public boolean isTraversable(int xIndex, int yIndex)
	{
		//TODO: add any additional traversable types (add new types as needed)
		if (xIndex<0 || xIndex >= WIDTH)
			return false;
		if (yIndex<0 || yIndex >= HEIGHT)
			return false;
		
		switch (campus[xIndex][yIndex].getType()) 
		{
			case GRASS:
			case ROADH:
			case ROADV:
			case FORK:
				return true;
			default:
				return false;
		}
	}
	
	public ArrayList<Point> getDoors()
	{
		ArrayList<Point> doors = new ArrayList<Point>();
		
		for (int i=0; i<WIDTH; i++)
			for (int j=0; j<HEIGHT; j++)
				if (campus[i][j].getType() == Tile.Type.DOOR)
					doors.add(new Point(i,j));
		
		return doors;
	}
}
