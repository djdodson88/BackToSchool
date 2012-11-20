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

		Random n = new Random();
		
		if(day <= 6)
		{
			entries4x4 = new int[4][4];
			solutions = new File("misc/4x4Sol.txt");
			parseSol(solutions);

			//solutions should be populated

			
			int solIndex = n.nextInt(12); // 12 different solutions
			fillEntries(entries4x4, solIndex); // choose 1 of 12 solutions
		}
		else
		{
			entries9x9 = new int[9][9];
			solutions  = new File("misc/9x9Sol.txt");
			parseSol(solutions);
			int solIndex = n.nextInt(6);
			fillEntries(entries9x9, solIndex);
			
		}


	}


	public int[][] gridToShow()
	{
		int[][] grid = new int[9][9];

		if(day <= 6)
		{
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
		}
		else
		{
			int remove = 25;
			Random n  = new Random();
			
			int[][] newAnswer = tempArray(entries9x9);
			
			int i = 0;
			
			while(i < remove)
			{
				int rowIndex = n.nextInt(9);
				int colIndex = n.nextInt(9);
				
				if(newAnswer[rowIndex][colIndex] != 0)
				{
					newAnswer[rowIndex][colIndex] = 0;
					i++;
				}
			}
			grid = newAnswer;
		}
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

		if(day <= 6)
		{
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
		else
		{
			while(row < 9)
			{
				for(int col = 0; col < 9; col++)
				{
					if(current[row][col] != entries9x9[row][col])
					{
						return false;
					}
				}
				row++;
			}

		}
		return true;
	}

	private int[][] get4x4Grid()
	{
		return entries4x4;
	}

	private int[][] get9x9Grid()
	{
		return entries9x9;

	}

	private void fillEntries(int[][] entry, int index)
	{
		int i = 0;
		
		if(day <= 6)
		{
			i = 4;
		}
		else
		{
			i = 9;
		}
		
		int[][] chosenSol = new int[i][i];

		chosenSol = solutionKey.get(index);

		for(int row = 0; row < i; row++)
		{
			for(int col = 0; col < i; col++)
			{
				entry[row][col] = chosenSol[row][col];
			}
		}



	}

	private void parseSol(File file)
	{
		solutionKey = new ArrayList<int[][]>();

		int index = 0;

		if(day <= 6)
		{
			index = 4;
		}
		else
		{
			index = 9;
		}

		try {

			Scanner s = new Scanner(file);

			while(s.hasNextInt())
			{

				int[][] sol; 

				sol = new int[index][index];

				for(int row = 0; row < index; row++)
				{
					for(int col = 0; col < index; col++)
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
