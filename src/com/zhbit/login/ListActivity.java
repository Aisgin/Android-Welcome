package com.zhbit.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhbit.hellowelcome.R;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ListActivity extends Activity implements OnItemClickListener {
	private ListView listView;
	private String[] from = {"img", "text"};
	private int[] to = {R.id.iv, R.id.tv};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_list);
		
		initView();
	}

	private void initView() {
		
		listView = (ListView)findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(),R.layout.activity_list_item,from,to);
		listView.setAdapter(simpleAdapter);
	}
	
	
	private List<Map<String, ?>> getData(){
		List<Map<String, ?>> list = new ArrayList<Map<String,?>>();
		String[] listString = getResources().getStringArray(R.array.list_listString);
		int[] imageIds = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5};
		for(int i=0;i<listString.length;i++){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put(from[0], imageIds[i]);
			map.put(from[1], listString[i]);
			list.add(map);
		}
		
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
