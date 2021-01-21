package com.example.ChitChat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UploadActivity extends Activity implements View.OnClickListener{

	private TextView tv;
	private Button btnshare;
	private Button btnupload;
	private Button btndownload2;
	private Button btnbrw;
	 
	
	    
	    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		btnshare=(Button)findViewById(R.id.btnshare);
		btnupload=(Button)findViewById(R.id.btnupload);
		btnbrw=(Button) findViewById(R.id.btnbrw);
		btndownload2=(Button) findViewById(R.id.btndownload2);
		tv=(TextView)findViewById(R.id.tv1);
		
		
		
		btnshare.setOnClickListener(this);
		btnupload.setOnClickListener(this);
		btnbrw.setOnClickListener(this);
		btndownload2.setOnClickListener(this);
		
	}


	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnupload:
			Toast.makeText(getBaseContext(), "Uploading Files........ ", Toast.LENGTH_SHORT).show();	
			break;
		case R.id.btnshare:
		{
		Intent in1=new Intent(this,ContactActivity.class);
		   startActivity(in1);
		}
		   break;
		   case R.id.btnbrw:
		   Toast.makeText(getBaseContext(), "Choose your files from storage ", Toast.LENGTH_SHORT).show();
		   break;
		   case R.id.btndownload2:
		   {   Intent in2=new Intent(this,FileActivity.class);
			   startActivity(in2);
		   }
			   break;
	}
	}
}

