package com.qiufeng.BitmapDisplayer;

import android.app.*;
import android.content.*;
import android.util.*;
import com.qiufeng.BitmapDisplayer.*;

public class t2bManager
{
	static Activity cx;
	static QDialog ad;
	public static String lastt2b;
	public static void setContext(final Activity ctx){
		cx=ctx;
	}
	public static void t2bClick(){
		ad=new QDialog(cx);
		ad.setTitle("请输入文字");
		ad.setEditHint("比如hello");
		ad.setPositiveButton("确定",new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface di,int ind){
					try{
						lastt2b=Base64Utils.encode(ad.getEditText());
						Log.d("t2b","text="+ad.getEditText()+",result="+lastt2b);
						new AlertDialog.Builder(cx)
							.setTitle(ad.getEditText())

							.setMessage("结果:"+lastt2b)
							.setPositiveButton("确定",new DialogInterface.OnClickListener(){
								public void onClick(DialogInterface di,int index){}
							})
							.setNeutralButton("复制",new DialogInterface.OnClickListener(){
								public void onClick(DialogInterface di,int index){
									ClipboardManager cm=(ClipboardManager)cx.getSystemService(cx.CLIPBOARD_SERVICE);
									cm.setText(lastt2b);
									ui.snack(cx,"已复制到剪贴板");
								}
							})
							.show();
					}catch(Exception e){
						ui.snack(cx,ad.getEditText()+"转换失败!");
						Log.e("t2bError",e.getMessage()+"\n"+utils.getStack(e));
					}
				}
			});
		ad.show();
	}
}
