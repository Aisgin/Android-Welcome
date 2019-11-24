package com.zhbit.login;

import com.zhbit.dao.User;
import com.zhbit.hellowelcome.R;
import com.zhbit.sql.MyDatabaseOpenHelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OperateSQLite extends Activity implements OnClickListener {
	
	private EditText etUser, etPwd;
	private Button intert, delete, update, query;
	private Context mcontext;
	private MyDatabaseOpenHelper helper;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		mcontext = this;
		initView();
		helper = MyDatabaseOpenHelper.getInstance(this);
	}
	
	private void initView(){
		etUser = (EditText) findViewById(R.id.et_sqlite_User);
		etPwd = (EditText) findViewById(R.id.et_sqlite_Pwd);
		intert = (Button) findViewById(R.id.btn_sqlite_intert);
		delete = (Button) findViewById(R.id.btn_sqlite_delete);
		update = (Button) findViewById(R.id.btn_sqlite_update);
		query = (Button) findViewById(R.id.btn_sqlite_query);
		intert.setOnClickListener(this);
		delete.setOnClickListener(this);
		update.setOnClickListener(this);
		query.setOnClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
//	private void message(String mes) {
//		Toast toast = Toast.makeText(mcontext, mes,
//				Toast.LENGTH_SHORT);
//		toast.show();
//	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_sqlite_intert:
			intert();
			break;
		case R.id.btn_sqlite_delete:
			delete();
			break;
		case R.id.btn_sqlite_update:
			update();
			break;
		case R.id.btn_sqlite_query:
			query();
			break;

		default:
			break;
		}
	}
	private void intert(){
		User user = new User();
		user.setName(etUser.getText().toString().trim());
		user.setPwd(etPwd.getText().toString().trim());
		helper.insertData(user);
		
	}
	private void delete() {
		User user = new User();
		user.setName(etUser.getText().toString().trim());
		user.setPwd(etPwd.getText().toString().trim());
		helper.deleteData(user);
		
	}

	private void update() {
		User user = new User();
		user.setName(etUser.getText().toString().trim());
		user.setPwd(etPwd.getText().toString().trim());
		int number = helper.updateData(user);
		if (number != -1){
			message("修改成功");
		} else {
			message("修改失败");
		}
	}

	private void query() {
		User user = new User();
		user.setName(etUser.getText().toString().trim());
		user.setPwd(etPwd.getText().toString().trim());
		String pwd = helper.queryData(user);
		message(pwd);
	}
	
	private void message(String mes) {
		Toast toast = Toast.makeText(mcontext, mes,
				Toast.LENGTH_SHORT);
		toast.show();
	}
	
	
}
