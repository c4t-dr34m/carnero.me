package carnero.me.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;
import carnero.me.Map;
import carnero.me.R;
import com.google.android.maps.GeoPoint;

@SuppressWarnings("unused")
public class SquareMapView extends View {

	private Context mContext;
	// dimensions
	private int mWidth = 0;
	private int mHeight = 0;
	private int mSquareSize = 0;
	private int mSquareMargin = 0;
	private int mPaddingLeft = 0;
	private int mPaddingTop = 0;
	// location
	private Integer mLatitude = null;
	private Integer mLongitude = null;
	private long mSquareLat;
	private long mSquareLon;
	private long mMarginLat;
	private long mMarginLon;
	// paint
	private PaintFlagsDrawFilter mSetFilter;
	private PaintFlagsDrawFilter mRemFilter;
	private Paint mSquareGreyPaint;
	private Paint mSquareBluePaint;

	public SquareMapView(Context context) {
		super(context);

		mContext = context;
	}

	public SquareMapView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mContext = context;
	}

	public SquareMapView(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);

		mContext = context;
	}

	@Override
	public void onAttachedToWindow() {
		mSetFilter = new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG);
		mRemFilter = new PaintFlagsDrawFilter(Paint.FILTER_BITMAP_FLAG, 0);

		if (mSquareGreyPaint == null) {
			final int color = mContext.getResources().getColor(R.color.map_square_grey);

			mSquareGreyPaint = new Paint();
			mSquareGreyPaint.setColor(color);
		}
		if (mSquareBluePaint == null) {
			final int color = mContext.getResources().getColor(R.color.map_square_blue);

			mSquareBluePaint = new Paint();
			mSquareBluePaint.setColor(color);
		}
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

		// map
		int x;
		int y;
		int posX;
		int posY;

		for (x = 0; x < Map.MAP_WIDTH; x++) {
			for (y = 0; y < Map.MAP_HEIGHT; y++) {
				if (Map.MAP_DEFINITION[y][x]) {
					posX = (x * (mSquareSize + mSquareMargin)) - (int) Math.floor(mSquareMargin / 2) + mPaddingLeft;
					posY = (y * (mSquareSize + mSquareMargin)) - (int) Math.floor(mSquareMargin / 2) + mPaddingTop;

					canvas.drawRect(posX, posY, posX + mSquareSize, posY + mSquareSize, mSquareGreyPaint); // left top right bottom
				}
			}
		}

		// current location
		if (mLatitude != null && mLongitude != null) {
			final long leftPos = (mLatitude - mMarginLat) - Map.MAP_LATITUDE_MIN_E6;
			final long topPos = Map.MAP_LONGITUDE_MAX_E6 - (mLongitude - mMarginLon);
			x = (int) (leftPos / mSquareLat);
			y = (int) (topPos / mSquareLon);

			posX = (x * (mSquareSize + mSquareMargin)) - (int) Math.floor(mSquareMargin / 2) + mPaddingLeft;
			posY = (y * (mSquareSize + mSquareMargin)) - (int) Math.floor(mSquareMargin / 2) + mPaddingTop;

			canvas.drawRect(posX, posY, posX + mSquareSize, posY + mSquareSize, mSquareBluePaint);
		}

		canvas.setDrawFilter(mRemFilter);
	}

	public void setCurrentLocation(GeoPoint geoPoint) {
		mLatitude = geoPoint.getLatitudeE6();
		mLongitude = geoPoint.getLongitudeE6();

		// map bounds
		if (mLatitude < Map.MAP_LATITUDE_MIN_E6) {
			mLatitude = Map.MAP_LATITUDE_MIN_E6;
		} else if (mLatitude > Map.MAP_LATITUDE_MAX_E6) {
			mLatitude = Map.MAP_LATITUDE_MAX_E6;
		}
		if (mLongitude < Map.MAP_LONGITUDE_MIN_E6) {
			mLongitude = Map.MAP_LONGITUDE_MIN_E6;
		} else if (mLongitude > Map.MAP_LONGITUDE_MAX_E6) {
			mLongitude = Map.MAP_LONGITUDE_MAX_E6;
		}

		invalidate();
	}

	private void countSquareSize() {
		final float maxSquareW = mWidth / Map.MAP_WIDTH; // maximum square width
		final float maxSquareH = mHeight / Map.MAP_HEIGHT; // maximum square height

		final float maxSquare;
		if (maxSquareW <= maxSquareH) {
			maxSquare = maxSquareW;
		} else {
			maxSquare = maxSquareH;
		}

		double border = maxSquare * 0.25; // 1/4
		if (border < 1) {
			border = 1.0d;
		}
		mSquareMargin = (int) Math.floor(border);

		double square = maxSquare - mSquareMargin;

		mSquareSize = (int) Math.floor(square);

		mPaddingLeft = Math.round((mWidth - (Map.MAP_WIDTH * (mSquareSize + mSquareMargin))) / 2);
		mPaddingTop = Math.round((mHeight - (Map.MAP_HEIGHT * (mSquareSize + mSquareMargin))) / 2);

		mSquareLat = (Map.MAP_LATITUDE_MAX_E6 - Map.MAP_LATITUDE_MIN_E6) / Map.MAP_WIDTH;
		mSquareLon = (Map.MAP_LONGITUDE_MAX_E6 - Map.MAP_LONGITUDE_MIN_E6) / Map.MAP_HEIGHT;
		mMarginLat = (int) Math.abs((-180 * 1e6) - Map.MAP_LATITUDE_MIN_E6);
		mMarginLon = (int) Math.abs((90 * 1e6) - Map.MAP_LONGITUDE_MAX_E6);
	}
}
