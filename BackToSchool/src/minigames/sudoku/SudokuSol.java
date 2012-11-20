package minigames.sudoku;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class SudokuSol {

	private int day;
	private File solutions;

	private ArrayList<int[][]> solutionKey;
	private int[][] entries4x4;
	private int[][] entries9x9;

	public SudokuSol(int lvl)
	{
		day = lvl;

		entries4x4 = new int[4][4];
		solutions = new File("misc/4x4Sol.txt");
		parseSol(solutions);

		//solutions should be populated

		Random n = new Random();
		int solIndex = n.nextInt(12); // 12 different solutions
		fillEntries(entries4x4, solIndex); // choose 1 of 12 solutions

		//entries9x9 = new int[8][8];
		//solutions  = new File("9x9Sol.txt");

	}


	public int[][] gridToShow()
	{
		int[][] grid = new int[8][8];


		grid = new int[4][4];

		int remove = 6; // number of elements to remove
		Random n = new Random();
		int i = 0;

		int[][] newAnswer = tempArray(entries4x4);

		while(i < remove)
		{
			int rowIndex = n.nextInt(4); 
			int colIndex = n.nextInt(4);


			if(newAnswer[colIndex][rowIndex] != 0)
			{
				newAnswer[colIndex][rowIndex] = 0; // 0 indicates it will be hidden
				i++;
			} 

		}


		grid = newAnswer;

		return grid;
	}


	public int[][] tempArray(int[][] array)
	{
		int[][] copy = new int[array.length][array[0].length];

		for(int i = 0; i < array.length; i++)
		{
			for(int j = 0; j < array[0].length; j++)
			{
				copy[i][j] = array[i][j];
			}
		}
		return copy;
	}
	public boolean compareAnswer(int[][] current)
	{


		int row = 0;

		while(row < 4)
		{
			for(int col = 0; col < 4; col++)
			{
				if(current[row][col] != entries4x4[row][col])
				{
					return false;
				}
			}
			row++;
		}
		return true;

	}

	private int[][] get4x4Grid()
	{
		return entries4x4;
	}

	private void fillEntries(int[][] entry, int index)
	{

		int[][] chosenSol = new int[4][4];

		chosenSol = solutionKey.get(index);

		for(int row = 0; row < 4; row++)
		{
			for(int col = 0; col < 4; col++)
			{
				entry[row][col] = chosenSol[row][col];
			}
		}


		// higher levels
		//int[][] chosenSol = new int[9][9];


	}

	private void parseSol(File file)
	{
		solutionKey = new ArrayList<int[][]>();

		try {

			Scanner s = new Scanner(file);

			while(s.hasNextInt())
			{

				int[][] sol = new int[4][4];

				for(int row = 0; row < 4; row++)
				{
					for(int col = 0; col < 4; col++)
					{
						sol[row][col] = s.nextInt();
					}
				}

				solutionKey.add(sol);
			}

		} catch (FileNotFoundException e) 
		{

			e.printStackTrace();
		}



	}


}
