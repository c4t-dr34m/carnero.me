package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectRbEkonto extends Work {

	public ProjectRbEkonto() {
		year = 2012;
		month = 11;
		name = "Mobile eKonto";
		description = "Full-featured smartphone banking";
		client = "Raiffeisenbank";
		downloads = 10000;
		months = 5;
		iconResource = R.drawable.ic_android;
		background = R.drawable.bcg_project_ekonto_aligned;
		tapAction = EntryIntent.getStoreIntent("cz.rb.app.smartphonebanking");
	}
}
