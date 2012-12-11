
package minigames.beerpong;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author CagataySahin
 */
    
public class BeerPanel extends JPanel{
    
    ColorScalaPanel p_scala;
    JLabel [] list;
    JLabel beerLabel;
    Dimension d;
    
    public BeerPanel(){
        
        setPreferredSize(new Dimension(TopViewPanel.WIDTH, 50));
        setBackground(Color.BLACK);
        setLayout( new BoxLayout(this, BoxLayout.X_AXIS));
        
        p_scala = new ColorScalaPanel(500, 50, 10, (float) 0.1, (float) 0.2, (float)  1, (float)  1);
        
        repaint();
        revalidate();
        
        beerLabel = new JLabel( new ImageIcon("art/beerpong/beer1.jpg") );
        p_scala.setAlignmentY(Component.CENTER_ALIGNMENT);
        beerLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        add(beerLabel);
        add(p_scala);
    }
    
}
