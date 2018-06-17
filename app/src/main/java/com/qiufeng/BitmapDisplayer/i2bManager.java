package com.qiufeng.BitmapDisplayer;

import android.app.*;
import android.content.*;
import android.util.*;
import java.io.*;

public class i2bManager
{
	static Activity cx;
	static public String lasti2b;
	static FileChooserDialog fcd;
	static AlertDialog.Builder abb;
	static ProgressDialog ab;
	static File lastSelected;
	static void setContext(Activity ctx){
		cx=ctx;
	}
	static public void i2bClick(){
		fcd=new FileChooserDialog(cx,"/sdcard");
		fcd.setOnFileSelectedListener(new FileChooserDialog.OnFileSelectedListener(){
				public void onFileSelected(File file){
					lastSelected=file;

					try{
						ab=new ProgressDialog(cx);
						ab.setTitle(file.getPath());
						ab.setCancelable(false);
						ab.setMessage("生成结果中...");
						ab.show();
						Thread thre=new Thread(new Runnable(){
								public void run(){
									try{
										abb=new AlertDialog.Builder(cx);
										lasti2b=BitmapUtils.toBase64(BitmapUtils.getBitmap(lastSelected));
										abb.setTitle(lastSelected.getPath());
										abb.setMessage("转换结果:"+lasti2b);
										abb.setPositiveButton("确定",new DialogInterface.OnClickListener(){
												public void onClick(DialogInterface di,int index){}
											})
											.setNeutralButton("复制",new DialogInterface.OnClickListener(){
												public void onClick(DialogInterface di,int index){
													ClipboardManager cm=(ClipboardManager)cx.getSystemService(cx.CLIPBOARD_SERVICE);
													cm.setText(lasti2b);
													ui.snack(cx,"已复制到剪贴板");
												}
											});
										Log.d("i2b","path="+lastSelected.getPath()+",result="+lasti2b);
										cx.runOnUiThread(new Runnable(){
												public void run(){
													abb.show();
													ab.dismiss();
												}
											});
									}catch(Exception e){
										Log.e("i2berror",e.getMessage()+"\n"+utils.getStack(e));
											cx.runOnUiThread(new Runnable(){
												public void run(){
													ui.snack(cx,"转换失败!");
													ab.dismiss();
											}
										});
									}
								}
							});
						thre.start();
					}catch(Exception e){
						ui.snack(cx,lastSelected.getPath()+"转换失败!");
						Log.e("i2bError",e.getMessage()+"\n"+utils.getStack(e));
					}
				}
			});
		fcd.show();
	}
}
