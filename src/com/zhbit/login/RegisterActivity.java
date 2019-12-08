package com.zhbit.login;


import com.zhbit.bean.User;
import com.zhbit.hellowelcome.R;
import com.zhbit.sql.MyDatabaseOpenHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends Activity implements OnClickListener {
	
	private EditText etUser,etPwd;
	private Button btnResetting,btnSubmit,btnBack;
	private MyDatabaseOpenHelper helper;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        helper = MyDatabaseOpenHelper.getInstance(this);
        initView();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
       
	private void initView(){
		etUser = (EditText)findViewById(R.id.etRUser);
        etPwd = (EditText)findViewById(R.id.etRPwd);
        btnResetting = (Button)findViewById(R.id.imageBtnResetting);
        btnSubmit = (Button)findViewById(R.id.imageBtnSubmit);
        btnBack = (Button)findViewById(R.id.btn_reg_back);
        btnResetting.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnBack.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageBtnResetting:
			resetting();
			break;
		case R.id.imageBtnSubmit:
			submit();
			break;
		case R.id.btn_reg_back:
			back();
			break;
		default:
			break;
		}
	}
	
	private void resetting(){
		etUser.setText("");
		etPwd.setText("");
	}
	
	private void submit(){
		String userStr="",pwdStr="";
		userStr = etUser.getText().toString();
		pwdStr = etPwd.getText().toString();
		if(userStr.equals("")){
			Toast toast=Toast.makeText(getApplicationContext(),"用户名不能为空", Toast.LENGTH_SHORT);
			toast.show();			
		}else if(pwdStr.equals("")){
			Toast toast=Toast.makeText(getApplicationContext(),"密码不能为空", Toast.LENGTH_SHORT);
			toast.show();
		}else{
			addUsertoSQLite();
			Toast toast=Toast.makeText(getApplicationContext(),"注册成功", Toast.LENGTH_SHORT);
			toast.show();
			Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
			startActivity(intent);
			RegisterActivity.this.finish();
		}
	}
	
	private void addUsertoSQLite(){
		User user = new User();
		user.setName(etUser.getText().toString().trim());
		user.setPwd(etPwd.getText().toString().trim());
		helper.insertData(user);
	}
	
	private void back(){
		Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(intent);
		RegisterActivity.this.finish();
	}
}


