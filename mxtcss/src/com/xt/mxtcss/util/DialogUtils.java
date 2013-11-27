package com.xt.mxtcss.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class DialogUtils {

	
	
	
	public static ProgressDialog progressDialogbuilder(Context context,String title,String msg){
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setIndeterminate(true);
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setCancelable(true);
		return dialog;
	}
	
	
	
	/**
	  * ÌבÊ¾
	  * 
	  * @param ctx
	  * @param msg
	  */
	 public static void alert(Context ctx,String title,String msg){
		 
	  Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
	 }
	
}
