package com.xt.mxtcss.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GpsManager {
	LocationManager locationManager = null;
	private static final String TAG = GpsManager.class.getSimpleName();
	Location location = null;
	Context context;
	

	public GpsManager(Context ctx){
		context = ctx;
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, locationListener);
		//locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER);
	}
	
	
	private LocationListener locationListener = new LocationListener() {
		//provider状态改变时候调用
		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			Log.i(TAG, "onStatusChanged");
		}
		//provider 被开启时候调用
		@Override
		public void onProviderEnabled(String arg0) {
			Location l = locationManager.getLastKnownLocation(arg0);
			Log.i(TAG, "onProviderEnabled---" + l);
			if(l != null){
				location = l ;
			}
		}
		//provider 被关闭时候调用
		@Override
		public void onProviderDisabled(String arg0) {
			location = null;
		}
		//位置发生改变是调用
		@Override
		public void onLocationChanged(Location arg0) {
			Log.i(TAG, "onLocationChanged---" + arg0);
			if(arg0 != null){
				location = arg0;
			}
		}
	};
	
	// 获取Location Provider
	 private String getProvider() {
	  // 构建位置查询条件
	  Criteria criteria = new Criteria();
	  // 查询精度：高
	  criteria.setAccuracy(Criteria.ACCURACY_FINE);
	  // 是否查询海拨：否
	  criteria.setAltitudeRequired(false);
	  // 是否查询方位角 : 否
	  criteria.setBearingRequired(false);
	  // 是否允许付费：是
	//  criteria.setCostAllowed(true);
	  // 电量要求：低
	  criteria.setPowerRequirement(Criteria.POWER_LOW);
	  // 返回最合适的符合条件的provider，第2个参数为true说明 , 如果只有一个provider是有效的,则返回当前provider
	  return locationManager.getBestProvider(criteria, true);
	 }

	
	

	public Location getLocation() {
		if(location == null){
			locationManager.requestLocationUpdates("gps", 60000, 1, locationListener);
		}
		return location;
	}
	
	
	public void closeLocation(){
		if(locationManager != null){
			if(locationListener != null){
				locationManager.removeUpdates(locationListener);
				locationListener = null;
			}
			locationManager = null;
		}
	}
	
	
	
	
	
	
	
	//to-do  
	//通过基站获取位置
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
