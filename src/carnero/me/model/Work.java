package carnero.me.model;

import android.content.Intent;

@SuppressWarnings("UnusedDeclaration")
public abstract class Work extends Entry {

	public String description;
	public String client;
	public int background;
	public int iconResource;
	public int downloads;
	public int months;
	public Intent tapAction;
}
