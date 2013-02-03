package carnero.me.data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectChXchat extends Work {

	public ProjectChXchat() {
		year = 2010;
		month = 4;
		name = "Xchat.cz";
		description = "Centrum's social network. Centrum Holding is a net media company active in the Central and Eastern Europe.";
		client = "centrum holdings";
		downloads = 0;
		months = 12;
		iconResource = 0; // iconResource = R.drawable.ic_web;
		background = 0; // background = R.drawable.bcg_project_xchat_aligned;
		tapAction = new EntryIntent().setWeb("http://xchat.centrum.cz//");
	}
}
