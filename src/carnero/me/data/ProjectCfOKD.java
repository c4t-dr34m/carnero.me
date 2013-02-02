package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectCfOKD extends Work {

	public ProjectCfOKD() {
		year = 2011;
		month = 2;
		name = "OKD.cz";
		description = "Website for OKD, the only hard coal producer in Czech Republic.";
		client = "OKD";
		downloads = 0;
		months = 3;
		iconResource = 0; // iconResource = R.drawable.ic_web;
		background = R.drawable.bcg_project_okd_aligned;
		tapAction = EntryIntent.getWebIntent("http://www.okd.cz/en");
	}
}
