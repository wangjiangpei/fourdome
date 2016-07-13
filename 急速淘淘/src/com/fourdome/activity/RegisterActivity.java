package com.fourdome.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

import com.example.slidingmenu.R;
import com.example.slidingmenu.R.id;
import com.example.slidingmenu.R.layout;
import com.fourdome.bean.User;

public class RegisterActivity extends Activity implements OnClickListener {

	private TextView cancel;
	private Button getcode, register;
	private EditText phone, code, password, username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		setStatus();
		cancel = (TextView) this.findViewById(R.id.act_register_cancel);
		cancel.setOnClickListener(this);
		getcode = (Button) this.findViewById(R.id.act_register_getcode);
		getcode.setOnClickListener(this);
		register = (Button) this.findViewById(R.id.act_register_register);
		register.setOnClickListener(this);
		phone = (EditText) this.findViewById(R.id.act_register_phone);
		code = (EditText) this.findViewById(R.id.act_register_code);
		password = (EditText) this.findViewById(R.id.act_register_password);
		username = (EditText) this.findViewById(R.id.act_register_username);

	}
	/** 设置沉浸式状态栏 */
	@TargetApi(19)
	public void setStatus() {
		Window window = this.getWindow();
		WindowManager.LayoutParams layoutParams = window.getAttributes();
		layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		window.setAttributes(layoutParams);
	}

	@Override
	public void onClick(View v) {

		final String pwd = password.getText().toString().trim();
		final String number = phone.getText().toString().trim();
		String code1 = code.getText().toString().trim();
		final String user1 = username.getText().toString().trim();

		switch (v.getId()) {

		// 点击取消
		case R.id.act_register_cancel:
			RegisterActivity.this.finish();
			break;
		// 点击获取验证码
		case R.id.act_register_getcode:
			// Toast.makeText(Register.this,number+"number",Toast.LENGTH_SHORT).show();
			if (!TextUtils.isEmpty(number)) {
				BmobSMS.requestSMSCode(this, number, "审核通过后的短信内容",
						new RequestSMSCodeListener() {
							@Override
							public void done(Integer smsId, BmobException ex) {
								// TODO Auto-generated method stub
								if (ex == null) {// 验证码发送成功
									Toast.makeText(RegisterActivity.this,
											"验证码发送成功，短信id：" + smsId,
											Toast.LENGTH_SHORT).show();// 用于查询本次短信发送详情
									// 将手机号密码存到bomb

									new MyCount().execute();

								} else {
									Toast.makeText(
											RegisterActivity.this,
											"errorCode = " + ex.getErrorCode()
													+ ",errorMsg = "
													+ ex.getLocalizedMessage(),
											Toast.LENGTH_SHORT).show();
								}
							}
						});
			} else {
				Toast.makeText(RegisterActivity.this, "请输入号码：",
						Toast.LENGTH_SHORT).show();
			}
			break;

		// 点击注册
		case R.id.act_register_register:

			if (pwd.equals("") || number.equals("")) {
				Toast.makeText(RegisterActivity.this, "请输入手机号和密码",
						Toast.LENGTH_SHORT).show();
			} else {

				if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(code1)) {
					BmobSMS.verifySmsCode(RegisterActivity.this, number, code1,
							new VerifySMSCodeListener() {

								@Override
								public void done(BmobException ex) {
									// TODO Auto-generated method stub
									if (ex == null) {// 短信验证码已验证成功
										// Toast.makeText(Register.this,"注册成功",
										// Toast.LENGTH_SHORT).show();
										// 将手机号密码存到bomb
										User user = new User();
										user.setUsername(user1);
										user.setMobilePhoneNumber(number);
										user.setPassword(pwd);
										// 注意：不能用save方法进行注册
										user.signUp(RegisterActivity.this,
												new SaveListener() {
													@Override
													public void onSuccess() {
														Toast.makeText(
																RegisterActivity.this,
																"注册成功",
																Toast.LENGTH_SHORT)
																.show();
														startActivity(new Intent(
																RegisterActivity.this,
																LoginActivity.class));
														RegisterActivity.this
																.finish();
														// 通过BmobUser.getCurrentUser(context)方法获取登录成功后的本地用户信息
													}

													@Override
													public void onFailure(
															int code, String msg) {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																RegisterActivity.this,
																"注册失败",
																Toast.LENGTH_SHORT)
																.show();
													}
												});

									} else {
										Toast.makeText(
												RegisterActivity.this,
												"验证失败：code ="
														+ ex.getErrorCode()
														+ ",msg = "
														+ ex.getLocalizedMessage(),
												Toast.LENGTH_SHORT).show();
									}
								}
							});
				} else {
					Toast.makeText(RegisterActivity.this, "请输入手机号和验证码",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		}
	}

	class MyCount extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			if (values[0] != 0) {
				getcode.setText("剩余" + values[0] + "秒");
			} else {
				getcode.setText("重新获取");
			}
		}

		@Override
		protected Integer doInBackground(Integer... params) {
			for (int i = 60; i >= 0; i--) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i);
			}
			return null;
		}
	}
}
