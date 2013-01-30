package carnero.me.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressWarnings("unused")
public class TextViewCondensed extends TextView {

	public TextViewCondensed(Context context) {
		super(context);
		init(context);
	}

	public TextViewCondensed(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public TextViewCondensed(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
		init(context);
	}

	public void init(Context context) {
		if (isInEditMode()) {
			return;
		}

		final Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Regular.ttf");
		setTypeface(face);
	}
}
