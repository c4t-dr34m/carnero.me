package carnero.me.listener;

import android.view.View;
import com.nineoldandroids.animation.Animator;

public class DisplayBeforeAnimatorListener implements Animator.AnimatorListener {

	private View mView;

	public DisplayBeforeAnimatorListener(View view) {
		mView = view;
	}

	@Override
	public void onAnimationStart(Animator animator) {
		mView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onAnimationEnd(Animator animator) {
		// nothing
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