package sugdk.graphics;

import java.util.ArrayList;

import sugdk.core.GameFrame;

/**
 * Animator
 * @author nhydock
 *
 * Handles all timing specific animations so simple HTML5/CSS-like animations can render 
 * without being dependent on FPS.
 */
public class Animator {

	private static GameFrame parent = null;
	//arraylist of parallel animatorlisteners and their start times
	private static ArrayList<Object[]> observers = new ArrayList<Object[]>();
	
	//keep constructor private so then people know not to make Animator objects
	private Animator(){}
	
	public static void setFrame(GameFrame p)
	{
		parent = p;
	}
	
	public static void update(){
		//don't try updating if parent isn't set
		if (parent == null)
			return;
		
		long time = parent.getCurrTime();
		for (int i = 0; i < observers.size(); i++)
		{
			Object[] index = observers.get(i);
			AnimatorListener a = (AnimatorListener)index[0];
			long sTime = (Long)index[1];
			
			a.update(time-sTime);
			
			if (a.getValue() >= a.getDestValue())
			{
				a.done();
				observers.remove(i);
			}
		}
	}
	
	
	public static void add(AnimatorListener a)
	{
		observers.add(new Object[]{a, parent.getCurrTime()});
	}
}
