package com.shipvgdc.sugdk.scenes;

/**
 * If you want to create a scene that comprises of substates, you're probably going to be using the
 * StateMachine provided and have states specific for that System.  This convenience class just packages
 * up common patterns and a premade stateMachine for you to use.
 * @author nhydock
 * @param <State> 
 */
public abstract class StateBasedSystem<State extends GameState<? extends GameSystem>> extends GameSystem
{
	/**
	 * Convoluted?  Yes!
	 * Basicly, we say StateBasedSystems have a state machine that consists of GameStates
	 * that manage this kind of state system.
	 */
	protected GameStateMachine<State> stateMachine;
	
	/**
	 * Generates the states and state machine
	 */
	abstract protected void initStates();

	/**
	 * Changes the state machine's state
	 * @param id - ID of the next state to switch to
	 */
	public void changeState(int id)
	{
		stateMachine.setState(id);
	}
	
	@Override
	public void update(float delta) {
		stateMachine.handleCurrentState(delta);
	}

	/**
	 * Notifies the active state with notfications sent to the system
	 */
	@Override
	public void notify(Notification type, Object... values)
	{
		super.notify(type, values);
		stateMachine.getCurrentState().notify(type, values);
	}
	
	/**
	 * Clear resources
	 */
	@Override
	public void end() {
		stateMachine.clear();
	}
	
	/**
	 * @return the id of the current state
	 */
	public int getCurrentState()
	{
		return stateMachine.getCurrentStateID();
	}
}
