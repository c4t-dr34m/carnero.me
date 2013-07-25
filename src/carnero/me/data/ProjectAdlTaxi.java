package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectAdlTaxi extends Work {

	public ProjectAdlTaxi() {
		year = 2013;
		month = 7;
		name = "Liftago Taxi";
		nameShort = "Liftago";
		description = "Liftago simply makes your taxi ride hassle-free";
		client = "ADLER iTech";
		downloads = 1000;
		months = 10;
		iconResource = R.drawable.ic_android;
		background = R.drawable.bcg_project_liftago_aligned;
		tapAction = new EntryIntent().setPackage("com.adleritech.app.liftago.passenger");
	}
}
