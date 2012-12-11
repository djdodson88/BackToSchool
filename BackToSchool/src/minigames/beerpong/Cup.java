package minigames.beerpong;

public class Cup extends Sprite{
	
	int init_center_x;
	int init_center_y;
	int init_center_z;
	
	boolean hit;
	
	public Cup( int c_x, int c_y, int c_z ){
		super();
		init_center_x = c_x;
		init_center_y = c_y;
		init_center_z = c_z;
		setRadius(20);
		setHit(false);
		reset();
	}
	
	//Resets the ball to its initial position and dance status.
	public void reset(){
		
		setCenterX ( init_center_x );
		setCenterY ( init_center_y );
		setCenterZ ( init_center_z );
	}
	
	public void setHit( boolean hit){
        this.hit = hit;
    }
	
	public int getInitCenterX(){
		return init_center_x;
	}
	
	public int getInitCenterY(){
		return init_center_y;
	}

	public int getInitCenterZ(){
		return init_center_z;
	}
	
	public boolean getHit(){
        return hit;
    }
}
