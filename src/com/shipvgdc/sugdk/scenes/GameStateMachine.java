package com.shipvgdc.sugdk.scenes;

import java.util.HashMap;

/**
 * GameStateMachine
 * <p/>
 * State Machine for scenes that allows swapping between game states
 * @author nhydock
 * @param <State> Type of game state the machine will be managing
 */
public class GameStateMachine<State extends GameState<?>>{

	private HashMap<Integer, State> stateList = new HashMap<Integer, State>();
	
	private State currentState; //current state in use by the machine
	private State nextState;	//state set to transition to
	
	private boolean switching;	//know to perform startup actions instead of handling during regular processing
	
	private int currentStateID;

	private int nextStateID;
	
	/**
	 * Adds a new state to the machine
	 * @param newState - the state to add to the machine
	 * @param force - forces the state to be added even if the ID is already taken
	 * @return true if the state was added to the machine, false if it couldn't be
	 */
	public boolean addState(State newState, boolean force)
	{
		boolean canAdd = !stateList.containsKey(newState.getID()) || force;
		
		if (canAdd)
		{
			stateList.put(newState.getID(), newState);
			//replace current running state to this state if the id is the same
			if (currentStateID == newState.getID())
			{
				this.setState(newState.getID());
			}
		}
		
		return canAdd;
	}
	
	/**
	 * Adds a new state to the machine.  By default it does not replace states with the same ID
	 * @param newState - the state to add to the machine
	 * @return true if the state was added to the machine, false if it couldn't be
	 */
	public boolean addState(State newState)
	{
		return addState(newState, false);
	}
	
	/**
	 * @return the state's current machine
	 */
	public State getCurrentState()
	{
		return currentState;
	}
	
	/**
	 * Updates the current state of the machine
	 * @param delta - time passed since previous update cycle of the game
	 */
	public void handleCurrentState(float delta)
	{
		if (nextState == null)
		{
			if (!switching)
			{
				currentState.handle(delta);
			}
			else
			{
				switching = currentState.start();
			}
		}
		else
		{
			// perform end processing until it reports as done
			if (!currentState.end())
			{
				currentState = nextState;
				currentStateID = nextStateID;
				nextState = null;
			}
		}
	}
	
	/**
	 * Sets the state machine to the state recognized by the ID number 
	 * @param ID - number identifying the state to switch to
	 */
	public void setState(int ID)
	{
		if (currentState != null)
		{
			nextState = stateList.get(ID);
			nextStateID = ID;
			// set the system to know that it's switching states and should perform
			// proper operations
			switching = (nextState != null);
		}
		else
		{
			currentStateID = ID;
			currentState = stateList.get(ID);
			switching = (currentState != null);
		}
	}
	
	public String toString()
	{
		String output = "";
		for (Integer i : stateList.keySet())
		{
			output += i + ":" + stateList.get(i) + "\n";
		}
		output += "CurrentState : " + currentState;
		return output;
	}

	/**
	 * @return the ID of the current scene
	 */
	public int getCurrentStateID()
	{
		return currentStateID;
	}
	
	/**
	 * @param id
	 * @return the state in the machine with this id
	 */
	public State getState(int id)
	{
		return stateList.get(id);
	}

	/**
	 * Deletes states and empties the stateList, thus returning the state machine to its initial state
	 */
	public void clear()
	{
		currentState = null;
		nextState = null;
		stateList.clear();
	}
	
	/**
	 * @return the number of registered state IDs in the machine
	 */
	public int getStateCount()
	{
		return stateList.keySet().size();
	}
}
