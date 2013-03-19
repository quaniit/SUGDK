package com.shipvgdc.sugdk.scenes;

import com.shipvgdc.sugdk.util.Notification;

/**
 * 
 */
public class SceneNotificationTypes
{
/**
 * Interface for creating notifications sent out by the display
 */
public static interface DisplayNotification extends Notification{}
/**
 * Interface for creating notifications sent out by the game logic system
 */
public static interface GameNotification extends Notification{}
/**
 * Interface for creating notifications sent out by the controller
 */
public static interface ControllerNotification extends Notification{}
}
