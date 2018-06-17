package com.qiufeng.BitmapDisplayer;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import com.qiufeng.BitmapDisplayer.MainActivity;
public class QDialog extends AlertDialog.Builder{
	public QDialog(Context cx){
		super(cx);
		input=new EditText(cx);
		setView(input);
	}
	public EditText input;
	public void setEditHint(String s){
		input.setHint(s);
	}
	public void setEditHint(int id){
		input.setHint(id);
	}
	public String getEditText(){
		return input.getText().toString();
	}
}
