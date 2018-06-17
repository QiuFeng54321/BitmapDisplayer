package com.qiufeng.util;
import java.util.*;
public class TaskThread extends Thread{
	public static final String CREATOR="QiuFeng";
	//to put tasks
	public HashSet hs;
	//duration
	public long duration=0;
	//CONSTRUCTORS
	public TaskThread(TaskContainer tc){
		hs=tc.get();
	}
	public TaskThread(HashSet hashSet){
		hs=hashSet;
	}
	public void setTaskContainer(TaskContainer tc){
		hs=tc.get();
	}
	public void setHashSet(HashSet hashSet){
		hs=hashSet;
	}
	//Set the duration
	public void setDuration(long dura){
		duration=dura;
	}
	//LISTENERS
	public static interface TaskListener{
		public void onTaskStart(TaskObject task,int index);
		public void onTaskFinished(TaskObject task,int index);
	}
	private HashSet taskListener=new HashSet();
	public static interface OnCompleteListener{
		public void onComplete(TaskThread tt);
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
	private void notifyTL1(TaskObject task,int index){
		Iterator iter = taskListener.iterator();  
		while (iter.hasNext()) {  
			TaskListener listener = (TaskListener) iter.next();  
			listener.onTaskStart(task,index);  
		}  
	}
	private void notifyTL2(TaskObject task,int index){
		Iterator iter = taskListener.iterator();  
		while (iter.hasNext()) {  
			TaskListener listener = (TaskListener) iter.next();  
			listener.onTaskFinished(task,index);  
		}  
	}
	private void notifyC(TaskThread tt){
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
		while (iter.hasNext()) {
			Task ta = (Task) iter.next();
			notifyTL1(new TaskObject(ta,ta.getName()),count);
			ta.execute();
			notifyTL2(new TaskObject(ta,ta.getName()),count);
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
		notifyC(this);
	}
	//get HashSet
	public HashSet getHashSet(){
		return hs;
	}
	@Override
	public String toString(){
		return "TaskThread{"+getName()+","+getId()+"}";
	}
}
