package carnero.me.network;

import android.text.TextUtils;
import carnero.me.Constants;
import carnero.me.model.GeoPoint;
import carnero.me.network.model.LatitudeBase;
import com.google.gson.Gson;

public class Latitude {

	private final Request mRequest;
	private final Gson mGson;
	//
	private static final String sUrl = "http://www.google.com/latitude/apps/badge/api?user=" + Constants.ID_LATITUDE + "&type=json";

	public Latitude() {
		mRequest = new Request();
		mGson = new Gson();
	}

	public GeoPoint getCoordinates() {
		final String response = mRequest.getJSON(sUrl);

		if (!TextUtils.isEmpty(response)) {
			LatitudeBase data = mGson.fromJson(response, LatitudeBase.class);

			if (data != null) {
				return new GeoPoint(data.getLatitudeE6(), data.getLongitudeE6());
			}
		}

		return null;
	}
}
