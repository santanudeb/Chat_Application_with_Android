package com.example.ChitChat;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePageActivity extends Activity implements View.OnClickListener{
	
	private Button log,sign;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
	    sign=(Button) findViewById(R.id.btnsign);
		log=(Button) findViewById(R.id.btn_log);
		sign.setOnClickListener(this);
		log.setOnClickListener(this);
		//b2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_log:
			Intent in=new Intent(this,ContactActivity.class);
			startActivity(in);
			break;
		case R.id.btnsign:
			Intent in1=new Intent(this,SignUpActivity.class);
			   startActivity(in1);
			break;
			
		}
		   
		}
		
		
	}
	
