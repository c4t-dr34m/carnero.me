package carnero.me.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import carnero.me.R;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class TransparentScrollView extends ScrollView {

	public final ArrayList<IAnimateView> mNetworks = new ArrayList<IAnimateView>();

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
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		init();
	}

	@Override
	public void onScrollChanged(int h, int v, int hOld, int vOld) {
		super.onScrollChanged(h, v, hOld, vOld);

		for (IAnimateView network : mNetworks) {
			network.resetAnimation();
		}
	}

	public void init() {
		final LinearLayout networksLayout = (LinearLayout) findViewById(R.id.networks_layout);
		final int networksCnt = networksLayout.getChildCount();
		View networkView;

		for (int i = 0; i < networksCnt; i++) {
			networkView = networksLayout.getChildAt(i);

			if (networkView instanceof IAnimateView) {
				mNetworks.add((IAnimateView) networkView);
			}
		}
	}
}
