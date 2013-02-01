package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Network;

@SuppressWarnings("UnusedDeclaration")
public class NetworkTwitter extends Network {

	public NetworkTwitter() {
		iconOn = R.drawable.ic_twitter;
		iconOff = R.drawable.ic_twitter_off;
		title = R.string.network_twitter;
		description = R.string.network_twitter_desc;
		tapAction = EntryIntent.getWebIntent("https://twitter.com/carnero_cc");
		packageName = "com.twitter.android";
	}
}
