package carnero.me.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import carnero.me.R;

public class NetworksFragment extends Fragment {

	private View mContent;
	private LinearLayout mLayout;
	private int mNetworkActive = 0;
	private int mNetworkInactive = 0;
	private PackageManager mPM;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		mContent  = inflater.inflate(R.layout.networks, container, false);

		mLayout = (LinearLayout) mContent.findViewById(R.id.networks_layout);

		return mContent;
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

		mNetworkActive = 0;
		resort(mContent.findViewById(R.id.github_layout), "com.github.mobile").setOnClickListener(new Launcher(getActivity(), "https://github.com/carnero/"));
		resort(mContent.findViewById(R.id.linkedin_layout), "com.linkedin.android").setOnClickListener(new Launcher(getActivity(), "http://www.linkedin.com/in/carnerocc/"));
		resort(mContent.findViewById(R.id.google_plus_layout), "com.google.android.apps.plus").setOnClickListener(new Launcher(getActivity(), "http://plus.google.com/116645889171150251778/posts"));
		resort(mContent.findViewById(R.id.twitter_layout), "com.twitter.android").setOnClickListener(new Launcher(getActivity(), "https://twitter.com/carnero_cc"));
		resort(mContent.findViewById(R.id.instagram_layout), "com.instagram.android").setOnClickListener(new Launcher(getActivity(), "http://instagram.com/_carnero"));
		resort(mContent.findViewById(R.id.flickr_layout), "com.yahoo.mobile.client.android.flickr").setOnClickListener(new Launcher(getActivity(), "http://www.flickr.com/photos/carnero/"));
		resort(mContent.findViewById(R.id.fivehundred_layout), "com.fivehundredpx.viewer").setOnClickListener(new Launcher(getActivity(), "http://500px.com/carnero"));
		resort(mContent.findViewById(R.id.pinterest_layout), "com.pinterest").setOnClickListener(new Launcher(getActivity(), "http://pinterest.com/carnero/"));
		resort(mContent.findViewById(R.id.foursquare_layout), "com.joelapenna.foursquared").setOnClickListener(new Launcher(getActivity(), "https://foursquare.com/carnero_cc"));

		if (mNetworkActive > 0 && mNetworkInactive > 0) {
			final View separator = mContent.findViewById(R.id.separator);

			mLayout.removeView(separator);
			mLayout.addView(separator, mNetworkActive);

			separator.setVisibility(View.VISIBLE);
		}
	}

	private View resort(View view, String packageName) {
		if (isPackageInstalled(packageName)) {
			mLayout.removeView(view);
			mLayout.addView(view, mNetworkActive);

			mNetworkActive++;

			view.setAlpha(1.0f);
		} else {
			mNetworkInactive++;

			view.setAlpha(0.6f);
		}

		return view;
	}

	private boolean isPackageInstalled(String clazz) {
		if (TextUtils.isEmpty(clazz)) {
			return false;
		}

		if (mPM == null) {
			mPM = getActivity().getPackageManager();
		}

		try {
			mPM.getPackageInfo(clazz, 0);

			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
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
