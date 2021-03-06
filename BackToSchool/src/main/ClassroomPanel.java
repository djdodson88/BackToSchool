package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import minigames.beerpong.BeerPong;
import minigames.fruit.Fruit_Game;
import minigames.sudoku.SudokuGame;
import minigames.tiles.PuzzlePanel;
import minigames.warehouse.WareHouseKeeper;


public class ClassroomPanel extends JLayeredPane
{
	private BufferedImage backgroundImg;
	private static boolean done;
	private int game;
	private int dayCnt;
	
	private SudokuGame sudoku;
	private Fruit_Game fruit;
	private WareHouseKeeper warehouse;
	private PuzzlePanel tiles;
	private BeerPong beer;
	
	private JButton default_exit; //for testing day structure, etc
	
	public ClassroomPanel(Player player, int game, Day day, BackToSchool frame)
	{
		System.out.println("new classroom");
		dayCnt = day.getDay();
		
		setup();
		
		JPanel minigame = new JPanel();
		
		setLayout(null);
		this.setPreferredSize(new Dimension(800,600));
		this.setBounds(0, 0, 800, 600);

		//game = 5;		// for testing (doesn't change splash screens preceeding, of course)
		switch(game)
		{
		
		case 1:
			fruit = new Fruit_Game(player, day, frame);
			minigame = fruit;
			break;
		case 2:
			//Warehouse
			warehouse = new WareHouseKeeper(player, day, frame);
			minigame = warehouse;
		
			break;
		case 3:
			sudoku = new SudokuGame(player, day, frame);
			minigame = sudoku;
			break;
		case 4:
			//Tiles
			tiles = new PuzzlePanel(player, day, frame);
			minigame = tiles;
			break;
		case 5:
			//Beer Pong
			// TO DO: Day parameter to control difficulty
			beer = new BeerPong(player, day, frame);
			minigame = beer;
			break;
		}
		
		minigame.setBounds(26, 32, 550, 450);
		
		add(minigame);
		moveToFront(minigame);
		minigame.requestFocusInWindow();
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
		
		//frame.add(new ClassroomPanel(game, 1));
	
		frame.setPreferredSize(new Dimension(800,600));
		frame.setVisible(true);
		frame.pack();
		
	}
	

}
