package main;


public class Day {
	private int day, nextClass;
	private boolean humMidterm, mathMidterm, sciMidterm, finalBattle;
	private boolean humAttend, mathAttend, sciAttend;
	private double midtermScore;
	
	public Day(int d)
	{
		day=d-1;
		nextDay();		
	}
	
	private void nextDay()
	{
		//TODO: store data from completed day if wanted
		
		day++;
		
		humMidterm = (day==4)? true : false;
		mathMidterm = (day==5) ? true : false;
		sciMidterm = (day==6) ? true : false;
		finalBattle = (day==10) ? true : false;
		
		humAttend = mathAttend = sciAttend = false;
		
		nextClass = 1;
	}
	
	public int getDay()
	{
		return day;	
	}
	
	public int getNextClass()
	{
		return nextClass;
	}
	
	public String getNextClassName()
	{
		switch (nextClass)
		{
			case 1:
				return "Humanities";
			case 2: 
				return "Math";
			case 3: 
				return "Science";
			default:
				return "Humanities";
		}
	}
	
	public void attendClass()
	{
		switch (nextClass)
		{
			case 1:
				humAttend = true;
				break;
			case 2:
				mathAttend = true;
				break;
			case 3:
				sciAttend = true;
				break;
		}
		
		nextClass++;
		
		if (nextClass == 4)
		{
			System.out.println("You start a new day!");
			nextDay();
		}
	}
	
	public void missClass()
	{
		nextClass++;
		
		if (nextClass == 4)
		{
			System.out.println("You start a new day!");
			nextDay();
		}
	}
	
	public boolean isMidtermNext()
	{
		if (nextClass==1 && humMidterm)
			return true;
		else if (nextClass==2 && mathMidterm)
			return true;
		else if (nextClass==3 && sciMidterm)
			return true;
		
		return false;
	}
	
	public boolean isHumMidtermDay()
	{
		return humMidterm;
	}
	
	public boolean isMathMidtermDay()
	{
		return mathMidterm;
	}
	
	public boolean isSciMidtermDay()
	{
		return sciMidterm;
	}
	
	public boolean isFinalDay()
	{
		return finalBattle;
	}
}
