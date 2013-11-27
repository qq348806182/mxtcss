package com.xt.mxtcss.activity;

import java.io.File;

import com.xt.mxtcss.R;
import com.xt.mxtcss.bean.PostParam;
import com.xt.mxtcss.task.UploadTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoDetailActivity extends Activity {
	
	public ImageView imgView ;
	public Button btnPhotoUpload;
	public Button btnDelete;
	public String photo_url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_detail_layout);
		btnPhotoUpload = (Button) findViewById(R.id.btn_photo_upload);
		imgView = (ImageView) findViewById(R.id.img_photo_preview);
		btnDelete = (Button) findViewById(R.id.btn_photo_delete);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Intent i = getIntent();
		final String photo_url = i.getStringExtra("photo_url");
		Bitmap bm = BitmapFactory.decodeFile(photo_url);
		imgView.setImageBitmap(bm);
		
		
		btnPhotoUpload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				PostParam param = new PostParam();
				param.setPath(photo_url);
				new UploadTask(PhotoDetailActivity.this).execute(param);
			}
		});
		
		
		
		btnDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder = new Builder(PhotoDetailActivity.this);
				builder.setTitle("提示");
				builder.setMessage("确定要删除吗？");
				builder.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						File f = new File(photo_url);
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
