package carnero.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import carnero.me.R;
import carnero.me.Utils;
import carnero.me.fragment.NetworksFragment;
import carnero.me.fragment.TimelineFragment;
import carnero.me.fragment.VcardFragment;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;

public class TabletActivity extends FragmentActivity {

	private Tracker mTracker;
	private static final Handler sHandler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (!Utils.isWide(this)) { // not wide enough, switch to phone layout
			final Intent intent = new Intent();
			intent.setClass(this, PhoneActivity.class);
			startActivity(intent);
			finish();

			return;
		}

		// activity content
		setContentView(R.layout.activity_tablet);

		if (savedInstanceState == null) {
			// vcard
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.container_vcard, new VcardFragment())
					.commit();

			/*
			 *  displaying of layout waits until onCreate completes
			 *  load another fragments little bit later to allow fast displaying of activity_phone one
			 */
			sHandler.postDelayed(new Runnable() {
				@Override
				public void run() {

					// networks
					getSupportFragmentManager()
							.beginTransaction()
							.setCustomAnimations(R.anim.appear_left, 0)
							.replace(R.id.container_networks, new NetworksFragment())
							.commit();

					// timeline
					getSupportFragmentManager()
							.beginTransaction()
							.setCustomAnimations(R.anim.appear_right, 0)
							.replace(R.id.container_timeline, new TimelineFragment())
							.commit();

				}
			}, 250);
		}
	}

	@Override
	public void onStart() {
		super.onStart();

		EasyTracker.getInstance().activityStart(this);

		final GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
		mTracker = analytics.getTracker(getString(R.string.ga_trackingId));
		mTracker.sendEvent("activity_tablet", "start", "tablet", 0l);

		GAServiceManager.getInstance().dispatch();
	}

	@Override
	public void onStop() {
		super.onStop();

		EasyTracker.getInstance().activityStop(this);
	}
}