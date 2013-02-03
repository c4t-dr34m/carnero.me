package carnero.me.model;

public class GeoPoint {

	private int mLatitudeE6;
	private int mLongitudeE6;

	public GeoPoint(int latitude, int longitude) {
		mLatitudeE6 = latitude;
		mLongitudeE6 = longitude;
	}

	public int getLatitudeE6() {
		return mLatitudeE6;
	}

	public int getLongitudeE6() {
		return mLongitudeE6;
	}
}
