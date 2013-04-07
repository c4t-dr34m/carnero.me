package carnero.me.model;

public abstract class Entry implements Comparable<Entry> {

	public int year;
	public int month;
	public String name;
	// constants
	public static enum TYPE {
		TYPE_WORK,
		TYPE_POSITION,
		TYPE_EDUCATION
	};

	public int compareTo(Entry entry) {
		int status = 0;

		if (year > entry.year || (year == entry.year && month > entry.month)) {
			status = -1;
		} else if (year < entry.year || (year == entry.year && month < entry.month)) {
			status = 1;
		}

		return status;
	}
}
