package carnero.me.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import carnero.me.Constants;
import carnero.me.R;
import carnero.me.network.Latitude;
import com.google.android.maps.GeoPoint;

public class VcardFragment extends Fragment {

	private Context mContext;
	private View mContent;
	//
	private int mLatitude = Integer.MIN_VALUE;
	private int mLongitude = Integer.MIN_VALUE;
	private boolean mNickDisplayed = true;
	private boolean mAnimating = false;
	// views
	private View mNickFrame;
	private View mName;
	private ImageView mMap;
	private View mLocation;
	// animations
	private Animation mSlideTop;
	private Animation mSlideBottom;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		mContent = inflater.inflate(R.layout.vcard, container, false);

		// views
		mNickFrame = mContent.findViewById(R.id.name_nick_frame);
		mName = mContent.findViewById(R.id.name_real);
		mMap = (ImageView) mContent.findViewById(R.id.location_map);
		mLocation = mContent.findViewById(R.id.location_current);

		return mContent;
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

		mContext = getActivity().getBaseContext();

		setPhoneNumber();

		mSlideTop = AnimationUtils.loadAnimation(mContext, R.anim.slide_top);
		mSlideBottom = AnimationUtils.loadAnimation(mContext, R.anim.slide_bottom);

		mContent.findViewById(R.id.name_frame).setOnClickListener(new View.OnClickListener() {
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
		mContent.findViewById(R.id.contact_phone).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					final Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+420777209671"));
					startActivity(call);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(mContext, getString(R.string.error_no_phone), Toast.LENGTH_SHORT).show();
				}
			}
		});
		mContent.findViewById(R.id.contact_email).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent mail = new Intent(Intent.ACTION_SEND);
				mail.setType("message/rfc822");
				mail.putExtra(Intent.EXTRA_EMAIL, new String[]{"carnero@carnero.cc"});

				try {
					startActivity(Intent.createChooser(mail, "Send email..."));
				} catch (ActivityNotFoundException e) {
					Toast.makeText(mContext, getString(R.string.error_no_mail), Toast.LENGTH_SHORT).show();
				}
			}
		});
		mContent.findViewById(R.id.contact_gtalk).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Uri uri = new Uri.Builder().scheme("imto").authority("gtalk").appendPath("carnero@carnero.cc").build();
				final Intent gtalk = new Intent(Intent.ACTION_SENDTO, uri);

				try {
					startActivity(Intent.createChooser(gtalk, "Send instant message..."));
				} catch (ActivityNotFoundException e) {
					Toast.makeText(mContext, getString(R.string.error_no_gtalk), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();

		getLocation();
	}

	private void setPhoneNumber() {
		boolean full = true;

		final TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		if (tm != null) {
			final String operator = tm.getNetworkCountryIso();

			if (!TextUtils.isEmpty(operator) && operator.equalsIgnoreCase("cz")) {
				full = false;
			}
		}

		if (full) {
			((TextView) mContent.findViewById(R.id.contact_phone_value)).setText("+420 777 209 671");
		} else {
			((TextView) mContent.findViewById(R.id.contact_phone_value)).setText("777 209 671");
		}
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
