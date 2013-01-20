package carnero.me.model;

public class Entry implements Comparable<Entry> {

	public int year;
	public int month;
	public String name;

	public int compareTo(Entry entry) {
		int status = 0;

		if (year > entry.year || (year == entry.year && month > entry.month)) {
			status = 1;
		} else if (year < entry.year || (year == entry.year && month < entry.month)) {
			status = -1;
		}

		return status;
	}
}
