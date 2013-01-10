package carnero.me.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressWarnings("unused")
public class TextViewThin extends TextView {

	public TextViewThin(Context context) {
		super(context);
		init(context);
	}

	public TextViewThin(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public TextViewThin(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
		init(context);
	}

	public void init(Context context) {
		if (isInEditMode()) {
			return;
		}

		final Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
		setTypeface(face);
	}
}
