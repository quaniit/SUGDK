package com.shipvgdc.sugdk.scenes;

import java.util.HashMap;

import com.badlogic.gdx.Game;

/**
 * SceneManager
 * <p/>
 * SceneManager is a state machine manager for use with libGdx that helps abstract
 * the setting and creation of scenes.
 * @author nhydock
 *
 */
public class SceneManager {
	
	private static SceneManager sm;
	
	/**
	 * @return current running scene manager of the system, or makes one
	 */
	public static SceneManager getManager()
	{
		if (sm == null)
			sm = new SceneManager();
		return sm;
	}
	
	private HashMap<Integer, Object> sceneList;
	private HashMap<Integer, Scene<?,?,?>> persistentScenes;
	private HashMap<Integer, Class<? extends Scene<?,?,?>>> generativeScenes;
	
	private Game linkedGame;
	
	
	/**
	 * Creates a new scene manager
	 * @param game - game to link the scene manager to
	 */
	private SceneManager()
	{
		sceneList = new HashMap<Integer, Object>();
		persistentScenes = new HashMap<Integer, Scene<?,?,?>>();
		generativeScenes = new HashMap<Integer, Class<? extends Scene<?,?,?>>>();
	}
	
	/**
	 * Links a SceneManager to a libGdx game
	 * @param game
	 */
	public void setLink(Game game)
	{
		linkedGame = game;
	}
	
	/**
	 * Adds a new scene to the manager
	 * When provided with an actual scene instance, it'll know that the scene is persistent
	 * @param id - identifying number for the scene
	 * @param scene - scene class
	 * @throws Exception when id is taken
	 * @throws NullPointerException when scene is null
	 */
	public void addScene(int id, Scene<?, ?, ?> scene) throws Exception, NullPointerException
	{
		if (!sceneList.containsKey(id))
		{
			if (scene != null)
			{
				persistentScenes.put(id, scene);
				sceneList.put(id, scene);
			}
			else
			{
				throw (new NullPointerException("Scene is null"));
			}
		}
		else
		{
			throw (new Exception("ID already taken by another scene"));
		}
	}
	
	/**
	 * Adds a new scene to the manager
	 * These scenes will dispose of themselves thanks to the garbage collector when switched out from being active
	 *  and will generate themselves again when switched to
	 * @param id - identifying number for the scene
	 * @param scene - scene class
	 * @throws Exception when id is taken
	 * @throws NullPointerException when scene is null
	 */
	public void addScene(int id, Class<? extends Scene<?, ?, ?>> scene) throws Exception, NullPointerException
	{
		if (!sceneList.containsKey(id))
		{
			if (scene != null)
			{
				generativeScenes.put(id, scene);
				sceneList.put(id, scene);
			}
			else
			{
				throw (new NullPointerException("Scene is null"));
			}
		}
		else
		{
			throw (new Exception("ID already taken by another scene"));
		}
	}
	
	/**
	 * Removes a scene from the manager
	 * @param id
	 * @throws Exception 
	 */
	public void popScene(int id) throws Exception
	{
		if (!sceneList.containsKey(id))
		{
			if (linkedGame.getScreen() == sceneList.get(id))
			{
				throw (new Exception("Scene is currently in use, please change scenes and try again"));
			}
			else if (persistentScenes.containsKey(id))
			{
				persistentScenes.remove(id);
			}
			else if (generativeScenes.containsKey(id))
			{
				generativeScenes.remove(id);
			}
		}
		else
		{
			sceneList.remove(id);
		}
	}
	
	/**
	 * Changes the active scene
	 * @param sceneID - id by which the scene is identified
	 * @throws NullPointerException if the manager has not yet been bound to a game
	 */
	public void setScene(int sceneID) throws NullPointerException
	{
		if (linkedGame != null)
		{
			Scene<?,?,?> s = null;
			if (persistentScenes.containsKey(sceneID))
			{
				s = persistentScenes.get(sceneID);
			}
			else if (generativeScenes.containsKey(sceneID))
			{
				try {
					s = generativeScenes.get(sceneID).newInstance();
				}
				catch (InstantiationException e) {
					e.printStackTrace();
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			if (s != null)
			{
				linkedGame.setScreen(s);
			}
		}
		else
		{
			throw (new NullPointerException("Manager has not yet been bound to a game"));
		}
	}
}
