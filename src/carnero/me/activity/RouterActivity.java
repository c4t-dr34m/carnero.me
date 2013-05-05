package carnero.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import carnero.me.Utils;

public class RouterActivity extends Activity {

	public void onCreate(Bundle state) {
		super.onCreate(state);

		Intent intent = new Intent();
		if (Utils.isWide(this)) {
			intent.setClass(this, TabletActivity.class);
		} else {
			intent.setClass(this, PhoneActivity.class);
		}
		startActivity(intent);
		finish();
	}
}
