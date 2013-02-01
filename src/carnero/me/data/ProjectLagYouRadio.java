package carnero.me.data;

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
		downloads = 10000;
		months = 1;
		iconResource = R.drawable.ic_android;
		background = R.drawable.bcg_project_youradio_aligned;
		tapAction = EntryIntent.getStoreIntent("eu.inmite.prj.lag.youradio");
	}
}
