package carnero.me.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import carnero.me.R;

public class NetworksFragment extends Fragment {

	private View mContent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		mContent  = inflater.inflate(R.layout.networks, container, false);

		return mContent;
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

		mContent.findViewById(R.id.linkedin_layout).setOnClickListener(new Launcher(getActivity(), "http://www.linkedin.com/in/carnerocc/"));
		mContent.findViewById(R.id.google_plus_layout).setOnClickListener(new Launcher(getActivity(), "http://plus.google.com/116645889171150251778/posts"));
		mContent.findViewById(R.id.twitter_layout).setOnClickListener(new Launcher(getActivity(), "https://twitter.com/carnero_cc"));
		mContent.findViewById(R.id.instagram_layout).setOnClickListener(new Launcher(getActivity(), "http://instagram.com/_carnero/"));
		mContent.findViewById(R.id.flickr_layout).setOnClickListener(new Launcher(getActivity(), "http://www.flickr.com/photos/carnero/"));
		mContent.findViewById(R.id.fivehundred_layout).setOnClickListener(new Launcher(getActivity(), "http://500px.com/carnero/photos"));
		mContent.findViewById(R.id.pinterest_layout).setOnClickListener(new Launcher(getActivity(), "http://pinterest.com/carnero/"));
		mContent.findViewById(R.id.foursquare_layout).setOnClickListener(new Launcher(getActivity(), "http://foursquare.com/carnero_cc"));
	}

	private class Launcher implements View.OnClickListener {

		private Activity mActivity;
		private String mUrl;

		public Launcher(Activity activity, String url) {
			mActivity = activity;
			mUrl = url;
		}

		@Override
		public void onClick(View view) {
			if (mActivity == null || TextUtils.isEmpty(mUrl)) {
				return;
			}

			final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
			mActivity.startActivity(intent);
		}
	}
}
