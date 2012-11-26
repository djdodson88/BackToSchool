import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BackToSchool extends JFrame
{
	protected enum Screen{CAMPUS, BATTLE, FINALBATTLE, CLASS};
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private CampusPanel campus;
	private Battle battle;
	private FinalBattle finalBattle;
	private Classroom classroom;
	
	public BackToSchool(CardLayout layout, JPanel panel)
	{
		super("Back 2 School");
		
		campus = null;
		battle = null;
		finalBattle = null;
		classroom = null;
		cardLayout = layout;
		cardPanel = panel;	
	}
	
	// add panels to the CardLayout panel through this
	protected void addPanel(JPanel panel, Screen screen)
	{
		cardPanel.add(panel, screen.name());
		
		switch (screen)
		{	case CAMPUS:
				campus = (CampusPanel)panel;
				campus.sendFrame(this);
				break;
			case CLASS:
				classroom = (Classroom)panel;
				//classroom.sendFrame(this);
				break;
			case BATTLE:
				battle = (Battle)panel;
				battle.sendFrame(this);
				break;
			case FINALBATTLE:
				finalBattle = (FinalBattle)panel;
				//finalBattle.sendFrame(this);
				break;
		}	
	}
	
	// Call from panels to switch (can also add panel-specific setup method calls here)
	protected void switchPanel(Screen screen)
	{
		cardLayout.show(cardPanel, screen.name());
		switch (screen)
		{	case CAMPUS:
				campus.requestFocus();
				campus.continueClasses();
				break;
			case CLASS:
				classroom.requestFocus();
				break;
			case BATTLE:
				battle.requestFocus();
				break;
			case FINALBATTLE:
				finalBattle.requestFocus();
				break;
		}		
	}
	
	public static void main(String[] args)
	{
		CardLayout layout = new CardLayout();
		JPanel cards = new JPanel(layout);
		BackToSchool frame = new BackToSchool(layout, cards);
		
		frame.addPanel(new CampusPanel(), Screen.CAMPUS);
		frame.setContentPane(cards);
		frame.switchPanel(Screen.CAMPUS);
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
