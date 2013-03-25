package com.shipvgdc.sugdk.tween;

import static com.shipvgdc.sugdk.tween.Tweens.*;
import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Adds Universal Tween Engine support to libGdx actors
 * @author nhydock
 *
 */
public class TweenableActor implements TweenAccessor<Actor> {
	
	@Override
	public int getValues(Actor target, int type, float[] val) {
		int returnlength = -1;
		
		switch (type)
		{
			case X:
				val[0] = target.getX();
				returnlength = 1;
				break;
			case Y:
				val[0] = target.getY();
				returnlength = 1;
				break;
			case XY:
				val[0] = target.getX();
				val[1] = target.getY();
				returnlength = 2;
				break;
			case Opacity:
				val[0] = target.getColor().a;
				returnlength = 1;
				break;
			case Color:
				Color c = target.getColor();
				val[0] = c.r;
				val[1] = c.g;
				val[2] = c.b;
				val[3] = c.a;
				returnlength = 4;
				break;
			case Width:
				val[0] = target.getWidth();
				returnlength = 1;
				break;
			case Height:
				val[0] = target.getHeight();
				returnlength = 1;
				break;
			case Size:
				val[0] = target.getWidth();
				val[1] = target.getHeight();
				returnlength = 2;
				break;
			case Scale:
				val[0] = target.getScaleX();
				val[1] = target.getScaleY();
				returnlength = 2;
				break;
			case Rotate:
				val[0] = target.getRotation();
				returnlength = 1;
				break;
			
		}
		return returnlength;
	}
	
	@Override
	public void setValues(Actor target, int type, float[] val) {
		switch (type)
		{
			case X:
				target.setX(val[0]);
				break;
			case Y:
				target.setY(val[0]);
				break;
			case XY:
				target.setX(val[0]);
				target.setY(val[1]);
				break;
			case Opacity:
				Color c = target.getColor();
				target.setColor(c.r, c.g, c.b, val[0]);
				break;
			case Color:
				try
				{
					target.setColor(val[0], val[1], val[2], val[3]);
				}
				catch (IndexOutOfBoundsException e)
				{
					target.setColor(val[0], val[1], val[2], target.getColor().a);
				}
				break;
			case Width:
				target.setWidth(val[0]);
				break;
			case Height:
				target.setHeight(val[0]);
				break;
			case Size:
				target.setSize(val[0], val[1]);
				break;
			case Scale:
				target.setScale(val[0], val[1]);
				break;
			case Rotate:
				target.setRotation(val[0]);
				break;
		}
	}
}
