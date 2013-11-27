package com.xt.mxtcss;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xt.mxtcss.db.DataBaseHelper;

import android.app.Application;
import android.content.SharedPreferences;

public class MxtcssApplication extends Application {
	
	public DataBaseHelper dataBaseHelper;
	
	@Override
	public void onCreate() {
		super.onCreate();
		//dataBaseHelper = new DataBaseHelper(this.getApplicationContext(),"xtcss.db",null,1);
		
	}
	

	public SharedPreferences getUserLoginInfo(){
		SharedPreferences sharedPreferences = this.getSharedPreferences("mxtcss", MODE_PRIVATE);
		return sharedPreferences;
	}
	
	
	public void setUserLoginInfo(String enterprisecode,String username){
		SharedPreferences sharedPreferences = this.getSharedPreferences("mxtcss", MODE_PRIVATE);
		sharedPreferences.edit().putString("enterprisecode", username)
		.putString("username",username)
		.putString("loginTime", new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date())).commit();
	}
}
