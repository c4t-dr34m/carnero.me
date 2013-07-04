package carnero.me.network.model;

public class LatitudeBase {

	public LatitudeFeature[] features;

	public int getLatitudeE6() {
		int latitude = 0;

		if (features != null && features.length > 0) {
			latitude = (int) (features[0].geometry.coordinates[1] * 1e6);
		}

		return latitude;
	}

	public int getLongitudeE6() {
		int longitude = 0;

		if (features != null && features.length > 0) {
			longitude = (int) (features[0].geometry.coordinates[0] * 1e6);
		}

		return longitude;
	}
}
