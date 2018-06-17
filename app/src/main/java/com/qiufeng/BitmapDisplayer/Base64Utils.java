package com.qiufeng.BitmapDisplayer;
import android.util.Base64;
public class Base64Utils{
	public static String encode(String s){
		return Base64.encodeToString(s.getBytes(),Base64.DEFAULT);
	}
	public static String decode(String s){
		return new String(Base64.decode(s,Base64.DEFAULT));
	}
}
