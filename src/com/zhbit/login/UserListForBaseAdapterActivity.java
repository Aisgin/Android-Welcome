package com.zhbit.login;

import java.util.ArrayList;

import com.zhbit.bean.User;
import com.zhbit.dao.UserDao;
import com.zhbit.hellowelcome.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UserListForBaseAdapterActivity extends Activity {
	Context mContext;
	ListView lv;
	UserDao dao;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userlist_baseadapter);
		lv = (ListView) findViewById(R.id.userlist_baseadapter);
		dao = new UserDao(this);
		mContext = this;
		MyAdapter adapter = new MyAdapter();
		lv.setAdapter(adapter);
		
	}
	class MyAdapter extends BaseAdapter{
		ArrayList<User> users = (ArrayList<User>)dao.queryUsers();
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return users.size();
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = null;
			if(convertView !=null){
				view = (TextView) convertView;
			}
			else{
				view = new TextView(mContext);
				view.setText(users.get(position).getName());
			}
			return view;
		}
		
	}

}
