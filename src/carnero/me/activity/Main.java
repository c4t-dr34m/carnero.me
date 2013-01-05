package carnero.me.activity;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import carnero.me.R;
import carnero.me.network.Latitude;
import com.google.android.maps.GeoPoint;

public class Main extends Activity {

	private TextView mDebug;
	private int mLatitude;
	private int mLongitude;
	private Handler mHandler = new Handler() {

		public void handleMessage(Message message) {
			if (mDebug != null) {
				mDebug.setText(String.valueOf(mLatitude) + "/" + String.valueOf(mLongitude));
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		mDebug = (TextView) findViewById(R.id.debug);

		new Thread() {

			@Override
			public void run() {
				final GeoPoint gp = new Latitude().getCoordinates();
				mLatitude = gp.getLatitudeE6();
				mLongitude = gp.getLongitudeE6();

				mHandler.sendEmptyMessage(1);
			}
		}.start();
	}
}
