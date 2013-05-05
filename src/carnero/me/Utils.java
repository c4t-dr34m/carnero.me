package carnero.me;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

public class Utils {

	private static PackageManager sPM;

	public static boolean isWide(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int width = (int) (dm.widthPixels / dm.density);

		if (width > Constants.TABLET_MIN_WIDTH_DP) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPackageInstalled(Context context, String clazz) {
		if (TextUtils.isEmpty(clazz)) {
			return false;
		}

		if (sPM == null) {
			sPM = context.getPackageManager();
		}

		try {
			sPM.getPackageInfo(clazz, 0);

			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}

	public static boolean isPackageInstalled(Context context, String[] classes) {
		if (classes == null || classes.length == 0) {
			return false;
		}

		if (sPM == null) {
			sPM = context.getPackageManager();
		}

		for (String clazz : classes) {
			try {
				sPM.getPackageInfo(clazz, 0);

				return true;
			} catch (PackageManager.NameNotFoundException e) {
				// pokemon
			}
		}

		return false;
	}
}
