package bomberman;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;


public class Map
{
	private Canvas canvas;
	private Shell shell;
	
	private int[][] LEVEL = LevelData.LEVELNOW;
	

	private int countBricksLevel = LevelData.countBricksLevel1;
	private int countEnemyLevel = LevelData.countEnemyLevel1;
	private int countDeathEnemyLevel = 0;
	
	public boolean isGame = true;
	public boolean pause = false;
	
	protected static final int TIMER_INTERVAL = 19;
	private Player player = new Player();
	private Bomb bomb = new Bomb();
	private Bang bang = new Bang();
	private Door door = new Door();
	private Enemy[] enemy = new Enemy[countEnemyLevel];
	private Random rand = new Random();
	private Image imgwall  = new Image(null,"./img/wall.png");
	private Image imgbricks  = new Image(null,"./img/bricks.png");
	PaintListener listener;
	Listener listenerBomb;
	Listener listenerPlayer;
	
	Runnable runnable = new Runnable() 
	{
		public void run() 
		{
			if (!pause)
			{
				for (int i = 0; i < countEnemyLevel; i++)
					enemy[i].animate();
				canvas.redraw();
				if (!isGame) 
				{
					dispose();
					new GameEnd(canvas, shell, player.isLife);
					return;
				}
			}	
			Display.getCurrent().timerExec(TIMER_INTERVAL, this);
		}
    };
    
    public void keyPressed(Event e)
    {
    	int key = e.keyCode;
		if (key == SWT.SPACE && bomb.isBomb == false && bang.isBang == false && !pause) 
		{
			bomb.isBomb = true;
			bomb.xb = (player.xp + player.WIDTH_SIZE/2)/50*50 + 7;
			bomb.yb = (player.yp + player.HEIGHT_SIZE/2)/50*50 + 2;
			bomb.timer.start();
			bang.setBangPoint(bomb.xb, bomb.yb);
		}
		if (key == SWT.ESC)
		{
			player.isLife = false;
			isGame = false;
		}
		if (key == SWT.CONTROL)
		{
			pause = !pause;
		}
    }
    
	public Map(Canvas can, Shell parent) {
		shell = parent;
		canvas = can;
		
		countEnemyLevel = 0;
		for(int i = 0; i<13; i++)
			for(int j = 0; j<31; j++)
			{
			LEVEL[i][j] = LevelData.LEVEL1[i][j]; 
				if (LEVEL[i][j] == 3)
				{
					enemy[countEnemyLevel++] = new Enemy(j * 50, i * 50);
					LEVEL[i][j] = 0;
				}
			}

		
		listener = new PaintListener() {
			public void paintControl(PaintEvent event) 
			{
				final GC gc = event.gc;
				if(isGame)
				{	
					int x = 0;
					int y = 0;
					
					
					for(int i = 0; i<13; i++)
					{
						x = 0;
						for(int j = 0; j<31; j++)
						{
							if (LEVEL[i][j] == 1) gc.drawImage(imgwall, x, y);
							if (LEVEL[i][j] == 2) gc.drawImage(imgbricks, x, y);
							x += 50;
						}
						y += 50;
					}								
					
					if(bomb.isBomb) 
					{
						if (bomb.timer.getTime() == bomb.BOMB_LIFE) 
						{
							bomb.timer.stop();
							bomb.isBomb = false;
							
							bang.isBang = true;
							bang.timer.start();
						}
						gc.drawImage(bomb.imgbomb, bomb.xb, bomb.yb);
					}
					
					if(door.isDoor) 
					{
						gc.drawImage(door.imgdoor, door.xd, door.yd);
						if (door.doorIsOpen &&
							(player.xp + player.WIDTH_SIZE/2)/50 == (door.xd + 25)/50 &&
							(player.yp + player.HEIGHT_SIZE/2)/50 == (door.yd + 25)/50)
							{
								isGame = false;
							}
					}
					
					gc.drawImage(player.imgplayer[player.countImgPlayer], player.xp, player.yp);

					for (int i = 0; i < countEnemyLevel; i++)	
						if (enemy[i].isLife) 
						{
							gc.drawImage(enemy[i].imgenemy[enemy[i].countImgEnemy], enemy[i].xe, enemy[i].ye);
							if ((player.yp + player.HEIGHT_SIZE/2)/50 == (enemy[i].ye + enemy[i].HEIGHT_SIZE/2)/50 &&
								(player.xp + player.WIDTH_SIZE/2)/50 == (enemy[i].xe + enemy[i].WIDTH_SIZE/2)/50)
								{
									player.isLife = false;
									isGame = false;
								}
						}

					if(bang.isBang) 
					{
						if (bang.timer.getTime() == bang.BANG_LIFE) 
						{
							bang.timer.stop();
							bang.isBang = false;
						}
						
						for(int i = 0; i < bang.LENGTH_BANG * 4 + 1; i++)
							if (bang.bangPoint[0][i] != -1)
							{
								gc.drawImage(bang.imgbang, bang.bangPoint[0][i] * 50, bang.bangPoint[1][i] * 50);
								if (LEVEL[bang.bangPoint[1][i]][bang.bangPoint[0][i]] == 2)
								{
									LEVEL[bang.bangPoint[1][i]][bang.bangPoint[0][i]] = 0;
									countBricksLevel--;
	
									int tmp = rand.nextInt(99);
									if(door.isDoor == false && (tmp < 15 || countBricksLevel == 1))
									{
										door.xd = bang.bangPoint[0][i] * 50 + 3;
										door.yd =  bang.bangPoint[1][i] * 50;
										door.isDoor = true;
									}
								}
								if (bang.bangPoint[1][i] == (player.yp + player.HEIGHT_SIZE/2)/50 && 
									bang.bangPoint[0][i] == (player.xp + player.WIDTH_SIZE/2)/50)
									{
										player.isLife = false;
										isGame = false;
									}
								
								for (int k = 0; k < countEnemyLevel; k++)
									if (bang.bangPoint[1][i] == (enemy[k].ye + enemy[k].HEIGHT_SIZE/2)/50 && 
										bang.bangPoint[0][i] == (enemy[k].xe + enemy[k].WIDTH_SIZE/2)/50 &&
										enemy[k].isLife == true)
										{
											enemy[k].isLife = false;
											if (++countDeathEnemyLevel == countEnemyLevel) door.doorIsOpen = true;
										}
							}
					}	
				}
			}
		};
		
		canvas.addPaintListener(listener);
		
		listenerPlayer = event -> player.animate(event);
		canvas.addListener(SWT.KeyDown, listenerPlayer);
		listenerBomb = event -> keyPressed(event);
		canvas.addListener(SWT.KeyDown, listenerBomb);
		
		Display.getCurrent().timerExec(TIMER_INTERVAL, runnable);
	}
	
		
	
	public void dispose()
	{
		canvas.removePaintListener(listener);
		canvas.removeListener(SWT.KeyDown, listenerBomb);
		canvas.removeListener(SWT.KeyDown, listenerPlayer);
		
		imgwall.dispose();
		imgbricks.dispose();
		
		for(int i = 0; i < countEnemyLevel; i++)
			enemy[i].dispose();
		
		bang.dispose();
		bomb.dispose();
		door.dispose();
		player.dispose();
	}
}
