package carnero.me.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import carnero.me.Utils;

@SuppressWarnings("UnusedDeclaration")
public class EntryIntent {

	private static final String sAndroidMarket = "com.google.market";
	private static final String sPlayStore = "com.google.vending";
	//
	private String mUrl;
	private String mPackageName;

	public EntryIntent setWeb(String url) {
		mUrl = url;

		return this;
	}

	public EntryIntent setPackage(String packageName) {
		mPackageName = packageName;

		return this;
	}

	public Intent getIntent(Context context) {
		if (!TextUtils.isEmpty(mUrl)) {
			return getWebIntent();
		} else if (!TextUtils.isEmpty(mPackageName)) {
			return getStoreIntent(context);
		}

		return null;
	}

	public Intent getStoreIntent(Context context) {
		final Uri uri;

		if (Utils.isPackageInstalled(context, sAndroidMarket) || Utils.isPackageInstalled(context, sPlayStore)) {
			uri = Uri.parse("market://details?id=" + mPackageName);
		} else {
			uri = Uri.parse("https://play.google.com/store/apps/details?id=" + mPackageName);
		}

		return new Intent(Intent.ACTION_VIEW, uri);
	}

	public Intent getWebIntent() {
		final Uri uri = Uri.parse(mUrl);

		return new Intent(Intent.ACTION_VIEW, uri);
	}
}
