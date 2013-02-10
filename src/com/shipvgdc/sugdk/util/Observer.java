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
	 * @param serial_id - class id of the observable object so it knows it can handle it
	 * @param type - 
	 * @param values
	 */
	public void update(int serial_id, Notification type, Object[] values);
}
