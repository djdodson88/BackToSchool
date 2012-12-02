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


public class ClassroomPanel extends JPanel{


	private BufferedImage backgroundImg;
	private static boolean done;
	private BackToSchool frame;
	private int game;
	private int dayCnt;
	
	private SudokuGame sudoku;
	private Fruit_Game fruit;
	private WareHouseKeeper warehouse;
	private PuzzlePanel tiles;
	private BeerPong beer;
	
	private Player student;
	
	private JButton default_exit; //for testing day structure, etc
	
	public ClassroomPanel(Player player, int game, Day day, BackToSchool frame)
	{

		this.frame = frame;
		student = player;
		dayCnt = day.getDay();
		
		setup();
		
		JPanel minigame = new JPanel();
		
		setLayout(null);
		this.setPreferredSize(new Dimension(800,600));
		this.setBounds(0, 0, 800, 600);

		switch(game)
		{
		
		case 1:
			fruit = new Fruit_Game(dayCnt);
			fruit.getFrame(frame);		
			minigame = fruit;
			break;
		case 2:
			//Warehouse
			warehouse = new WareHouseKeeper(dayCnt);
			minigame = warehouse;
		
			break;
		case 3:
			sudoku = new SudokuGame(dayCnt);
			sudoku.getPlayer(student);
			sudoku.getClassSubject(day.getNextClass());
			
			sudoku.getFrame(frame);
			minigame = sudoku;
			
			break;
		case 4:
			//Tiles
			tiles = new PuzzlePanel(dayCnt);
			minigame = tiles;
			break;
		case 5:
			//Beer Pong
			// TO DO: Day parameter to control difficulty
			beer = new BeerPong();
			minigame = beer;
			break;
		}
		
		minigame.setBounds(26, 32, 550, 450);
		
		this.add(minigame);
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
