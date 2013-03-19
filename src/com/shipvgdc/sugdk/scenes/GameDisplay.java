package com.shipvgdc.sugdk.scenes;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.shipvgdc.sugdk.scenes.SceneNotificationTypes.DisplayNotification;
import com.shipvgdc.sugdk.scenes.SceneNotificationTypes.GameNotification;

import com.shipvgdc.sugdk.util.Bridge;
import com.shipvgdc.sugdk.util.Observer;

/**
 * GameDisplay
 * <p/>
 * Abstracted away the display specifics for a game.  
 * Useful if you want your game to follow a MVC structure.
 *  
 * @param <Sys>  
 * @author nhydock
 * 
 */
public abstract class GameDisplay<Sys extends GameSystem> extends Bridge<DisplayNotification, GameNotification>
{

	protected Sys system;
	
	/**
	 * Has its own batch just in cas that's desired
	 */
	protected SpriteBatch batch;
	
	/**
	 * 2D camera to draw interface components
	 */
	protected OrthographicCamera camera;

	/**
	 * Stage for handling 2d Components
	 */
	protected Stage ui;
	
	/**
	 * Tween manager for the display.  All sub components should have access to it
	 */
	public TweenManager tweenmanager;
	
	/**
	 * Creates a game scene instance
	 */
	public GameDisplay()
	{
		this.batch = new SpriteBatch();
	}
	
	/**
	 * Things to perform when this display is loaded up,
	 * such as making sprite batches and other image loading
	 */
	public abstract void init();
	
	/**
	 * You should dispose of all the graphical elements when done with this display to prevent leaking
	 */
	public abstract void dispose();
	
	/**
	 * Update should be used to update images and animations that are dependent on game cycle timing
	 * @param delta
	 */
	public abstract void update(float delta);
	
	/**
	 * Render should be specifically for drawing stuff to the screen
	 */
	public abstract void render();
	
	/**
	 * Update the display's dimensions.  
	 * Useful for fixing game cameras when the game is adjusted
	 * @param width - width of the viewport
	 * @param height - height of the viewport
	 */
	public abstract void resize(int width, int height);
	
	/**
	 * @return the ui stage
	 */
	public Stage getUI()
	{
		return ui;
	}
	
	@Override
	public void addObserver(Observer<DisplayNotification> o)
	{
		super.addObserver(o);
		if (o instanceof GameController)
		{
			((GameController)o).setDisplay(this);
		}
	}
	
	protected void setSystem(Sys system)
	{
		this.system = system;
	}
}
