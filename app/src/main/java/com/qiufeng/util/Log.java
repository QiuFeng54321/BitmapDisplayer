package com.qiufeng.util;
import java.io.*;
import java.util.*;
import com.qiufeng.io.*;
import java.text.SimpleDateFormat;
//this class can be used to create logs.
public class Log{
	public static final String CREATOR="QiuFeng";
	public File LogFile;
	public SimpleDateFormat sdf=new SimpleDateFormat("<yyyy/MM/dd HH:mm:ss>");
	public Log(String path){
		LogFile=new File(path);
	}
	public Log(File file){
		LogFile=file;
	}
	public void setFormat(SimpleDateFormat format){
		sdf=format;
	}
	public void setFormat(String format){
		setFormat(new SimpleDateFormat(format));
	}
	//get Log info
	public String[] getLogs() throws Exception{
		QTextInputStream q=new QTextInputStream(LogFile);
		return q.readFullTextArray();
	}
	//append a log
	public void addLog(String str) throws Exception{
		Date d=new Date();
		StringBuilder sb=new StringBuilder();
		String[] logs=getLogs();
		sb.append(StringUtils.join(logs,"\n"));
		if(logs.length>0){
			sb.append("\n");
		}
		sb.append(sdf.format(d)+str);
		QTextOutputStream qtos=new QTextOutputStream(LogFile);
		qtos.simpleWrite(sb.toString());
		qtos.close();
	}
	//clear all logs
	public void clearLog() throws Exception{
		QTextOutputStream qtos=new QTextOutputStream(LogFile);
		qtos.simpleWrite("");
		qtos.close();
	}
}
