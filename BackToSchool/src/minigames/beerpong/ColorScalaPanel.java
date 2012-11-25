package minigames.beerpong;

/**
 *
 * @author Cagatay Sahin
 */

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
    
public class ColorScalaPanel extends JPanel{
    
    public int width;
    public int height;
    private int labelCount;
    
    private float minHue;
    private float maxHue;
    private float hueStep;
    
    private int visibleCount;
    
    private float saturation;
    private float lumination;
    
    private Dimension labelDimension;
    private JLabel[] labelList ;
    
    public ColorScalaPanel(int wi ,int he, int la,  float mi, float ma, float sa, float lu){
        
        width = wi;
        height = he;
        setLabelCount(la);
        setMinHue(mi);
        setMaxHue(ma);
        setSaturation(sa);
        setLumination(lu);
        
        visibleCount = labelCount;
        hueStep = (maxHue - minHue) / labelCount; 
     
        setPreferredSize( new Dimension(width, height));
        setMinimumSize( new Dimension(width, height));
        setMaximumSize( new Dimension(width, height));
        setBackground(Color.WHITE);
        setLayout( new BoxLayout(this, BoxLayout.X_AXIS));
        
        labelDimension = new Dimension(width/labelCount, height);
        
        labelList = new JLabel[labelCount];
        
        for(int i = 0; i < labelCount; i++ )
        {
            System.out.println(hueStep * i);
            labelList[i] = new JLabel();
            labelList[i].setOpaque(true);
            labelList[i].setPreferredSize(labelDimension);
            labelList[i].setMaximumSize(labelDimension);
            labelList[i].setMinimumSize(labelDimension);
            
            labelList[i].setBackground( Color.getHSBColor( maxHue - hueStep * (i + 1) , saturation, lumination)  );        
            labelList[i].setVisible(true);
            
            this.add(labelList[i]);
        }
    }
    
    //Getter Methods
    public int getLabelCount(){
        return labelCount;
    }
    
    public float getMinHue(){
        return minHue;
    }
    
    public float getMaxHue(){
        return maxHue;
    }
    
    public float getSaturation(){
        return saturation;
    }
    
    public float getLumination(){
        return lumination;
    }
    
    //Setter Methods
    public void setVisibleCount(int _visibleCount ){
        if( _visibleCount < 0 || _visibleCount > labelCount )
        {
            System.err.println("Error: visibleCount is invalid");
        }
        else
        {
            visibleCount = _visibleCount;
            for(int i = 0; i < labelCount; i++ )
            {
                if( i < visibleCount)
                    labelList[i].setVisible(true);
                else
                    labelList[i].setVisible(false);
            }
        }
    }
    
    public void setVisibleAll(boolean visibility){
        for(int i = 0; i < labelCount; i++ )
        {
            labelList[i].setVisible(visibility);
        }
    }
    
        public void setLabelCount(int labelCount){
        this.labelCount = labelCount;
    }
    
    public void setMinHue( float _minHue){
        if( _minHue < 0 ) 
            minHue = 0;
        
        else if ( _minHue > 1)
            minHue = 1;
        
        else
            minHue = _minHue;
    }
    
    public void setMaxHue(float _maxHue){
        if( _maxHue < 0 ) 
            maxHue = 0;
        
        else if ( _maxHue > 1)
            maxHue = 1;
        
        else
            maxHue = _maxHue;
    }
    
    public void setSaturation(float _saturation){
        if( _saturation < 0)
            saturation = 0;
        
        else if( _saturation > 1 )
            saturation = 1;
        
        else
            saturation = _saturation;
    }
    
    public void setLumination(float _lumination){
        if( _lumination < 0)
            lumination = 0;
        
        else if( _lumination > 1)
            lumination = 1;
        
        else
            lumination = _lumination;
    }
} //End of class ColorScalaPanel

