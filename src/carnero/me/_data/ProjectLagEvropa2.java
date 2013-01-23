package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectLagEvropa2 extends Work {

	public ProjectLagEvropa2() {
		year = 2012;
		month = 6;
		name = "Evropa 2";
		description = "Player for one of most prominent czech radio stations";
		client = "Lagardère Active ČR";
		rating = 4.3f;
		screenshotResource = R.drawable.scr_rb;
		tapAction = EntryIntent.getStoreIntent("eu.inmite.lag.radio.evropa2");
	}
}
