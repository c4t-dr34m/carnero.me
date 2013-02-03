package carnero.me.data;

import carnero.me.model.Education;
import carnero.me.model.EntryIntent;

@SuppressWarnings("UnusedDeclaration")
public class EducationTUL extends Education {

	public EducationTUL() {
		year = 2006;
		month = 8;
		name = "Technical University of Liberec";
		description = "EIŘS,\u00A0bachelor's\u00A0degree";
		tapAction = new EntryIntent().setWeb("http://www.fm.tul.cz/en");
	}
}
