package carnero.me._data;

import carnero.me.model.EntryIntent;
import carnero.me.model.Position;

@SuppressWarnings("UnusedDeclaration")
public class WorkCyberfox extends Position {

	public WorkCyberfox() {
		year = 2010;
		month = 5;
		name = "Cyber Fox";
		tapAction = EntryIntent.getWebIntent("http://www.cyberfox.cz/en/");
	}
}
