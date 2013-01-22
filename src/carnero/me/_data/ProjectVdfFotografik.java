package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectVdfFotografik extends Work {

	public ProjectVdfFotografik() {
		year = 2012;
		month = 7;
		name = "Fotografik";
		description = "Simple photo editor";
		client = "Vodafone Czech Republic";
		rating = 3.8f;
		screenshotResource = R.drawable.scr_rb_aligned;
		tapAction = EntryIntent.getStoreIntent("eu.inmite.android.vodafone.mms");
	}
}
