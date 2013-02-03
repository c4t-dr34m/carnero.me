package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Network;

@SuppressWarnings("UnusedDeclaration")
public class NetworkGithub extends Network {

	public NetworkGithub() {
		iconOn = R.drawable.ic_github;
		iconOff = R.drawable.ic_github_off;
		title = R.string.network_github;
		description = R.string.network_github_desc;
		tapAction = new EntryIntent().setWeb("https://github.com/carnero/");
		packageName = "com.github.mobile";
	}
}
