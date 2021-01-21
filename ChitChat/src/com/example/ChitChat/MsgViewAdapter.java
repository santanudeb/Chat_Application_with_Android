package com.example.ChitChat;

import java.util.List;

import android.content.Context;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import entity.Chat;
import entity.User;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

public class MsgViewAdapter extends ArrayAdapter<Chat> {
	private int layoutResource;
	private String currentUser;
	public MsgViewAdapter(Context context, int resource, List<Chat> objects,String currentUser) {
		super(context, resource, objects);
		this.layoutResource = resource;
		this.currentUser=currentUser;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;

		if (view == null) {
			LayoutInflater linf = LayoutInflater.from(getContext());
			view = linf.inflate(layoutResource, null);
		}
		Chat chat = getItem(position);
		if (chat != null) {
			TextView textv = (TextView) view.findViewById(R.id.textv);
			textv.setText(chat.getMessage());
			
			if(chat.getMsgFrom().getUserid().equals(currentUser)){
				textv.setGravity(Gravity.RIGHT);
			}else{
				textv.setGravity(Gravity.LEFT);
			}
		}
		
		
		
		return view;
	}
}
