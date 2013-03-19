package com.shipvgdc.sugdk.util;

import java.util.ArrayList;
import com.shipvgdc.sugdk.util.Notification;

/**
 * 
 * @author nhydock
 * @param <Type> Type of notifications that it can generate/notify observers of
 */
public abstract class Observable<Type extends Notification> {
	
	
	/**
	 * Serial ID identifying the observable class
	 */
	
	private ArrayList<Observer<Type>> observers = new ArrayList<Observer<Type>>();
	
	/**
	 * Notifies all observers
	 * @param type - specific type of update to perform
	 */
	public void notify(Type type)
	{
		notify(type, (Object)null);
	}
	
	/**
	 * Notifies all observers
	 * @param type - specific type of update
	 * @param values - values to pass onto the observers
	 */
	public void notify(Type type, Object... values)
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
	public void addObserver(Observer<Type> o)
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
	public void removeObserver(Observer<Type> o)
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
	
	/**
	 * Removes all observers from this observable object
	 */
	public void clearObservers() {
		observers.clear();
	}
}
