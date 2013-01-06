package carnero.me.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import carnero.me.Constants;
import carnero.me.R;
import carnero.me.network.Latitude;
import com.google.android.maps.GeoPoint;

public class Main extends Activity {

	private int mLatitude;
	private int mLongitude;
	private boolean mNickDisplayed = true;
	private boolean mAnimating = false;
	// views
	private TextView mNick;
	private TextView mName;
	private ImageView mMap;
	private View mLocation;
	// animations
	private Animation mSlideTop;
	private Animation mSlideBottom;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		mNick = (TextView) findViewById(R.id.name_nick);
		mName = (TextView) findViewById(R.id.name_real);
		mMap = (ImageView) findViewById(R.id.location_map);
		mLocation = findViewById(R.id.location_current);

		mSlideTop = AnimationUtils.loadAnimation(this, R.anim.slide_top);
		mSlideBottom = AnimationUtils.loadAnimation(this, R.anim.slide_bottom);

		findViewById(R.id.name_frame).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mAnimating) {
					return;
				}

				if (mNickDisplayed) {
					animateToName();
				} else {
					animateToNick();
				}
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();

		getLocation();
	}

	private void getLocation() {
		final LocationHandler handler = new LocationHandler();
		final LocationThread thread = new LocationThread(handler);

		thread.start();
	}

	public void animateToName() {
		mSlideTop.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				mAnimating = true;
				mName.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mNick.setVisibility(View.INVISIBLE);
				mNickDisplayed = false;
				mAnimating = false;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// nothing
			}
		});

		mNick.clearAnimation();
		mName.clearAnimation();

		mNick.startAnimation(mSlideTop);
		mName.startAnimation(mSlideBottom);
	}

	public void animateToNick() {
		mSlideTop.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				mAnimating = true;
				mNick.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mName.setVisibility(View.INVISIBLE);
				mNickDisplayed = true;
				mAnimating = false;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// nothing
			}
		});

		mName.clearAnimation();
		mNick.clearAnimation();

		mName.startAnimation(mSlideTop);
		mNick.startAnimation(mSlideBottom);
	}

	// classes
	private class LocationThread extends Thread {

		private Handler mHandler;

		public LocationThread(Handler handler) {
			mHandler = handler;
		}

		@Override
		public void run() {
			final GeoPoint gp = new Latitude().getCoordinates();
			mLatitude = gp.getLatitudeE6();
			mLongitude = gp.getLongitudeE6();

			mHandler.sendEmptyMessage(1);
		}
	}

	private class LocationHandler extends Handler {

		public void handleMessage(Message message) {
			// whole map
			final int mapWidth = mMap.getWidth();
			final int mapHeight = mMap.getHeight();
			// pixel square
			final int squareSize = Math.round(((mapWidth / 80) + (mapHeight / 42)) / 2);
			final int squareBorder = squareSize / 8;
			// map square
			final int mapSquareLat = (Constants.MAP_LATITUDE_MAX_E6 - Constants.MAP_LATITUDE_MIN_E6) / Constants.MAP_SQUARE_WIDTH;
			final int mapSquareLon = (Constants.MAP_LONGITUDE_MAX_E6 - Constants.MAP_LONGITUDE_MIN_E6) / Constants.MAP_SQUARE_HEIGHT;
			// map border
			final int mapLatitudeMargin = (int) Math.abs((-180 * 1e6) - Constants.MAP_LATITUDE_MIN_E6);
			final int mapLongitudeMargin = (int) Math.abs((90 * 1e6) - Constants.MAP_LONGITUDE_MAX_E6);

			// map bounds
			if (mLatitude < Constants.MAP_LATITUDE_MIN_E6) {
				mLatitude = Constants.MAP_LATITUDE_MIN_E6;
			} else if (mLatitude > Constants.MAP_LATITUDE_MAX_E6) {
				mLatitude = Constants.MAP_LATITUDE_MAX_E6;
			}
			if (mLongitude < Constants.MAP_LONGITUDE_MIN_E6) {
				mLongitude = Constants.MAP_LONGITUDE_MIN_E6;
			} else if (mLongitude > Constants.MAP_LONGITUDE_MAX_E6) {
				mLongitude = Constants.MAP_LONGITUDE_MAX_E6;
			}

			// location pixel margin
			final int leftPos = (mLatitude - mapLatitudeMargin) - Constants.MAP_LATITUDE_MIN_E6;
			final int topPos = Constants.MAP_LONGITUDE_MAX_E6 - (mLongitude - mapLongitudeMargin);
			final int left = (leftPos / mapSquareLat) * squareSize - squareBorder;
			final int top = (topPos / mapSquareLon) * squareSize - squareBorder;

			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(left, top, 0, 0);

			mLocation.setLayoutParams(params);
			mLocation.setVisibility(View.VISIBLE);
		}
	}
}
