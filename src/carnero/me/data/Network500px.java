package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Network;

@SuppressWarnings("UnusedDeclaration")
public class Network500px extends Network {

	public Network500px() {
		iconOn = R.drawable.ic_fivehundred;
		iconOff = R.drawable.ic_fivehundred_off;
		title = R.string.network_500px;
		description = R.string.network_500px_desc;
		tapAction = new EntryIntent().setWeb("http://500px.com/carnero");
		packageName = new String[]{"com.fivehundredpx.viewer"};
	}
}
