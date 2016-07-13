package com.fourdome.activity;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;

import com.example.fragment.MainActivity;
import com.example.slidingmenu.BaseApplication;
import com.example.slidingmenu.R;
import com.example.slidingmenu.R.id;
import com.example.slidingmenu.R.layout;
import com.fourdome.bean.User;

public class LoginActivity extends Activity implements OnClickListener {
	private TextView cancel, register;
	private EditText phone, password;
	private Button login;
	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setStatus();
		initViews();
		initDates();
		initOpens();
	}
	/** 设置沉浸式状态栏 */
	@TargetApi(19)
	public void setStatus() {
		Window window = this.getWindow();
		WindowManager.LayoutParams layoutParams = window.getAttributes();
		layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		window.setAttributes(layoutParams);
	}

	public void initViews() {
		type = getIntent().getStringExtra("type");
		cancel = (TextView) findViewById(R.id.act_login_cancel);
		cancel.setOnClickListener(this);
		register = (TextView) findViewById(R.id.act_login_register);
		register.setOnClickListener(this);
		phone = (EditText) findViewById(R.id.act_login_phone);
		password = (EditText) findViewById(R.id.act_login_password);
		login = (Button) findViewById(R.id.act_login_login);
		phone.setText("13522909414");
		password.setText("qqq");
		login.setOnClickListener(this);
	}

	public void initDates() {

	}

	public void initOpens() {

	}

	@Override
	public void onClick(View v) {
		final String number = phone.getText().toString().trim();
		String pwd = password.getText().toString().trim();

		switch (v.getId()) {
		// 点击取消
		case R.id.act_login_cancel:
			LoginActivity.this.finish();
			break;
		// 点击注册
		case R.id.act_login_register:
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			LoginActivity.this.finish();
			break;
		// 点击登陆
		case R.id.act_login_login:

			BmobUser.loginByAccount(LoginActivity.this, number, pwd,
					new LogInListener<User>() {

						@Override
						public void done(final User user, BmobException e) {
							if (user != null) {
								// 获取用户列表
								BmobQuery<User> query = new BmobQuery<User>();
								query.addWhereEqualTo("mobilePhoneNumber",
										number);
								query.findObjects(LoginActivity.this,
										new FindListener<User>() {
											@Override
											public void onSuccess(
													List<User> object) {
												// Toast.makeText(Login.this,"查询用户成功",Toast.LENGTH_SHORT).show();
												BaseApplication.user=user;
											}

											@Override
											public void onError(int code,
													String msg) {
												// TODO Auto-generated method
												// stub
												// toast("查询用户失败："+msg);
											}
										});

								Toast.makeText(LoginActivity.this, "登录成功",
										Toast.LENGTH_SHORT).show();
								typeControl(type);
								LoginActivity.this.finish();
							} else {
								Toast.makeText(LoginActivity.this, "登录失败",
										Toast.LENGTH_SHORT).show();
							}
						}
					});

			break;

		}
	}

	// 根据类型选择跳转
	public void typeControl(String type) {
		if (type.equals("main")) {
			skip(MainActivity.class);
		} else if (type.equals("buy")) {

		}
	}

	public void skip(Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		this.startActivity(intent);
	}
}
