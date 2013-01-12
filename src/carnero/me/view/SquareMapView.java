package carnero.me.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.maps.GeoPoint;

@SuppressWarnings("unused")
public class SquareMapView extends View {

	private int mWidth = 0;
	private int mHeight = 0;
	private int mSquareSize = 0;
	private PaintFlagsDrawFilter mSetFilter;
	private PaintFlagsDrawFilter mRemFilter;
	private GeoPoint mCurrentLocation;

	public SquareMapView(Context context) {
		super(context);
	}

	public SquareMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SquareMapView(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
	}

	@Override
	public void onAttachedToWindow() {
		mSetFilter = new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG);
		mRemFilter = new PaintFlagsDrawFilter(Paint.FILTER_BITMAP_FLAG, 0);

		// TODO: load colors
	}

	@Override
	public void onSizeChanged(int w, int h, int wOld, int hOld) {
		mWidth = w;
		mHeight = h;

		countSquareSize();
		invalidate();
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.setDrawFilter(mSetFilter);

		// TODO: draw filter

		canvas.setDrawFilter(mRemFilter);
	}

	public void setCurrentLocation(GeoPoint geoPoint) {
		mCurrentLocation = geoPoint;
	}

	private void countSquareSize() {

	}
}
