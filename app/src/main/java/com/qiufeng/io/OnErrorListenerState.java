package com.qiufeng.io;
import java.util.*;
public class OnErrorListenerState{
	public HashSet onErrorListener=new HashSet();
	public static interface OnErrorListener{
		public void onError(Exception error);
	}
	public void notifyOE(Exception error){
		Iterator iter = onErrorListener.iterator();  
		while (iter.hasNext()) {  
			OnErrorListener listener = (OnErrorListener) iter.next();  
			listener.onError(error);  
		}  
	}
	public void setOnErrorListener(OnErrorListener listener){
		onErrorListener=new HashSet();
		onErrorListener.add(listener);
	}
	public OnErrorListenerState(){}
}
