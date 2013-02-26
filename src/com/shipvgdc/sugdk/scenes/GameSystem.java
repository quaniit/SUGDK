package com.shipvgdc.sugdk.scenes;

import com.badlogic.gdx.InputProcessor;
import com.shipvgdc.sugdk.util.Observable;
import com.shipvgdc.sugdk.util.Observer;

/**
 * GameSystem
 * <p/>
 * Abstracted away the logic specifics for a game.  
 * Useful if you want your game to follow a MVC structure.
 * @author nhydock
 */
public abstract class GameSystem extends Observable implements Observer<GameController>{

	protected GameDisplay<? extends GameSystem> display;
	
	/**
	 * Things to do when the system is first started/the scene is switched to
	 */
	abstract public void start();
	
	/**
	 * Things to do when the scene is over and the system should be shutdown
	 */
	abstract public void end();
	
	/**
	 * Update they system while the scene is active
	 * @param delta - amount of time passed since previous update
	 */
	abstract public void update(float delta);
	
	/**
	 * Sets the linked display of this system
	 * @param d
	 */
	public void setDisplay(GameDisplay<? extends GameSystem> d)
	{
		display = d;
	}
}
