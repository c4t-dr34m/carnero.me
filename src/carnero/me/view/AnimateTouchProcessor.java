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
	private int mAction;
	// animation
	private Animation mAnimationDown;
	private Animation mAnimationUp;

	public AnimateTouchProcessor(Context context) {
		if (mAnimationDown == null) {
			mAnimationDown = AnimationUtils.loadAnimation(context, R.anim.btn_down);
		}
		if (mAnimationUp == null) {
			mAnimationUp = AnimationUtils.loadAnimation(context, R.anim.btn_up);
		}
	}

	public AnimateTouchProcessor(Context context, int down, int up) {
		if (mAnimationDown == null) {
			mAnimationDown = AnimationUtils.loadAnimation(context, down);
		}
		if (mAnimationUp == null) {
			mAnimationUp = AnimationUtils.loadAnimation(context, up);
		}
	}

	public void processTouch(View view, MotionEvent event) {
		mAction = event.getAction();

		switch (mAction) {
			case MotionEvent.ACTION_DOWN:
				mIsDown = true;
				mDownX = event.getX();
				mDownY = event.getY();

				view.clearAnimation();
				view.startAnimation(mAnimationDown);

				break;
			case MotionEvent.ACTION_UP:
				if (mIsDown) {
					view.clearAnimation();
					view.startAnimation(mAnimationUp);

					mIsDown = false;
				}

				break;
			case MotionEvent.ACTION_MOVE:
				final float dstX = Math.abs(event.getX() - mDownX);
				final float dstY = Math.abs(event.getY() - mDownY);
				final double dst = Math.sqrt(dstX * dstX + dstY * dstY);

				if (mIsDown && dst > 25) {
					view.clearAnimation();
					view.startAnimation(mAnimationUp);

					mIsDown = false;
				}

				break;
		}
	}

	public void reset(View view) {
		if (mIsDown) {
			view.clearAnimation();
			view.startAnimation(mAnimationUp);

			mIsDown = false;
		}
	}
}