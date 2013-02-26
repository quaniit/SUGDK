package com.shipvgdc.sugdk.scenes;

import com.badlogic.gdx.InputProcessor;
import com.shipvgdc.sugdk.util.Observable;

/**
 * Linking controller class that'll take input signals from an input processor 
 * and generates signals that the system can understand.
 * @author nhydock
 * 
 * @param <S> 
 */
public abstract class GameController<S extends GameSystem> extends Observable implements InputProcessor{

	/**
	 * The game controller is capable of forwarding input messages to things
	 * such as Stages, however it is recommended that you do the processing of things
	 * that will affect Systems in here and only visual effects dependent on input
	 * within the stage.
	 * 
	 * It is not required that there be a stage to forward to for this the controller to work
	 */
	protected InputProcessor input;
	
	/**
	 * The parent system that the controller will send notifications to depending on input
	 */
	protected S system;
	
	/**
	 * Sets the system the controller will send notifications to
	 * @param system
	 */
	public void setSystem(S system)
	{
		this.system = system;
	}
	
	/**
	 * Sets the input processor that the controller will forward messages to
	 * @param input
	 */
	public void setInputForward(InputProcessor input)
	{
		this.input = input;
	}
	
	/*
	 * Override these methods to send out notifications
	 */
	@Override
	public boolean keyDown(int arg0) {
		if (input != null)
		{
			return input.keyDown(arg0);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		if (input != null)
		{
			return input.keyTyped(arg0);
		}
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		if (input != null)
		{
			return input.keyUp(arg0);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		if (input != null)
		{
			return input.mouseMoved(arg0, arg1);
		}
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		if (input != null)
		{
			return input.scrolled(arg0);
		}
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		if (input != null)
		{
			return input.touchDown(arg0, arg1, arg2, arg3);
		}
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		if (input != null)
		{
			return input.touchDragged(arg0, arg1, arg2);
		}
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		if (input != null)
		{
			return input.touchUp(arg0, arg1, arg2, arg3);
		}
		return false;
	}

}
