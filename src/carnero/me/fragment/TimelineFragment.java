package carnero.me.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import carnero.me.R;
import carnero.me._data.List;
import carnero.me.model.Entry;
import carnero.me.model.Position;
import carnero.me.model.School;
import carnero.me.model.Work;

public class TimelineFragment extends Fragment {

	private Context mContext;
	private LayoutInflater mInflater;
	private View mContent;
	private LinearLayout mLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		mInflater = inflater;
		mContent = inflater.inflate(R.layout.timeline, container, false);

		mLayout = (LinearLayout) mContent.findViewById(R.id.entries);

		return mContent;
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

		mContext = getActivity().getBaseContext();

		View view = null;
		for (Entry entry : List.ENTRIES) {
			if (entry instanceof Work) {
				view = fillLayoutWork((Work) entry);
			} else if (entry instanceof Position) {
				// view = fillLayoutPosition(Position entry);
			} else if (entry instanceof School) {
				// view = fillLayoutSchool(School entry);
			}

			if (view != null) {
				mLayout.addView(view);
			}
		}
	}

	private View fillLayoutWork(Work entry) {
		final View layout = mInflater.inflate(R.layout.item_timeline_work, null);

		String month = String.valueOf(entry.month);
		if (month.length() < 2) {
			month = "0" + month;
		}
		// texts
		((TextView) layout.findViewById(R.id.month)).setText(month);
		((TextView) layout.findViewById(R.id.year)).setText(String.valueOf(entry.year));
		((TextView) layout.findViewById(R.id.title)).setText(entry.name);
		((TextView) layout.findViewById(R.id.description)).setText(entry.description);
		((TextView) layout.findViewById(R.id.client)).setText(entry.client);
		// screenshot
		layout.findViewById(R.id.screenshot).setBackgroundResource(entry.screenshotResource);
		// stars
		final LinearLayout stars = (LinearLayout) layout.findViewById(R.id.stars);
		final int full = (int) Math.floor(entry.rating);
		final int part = Math.round((entry.rating - full) * 10);

		stars.removeAllViewsInLayout();
		for (int i = 1; i <= full; i++) {
			stars.addView(mInflater.inflate(R.layout.item_star, stars, false));
		}
		if (part >= 7) {
			stars.addView(mInflater.inflate(R.layout.item_star, stars, false));
		} else if (part >= 3) {
			stars.addView(mInflater.inflate(R.layout.item_star_half, stars, false));
		}
		// action
		layout.setOnClickListener(new EntryAction(entry.tapAction));

		return layout;
	}

	// classes
	private class EntryAction implements View.OnClickListener {

		private Intent mIntent;

		public EntryAction(Intent intent) {
			mIntent = intent;
		}

		@Override
		public void onClick(View v) {
			getActivity().startActivity(mIntent);
		}
	}
}
