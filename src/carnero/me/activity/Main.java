package carnero.me.activity;

import android.os.Bundle;
import carnero.me.R;
import carnero.me.fragment.NetworksFragment;
import carnero.me.fragment.TimelineFragment;
import carnero.me.fragment.VcardFragment;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class Main extends SlidingFragmentActivity {

	private Tracker mTracker;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT_RIGHT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setBehindOffset((int) getResources().getDimension(R.dimen.sliding_menu_offset));
		menu.setFadeDegree(0.5f);
		menu.setShadowWidthRes(R.dimen.sliding_menu_shadow);
		menu.setShadowDrawable(R.drawable.shadow_left);
		menu.setSecondaryShadowDrawable(R.drawable.shadow_right);

		// activity content
		setContentView(R.layout.main);
		setBehindContentView(R.layout.menu_primary);
		menu.setSecondaryMenu(R.layout.menu_secondary);

		// vcard
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.fragment_container, new VcardFragment())
				.commit();

		// networks
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.fragment_container_primary, new NetworksFragment())
				.commit();

		// timeline
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.fragment_container_secondary, new TimelineFragment())
				.commit();

		menu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
			@Override
			public void onOpened() {
				if (mTracker != null) {
					mTracker.sendEvent("main", "open", "slide_menu", 0l);
				}
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();

		EasyTracker.getInstance().activityStart(this);

		final GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
		mTracker = analytics.getTracker(getString(R.string.ga_trackingId));
	}

	@Override
	public void onStop() {
		super.onStop();

		EasyTracker.getInstance().activityStop(this);
	}
}