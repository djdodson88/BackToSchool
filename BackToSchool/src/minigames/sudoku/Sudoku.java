package minigames.sudoku;

import java.awt.*;
import javax.swing.*;


public class Sudoku extends JFrame{

	private static int day;
	
	public static void main(String[] args)
	{
		day = Integer.parseInt(args[0]);
		
	      javax.swing.SwingUtilities.invokeLater(new Runnable() {
		         public void run() {
		JFrame frame = new JFrame("");
		frame.setPreferredSize(new Dimension(550,450));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SudokuGame(7));
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		
		         }
	      });

	}
}
