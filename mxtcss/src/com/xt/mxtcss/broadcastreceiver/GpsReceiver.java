package com.xt.mxtcss.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

public class GpsReceiver extends BroadcastReceiver {
	Context context;
	@Override
	public void onReceive(Context arg0, Intent intent) {
	   Bundle bundle=intent.getExtras();      
	   String lon=bundle.getString("lon");    
	   String lat=bundle.getString("lat");
	   
	   //arg0.get
	   if(lon!=null&&!"".equals(lon)&&lat!=null&&!"".equals(lat)){
		  // double distance=getDistance(Double.parseDouble(lat), 
		   //Double.parseDouble(lon), homeLat, homeLon);
		  // context..setText("目前经纬度\n经度："+lon+"\n纬度："+lat+"\n离宿舍距离："+java.lang.Math.abs(distance));
	   }else{
		   //editText.setText("目前经纬度\n经度："+lon+"\n纬度："+lat);
	   }
	   
	}
	
	
	public GpsReceiver(Context cxt){
		context = cxt;
		
	}
	
	 /**
	  * 把经纬度换算成距离
	  * 
	  * @param lat1 开始纬度
	  * @param lon1 开始经度
	  * @param lat2 结束纬度
	  * @param lon2 结束经度
	  * @return
	  */
	 private double getDistance(double lat1, double lon1, double lat2, double lon2) {
	  float[] results = new float[1];
	  Location.distanceBetween(lat1, lon1, lat2, lon2, results);
	  return results[0];
	 }  
}
