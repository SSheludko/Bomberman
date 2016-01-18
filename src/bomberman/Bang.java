package bomberman;

import org.eclipse.swt.graphics.Image;

public class Bang {
	private int[][] LEVEL = LevelData.LEVELNOW;
	
	public boolean isBang = false;
	
	public int LENGTH_BANG = 1;
	public int[][] bangPoint = new int[2][4 * LENGTH_BANG + 1];
	
	public Image imgbang  = new Image(null,"./img/bang.png");
	public Stopwatch timer = new Stopwatch();
	
	public int BANG_LIFE = 1;
	
	
	public void setBangPoint(int xb, int yb)
	{
		for(int i = 0; i < LENGTH_BANG * 4 + 1; i++)
		{
			bangPoint[0][i] = -1;
			bangPoint[1][i] = -1;
		}
		
		int x = xb / 50;
		int y = yb / 50;
		bangPoint[0][0] = x;
		bangPoint[1][0] = y;
		
		for (int i = 0; i < LENGTH_BANG; i++)
		{
			if (LEVEL[y - i - 1][x] == 0)
			{
				bangPoint[0][i + 1] = x;
				bangPoint[1][i + 1] = y - i - 1;
			}
			if (LEVEL[y - i - 1][x] == 2)
			{
				bangPoint[0][i + 1] = x;
				bangPoint[1][i + 1] = y - i - 1;
				break;
			}
			if (LEVEL[y - i - 1][x] == 1) break;
		}
		
		for (int i = 0; i < LENGTH_BANG; i++)
		{
			if (LEVEL[y][x + i + 1] == 0)
			{
				bangPoint[0][LENGTH_BANG + i + 1] = x + i + 1;
				bangPoint[1][LENGTH_BANG + i + 1] = y;
			}
			if (LEVEL[y][x + i + 1] == 2)
			{
				bangPoint[0][LENGTH_BANG + i + 1] = x + i + 1;
				bangPoint[1][LENGTH_BANG + i + 1] = y;
				break;
			}
			if (LEVEL[y][x + i + 1] == 1) break;
		}
		
		for (int i = 0; i < LENGTH_BANG; i++)
		{
			if (LEVEL[y + i + 1][x] == 0)
			{
				bangPoint[0][2 * LENGTH_BANG + i + 1] = x;
				bangPoint[1][2 * LENGTH_BANG + i + 1] = y + i + 1;
			}
			if (LEVEL[y + i + 1][x] == 2)
			{
				bangPoint[0][2 * LENGTH_BANG + i + 1] = x;
				bangPoint[1][2 * LENGTH_BANG + i + 1] = y + i + 1;
				break;
			}
			if (LEVEL[y + i + 1][x] == 1) break;
		}
		
		for (int i = 0; i < LENGTH_BANG; i++)
		{
			if (LEVEL[y][x - i - 1] == 0)
			{
				bangPoint[0][3 * LENGTH_BANG + i + 1] = x - i - 1;
				bangPoint[1][3 * LENGTH_BANG + i + 1] = y;
			}
			if (LEVEL[y][x - i - 1] == 2)
			{
				bangPoint[0][3 * LENGTH_BANG + i + 1] = x - i - 1;
				bangPoint[1][3 * LENGTH_BANG + i + 1] = y;
				break;
			}
			if (LEVEL[y][x - i - 1] == 1) break;
		}
	}
	
	public void dispose()
	{
		imgbang.dispose();
		timer.stop();
	}
}
