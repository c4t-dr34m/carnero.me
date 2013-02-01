package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Network;

@SuppressWarnings("UnusedDeclaration")
public class NetworkPinterest extends Network {

	public NetworkPinterest() {
		iconOn = R.drawable.ic_pinterest;
		iconOff = R.drawable.ic_pinterest_off;
		title = R.string.network_pinterest;
		description = R.string.network_pinterest_desc;
		tapAction = EntryIntent.getWebIntent("http://pinterest.com/carnero/");
		packageName = "com.pinterest";
	}
}
