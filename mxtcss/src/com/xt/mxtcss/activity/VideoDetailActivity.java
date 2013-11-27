package com.xt.mxtcss.activity;

import java.io.File;

import com.xt.mxtcss.R;
import com.xt.mxtcss.bean.PostParam;
import com.xt.mxtcss.task.UploadTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VideoDetailActivity extends Activity {

	public SurfaceView surfaceView ; 
	public Button btnVideUpload;
	public Button btnDelete;
	public Button btnVideoPlay;
	public String video_url;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_detail_layout);
		surfaceView = (SurfaceView) findViewById(R.id.surface_video_play);
		btnVideUpload = (Button) findViewById(R.id.btn_video_upload);
		btnDelete = (Button) findViewById(R.id.btn_video_delete);
		btnVideoPlay = (Button) findViewById(R.id.btn_video_play);
		
	}
	
	
	
	
	
	@Override
	protected void onStart() {
		super.onStart();
		Intent ints = getIntent();
		final String url  = ints.getStringExtra("video_url");
		Toast.makeText(this, url, Toast.LENGTH_SHORT);
		btnVideUpload.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				PostParam param = new PostParam();
				
				param.setPath(url);
				new UploadTask(VideoDetailActivity.this).execute(param);
			}
		});
		
		
		
		btnDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder = new Builder(VideoDetailActivity.this);
				builder.setTitle("提示");
				builder.setMessage("确定要删除吗？");
				builder.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						File f = new File(url);
						if(f.exists()){
							f.delete();
						}
					}
				});
				
				
				builder.setNegativeButton("取消", new OnClickListener() {
					   @Override
					 public void onClick(DialogInterface dialog, int which) {
					    dialog.dismiss();
					 }
				});
				builder.create().show();
			}
		});
		
	}
}
