package carnero.me;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

public class Utils {

	private static PackageManager sPM;

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
}
