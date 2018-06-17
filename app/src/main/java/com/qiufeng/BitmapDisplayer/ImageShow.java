package com.qiufeng.BitmapDisplayer;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.widget.*;
import java.io.*;
import android.view.View.*;
import android.view.*;
import android.widget.Toolbar.*;

public class ImageShow extends AlertDialog.Builder implements DialogInterface.OnClickListener
{
	Bitmap b;
	ImageView iv;
	ImageView color;
	TextView info;
	LinearLayout ml;
	LinearLayout tl;
	Bitmap colBit;
	Activity cx;
	DialogInterface.OnClickListener d=new DialogInterface.OnClickListener(){

		@Override
		public void onClick(DialogInterface p1, int p2)
		{
			// TODO: Implement this method
		}
	};
	public ImageShow(Activity ctx,final Bitmap bd){
		super(ctx);
		cx=ctx;
		b=bd;
		ml=new LinearLayout(cx);
		tl=new LinearLayout(cx);
		iv=new ImageView(ctx);
		color=new ImageView(cx);
		info=new TextView(cx);
		info.setTextSize(15);
		tl.addView(info);
		tl.addView(color,new ViewGroup.LayoutParams(100,100));
		ml.setOrientation(ml.VERTICAL);
		ml.addView(tl);
		colBit=Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
		color.setImageBitmap(colBit);
		iv.setImageBitmap(bd);
		iv.setOnTouchListener(new View.OnTouchListener(){
			public boolean onTouch(View p1,MotionEvent p2){
				int x=Math.round(p2.getX());
				int y=Math.round(p2.getY());
				int col;
				try{
					col=bd.getPixel(x,y);
					colBit.setPixel(0,0,col);
					color.setImageBitmap(colBit);
				}catch(Exception e){
					info.setText("Touch out of range!");
					return true;
				}
				int[] cols={Color.alpha(col),Color.red(col),Color.green(col),Color.blue(col)};
				StringBuilder sb=new StringBuilder();
				sb.append("x:").append(x);
				sb.append(",y:").append(y);
				sb.append('\n');
				sb.append("alpha:").append(cols[0]);
				sb.append(" red:").append(cols[1]).append(" green:").append(cols[2]).append(" blue:").append(cols[3]);
				info.setText(sb.toString());
				return true;
			}
		});
		ml.addView(iv);
		setView(ml);
		setTitle("转换结果");
		setNeutralButton("保存",this);
		setPositiveButton("确定",d);
	}

	@Override
	public void onClick(DialogInterface p1, int p2)
	{
		// TODO: Implement this method
		FileChooserDialog fcd=new FileChooserDialog(cx,"/storage/emulated/0",true);
		fcd.setOnConfirmListener(new FileChooserDialog.OnConfirmListener(){
			public void onConfirm(String path,String text){
				if(text.equals("")){
					ui.snack(cx,"无效文件名称!");
					return;
				}else{
					try{
						BitmapUtils.saveBitmap(b,new File(path,text));
						ui.snack(cx,"保存成功!");
					}catch(Exception e){
						ui.snack(cx,"保存失败!");
					}
				}
			}
		});
		fcd.show();
	}

	
}
