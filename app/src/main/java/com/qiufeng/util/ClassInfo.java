package com.qiufeng.util;
import java.lang.reflect.*;
public class ClassInfo{
	public static final String CREATOR="QiuFeng";
	public int METHOD_NOT_FOUND=12345;
	private Class ClassObject;
	public ClassInfo(Class clazz){
		ClassObject=clazz;
	}
	public Class getClassObject(){
		return ClassObject;
	}
	public String[] getMethodsName(){
		Method[] methods=ClassObject.getMethods();
		String[] names=new String[methods.length];
		for(int i=0;i<methods.length;i++){
			names[i]=methods[i].getName();
		}
		return names;
	}
	public int getMethodModifier(String mn){
		Method[] methods=ClassObject.getMethods();
		for(Method i : methods){
			if(i.getName().equals(mn)){
				return i.getModifiers();
			}
		}
		return METHOD_NOT_FOUND;
	}
}
