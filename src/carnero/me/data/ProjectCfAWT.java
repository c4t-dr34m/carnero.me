package carnero.me.data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectCfAWT extends Work {

	public ProjectCfAWT() {
		year = 2010;
		month = 11;
		name = "AWT.eu";
		description = "Website for AWT Group, largest private provider of rail freight services in Europe.";
		client = "AWT Group";
		downloads = 0;
		months = 3;
		iconResource = 0; // iconResource = R.drawable.ic_web;
		background = 0; // background = R.drawable.bcg_project_awt_aligned;
		tapAction = EntryIntent.getStoreIntent("http://www.awt.eu/en");
	}
}
