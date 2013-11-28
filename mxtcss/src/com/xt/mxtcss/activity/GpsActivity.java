package com.xt.mxtcss.activity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xt.mxtcss.R;
import com.xt.mxtcss.broadcastreceiver.GpsReceiver;
import com.xt.mxtcss.service.GpsService;
import com.xt.mxtcss.util.DialogUtils;
import com.xt.mxtcss.util.GpsUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * GPS
 * @author ljj
 *
 */
public class GpsActivity extends Activity {
	private static final String TAG = GpsActivity.class.getSimpleName();
	private GpsReceiver receiver;
	private TextView textViewlat;
	private TextView textViewlon;
	private EditText editTextlat;
	private EditText editTextlon;
	private EditText editTexterror;
	private LocationManager locationManager;
	private String provider;
	private Location location;
	private Address address;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //�ж�GPS�Ƿ����
		  setContentView(R.layout.activity_gps_layout);
		  
		  textViewlat = (TextView) findViewById(R.id.textView_lat);
		  textViewlon = (TextView) findViewById(R.id.textView_lon);
		  editTextlat = (EditText) findViewById(R.id.editText_lat);
		  editTextlon = (EditText) findViewById(R.id.editText_lon);
		  editTexterror = (EditText) findViewById(R.id.error );
		  Log.i(TAG, GpsUtils.isGpsEnabled((LocationManager)getSystemService(Context.LOCATION_SERVICE))+"");
		  if(!GpsUtils.isGpsEnabled((LocationManager)getSystemService(Context.LOCATION_SERVICE))){
			   Toast.makeText(this, "GSP��ǰ�ѽ��ã���������ϵͳ������Ļ������", Toast.LENGTH_LONG).show();
			   Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);  
			   startActivity(callGPSSettingIntent);
			   return;
		  }   
		 
		  locationManager =(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		 // getProvider();
		  openGPS();
		  getGpsBySM();
		// ��ȡλ��
	     // location = locationManager.getLastKnownLocation(getProvider());
	      
	    
	    //  locationManager.requestLocationUpdates(getProvider(), 2000, 10,locationListener);
		//��������
		//  startService(new Intent(this, GpsService.class));
		   
		  //ע��㲥
		//  receiver=new GpsReceiver(this);
		 // IntentFilter filter=new IntentFilter();
		 // filter.addAction("com.xt.mxtcss.activity.GpsService");
		 // registerReceiver(receiver, filter);
	     // updateWithNewLocation(location);
		 }
	
	@Override
	protected void onDestroy() {
		//unregisterReceiver(receiver);
		//stopService(new Intent(this,GpsService.class));
		super.onDestroy();
	}
	
	private void openGPS() {       
        if (locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
        ||locationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER)
        ) {
           Toast.makeText(this, "λ��Դ�����ã�", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "λ��Դδ���ã�", Toast.LENGTH_SHORT).show();
        // ת��GPS���ý���
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
       startActivityForResult(intent,0);
    }
	
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
		 
		 
		 private final LocationListener locationListener = new LocationListener(){
			    // λ�÷����ı�����
			        public void onLocationChanged(Location location) {
			        	DialogUtils.alert(GpsActivity.this, "", "λ�÷����ı䣡");
			        	updateWithNewLocation(location);
			        }
			        // provider���û��رպ����
			        public void onProviderDisabled(String provider){
			        	//updateWithNewLocation(null);
			        }
			        // provider���û����������
			        public void onProviderEnabled(String provider){ }
			        // provider״̬�仯ʱ����
			        public void onStatusChanged(String provider, int status,
			        Bundle extras){ }
			    };
			    
	   
	    private List<Address> getAddressbyGeoPoint(Location location) {
	    	List<Address> result = null;
	    	// �Ƚ�Locationת��ΪGeoPoint
	    	// GeoPoint gp=getGeoByLocation(location);
	    	try {
		    	if (location != null) {
		    	// ��ȡGeocoder��ͨ��Geocoder�Ϳ����õ���ַ��Ϣ
		    		Geocoder gc = new Geocoder(this, Locale.getDefault());
		    		result= gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
		    		//editTextlat.setText(result.toString());
		    	}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	    	return result;
	    }
	    private Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 1){
					editTexterror.setText("��λʧ�ܣ��������¶�λ..." +msg.obj +"��");
				}
				super.handleMessage(msg);
			}
	    };
	    
	    private void updateWithNewLocation( Location c){
	    	if(location == null){
	    		new Thread(new Runnable() {
					@Override
					public void run() {
						int k = 0;
						while(location == null){
							if(location == null){
								 locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10,locationListener);
								 location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							} 
							if(location == null) {
								locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10,locationListener);
								location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
							}
						   Message  msg = Message.obtain();;
		                   msg.what = 1;
		                   msg.obj = k++;
		                   handler.sendMessage(msg);
						  // location = locationManager.getLastKnownLocation(getProvider());
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						}
					}
				}).start();
	    	}else{
	    		editTextlat.setText(location.getLatitude()+"");
		    	editTextlon.setText(location.getLongitude()+"");
	    	}
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    private int mcc;  
		  
	    private int mnc;  
	  
	    private int lac;  
	  
	    private int cid;  
	    
	    
	    private void getGpsBySM(){
    	   TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);  
    	   
           // ����ֵMCC + MNC  
               String operator = mTelephonyManager.getNetworkOperator();  
               mcc = Integer.parseInt(operator.substring(0, 3));  
               mnc = Integer.parseInt(operator.substring(3));  
 
               // �й��ƶ����й���ͨ��ȡLAC��CID�ķ�ʽ  
               GsmCellLocation location = (GsmCellLocation) mTelephonyManager.getCellLocation();  
               lac = location.getLac();  
               cid = location.getCid();  
 
               Log.i(TAG, "MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac + "\t CID = " + cid);  
 
               new Thread() {  
                   @Override  
                   public void run() {  
                       try {  
                           String json = getJsonCellPos(mcc, mnc, lac, cid);  
                           Log.i(TAG, "request = " + json); 
                           System.out.println(json);
	                       String url = "http://www.minigps.net/minigps/map/google/location";  
	                       String result = httpPost(url, json);  
	                       editTexterror.setText(result);
	                       Log.i(TAG, "result = " + result);  
                   } catch (Exception e) {  
                       // TODO Auto-generated catch block  
                       e.printStackTrace();  
                   }  
               }  
           }.start();  
	    	
	    }
	    
	   // private static final String TAG = "GSMCellLocationActivity";  
	    
	    
	    
	    
	    /** 
	     * ���õ�����������API���ݻ�վ��Ϣ���һ�վ�ľ�γ��ֵ����ַ��Ϣ 
	     */  
	    public String httpPost(String url, String jsonCellPos) throws IOException{  
	        byte[] data = jsonCellPos.toString().getBytes();  
	        URL realUrl = new URL(url);  
	        HttpURLConnection httpURLConnection = (HttpURLConnection) realUrl.openConnection();  
	        httpURLConnection.setConnectTimeout(6 * 1000);  
	        httpURLConnection.setDoOutput(true);  
	        httpURLConnection.setDoInput(true);  
	        httpURLConnection.setUseCaches(false);  
	        httpURLConnection.setRequestMethod("POST");  
	        httpURLConnection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");  
	        httpURLConnection.setRequestProperty("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");  
	        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");  
	        httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");  
	        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");  
	        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));  
	        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");  
	  
	        httpURLConnection.setRequestProperty("Host", "www.minigps.net");  
	        httpURLConnection.setRequestProperty("Referer", "http://www.minigps.net/map.html");  
	        httpURLConnection.setRequestProperty("User-Agent",  
	                "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.94 Safari/537.4X-Requested-With:XMLHttpRequest");  
	  
	        httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");  
	        httpURLConnection.setRequestProperty("Host", "www.minigps.net");  
	  
	        DataOutputStream outStream = new DataOutputStream(httpURLConnection.getOutputStream());  
	        outStream.write(data);  
	        outStream.flush();  
	        outStream.close();  
	  
	        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {  
	            InputStream inputStream = httpURLConnection.getInputStream(); 
	            String res = new String(read(inputStream));
	            return res;  
	        }  
	        return null;  
	    }  
	    
	    
	    
	    /**  
	     * ��ȡJSON��ʽ�Ļ�վ��Ϣ  
	     * @param mcc �ƶ����Ҵ��루�й���Ϊ460��  
	     * @param mnc �ƶ�������루�й��ƶ�Ϊ0���й���ͨΪ1���й�����Ϊ2����   
	     * @param lac λ��������  
	     * @param cid ��վ���  
	     * @return json  
	     * @throws JSONException  
	     */  
	    private String getJsonCellPos(int mcc, int mnc, int lac, int cid) throws JSONException {  
	        JSONObject jsonCellPos = new JSONObject();  
	        jsonCellPos.put("version", "1.1.0");  
	        jsonCellPos.put("host", "maps.google.com");  
	  
	        JSONArray array = new JSONArray();  
	        JSONObject json1 = new JSONObject();  
	        json1.put("location_area_code", "" + lac + "");  
	        json1.put("mobile_country_code", "" + mcc + "");  
	        json1.put("mobile_network_code", "" + mnc + "");  
	        json1.put("age", 0);  
	        json1.put("cell_id", "" + cid + "");  
	        array.put(json1);  
	  
	        jsonCellPos.put("cell_towers", array);  
	        return jsonCellPos.toString();  
	    } 
	    
	    
	    
	    
	    
	    /** 
	     * ��ȡIO������byte[]��ʽ�洢 
	     * @param inputSream InputStream 
	     * @return byte[] 
	     * @throws IOException 
	     */  
	    public byte[] read(InputStream inputSream) throws IOException {  
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        int len = -1;  
	        byte[] buffer = new byte[1024];  
	        while ((len = inputSream.read(buffer)) != -1) {  
	            outStream.write(buffer, 0, len);  
	        }  
	        outStream.close();  
	        inputSream.close();  
	  
	        return outStream.toByteArray();  
	    }   
	    
	    
	    
	    
	    
}
