package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Transcript extends JPanel{
	JLabel dayLabel;
	JLabel creativityLabel;
	JLabel sciRigorLabel;
	JLabel quantReasLabel;
	JLabel averageGPA;
	ImageIcon background;
	
	public Transcript(Player player, Day day){
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		this.setBackground(Color.white);// color of background
		
		dayLabel = new JLabel("Day "+day.getDay()+" Transcript");
		dayLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		dayLabel.setBounds(300,0,300,100);
		
		creativityLabel = new JLabel("Creativity: "+Double.toString(player.getCreativity()));
		creativityLabel.setBounds(300,100,300,100);
		creativityLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		sciRigorLabel = new JLabel("Scientific Rigor: "+Double.toString(player.getSciRigor()));
		sciRigorLabel.setBounds(300,180,300,100);
		sciRigorLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		quantReasLabel = new JLabel("Quantitative Reasoning: "+Double.toString(player.getQuantReasoning()));
		quantReasLabel.setBounds(300,260,300,100);
		quantReasLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		double aveGPA = (player.getCreativity()+player.getSciRigor()+player.getQuantReasoning())/3;
		averageGPA = new JLabel("Average GPA: "+ aveGPA);
		averageGPA.setBounds(300,350,300,100);
		averageGPA.setFont(new Font("Serif", Font.PLAIN, 25));
		background = new ImageIcon("art/transcript/transcript_bg.jpg");
		
		
		this.add(dayLabel);
		this.add(averageGPA);
		this.add(creativityLabel);
		this.add(sciRigorLabel);
		this.add(quantReasLabel);
		
		setLayout(null);
		setVisible(true);
		
	}
	
	public void paintComponent(Graphics g)
	{
		background.paintIcon(this,g,0,0);
	}
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Back To School: Battle Mode");
		Transcript transcript = new Transcript(new Player(), new Day(1));
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(battle);
		frame.setContentPane(transcript);
		frame.pack();
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(350,500,100,50);
		exitButton.setBackground(null);
		exitButton.setOpaque(false);
	//	exitButton.setBorder(null);
		
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
			
		});
		
		frame.add(exitButton);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Transcript(new Player(),new Day(1)); // Let the constructor do the job
			}
		});
		frame.setVisible(true);
	}

}
