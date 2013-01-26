package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectLagPigy extends Work {

	public ProjectLagPigy() {
		year = 2011;
		month = 11;
		name = "Pigy.cz";
		description = "Radio player for children";
		client = "Lagardère Active ČR";
		rating = 4.0f;
		iconResource = R.drawable.ic_android;
		tapAction = EntryIntent.getStoreIntent("eu.inmite.lag.pigy.player");
	}
}
