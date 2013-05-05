package carnero.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import carnero.me.Constants;

public class RouterActivity extends Activity {

	public void onCreate(Bundle state) {
		super.onCreate(state);

		Intent intent = new Intent();
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int width = (int) (dm.widthPixels / dm.density);

		if (width > Constants.TABLET_MIN_WIDTH_DP) {
			intent.setClass(this, TabletActivity.class);
		} else {
			intent.setClass(this, PhoneActivity.class);
		}
		startActivity(intent);
		finish();
	}
}
