package com.shipvgdc.sugdk.scenes;

/**
 * If you want to create a scene that comprises of substates, you're probably going to be using the
 * StateMachine provided and have states specific for that System.  This convenience class just packages
 * up common patterns and a premade stateMachine for you to use.
 * @author nhydock
 */
public abstract class StateBasedSystem implements GameSystem
{
	/**
	 * Convoluted?  Yes!
	 * Basicly, we say StateBasedSystems have a state machine that consists of GameStates
	 * that manage this kind of state system.
	 */
	protected GameStateMachine<? extends GameState<? extends GameSystem>> stateMachine;
	
	@Override
	public void update(float delta)
	{
		stateMachine.handleCurrentState(delta);
	}
	
	/**
	 * Generates the states and state machine
	 */
	abstract protected void initStates();

	/**
	 * Changes the state machine's state
	 * @param id - ID of the next state to switch to
	 */
	abstract public void changeState(int id);
}
