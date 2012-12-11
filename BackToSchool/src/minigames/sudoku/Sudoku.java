package minigames.sudoku;

import java.awt.*;
import javax.swing.*;
import main.BackToSchool;
import main.Day;
import main.Player;


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
		
		
		CardLayout layout = new CardLayout();
		JPanel cards = new JPanel(layout);
		BackToSchool b2sFrame = new BackToSchool(layout, cards);
		frame.add(new SudokuGame(new Player(), new Day(5), b2sFrame));
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		
		         }
	      });

	}
}
