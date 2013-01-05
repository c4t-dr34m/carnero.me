package carnero.me.network;

import android.text.TextUtils;
import android.util.Log;
import carnero.me.Constants;
import carnero.me.network.model.LatitudeBase;
import com.google.android.maps.GeoPoint;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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
