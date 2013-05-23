package com.shipvgdc.sugdk.tween;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

import aurelienribon.tweenengine.TweenAccessor;

public class TweenableMatrix implements TweenAccessor<Matrix4> {

	@Override
	public int getValues(Matrix4 target, int type, float[] val) {
		int rLen = 0;
		switch (type)
		{
			case (Tweens.X):
			{
				Vector3 v = new Vector3();
				target.getTranslation(v);
				val[0] = v.x;
				rLen = 1;
				break;
			}
			case (Tweens.Y):
			{
				Vector3 v = new Vector3();
				target.getTranslation(v);
				val[0] = v.y;
				rLen = 1;
				break;
			}
			case (Tweens.Z):
			{
				Vector3 v = new Vector3();
				target.getTranslation(v);
				val[0] = v.z;
				rLen = 1;
				break;
			}
			case (Tweens.XY):
			{
				Vector3 v = new Vector3();
				target.getTranslation(v);
				val[0] = v.x;
				val[1] = v.y;
				rLen = 2;
				break;
			}
			case (Tweens.XYZ):
			{
				Vector3 v = new Vector3();
				target.getTranslation(v);
				val[0] = v.x;
				val[1] = v.y;
				val[2] = v.z;
				rLen = 3;
				break;
			}
			case (Tweens.Rotate):
			{
				val[0] = 0;
				val[1] = 0;
				val[2] = 0;
				rLen = 3;
				break;
			}
		}
		
		return rLen;
	}

	@Override
	public void setValues(Matrix4 target, int type, float[] val) {
		switch(type)
		{
			case (Tweens.Rotate):
			{
				//target.transform(arg0, arg1, arg2, arg3, arg4, arg5, arg6)
				break;
			}
		}
	}

}
