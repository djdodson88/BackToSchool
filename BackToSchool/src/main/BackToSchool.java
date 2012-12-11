package main;

import java.awt.CardLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BackToSchool extends JFrame
{
	public enum Screen{CAMPUS, BATTLE, FINALBATTLE, CLASS, MINISPLASH, WELCOME, LIBRARY, TRANSCRIPT, ABOUT};
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private CampusPanel campus;
	private Battle battle;
	private FinalBattle finalBattle;
	private ClassroomPanel classroom;
	private MiniSplashPane miniSplash;
	private Welcome welcome;
	private Transcript transcript;
	private Library library;
	private About about;
	
	public BackToSchool(CardLayout layout, JPanel panel)
	{
		super("Back 2 School");
		
		campus = null;
		battle = null;
		finalBattle = null;
		classroom = null;
		miniSplash = null;
		welcome = null;
		transcript = null;
		about = null;
		library = null;
		
		cardLayout = layout;
		cardPanel = panel;
	}
	
	// add panels to the CardLayout panel through this
	protected void addPanel(JComponent panel, Screen screen)
	{
		cardPanel.add(panel, screen.name());
		
		switch (screen)
		{	case CAMPUS:
				campus = (CampusPanel)panel;
				campus.sendFrame(this);
				break;
			case CLASS:
				classroom = (ClassroomPanel) panel;
				break;
			case MINISPLASH:
				miniSplash = (MiniSplashPane)panel;
				miniSplash.sendFrame(this);
				break;
			case BATTLE:
				battle = (Battle)panel;
				battle.sendFrame(this);
				break;
			case FINALBATTLE:
				finalBattle = (FinalBattle)panel;
				finalBattle.sendFrame(this);
				break;
			case WELCOME:
				welcome  = (Welcome)panel;
				welcome.sendFrame(this);
				break;
			case LIBRARY:
				library = (Library)panel;
				library.sendFrame(this);
				break;
			case TRANSCRIPT:
				transcript = (Transcript)panel;
				transcript.sendFrame(this);
				break;
			case ABOUT:
				about = (About)panel;
				about.sendFrame(this);
				break;
		}	
	}
	
	// Call from panels to switch (can also add panel-specific setup method calls here)
	public void switchPanel(Screen screen)
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
			case MINISPLASH:
				miniSplash.requestFocus();
				break;
			case BATTLE:
				battle.requestFocus();
				break;
			case FINALBATTLE:
				finalBattle.requestFocus();
				break;
			case WELCOME: 
				campus.reset();
				welcome.requestFocus();
				break;
			case LIBRARY:
				library.requestFocus();
				break;
			case TRANSCRIPT:
				transcript.requestFocus();
				break;
			case ABOUT:
				about.requestFocus();
				break;
		}
		
	}
	
	public static void main(String[] args)
	{
		CardLayout layout = new CardLayout();
		JPanel cards = new JPanel(layout);
		BackToSchool frame = new BackToSchool(layout, cards);
		
		frame.addPanel(new Welcome(), Screen.WELCOME);
		frame.addPanel(new About(), Screen.ABOUT);
		frame.addPanel(new CampusPanel(), Screen.CAMPUS);
		frame.setContentPane(cards);
		
		// Testing
		int welcome = 0;	
		welcome = 1; //uncomment if you want to see welcome screen
		
		if(welcome == 1)
		{
			frame.switchPanel(Screen.WELCOME); 	
		}
		else
		{
			frame.switchPanel(Screen.CAMPUS);	
		}
			
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
