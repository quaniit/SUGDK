package com.shipvgdc.sugdk.MockClasses;

public class MockState3 extends MockState {

	public static final int ID = 2;
	public int counter = 0;
	public boolean poststart = false;
	
	public MockState3(MockStateBasedSystem parent) {
		super(parent);
	}

	@Override
	public boolean start()
	{
		if (counter < 5)
		{
			counter++;
			return true;
		}
		return false;
	}
	
	public void handle(float delta)
	{
		poststart = true;
	}
	
	public boolean end()
	{
		if (counter > 0)
		{
			counter--;
			return true;
		}
		return false;
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
}
