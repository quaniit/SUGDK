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

	@Override
	public boolean keyDown(int key) {
		return stateMachine.getCurrentState().keyDown(key);
	}
	
	@Override
	public boolean keyTyped(char keyChar) {
		// do nothing
		return stateMachine.getCurrentState().keyTyped(keyChar);
	}

	@Override
	public boolean keyUp(int key) {
		return stateMachine.getCurrentState().keyUp(key);
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		return stateMachine.getCurrentState().mouseMoved(arg0, arg1);
	}

	@Override
	public boolean scrolled(int arg0) {
		return stateMachine.getCurrentState().scrolled(arg0);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return stateMachine.getCurrentState().touchDown(x, y, pointer, button);
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return stateMachine.getCurrentState().touchDragged(arg0, arg1, arg2);
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return stateMachine.getCurrentState().touchUp(x, y, pointer, button);
	}
	
	/**
	 * Clear resources
	 */
	@Override
	public void end() {
		stateMachine.clear();
	}
	
	public int getCurrentState()
	{
		return stateMachine.getCurrentStateID();
	}
}
