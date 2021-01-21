package com.example.ChitChat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;


public class FileActivity extends Activity implements View.OnClickListener{

	private Button download;
	private Button share2;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);
		share2=(Button)findViewById(R.id.btnshare2);
		download=(Button)findViewById(R.id.btndownload);
		
		tv=(TextView)findViewById(R.id.tvName);
		
		share2.setOnClickListener(this);
		download.setOnClickListener(this);
		
		
	}
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.btndownload:
				Toast.makeText(getBaseContext(), "Downloading Files........ ", Toast.LENGTH_SHORT).show();	
				break;
			case R.id.btnshare2:
			{
				Intent in1=new Intent(this,ContactActivity.class);
				   startActivity(in1);
				}
				break;
			
	}
		}
}
