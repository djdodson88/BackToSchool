import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	public Transcript(Player player, Day day){
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		this.setBackground(Color.white);// color of background
		
		dayLabel = new JLabel("Day "+day.getDay());
		dayLabel.setBounds(0,0,50,50);
		
		creativityLabel = new JLabel(Double.toString(player.getCreativity()));
		creativityLabel.setBounds(100,100,50,50);
		
		sciRigorLabel = new JLabel(Double.toString(player.getSciRigor()));
		sciRigorLabel.setBounds(100,200,50,50);
		
		quantReasLabel = new JLabel(Double.toString(player.getQuantReasoning()));
		quantReasLabel.setBounds(100,300,50,50);
		
		
		
		
		this.add(dayLabel);
		this.add(creativityLabel);
		this.add(sciRigorLabel);
		this.add(quantReasLabel);
		
		setLayout(null);
		setVisible(true);
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
		exitButton.setBounds(100,500,100,50);
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
