package com.example.slidingmenu;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShopActivity extends Activity {
	//�̵���
	private String shopName;
	//�̵�ͼƬ
	private BmobFile shopPicture;
	//�̵����
	private String shopIntro;
	//�̵���Ϣ
	private String shopShow;
	//�̵�ʵ��λ��
	private String shopAddress;
	//�̵����λ��
	private BmobGeoPoint shopDistance;
	//���ͷ�Χ(λ��)
	private String sendRange;
	//���ͷ�Χ(�۸�����)
	private Integer sendMonney;
	//�������� 
	private Integer shopHotCount;
	//������
	private Integer shopHot;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
	}
}
