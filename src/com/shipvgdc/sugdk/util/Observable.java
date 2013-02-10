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
	
	private ArrayList<Observer<? extends Observable>> observers;
	
	/**
	 * Notifies all observers
	 * @param type - specific type of update
	 * @param values - values to pass onto the observers
	 */
	public void notify(Notification type, Object... values)
	{
		for (int i = 0; i < observers.size(); i++)
		{
			observers.get(i).update(this.serial(), type, values);
		}
	}
	
	/**
	 * Gets the class serial id
	 * @return serial_id
	 */
	abstract public int serial();
	
	public void addObserver(Observer<? extends Observable> o)
	{
		observers.add(o);
	}
	
	public void removeObserver(Observer<? extends Observable> o)
	{
		observers.remove(o);
	}
	
	public int observerCount()
	{
		return observers.size();
	}
	
}
