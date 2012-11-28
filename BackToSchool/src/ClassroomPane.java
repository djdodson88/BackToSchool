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


public class ClassroomPane extends JLayeredPane{


	private BufferedImage backgroundImg;
	private static boolean done;
	private BackToSchool frame;
	
	public ClassroomPane()
	{
		setup();
		

		int game;
		
		Random generator = new Random();
		game = generator.nextInt(4) + 1; // 1-4
		JPanel miniSplash = new MiniSplash(game);
		JPanel minigame = new JPanel();

		game = 1; // Override random for testing
		
		this.setPreferredSize(new Dimension(800,600));
		
		this.setBounds(0, 0, 800, 600);

		miniSplash.setBounds(50,50, 500, 300);
		
		this.add(miniSplash);
		this.moveToFront(miniSplash);
		
		//splashLoop((MiniSplash) miniSplash); // loops until splash screen finishes
		
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
			minigame = new SudokuGame(2);
			break;
		case 4:
			//Tiles
			minigame = new PuzzlePanel(1); 
			break;
		}
		
		minigame.setBounds(26, 32, 550, 450);
		
		this.add(minigame);
		this.moveToFront(minigame);
		minigame.requestFocusInWindow();
	
	}

	protected void sendFrame(BackToSchool frame) 
	{
		this.frame = frame;
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
		
		JFrame frame = new JFrame("Back to School: Classroom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ClassroomPane());
	
		frame.setPreferredSize(new Dimension(800,600));
		frame.setVisible(true);
		frame.pack();
		
	}
	
	private void splashLoop(MiniSplash splash)
	{
		while(!splash.isDone())
		{
			System.out.print(""); // workaround for now
		}
		
	}
}
