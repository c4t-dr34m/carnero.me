package carnero.me.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import carnero.me.Constants;
import carnero.me.R;
import carnero.me.network.Latitude;
import com.google.android.maps.GeoPoint;

public class Main extends Activity {

	private int mLatitude = Integer.MIN_VALUE;
	private int mLongitude = Integer.MIN_VALUE;
	private boolean mNickDisplayed = true;
	private boolean mAnimating = false;
	// views
	private View mNickFrame;
	private View mName;
	private ImageView mMap;
	private View mLocation;
	private ViewPager mNetworksPager;
	// animations
	private Animation mSlideTop;
	private Animation mSlideBottom;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		mNickFrame = findViewById(R.id.name_nick_frame);
		mName = findViewById(R.id.name_real);
		mMap = (ImageView) findViewById(R.id.location_map);
		mLocation = findViewById(R.id.location_current);
		mNetworksPager = (ViewPager) findViewById(R.id.networks_pager);

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

		// contact actions
		findViewById(R.id.contact_phone).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					final Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+42077209671"));
					startActivity(call);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(Main.this, getString(R.string.error_no_phone), Toast.LENGTH_SHORT).show();
				}
			}
		});
		findViewById(R.id.contact_email).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent mail = new Intent(Intent.ACTION_SEND);
				mail.setType("message/rfc822");
				mail.putExtra(Intent.EXTRA_EMAIL, new String[] {"carnero@carnero.cc"});

				try {
					startActivity(Intent.createChooser(mail, "Send mail..."));
				} catch (ActivityNotFoundException e) {
					Toast.makeText(Main.this, getString(R.string.error_no_mail), Toast.LENGTH_SHORT).show();
				}
			}
		});
		findViewById(R.id.contact_gtalk).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO: gtalk
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
				mNickFrame.setVisibility(View.INVISIBLE);
				mNickDisplayed = false;
				mAnimating = false;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// nothing
			}
		});

		mNickFrame.clearAnimation();
		mName.clearAnimation();

		mNickFrame.startAnimation(mSlideTop);
		mName.startAnimation(mSlideBottom);
	}

	public void animateToNick() {
		mSlideTop.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				mAnimating = true;
				mNickFrame.setVisibility(View.VISIBLE);
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
		mNickFrame.clearAnimation();

		mName.startAnimation(mSlideTop);
		mNickFrame.startAnimation(mSlideBottom);
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
			if (mLatitude == Integer.MIN_VALUE || mLongitude == Integer.MIN_VALUE) {
				return;
			}

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
