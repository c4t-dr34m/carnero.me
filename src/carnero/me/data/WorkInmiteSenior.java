package carnero.me.data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Position;

@SuppressWarnings("UnusedDeclaration")
public class WorkInmiteSenior extends Position {

	public WorkInmiteSenior() {
		year = 2012;
		month = 11;
		name = "Inmite";
		position = "senior\u00A0android\u00A0developer";
		tapAction = new EntryIntent().setWeb("http://www.inmite.eu");
	}
}
