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
		downloads = 50000;
		months = 0;
		iconResource = R.drawable.ic_android;
		tapAction = EntryIntent.getStoreIntent("eu.inmite.apps.smsjizdenka");
	}
}
