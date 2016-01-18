package bomberman;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;


public class GameEnd 
{
	private Canvas canvas;
	private Shell shell;
	private PaintListener paintListener;
	private Button buttonRestart;
	private Image imageRestart = new Image(null, "./img/restart.png");
	private Button buttonExit;
	private Image imageExit = new Image(null, "./img/exit.png");
	private Button buttonMenu;
	private Image imageMenu = new Image(null, "./img/menu.png");
	
	public GameEnd(Canvas can, Shell parent, boolean isPlayer)
	{
		shell = parent;
		canvas = can;

		paintListener = new PaintListener()
		{
			public void paintControl(PaintEvent event) 
			{
				final GC gc = event.gc;
				Image img;
				if (isPlayer) 
				{
					img  = new Image(null,"./img/win.png");
					gc.drawImage(img, 530, 180);
				}
				else 
				{
					img  = new Image(null,"./img/gameover.png");
					gc.drawImage(img, 430, 30);
				}
				img.dispose();
				gc.dispose();
			}
		};
		canvas.addPaintListener(paintListener);

		
		buttonRestart = new Button(canvas, SWT.PUSH);
		buttonRestart.setLocation(new Point(200,370));
		buttonRestart.setSize(new Point(552,72));
		buttonRestart.setImage(imageRestart);
		buttonRestart.addListener(SWT.Selection, new Listener() 
		{
			public void handleEvent(Event e) 
			{
			    switch (e.type) 
			    {
				    case SWT.Selection:
				    	dispose();
				    	new Map(canvas, shell);
				        break;
			    }
		    }
		});
		
		buttonMenu = new Button(canvas, SWT.PUSH);
		buttonMenu.setLocation(new Point(600,453));
		buttonMenu.setSize(new Point(312,72));
		buttonMenu.setImage(imageMenu);
		buttonMenu.addListener(SWT.Selection, new Listener() 
		{
			public void handleEvent(Event e) 
			{
			    switch (e.type) 
			    {
				    case SWT.Selection:
				    	dispose();
				    	new Menu(canvas, shell);
				        break;
			    }
		    }
		});
		
		buttonExit = new Button(canvas, SWT.PUSH);
		buttonExit.setLocation(new Point(760,540));
		buttonExit.setSize(new Point(312,72));
		buttonExit.setImage(imageExit);
		buttonExit.addListener(SWT.Selection, new Listener() 
		{
			public void handleEvent(Event e) 
			{
			    switch (e.type) 
			    {
				    case SWT.Selection:
				    	dispose();
				    	shell.getDisplay().close();
				        break;
			    }
		    }
		});
	}
	
	
	public void dispose()
	{
        buttonRestart.dispose();
        imageRestart.dispose();
        imageMenu.dispose();
		buttonMenu.dispose();
		imageExit.dispose();
		buttonExit.dispose();		
        canvas.removePaintListener(paintListener);
        canvas.redraw();
	}
}
