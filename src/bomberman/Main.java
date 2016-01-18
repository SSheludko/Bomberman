package bomberman;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main {

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display, SWT.TITLE);
		shell.setLayout(new FillLayout());
		shell.setSize(1555, 678);
		
		Canvas canvas = new Canvas(shell, SWT.DOUBLE_BUFFERED);
		
		new Menu(canvas, shell);
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		display.dispose();
	}

}