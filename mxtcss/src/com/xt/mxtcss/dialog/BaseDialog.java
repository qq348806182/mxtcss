package com.xt.mxtcss.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

public class BaseDialog {

	
	
	public static void  buildDialog(Context context,String msg){
		new AlertDialog.Builder(context)
		.setMessage(msg)
		.show();
	}
	
}
