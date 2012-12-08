package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Library extends JPanel {
	JLabel txt;

	JButton humanitiesButton;
	JButton scienceButton;
	JButton mathButton;
	JButton exitButton;
	BackToSchool frame;

	boolean optionSelected;
	boolean humanitiesSelected;
	boolean scienceSelected;
	boolean mathSelected;
	
	BufferedImage background;
	Player student; 

	public Library(Player player){
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		setup();
		
		
		setLayout(null);
		optionSelected=false;
		humanitiesSelected=false;
		scienceSelected=false;
		mathSelected=false;

		student = player;
	
		txt = new JLabel("Please choose a subject to checkout");
		
		txt.setFont(new Font("Courier", Font.PLAIN, 14));
		txt.setBounds(200,180,500,30);

		humanitiesButton=new JButton(new ImageIcon("art/library/library_hum.jpg"));
	
		humanitiesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				if(!optionSelected){
					optionSelected=true;
					humanitiesSelected=true;
					student.increaseCreativit(0.5);
					repaint();
				}
			}
		});
		scienceButton=new JButton(new ImageIcon("art/library/science_lib.jpg"));
		scienceButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				if(!optionSelected){
					optionSelected=true;
					student.increaseSciRigor(0.5); 
					scienceSelected = true;
					repaint();
				}
			}
		});
		mathButton=new JButton(new ImageIcon("art/library/math_lib.png"));
		mathButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				if(!optionSelected){
					optionSelected=true;
					student.increaseQuantReasoning(0.5);
					mathSelected=true;
					repaint();
				}
			}			
		});
		
		exitButton=new JButton(new ImageIcon("art/buttons/exit_btn.jpg"));
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				frame.switchPanel(BackToSchool.Screen.CAMPUS);
			}			
		});
		
		exitButton.setVisible(false);

		humanitiesButton.setBounds(200,225, 200,67); 
		scienceButton.setBounds(200,300,200,67); 
		mathButton.setBounds(200,375,200,67); 
		exitButton.setBounds(600,450,100,30);

		this.add(txt);
		this.add(humanitiesButton);
		this.add(scienceButton);
		this.add(mathButton);
		this.add(exitButton);
	}
	
	private void setup()
	{
		try {
			background = ImageIO.read(new File("art/library/library_view.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Player getBackPlayer(){
		return student;
	}
	
	public void getFrame(BackToSchool frame)
	{
		this.frame = frame;
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		
		if(optionSelected)
		{
			txt.setBounds(450,300,300,30);
			if(humanitiesSelected){
				txt.setText("+0.5 exp on Creativity");
			}
			else if(mathSelected)
			{
				txt.setText("+0.5 exp on Quantitive Reasoning");
			}
			else if(scienceSelected){
				txt.setText("+0.5 exp on Scientific Rigor");
			}
		
			exitButton.setVisible(true);
		}
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Back To School: Library");
		Player player = new Player();
		Library library = new Library(player);
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(battle);
		frame.setContentPane(library);
		frame.pack();
		frame.setVisible(true);
	}
}
