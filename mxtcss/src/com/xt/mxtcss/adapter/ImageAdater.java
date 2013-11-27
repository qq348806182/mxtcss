package com.xt.mxtcss.adapter;



import com.xt.mxtcss.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdater extends BaseAdapter {

	private Context context;
	
	private Integer images[] ={
		R.drawable.phone,
		R.drawable.camera,
		R.drawable.contact,
		R.drawable.box,
		
		
		R.drawable.box,
		R.drawable.contact,
		R.drawable.camera,
		R.drawable.phone,
	
		R.drawable.phone,
		R.drawable.camera,
		R.drawable.contact,
		R.drawable.box,
		R.drawable.box,
		R.drawable.contact,
		R.drawable.camera,
		R.drawable.phone
			
	};
	
	private String texts[] = {
			"手机信息",
			"拍照上传",
			"手机设置",
			"通信记录",
			"扫一扫",
			"GPS信息",
			"手机信息",
			"拍照上传",
			"手机信息",
			"拍照上传",
			"手机设置",
			"手机信息",
			"拍照上传",
			"手机设置",
			"手机信息",
			"拍照上传"
			
			
	};
	
	
	
	
	public ImageAdater(Context context){
		this.context  = context;
	}
	
	
	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		ImgTextWrapper wrapper;  
		  if(view==null) {  
		   wrapper = new ImgTextWrapper();  
		   LayoutInflater inflater = LayoutInflater.from(context);  
		   view = inflater.inflate(R.layout.activity_cusumer_icon, null);  
		   view.setTag(wrapper);  
		   //view.setPadding(15, 15, 15, 15);  //每格的间距  
		  } else {  
		   wrapper = (ImgTextWrapper)view.getTag();  
		  }  
		    
		  wrapper.imageView = (ImageView)view.findViewById(R.id.MainActivityImage);  
		  wrapper.imageView.setBackgroundResource(images[position]);  
		  wrapper.textView = (TextView)view.findViewById(R.id.MainActivityText);  
		  wrapper.textView.setText(texts[position]);  
		    
		  return view; 
	}
	
	
	class ImgTextWrapper {  
		 ImageView imageView;  
		 TextView textView;  
		   
	} 

}
