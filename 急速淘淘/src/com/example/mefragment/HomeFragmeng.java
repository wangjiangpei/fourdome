package com.example.mefragment;

import java.util.ArrayList;
import java.util.List;

import com.example.slidingmenu.BaseApplication;
import com.example.slidingmenu.BaseFragment;
import com.example.slidingmenu.R;
import com.fourdome.activity.MeActivity;
import com.fourdome.activity.ShopActivity;
import com.fourdome.bean.ShopBean;
import com.fourdome.bean.TradeBean;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 这里是viewprage主页
 * 
 * 
 * @author Administrator
 * 
 */
public class HomeFragmeng extends BaseFragment {

	TextView tv;
	private View v;
	// 幻灯片
	private ViewPager prage;
	private ImageView view1, view2, view3, view4;
	private List<ImageView> viewContainter;
	private LayoutInflater inflater;
	private MyAsyncTask asyn;
	private ListView listView;
	private List<ShopBean> shopList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;

		v = inflater.inflate(R.layout.home_fragment, null);
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i("sow", "onResume");
		// 主界面幻灯片
		viewContainter = new ArrayList<ImageView>();
		final int imgids[] = { R.drawable.a1, R.drawable.a2, R.drawable.a3,
				R.drawable.a4, };
		int ids[] = { R.id.home_view1, R.id.home_view2, R.id.home_view3,
				R.id.home_view4 };
		for (int i = 0; i < 4; i++) {
			viewContainter.add((ImageView) getActivity().findViewById(ids[i]));
		}
		// listview-----------------------
		if (BaseApplication.shopList != null) {
			shopList = BaseApplication.shopList;
		} else {
			shopList = new ArrayList<ShopBean>();
		}
		listView = (ListView) getActivity().findViewById(R.id.home_listView);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				tiaozhuan(ShopActivity.class, shopList.get(arg2));
			}
		});
		listView.setAdapter(new BaseAdapter() {

			private ViewHader vh;
			private float ran = 0.1f;

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				if (arg1 == null) {
					arg1 = getActivity().getLayoutInflater().inflate(
							R.layout.shop_listview_item, null);
					vh = new ViewHader();
					vh.name = (TextView) arg1
							.findViewById(R.id.shop_listview_item_name);
					vh.range = (TextView) arg1
							.findViewById(R.id.shop_listview_item_range);
					vh.send = (TextView) arg1
							.findViewById(R.id.shop_listview_item_send);
					vh.show = (TextView) arg1
							.findViewById(R.id.shop_listview_item_intro);
					vh.image = (ImageView) arg1
							.findViewById(R.id.shop_listview_item_head);
					arg1.setTag(vh);
				}
				vh = (ViewHader) arg1.getTag();
				vh.name.setText(shopList.get(arg0).getShopName());
				//计算/设置距离
				int loa = (int) shopList.get(arg0).getShopDistance()
						.distanceInKilometersTo(BaseApplication.mePoint) * 100;
				ran = (float) loa / 100;
				vh.range.setText("距离" + ran + "公里");
				vh.send.setText("派送范围" + shopList.get(arg0).getSendRange()
						+ "公里");
				vh.show.setText(shopList.get(arg0).getShopIntro());
				//加载图片
				loadImage(getActivity(),vh.image, shopList.get(arg0).getShopPicture());
				return arg1;
			}

			@Override
			public long getItemId(int arg0) {
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				return null;
			}

			@Override
			public int getCount() {
				return shopList.size();
			}

			class ViewHader {
				TextView name, range, show, send;
				ImageView image;
			}
		});

		// Viewprage-------------------
		prage = (ViewPager) getActivity().findViewById(R.id.home_prage_slide);
		asyn = new MyAsyncTask();
		asyn.execute(0);
		prage.setOnPageChangeListener(pragelistener);
		prage.setAdapter(new PagerAdapter() {

			// viewpager中的组件数量
			@Override
			public int getCount() {
				return 4;
			}

			// 滑动切换的时候销毁当前的组件
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				((ViewPager) container).removeView(viewContainter.get(position));
			}

			// 每次滑动的时候生成的组件
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View v = inflater.inflate(R.layout.prage_item, null);
				ImageView img = (ImageView) v
						.findViewById(R.id.prage_item_image);
				img.setBackground(getResources().getDrawable(imgids[position]));
				((ViewPager) container).addView(v);
				return v;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getItemPosition(Object object) {
				return super.getItemPosition(object);
			}
		});

	}// ------------------------OnResum
		// ViewPrage滑动事件

	private OnPageChangeListener pragelistener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			operateMenu(arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			switch (arg0) {
			case 1:

				break;
			case 2:

				break;
			case 0:

				break;

			default:
				break;
			}
		}
	};

	// 实现ViewPrage自动滑动
	class MyAsyncTask extends AsyncTask<Integer, Integer, Void> {

		@Override
		protected Void doInBackground(Integer... arg0) {
			for (int i = 0; i < 4; i++) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (i == 3) {
					publishProgress(i);
					i = -1;
				} else {
					publishProgress(i);
				}
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			int count = values[0];
			operateMenu(count);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}// ---------------------------------------Asyn
		// 执行viewprage 变动操作

	private void operateMenu(int count) {
		prage.setCurrentItem(count);
		for (int i = 0; i < 4; i++) {
			if (i == count) {
				viewContainter.get(i).setImageResource(R.drawable.huidian);
			} else {
				viewContainter.get(i).setImageResource(R.drawable.baidian);
			}
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i("show", "OnPause");
		asyn.cancel(getRetainInstance());
	}
}
