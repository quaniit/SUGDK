package com.shipvgdc.sugdk.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Scene
 * <p/>
 * More logical and modular handling of the engine's current state than swapping out "Screens".
 * Scenes are wrappers of Screens in which the logic and display are separated into different components
 * that are encapsulated from one another while still being coupled.  It makes for a well structured
 * MVC designed game system to work with.
 * @author nhydock
 *
 * @param <S> A GameSystem extended class
 * @param <D> A GameDisplay extended class whose system type is the same as S
 * @param <C> Input controller to convert UI and Key input into command messages for the system
 */
public class Scene<S extends GameSystem, D extends GameDisplay<S>, C extends GameController> implements Screen {

	protected S system;
	protected D display;
	protected C controller;
	
	/**
	 * Links the components together
	 */
	public void link()
	{
		system.addObserver(display);
		display.addObserver(controller);
		controller.addObserver(system);
	}
	
	/**
	 * Ends the scene
	 */
	@Override
	public void dispose() {
		system.clearObservers();
		display.clearObservers();
		controller.clearObservers();
		system.end();
		display.dispose();
	}
	
	/**
	 * Ends the system
	 */
	@Override
	public void hide() {
		system.end();
	}
	
	@Override
	public void pause() {
		
	}
	
	/**
	 * Performs system updates and rendering
	 */
	@Override
	public void render(float delta) {
		//before rendering things, we need to update the system
		system.update(delta);
		
		//then we update the display
		display.update(delta);
		
		//and now we can finally render the display
		display.render();
	}
	
	/**
	 * Resize the display to the new dimensions
	 */
	@Override
	public void resize(int width, int height) {
		display.resize(width, height);
	}
	
	@Override
	public void resume() {
		// set the current processor to be the system's input handling
		Gdx.input.setInputProcessor(controller);	
	}
	
	/**
	 * Start up the scene when it's set to be the current scene
	 */
	@Override
	public void show() {
		// start the system and renderer
		system.start();
		display.init();
		
		//allow the system to do any first time processing that can 
		// only be done after the display has been initialized
		system.postStart();
		
		// set the current processor to be the system's input handling
		Gdx.input.setInputProcessor(controller);
	}
	
}
