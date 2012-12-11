package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Point;
import main.Day.Course;
import main.Tile.Direction;
import main.Tile.Type;

public class Campus 
{
	private Tile[][] campus;
	private int WIDTH = 60, HEIGHT = 40;
	
	public Campus()
	{
		//TODO: create tool to make campus design easier
		campus = new Tile[WIDTH][HEIGHT];
		
		// generate randomized land tiles 
		for (int i=0; i<WIDTH; i++)
			for (int j=0; j<HEIGHT; j++)
			{
				int random = (int)(Math.random()*10);
				if (random == 2 || random == 9)
					campus[i][j] = new Tile(Tile.Type.GRASS);
				else if (random == 5)
					campus[i][j] = new Tile(Tile.Type.FLOWER);
				else
					campus[i][j] = new Tile(Tile.Type.LAND);
			}
		
		// generated code
		campus[0][16] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[0][17] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[1][2] = new Tile(Tile.Type.ROOF, Tile.Direction.RIGHT);
		campus[1][3] = new Tile(Tile.Type.WALL, Tile.Direction.RIGHT);
		campus[1][13] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[1][14] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[1][18] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[1][19] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[2][2] = new Tile(Tile.Type.ROOF, Tile.Direction.RIGHT);
		campus[2][3] = new Tile(Tile.Type.WALL, Tile.Direction.RIGHT);
		campus[2][16] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[2][17] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[2][27] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[2][28] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[3][0] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[3][1] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[3][2] = new Tile(Tile.Type.WALL, Tile.Direction.LEFT);
		campus[3][3] = new Tile(Tile.Type.WALL, Tile.Direction.DOWN);
		campus[3][4] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[3][12] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[3][13] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[3][18] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[3][19] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[3][20] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[3][27] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[3][28] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[3][29] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[4][2] = new Tile(Tile.Type.ROOF, Tile.Direction.RIGHT);
		campus[4][3] = new Tile(Tile.Type.WALL, Tile.Direction.RIGHT);
		campus[4][4] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[4][5] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[4][6] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[4][7] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[4][8] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[4][9] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[4][18] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[4][19] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[4][20] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[4][27] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[4][28] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[4][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[5][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[5][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[5][19] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[5][20] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[5][21] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[5][22] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[5][27] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[5][28] = new Tile(Tile.Type.WINDOW, Tile.Direction.LEFT);
		campus[5][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[5][35] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[5][36] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[6][2] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[6][3] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[6][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[6][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[6][8] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[6][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[6][19] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[6][20] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[6][22] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[6][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[6][35] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[6][36] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[7][2] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[7][3] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[7][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[7][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[7][8] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[7][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[7][18] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[7][19] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[7][20] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[7][22] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[7][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[7][35] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[7][36] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[7][37] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[8][0] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[8][1] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[8][2] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[8][3] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[8][4] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[8][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[8][8] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[8][9] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[8][22] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[8][23] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[8][24] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[8][25] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[8][26] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[8][27] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[8][28] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[8][29] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[8][35] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[8][36] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[8][37] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[9][2] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[9][3] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[9][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[9][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[9][8] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[9][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[9][22] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[9][24] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[9][25] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[9][26] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[9][27] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[9][28] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[9][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[9][35] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[9][36] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[9][37] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[10][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[10][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[10][22] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[10][23] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][24] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][25] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][26] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][27] = new Tile(Tile.Type.FORK, Tile.Direction.RIGHT);
		campus[10][28] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][29] = new Tile(Tile.Type.FORK, Tile.Direction.LEFT);
		campus[10][30] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][31] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][32] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][33] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][34] = new Tile(Tile.Type.FORK, Tile.Direction.RIGHT);
		campus[10][35] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][36] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[10][37] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[11][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[11][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[11][8] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[11][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[11][12] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[11][13] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[11][22] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[11][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[11][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[12][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[12][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[12][8] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[12][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[12][12] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[12][13] = new Tile(Tile.Type.WINDOW, Tile.Direction.LEFT);
		campus[12][21] = new Tile(Tile.Type.CORNER, Tile.Direction.RIGHT);
		campus[12][22] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[12][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[12][32] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[12][33] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[12][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[13][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[13][6] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[13][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[13][8] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[13][9] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[13][11] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[13][12] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[13][13] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[13][14] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[13][21] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[13][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[13][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[13][35] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[13][36] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[14][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[14][7] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[14][8] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[14][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[14][12] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[14][13] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[14][14] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[14][15] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[14][16] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[14][17] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[14][18] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[14][19] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[14][20] = new Tile(Tile.Type.FORK, Tile.Direction.RIGHT);
		campus[14][21] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[14][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[14][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[15][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[15][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[15][14] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[15][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[15][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[15][29] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[15][30] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[15][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[16][4] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[16][5] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[16][6] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[16][7] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[16][8] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[16][9] = new Tile(Tile.Type.FORK, Tile.Direction.LEFT);
		campus[16][10] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[16][11] = new Tile(Tile.Type.FORK, Tile.Direction.RIGHT);
		campus[16][12] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[16][13] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[16][14] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[16][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[16][25] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[16][26] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[16][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[16][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[16][36] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[16][37] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[17][1] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[17][2] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[17][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[17][11] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[17][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[17][25] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[17][26] = new Tile(Tile.Type.WINDOW, Tile.Direction.LEFT);
		campus[17][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[17][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[18][1] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[18][2] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[18][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[18][11] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[18][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[18][25] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[18][26] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[18][27] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[18][31] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[18][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[19][1] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[19][2] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[19][3] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[19][4] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[19][6] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[19][7] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[19][11] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[19][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[19][25] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[19][26] = new Tile(Tile.Type.WINDOW, Tile.Direction.RIGHT);
		campus[19][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[19][30] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[19][31] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[19][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[20][1] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[20][2] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[20][11] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[20][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[20][25] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[20][26] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[20][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[20][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[21][1] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[21][2] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[21][11] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[21][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[21][27] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[21][28] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[21][29] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[21][30] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[21][31] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[21][32] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[21][33] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[21][34] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[22][11] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[22][12] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[22][13] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[22][14] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[22][15] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[22][16] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[22][17] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[22][18] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[22][19] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[22][20] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[22][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[22][32] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[22][33] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[22][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[23][5] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[23][6] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[23][11] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[23][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[23][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[23][32] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[23][33] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[23][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[24][11] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[24][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[24][22] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[24][23] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[24][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[24][32] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[24][33] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[24][34] = new Tile(Tile.Type.FORK, Tile.Direction.LEFT);
		campus[24][35] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[24][36] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[25][8] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[25][9] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[25][10] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[25][11] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[25][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[25][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[25][32] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[25][33] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[25][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[26][8] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[26][9] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[26][10] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[26][11] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[26][17] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[26][18] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[26][20] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[26][27] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[26][32] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[26][33] = new Tile(Tile.Type.WINDOW, Tile.Direction.LEFT);
		campus[26][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[27][8] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[27][9] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[27][10] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[27][17] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[27][18] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[27][19] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[27][20] = new Tile(Tile.Type.FORK, Tile.Direction.LEFT);
		campus[27][21] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[27][22] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[27][23] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[27][24] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[27][25] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[27][26] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[27][27] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[27][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[28][17] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[28][18] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[28][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[28][37] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[28][38] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[29][2] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[29][3] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[29][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[30][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[31][8] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[31][9] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[31][30] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[31][31] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[31][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[32][27] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[32][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[33][3] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[33][4] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[33][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[34][3] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[34][4] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[34][34] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[34][35] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[34][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[35][3] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[35][4] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[35][5] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[35][6] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[35][7] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[35][8] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[35][9] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[35][22] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[35][23] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[35][34] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[35][35] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[35][36] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[36][3] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[36][4] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[36][8] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[36][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[36][21] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[36][22] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[36][23] = new Tile(Tile.Type.WINDOW, Tile.Direction.LEFT);
		campus[36][27] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[36][28] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[36][34] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[36][35] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[37][3] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[37][4] = new Tile(Tile.Type.WINDOW, Tile.Direction.LEFT);
		campus[37][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[37][12] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[37][13] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[37][21] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[37][22] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[37][23] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[37][24] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[37][25] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[37][26] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[37][27] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[37][28] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[37][29] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[38][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[38][12] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[38][13] = new Tile(Tile.Type.WINDOW, Tile.Direction.RIGHT);
		campus[38][22] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[38][23] = new Tile(Tile.Type.WINDOW, Tile.Direction.RIGHT);
		campus[38][25] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[38][27] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[38][28] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[38][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[39][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[39][12] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[39][13] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[39][14] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[39][15] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[39][16] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[39][17] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[39][21] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[39][22] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[39][23] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[39][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[39][32] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[39][33] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[40][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[40][12] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[40][13] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[40][17] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[40][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[41][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[41][17] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[41][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[42][6] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[42][7] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[42][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[42][17] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[42][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[43][6] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[43][7] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[43][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[43][17] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[43][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[44][6] = new Tile(Tile.Type.SIGN, Tile.Direction.UP);
		campus[44][7] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[44][8] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[44][9] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[44][17] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[44][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[45][6] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[45][7] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[45][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[45][17] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[45][24] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[45][25] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[45][27] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[45][28] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[45][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[45][31] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[45][32] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[45][34] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[46][6] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[46][7] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[46][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[46][15] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[46][16] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[46][17] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[46][26] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[46][27] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[46][28] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[46][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[46][31] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[46][32] = new Tile(Tile.Type.WINDOW, Tile.Direction.LEFT);
		campus[46][34] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[47][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[47][14] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[47][15] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[47][16] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[47][17] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[47][26] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[47][27] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[47][28] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[47][29] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[47][31] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[47][32] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[47][33] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[47][34] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[48][9] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[48][15] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[48][16] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[48][17] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[48][22] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[48][23] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[48][27] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[48][28] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[48][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[48][31] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[48][32] = new Tile(Tile.Type.WINDOW, Tile.Direction.RIGHT);
		campus[48][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[49][6] = new Tile(Tile.Type.CORNER, Tile.Direction.RIGHT);
		campus[49][7] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[49][8] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[49][9] = new Tile(Tile.Type.FORK, Tile.Direction.LEFT);
		campus[49][10] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[49][11] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[49][12] = new Tile(Tile.Type.FORK, Tile.Direction.RIGHT);
		campus[49][13] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[49][14] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[49][15] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[49][16] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[49][17] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[49][27] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[49][28] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[49][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[49][31] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[49][32] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[49][34] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[49][35] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[49][36] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[50][1] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[50][2] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[50][6] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[50][12] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[50][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[50][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[50][35] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[50][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[51][6] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[51][10] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[51][11] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[51][12] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[51][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[51][32] = new Tile(Tile.Type.CORNER, Tile.Direction.RIGHT);
		campus[51][33] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[51][34] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[51][35] = new Tile(Tile.Type.FLOWER, Tile.Direction.DOWN);
		campus[51][36] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[52][6] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[52][10] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[52][11] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[52][12] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[52][29] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[52][30] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[52][31] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[52][32] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[52][34] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[52][35] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[52][36] = new Tile(Tile.Type.FORK, Tile.Direction.LEFT);
		campus[52][37] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[52][38] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[52][39] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[53][6] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[53][10] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[53][11] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[53][12] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[53][26] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[53][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[53][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[53][39] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[54][4] = new Tile(Tile.Type.CORNER, Tile.Direction.RIGHT);
		campus[54][5] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[54][6] = new Tile(Tile.Type.FORK, Tile.Direction.LEFT);
		campus[54][7] = new Tile(Tile.Type.CORNER, Tile.Direction.UP);
		campus[54][12] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[54][26] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[54][27] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[54][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[54][31] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[54][32] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[54][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[54][39] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[55][1] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[55][2] = new Tile(Tile.Type.WINDOW, Tile.Direction.LEFT);
		campus[55][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[55][5] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[55][6] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[55][7] = new Tile(Tile.Type.FORK, Tile.Direction.DOWN);
		campus[55][8] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[55][9] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[55][10] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[55][11] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[55][12] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[55][26] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[55][27] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[55][29] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[55][31] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[55][32] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[55][34] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[55][37] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[55][38] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[55][39] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[56][1] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[56][2] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[56][3] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[56][4] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[56][5] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[56][6] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[56][7] = new Tile(Tile.Type.FORK, Tile.Direction.UP);
		campus[56][18] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[56][19] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[56][26] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[56][27] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[56][28] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[56][29] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[56][31] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[56][32] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[56][33] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[56][34] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[56][37] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[56][38] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[56][39] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[57][1] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[57][2] = new Tile(Tile.Type.WINDOW, Tile.Direction.RIGHT);
		campus[57][4] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[57][5] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[57][6] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[57][7] = new Tile(Tile.Type.ROAD, Tile.Direction.LEFT);
		campus[57][23] = new Tile(Tile.Type.TREE, Tile.Direction.UP);
		campus[57][24] = new Tile(Tile.Type.TREE, Tile.Direction.DOWN);
		campus[57][26] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[57][27] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[57][28] = new Tile(Tile.Type.GRASS, Tile.Direction.DOWN);
		campus[57][31] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[57][32] = new Tile(Tile.Type.WINDOW, Tile.Direction.RIGHT);
		campus[57][37] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[57][38] = new Tile(Tile.Type.DOOR, Tile.Direction.UP);
		campus[57][39] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[58][4] = new Tile(Tile.Type.CORNER, Tile.Direction.DOWN);
		campus[58][5] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[58][6] = new Tile(Tile.Type.ROAD, Tile.Direction.UP);
		campus[58][7] = new Tile(Tile.Type.CORNER, Tile.Direction.LEFT);
		campus[58][26] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[58][27] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[58][31] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[58][32] = new Tile(Tile.Type.WALL, Tile.Direction.UP);
		campus[58][37] = new Tile(Tile.Type.ROOF, Tile.Direction.UP);
		campus[58][38] = new Tile(Tile.Type.WALL, Tile.Direction.UP);



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
		if (xIndex<0 || xIndex >= WIDTH)
			return false;
		if (yIndex<0 || yIndex >= HEIGHT)
			return false;
		
		switch (campus[xIndex][yIndex].getType()) 
		{
			case GRASS:
			case LAND:
			case FLOWER:
			case ROAD:
			case FORK:
			case CORNER:
				return true;
			default:
				return false;
		}
	}
	
	public ArrayList<Point> getDoors(boolean refresh)
	{
		ArrayList<Point> doors = new ArrayList<Point>();
		
		for (int i=0; i<WIDTH; i++)
			for (int j=0; j<HEIGHT; j++)
				if (campus[i][j].getType() == Tile.Type.DOOR)
				{	
					doors.add(new Point(i,j));
					if (refresh)
						campus[i][j-1] = new Tile(Tile.Type.ROOF);
				}
		
		return doors;
	}
	
	protected void addSign(Point door, Course course)
	{
		Tile tile = null;
		
		switch(course)
		{
			case HUMANITIES:
				tile = new Tile(Tile.Type.SIGN);
				break;
			case MATH:
				tile = new Tile(Tile.Type.SIGN, Tile.Direction.LEFT);
				break;
			default:
				tile = new Tile(Tile.Type.SIGN, Tile.Direction.RIGHT);
				break;
		}
		
		campus[door.x][door.y-1] = tile;
	}
	
	protected void addLibrary(Point library) 
	{
		campus[library.x][library.y-1] = new Tile(Tile.Type.SIGN, Tile.Direction.DOWN);
	}

	protected boolean addLand(Point point)
	{
		if (point.x < 0 || point.x >= WIDTH || point.y < 0 || point.y >= HEIGHT)
			return false;
		else
		{	// random land tile (same as in constructor)
			int random = (int)(Math.random()*10);
			if (random == 2 || random == 9)
				campus[point.x][point.y] = new Tile(Tile.Type.GRASS);
			else if (random == 5)
				campus[point.x][point.y] = new Tile(Tile.Type.FLOWER);
			else
				campus[point.x][point.y] = new Tile(Tile.Type.LAND);
			return true;
		}
	}
	
	protected boolean changeTile(Tile tile, Point point)
	{
		if (point.x < 0 || point.x >= WIDTH || point.y < 0 || point.y >= HEIGHT)
			return false;
		else
		{	campus[point.x][point.y] = tile;
			return true;
		}
	}
	
	public ArrayList<Point> getAdjacent(Point current) 
	{
		ArrayList<Point> adjacent = new ArrayList<Point>();
		
		if (isTraversable(current.x-1,current.y))
			adjacent.add(new Point(current.x-1,current.y));
		if (isTraversable(current.x+1,current.y))
			adjacent.add(new Point(current.x+1,current.y));	
		if (isTraversable(current.x,current.y-1))
			adjacent.add(new Point(current.x,current.y-1));
		if (isTraversable(current.x,current.y+1))
			adjacent.add(new Point(current.x,current.y+1));
		
		return adjacent;
	}
	
	public String getConstructor()
	{
		String output = "";
		
		for (int i=0; i<WIDTH; i++)
			for (int j=0; j<HEIGHT; j++)
			{	Tile.Type type = campus[i][j].getType();
				if ((type == Type.GRASS || type == Type.LAND || type == Type.FLOWER) && campus[i][j].getDirection()==Direction.DOWN)
					output += "campus["+i+"]["+j+"] = " + campus[i][j] + ";\n";
				else if (type != Type.GRASS && type != Type.LAND && type != Type.FLOWER)
					output += "campus["+i+"]["+j+"] = " + campus[i][j] + ";\n";
			}
		return output;
	}
}
