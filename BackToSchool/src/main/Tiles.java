package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Tiles 
{	
	private BufferedImage corner, grass, land, fork, roadh, roadv, roof, wall, door, treeTop, treeTrunk, 
				  signHum, signMath, signSci, flower;
	
	public Tiles()
	{
		try 
		{	corner = ImageIO.read(new File("art/school/roadcorner.jpg"));
			grass = ImageIO.read(new File("art/school/grass.jpg"));
			flower = ImageIO.read(new File("art/school/flower.jpg"));
			land = ImageIO.read(new File("art/school/land.jpg"));
			fork = ImageIO.read(new File("art/school/roadfork.jpg"));
			roadh = ImageIO.read(new File("art/school/road-side.jpg"));
			roadv = ImageIO.read(new File("art/school/road.jpg"));
			roof = ImageIO.read(new File("art/school/wallroof.jpg"));
			wall = ImageIO.read(new File("art/school/wall.jpg"));
			door = ImageIO.read(new File("art/school/door.jpg"));
			treeTop = ImageIO.read(new File("art/school/tree_top.jpg"));
			treeTrunk = ImageIO.read(new File("art/school/treetrunk.jpg"));
			signMath = ImageIO.read(new File("art/school/math_sign.jpg"));
			signSci = ImageIO.read(new File("art/school/science_sign.jpg"));
			signHum = ImageIO.read(new File("art/school/humanities.jpg"));
		} 
		catch (IOException e) 
		{	e.printStackTrace();
		}	
	}
	
	public Image get(Tile t)
	{
		BufferedImage tile = null;
	
		switch (t.getType())
		{
			case CORNER:
			{	tile = corner;
				break;	
			}
			case GRASS:
			{	
				Random r = new Random();
				int choose = r.nextInt(10)+1;
			
				if(choose <= 8)
				{
					tile = land;
				}
				else if (choose == 9)
				{
					tile = flower;
				}
				else
				{
					tile = grass;
				}
				break;	
			}
			case LAND:
			{	tile = land;
				break;	
			}
			case FORK:
			{	tile = fork;
				break;	
			}
			case ROADH:
			{	tile = roadh;
				break;	
			}
			case ROADV:
			{	tile = roadv;
				break;	
			}
			case ROOF:
			{	tile = roof;
				break;	
			}
			case WALL:
			{	tile = wall;
				break;	
			}
			case DOOR:
			{	tile = door;
				break;
			}
			case TREE:
			{	if (t.getDirection() == Tile.Direction.UP) 
					tile = treeTop;
				else
					tile = treeTrunk;
				break;	
			}
			case SIGN:
			{
				switch (t.getDirection())
				{
					case LEFT: 
					{	tile = signMath;
						break;
					}
					case RIGHT:
					{	tile = signSci;
						break;
					}
					default:
					{	tile = signHum;
						break;
					}
				}		
				break;
			}
			default:
			{	
				tile = land;
				break;	
			}
		}
		return tile;
	}
}
