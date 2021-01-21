package com.example.ChitChat;

import java.util.List;

import entity.User;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactViewAdapter extends ArrayAdapter<User> {
	private int layoutResource;
	
	public ContactViewAdapter(Context context, int resource, List<User> objects) {
		super(context, resource, objects);
		this.layoutResource=resource;
	}



	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=convertView;
		
		if(view==null){
			LayoutInflater linf=LayoutInflater.from(getContext());
			view=linf.inflate(layoutResource, null);
		}
		
		User user=getItem(position);
		
		if(user!=null){
			ImageView img=(ImageView) view.findViewById(R.id.imgUserPic);
			TextView tvName=(TextView) view.findViewById(R.id.tvName);
			TextView tvStatus=(TextView) view.findViewById(R.id.tvStatus);
			
			
			tvName.setText(user.getName());
			tvStatus.setText(user.getStatus());
		}
		
		return view;	
	}

}
