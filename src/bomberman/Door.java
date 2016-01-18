package bomberman;

import org.eclipse.swt.graphics.Image;

public class Door {
	public Image imgdoor  = new Image(null,"./img/door.png");
	public int xd;
	public int yd;
	public boolean isDoor = false;
	public boolean doorIsOpen = false;
	
	public void dispose()
	{
		imgdoor.dispose();
	}
}
