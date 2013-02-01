package carnero.me.data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectCfTC extends Work {

	public ProjectCfTC() {
		year = 2011;
		month = 4;
		name = "TC.cz";
		description = "Website for The Technology Centre of the Academy of Sciences of the Czech Republic, key national institution for the research and development infrastructure.";
		client = "Technology Centre ASCR";
		downloads = 0;
		months = 3;
		iconResource = 0; // iconResource = R.drawable.ic_web;
		background = 0; // background = R.drawable.bcg_project_tc_aligned;
		tapAction = EntryIntent.getWebIntent("http://www.tc.cz/en");
	}
}
