import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	boolean optionSelected;
	boolean humanitiesSelected;
	boolean scienceSelected;
	boolean mathSelected;

	public Library(Player player){
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		this.setBackground(Color.white);// color of background
		setLayout(null);
		optionSelected=false;
		humanitiesSelected=false;
		scienceSelected=false;
		mathSelected=false;

		txt = new JLabel("Please select a skill level to increase");
		txt.setBounds(350,100,300,30);


		humanitiesButton=new JButton("Humanities");
		humanitiesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				if(!optionSelected){
					optionSelected=true;
					humanitiesSelected=true;
					System.out.println("Pressed Humanities");
				}
			}
		});
		scienceButton=new JButton("Science");
		scienceButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				if(!optionSelected){
					optionSelected=true;
					System.out.println("Pressed Science");
					scienceSelected = true;
				}
			}
		});
		mathButton=new JButton("Math");
		mathButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				if(!optionSelected){
					optionSelected=true;
					System.out.println("Pressed Science");
					mathSelected=true;
				}
			}			
		});

		humanitiesButton.setBounds(388,200,100,30); 
		scienceButton.setBounds(388,300,100,30); 
		mathButton.setBounds(388,400,100,30); 

		this.add(txt);
		this.add(humanitiesButton);
		this.add(scienceButton);
		this.add(mathButton);
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
