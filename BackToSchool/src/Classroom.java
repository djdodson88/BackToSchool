import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import minigames.fruit.Fruit_Game;
import minigames.sudoku.SudokuGame;
import minigames.tiles.PuzzlePanel;
import minigames.warehouse.WareHouseKeeper;



public class Classroom extends JPanel{

	private BufferedImage backgroundImg;
	private static boolean done;
	
	public Classroom()
	{
		// Pass through day
		setup();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, null);

	}

	private void setup()
	{
		try {
			backgroundImg = ImageIO.read(new File("art/classroom/classroom.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{

		int game;
		
		Random generator = new Random();
		game = generator.nextInt(4) + 1; // 1-4
		JPanel miniSplash = new MiniSplash(game);
		JPanel minigame = new JPanel();

		game = 4; // Override random for testing
		
		JFrame frame = new JFrame("Back to School: Classroom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLayeredPane lpane = new JLayeredPane();
		frame.setPreferredSize(new Dimension(800,600));
		frame.setResizable(false);
		frame.add(lpane);
		lpane.setBounds(0, 0, 800, 600);

		JPanel classroom = new Classroom();

		
		classroom.setBounds(0, 0, 800, 600);
		miniSplash.setBounds(50,50, 500, 300);
		
		lpane.add(classroom);
		lpane.add(miniSplash);
		lpane.moveToFront(miniSplash);
		
		frame.pack();
		frame.setVisible(true);

		splashLoop((MiniSplash) miniSplash); // loops until splash screen finishes
		
		switch(game)
		{
		
		case 1:
			minigame = new Fruit_Game(9);
			break;
		case 2:
			//Warehouse
			minigame = new WareHouseKeeper(1); 
		
			break;
		case 3:
			minigame = new SudokuGame(7);
			break;
		case 4:
			//Tiles
			minigame = new PuzzlePanel(1); 
			break;
		}
		
		minigame.setBounds(26, 32, 550, 450);
		
		lpane.add(minigame);
		lpane.moveToFront(minigame);
		minigame.requestFocus();
	
	}
	
	public static void splashLoop(MiniSplash splash)
	{
		while(!splash.isDone())
		{
			System.out.print(""); // workaround for now
		}
		
	}
}
