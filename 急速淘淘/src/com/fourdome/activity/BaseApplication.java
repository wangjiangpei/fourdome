package com.fourdome.activity;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobGeoPoint;

import com.example.fragment.MainActivity;
import com.fourdome.bean.ShopBean;
import com.fourdome.bean.User;

import android.app.Application;

public class BaseApplication extends Application {
	
	public static List<ShopBean> shopList;
	public static BmobGeoPoint mePoint;
	public static User user;
	public static MainActivity main;

	@Override
	public void onCreate() {
		super.onCreate();
		Bmob.initialize(this, "3b874ca04c134fe3f177d4b54d5f63d8");
	}
}
