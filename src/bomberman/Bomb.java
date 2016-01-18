package bomberman;

import org.eclipse.swt.graphics.Image;

public class Bomb {
	public boolean isBomb = false;
	public int xb;
	public int yb;
	public Image imgbomb  = new Image(null,"./img/bomb.png");
	public Stopwatch timer = new Stopwatch();
	
	public int BOMB_LIFE = 4;
	
	public void dispose()
	{
		imgbomb.dispose();
	}
	
}
