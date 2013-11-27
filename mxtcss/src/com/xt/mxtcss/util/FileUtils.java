package com.xt.mxtcss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

	
	
	public static void FileCopy(File src,File desc){
		FileCopy(src.getAbsoluteFile(),desc.getAbsoluteFile());
	}
	
	public static void FileCopy(String src,String desc){
		FileInputStream fin = null;
		FileOutputStream fos = null;
		try {
			 fin = new FileInputStream(src);
			 fos = new FileOutputStream(desc);
			 byte[] buff = new byte[1024];
			 int read = 0;
			 while((read = fin.read(buff))!=-1){
				 fos.write(buff, 0, read);
			 }
			 fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fin != null){
				try {
					fin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
	}
	
}
