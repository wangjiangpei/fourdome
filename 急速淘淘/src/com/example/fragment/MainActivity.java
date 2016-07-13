package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.bmob.v3.datatype.BmobFile;

import com.example.mefragment.HomeFragmeng;
import com.example.mefragment.MeFragment;
import com.example.mefragment.MessageFragment;
import com.example.mefragment.MoreFragment;
import com.example.slidingmenu.R;
import com.example.view.slidingmenu.BaseSlidingFragmentActivity;
import com.example.view.slidingmenu.SlidingMenu;
import com.example.view.slidingmenu.SlidingMenu.OnOpenedListener;
import com.fourdome.activity.BaseApplication;
import com.fourdome.utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends BaseSlidingFragmentActivity implements
		OnClickListener {
	//viewprage�ؼ�
	@SuppressWarnings("unused")
	private LinearLayout lin_home, lin_message, lin_me, lin_more, lin_menu;
	private ImageView img_home, img_message, img_me, img_more;
	private TextView text_home, text_message, text_me, text_more;
	private ViewPager viewprage;
	private FragmentManager fm;
	//-------------------
	private List<Fragment> fragment_list;
	/** ������ */
	private MainLeftFragment mFrag = new MainLeftFragment();
	/** �Ҳ���� */
	private MainRightFragment mFragright = new MainRightFragment();
	/** SlidingMenu�ؼ� */
	private SlidingMenu mSlidingMenu;

	private ImageView showLeft;
	private View showRight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BaseApplication.main=this;
		
		chushihua();
		setStatus();
		initSlidingMenu();
		initView();
		Log.i("show", "mainactivityresume");
		if (BaseApplication.user!=null&&BaseApplication.user.getMyHead() != null) {
			loadImage(showLeft, BaseApplication.user.getMyHead());
		}

	}
	public void imageLoader() {
		loadImage(showLeft, BaseApplication.user.getMyHead());
	}
	protected void onResume() {
		super.onResume();
		Log.i("show", "mainactivityresume");
		if (BaseApplication.user!=null&&BaseApplication.user.getMyHead() != null) {
			loadImage(showLeft, BaseApplication.user.getMyHead());
		}
	}
	
	//ͼƬ����
	public void loadImage(ImageView image, BmobFile bmobFile) {
		ImageLoader loader = ImageLoaderUtils.getInstance(this);
		DisplayImageOptions options = ImageLoaderUtils.getOpt();
		BmobFile bmobfile = bmobFile;
		loader.displayImage(
				bmobfile.getFileUrl(this), image,
				options);
	}
	private void chushihua() {
		// ������ViewPrage
		fragment_list = new ArrayList<Fragment>();
		viewprage = (ViewPager) findViewById(R.id.mian_vp);
		lin_home = (LinearLayout) findViewById(R.id.main_lin_home);
		lin_message = (LinearLayout) findViewById(R.id.main_lin_message);
		lin_me = (LinearLayout) findViewById(R.id.main_lin_me);
		lin_more = (LinearLayout) findViewById(R.id.main_lin_more);
		img_home = (ImageView) findViewById(R.id.main_img_home);
		img_message = (ImageView) findViewById(R.id.main_img_message);
		img_me = (ImageView) findViewById(R.id.main_img_me);
		img_more = (ImageView) findViewById(R.id.main_img_more);
		text_home = (TextView) findViewById(R.id.main_text_home);
		text_message = (TextView) findViewById(R.id.main_text_message);
		text_me = (TextView) findViewById(R.id.main_text_me);
		text_more = (TextView) findViewById(R.id.main_text_more);
		fm = getSupportFragmentManager();
		fragment_list.add(new HomeFragmeng());
		fragment_list.add(new MessageFragment());
		fragment_list.add(new MeFragment());
		fragment_list.add(new MoreFragment());
	}

	private void initView() {

		showLeft = (ImageView) findViewById(R.id.showLeft);
		showRight = findViewById(R.id.showRight);

		showLeft.setOnClickListener(this);
		showRight.setOnClickListener(this);
	}

	/** ��ʼ�������˵� */
	private void initSlidingMenu() {
		// ����viewPrage�¼�-----------
		viewprage.setAdapter(new MyAdapter(fm));
		lin_home.setOnClickListener(linlistener);
		lin_message.setOnClickListener(linlistener);
		lin_me.setOnClickListener(linlistener);
		lin_more.setOnClickListener(linlistener);
		viewprage.setOnPageChangeListener(pragelistener);
		// -------------------------------------

		// ----------�໬�˵�
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int mScreenWidth = dm.widthPixels;// ��ȡ��Ļ�ֱ��ʿ��
		setBehindContentView(R.layout.hsy_main_left_layout);// ������˵�
		FragmentTransaction mFragementTransaction = getSupportFragmentManager()
				.beginTransaction();
		mFragementTransaction.replace(R.id.main_left_fragment, mFrag);
		mFragementTransaction.commit();
		// customize the SlidingMenu
		mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// �������󻬻����һ����������Ҷ����Ի������������Ҷ����Ի�
		mSlidingMenu.setShadowWidth(1);// ������Ӱ���
		mSlidingMenu.setBehindOffset(mScreenWidth / 3);// ���ò˵����
		mSlidingMenu.setFadeDegree(0.35f);// ���õ��뵭���ı���
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		mSlidingMenu.setShadowDrawable(R.drawable.hsy_slidingmenu_shadow);// ������˵���ӰͼƬ
		mSlidingMenu.setSecondaryShadowDrawable(R.drawable.hsy_right_shadow);// �����Ҳ˵���ӰͼƬ
		mSlidingMenu.setFadeEnabled(true);// ���û���ʱ�˵����Ƿ��뵭��
		mSlidingMenu.setBehindScrollScale(0.333f);// ���û���ʱ��קЧ��
		mSlidingMenu.setSecondaryMenu(R.layout.hsy_main_right_layout);
		mSlidingMenu.setOnOpenedListener(new OnOpenedListener() {
			public void onOpened(int currentItem) {
				Log.e("show",currentItem+"---");
				if (currentItem == SlidingMenu.LeftMenu) {// ��һ������
					Log.e("show", "���ı�����");
					
				} else if (currentItem == SlidingMenu.RightMenu) {// �ڶ�������
					Log.e("show", "�Ҳ�ı�����");
				}
			}
		});
		FragmentTransaction mFragementTransactionright = getSupportFragmentManager()
				.beginTransaction();
		mFragementTransactionright
				.replace(R.id.main_right_fragment, mFragright);
		mFragementTransactionright.commit();
	}

	/** ���ó���ʽ״̬�� */
	@TargetApi(19)
	public void setStatus() {
		Window window = this.getWindow();
		WindowManager.LayoutParams layoutParams = window.getAttributes();
		layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		window.setAttributes(layoutParams);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.showLeft:
			mSlidingMenu.showMenu(true);
			break;
		case R.id.showRight:
			mSlidingMenu.showSecondaryMenu(true);
			break;

		default:
			break;
		}
	}

	// ----------------------------------------------------

	// �ײ��˵�����¼�------------------------------------
	private OnClickListener linlistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.main_lin_home:
				operateMenu(0);
				break;
			case R.id.main_lin_message:
				operateMenu(1);
				break;
			case R.id.main_lin_me:
				operateMenu(2);
				break;
			case R.id.main_lin_more:
				operateMenu(3);
				break;
			}
		}
	};

	// ��Ӧmenu״̬�仯
	@SuppressLint("ResourceAsColor")
	private void operateMenu(int count) {
		ImageView imgs[] = { img_home, img_message, img_me, img_more };
		TextView texts[] = { text_home, text_message, text_me, text_more };
		int imgidsm[] = { R.drawable.d1, R.drawable.d2, R.drawable.d3,
				R.drawable.d4 };
		int imgidsp[] = { R.drawable.g1, R.drawable.g2, R.drawable.g3,
				R.drawable.g4 };
		viewprage.setCurrentItem(count);
		for (int i = 0; i < 4; i++) {
			if (count == i) {
				imgs[i].setImageResource(imgidsp[i]);
				texts[i].setTextColor(this.getResources().getColor(
						R.color.press));
			} else {
				imgs[i].setImageResource(imgidsm[i]);
				texts[i].setTextColor(this.getResources().getColor(
						R.color.white));
			}
		}
	}

	// viewpage�����¼�
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

		}
	};

	// viewpage������
	class MyAdapter extends FragmentStatePagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragment_list.get(arg0);
		}

		@Override
		public int getCount() {
			return fragment_list.size();
		}

	}
}
