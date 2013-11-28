package com.xt.mxtcss.service;

import com.xt.mxtcss.util.GpsManager;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

public class GpsService extends Service {
	private boolean threadDisable = false;
	private GpsManager gps = null;
	private static final String TAG = GpsService.class.getSimpleName();
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		gps = new GpsManager(GpsService.this);
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						while(!threadDisable){
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
							}
							Location location= gps.getLocation();
							Log.i(TAG, "纬度："+"---------------" + location);
							if(location != null){
							  Intent intent=new Intent();
						      intent.putExtra("lat", location==null?"":location.getLatitude()+""); 
						      intent.putExtra("lon", location==null?"":location.getLongitude()+""); 
						      intent.setAction("com.xt.mxtcss.service.GpsService"); 
						      Log.i(TAG, "纬度："+location.getLatitude());
						      Log.i(TAG, "经度："+location.getLongitude());
						      sendBroadcast(intent);
							}
							
						}
					}
		}).start();
		
	}
	
	
	@Override
	public void onDestroy() {
		threadDisable = true;
		if(gps != null){
			gps.closeLocation();
		}
		super.onDestroy();
	}
	
	
	

}
