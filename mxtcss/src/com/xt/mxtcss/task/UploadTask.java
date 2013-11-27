package com.xt.mxtcss.task;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.xt.mxtcss.bean.PostParam;
import com.xt.mxtcss.network.HttpRequest;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class UploadTask extends AsyncTask<PostParam, Integer, Boolean> {
	
	
	Context context;
	
	
	
	public UploadTask(Context context){
		this.context = context;
	}
	
	
	@Override
	protected Boolean doInBackground(PostParam... arg0) {
		try {
			HttpRequest.Post(arg0[0]);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		if(result){
			Toast.makeText(context, "上传成功！", Toast.LENGTH_SHORT).show();
		}
		super.onPostExecute(result);
	}



}
