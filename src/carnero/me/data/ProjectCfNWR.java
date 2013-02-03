package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectCfNWR extends Work {

	public ProjectCfNWR() {
		year = 2010;
		month = 8;
		name = "NewWorldResources.eu";
		description = "Website for New World Resources, one of Central Europe's leading hard coal and coke producers.";
		client = "New World Resources";
		downloads = 0;
		months = 3;
		iconResource = 0; // iconResource = R.drawable.ic_web;
		background = R.drawable.bcg_project_nwr_aligned;
		tapAction = new EntryIntent().setWeb("http://www.newworldresources.eu/en");
	}
}
