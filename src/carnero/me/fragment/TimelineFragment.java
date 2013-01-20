package carnero.me.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import carnero.me.R;

public class TimelineFragment extends Fragment {

	private Context mContext;
	private View mContent;
	private LinearLayout mEntries;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		mContent = inflater.inflate(R.layout.timeline, container, false);

		mEntries = (LinearLayout) mContent.findViewById(R.id.entries);

		return mContent;
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

		mContext = getActivity().getBaseContext();

		// TODO carnero: fill-in CV entries
	}
}
