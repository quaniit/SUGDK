package com.shipvgdc.sugdk.tween;

import static com.shipvgdc.sugdk.tween.Tweens.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * @author nhydock
 *
 */
public class TweenableSprite implements TweenAccessor<Sprite> {
	
	@Override
	public int getValues(Sprite target, int type, float[] val) {
		int rLen = -1;	//number of values returned
		switch (type)
		{
			case X:
				val[0] = target.getX();
				rLen = 1;
				break;
			case Y:
				val[0] = target.getY();
				rLen = 1;
				break;
			case XY:
				val[0] = target.getX();
				val[1] = target.getY();
				rLen = 2;
				break;
			case Opacity:
				val[0] = target.getColor().a;
				rLen = 1;
				break;
			case Color:
				Color c = target.getColor();
				val[0] = c.r;
				val[1] = c.g;
				val[2] = c.b;
				val[3] = c.a;
				rLen = 4;
				break;
			case Width:
				val[0] = target.getWidth();
				rLen = 1;
				break;
			case Height:
				val[0] = target.getHeight();
				rLen = 1;
				break;
			case Size:
				val[0] = target.getWidth();
				val[1] = target.getHeight();
				rLen = 2;
				break;
			case Scale:
				val[0] = target.getScaleX();
				val[1] = target.getScaleY();
				rLen = 2;
				break;
			case Rotate:
				val[0] = target.getRotation();
				rLen = 1;
				break;
		}
		return rLen;
	}
	
	@Override
	public void setValues(Sprite target, int type, float[] val) {
		switch (type)
		{
			case X:
				target.setX(val[0]);
				break;
			case Y:
				target.setY(val[0]);
				break;
			case XY:
				target.setPosition(val[0], val[1]);
				break;
			case Opacity:
				Color c = target.getColor();
				target.setColor(c.r, c.g, c.b, val[0]);
				break;
			case Color:
				target.setColor(val[0], val[1], val[2], val[3]);
				break;
			case Size:
				target.setSize(val[0], val[1]);
				break;
			case Width:
				target.setSize(val[0], target.getHeight());
				break;
			case Height:
				target.setSize(target.getWidth(), val[0]);
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
