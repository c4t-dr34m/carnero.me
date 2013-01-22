package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectImtTicket extends Work {

	public ProjectImtTicket() {
		year = 2012;
		month = 6;
		name = "SMS Ticket";
		description = "User friendly way to order ticket in Czech republic";
		client = "Inmite";
		rating = 4.6f;
		screenshotResource = R.drawable.scr_rb_aligned;
		tapAction = EntryIntent.getStoreIntent("eu.inmite.apps.smsjizdenka");
	}
}
