package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectRbEkonto extends Work {

	public ProjectRbEkonto() {
		year = 2012;
		month = 11;
		name = "Mobile eKonto";
		description = "Full-featured smartphone banking";
		client = "Raiffeisenbank";
		rating = 4.8f;
		screenshotResource = R.drawable.scr_rb;
		tapAction = EntryIntent.getStoreIntent("cz.rb.app.smartphonebanking");
	}
}
