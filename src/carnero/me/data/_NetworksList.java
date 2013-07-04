package carnero.me.data;

import carnero.me.model.Network;

import java.util.ArrayList;

public class _NetworksList {

	public static final ArrayList<Network> ENTRIES = new ArrayList<Network>();

	static {
		ENTRIES.add(new NetworkGithub());
		ENTRIES.add(new NetworkLinkedin());
		ENTRIES.add(new NetworkPlus());
		ENTRIES.add(new NetworkTwitter());
		ENTRIES.add(new NetworkInstagram());
		ENTRIES.add(new Network500px());
		ENTRIES.add(new NetworkPinterest());
		ENTRIES.add(new NetworkFoursquare());
	}
}
