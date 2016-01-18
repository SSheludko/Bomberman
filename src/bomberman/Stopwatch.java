package bomberman;

import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch{
	int secondsPassed = 0;
	
	Timer myTimer = null;

	public int getTime()
	{
		return secondsPassed;
	}
	
	
	public void start()
	{
		myTimer = new Timer();
		TimerTask task = new TimerTask() 
		{
			public void run()
			{
				secondsPassed++;
			}
		};
		myTimer.schedule(task, 1000, 1000); //Установить начальную задержку в 1 секунду, а затем повторите каждую секунду.
	}
	
	public void stop()
	{
		if (myTimer != null) myTimer.cancel();
		secondsPassed = 0;
	}
}

    