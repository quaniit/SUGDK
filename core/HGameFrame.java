package sugdk.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import sugdk.graphics.GLGraphics;

abstract public class HGameFrame extends GameFrame{
	
	DisplayMode dm;
	Thread animator;
	
	public HGameFrame(int[] res, int fps, boolean windowed)
	{
		super();
		renderMode = true;
		dm = new DisplayMode(res[0], res[1]);
		if (windowed)
			initWindowed();
		else
			initFullScreen();
		instance = this;
	}

	@Override
	protected void initWindowed() {
		try {
			Display.setDisplayMode(dm);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Display.setLocation((int)(screenSize.getWidth()/2-this.getWidth()/2), (int)(screenSize.getHeight()/2-this.getHeight()/2));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	protected void initFullScreen() {
		try {
			Display.setDisplayMode(dm);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	protected void startGame() {
		if (animator == null || !running)
		{
			animator = new Thread(this);
			animator.start();
		}
	}

	@Override
	protected void paintScreen() {
		gameRender(null);		//renders the opengl
		pollInput();			//execute input presses
		Display.update();		//updates the display and input listeners
		
		//have to manually create listen checking here
		if (Display.isCloseRequested())
			windowClosing(null);
		if (!Display.isActive())
			windowDeactivated(null);
		else
			windowActivated(null);
	}

	@Override
	abstract protected void gameUpdate();

	@Override
	abstract protected void gameRender(Graphics g);

	/**
	 * Resizes the lwjgl display.
	 * What occurs is the current display is destroyed, then a new one is created in its place using the new size
	 */
	@Override
	public void setSize(int w, int h) {
		try {
			dm = new DisplayMode(w, h);
			Display.destroy();
			Display.setDisplayMode(dm);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setWidth(int w) {
		setSize(w, dm.getHeight());
	}

	@Override
	public int getWidth() {
		return dm.getWidth();
	}

	@Override
	public void setHeight(int h) {
		setSize(dm.getWidth(), h);
	}

	@Override
	public int getHeight() {
		return dm.getHeight();
	}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		stopGame();
		
		Display.destroy();	//need to throw this in here to free display resources of lwjgl
		System.exit(0);
	}
	
	/**
	 * Input handling is done a bit differently than it is with an SGameFrame because LWJGL's input listener
	 * isn't exactly the same format as a Swing KeyListener
	 */
	public void pollInput()
	{
		//handle input for every event that happened with the keyboard
		while (Keyboard.next())
		{
			//it only can handle key pressed
			if (Keyboard.getEventKeyState()){
				 engine.getSceneManager().getCurrentScene().keyPressed(Keyboard.getEventKey());
			}
		}
	}
}
