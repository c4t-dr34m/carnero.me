package carnero.me.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class TransparentScrollView extends ScrollView {

	private final ArrayList<SoftReference<IAnimateView>> mNetworks = new ArrayList<SoftReference<IAnimateView>>();
	private ResetWatcher mWatcher;
	private boolean mMoved = false;
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
	public void onAttachedToWindow() {
		super.onAttachedToWindow();

		if (mWatcher != null) {
			mWatcher.kill();
		}
		mWatcher = new ResetWatcher(new ResetHandler());
		mWatcher.start();
	}

	@Override
	public void onDetachedFromWindow() {
		if (mWatcher != null) {
			mWatcher.kill();
			mWatcher = null;
		}

		super.onDetachedFromWindow();
	}

	@Override
	public void onScrollChanged(int h, int v, int hOld, int vOld) {
		super.onScrollChanged(h, v, hOld, vOld);

		synchronized (this) {
			mMoved = true;
		}
	}

	@Override
	public void onOverScrolled(int h, int v, boolean clampX, boolean clampY) {
		super.onOverScrolled(h, v, clampX, clampY);

		synchronized (this) {
			mMoved = true;
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

			if (view instanceof IAnimateView) {
				reference = new SoftReference<IAnimateView>((IAnimateView) view);
				mNetworks.add(reference);
			} else if (view instanceof ViewGroup) {
				findAnimateViews((ViewGroup) view);
			}
		}
	}

	// classes

	private class ResetWatcher extends Thread {

		private boolean mKilled = false;
		private ResetHandler mHandler;

		public ResetWatcher(ResetHandler handler) {
			mHandler = handler;
		}

		@Override
		public void run() {
			while (!mKilled) {
				try {
					sleep(150);
				} catch (InterruptedException ie) {
					// pokemon
				}

				if (mMoved) {
					Message message = mHandler.obtainMessage();
					if (message == null) {
						message = new Message();
					}
					mHandler.sendMessage(message);
				}
			}
		}

		public void kill() {
			mKilled = true;
		}
	}

	private class ResetHandler extends Handler {

		public void handleMessage(Message message) {
			IAnimateView view;
			for (SoftReference<IAnimateView> network : mNetworks) {
				view = network.get();
				if (view != null) {
					view.resetAnimation();
				}
			}

			synchronized (TransparentScrollView.this) {
				mMoved = false;
			}
		}
	}
}
