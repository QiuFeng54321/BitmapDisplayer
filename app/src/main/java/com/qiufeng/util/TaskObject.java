package com.qiufeng.util;
public class TaskObject{
	public Task task;
	public String n;
	private static int currentID=0;
	public TaskObject(Task t,String name){
		task=t;
		n=name;
	}
	public TaskObject(Task t){
		task=t;
		n=String.valueOf(currentID);
		currentID++;
	}
	public String getName(){
		return n;
	}
	public Task getTask(){
		return task;
	}
}
