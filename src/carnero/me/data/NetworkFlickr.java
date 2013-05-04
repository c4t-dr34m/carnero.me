package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Network;

@SuppressWarnings("UnusedDeclaration")
public class NetworkFlickr extends Network {

	public NetworkFlickr() {
		iconOn = R.drawable.ic_flickr;
		iconOff = R.drawable.ic_flickr_off;
		title = R.string.network_flickr;
		description = R.string.network_flickr_desc;
		tapAction = new EntryIntent().setWeb("http://www.flickr.com/photos/carnero/");
		packageName = new String[]{
				"com.yahoo.mobile.client.android.flickr",
				"com.bourke.glimmr",
				"com.bourke.glimmrpro",
				"com.snapwood.flickfolio"
		};
	}
}
