package com.example.ChitChat;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ReportActivity extends Activity implements View.OnClickListener{
	

	private EditText editT1;
	private EditText editT2;
	private TextView textv1;
	private TextView textv2;
	private Button btnsubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		btnsubmit=(Button)findViewById(R.id.btnsubmit);
		textv1=(TextView)findViewById(R.id.textv1);
		textv2=(TextView)findViewById(R.id.textv2);
		editT1=(EditText)findViewById(R.id.editT1);
		editT2=(EditText)findViewById(R.id.editT2);
		
		btnsubmit.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getBaseContext(), "Submitted.. ", Toast.LENGTH_SHORT).show();
		editT1.setText("");
		editT2.setText("");
	}

	
	


}



