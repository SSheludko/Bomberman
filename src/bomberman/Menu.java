package bomberman;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Menu
{		
	private Canvas canvas;
	private Shell shell;
	
	private Image imageStart = new Image(null, "./img/start.png");
	Button buttonStart;
	private Image imageExit = new Image(null, "./img/exit.png");
	Button buttonExit;
	
	public Menu(Canvas can, Shell parent)
	{	
		shell = parent;
		canvas = can;
		
		Color col = new Color(shell.getDisplay(), 0, 128, 0);
		canvas.setBackground(col);
		
		buttonStart = new Button(canvas, SWT.PUSH);
		buttonStart.setLocation(new Point(540,220));
		buttonStart.setSize(new Point(392,72));
		buttonStart.setImage(imageStart);
		buttonStart.addListener(SWT.Selection, new Listener() 
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
		
		buttonExit = new Button(canvas, SWT.PUSH);
		buttonExit.setLocation(new Point(585,320));
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
		imageStart.dispose();
		buttonStart.dispose();
		imageExit.dispose();
		buttonExit.dispose();
	}
	
}