package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectLagYouRadio extends Work {

	public ProjectLagYouRadio() {
		year = 2013;
		month = 1;
		name = "YouRadio";
		description = "Czech on-line radio player following user's taste";
		client = "Lagardère Active ČR";
		rating = 4.1f;
		screenshotResource = R.drawable.scr_youradio;
		tapAction = EntryIntent.getStoreIntent("eu.inmite.prj.lag.youradio");
	}
}
