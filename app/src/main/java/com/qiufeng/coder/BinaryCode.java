package com.qiufeng.coder;
public class BinaryCode{

	public static final String creator="QiuFeng";
	private long Bin2Dec(long j){
  
		String[] n=StringUtils.shift(String.valueOf(j).split(""));
	    long res=0;
		for(int i=0;i<n.length;i++){
   
			res+=(Integer.parseInt(n[i]))*Math.pow(2,n.length-i-1);
			}
  
		return res;
	}
	
	public String encode(String s){
		
		char[] str=s.toCharArray();
		String[] res=new String[str.length];
		for(int j=0;j<str.length;j++){
 			res[j]=Integer.toBinaryString((int)(str[j]));
 		}
  		return StringUtils.join(res,"pk");
	}
	public String decode(String s){
  		String[] str=s.split("(pk)");
  		for(int k=0;k<str.length;k++){
			if(!str[k].isEmpty()){
     			long buf=Bin2Dec(Long.parseLong(str[k]));
				char[] buffer={(char)buf};
     			str[k]=new String(buffer);
			}
  		}
  		return StringUtils.join(str,"");
	}
}
