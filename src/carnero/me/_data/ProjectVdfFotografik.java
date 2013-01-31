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
		downloads = 10000;
		months = 1;
		iconResource = R.drawable.ic_android;
		tapAction = EntryIntent.getStoreIntent("eu.inmite.android.vodafone.mms");
	}
}
