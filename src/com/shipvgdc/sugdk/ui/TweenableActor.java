package com.shipvgdc.sugdk.ui;

import static com.shipvgdc.sugdk.ui.ActorTweens.Color;
import static com.shipvgdc.sugdk.ui.ActorTweens.Height;
import static com.shipvgdc.sugdk.ui.ActorTweens.Opacity;
import static com.shipvgdc.sugdk.ui.ActorTweens.Rotate;
import static com.shipvgdc.sugdk.ui.ActorTweens.Scale;
import static com.shipvgdc.sugdk.ui.ActorTweens.Size;
import static com.shipvgdc.sugdk.ui.ActorTweens.Width;
import static com.shipvgdc.sugdk.ui.ActorTweens.X;
import static com.shipvgdc.sugdk.ui.ActorTweens.XY;
import static com.shipvgdc.sugdk.ui.ActorTweens.Y;
import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TweenableActor implements TweenAccessor<Actor> {
	
	@Override
	public int getValues(Actor target, int type, float[] val) {
		switch (type)
		{
			case X:
				val[0] = target.getX();
				break;
			case Y:
				val[0] = target.getY();
				break;
			case XY:
				val[0] = target.getX();
				val[1] = target.getY();
				break;
			case Opacity:
				val[0] = target.getColor().a;
				break;
			case Color:
				Color c = target.getColor();
				val[0] = c.r;
				val[1] = c.g;
				val[2] = c.b;
				val[3] = c.a;
				break;
			case Width:
				val[0] = target.getWidth();
				break;
			case Height:
				val[1] = target.getHeight();
				break;
			case Size:
				val[0] = target.getWidth();
				val[1] = target.getHeight();
				break;
			case Scale:
				val[0] = target.getScaleX();
				val[1] = target.getScaleY();
				break;
			case Rotate:
				val[0] = target.getRotation();
				break;
			
			default:
				return 0;
			
		}
		return 1;
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
				target.setWidth(val[0]);
				break;
			case Height:
				target.setHeight(val[1]);
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
