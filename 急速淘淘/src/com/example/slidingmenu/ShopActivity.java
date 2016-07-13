package com.example.slidingmenu;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShopActivity extends Activity {
	//商店名
	private String shopName;
	//商店图片
	private BmobFile shopPicture;
	//商店介绍
	private String shopIntro;
	//商店信息
	private String shopShow;
	//商店实际位置
	private String shopAddress;
	//商店地理位置
	private BmobGeoPoint shopDistance;
	//派送范围(位置)
	private String sendRange;
	//派送范围(价格区间)
	private Integer sendMonney;
	//好评人数 
	private Integer shopHotCount;
	//好评度
	private Integer shopHot;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
	}
}
