package com.qiufeng.coder;
public class CharGroup{

	public static final String creator="QiuFeng";
	private char[] c;
	public CharGroup(String any){
	    c=any.toCharArray();
	}
  
	public CharGroup(char[] any){
		c=any;
	}
	public char[] toChar(){
   
	    return c;
	}
  
	public String toString(){
    
		return new String(c);
	}
  
	public void setData(String str){
	 	c=str.toCharArray();
	}
	public void setData(char[] ch){
		c=ch;
	}
}
