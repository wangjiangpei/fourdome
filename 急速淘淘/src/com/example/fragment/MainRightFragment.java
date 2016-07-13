package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.slidingmenu.BaseFragment;
import com.example.slidingmenu.R;
import com.fourdome.bean.Btype;
import com.fourdome.bean.Ltype;

/**
 * 右面侧滑菜单功能界面
 */
public class MainRightFragment extends BaseFragment implements OnClickListener {
	private String[] bTypes = { "水果蔬菜", "海鲜水产", "猪羊牛肉", "冷冻食品", "中外名酒",
			"小型家电 ", "个护化妆 ", "时尚女装", "时尚男装", "男女内衣" };
	private TextView type1, type2, type3, type4, type5, type6, type7, type8,
			type9, type10;
	int[] ids = { R.id.type1, R.id.type2, R.id.type3, R.id.type4, R.id.type5,
			R.id.type6, R.id.type7, R.id.type8, R.id.type9, R.id.type10, };
	TextView[] tvs = { type1, type2, type3, type4, type5, type6, type7, type8,
			type9, type10, };
	private View view;
	private GridView gridView;
	private List<Ltype> gridDates;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.hsy_main_right_fragment, null);
//		initViews();
//		initOpers();
		return view;
	}

	private void initViews() {
		type1 = (TextView) getActivity().findViewById(R.id.type1);
		type2 = (TextView) getActivity().findViewById(R.id.type2);
		type3 = (TextView) getActivity().findViewById(R.id.type3);
		type4 = (TextView) getActivity().findViewById(R.id.type4);
		type5 = (TextView) getActivity().findViewById(R.id.type5);
		type6 = (TextView) getActivity().findViewById(R.id.type6);
		type7 = (TextView) getActivity().findViewById(R.id.type7);
		type8 = (TextView) getActivity().findViewById(R.id.type8);
		type9 = (TextView) getActivity().findViewById(R.id.type9);
		type10 = (TextView) getActivity().findViewById(R.id.type10);
		type1.setOnClickListener(this);
		type2.setOnClickListener(this);
		type3.setOnClickListener(this);
		type4.setOnClickListener(this);
		type5.setOnClickListener(this);
		type6.setOnClickListener(this);
		type7.setOnClickListener(this);
		type8.setOnClickListener(this);
		type9.setOnClickListener(this);
		type10.setOnClickListener(this);
		gridDates = new ArrayList<Ltype>();
		gridView = (GridView) view.findViewById(R.id.right_gridview);

	}

	private void initOpers() {
		//gridView.setAdapter(adapter);
		notifyDataSetInvalidateds(0);
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onClick(View view) {
		int count = -1;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == view.getId()) {
				count = i;
			}
		}
	}

	private void notifyDataSetInvalidateds(final int count) {
		BmobQuery<Ltype> query = new BmobQuery<Ltype>();
		query.addWhereEqualTo("Btype", bTypes[count]);
		query.findObjects(getActivity(), new FindListener<Ltype>() {

			@Override
			public void onError(int arg0, String arg1) {
				ToastMsg("网络错误");
			}

			@Override
			public void onSuccess(List<Ltype> arg0) {
				gridDates = arg0;
				adapter.notifyDataSetInvalidated();
				for (int i = 0; i < tvs.length; i++) {
					if (i==count) {
						tvs[i].setTextColor(Color.RED);
					}else{
						tvs[i].setTextColor(Color.WHITE);
					}
				}
			}
		});
	}

	private BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			return null;
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
			return 0;
		}
	};
}
