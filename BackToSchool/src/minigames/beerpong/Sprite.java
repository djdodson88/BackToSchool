
package minigames.beerpong;

/**
 *
 * @author Cagatay Sahin
 */
public class Sprite {
    
    //!!Tentative!!
    boolean hit;
    boolean out;
    
    final static int PRECISION = 10000;
            
    private int radius;
    
    private int center_x;
    private int center_y;
    private int center_z;
    
    private int speed_x;
    private int speed_y;
    private int speed_z;
    
    private int acceleration_x;
    private int acceleration_y;
    private int acceleration_z;
    
    //Default constructor
    public Sprite(){
        out = false;
        hit = false;
    }
    
    
    public Sprite( int r, int c_x, int c_y, int c_z, int s_x, int s_y, int s_z  ){
        out = false;
        hit = false;
        
        radius = r;
        center_x = c_x * PRECISION;
        center_y = c_y * PRECISION;
        center_z = c_z * PRECISION;
        speed_x = s_x;
        speed_y = s_y;
        speed_z = s_z;
    }
    
    //Returns the distance between the centers of this and other sprite
    public double getDistance( Sprite other)
    {
        int xDist = (center_x/PRECISION - other.getCenterX());
        int yDist = (center_y/PRECISION - other.getCenterY());
        return  Math.sqrt( xDist * xDist + yDist * yDist );
    }
    
    public void move(){
     
        center_x += speed_x;
        center_y += speed_y;
        center_z += speed_z;
        
        //If the spirit hits an x border (x < 0 )
        if( getCenterX() < 0 || getCenterX() > TablePanel.WIDTH )
        {
            System.out.println("MISS X");
            out = true;
            //speed_x = speed_x * -1;
        }
        else
        {
            
            speed_x += acceleration_x;
        }
        
        //If the spirit hits a y border (y < 0 )
        if( getCenterY() < 0 || getCenterY() > TablePanel.HEIGHT )
        {
            out = true;
            System.out.println("MISS Y");
            //speed_y = speed_y * -1;
        }
        else
        {
            speed_y += acceleration_y;
        }
        
        //If the spirit hits a z border (z < 0 )
        if( center_z < 0 )
        {
            
            speed_z = speed_z * -1;
        }
        else
        {
            speed_z += acceleration_z;
        }
        
    }
    
    //Getter methods
    public int getRadius(){
        return radius + getCenterZ() / 2;
    }
    
    public int getCenterX(){
    
        return center_x / PRECISION;
    }
    
    public int getCenterY(){
        return center_y / PRECISION;
    }
    
    public int getCenterZ(){
        return center_z / PRECISION;
    }
    
    public int getSpeedX(){
        return speed_x;
    }
    
    public int getSpeedY(){
        return speed_y;
    }
    
    public int getSpeedZ(){
        return speed_z;
    }
    
    public int getAccelerationX(){
        return acceleration_x;
    }
    
    public int getAccelerationY(){
        return acceleration_y;
    }
    
    public int getAccelerationZ(){
        return acceleration_z;
    }
    
    public boolean getOut(){
        return out;
    }
    
    public boolean getHit(){
        return hit;
    }
    
    //Setter methods
    public void setRadius( int radius ){
        this.radius = radius; 
    }
    
    public void setCenterX( int center_x ){
        this.center_x = center_x * PRECISION; 
    }
    
    public void setCenterY( int center_y ){
        this.center_y = center_y * PRECISION;
    }
    
    public void setCenterZ( int center_z ){
        this.center_z = center_z * PRECISION; 
    }
    
    public void setSpeedX( int speed_x ){
        this.speed_x = speed_x;
    }
    
    public void setSpeedY( int speed_y ){
        this.speed_y = speed_y;
    }
    
    public void setSpeedZ( int speed_z ){
        this.speed_z = speed_z; 
    }
    
    public void setAccelerationX( int acceleration_x ){
        this.acceleration_x = acceleration_x; 
    }
    
    public void setAccelerationY( int acceleration_y ){
        this.acceleration_y = acceleration_y; 
    }
    
    public void setAccelerationZ( int acceleration_z ){
        this.acceleration_z = acceleration_z; 
    }
    
    public void setOut( boolean out){
        this.out = out;
    }
    
    public void setHit( boolean hit){
        this.hit = hit;
    }
}
