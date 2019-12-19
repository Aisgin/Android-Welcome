package com.zhbit.login;

import com.zhbit.utils.UserInfoUtils;
import com.zhbit.bean.User;
import com.zhbit.dao.UserDao;
import com.zhbit.hellowelcome.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private ImageButton btnRegister, btnLogin;
	private EditText etUser, etPwd;
	private TextView tv_forgetpwd;
	private CheckBox cb_save;
	private Context mcontext;
	private UserDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		dao = new UserDao(this);
		mcontext = this;
		initView();
		getInfo();
	}

	private void initView() {
		etUser = (EditText) findViewById(R.id.etUser);
		etPwd = (EditText) findViewById(R.id.etPwd);
		btnRegister = (ImageButton) findViewById(R.id.imageBtnRegister);
		btnLogin = (ImageButton) findViewById(R.id.imageBtnLogin);
		cb_save = (CheckBox) findViewById(R.id.cb_save);
		tv_forgetpwd = (TextView) findViewById(R.id.tv_forgetpwd);
		btnRegister.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		tv_forgetpwd.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageBtnRegister:
			register();
			break;
		case R.id.imageBtnLogin:
			login();
			break;
		case R.id.tv_forgetpwd:
			forgetpwd();
			break;
		default:
			break;
		}
	}

	private void register() {
		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(intent);
		LoginActivity.this.finish();
	}

	private void login() {
		String userStr = etUser.getText().toString().trim();
		String pwdStr = etPwd.getText().toString().trim();
		if (TextUtils.isEmpty(userStr)) {
			message("请输入用户名");
		} else if (TextUtils.isEmpty(pwdStr)) {
			message("请输入密码为空");
		} else {
			if(checkPwd() == 1){
				message("登陆成功");
				User user = new User(userStr,userStr);
				boolean result = cb_save.isChecked(); // 勾选的状态
				if (result) {
					if(save(user)){
						message("保存成功！");
					} else {
						message("保存失败！");
					}	
				}
				Intent intent = new Intent(LoginActivity.this,
						ListActivity.class);
				intent.putExtra("user", user);
				startActivity(intent);
				LoginActivity.this.finish();
			}else{
				message("登陆失败，密码错误或用户名未注册");
			}
			
		}
	}
	
	private int checkPwd(){
		int result = -1;
		User user = new User();
		user.setName(etUser.getText().toString().trim());
		String pwd = dao.queryData(user);
		if ( pwd.equals(etPwd.getText().toString().trim())){
			result = 1;
		}
		return result;
	}
	
	private void forgetpwd(){
		message("点击了忘记密码");
	}

	private boolean save(User user){
		UserInfoUtils userinfoutils = new UserInfoUtils();
//		return userinfoutils.saveInfoForLocal(mcontext,user);
		return userinfoutils.saveInfoForSDCard(mcontext,user);
	}
//	private void save(User user) {
//		BufferedWriter bufferdWriter = null;
//		try {
//			FileOutputStream outputStream = openFileOutput("data", Activity.MODE_PRIVATE);
//			bufferdWriter = new BufferedWriter(new OutputStreamWriter(
//					outputStream));
//			bufferdWriter.write(user.getName());
//			bufferdWriter.write(user.getPwd());
//			outputStream.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (bufferdWriter != null) {
//				try {
//					bufferdWriter.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
	private void getInfo() {
		UserInfoUtils userinfoutiles = new UserInfoUtils();
//		User user = userinfoutiles.getInfoForLocal(mcontext);
		User user = userinfoutiles.getInfoForSDCard(mcontext);
		etUser.setText(user.getName());
		etPwd.setText(user.getPwd());
	}
	
	private void message(String mes) {
		Toast toast = Toast.makeText(mcontext, mes,
				Toast.LENGTH_SHORT);
		toast.show();
	}
}
