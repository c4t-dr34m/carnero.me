package carnero.me.view;

import android.content.Context;
import android.util.AttributeSet;

@SuppressWarnings("unused")
public class LeftVerticalThin extends VerticalTextViewThin {

	public LeftVerticalThin(Context context) {
		super(context);
		init(context);
	}

	public LeftVerticalThin(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public LeftVerticalThin(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
		init(context);
	}

	public void init(Context context) {
		mAngle = -90;

		super.init(context);
	}
}
