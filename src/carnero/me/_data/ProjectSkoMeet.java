package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectSkoMeet extends Work {

	public ProjectSkoMeet() {
		year = 2012;
		month = 7;
		name = "Meet App";
		description = "Social application making in-person meetings easier";
		client = "ŠKODA AUTO";
		rating = 3.8f;
		iconResource = R.drawable.ic_project_meet;
		tapAction = EntryIntent.getStoreIntent("eu.inmite.prj.skoda.meet");
	}
}
