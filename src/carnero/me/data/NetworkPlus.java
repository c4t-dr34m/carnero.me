package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Network;

@SuppressWarnings("UnusedDeclaration")
public class NetworkPlus extends Network {

	public NetworkPlus() {
		iconOn = R.drawable.ic_google_plus;
		iconOff = R.drawable.ic_google_plus_off;
		title = R.string.network_plus;
		description = R.string.network_plus_desc;
		tapAction = EntryIntent.getWebIntent("https://github.com/carnero/");
		packageName = "com.google.android.apps.plus";
	}
}
