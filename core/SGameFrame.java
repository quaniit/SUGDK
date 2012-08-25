package sugdk.core;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;

/**
 * SGameFrame
 * @author nhydock
 *
 *	SGameFrame is a type of game frame that uses Software rendering/AWT to
 *	draw all the graphics to screen.
 */
abstract public class SGameFrame extends GameFrame implements KeyListener{

	private JFrame window;
	
	protected volatile Thread animator;
	
	protected volatile Canvas canvas;

	// Double buffering support.
	private BufferStrategy bufferStrategy;
	private Graphics buffer;

	public SGameFrame(String name, int fps, boolean windowed)
	{
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd = ge.getDefaultScreenDevice();

//		setLayout(null);

		window = new JFrame(name);
		isWindowed = windowed;

		if (!isWindowed)
		{
			if (!gd.isFullScreenSupported())
			// If the display doesn't support full screen, display a warning,
			// and then start the game in windowed mode.
			{
				System.out.println("Full-screen exclusive mode not supported");
				initWindowed();
			}
			else
			// Otherwise, the user has requested full screen, and it is
			// supported, so start the game in full screen mode.
				initFullScreen();
		}
		else
			initWindowed();
		
		setFPS(fps);

		setBufferStrategy();

		// Make this panel receive key events.
		window.setFocusable(true);
		window.requestFocus();
	}
	
	protected void initWindowed()
	// Set up the frame for windowed mode.
	{
		canvas = new Canvas();
		window.add(canvas);
		
		//center the window in the middle of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation((int)(screenSize.getWidth()/2-this.getWidth()/2), (int)(screenSize.getHeight()/2-this.getHeight()/2));
	
		//setUndecorated(true);
		window.addWindowListener(this);
		window.setIgnoreRepaint(true);					// Turn off all paint events.
		window.setResizable(false);					// Prevent frame resizing.
		window.setVisible(true);
	}
	
	protected void initFullScreen()
	// Set up the frame to be full screen.
	{
		window.setUndecorated(true);					// No menu bar, borders, etc.
		window.setIgnoreRepaint(true);					// Turn off all paint events.
		window.setResizable(false);					// Prevent frame resizing.
		
		gd.setFullScreenWindow(window);			// Switch on full-screen exclusive mode
	}
	
	@Override
	protected void startGame()
	// Initialize and start the thread. 
	{ 
		if (animator == null || !running)
		{
			animator = new Thread(this);
			animator.start();
			window.addKeyListener(this);
		}
	}
	
	private void setBufferStrategy()
	// Attempt to set the BufferStrategy (for double buffering).
	{
		try
		// Try to create a buffer strategy. Wait until it has been made.
		{
			EventQueue.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					if (isWindowed)
						canvas.createBufferStrategy(2);
					else
						window.createBufferStrategy(2);
				}
			});
		}
		catch (Exception e)
		// Whoops! Something happened and a buffer strategy couldn't be made.
		{
			System.out.println("Error while creating buffer strategy");
			System.exit(0);
		}
		
		try
		// Sleep to give time for the buffer strategy to be carried out.
		{
			Thread.sleep(500); // 0.5 sec
		}
		catch(InterruptedException ex){}

		if (isWindowed)
			bufferStrategy = canvas.getBufferStrategy();
		else
			bufferStrategy = window.getBufferStrategy();
	}
	
	@Override
	public void setSize(int w, int h)
	// Set the size of the drawing area (canvas if windowed, frame if not).
	{
		if (isWindowed)
		{
			canvas.setPreferredSize(new Dimension(w,h));
			window.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			window.setLocation((int)(screenSize.getWidth()/2-this.getWidth()/2), (int)(screenSize.getHeight()/2-this.getHeight()/2));
		}
		else
			window.setSize(w,h);
	}

	public void setWidth(int w)
	// Set the width of the drawing area (canvas if windowed, frame if not).

	{
		if (isWindowed)
		{
			canvas.setPreferredSize(new Dimension(w,canvas.getHeight()));
			window.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			window.setLocation((int)(screenSize.getWidth()/2-this.getWidth()/2), (int)(screenSize.getHeight()/2-this.getHeight()/2));
		}
		else
			window.setSize(w,window.getHeight());
	}

	@Override
	public int getWidth()
	// Get the width of the drawing area (canvas if windowed, frame if not).
	{
		if (isWindowed)
			return canvas.getWidth();

		return ImageObserver.WIDTH;
	}

	public void setHeight(int h)
	// Set the height of the drawing area (canvas if windowed, frame if not).
	{
		if (isWindowed)
		{
			canvas.setPreferredSize(new Dimension(canvas.getWidth(),h));
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			window.setLocation((int)(screenSize.getWidth()/2-this.getWidth()/2), (int)(screenSize.getHeight()/2-this.getHeight()/2));
			window.pack();
		}
		else
		{
			window.setSize(window.getWidth(),h);
		}
	}

	@Override
	public int getHeight()
	// Get the height of the drawing area (canvas if windowed, frame if not).
	{
		if (isWindowed)
			return canvas.getHeight();

		return ImageObserver.HEIGHT;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	abstract protected void gameUpdate();

	@Override
	abstract protected void gameRender(Graphics g);
	
	/**
	 * Use active rendering to draw to a back buffer and then place the buffer on-screen.
	 */
	public void paintScreen()
	{
		try
		{
			buffer = bufferStrategy.getDrawGraphics();
			gameRender(buffer);
			buffer.dispose();
			
			if (!bufferStrategy.contentsLost())
				bufferStrategy.show();
			else
				System.out.println("Contents Lost");

			// Sync the display on some systems.
			// (on Linux, this fixes event queue problems)
			Toolkit.getDefaultToolkit().sync();

//			System.out.println("frame updated");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			running = false;
		}
	}
}
