package com.xt.mxtcss.task;

import android.R.anim;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class LoginTask extends AsyncTask<String, Integer, Boolean> {
	private String Tag = LoginTask.class.getSimpleName();
	ProgressDialog dialog ;
	
	
	Context context;
	
	public LoginTask(Context context){
		this.context = context;
	}
	
	
	@Override
	protected Boolean doInBackground(String... params) {
		//登录请求验证
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
	@Override
	protected void onPostExecute(Boolean result) {
		if(result){
			dialog.dismiss();
		}
		
	}

	@Override
	protected void onPreExecute() {
		dialog = ProgressDialog.show(context, "登录中", "正在登录。。。");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		Log.i(Tag, "onProgressUpdate run......" + values);
	}

	
	@Override
	protected void onCancelled() {
		super.onCancelled();
	}
	
}
