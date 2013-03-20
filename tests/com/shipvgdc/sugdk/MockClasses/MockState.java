package com.shipvgdc.sugdk.MockClasses;

import com.shipvgdc.sugdk.scenes.GameState;
import com.shipvgdc.sugdk.scenes.SceneNotificationTypes.ControllerNotification;

public class MockState extends GameState<MockStateBasedSystem> {

	public float timer;
	public static final int ID = 0;
	public MockState(MockStateBasedSystem parent) {
		super(parent);
	}

	@Override
	public void update(ControllerNotification type, Object... values) {
		System.out.println("Recieved a notification");
	}

	@Override
	public void handle(float delta) {
		this.timer += delta;
	}

	@Override
	public boolean start() {
		this.timer = 1.0f;
		return false;
	}

	@Override
	public boolean end() {
		this.timer = 0.5f;
		return false;
	}

	@Override
	public int getID() {
		return ID;
	}
	
}
