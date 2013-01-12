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
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import carnero.me.R;
import carnero.me.network.Latitude;
import carnero.me.view.SquareMapView;
import com.google.android.maps.GeoPoint;

public class VcardFragment extends Fragment {

	private Context mContext;
	private View mContent;
	//
	private GeoPoint mLocation;
	private boolean mNickDisplayed = true;
	private boolean mAnimating = false;
	// views
	private View mNickFrame;
	private View mName;
	private TextView mJob;
	private SquareMapView mMap;
	// animations
	private Animation mSlideTop;
	private Animation mSlideBottom;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		mContent = inflater.inflate(R.layout.vcard, container, false);

		// views
		mNickFrame = mContent.findViewById(R.id.name_nick);
		mName = mContent.findViewById(R.id.name_real);
		mJob = (TextView) mContent.findViewById(R.id.layout_job);
		mMap = (SquareMapView) mContent.findViewById(R.id.location_map);

		return mContent;
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

		mContext = getActivity().getBaseContext();

		setPhoneNumber();
		formatJob();

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
		final View gtalk = mContent.findViewById(R.id.contact_gtalk);
		final View email = mContent.findViewById(R.id.contact_email);
		final View phone = mContent.findViewById(R.id.contact_phone);

		gtalk.setOnClickListener(new View.OnClickListener() {
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
		gtalk.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Toast.makeText(mContext, R.string.contact_gtalk_value, Toast.LENGTH_LONG).show();

				return true;
			}
		});

		email.setOnClickListener(new View.OnClickListener() {
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
		email.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Toast.makeText(mContext, R.string.contact_email_value, Toast.LENGTH_LONG).show();

				return true;
			}
		});

		phone.setOnClickListener(new View.OnClickListener() {
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
		phone.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Toast.makeText(mContext, R.string.contact_phone_value, Toast.LENGTH_LONG).show();

				return true;
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

	public void formatJob() {
		final int color = getResources().getColor(android.R.color.white);
		final SpannableString text = new SpannableString(getResources().getText(R.string.vcard_position));

		text.setSpan(new ForegroundColorSpan(color), 7, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		mJob.setText(text);
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
			mLocation = new Latitude().getCoordinates();

			mHandler.sendEmptyMessage(1);
		}
	}

	private class LocationHandler extends Handler {

		public void handleMessage(Message message) {
			if (mLocation == null) {
				return;
			}

			mMap.setCurrentLocation(mLocation);
		}
	}
}
