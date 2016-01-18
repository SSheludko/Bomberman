package bomberman;


import org.eclipse.swt.graphics.Image;

public class Enemy {
	private int[][] LEVEL = LevelData.LEVELNOW;
	
	public boolean isLife = true;
	private String move = "right";
	
	private final int D = 1;
	public final int HEIGHT_SIZE = 41;
	public final int WIDTH_SIZE = 45;
	public int xe;
	public int ye;
	
	public Image[] imgenemy =
		{
				new Image(null,"./img/enemy0.png"),
				new Image(null,"./img/enemy1.png"),
				new Image(null,"./img/enemy2.png"),
				new Image(null,"./img/enemy1.png")
		};
	public int countImgEnemy = 0;
	
	public Enemy(int x, int y)
	{
		xe = x;
		ye = y;
	}

	public void animate()
	{
        if (move == "right")
        {
	        if (LEVEL[(ye)/50][(xe + D + WIDTH_SIZE)/50] == 0 && 
	        	LEVEL[(ye + HEIGHT_SIZE)/50][(xe + D + WIDTH_SIZE)/50] == 0) 
	        		xe += D; 
	        else move = "down";
	        if (LEVEL[(ye - D)/50][(xe)/50] == 0 &&
			    LEVEL[(ye - D)/50][(xe + WIDTH_SIZE)/50] == 0 &&
			    move != "down") 
	        	{	
	        		ye -= D;
	        		move = "up";
	        	}
        }
        
        if (move == "down")
        {
        	if( LEVEL[(ye + D + HEIGHT_SIZE)/50][(xe)/50] == 0 &&
	        	LEVEL[(ye + D + HEIGHT_SIZE)/50][(xe + WIDTH_SIZE)/50] == 0) 
        			ye += D;
	        else move = "left";
        	if (LEVEL[(ye)/50][(xe + D + WIDTH_SIZE)/50] == 0 && 
        		LEVEL[(ye + HEIGHT_SIZE)/50][(xe + D + WIDTH_SIZE)/50] == 0 &&
        		move != "left") 
        		{
        			xe += D;
        			move = "right";
        		}
        }
        
        if (move == "left")
        {
            if (LEVEL[(ye)/50][(xe - D)/50] == 0 &&
            	LEVEL[(ye + HEIGHT_SIZE)/50][(xe - D)/50] == 0) 
        			xe -= D;
            else move = "up";
            if (LEVEL[(ye + D + HEIGHT_SIZE)/50][(xe)/50] == 0 &&
            	LEVEL[(ye + D + HEIGHT_SIZE)/50][(xe + WIDTH_SIZE)/50] == 0 &&
            	move != "up") 
            	{
            		ye += D;
            		move = "down";
            	}
        }
        
        if (move == "up")
        {
        	if (LEVEL[(ye - D)/50][(xe)/50] == 0 &&
		        LEVEL[(ye - D)/50][(xe + WIDTH_SIZE)/50] == 0) 
	        		ye -= D;
	        else move = "right";
        	if (LEVEL[(ye)/50][(xe - D)/50] == 0 &&
	            LEVEL[(ye + HEIGHT_SIZE)/50][(xe - D)/50] == 0 &&
	            move != "right") 
        		{
        			xe -= D;
        			move = "left";
        		}
        }
        
        countImgEnemy += 1;
        if (countImgEnemy == 4)
        	countImgEnemy = 0;
	}
	
	public void dispose()
	{
		for(int i = 0; i < 4; i++)
			imgenemy[i].dispose();
	}
}
