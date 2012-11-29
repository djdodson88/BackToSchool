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

import minigames.beerpong.BeerPong;
import minigames.fruit.Fruit_Game;
import minigames.sudoku.SudokuGame;
import minigames.tiles.PuzzlePanel;
import minigames.warehouse.WareHouseKeeper;


public class ClassroomPanel extends JPanel{


	private BufferedImage backgroundImg;
	private static boolean done;
	private BackToSchool frame;
	private int game;
	
	public ClassroomPanel(int game, int day)
	{

		setup();
		
		JPanel minigame = new JPanel();

		//game = 2; // Override random for testing
		
		setLayout(null);
		this.setPreferredSize(new Dimension(800,600));
		
		this.setBounds(0, 0, 800, 600);

		switch(game)
		{
		
		case 1:
			minigame = new Fruit_Game(day);
			break;
		case 2:
			//Warehouse
			minigame = new WareHouseKeeper(day); 
		
			break;
		case 3:
			minigame = new SudokuGame(day);
			break;
		case 4:
			//Tiles
			minigame = new PuzzlePanel(day); 
			break;
		case 5:
			//Beer Pong
			minigame = new BeerPong();
			break;
		}
		
		minigame.setBounds(26, 32, 550, 450);
		
		this.add(minigame);
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
		int game = 1;
		
		frame.add(new ClassroomPanel(game, 1));
	
		frame.setPreferredSize(new Dimension(800,600));
		frame.setVisible(true);
		frame.pack();
		
	}
	

}
