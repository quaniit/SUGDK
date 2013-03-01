package com.shipvgdc.sugdk.util;

import com.shipvgdc.sugdk.util.Observable.Notification;

/**
 * @author nhydock
 *
 * @param <T>
 */
public interface Observer<T extends Observable> {
	
	/**
	 * Updates properites of the observer
	 * @param type
	 * @param values
	 */
	public void update(Notification type, Object... values);
}
