package com.qiufeng.BitmapDisplayer;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;
public class MainActivity extends Activity 
{
	final Activity cx=this;
	QDialog ad;
	public String lasti2b;
	public String lastt2b;
	FileChooserDialog fcd;
	AlertDialog.Builder abb;
	ProgressDialog ab;
	File lastSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			getActionBar().setSubtitle("by QiuFeng");
		//getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03A9F4")));
		//getActionBar().setElevation(100);
		t2bManager.setContext(cx);
		i2bManager.setContext(cx);
		Button i2b = (Button)findViewById(R.id.i2b);
		i2b.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				i2bManager.i2bClick();
			}
		});
			Button t2b=(Button)findViewById(R.id.t2b);
		t2b.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					t2bManager.t2bClick();
				}
			});
		Button b2i=(Button)findViewById(R.id.b2i);
		b2i.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					new b2iManager(cx).show();
				}
			});
		Button b2t=(Button)findViewById(R.id.b2t);
		b2t.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					new b2tManager(cx).show();
				}
			});
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		menu.add("关于");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		// TODO: Implement this method
		if(item.getTitle().toString().equals("关于")){
			startActivity(new Intent(MainActivity.this,AboutActivity.class));
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	
}
