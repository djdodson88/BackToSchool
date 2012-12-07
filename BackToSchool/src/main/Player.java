package main;


public class Player {
	double sciRigor;
	double creativity;
	double quantReasoning;
	
	// player starts with 1.0 GPA statistics (debatable)
	public Player(){
		sciRigor=4.0;
		creativity=4.0;
		quantReasoning=4.0;
	}
	
	// increase the statistic
	public void increaseSciRigor(double inc){
		sciRigor+=inc;
	}
	
	// increase the statistic
	public void increaseCreativit(double inc){
		creativity+=inc;
	}
	
	// increase the statistic
	public void increaseQuantReasoning(double inc){
		quantReasoning+=inc;
	}
	
	//return the statistic
	public double getSciRigor(){
		return sciRigor;
	}
	
	//return the statistic
	public double getCreativity(){
		return creativity;
	}
	
	//return the statistic
	public double getQuantReasoning(){
		return quantReasoning;
	}
	

}
