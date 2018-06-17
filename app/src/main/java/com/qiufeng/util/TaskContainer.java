package com.qiufeng.util;
import java.util.*;
public class TaskContainer{
	public static final String CREATOR="QiuFeng";
	private HashSet<Task> hs;
	public String name;
	private static int currentID=0;
	public TaskContainer(){
		this(String.valueOf(currentID));
		currentID++;
	}
	public TaskContainer(String name){
		hs=new HashSet<Task>();
	}
	public void addTask(Task task){
		hs.add(task);
	}
	public void removeTask(Task task){
		hs.remove(task);
	}
	public void clearAll(){
		hs.clear();
	}
	public HashSet get(){
		return hs;
	}
}
