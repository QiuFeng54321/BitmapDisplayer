package com.qiufeng.BitmapDisplayer;
import android.graphics.*;
import java.io.*;
import android.util.*;
import android.graphics.drawable.*;
public class BitmapUtils{
	public static String toBase64(Bitmap bit){
		String base64=new String();
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        base64 = Base64.encodeToString(bytes, android.util.Base64.DEFAULT);
        return base64;
	}
	public static Bitmap getBitmap(File file) throws Exception{
		BitmapDrawable d=(BitmapDrawable)Drawable.createFromStream(new FileInputStream(file),"utf-8");
		Bitmap bitmap=d.getBitmap();
		return bitmap;
	}
	public static Bitmap toBitmap(String b){
		Bitmap bitmap=Bitmap.createBitmap(1,1,Bitmap.Config.ALPHA_8);
		try {
            byte[] bitmapArray = Base64.decode(b, Base64.DEFAULT);
            bitmap = android.graphics.BitmapFactory.decodeByteArray(bitmapArray, 0,bitmapArray.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	public static void saveBitmap(Bitmap bitmap, File file)throws Exception  {  
		Bitmap newBitmap = android.graphics.Bitmap.createBitmap(bitmap.getWidth(),  
															 bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);  
		Canvas canvas = new android.graphics.Canvas(newBitmap);  
		canvas.drawColor(android.graphics.Color.WHITE);  
		canvas.drawBitmap(bitmap, 0, 0, null);  
		FileOutputStream stream = new FileOutputStream(file);  
		newBitmap.compress(android.graphics.Bitmap.CompressFormat.PNG,100, stream);  
		stream.close();  
	}  
}
