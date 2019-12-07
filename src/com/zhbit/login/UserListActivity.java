package com.zhbit.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhbit.dao.User;
import com.zhbit.hellowelcome.R;
import com.zhbit.sql.MyDatabaseOpenHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class UserListActivity extends Activity implements OnItemClickListener,
		OnClickListener {
	private ListView listView;
	private String[] from = { "name", "pwd" };
	private int[] to = { R.id.tv_userlist_name, R.id.tv_userlist_pwd };
	private Button btnBack;
	private MyDatabaseOpenHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		System.out.print("UserListActivity be start");
		setContentView(R.layout.activity_userlist);
		helper = MyDatabaseOpenHelper.getInstance(this);
		initView();
	}

	private void initView() {
		btnBack = (Button) findViewById(R.id.btn_userlist_back);
		listView = (ListView) findViewById(R.id.info_users);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(),
				R.layout.activity_userlist_item, from, to);
		listView.setAdapter(simpleAdapter);
		btnBack.setOnClickListener(this);
		// listView.setOnItemClickListener(this);
		
	}

	private List<Map<String, ?>> getData() {
		List<Map<String, ?>> list = new ArrayList<Map<String, ?>>();
		List<User> users = new ArrayList<User>();
		List<User> cusers = (ArrayList<User>)helper.queryUsers();
//		users = (ArrayList<User>)helper.queryUsers().clone();
	
		for( User cuser : cusers){
			System.out.print(cuser.getName());
			if(cuser.getName()!=null && cuser.getPwd()!=null){
				users.add(cuser);
			}
			
		}
		if(users.size()>0){
			for (int i = 0; i < users.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(from[0], users.get(i).getName());
				map.put(from[1], users.get(i).getPwd());
				list.add(map);
			}
		}
		System.out.print("****************"+users.size());
		return list;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void message(String mes) {
		Toast toast = Toast.makeText(getApplicationContext(), mes,
				Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(UserListActivity.this, ListActivity.class);
		startActivity(intent);
		UserListActivity.this.finish();
	}

}
