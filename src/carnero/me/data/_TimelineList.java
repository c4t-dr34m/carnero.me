package carnero.me.data;

import carnero.me.model.Entry;

import java.util.ArrayList;
import java.util.Collections;

public class _TimelineList {

	public static final ArrayList<Entry> ENTRIES = new ArrayList<Entry>();

	static {
		// projects
		ENTRIES.add(new ProjectCfNWR());
		ENTRIES.add(new ProjectCfAWT());
		ENTRIES.add(new ProjectCfOKD());
		ENTRIES.add(new ProjectCfTC());
		ENTRIES.add(new ProjectChFotoalba());
		ENTRIES.add(new ProjectChXchat());
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
		ENTRIES.add(new ProjectAdlTaxi());
		// jobs
		ENTRIES.add(new WorkInmiteSenior());
		ENTRIES.add(new WorkOracle());
		ENTRIES.add(new WorkInmite());
		ENTRIES.add(new WorkCyberfox());
		ENTRIES.add(new WorkCentrum());
		// education
		ENTRIES.add(new EducationTUL());

		Collections.sort(ENTRIES);
	}
}
