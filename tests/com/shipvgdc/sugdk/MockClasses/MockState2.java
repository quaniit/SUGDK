package com.shipvgdc.sugdk.MockClasses;

public class MockState2 extends MockState
{
	public static final int ID = 1;
	
	public MockState2(MockStateBasedSystem parent) {
		super(parent);
	}
	
	@Override
	public boolean start()
	{
		this.timer = 2.0f;
		return false;
	}
	
	@Override
	public void handle(float delta)
	{
		this.timer += delta/2;
	}
	
	@Override
	public boolean end()
	{
		this.timer = -1.0f;
		return false;
	}
	public int getID()
	{
		return ID;
	}
}