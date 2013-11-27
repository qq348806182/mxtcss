package com.xt.mxtcss.activity;

import com.xt.mxtcss.dialog.BaseDialog;
import com.xt.mxtcss.task.LoginTask;
import com.xt.mxtcss.util.StringUtils;

import com.xt.mxtcss.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText edit_enterprisecode = null;
	EditText edit_username = null;
	EditText edit_passwd = null;
	Button button_login = null;
	Button button_reset = null;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		setTitle("矿山联网移动平台");
		
		
		edit_enterprisecode = (EditText) findViewById(R.id.edit_enterprisecode);
		edit_username = (EditText) findViewById(R.id.edit_username);
		edit_passwd = (EditText) findViewById(R.id.edit_passwd);
		
		
		button_login = (Button) findViewById(R.id.button_login);
		button_reset = (Button) findViewById(R.id.button_reset);
		
		
		
		button_login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(StringUtils.isEmpty(edit_enterprisecode.getText().toString())){
					//Toast.makeText(LoginActivity.this, "请输入单位编号", Toast.LENGTH_LONG).show();
					//return;
				}
				if(StringUtils.isEmpty(edit_username.getText().toString())){
					//Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_LONG).show();
					//return;
				}
				if(StringUtils.isEmpty(edit_passwd.getText().toString())){
					//BaseDialog.buildDialog(LoginActivity.this, "请输入单位编号！");
					//Toast.makeText(LoginActivity.this, "请输入用户密码", Toast.LENGTH_LONG).show();
					//return;
				}
				if("".equals(edit_enterprisecode.getText().toString().trim())){
					startActivity(new Intent(LoginActivity.this,CusumerActivity.class));
				}else{
					new LoginTask(LoginActivity.this).execute();
				}
				
				
			}
			
		});
		
		button_reset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				edit_enterprisecode.setText("");
				edit_username.setText("");
				edit_passwd.setText("");
				
			}
		});
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
