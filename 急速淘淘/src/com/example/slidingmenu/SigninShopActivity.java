package com.example.slidingmenu;

import java.io.File;

import com.fourdome.activity.BaseActivity;
import com.fourdome.activity.IActivitv;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 
 * 
 * 
 * 注册商家店铺
 * 
 * @author Administrator
 *
 */
public class SigninShopActivity extends BaseActivity implements IActivitv{
	
	private ImageView shopPicture;
	private EditText shopNams,shopIntro,shopSendRange,shopShow;
	private TextView shopDistance;
	private OnClickListener pictureListener;
	private OnClickListener distanceListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin_shop);
		initViews();
		initDates();
		initOpers();
	}
	@Override
	public void initViews() {
		//商店图片
		shopPicture=getImageView(R.id.signinshop_picture);
		//商店名
		shopNams=getEditText(R.id.signinshop_name);
		//商店简介
		shopIntro=getEditText(R.id.signinshop_intro);
		//商店派送范围
		shopSendRange=getEditText(R.id.signinshop_send);
		//商店描述
		shopShow=getEditText(R.id.signinshop_show);
		//商店位置
		shopDistance=getTextView(R.id.signinshop_getlocation);
	}
	@Override
	public void initOpers() {
		shopPicture.setOnClickListener(pictureListener);
		shopDistance.setOnClickListener(distanceListener);
	}
	@Override
	public void initDates() {
		//商店图片点击事件
		pictureListener=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
			}
		};
		//商店位置点击事件
		distanceListener=new OnClickListener() {
			private android.content.DialogInterface.OnClickListener noListener;

			@Override
			public void onClick(View arg0) {
				String name=shopNams.getText().toString().trim();
				if (name.equals("")) {
					dialog("提示", "请先输入名字以方便在地图上生成图标",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
						}
					} ,null);
				}else{
					//定位
					//SigninShopActivity.this.startActivity(new Intent(SigninShopActivity.this));
				}
			}
		};
	}
}
