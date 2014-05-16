package net.eitr.fractaltree.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteAccessor implements TweenAccessor<Sprite>
{
	public static final int ALPHA = 1;
	public static final int POS_XY = 2;
	public static final int SCALE = 3;
	public static final int COLOR = 4;


	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues)
	{
		switch (tweenType)
		{
			case ALPHA:
				returnValues[0] = target.getColor().a;
				return 1;
			case POS_XY:
				returnValues[0] = target.getX();
				returnValues[1] = target.getY();
				return 2;
			case SCALE:
				returnValues[0] = target.getScaleX();
				returnValues[1] = target.getScaleY();
				return 2;
			case COLOR:
				returnValues[0] = target.getColor().r;
				returnValues[1] = target.getColor().g;
				returnValues[2] = target.getColor().b;
				returnValues[3] = target.getColor().a;
				return 4;
			default: 
				assert false;
		}
		return -1;
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] returnValues)
	{
		switch (tweenType)
		{
			case ALPHA:
				target.setAlpha(returnValues[0]);
				break;
			default: 
				assert false;
		}
	}

}
