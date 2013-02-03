package carnero.me.data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Position;

@SuppressWarnings("UnusedDeclaration")
public class WorkInmite extends Position {

	public WorkInmite() {
		year = 2011;
		month = 5;
		name = "Inmite";
		position = "android\u00A0developer";
		tapAction = new EntryIntent().setWeb("http://www.inmite.eu");
	}
}
