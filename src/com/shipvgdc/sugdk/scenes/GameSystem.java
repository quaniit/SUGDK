package com.shipvgdc.sugdk.scenes;

import com.shipvgdc.sugdk.scenes.SceneNotificationTypes.ControllerNotification;
import com.shipvgdc.sugdk.scenes.SceneNotificationTypes.GameNotification;
import com.shipvgdc.sugdk.util.Bridge;
import com.shipvgdc.sugdk.util.Observer;

/**
 * GameSystem
 * <p/>
 * Abstracted away the logic specifics for a game.  
 * Useful if you want your game to follow a MVC structure.
 * @author nhydock
 * 
 */
public abstract class GameSystem extends Bridge<GameNotification, ControllerNotification>
{

	/**
	 * Things to do when the system is first started/the scene is switched to
	 */
	abstract public void start();
	
	/**
	 * Allow the system to do any first time processing that can 
	 *  only be done after the display has been initialized
	 */
	abstract public void postStart();
	
	/**
	 * Things to do when the scene is over and the system should be shutdown
	 */
	abstract public void end();
	
	/**
	 * Update they system while the scene is active
	 * @param delta - amount of time passed since previous update
	 */
	abstract public void update(float delta);
	
	@Override
	public void addObserver(Observer<GameNotification> o)
	{
		super.addObserver(o);
		if (o instanceof GameDisplay)
		{
			((GameDisplay<GameSystem>)o).setSystem(this);
		}
	}
}
