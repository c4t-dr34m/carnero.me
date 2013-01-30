package carnero.me._data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectCcCgeo extends Work {

	public ProjectCcCgeo() {
		year = 2011;
		month = 9;
		name = "c:geo";
		description = "World's best geocaching client";
		client = "carnero";
		downloads = 1000000;
		months = 0;
		iconResource = R.drawable.ic_android;
		tapAction = EntryIntent.getStoreIntent("cgeo.geocaching");
	}
}
