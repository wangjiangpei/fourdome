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
 * ע���̼ҵ���
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
		//�̵�ͼƬ
		shopPicture=getImageView(R.id.signinshop_picture);
		//�̵���
		shopNams=getEditText(R.id.signinshop_name);
		//�̵���
		shopIntro=getEditText(R.id.signinshop_intro);
		//�̵����ͷ�Χ
		shopSendRange=getEditText(R.id.signinshop_send);
		//�̵�����
		shopShow=getEditText(R.id.signinshop_show);
		//�̵�λ��
		shopDistance=getTextView(R.id.signinshop_getlocation);
	}
	@Override
	public void initOpers() {
		shopPicture.setOnClickListener(pictureListener);
		shopDistance.setOnClickListener(distanceListener);
	}
	@Override
	public void initDates() {
		//�̵�ͼƬ����¼�
		pictureListener=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
			}
		};
		//�̵�λ�õ���¼�
		distanceListener=new OnClickListener() {
			private android.content.DialogInterface.OnClickListener noListener;

			@Override
			public void onClick(View arg0) {
				String name=shopNams.getText().toString().trim();
				if (name.equals("")) {
					dialog("��ʾ", "�������������Է����ڵ�ͼ������ͼ��",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
						}
					} ,null);
				}else{
					//��λ
					//SigninShopActivity.this.startActivity(new Intent(SigninShopActivity.this));
				}
			}
		};
	}
}
