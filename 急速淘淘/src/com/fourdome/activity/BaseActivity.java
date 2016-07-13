package com.fourdome.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 	
 * @author Administrator
 *
 */
public class BaseActivity extends Activity {



	public Button getButton(int id) {
		return (Button) findViewById(id);
	}

	public EditText getEditText(int id) {
		return (EditText) findViewById(id);
	}

	public TextView getTextView(int id) {
		return (TextView) findViewById(id);
	}

	public ImageView getImageView(int id) {
		return (ImageView) findViewById(id);
	}

	public ListView getListView(int id) {
		return (ListView) findViewById(id);
	}

	public void toasts(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
				.show();
	}

	public void toastl(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
	}

	public void syso(String text) {
		System.out.println(text);

	}

	public void i(String text) {
		Log.i("show", text);
	}
	/**
	 * 
	 * @param title	   	标题
	 * @param message	内容
	 * @param yesListener	确定按钮监听
	 * @param noListener	取消按钮监听  为空则不设置取消按钮
	 */	
	public void dialog(String title, String message, OnClickListener yesListener,OnClickListener noListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title)
		.setMessage(message)
		.setCancelable(false);
		builder.setNeutralButton("确定", yesListener);
		if (noListener!=null) {
			builder.setNegativeButton("取消", noListener);
		}

		builder.show();
	}
	
}
