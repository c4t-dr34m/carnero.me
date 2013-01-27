package carnero.me.model;

import android.content.Intent;
import android.net.Uri;

@SuppressWarnings("UnusedDeclaration")
public class EntryIntent {

	public static Intent getStoreIntent(String packageName) {
		final Uri uri = Uri.parse("market://details?id=" + packageName);

		return new Intent(Intent.ACTION_VIEW, uri);
	}

	public static Intent getWebIntent(String url) {
		final Uri uri = Uri.parse(url);

		return new Intent(Intent.ACTION_VIEW, uri);
	}
}
