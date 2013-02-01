package carnero.me.data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Position;

@SuppressWarnings("UnusedDeclaration")
public class WorkInmiteSenior extends Position {

	public WorkInmiteSenior() {
		year = 2012;
		month = 11;
		name = "Inmite";
		position = "senior android developer";
		tapAction = EntryIntent.getWebIntent("http://www.inmite.eu");
	}
}
