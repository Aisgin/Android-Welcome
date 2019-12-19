package com.zhbit.login;

import com.zhbit.bean.User;
import com.zhbit.hellowelcome.R;
import com.zhbit.utils.UserInfoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 个人中心Activity
 * 显示用户头像、名字信息及操作列表
 * @author lenat
 *
 */
public class ListActivity extends Activity implements OnItemClickListener {
	private ListView listView;
	private TextView nameText;
	private String[] from = { "img", "text" };
	private int[] to = { R.id.iv, R.id.tv };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_list);

		initView();
	}

	private void initView() {

		listView = (ListView) findViewById(R.id.listView1);
		nameText = (TextView) findViewById(R.id.info_username);
//		Intent intent = getIntent();
//		User user = (User) intent.getSerializableExtra("user");
//		nameText.setText(user.getName());
		UserInfoUtils userinfoutiles = new UserInfoUtils();
//		User user = userinfoutiles.getInfoForLocal(mcontext);
		User user = userinfoutiles.getInfoForSDCard(this);
		nameText.setText(user.getName());
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(),
				R.layout.activity_list_item, from, to);
		listView.setAdapter(simpleAdapter);
		listView.setOnItemClickListener(this);
	}

	private List<Map<String, ?>> getData() {
		List<Map<String, ?>> list = new ArrayList<Map<String, ?>>();
		String[] listString = getResources().getStringArray(
				R.array.list_listString);
		int[] imageIds = { R.drawable.s1, R.drawable.s2, R.drawable.s3,
				R.drawable.s4, R.drawable.s5 };
		for (int i = 0; i < listString.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		message(position + "@@@" + id);
		switch (position) {
		case 0:
			break;

		case 1:
			break;
		case 2:
			break;
		case 3:
			toAboutUsActivity();
			break;
		case 4:
			toLoginActivity();
			break;
		default:
			message("错误点击，请联系我们");
			break;
		}
	}
	
	private void toAboutUsActivity(){
		Intent intent = new Intent(ListActivity.this, AboutUsActivity.class);
		startActivity(intent);
		ListActivity.this.finish();
	}

	private void toLoginActivity() {
		Intent intent = new Intent(ListActivity.this, LoginActivity.class);
		startActivity(intent);
		ListActivity.this.finish();
	}
}
