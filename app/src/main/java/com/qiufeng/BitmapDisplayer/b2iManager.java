package com.qiufeng.BitmapDisplayer;

import android.app.*;
import android.content.*;
import android.graphics.*;

public class b2iManager extends QDialog implements DialogInterface.OnClickListener
{

	Activity cx;
	@Override
	public void onClick(DialogInterface p1, int p2)
	{
		// TODO: Implement this method
		Bitmap bm=BitmapUtils.toBitmap(getEditText());
		//ui.toast(cx,getEditText());
		ImageShow is=new ImageShow(cx,bm);
		is.show();
	}

	public b2iManager(Activity cx){
		super(cx);
		this.cx=cx;
		setTitle("请输入Base64编码");
		setPositiveButton("确定",this);
	}
}
