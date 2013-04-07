package carnero.me.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class TransparentScrollView extends ScrollView {

	private final ArrayList<IAnimateView> mNetworks = new ArrayList<IAnimateView>();

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

	private void init() {
		findAnimateViews(this);
	}

	private void findAnimateViews(ViewGroup group) {
		final int networksCnt = group.getChildCount();
		View view;

		for (int i = 0; i < networksCnt; i++) {
			view = group.getChildAt(i);

			if (view instanceof IAnimateView) {
				mNetworks.add((IAnimateView) view);
			} else if (view instanceof ViewGroup) {
				findAnimateViews((ViewGroup) view);
			}
		}
	}
}
