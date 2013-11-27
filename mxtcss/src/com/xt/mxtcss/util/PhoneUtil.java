package com.xt.mxtcss.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

public class PhoneUtil {

	
	//获取手机安装的应用信息（排除系统自带）
	public String getAllApp(Context context){
		 String result = "";  
         List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);  
        for (PackageInfo i : packages) {  
             if ((i.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {  
               result += i.applicationInfo.loadLabel(context.getPackageManager()).toString() + ",";  
             }  
       }  
		  return result.substring(0, result.length() - 1);  
	}
	
	//获取手机可用内存和总内存
	private String[] getTotalMemory(Context context) {  
        String[] result = {"",""};  //1-total 2-avail  
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();    
       // ActivityManager.getMemoryInfo(mi);    
        long mTotalMem = 0;  
        long mAvailMem = mi.availMem;  
        String str1 = "/proc/meminfo";  
        String str2;  
        String[] arrayOfString;  
        try {  
            FileReader localFileReader = new FileReader(str1);  
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);  
            str2 = localBufferedReader.readLine();  
            arrayOfString = str2.split("\\s+");  
            mTotalMem = Integer.valueOf(arrayOfString[1]).intValue() * 1024;  
            localBufferedReader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        result[0] = Formatter.formatFileSize(context, mTotalMem);  
        result[1] = Formatter.formatFileSize(context, mAvailMem);  
        //Log.i(TAG, "meminfo total:" + result[0] + " used:" + result[1]);  
        return result;  
    }
	
	
	//
	private String[] getCpuInfo() {  
        String str1 = "/proc/cpuinfo";  
        String str2 = "";  
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率  
        String[] arrayOfString;  
        try {  
            FileReader fr = new FileReader(str1);  
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);  
            str2 = localBufferedReader.readLine();  
            arrayOfString = str2.split("\\s+");  
            for (int i = 2; i < arrayOfString.length; i++) {  
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";  
            }  
            str2 = localBufferedReader.readLine();  
            arrayOfString = str2.split("\\s+");  
            cpuInfo[1] += arrayOfString[2];  
            localBufferedReader.close();  
        } catch (IOException e) {  
        }  
       // Log.i(TAG, "cpuinfo:" + cpuInfo[0] + " " + cpuInfo[1]);  
        return cpuInfo;  
    }
	
	
	
	private String getMacAddress(Context context){  
        String result = "";  
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();  
        result = wifiInfo.getMacAddress();  
        return result;  
	}
	
	private void getInfo(Context context) {  
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);  
        String imei = mTm.getDeviceId();  
        String imsi = mTm.getSubscriberId();  
        String mtype = android.os.Build.MODEL; // 手机型号  
        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得  
    }
	
	
	
	private void getWeithAndHeight(Context context){  
        //这种方式在service中无法使用，  
       /* DisplayMetrics dm = new DisplayMetrics();  
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);  
        String width = dm.widthPixels;              //宽  
        String height = dm.heightPixels;           //高  
   
        //在service中也能得到高和宽  
        WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);  
        width = mWindowManager.getDefaultDisplay().getWidth();  
        height = mWindowManager.getDefaultDisplay().getHeight();  */
    }
	
	
	
	
}
