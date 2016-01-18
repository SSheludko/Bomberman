package bomberman;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;

public class Player 
{
	private int[][] LEVEL = LevelData.LEVELNOW;
	
	public boolean isLife = true;
	
	private final int D = 2;
	public final int HEIGHT_SIZE = 42;
	public final int WIDTH_SIZE = 30;
	public int xp = 50;
	public int yp = 50;
	public Image[] imgplayer =
		{
				new Image(null,"./img/player0.png"),
				new Image(null,"./img/player1.png"),
				new Image(null,"./img/player2.png"),
				new Image(null,"./img/player1.png")
		};
	public int countImgPlayer = 0;
	
    public void animate(Event e) 
    {
        int key = e.keyCode;
        
        if (key == SWT.ARROW_LEFT && 
        	LEVEL[(yp)/50][(xp - D)/50] == 0 &&
        	LEVEL[(yp + HEIGHT_SIZE - 1)/50][(xp - D)/50] == 0) 
        		xp -= D;
        
        if (key == SWT.ARROW_RIGHT &&
        	LEVEL[(yp)/50][(xp + D + WIDTH_SIZE - 1)/50] == 0 &&
        	LEVEL[(yp + HEIGHT_SIZE - 1)/50][(xp + D + WIDTH_SIZE - 1)/50] == 0) 
        		xp += D;
        
        if (key == SWT.ARROW_UP && 
        	LEVEL[(yp - D)/50][(xp)/50] == 0 &&
        	LEVEL[(yp - D)/50][(xp + WIDTH_SIZE - 1)/50] == 0) 
        		yp -= D;
        
        if (key == SWT.ARROW_DOWN && 
        	LEVEL[(yp + D + HEIGHT_SIZE - 1)/50][(xp)/50] == 0 &&
        	LEVEL[(yp + D + HEIGHT_SIZE - 1)/50][(xp + WIDTH_SIZE - 1)/50] == 0) 
        		yp += D;
		
        countImgPlayer += 1;
    	if (countImgPlayer == 4)
    		countImgPlayer = 0;
	}
    
    public void dispose()
    {
		for(int i = 0; i < 4; i++)
			imgplayer[i].dispose();
    }
}
