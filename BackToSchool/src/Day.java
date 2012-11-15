
public class Day {
	int day;
	boolean humMidterm;
	boolean mathMidterm;
	boolean sciMidterm;
	boolean playFinalBattle;
	double midtermScore;
	
	public Day(int d)
	{
		day=d;
		humMidterm = false;
		mathMidterm = false;
		sciMidterm = false;
		playFinalBattle = false;
		
		if(day==4)
			humMidterm=true;
		else if(day==5)
			mathMidterm=true;
		else if(day==6)
			sciMidterm=true;
		else if(day==10)
			playFinalBattle=true;
	}
	
	public int getDay(){
		return day;
	}
	
	public boolean isHumMidtermDay(){
		return humMidterm;
	}
	
	public boolean isMathMidtermDay(){
		return mathMidterm;
	}
	
	public boolean isSciMidtermDay(){
		return sciMidterm;
	}
	
	public boolean isFinalDay(){
		return playFinalBattle;
	}
}
