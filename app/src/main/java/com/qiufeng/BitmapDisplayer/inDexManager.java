package com.qiufeng.BitmapDisplayer;

import android.app.*;
import android.content.*;
import android.view.*;

public class inDexManager
{
	static Context cx;
	public static boolean isInDex=false;
	private static void setContext(Context ctx){
		cx=ctx;
		initialised=true;
	}
	public static void init(Context ctx){
		setContext(ctx);
		try{
			LayoutInflater.from(cx).inflate(R.layout.main,null);
			isInDex=false;
		}catch(Exception e){
			isInDex=true;
		}
	}
	static boolean initialised=false;
}
