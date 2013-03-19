package com.shipvgdc.sugdk.tween;

import static org.junit.Assert.*;

import org.junit.*;

import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Linear;

public class ActorTweeningTest {

	public TweenManager tm;
	
	@Before
	public void init()
	{
		Tween.registerAccessor(Actor.class, new TweenableActor());
		tm = new TweenManager();
	}
	
	@Test
	public void testTweenPosition() {
		Actor a = new Actor();
		
		a.setPosition(0.0f, 0.0f);
		assertEquals(0, a.getX(), 0.005f);
		assertEquals(0, a.getX(), 0.005f);
		
		//Set X
		Tween.set(a, Tweens.X).target(5.0f).start(tm);
		assertEquals(1, tm.getRunningTweensCount());
		tm.update(1.0f);
		assertEquals(5.0f, a.getX(), 0.005f);
		//we update again because a tween will only be removed from the manager
		//if it's called to update and has already been finished
		tm.update(1.0f);
		assertEquals(0, tm.getRunningTweensCount());
		
		//Set Y
		Tween.set(a, Tweens.Y).target(10.0f).start(tm);
		assertEquals(1, tm.getRunningTweensCount());
		tm.update(1.0f);
		assertEquals(10.0f, a.getY(), 0.005f);
		tm.update(1.0f);
				
		//Set X and Y
		Tween.set(a, Tweens.XY).target(20.0f, 50.0f).start(tm);
		assertEquals(1, tm.getRunningTweensCount());
		tm.update(1.0f);
		assertEquals(20.0f, a.getX(), 0.005f);
		assertEquals(50.0f, a.getY(), 0.005f);
		tm.update(1.0f);
				
		//reset
		a.setPosition(0.0f, 0.0f);
		
		//we use Linear tweening just to make things easier for ourselves to test
		//Interpolate X
		Tween.to(a, Tweens.X, 1.0f).target(5.0f).ease(Linear.INOUT).start(tm);
		assertEquals(1, tm.getRunningTweensCount());
		tm.update(0.5f);
		assertEquals(2.5f, a.getX(), 0.005f);
		tm.update(0.5f);
		assertEquals(5.0f, a.getX(), 0.005f);
		tm.update(1.0f);
		assertEquals(5.0f, a.getX(), 0.005f); //make sure it doesn't change anymore after it's done
		tm.update(0.1f);
		
		//Interpolate Y
		Tween.to(a, Tweens.Y, 1.0f).target(50.0f).ease(Linear.INOUT).start(tm);
		assertEquals(1, tm.getRunningTweensCount());
		tm.update(0.5f);
		assertEquals(25.0f, a.getY(), 0.005f);
		tm.update(0.5f);
		assertEquals(50.0f, a.getY(), 0.005f);
		tm.update(1.0f);
		assertEquals(5.0f, a.getX(), 0.005f); //in case it went over it might not be done until it corrects itself
		tm.update(0.1f);
		
		//Interpolate X and Y
		a.setPosition(0.0f, 0.0f);
		Tween.to(a, Tweens.XY, 1.0f).target(20.0f, 50.0f).ease(Linear.INOUT).start(tm);
		assertEquals(1, tm.getRunningTweensCount());
		tm.update(0.25f);
		assertEquals(5.0f, a.getX(), 0.005f);
		assertEquals(12.5f, a.getY(), 0.005f);
		tm.update(0.25f);
		assertEquals(10.0f, a.getX(), 0.005f);
		assertEquals(25.0f, a.getY(), 0.005f);
		tm.update(0.5f);
		assertEquals(20.0f, a.getX(), 0.005f);
		assertEquals(50.0f, a.getY(), 0.005f);
		tm.update(1.0f);
		
	}

	
}
