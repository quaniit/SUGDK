package com.shipvgdc.sugdk.util;

/**
 * Bridges provide a lock and key mechanism link that'll take in
 * notifications of one type and pop out notifications of another type.
 * <p/>
 * Another way to think about a bridge is to think of it as an Observable Observer
 * @author nhydock
 *
 * @param <S> Type of notification it sends out
 * @param <R> Type of notification it recieves
 */
public abstract class Bridge<S extends Notification, R extends Notification> extends Observable<S> implements Observer<R> {

}
