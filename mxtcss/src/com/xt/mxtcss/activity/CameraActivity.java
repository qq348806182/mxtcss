package com.xt.mxtcss.activity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.xt.mxtcss.R;
import com.xt.mxtcss.util.FileUtils;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Video;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CameraActivity extends Activity {

	public RadioGroup radiogroups;
	public RadioButton radio_photo ; 
	public RadioButton radio_video ; 
	public Button btn_takePhoto;
	public Button btn_takeVideo;
	public SimpleAdapter adapter;
	public ListView list;
	public ArrayList<HashMap<String, Object>> listItems;
	public File rootDir ;
	public final static Integer TAKE_PHOTO = 0;
	public final static Integer TAKE_VIDEO = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_layout);
		radio_photo = (RadioButton) findViewById(R.id.radio_photo);
		radio_video = (RadioButton) findViewById(R.id.radio_video);
		btn_takePhoto = (Button) findViewById(R.id.btn_takephoto);
		btn_takeVideo = (Button) findViewById(R.id.btn_takevideo);
		radiogroups = (RadioGroup) findViewById(R.id.radiogroups);
		list = (ListView) findViewById(R.id.photolist);
		initDate();
		list.setAdapter(adapter);
		
		
		btn_takePhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), TAKE_PHOTO);
			}
		});
		btn_takeVideo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent in = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				startActivityForResult(in, TAKE_VIDEO);
			}
		});
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				HashMap<String,Object> hashMap =  (HashMap<String, Object>) adapter.getItem(arg2);
				Intent i = new Intent();
				if(hashMap.get("ItemImage").toString().endsWith(".jpg")){
					i.setClass(CameraActivity.this,PhotoDetailActivity.class);
					i.putExtra("photo_url", rootDir + File.separator + "photo"+ File.separator + hashMap.get("ItemImage"));
				}else{
					i.setClass(CameraActivity.this,VideoDetailActivity.class);
					i.putExtra("video_url", rootDir + File.separator + "video"+ File.separator + hashMap.get("ItemImage"));
				}
				startActivity(i);
			}
		});
		
		
		
		radiogroups.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				if(arg1 == radio_photo.getId()){
					if(!listItems.isEmpty()){
						flushListData(0);
						adapter.notifyDataSetChanged();
					}
				}
				if(arg1 == radio_video.getId()){
					if(!listItems.isEmpty()){
						flushListData(1);
						adapter.notifyDataSetChanged();
					}
				}
			}
		});
	}
	
	private void initDate() {
		File sdDir = null;
		listItems = new ArrayList<HashMap<String,Object>>();
		if(Environment.isExternalStorageEmulated()){
			sdDir = Environment.getExternalStorageDirectory();
		}else{
			Toast.makeText(this, "SD卡读取失败！", Toast.LENGTH_SHORT).show();
		}
		rootDir = new File(sdDir + File.separator + "mxtcss");
		if(!rootDir.exists()){
			rootDir.mkdirs();
		}
		File video = new File(rootDir + File.separator + "video");
		if(!video.exists()){
			video.mkdir();
		}
		//Toast.makeText(this, sdDir.getAbsolutePath().toString(), Toast.LENGTH_SHORT).show();
		flushListData(0);
		adapter   = new SimpleAdapter(this, 
											listItems, 	
											R.layout.phone_info_layout_listitem, 
											new String[]{"ItemTitle","ItemImage"}, 
											new int[]{R.id.ItemTitle,R.id.ItemImage});
		
		radio_photo.setChecked(true);
		
	}	
	
	
	private void  flushListData(int type){
		listItems.clear();
		if(type == TAKE_PHOTO){
			File photesdir = new File(rootDir + File.separator + "photo");
			if(photesdir.exists() && photesdir.isDirectory()){
				for(File f : photesdir.listFiles()){
					HashMap<String,Object> hashMap = new HashMap<String,Object>();
					hashMap.put("ItemTitle", ""+(f.length()/1024));
					hashMap.put("ItemImage", ""+f.getName());
					listItems.add(hashMap);
				}
			}
		}else if(type == TAKE_VIDEO){
			File photesdir = new File(rootDir + File.separator + "video");
			if(photesdir.exists() && photesdir.isDirectory()){
				for(File f : photesdir.listFiles()){
					HashMap<String,Object> hashMap = new HashMap<String,Object>();
					hashMap.put("ItemTitle", ""+(f.length()/1024));
					hashMap.put("ItemImage", ""+f.getName());
					listItems.add(hashMap);
				}
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK){
			Intent i = new Intent();
			if(requestCode == TAKE_PHOTO){
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				File photoDir = new File(rootDir + File.separator + "photo");
				if(!photoDir.exists()){
					photoDir.mkdir();
				}
				File photofile = new File(photoDir + File.separator + System.currentTimeMillis()+".jpg");
				try {
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(photofile));
					bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
					bos.flush();
					bos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				i.setClass(this,PhotoDetailActivity.class);
				i.putExtra("photo_url", photofile.getAbsolutePath());
				startActivity(i);
			}
			
			if(requestCode == TAKE_VIDEO){
			 Cursor cursor = getContentResolver().query(data.getData(), null, null, null, null);  
             cursor.moveToFirst();  
             String mediaFilePath = cursor.getString(1); //
	         File mMediaFile=new File(mediaFilePath);
	         String newpath =  rootDir.getAbsoluteFile() + File.separator + "video" + File.separator + mMediaFile.getName();
	         FileUtils.FileCopy(mMediaFile.getAbsolutePath(),newpath);
	         mMediaFile.delete();
	         i.setClass(this,PhotoDetailActivity.class);
			 i.putExtra("video_url", newpath);
			 startActivity(i);
			}
		}
	}
	
	
	@Override
	protected void onRestart() {
		System.out.println("run-------------restart");
		super.onRestart();
	}
	@Override
	protected void onResume() {
		System.out.println("run-------------onResume");
		super.onResume();
	}
	
}
