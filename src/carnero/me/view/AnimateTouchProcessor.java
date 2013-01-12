package carnero.me.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import carnero.me.R;

public class AnimateTouchProcessor {

	private boolean mIsDown = false;
	private float mDownX;
	private float mDownY;
	// animation
	private static Animation sAnimationDown;
	private static Animation sAnimationUp;

	public AnimateTouchProcessor(Context context) {
		if (sAnimationDown == null) {
			sAnimationDown = AnimationUtils.loadAnimation(context, R.anim.btn_down);
		}
		if (sAnimationUp == null) {
			sAnimationUp = AnimationUtils.loadAnimation(context, R.anim.btn_up);
		}
	}

	public void processTouch(View view, MotionEvent event) {
		final int action = event.getAction();

		switch (action) {
			case MotionEvent.ACTION_DOWN:
				mIsDown = true;
				mDownX = event.getX();
				mDownY = event.getY();

				view.clearAnimation();
				view.startAnimation(sAnimationDown);

				break;
			case MotionEvent.ACTION_UP:
				if (mIsDown) {
					view.clearAnimation();
					view.startAnimation(sAnimationUp);

					mIsDown = false;
				}

				break;
			case MotionEvent.ACTION_MOVE:
				final float dstX = Math.abs(event.getX() - mDownX);
				final float dstY = Math.abs(event.getY() - mDownY);
				final double dst = Math.sqrt(dstX * dstX + dstY * dstY);

				if (mIsDown && dst > 25) {
					view.clearAnimation();
					view.startAnimation(sAnimationUp);

					mIsDown = false;
				}

				break;
		}
	}

	public void reset(View view) {
		if (mIsDown) {
			view.clearAnimation();
			view.startAnimation(sAnimationUp);

			mIsDown = false;
		}
	}
}