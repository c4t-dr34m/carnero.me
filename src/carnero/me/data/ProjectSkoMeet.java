package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectSkoMeet extends Work {

	public ProjectSkoMeet() {
		year = 2012;
		month = 7;
		name = "ŠKODA Meet App";
		nameShort = "Meet App";
		description = "Social application making in-person meetings easier";
		client = "ŠKODA AUTO";
		downloads = 1000;
		months = 3;
		iconResource = R.drawable.ic_android;
		background = R.drawable.bcg_project_meet_aligned;
		tapAction = new EntryIntent().setPackage("eu.inmite.prj.skoda.meet");
	}
}
