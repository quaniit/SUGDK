package com.shipvgdc.sugdk.scenes;

import com.badlogic.gdx.InputProcessor;
import com.shipvgdc.sugdk.util.Bridge;
import com.shipvgdc.sugdk.util.Observer;

import com.shipvgdc.sugdk.scenes.SceneNotificationTypes.ControllerNotification;
import com.shipvgdc.sugdk.scenes.SceneNotificationTypes.DisplayNotification;

/**
 * Linking controller class that'll take display.getUI() signals from an display.getUI() processor 
 * and generates signals that the system can understand.
 * @author nhydock
 * 
 */
public abstract class GameController extends Bridge<ControllerNotification, DisplayNotification> implements InputProcessor
{

	/**
	 * The game controller is capable of forwarding input messages to a Display's stage, 
	 * however it is recommended that you do the processing of things
	 * that will affect Systems in here and only visual effects dependent on input
	 * within the stage.
	 * 
	 * It is not required that there be a stage to forward to for this the controller to work
	 */
	protected GameDisplay<? extends GameSystem> display;
	
	/**
	 * The parent system that the controller will send notifications to depending on input
	 */
	protected GameSystem system;
	
	/**
	 * Sets the system the controller will send notifications to
	 * @param system
	 */
	protected void setSystem(GameSystem system)
	{
		this.system = system;
	}
	/**
	 * Sets the display that the controller will forward messages to
	 * @param display
	 */
	protected void setDisplay(GameDisplay<? extends GameSystem> display)
	{
		this.display = display;
	}
	
	@Override
	public void addObserver(Observer<ControllerNotification> o)
	{
		super.addObserver(o);
		if (o instanceof GameSystem)
		{
			setSystem((GameSystem)o);
		}
	}
	
	/*
	 * Override these methods to send out notifications
	 */
	@Override
	public boolean keyDown(int arg0) {
		if (display.getUI() != null)
		{
			return display.getUI().keyDown(arg0);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		if (display.getUI() != null)
		{
			return display.getUI().keyTyped(arg0);
		}
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		if (display.getUI() != null)
		{
			return display.getUI().keyUp(arg0);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		if (display.getUI() != null)
		{
			return display.getUI().mouseMoved(arg0, arg1);
		}
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		if (display.getUI() != null)
		{
			return display.getUI().scrolled(arg0);
		}
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		if (display.getUI() != null)
		{
			return display.getUI().touchDown(arg0, arg1, arg2, arg3);
		}
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		if (display.getUI() != null)
		{
			return display.getUI().touchDragged(arg0, arg1, arg2);
		}
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		if (display.getUI() != null)
		{
			return display.getUI().touchUp(arg0, arg1, arg2, arg3);
		}
		return false;
	}

}
