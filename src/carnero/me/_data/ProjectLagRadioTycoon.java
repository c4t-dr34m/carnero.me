package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectLagRadioTycoon extends Work {

	public ProjectLagRadioTycoon() {
		year = 2012;
		month = 4;
		name = "Radio Tycoon";
		description = "Real-time game allowing everyone to be owner of radio station";
		client = "Lagardère Active ČR";
		downloads = 10000;
		months = 3;
		iconResource = R.drawable.ic_android;
		tapAction = EntryIntent.getStoreIntent("eu.inmite.prj.lag.radiotyc.android");
	}
}
