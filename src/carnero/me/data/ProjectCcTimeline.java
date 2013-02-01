package carnero.me.data;

import carnero.me.R;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectCcTimeline extends Work {

	public ProjectCcTimeline() {
		year = 2012;
		month = 7;
		name = "Photo Timeline";
		description = "Gallery that shows your shots ordered by date - in timeline or in calendar";
		client = "carnero";
		downloads = 20000;
		months = 3;
		iconResource = R.drawable.ic_android;
		background = R.drawable.bcg_project_timeline_aligned;
		tapAction = null;
	}
}
