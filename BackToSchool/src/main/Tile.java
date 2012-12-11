package main;


public class Tile 
{
	public enum Direction {UP, RIGHT, DOWN, LEFT}
	public enum Type {CORNER, WALL, WINDOW, ROOF, GRASS, LAND, FLOWER, ROAD, FORK, DOOR, TREE, SIGN}
	private Type type;
	private Direction dir;
	
	public Tile(Type type)
	{
		this.type = type;
		this.dir = Direction.UP;
	}
	
	public Tile(Type type, Direction dir)
	{
		this.type = type;
		this.dir = dir;
	}
	
	public Type getType()
	{	
		return type;
	}
	
	public Direction getDirection()
	{
		return dir;
	}
	
	public String toString()
	{
		return "new Tile(Tile.Type."+type+", Tile.Direction."+dir+");";
	}
}
