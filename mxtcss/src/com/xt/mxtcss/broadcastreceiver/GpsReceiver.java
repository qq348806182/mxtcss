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
		  // context..setText("Ŀǰ��γ��\n���ȣ�"+lon+"\nγ�ȣ�"+lat+"\n��������룺"+java.lang.Math.abs(distance));
	   }else{
		   //editText.setText("Ŀǰ��γ��\n���ȣ�"+lon+"\nγ�ȣ�"+lat);
	   }
	   
	}
	
	
	public GpsReceiver(Context cxt){
		context = cxt;
		
	}
	
	 /**
	  * �Ѿ�γ�Ȼ���ɾ���
	  * 
	  * @param lat1 ��ʼγ��
	  * @param lon1 ��ʼ����
	  * @param lat2 ����γ��
	  * @param lon2 ��������
	  * @return
	  */
	 private double getDistance(double lat1, double lon1, double lat2, double lon2) {
	  float[] results = new float[1];
	  Location.distanceBetween(lat1, lon1, lat2, lon2, results);
	  return results[0];
	 }  
}
