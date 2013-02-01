package carnero.me.data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectChFotoalba extends Work {

	public ProjectChFotoalba() {
		year = 2009;
		month = 5;
		name = "Fotoalba.cz";
		description = "Centrum's photo sharing service. Centrum Holding is a net media company active in the Central and Eastern Europe.";
		client = "centrum holdings";
		downloads = 0;
		months = 36;
		iconResource = 0; // iconResource = R.drawable.ic_web;
		background = 0; // background = R.drawable.bcg_project_fotoalba_aligned;
		tapAction = EntryIntent.getWebIntent("http://fotoalba.centrum.cz/");
	}
}
