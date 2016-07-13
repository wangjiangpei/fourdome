package com.example.slidingmenu;

import java.util.List;

import c.b.BP;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobGeoPoint;

import com.baidu.mapapi.SDKInitializer;
import com.example.fragment.MainActivity;
import com.fourdome.bean.ShopBean;
import com.fourdome.bean.User;

import android.app.Application;

public class BaseApplication extends Application {
	
	public static List<ShopBean> shopList;
	public static BmobGeoPoint mePoint;
	public static User user;
	public static MainActivity main;
	public static BmobGeoPoint point;

	@Override
	public void onCreate() {
		super.onCreate();
		//SDKInitializer.initialize(this);//实例化百度mapSDK
		Bmob.initialize(this, "3b874ca04c134fe3f177d4b54d5f63d8");//Bmom初始化
		BP.init(this, "3b874ca04c134fe3f177d4b54d5f63d8");//支付SDK初始化
	}
}
