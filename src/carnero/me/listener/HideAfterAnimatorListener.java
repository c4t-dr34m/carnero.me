package carnero.me.listener;

import android.view.View;
import com.nineoldandroids.animation.Animator;

public class HideAfterAnimatorListener implements Animator.AnimatorListener {

	private View mView;

	public HideAfterAnimatorListener(View view) {
		mView = view;
	}

	@Override
	public void onAnimationStart(Animator animator) {
		// nothing
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
