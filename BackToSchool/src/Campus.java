public class Campus 
{
	private Tile[][] campus;
	
	public Campus()
	{
		//TODO: create tool to make campus design easier
		campus = new Tile[30][20];
		
		for (int i=0; i<30; i++)
			for (int j=0; j<20; j++)
				campus[i][j] = new Tile(Tile.Type.GRASS, Tile.Direction.UP);
		
		
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
	}
	
	public Tile getTile(int xIndex, int yIndex)
	{
		return campus[xIndex][yIndex];
	}
	
	public boolean isTraversable(int xIndex, int yIndex)
	{
		//TODO: add any additional traversable types (add new types as needed)
		
		switch (campus[xIndex][yIndex].getType()) 
		{
			case GRASS:
			case ROADH:
				return true;
			default:
				return false;
		}
	}
}
