package com.shipvgdc.sugdk.ui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Quaternion;

import static com.shipvgdc.sugdk.ui.Tweens.*;

import aurelienribon.tweenengine.TweenAccessor;

public class TweenableCamera implements TweenAccessor<Camera>{

	@Override
	public int getValues(Camera target, int type, float[] val) {
		switch (type)
		{
			case X:
				val[0] = target.position.x;
				break;
			case Y:
				val[0] = target.position.y;
				break;
			case Z:
				val[0] = target.position.z;
				break;
			case XY:
				val[0] = target.position.x;
				val[1] = target.position.y;
				break;
			case XYZ:
				val[0] = target.position.x;
				val[1] = target.position.y;
				val[2] = target.position.z;
				break;
			case Rotate:
				Quaternion q = new Quaternion();
				target.combined.getRotation(q);
				val[0] = q.w;
				val[1] = q.x;
				val[2] = q.y;
				val[3] = q.z;
				break;
			case CameraTweens.LookAt:
				val[0] = target.direction.x;
				val[1] = target.direction.y;
				val[2] = target.direction.z;
			default:
				return 0;
		}
		
		return 1;
	}

	@Override
	public void setValues(Camera target, int type, float[] val) {
		switch (type)
		{
			case X:
				target.translate(val[0], 0, 0);
				break;
			case Y:
				target.translate(0, val[0], 0);
				break;
			case Z:
				target.translate(0, 0, val[0]);
				break;
			case XY:
				target.translate(val[0], val[1], 0);
				break;
			case XYZ:
				target.translate(val[0], val[1], val[3]);
				break;
			case Rotate:
				target.combined.setToRotation(val[1], val[2], val[3], val[0]);
				break;
			case CameraTweens.LookAt:
				target.lookAt(val[0], val[1], val[3]);
				break;
		}
	}

	/**
	 * These are tweens specific to cameras
	 * @author nhydock
	 *
	 */
	public static class CameraTweens
	{
		public static final int LookAt = 0x31;
	}
	
	
}
