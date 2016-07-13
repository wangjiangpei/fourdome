package com.fourdome.activity;

import com.example.slidingmenu.R;
import com.example.slidingmenu.R.layout;
import com.example.slidingmenu.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/**
 * ฯ๊ว้
 * @author Administrator
 *
 */
public class ShopActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datails);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.datails, menu);
		return true;
	}

}
