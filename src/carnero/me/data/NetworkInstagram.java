package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Network;

@SuppressWarnings("UnusedDeclaration")
public class NetworkInstagram extends Network {

	public NetworkInstagram() {
		iconOn = R.drawable.ic_instagram;
		iconOff = R.drawable.ic_instagram_off;
		title = R.string.network_instagram;
		description = R.string.network_instagram_desc;
		tapAction = new EntryIntent().setWeb("http://instagram.com/_carnero");
		packageName = new String[]{"com.instagram.android"};
	}
}
