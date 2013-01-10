package carnero.me.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

@SuppressWarnings("unused")
public class AnimateLinearLayout extends LinearLayout {

	private AnimateTouchProcessor mProcessor;

	public AnimateLinearLayout(Context context) {
		super(context);
		init(context);
	}

	public AnimateLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public AnimateLinearLayout(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
		init(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mProcessor.processTouch(this, event);

		return super.onTouchEvent(event);
	}

	private void init(Context context) {
		mProcessor = new AnimateTouchProcessor(context);
	}
}
