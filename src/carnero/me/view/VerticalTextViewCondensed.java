package carnero.me.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressWarnings("unused")
public class VerticalTextViewCondensed extends TextView {

	protected int mAngle = -90;

	public VerticalTextViewCondensed(Context context) {
		super(context);
		init(context);
	}

	public VerticalTextViewCondensed(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public VerticalTextViewCondensed(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
		init(context);
	}

	@Override
	public void onMeasure(int widthSpec, int heightSpec) {
		super.onMeasure(heightSpec, widthSpec);

		setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
	}

	@Override
	public void onDraw(Canvas canvas) {
		final TextPaint paint = getPaint();
		paint.setColor(getCurrentTextColor());
		paint.drawableState = getDrawableState();

		canvas.save();

		if (mAngle > 0) {
			canvas.translate(getWidth(), 0);
		} else {
			canvas.translate(0, getHeight());
		}
		canvas.rotate(mAngle);
		canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());

		getLayout().draw(canvas);

		canvas.restore();
	}

	public void init(Context context) {
		if (isInEditMode()) {
			return;
		}

		final Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Regular.ttf");
		setTypeface(face);
	}
}
