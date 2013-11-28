package com.xt.mxtcss.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.xt.mxtcss.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
/**
 * 手机信息
 * @author ljj
 *
 */
public class PhoneInfoActivity extends Activity {

	
	//public ArrayList<HashMap<String, Object>> listItems;
	//public SimpleAdapter simpleAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_info_layout);
		//initListView();
		//this.setListAdapter(simpleAdapter);
		
	}


	/*private void initListView() {
		listItems = new ArrayList<HashMap<String,Object>>();
		for(int i =0;i<20;i++){
			HashMap<String,Object> hashMap = new HashMap<String,Object>();
			hashMap.put("ItemTitle", ""+i);
			hashMap.put("ItemImage", ""+i);
			listItems.add(hashMap);
		}
		
		
		simpleAdapter   = new SimpleAdapter(this, 
											listItems, 	
											R.layout.phone_info_layout_listitem, 
											new String[]{"ItemTitle","ItemImage"}, 
											new int[]{R.id.ItemTitle,R.id.ItemImage});
		
		
		
	}*/
}
