package com.shipvgdc.sugdk.scenes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.shipvgdc.sugdk.MockClasses.MockState;
import com.shipvgdc.sugdk.MockClasses.MockState2;
import com.shipvgdc.sugdk.MockClasses.MockState3;

public class GameStateMachineTest {

	@Test
	public void testInitialization() {
		GameStateMachine<MockState> machine = new GameStateMachine<MockState>();
		assertNotNull(machine);
	}
	
	@Test
	public void testAddingScenes()
	{
		GameStateMachine<MockState> machine = new GameStateMachine<MockState>();
		
		MockState m = new MockState(null);
		MockState m2 = new MockState2(null);
		
		machine.addState(m);
		machine.addState(m2);
		
		//should not set state until told, even if it was the first state in the machine
		assertNull(machine.getCurrentState());
				
		assertEquals(2, machine.getStateCount());
		
		machine.setState(MockState.ID);
		assertEquals(m, machine.getCurrentState());
		assertEquals(MockState.ID, machine.getCurrentStateID());
	}

	@Test
	public void testSwitchingScenes()
	{
		GameStateMachine<MockState> machine = new GameStateMachine<MockState>();
		
		MockState m = new MockState(null);
		MockState m2 = new MockState2(null);
		
		machine.addState(m);
		machine.addState(m2);
		
		machine.setState(MockState.ID);
	
		//even though the state has been set and it was the first state, it has yet to be started
		assertEquals(0.0f, machine.getCurrentState().timer, 0.005f);
		
		machine.handleCurrentState(500.0f);
		//no matter the amount of time passed, since should only call start because that's what needs to be done until start is done
		assertEquals(1.0f, machine.getCurrentState().timer, 0.005f);
		
		machine.handleCurrentState(499.0f);
		//now if we update again, the amount of time passed should play a factor
		assertEquals(500.0f, machine.getCurrentState().timer, 0.005f);
		
		//now we switch states
		machine.setState(MockState2.ID);
		
		//current state should still be MockState with nothing changed
		assertEquals(m, machine.getCurrentState());
		assertEquals(500.0f, machine.getCurrentState().timer, 0.005f);
		
		//once we update it should call the state to end
		machine.handleCurrentState(1.0f);
		//since the state is done the current state should be MockState2
		assertEquals(m2, machine.getCurrentState());
		assertEquals(0.0f, machine.getCurrentState().timer, 0.005f);
		//m should have been operated on though, which means when going out m had one last chance
		//to send any notifications to the system's observers
		assertEquals(0.5f, m.timer, 0.005f);
		
		//update again to see the new state start
		//should work the same as the first time we tested starting a state
		machine.handleCurrentState(500.0f);
		assertEquals(2.0f, machine.getCurrentState().timer, 0.005f);
	}
	
	@Test
	public void testRepeatingStartAndEnd()
	{
		GameStateMachine<MockState> machine = new GameStateMachine<MockState>();
		
		MockState3 m = new MockState3(null);
		MockState m2 = new MockState2(null);
		
		machine.addState(m);
		machine.addState(m2);
		
		machine.setState(MockState3.ID);
		
		assertEquals(m, machine.getCurrentState());
		assertEquals(0, m.counter);
		assertFalse(m.poststart);
		
		machine.handleCurrentState(0.0f);
		machine.handleCurrentState(0.0f);
		machine.handleCurrentState(0.0f);
		machine.handleCurrentState(0.0f);
		machine.handleCurrentState(0.0f);
		
		assertEquals(5, m.counter);
		
		//sixth pass should end start
		machine.handleCurrentState(0.0f);
		
		//now we should handling
		assertFalse(m.poststart);
		machine.handleCurrentState(0.0f);
		assertTrue(m.poststart);
		assertEquals(5, m.counter);
		
		machine.setState(MockState2.ID);
		
		assertEquals(m, machine.getCurrentState());
		machine.handleCurrentState(0.0f);
		machine.handleCurrentState(0.0f);
		machine.handleCurrentState(0.0f);
		machine.handleCurrentState(0.0f);
		machine.handleCurrentState(0.0f);
		assertEquals(0, m.counter);
		
		machine.handleCurrentState(0.0f);
		assertEquals(m2, machine.getCurrentState());
		
	}
}
