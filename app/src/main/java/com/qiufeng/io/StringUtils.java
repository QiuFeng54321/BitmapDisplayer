package com.qiufeng.io;
public class StringUtils{

	public static final String CREATOR="QiuFeng";
	public static String join(Object[] o,String flag){
		
		StringBuffer str=new StringBuffer();
	
		for(int i=0,len=o.length;i<len;i++){
		
			str.append(String.valueOf(o[i]));
		
			if(i<len-1)str.append(flag);
		}
		return str.toString();
	}
	public static String[] toStringArray(char[] array){
		String[] res=new String[array.length];
		for(int i=0;i<array.length;i++){
			char[] buf={(char)array[i]};
			res[i]=(new String(buf));
		}
		return res;
	}
	public static String[] shift(String[] array){
		String[] buf=new String[array.length-1];
		for(int i=1;i<array.length;i++){
			buf[i-1]=array[i];
		}
		return buf;
	}
	public static String[] pop(String[] array){
		String[] buf=new String[array.length-1];
		for(int i=0;i<array.length-1;i++){
			buf[i]=array[i];
		}
		return buf;
	}
	public static String[] push(String[] array,String element){
		String[] res=new String[array.length+1];
		for(int i=0;i<array.length;i++){
			res[i]=array[i];
		}
		res[array.length]=array[(array.length>0?array.length-1:0)];
		return res;
	}
}
