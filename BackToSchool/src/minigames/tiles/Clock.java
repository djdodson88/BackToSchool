package minigames.tiles;

public class Clock 
{
	private long currentTime;
	private long timeLeftOver;
	private long elapsed;
	private boolean pause;

	public Clock(long seconds)
	{
		// time is in milliseconds
		timeLeftOver = seconds * 1000;
		pause = false;
	}

	public void start()
	{
			currentTime = System.currentTimeMillis();
			pause = false;
	}

	public void timeStop()
	{
		pause = true;
	}


	public int timeRemaining()
	{

		if(!pause)
		{
			elapsed = System.currentTimeMillis() - currentTime;
			return (int)(timeLeftOver - elapsed)/1000;
		}

		return  (int)(timeLeftOver - elapsed)/1000;
	}




}
