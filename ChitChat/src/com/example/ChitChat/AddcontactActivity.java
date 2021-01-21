package com.example.ChitChat;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import util.ServerAddress;

import com.example.ChitChat.SignUpActivity.MyAsync;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddcontactActivity extends Activity implements View.OnClickListener {
	

	private EditText editcontact;
	private EditText editphono;
	private EditText editemail;
	private Button btnsearch;
	private Button btncancel;
	private Button btndone;
	private ArrayList<NameValuePair> lst;
	private ArrayList<NameValuePair> nameValuePairs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcontact);
		editcontact=(EditText)findViewById(R.id.editcontact);
		editphono=(EditText)findViewById(R.id.editphoneno);
		editemail=(EditText)findViewById(R.id.editemail);
		btncancel=(Button)findViewById(R.id.btncancel);
		btndone=(Button)findViewById(R.id.btndone);
		btnsearch=(Button)findViewById(R.id.btnsearch);
		
		btndone.setEnabled(true);
		
		btncancel.setOnClickListener(this);
		btndone.setOnClickListener(this);
		btnsearch.setOnClickListener(this);
		
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addcontact, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.btndone:
			nameValuePairs = new ArrayList<NameValuePair>();
		    
			nameValuePairs.add(new BasicNameValuePair("table","contact"));
			nameValuePairs.add(new BasicNameValuePair("option","addrec"));
			
			//TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			//String simSerialNumber = telemamanger.getSimSerialNumber();
			
			//nameValuePairs.add(new BasicNameValuePair("userid",simSerialNumber));
			nameValuePairs.add(new BasicNameValuePair("userid","3"));
			nameValuePairs.add(new BasicNameValuePair("contactid",editphono.getText().toString()));
		    
		    
		    new AsyncContactAdd().execute(ServerAddress.getMainPageAddress());

			break;
		case R.id.btncancel:
			break;
		case R.id.btnsearch:
			nameValuePairs = new ArrayList<NameValuePair>();
		    nameValuePairs.add(new BasicNameValuePair("option","getbyid"));
		    String sql=String.format("select * from user where contact='%s'",editphono.getText().toString());
		    
		    nameValuePairs.add(new BasicNameValuePair("sql",sql));
		    
		    new AsyncContactSearch().execute(ServerAddress.getMainPageAddress());
		    
			break;
		
		}
		
	}
	
	class AsyncContactAdd extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			String msg="";
			try {
						
				msg=HttpUtil.getResultFromWeb(params[0], nameValuePairs);
				
			} catch (Exception e) {				
				e.printStackTrace();
				msg="Exception: "+e.getMessage();
			}
			return msg;
		}
		
		@Override
		protected void onPostExecute(String result) {						
			
			try {
				
				JSONObject jsob=new JSONObject(result);
				if(jsob.getInt("errcode")==0){
					result=jsob.getString("result");
				}
				
			} catch (JSONException e) {
				
				if(result.indexOf("Duplicate entry")!=-1){
					result="Contact already exists...";
				}
				else{
					result="Exception: "+e.getMessage();
				}
				e.printStackTrace();
			}
			
			Toast.makeText(getBaseContext(),result, Toast.LENGTH_SHORT).show();
			
		}
		
	}
	
	class AsyncContactSearch extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			String msg="";
			try {
						
				msg=HttpUtil.getResultFromWeb(params[0], nameValuePairs);
				
			} catch (Exception e) {				
				e.printStackTrace();
				msg="Exception: "+e.getMessage();
			}
			return msg;
		}
		
		@Override
		protected void onPostExecute(String result) {
			//Toast.makeText(getBaseContext(),result, Toast.LENGTH_SHORT).show();
			try {
				JSONObject jsob=new JSONObject(result);
				int errCode=jsob.getInt("errcode");
				if(errCode!=0){					
					Toast.makeText(getBaseContext(),jsob.getString("errmsg"), Toast.LENGTH_SHORT).show();
					editcontact.setText("");
					editemail.setText("");
					btndone.setEnabled(false);
				}else{
					JSONObject resultOb=new JSONObject(jsob.getString("result"));
					
					String name=resultOb.getString("name");
					editcontact.setText(name);
					String emailid=resultOb.getString("emailid");
					editemail.setText(emailid);
				    	
					btndone.setEnabled(true);
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
