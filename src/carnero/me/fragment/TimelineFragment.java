package carnero.me.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import carnero.me.R;
import carnero.me.data._TimelineList;
import carnero.me.model.Entry;
import carnero.me.model.Position;
import carnero.me.model.School;
import carnero.me.model.Work;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class TimelineFragment extends Fragment {

	private Context mContext;
	private Resources mResources;
	private LayoutInflater mInflater;
	private View mContent;
	private LinearLayout mLayout;
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
		mResources = mContext.getResources();

		View view = null;
		for (Entry entry : _TimelineList.ENTRIES) {
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

		final String dSt = mDecimalFormat.format(entry.downloads);
		final SpannableString dSp = new SpannableString(mResources.getString(R.string.cv_downloads, dSt));
		dSp.setSpan(new TextAppearanceSpan(mContext, R.style.Timeline_Card_Description), 0, dSt.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		final String eSt = mResources.getQuantityString(R.plurals.cv_experience, entry.months, entry.months);
		final SpannableString eSp = new SpannableString(eSt + " " + mResources.getString(R.string.cv_experience));
		eSp.setSpan(new TextAppearanceSpan(mContext, R.style.Timeline_Card_Description), 0, eSt.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// texts
		((TextView) layout.findViewById(R.id.title)).setText(entry.name);
		((TextView) layout.findViewById(R.id.downloads)).setText(dSp);
		((TextView) layout.findViewById(R.id.experience)).setText(eSp);
		((TextView) layout.findViewById(R.id.description)).setText(entry.description);
		((TextView) layout.findViewById(R.id.client)).setText(entry.client);
		// background
		if (entry.background != 0) {
			layout.findViewById(R.id.background).setBackgroundResource(entry.background);
		}
		// tapAction
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
		// tapAction
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
