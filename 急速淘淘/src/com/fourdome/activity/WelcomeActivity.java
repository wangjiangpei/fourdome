package com.fourdome.activity;

import com.example.fragment.MainActivity;
import com.example.slidingmenu.R;
import com.example.slidingmenu.R.layout;
import com.example.slidingmenu.R.menu;

import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class WelcomeActivity extends Activity {

	 private Button login;
	    private Button register;
	    private LinearLayout mRelativeLayout;
	    private Animation mAlphaAnimation;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_welcome);
	        setStatus();
	        initViews();
	        initDatas();
	        initOpers();
	    }

	    /** ���ó���ʽ״̬�� */
	    @TargetApi(19)
	    public void setStatus() {
	        Window window = this.getWindow();
	        WindowManager.LayoutParams layoutParams = window.getAttributes();
	        layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
	        window.setAttributes(layoutParams);
	    }

	    public void initViews() {
	        login= (Button) findViewById(R.id.act_welcome_login);
	        register= (Button) findViewById(R.id.act_welcome_register);
	        mRelativeLayout= (LinearLayout) findViewById(R.id.act_welcome);
	        //������Դ
	        mAlphaAnimation= AnimationUtils.loadAnimation(this,R.anim.anim_anim);
	        mRelativeLayout.setAnimation(mAlphaAnimation);

	    }

	    public void initDatas() {

	    }

	    public void initOpers() {
	        //��������
	        mAlphaAnimation.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationStart(Animation animation) {

	            }

	            @Override
	            public void onAnimationEnd(Animation animation) {
	                //����ɼ�
	                login.setVisibility(View.VISIBLE);
	                register.setVisibility(View.VISIBLE);
	            }

	            @Override
	            public void onAnimationRepeat(Animation animation) {

	            }
	        });

	        //��¼��ť

	        login.getBackground().setAlpha(100);//0~255͸����ֵ
	        login.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	Intent intent = new Intent();
	            	 intent.setClass(WelcomeActivity.this, LoginActivity.class);
	            	 intent.putExtra("type", "main");
	                startActivity(intent);
	                //startActivity(new Intent(Welcome.this, MainActivity.class));
	            }
	        });
	        //ע�ᰴť

	        register.getBackground().setAlpha(100);//0~255͸����ֵ
	        register.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
	            }
	        });
	    }

}
