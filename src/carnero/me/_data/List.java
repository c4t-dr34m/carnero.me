package carnero.me._data;

import carnero.me.model.Entry;

import java.util.ArrayList;
import java.util.Collections;

public class List {

	public static final ArrayList<Entry> ENTRIES = new ArrayList<Entry>();

	static {
		ENTRIES.add(new ProjectCcCgeo());
		ENTRIES.add(new ProjectCcTimeline());
		ENTRIES.add(new ProjectImtTicket());
		ENTRIES.add(new ProjectLagEvropa2());
		ENTRIES.add(new ProjectLagPigy());
		ENTRIES.add(new ProjectLagRadioTycoon());
		ENTRIES.add(new ProjectLagYouRadio());
		ENTRIES.add(new ProjectRbEkonto());
		ENTRIES.add(new ProjectSkoMeet());
		ENTRIES.add(new ProjectTmoME());
		ENTRIES.add(new ProjectVdfFotografik());

		Collections.sort(ENTRIES);
	}
}