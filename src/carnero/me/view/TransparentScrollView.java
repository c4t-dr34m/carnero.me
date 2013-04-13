package carnero.me.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class TransparentScrollView extends ScrollView {

	private final ArrayList<SoftReference<IAnimateView>> mNetworks = new ArrayList<SoftReference<IAnimateView>>();
	private long mCleanup = 0;

	public TransparentScrollView(Context context) {
		super(context);
	}

	public TransparentScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TransparentScrollView(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
	}

	@Override
	public void onScrollChanged(int h, int v, int hOld, int vOld) {
		super.onScrollChanged(h, v, hOld, vOld);

		IAnimateView view;
		for (SoftReference<IAnimateView> network : mNetworks) {
			view = network.get();
			if (view != null) {
				view.resetAnimation();
			}
		}
	}

	public void registerView(View view) {
		if (view instanceof IAnimateView) {
			mNetworks.add(new SoftReference<IAnimateView>((IAnimateView) view));
		} else if (view instanceof ViewGroup) {
			findAnimateViews((ViewGroup) view);
		}

		if ((mCleanup + 1000) < SystemClock.elapsedRealtime()) {
			// remove expired references
			final ArrayList<SoftReference> trash = new ArrayList<SoftReference>();

			IAnimateView iav;
			for (SoftReference<IAnimateView> network : mNetworks) {
				iav = network.get();
				if (iav == null) {
					trash.add(network);
				}
			}

			for (SoftReference reference : trash) {
				mNetworks.remove(reference);
			}
			trash.clear();

			mCleanup = SystemClock.elapsedRealtime();
		}
	}

	private void findAnimateViews(ViewGroup group) {
		final int networksCnt = group.getChildCount();
		View view;

		SoftReference<IAnimateView> reference;
		for (int i = 0; i < networksCnt; i++) {
			view = group.getChildAt(i);

			Log.d(">>>>", "checking: " + view.getClass().getSimpleName());

			if (view instanceof IAnimateView) {
				reference = new SoftReference<IAnimateView>((IAnimateView) view);
				mNetworks.add(reference);
			} else if (view instanceof ViewGroup) {
				findAnimateViews((ViewGroup) view);
			}
		}
	}
}
