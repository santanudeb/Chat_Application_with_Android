package com.example.ChitChat;

import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.datatype.Duration;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.ServerAddress;

import com.example.ChitChat.ContactActivity.AsyncContactList;
import com.example.ChitChat.SignUpActivity.MyAsync;

import entity.Chat;
import entity.User;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

	private ListView msgview;
	private EditText edittext;
	private Button btnSend;
	private Button refresh1;
	private Button attach1;
	private Button contact1;

	private ArrayList<Chat> chats;
	private ArrayList<NameValuePair> nameValuePairs;

	private User receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		msgview = (ListView) findViewById(R.id.msgview);
		edittext = (EditText) findViewById(R.id.msgedit);
		btnSend = (Button) findViewById(R.id.btnSend);
		refresh1= (Button) findViewById(R.id.refresh1);
		attach1= (Button) findViewById(R.id.attach1);
		contact1= (Button) findViewById(R.id.contact1);
		
		refresh1.setOnClickListener(this);
		btnSend.setOnClickListener(this);
		attach1.setOnClickListener(this);
		contact1.setOnClickListener(this);

		Intent i = getIntent();
		receiver = (User) i.getExtras().get("msg_to");		

		showMessages();
		
	}
	
	public void showMessages(){
		nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("sql","select * from chat where (msg_to = '3' and msg_from ='"+receiver.getUserid()+"')  or (msg_to = '"+receiver.getUserid()+"' and msg_from = '3')"));
		nameValuePairs.add(new BasicNameValuePair("option", "getallrec"));		
		new AsyncView().execute(ServerAddress.getMainPageAddress());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String action=item.getTitle().toString();
		
		if(action.equals("refresh")){
			Intent inte=new Intent(getBaseContext(), MainActivity.class);
			startActivity(inte);
		}
		
		//Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
		return true;
	}

	public void onGroupItemClick(MenuItem item) {
		if (item.isChecked()) {
			item.setChecked(false);
		} else {
			item.setChecked(true);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSend:

			nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs.add(new BasicNameValuePair("message", edittext.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair("msg_from", "3"));
			nameValuePairs.add(new BasicNameValuePair("msg_to", receiver.getUserid()));

			StringBuffer sb = new StringBuffer();
			Calendar cal = Calendar.getInstance();
			sb.append(cal.get(Calendar.YEAR)).append("-");
			sb.append(cal.get(Calendar.MONTH) + 1).append("-");
			sb.append(cal.get(Calendar.DAY_OF_MONTH)).append(" ");

			sb.append(cal.get(Calendar.HOUR)).append(":");
			sb.append(cal.get(Calendar.MINUTE)).append(":");
			sb.append(cal.get(Calendar.SECOND));

			nameValuePairs.add(new BasicNameValuePair("msg_time", sb.toString()));

			nameValuePairs.add(new BasicNameValuePair("option", "addrec"));
			nameValuePairs.add(new BasicNameValuePair("table", "chat"));
			
			new AsyncSend().execute(ServerAddress.getMainPageAddress());
			break;
		case R.id.refresh1:
			Intent in1=new Intent(this,ReportActivity.class);
			   startActivity(in1);
			break;
		case R.id.attach1:
			Intent in2=new Intent(this,UploadActivity.class);
			   startActivity(in2);
			break;
		case R.id.contact1:
			Intent in3=new Intent(this,ContactActivity.class);
			   startActivity(in3);
			break;
			
		}
 
	
	}

	
	class AsyncSend extends AsyncTask<String, Void, String> {


		@Override
		protected String doInBackground(String... params) {
			String msg = "";
			try {

				msg = HttpUtil.getResultFromWeb(params[0], nameValuePairs);

			} catch (Exception e) {
				e.printStackTrace();
				msg = "Exception: " + e.getMessage();
			}
			return msg;
		}

		protected void onPostExecute(String result) {

			try {

				JSONObject jsob = new JSONObject(result);
				if (jsob.getInt("errcode") == 0) {
					result = jsob.getString("result");
					result = "Sent Successfuly";

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
			
			showMessages();
			
			edittext.setText("");
		}
	}

	class AsyncView extends AsyncTask<String, Void, String>{
 
		private JSONArray jsarr;
		@Override
		protected String doInBackground(String... params) {
			String msg = "";
			try {

				msg = HttpUtil.getResultFromWeb(params[0],nameValuePairs );

			} catch (Exception e) {
				e.printStackTrace();
				msg = "Exception: " + e.getMessage();
			}
			return msg;
		}
		
		protected void onPostExecute(String result) {

			try {

				JSONObject jsob = new JSONObject(result);
				if (jsob.getInt("errcode") == 0) {
					jsarr = jsob.getJSONArray("result");
					
					chats=new ArrayList<Chat>();
					for (int j = 0; j < jsarr.length(); j++) {
						JSONObject recOb = jsarr.getJSONObject(j);

						Chat chat = new Chat();
						chat.setChatId(recOb.getInt("chatid"));
						chat.setMsgTime(recOb.getString("msg_time"));
						chat.setMessage(recOb.getString("message"));						
						chat.getMsgFrom().setUserid(recOb.getString("msg_from"));
						chat.getMsgTo().setUserid(recOb.getString("msg_to"));
						
						chats.add(chat);
		
					}
					//ArrayAdapter<Chat> adpChat=new ArrayAdapter<Chat>(getBaseContext(),android.R.layout.simple_list_item_1,chats);
					MsgViewAdapter adpChat=new MsgViewAdapter(getBaseContext(), R.layout.activity_message_view, chats, "3");
					msgview.setAdapter(adpChat);
				}
			}catch (JSONException e) {
				e.printStackTrace();
			}
		
		}
		
	}
}


