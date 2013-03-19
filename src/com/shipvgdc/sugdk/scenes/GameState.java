package com.shipvgdc.sugdk.scenes;

import com.shipvgdc.sugdk.scenes.SceneNotificationTypes.ControllerNotification;
import com.shipvgdc.sugdk.scenes.SceneNotificationTypes.GameNotification;
import com.shipvgdc.sugdk.util.Bridge;

/**
 * GameState
 * <p/>
 * State interface for allowing scene systems to have their own statemachine to them
 * @author nhydock
 * 
 * @param <Sys>
 */
public abstract class GameState<Sys extends StateBasedSystem<?>> extends Bridge<GameNotification, ControllerNotification>
{

	/**
	 * ID identifier of the state
	 */
	protected static final int ID = 0;
	
	/**
	 * System that the state belongs to
	 */
	protected Sys parent;
	
	/**
	 * Creates a game state
	 * @param parent
	 */
	public GameState(Sys parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Does handling of things that need to be updated per cycle
	 * @param delta - time passed since last update cycle
	 */
	public abstract void handle(float delta);
	
	/**
	 * Performs state setup when the state is switched to
	 * Start processing can be performed repeatedly until an end condition is met
	 * before the state machine will switch into doing regular update handling.
	 * <p/>
	 * Note that process operations should not and can not be dependent on updating time
	 * @return 
	 *  	true if the state is done performing its start processing
	 */
	public boolean start()
	{
		return false;
	}
	
	/**
	 * Performs state setup when the state is switched away from.
	 * End processing can be performed repeatedly until an end condition is met before
	 * the machine will switch to the next state.
	 * <p/>
	 * Note that process operations should not and can not be dependent on updating time
	 * @return 
	 *  	true if the state is done performing its end processing
	 */
	public boolean end()
	{
		return false;
	}
	
	/**
	 * @return the identifier for the state
	 */
	public abstract int getID();
	
}
