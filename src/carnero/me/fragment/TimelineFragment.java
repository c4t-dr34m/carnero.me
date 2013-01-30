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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class TimelineFragment extends Fragment {

	private Context mContext;
	private LayoutInflater mInflater;
	private View mContent;
	private LinearLayout mLayout;
	private String[] mMonths;
	private NumberFormat mDecimalFormat = DecimalFormat.getInstance(Locale.getDefault());

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
		mMonths = getResources().getStringArray(R.array.months);

		View view = null;
		for (Entry entry : List.ENTRIES) {
			if (entry instanceof Work) {
				view = fillLayout((Work) entry);
			} else if (entry instanceof Position) {
				view = fillLayout((Position) entry);
			} else if (entry instanceof School) {
				// view = fillLayout((School) entry);
			}

			if (view != null) {
				mLayout.addView(view);
			}
		}
	}

	private View fillLayout(Work entry) {
		final View layout = mInflater.inflate(R.layout.item_timeline_work, null);

		// texts
		((TextView) layout.findViewById(R.id.title)).setText(entry.name);
		((TextView) layout.findViewById(R.id.experience)).setText(mDecimalFormat.format(entry.downloads));
		((TextView) layout.findViewById(R.id.downloads)).setText(mDecimalFormat.format(entry.months));
		((TextView) layout.findViewById(R.id.description)).setText(entry.description);
		((TextView) layout.findViewById(R.id.client)).setText(entry.client);
		// action
		if (entry.tapAction != null) {
			layout.setOnClickListener(new EntryAction(entry.tapAction));
		}

		return layout;
	}

	private View fillLayout(Position entry) {
		final View layout = mInflater.inflate(R.layout.item_timeline_position, null);

		// texts
		((TextView) layout.findViewById(R.id.title)).setText(entry.name);
		((TextView) layout.findViewById(R.id.position)).setText(entry.position);
		// action
		if (entry.tapAction != null) {
			layout.setOnClickListener(new EntryAction(entry.tapAction));
		}

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
