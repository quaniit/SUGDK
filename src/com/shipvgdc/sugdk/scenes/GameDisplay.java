package com.shipvgdc.sugdk.scenes;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * GameDisplay
 * <p/>
 * Abstracted away the display specifics for a game.  
 * Useful if you want your game to follow a MVC structure.
 * 
 * @param <S> GameSystem that the display can examine/be linked to
 * @author nhydock
 * 
 */
public abstract class GameDisplay<S extends GameSystem> {

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
	 * System the the display is to watch and update with
	 */
	protected S system;
	
	/**
	 * Creates a game scene instance
	 * @param system
	 */
	public GameDisplay(S system)
	{
		this.system = system;
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
	 * Updates the display on a specific type of notification from the system
	 * @param notification
	 */
	public abstract void notify(Object notification);
	
}
