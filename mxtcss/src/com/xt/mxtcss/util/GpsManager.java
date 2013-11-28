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
		//provider״̬�ı�ʱ�����
		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			Log.i(TAG, "onStatusChanged");
		}
		//provider ������ʱ�����
		@Override
		public void onProviderEnabled(String arg0) {
			Location l = locationManager.getLastKnownLocation(arg0);
			Log.i(TAG, "onProviderEnabled---" + l);
			if(l != null){
				location = l ;
			}
		}
		//provider ���ر�ʱ�����
		@Override
		public void onProviderDisabled(String arg0) {
			location = null;
		}
		//λ�÷����ı��ǵ���
		@Override
		public void onLocationChanged(Location arg0) {
			Log.i(TAG, "onLocationChanged---" + arg0);
			if(arg0 != null){
				location = arg0;
			}
		}
	};
	
	// ��ȡLocation Provider
	 private String getProvider() {
	  // ����λ�ò�ѯ����
	  Criteria criteria = new Criteria();
	  // ��ѯ���ȣ���
	  criteria.setAccuracy(Criteria.ACCURACY_FINE);
	  // �Ƿ��ѯ��������
	  criteria.setAltitudeRequired(false);
	  // �Ƿ��ѯ��λ�� : ��
	  criteria.setBearingRequired(false);
	  // �Ƿ������ѣ���
	//  criteria.setCostAllowed(true);
	  // ����Ҫ�󣺵�
	  criteria.setPowerRequirement(Criteria.POWER_LOW);
	  // ��������ʵķ���������provider����2������Ϊtrue˵�� , ���ֻ��һ��provider����Ч��,�򷵻ص�ǰprovider
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
	//ͨ����վ��ȡλ��
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
