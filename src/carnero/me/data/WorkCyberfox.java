package carnero.me.data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Position;

@SuppressWarnings("UnusedDeclaration")
public class WorkCyberfox extends Position {

	public WorkCyberfox() {
		year = 2010;
		month = 5;
		name = "Cyber Fox";
		position = "php\u00A0developer";
		tapAction = new EntryIntent().setWeb("http://www.cyberfox.cz/en/");
	}
}
