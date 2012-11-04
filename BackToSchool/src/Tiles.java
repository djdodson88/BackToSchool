import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Tiles 
{	
	public static Image get(Tile t)
	{
		BufferedImage tile = null;
		boolean rotate = true;
		try {	
			switch (t.getType())
			{
				case CORNER:
				{	tile = ImageIO.read(new File("art/school/roadcorner.jpg"));
					break;	
				}
				case GRASS:
				{	tile = ImageIO.read(new File("art/school/grass.jpg"));
					break;	
				}
				case LAND:
				{	tile = ImageIO.read(new File("art/school/land.jpg"));
					break;	
				}
				case FORK:
				{	tile = ImageIO.read(new File("art/school/roadfork.jpg"));
					break;	
				}
				case ROADH:
				{	tile = ImageIO.read(new File("art/school/road-side.jpg"));
					break;	
				}
				case ROADV:
				{	tile = ImageIO.read(new File("art/school/road.jpg"));
					break;	
				}
				case ROOF:
				{	tile = ImageIO.read(new File("art/school/wallroof.jpg"));
					break;	
				}
				case WALL:
				{	tile = ImageIO.read(new File("art/school/wall.jpg"));
					break;	
				}
				case DOOR:
				{	tile = ImageIO.read(new File("art/school/door.jpg"));
					break;
				}
				case TREE:
				{	if (t.getDirection() == Tile.Direction.UP) 
						tile = ImageIO.read(new File("art/school/tree_top.jpg"));
					else
						tile = ImageIO.read(new File("art/school/treetrunk.jpg"));
					rotate = false;
					break;
				}
				case SIGN:
				{
					switch (t.getDirection())
					{
						case LEFT:
						{	tile = ImageIO.read(new File("art/school/humanities.jpg"));
							break;
						}
						case UP: 
						{	tile = ImageIO.read(new File("art/school/math_sign.jpg"));
							break;
						}
						default:
						{	tile = ImageIO.read(new File("art/school/science_sign.jpg"));
							break;
						}
					}		
					rotate = false;
					break;
				}
				default:
				{	tile = ImageIO.read(new File("art/school/land.jpg"));
					break;	
				}
			}
		} 
		catch (IOException e)
		{	/*TODO: any exceptiopn handling*/ } 
		
		//TODO: any image manipulation
		if (rotate)
		{
			
		}
		return tile;
	}
}
