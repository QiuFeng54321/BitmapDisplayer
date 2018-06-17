package com.qiufeng.coder;
public class Caesar{
	public static final String creator="QiuFeng";
	public static String encode(String str,int key){
		char[] c=str.toCharArray();
		for(int i=0;i<c.length;i++){
			c[i]+=key;
		}
		return new String(c);
	}
	public static String decode(String str,int key){
		char[] c=str.toCharArray();
		for(int i=0;i<c.length;i++){
			c[i]-=key;
		}
		return new String(c);
	}
}
