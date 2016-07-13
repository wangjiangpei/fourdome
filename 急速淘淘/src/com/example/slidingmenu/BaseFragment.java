package com.example.slidingmenu;

import cn.bmob.v3.datatype.BmobFile;

import com.a.a.in;
import com.fourdome.activity.ShopActivity;
import com.fourdome.bean.ShopBean;
import com.fourdome.utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * BaseFragment
 * */
public abstract class BaseFragment extends Fragment {

	private Toast mT;

	public void ToastMsg(String msg) {
		if (mT == null) {
			mT = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
		}
		mT.setText(msg);
		mT.show();
	}
	public void loadImage(FragmentActivity fragmentActivity, ImageView image, BmobFile bmobFile) {
		ImageLoader loader = ImageLoaderUtils.getInstance(BaseFragment.this
				.getActivity());
		DisplayImageOptions options = ImageLoaderUtils.getOpt();
		BmobFile bmobfile = bmobFile;
		loader.displayImage(
				bmobfile.getFileUrl(fragmentActivity), image,
				options);
	}
	public void tiaozhuan(Class<ShopActivity> class1, ShopBean shopBean) {
		Intent intent = new Intent();
		intent.setClass(getActivity(),class1);
		intent.putExtra("date", shopBean);
	}

}
