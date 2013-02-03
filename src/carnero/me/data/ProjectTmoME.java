package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectTmoME extends Work {

	public ProjectTmoME() {
		year = 2012;
		month = 4;
		name = "ME2012";
		description = "Live UEFA's EURO 2012 coverage ";
		client = "T-Mobile Czech Republic";
		downloads = 10000;
		months = 1;
		iconResource = R.drawable.ic_android;
		background = R.drawable.bcg_project_me2012_aligned;
		tapAction = new EntryIntent().setPackage("cz.t_mobile.me2012");
	}
}
