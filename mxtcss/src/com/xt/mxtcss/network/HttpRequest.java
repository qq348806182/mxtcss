package com.xt.mxtcss.network;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import com.xt.mxtcss.bean.PostParam;
import com.xt.mxtcss.util.Contants;

public class HttpRequest {

	
	
	
	
	public static boolean Request(PostParam param){
		
		
		
		return false;
	}
	
	
	
	
	public static boolean Post(PostParam param ) throws ClientProtocolException, IOException{
		
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		
		HttpPost httppost = new HttpPost(Contants.UPLOAD_URL);
		File file = new File(param.getPath());
		
		
		MultipartEntity mpEntity = new MultipartEntity(); //文件传输
	    ContentBody cbFile = new FileBody(file);
	    mpEntity.addPart("userfile", cbFile); 
	    httppost.setEntity(mpEntity);
	    
	    
	    System.out.println("executing request " + httppost.getRequestLine());
	    
	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity resEntity = response.getEntity();

	    System.out.println(response.getStatusLine());//通信Ok
		
		
		return true;
	}
}
