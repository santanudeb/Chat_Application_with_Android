package com.example.ChitChat;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.ServerAddress;

import com.example.ChitChat.AddcontactActivity.AsyncContactSearch;

import entity.User;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ContactActivity extends Activity {

	private Button btnAdd;	
	private ListView lv;
	private ArrayList<NameValuePair> nameValuePairs;
	private ArrayList<User> contacts;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		contacts=new ArrayList<User>();
		
		btnAdd=(Button)findViewById(R.id.addbtn);		
		lv=(ListView)findViewById(R.id.listView1);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				Intent chat=new Intent(getBaseContext(),MainActivity.class);
				User u= contacts.get(position);
				chat.putExtra("msg_to",u);
				startActivity(chat);
				
			
			}
			
		});

		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent inte=new Intent(getBaseContext(), AddcontactActivity.class);
				startActivity(inte);
				
			}
		});
		
		nameValuePairs = new ArrayList<NameValuePair>();
	    
		nameValuePairs.add(new BasicNameValuePair("sql","select * from contact c,user u where u.userid=c.contactid"));
		nameValuePairs.add(new BasicNameValuePair("option","getallrec"));
		
		
		new AsyncContactList().execute(ServerAddress.getMainPageAddress());	

	}
	
	
	class AsyncContactList extends AsyncTask<String, Void, String>{

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
				if(jsob.getInt("errcode")==0){
					JSONArray jsarr=jsob.getJSONArray("result");
										
					
					for(int i=0;i<jsarr.length();i++){
						JSONObject recOb=jsarr.getJSONObject(i);
						
						User user=new User();
						user.setName(recOb.getString("name"));
						user.setUserid(recOb.getString("userid"));
						user.setStatus(recOb.getString("status"));
						user.setContact(recOb.getString("contact"));
						//user.setImage(recOb.getString("image"));
						
						contacts.add(user);	
						
					}
					
					//Toast.makeText(getBaseContext(),"recs: "+contacts.size(), Toast.LENGTH_SHORT).show();
					
					ContactViewAdapter cvadp=new ContactViewAdapter(getBaseContext(), R.layout.contact_view, contacts);
					
					lv.setAdapter(cvadp);
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}	
}
