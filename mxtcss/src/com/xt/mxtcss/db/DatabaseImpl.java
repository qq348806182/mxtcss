package com.xt.mxtcss.db;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseImpl implements Database {

	private final String DB_NAME = "xtcssdb";
	
	
	
	
	
	public DatabaseImpl(Activity mActivity){
		SQLiteDatabase db = mActivity.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
	}
	
	@Override
	public void create() {
		

	}

}
