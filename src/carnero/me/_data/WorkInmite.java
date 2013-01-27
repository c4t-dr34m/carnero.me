package carnero.me._data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Position;

@SuppressWarnings("UnusedDeclaration")
public class WorkInmite extends Position {

	public WorkInmite() {
		year = 2011;
		month = 5;
		name = "Inmite";
		tapAction = EntryIntent.getWebIntent("http://www.inmite.eu");
	}
}
