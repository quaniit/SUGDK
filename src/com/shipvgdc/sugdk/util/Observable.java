package com.shipvgdc.sugdk.util;

import java.util.ArrayList;

/**
 * 
 * @author nhydock
 */
public abstract class Observable {
	
	/**
	 * Types of notfications that can be passed to this observer
	 */
	public static interface Notification{}
	
	/**
	 * Serial ID identifying the observable class
	 */
	
	private ArrayList<Observer<? extends Observable>> observers = new ArrayList<Observer<? extends Observable>>();
	
	/**
	 * Notifies all observers
	 * @param type - specific type of update to perform
	 */
	public void notify(Notification type)
	{
		notify(type, (Object)null);
	}
	
	/**
	 * Notifies all observers
	 * @param type - specific type of update
	 * @param values - values to pass onto the observers
	 */
	public void notify(Notification type, Object... values)
	{
		for (int i = 0; i < observers.size(); i++)
		{
			observers.get(i).update(type, values);
		}
	}
	
	/**
	 * Adds an observer to observe this observable
	 * @param o
	 */
	public void addObserver(Observer<? extends Observable> o)
	{
		if (o != null)
		{
			observers.add(o);
		}
	}
	
	/**
	 * Removes an observer from the observable if it's observing it
	 * @param o
	 */
	public void removeObserver(Observer<? extends Observable> o)
	{
		if (o != null)
		{
			observers.remove(o);
		}
	}
	
	/**
	 * @return the amount of observers observing this observable
	 */
	public int observerCount()
	{
		return observers.size();
	}
	
}
