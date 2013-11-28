package com.xt.mxtcss.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.*;
/**
 * 联系人信息
 * @author ljj
 *
 */
public class ContactInfoActivity extends ListActivity  {

	
	private String[] PHONES_PROJECTION  = new String[]{Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID,Phone.CONTACT_ID };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
	}
	
	
	private void getPhoneContacts(){
		ContentResolver contentResolver = (ContentResolver) getContentResolver().query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
		
		
		
		
		
		
		
		
		
	}
	
	
}
