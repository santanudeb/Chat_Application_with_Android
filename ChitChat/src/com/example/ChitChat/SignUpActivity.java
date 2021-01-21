package com.example.ChitChat;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import util.ServerAddress;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUpActivity extends Activity implements View.OnClickListener {

	private static final int CHOOSE_FILE_REQUESTCODE = 11;
	private EditText ed;
	private EditText ed1;
	private EditText ed2;
	private Button btncancel;
	private Button btndone;
	private ImageView imgViewProfile;
	
	private ArrayList<NameValuePair> lst;
	private ArrayList<NameValuePair> nameValuePairs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign__up_);
		ed=(EditText)findViewById(R.id.editText1);
		ed1=(EditText)findViewById(R.id.editText2);
		ed2=(EditText)findViewById(R.id.ed2);
		imgViewProfile=(ImageView) findViewById(R.id.imgViewProfile);
		
		btncancel=(Button)findViewById(R.id.btncancel2);
		btndone=(Button)findViewById(R.id.btnsignup);
		
		btncancel.setOnClickListener(this);
		btndone.setOnClickListener(this);
		imgViewProfile.setOnClickListener(this);
		
					
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){   
		case R.id.btnsignup:
			nameValuePairs = new ArrayList<NameValuePair>();
			
			//TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			//String simSerialNumber = telemamanger.getSimSerialNumber();
			
			//nameValuePairs.add(new BasicNameValuePair("userid",simSerialNumber));								
		    nameValuePairs.add(new BasicNameValuePair("name",ed1.getText().toString()));
		    nameValuePairs.add(new BasicNameValuePair("emailid",ed2.getText().toString()));		
		    nameValuePairs.add(new BasicNameValuePair("option","addrec"));
		    nameValuePairs.add(new BasicNameValuePair("table","user"));
		    
		    
		    BasicNameValuePair bnpCode=new BasicNameValuePair("contact",ed.getText().toString());
			nameValuePairs.add(bnpCode);
			nameValuePairs.add(new BasicNameValuePair("userid","3"));
			
			
			new MyAsync().execute(ServerAddress.getMainPageAddress());
			
			break;
		      case R.id.btncancel2:
			Intent shw=new Intent(getBaseContext(), SignUpActivity.class);
			startActivity(shw);
		      case R.id.imgViewProfile:
			openFile("image/jpg");
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    String Fpath = data.getDataString();
	    
	    Toast.makeText(getBaseContext(), Fpath, Toast.LENGTH_SHORT).show(); 
	     
	    super.onActivityResult(requestCode, resultCode, data);

	}

	class MyAsync extends AsyncTask<String, Void, String>{

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
			Toast.makeText(getBaseContext(),result, Toast.LENGTH_SHORT).show();
		}
		
	}
    
	
	public void openFile(String minmeType) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(minmeType);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // special intent for Samsung file manager
        Intent sIntent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
         // if you want any file type, you can skip next line 
        sIntent.putExtra("CONTENT_TYPE", minmeType); 
        sIntent.addCategory(Intent.CATEGORY_DEFAULT);

        Intent chooserIntent;
        if (getPackageManager().resolveActivity(sIntent, 0) != null){
            // it is device with samsung file manager
            chooserIntent = Intent.createChooser(sIntent, "Open file");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { intent});
        }
        else {
            chooserIntent = Intent.createChooser(intent, "Open file");
        }

        try {
            startActivityForResult(chooserIntent, CHOOSE_FILE_REQUESTCODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "No suitable File Manager was found.", Toast.LENGTH_SHORT).show();
        }
    }
}


