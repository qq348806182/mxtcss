package com.xt.mxtcss.activity;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
/**
 * 管理员菜单
 * @author ljj
 *
 */
public class AdminActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.simple_gallery_item);
	}
	
	
	
}
