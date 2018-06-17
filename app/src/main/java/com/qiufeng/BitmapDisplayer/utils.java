package com.qiufeng.BitmapDisplayer;
import android.content.*;

public class utils
{
	public static String getStack(Exception e){
		StackTraceElement[] ste=e.getStackTrace();
		StringBuffer sb=new StringBuffer();
		for(StackTraceElement steo : ste){
			sb.append(steo.getMethodName())
				.append("("+steo.getFileName())
				.append(" #"+steo.getLineNumber()+")")
				.append("\n");
		}
		return sb.toString();
	}
	public static DialogInterface.OnClickListener newOnClick(){
		return new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface p1,int p2){}
		};
	}
}
