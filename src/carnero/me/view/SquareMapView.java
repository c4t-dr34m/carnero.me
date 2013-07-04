package carnero.me.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import carnero.me.Constants;
import carnero.me.Map;
import carnero.me.R;
import carnero.me.model.GeoPoint;

@SuppressWarnings("unused")
public class SquareMapView extends View {

	private Context mContext;
	private boolean mRenderMap = true;
	// dimensions
	private int mWidth = 0;
	private int mHeight = 0;
	private int mSquareSize = 0;
	private int mSquareMargin = 0;
	private int mPaddingLeft = 0;
	private int mPaddingTop = 0;
	// location
	private Integer mLatitude = Constants.DEF_LATITUDE;
	private Integer mLongitude = Constants.DEF_LONGITUDE;
	private long mSquareLat;
	private long mSquareLon;
	// paint
	private PaintFlagsDrawFilter mSetFilter;
	private PaintFlagsDrawFilter mRemFilter;
	private Paint mSquareGreyPaint;
	private Paint mSquareHighlightPaint;
	private Paint mSquareBluePaint;
	private int[] mLocationGlow = new int[]{10, 20, 30, 100};

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
		if (mSquareHighlightPaint == null) {
			final int color = mContext.getResources().getColor(R.color.map_square_highlight);

			mSquareHighlightPaint = new Paint();
			mSquareHighlightPaint.setColor(color);
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

		if (!mRenderMap) {
			return;
		}

		canvas.setDrawFilter(mSetFilter);

		// map
		int x;
		int y;
		int posX;
		int posY;
		int point;

		for (x = 0; x < Map.WIDTH; x++) {
			for (y = 0; y < Map.HEIGHT; y++) {
				point = Map.MAP_DEFINITION[y][x];
				if (point > 0) {
					posX = (x * (mSquareSize + mSquareMargin)) + mPaddingLeft;
					posY = (y * (mSquareSize + mSquareMargin)) + mPaddingTop;

					switch (point) {
						case 1:
							canvas.drawRect(posX, posY, posX + mSquareSize, posY + mSquareSize, mSquareGreyPaint); // left top right bottom
							break;
						case 2:
							canvas.drawRect(posX, posY, posX + mSquareSize, posY + mSquareSize, mSquareHighlightPaint); // left top right bottom
							break;
						case 3:
							int cnt = mLocationGlow.length;
							RectF rect;
							for (int g : mLocationGlow) {
								rect = new RectF(posX - cnt, posY - cnt, posX + cnt + mSquareSize, posY + cnt + mSquareSize);

								mSquareBluePaint.setAlpha(g);
								canvas.drawRoundRect(rect, cnt, cnt, mSquareBluePaint);

								cnt = cnt - 1;
							}
							break;
						default:
							continue;
					}
				}
			}
		}

		canvas.setDrawFilter(mRemFilter);
	}

	public void setCurrentLocation(GeoPoint geoPoint) {
		mLatitude = geoPoint.getLatitudeE6();
		mLongitude = geoPoint.getLongitudeE6();

		invalidate();
	}

	private void countSquareSize() {
		final float maxSquareW = mWidth / Map.WIDTH; // maximum square width
		final float maxSquareH = mHeight / Map.HEIGHT; // maximum square height

		final float maxSquare;
		if (maxSquareW <= maxSquareH) {
			maxSquare = maxSquareW;
		} else {
			maxSquare = maxSquareH;
		}

		double border = maxSquare * 0.25;
		if (border < 1) {
			border = 1.0d;
		}
		mSquareMargin = (int) Math.floor(border);

		double square = maxSquare - mSquareMargin;

		mSquareSize = (int) Math.floor(square);

		mPaddingLeft = Math.round((mWidth - (Map.WIDTH * (mSquareSize + mSquareMargin))) / 2);
		mPaddingTop = Math.round((mHeight - (Map.HEIGHT * (mSquareSize + mSquareMargin))) / 2);

		mSquareLat = (Map.LATITUDE_MAX_E6 - Map.LATITUDE_MIN_E6) / Map.HEIGHT;
		mSquareLon = (Map.LONGITUDE_MAX_E6 - Map.LONGITUDE_MIN_E6) / Map.WIDTH;

		mRenderMap = mSquareSize >= 2;
	}
}
