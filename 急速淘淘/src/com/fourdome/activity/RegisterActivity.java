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
	/** ���ó���ʽ״̬�� */
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

		// ���ȡ��
		case R.id.act_register_cancel:
			RegisterActivity.this.finish();
			break;
		// �����ȡ��֤��
		case R.id.act_register_getcode:
			// Toast.makeText(Register.this,number+"number",Toast.LENGTH_SHORT).show();
			if (!TextUtils.isEmpty(number)) {
				BmobSMS.requestSMSCode(this, number, "���ͨ����Ķ�������",
						new RequestSMSCodeListener() {
							@Override
							public void done(Integer smsId, BmobException ex) {
								// TODO Auto-generated method stub
								if (ex == null) {// ��֤�뷢�ͳɹ�
									Toast.makeText(RegisterActivity.this,
											"��֤�뷢�ͳɹ�������id��" + smsId,
											Toast.LENGTH_SHORT).show();// ���ڲ�ѯ���ζ��ŷ�������
									// ���ֻ�������浽bomb

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
				Toast.makeText(RegisterActivity.this, "��������룺",
						Toast.LENGTH_SHORT).show();
			}
			break;

		// ���ע��
		case R.id.act_register_register:

			if (pwd.equals("") || number.equals("")) {
				Toast.makeText(RegisterActivity.this, "�������ֻ��ź�����",
						Toast.LENGTH_SHORT).show();
			} else {

				if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(code1)) {
					BmobSMS.verifySmsCode(RegisterActivity.this, number, code1,
							new VerifySMSCodeListener() {

								@Override
								public void done(BmobException ex) {
									// TODO Auto-generated method stub
									if (ex == null) {// ������֤������֤�ɹ�
										// Toast.makeText(Register.this,"ע��ɹ�",
										// Toast.LENGTH_SHORT).show();
										// ���ֻ�������浽bomb
										User user = new User();
										user.setUsername(user1);
										user.setMobilePhoneNumber(number);
										user.setPassword(pwd);
										// ע�⣺������save��������ע��
										user.signUp(RegisterActivity.this,
												new SaveListener() {
													@Override
													public void onSuccess() {
														Toast.makeText(
																RegisterActivity.this,
																"ע��ɹ�",
																Toast.LENGTH_SHORT)
																.show();
														startActivity(new Intent(
																RegisterActivity.this,
																LoginActivity.class));
														RegisterActivity.this
																.finish();
														// ͨ��BmobUser.getCurrentUser(context)������ȡ��¼�ɹ���ı����û���Ϣ
													}

													@Override
													public void onFailure(
															int code, String msg) {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																RegisterActivity.this,
																"ע��ʧ��",
																Toast.LENGTH_SHORT)
																.show();
													}
												});

									} else {
										Toast.makeText(
												RegisterActivity.this,
												"��֤ʧ�ܣ�code ="
														+ ex.getErrorCode()
														+ ",msg = "
														+ ex.getLocalizedMessage(),
												Toast.LENGTH_SHORT).show();
									}
								}
							});
				} else {
					Toast.makeText(RegisterActivity.this, "�������ֻ��ź���֤��",
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
				getcode.setText("ʣ��" + values[0] + "��");
			} else {
				getcode.setText("���»�ȡ");
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
