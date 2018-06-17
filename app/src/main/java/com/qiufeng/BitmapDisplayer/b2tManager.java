package com.qiufeng.BitmapDisplayer;
import android.app.*;
import android.content.*;

public class b2tManager extends QDialog implements DialogInterface.OnClickListener
{
	Activity cx;
	String result;
	public b2tManager(Activity cx){
		super(cx);
		this.cx=cx;
		setTitle("请输入要转换的Base64");
		setPositiveButton("确定",this);
	}
	@Override
	public void onClick(DialogInterface p1, int p2)
	{
		// TODO: Implement this method
		String text=getEditText();
		try{
			result=Base64Utils.decode(text);
		}catch(Exception e){
			ui.snack(cx,"转换失败!");
			return;
		}
		DialogInterface.OnClickListener clip=new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface p1,int p2){
				ClipboardManager cm=(ClipboardManager)cx.getSystemService(cx.CLIPBOARD_SERVICE);
				cm.setText(result);
				ui.snack(cx,"复制成功!");
			}
		};
		AlertDialog.Builder ab=new AlertDialog.Builder(cx);
		ab.setTitle(text+"的转换结果");
		ab.setMessage(result);
		ab.setPositiveButton("确定",utils.newOnClick());
		ab.setNeutralButton("复制",clip);
		ab.show();
	}

	
}
