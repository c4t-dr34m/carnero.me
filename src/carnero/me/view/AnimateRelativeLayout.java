package carnero.me.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import carnero.me.R;

@SuppressWarnings("unused")
public class AnimateRelativeLayout extends RelativeLayout {

	private static Animation sAnimationDown;
	private static Animation sAnimationUp;

	public AnimateRelativeLayout(Context context) {
		super(context);
		init(context);
	}

	public AnimateRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public AnimateRelativeLayout(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
		init(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = event.getAction();

		switch (action) {
			case MotionEvent.ACTION_DOWN:
				clearAnimation();
				startAnimation(sAnimationDown);
				break;
			case MotionEvent.ACTION_UP:
				clearAnimation();
				startAnimation(sAnimationUp);
				break;
		}

		return super.onTouchEvent(event);
	}

	private void init(Context context) {
		if (sAnimationDown == null) {
			sAnimationDown = AnimationUtils.loadAnimation(context, R.anim.btn_down);
		}
		if (sAnimationUp == null) {
			sAnimationUp = AnimationUtils.loadAnimation(context, R.anim.btn_up);
		}
	}
}
