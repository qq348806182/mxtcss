package com.xt.mxtcss.activity;

import com.xt.mxtcss.R;
import com.xt.mxtcss.adapter.ImageAdater;
import com.xt.mxtcss.zxing.CaptureActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class CusumerActivity extends Activity {

	GridView gridView ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cusumer);
		gridView = (GridView) findViewById(R.id.MainActivityGrid);
		ImageAdater adater = new ImageAdater(this);
		gridView.setAdapter(adater);
		gridView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(arg3 == 0){
					startActivity(new Intent(CusumerActivity.this,PhoneInfoActivity.class));
				}
				if(arg3 == 1){
					startActivity(new Intent(CusumerActivity.this,CameraActivity.class));
				}
				if(arg3 == 4){
					startActivity(new Intent(CusumerActivity.this,CaptureActivity.class));
				}
				if(arg3 == 2){
					startActivity(new Intent(Settings.ACTION_SETTINGS));
				}
				
			}
			
		});
	}
	
	
	
}
