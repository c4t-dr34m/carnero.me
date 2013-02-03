package carnero.me.data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Position;

@SuppressWarnings("UnusedDeclaration")
public class WorkCentrum extends Position {

	public WorkCentrum() {
		year = 2006;
		month = 8;
		name = "centrum holdings";
		position = "php\u00A0developer";
		tapAction = new EntryIntent().setWeb("http://www.centrumholdings.com/en/about-us/");
	}
}
