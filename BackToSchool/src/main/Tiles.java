package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Tiles 
{	
	private BufferedImage cornerUR, cornerUL, cornerDR, cornerDL, grass, land, forkU, forkL, forkR, forkD, roadh, roadv, roof, wall, windowL, windowR, windowU, door, treeTop, treeTrunk, 
				  signHum, signMath, signSci, signLibrary, signDorm, wallDorm, doorDorm, roofDorm, flower;
	
	public Tiles()
	{
		try 
		{	
			cornerUR = ImageIO.read(new File("art/school/cornerUR.jpg"));
			cornerUL = ImageIO.read(new File("art/school/cornerUL.jpg"));
			cornerDR = ImageIO.read(new File("art/school/cornerDR.jpg"));
			cornerDL = ImageIO.read(new File("art/school/cornerDL.jpg"));
			grass = ImageIO.read(new File("art/school/grass.jpg"));
			flower = ImageIO.read(new File("art/school/flower.jpg"));
			land = ImageIO.read(new File("art/school/land.jpg"));
			forkU = ImageIO.read(new File("art/school/forkU.jpg"));
			forkR = ImageIO.read(new File("art/school/forkR.jpg"));
			forkD = ImageIO.read(new File("art/school/forkD.jpg"));
			forkL = ImageIO.read(new File("art/school/forkL.jpg"));
			roadh = ImageIO.read(new File("art/school/road-side.jpg"));
			roadv = ImageIO.read(new File("art/school/road.jpg"));
			roof = ImageIO.read(new File("art/school/wallroof.jpg"));
			roofDorm = ImageIO.read(new File("art/school/dorm_roof.jpg")); 
			wall = ImageIO.read(new File("art/school/wall.jpg"));
			wallDorm = ImageIO.read(new File("art/school/dorm_wall.jpg")); 
			door = ImageIO.read(new File("art/school/door.jpg"));
			doorDorm = ImageIO.read(new File("art/school/dorm_door.jpg"));
			treeTop = ImageIO.read(new File("art/school/tree_top.jpg"));
			treeTrunk = ImageIO.read(new File("art/school/treetrunk.jpg"));
			signMath = ImageIO.read(new File("art/school/math_sign.jpg"));
			signSci = ImageIO.read(new File("art/school/science_sign.jpg"));
			signHum = ImageIO.read(new File("art/school/humanities.jpg"));
			signLibrary = ImageIO.read(new File("art/school/library_sign.jpg"));
			signDorm = ImageIO.read(new File("art/school/dorm_sign.jpg"));
			windowL = ImageIO.read(new File("art/school/window-1.jpg"));
			windowR = ImageIO.read(new File("art/school/window-2.jpg"));
			windowU = ImageIO.read(new File("art/school/window.jpg"));
			
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
			{	switch(t.getDirection())
				{
					case UP:	tile = cornerUR;
								break;
					case LEFT:	tile = cornerUL;
								break;
					case RIGHT: tile = cornerDR;
								break;
					case DOWN: 	tile = cornerDL;
				}
				break;
			}
			case GRASS:
			{	tile = grass;
				break;	
			}
			case LAND:
			{	tile = land;
				break;	
			}
			case FLOWER:
			{	tile = flower;
				break;
			}
			case FORK:
			{	switch(t.getDirection())
				{
					case UP:	tile = forkU;
								break;
					case LEFT:	tile = forkL;
								break;
					case RIGHT: tile = forkR;
								break;
					case DOWN: 	tile = forkD;
				}
				break;
			}
			case ROAD:
			{	if (t.getDirection() == Tile.Direction.UP)
					tile = roadv;
				else
					tile = roadv;
				break;	
			}
			case ROOF:
			{	if (t.getDirection() == Tile.Direction.RIGHT)
					tile = roofDorm;
				else
					tile = roof;
				break;	
			}
			case WALL:
			{	switch(t.getDirection())
				{
					case LEFT:	tile = signDorm;
								break;
					case RIGHT: tile = wallDorm;
								break;
					case DOWN: 	tile = doorDorm;
								break;
					default:	tile = wall;
				}
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
					case DOWN:
					{	tile = signLibrary;
						break;
					}
					default:
					{	tile = signHum;
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
