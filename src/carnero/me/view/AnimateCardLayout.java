package carnero.me.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import carnero.me.R;

@SuppressWarnings("unused")
public class AnimateCardLayout extends LinearLayout implements IAnimateView {

	private AnimateTouchProcessor mProcessor;

	public AnimateCardLayout(Context context) {
		super(context);
		init(context);
	}

	public AnimateCardLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public AnimateCardLayout(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
		init(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mProcessor.processTouch(this, event);

		return super.onTouchEvent(event);
	}

	private void init(Context context) {
		mProcessor = new AnimateTouchProcessor(context, R.anim.card_down, R.anim.card_up);
	}

	public void resetAnimation() {
		mProcessor.reset(this);
	}
}
