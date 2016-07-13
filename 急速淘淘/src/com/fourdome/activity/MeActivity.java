package com.fourdome.activity;

import com.example.slidingmenu.R;
import com.example.slidingmenu.R.layout;
import com.example.slidingmenu.R.menu;

import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me);
		setStatus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.me, menu);
		return true;
	}
	/** ÉèÖÃ³Á½þÊ½×´Ì¬À¸ */
	@TargetApi(19)
	public void setStatus() {
		Window window = this.getWindow();
		WindowManager.LayoutParams layoutParams = window.getAttributes();
		layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		window.setAttributes(layoutParams);
	}
}
