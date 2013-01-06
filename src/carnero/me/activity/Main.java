package carnero.me.activity;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import carnero.me.Constants;
import carnero.me.R;
import carnero.me.network.Latitude;
import com.google.android.maps.GeoPoint;

public class Main extends Activity {

	private int mLatitude;
	private int mLongitude;
	// views
	private ViewPager mPager;
	private ImageView mMap;
	private View mLocation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		mPager = (ViewPager) findViewById(R.id.pager);
		mMap = (ImageView) findViewById(R.id.location_map);
		mLocation = findViewById(R.id.location_current);
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
