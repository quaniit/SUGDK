package com.shipvgdc.sugdk.util;

import com.shipvgdc.sugdk.util.Notification;

/**
 * @author nhydock
 *
 * @param <Type> type of notifications the observer can recieve
 */
public interface Observer<Type extends Notification> {
	
	/**
	 * Updates properites of the observer
	 * @param type
	 * @param values
	 */
	void update(Type type, Object... values);
}
