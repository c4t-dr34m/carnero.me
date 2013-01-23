package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectCcTimeline extends Work {

	public ProjectCcTimeline() {
		year = 2012;
		month = 7;
		name = "Photo Timeline";
		description = "Gallery that shows your shots ordered by date - in timeline or in calendar";
		client = "carnero";
		rating = 4.5f;
		screenshotResource = R.drawable.scr_rb;
		tapAction = EntryIntent.getStoreIntent("carnero.timeline");
	}
}
