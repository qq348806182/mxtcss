package com.xt.mxtcss.util;

import org.json.JSONObject;

import android.location.Location;
import android.location.LocationManager;

public class GpsUtils {

	
	public static boolean isGpsEnabled(LocationManager locationManager){
		boolean isOpenGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		boolean isOpenNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		if(isOpenGps || isOpenNetwork){
			return true;
		}
		return false;
	}
	
	
    /**
     * 根据基站信息获取经纬度 
     * 
     * 原理向http://www.google.com/loc/json发送http的post请求，根据google返回的结果获取经纬度
     * 
     * @param cellIds
     * @return
     * @throws Exception 
     */
    public static Location callGear(Context ctx, ArrayList<CellInfo> cellIds) throws Exception {
		 String result="";
		 JSONObject data=null;
		 if (cellIds == null||cellIds.size()==0) {
		  DialogUtils.alert(ctx, "cell request param null");
		      return null;
		     };
		      
		  try {
		   result = NetUtils.getResponseResult(ctx, "http://www.google.com/loc/json", cellIds);
		    
		   if(result.length() <= 1)
		    return null;
		   data = new JSONObject(result);
		   data = (JSONObject) data.get("location");
		 
		   Location loc = new Location(LocationManager.NETWORK_PROVIDER);
		   loc.setLatitude((Double) data.get("latitude"));
		   loc.setLongitude((Double) data.get("longitude"));
		   loc.setAccuracy(Float.parseFloat(data.get("accuracy").toString()));
		   loc.setTime(UtilTool.getUTCTime());
		   return loc;
		  } catch (JSONException e) {
		   return null;
		  } catch (UnsupportedEncodingException e) {
		   e.printStackTrace();
		  } catch (ClientProtocolException e) {
		   e.printStackTrace();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  return null;
		 }
	
	
	
	
	
}
