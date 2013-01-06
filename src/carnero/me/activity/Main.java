package carnero.me.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import carnero.me.R;
import carnero.me.fragment.NetworksFragment;
import carnero.me.fragment.TimelineFragment;
import carnero.me.fragment.VcardFragment;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class Main extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT_RIGHT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setBehindOffset((int) getResources().getDimension(R.dimen.sliding_menu_offset));
		menu.setShadowDrawable(com.slidingmenu.lib.R.drawable.defaultshadow);
		menu.setSecondaryShadowDrawable(com.slidingmenu.lib.R.drawable.defaultshadowright);

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
	}
}
