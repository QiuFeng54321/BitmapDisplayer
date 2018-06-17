package com.qiufeng.util;
import java.util.*;
public class SyncTaskThread extends Thread{
	public static final String CREATOR="QiuFeng";
	//to put tasks
	public HashSet hs;
	//duration
	public long duration=0;
	//CONSTRUCTORS
	public SyncTaskThread(HashSet hashSet){
		hs=hashSet;
	}
	public void setTaskThreads(HashSet hashSet){
		hs=hashSet;
	}
	//Set the duration
	public void setDuration(long dura){
		duration=dura;
	}
	//LISTENERS
	public static interface TaskListener{
		public void onTaskStart(TaskThread task,int index);
	}
	private HashSet taskListener=new HashSet();
	public static interface OnCompleteListener{
		public void onComplete(SyncTaskThread tt);
	}
	private HashSet CL=new HashSet();
	public void setTaskListener(TaskListener t){
		taskListener=new HashSet();
		taskListener.add(t);
	}
	public void setOnCompleteListener(OnCompleteListener t){
		CL=new HashSet();
		CL.add(t);
	}
	//Notifiers
	private void notifyTL1(TaskThread task,int index){
		Iterator iter = taskListener.iterator();  
		while (iter.hasNext()) {  
			TaskListener listener = (TaskListener) iter.next();  
			listener.onTaskStart(task,index);  
		}  
	}
	private void notifyC(SyncTaskThread tt){
		Iterator iter = CL.iterator();  
		while (iter.hasNext()) {  
			OnCompleteListener listener = (OnCompleteListener) iter.next();  
			listener.onComplete(tt);  
		}  
	}
	//RUN
	@Override
	public void run(){
		Iterator iter = hs.iterator();  
		int count=0;
		int len=hs.size();
		while (iter.hasNext()) {
			TaskThread ta = (TaskThread) iter.next();
			notifyTL1(ta,count);
			ta.start();
			count++;
			if(duration>0){
				try{
					Thread.sleep(duration);
				}catch(InterruptedException e){

				}
			}
		}
		//exit
		//Call OnCompleteListener
		while(count<len){}
		notifyC(this);
	}
	//get HashSet
	public HashSet getHashSet(){
		return hs;
	}
	@Override
	public String toString(){
		return "SyncTaskThread{"+getName()+","+getId()+"}";
	}
}
