package carnero.me.listener;

import android.view.View;
import com.nineoldandroids.animation.Animator;

public class BothEndsAnimatorListener implements Animator.AnimatorListener {

	private View mView;

	public BothEndsAnimatorListener(View view) {
		mView = view;
	}

	@Override
	public void onAnimationStart(Animator animator) {
		mView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onAnimationEnd(Animator animator) {
		mView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onAnimationRepeat(Animator animator) {
		// nothing
	}

	@Override
	public void onAnimationCancel(Animator animator) {
		// nothing
	}
}