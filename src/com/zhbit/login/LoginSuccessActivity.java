package com.zhbit.login;


import com.zhbit.bean.User;
import com.zhbit.hellowelcome.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginSuccessActivity extends Activity implements OnClickListener {
	
	
	private String name,pwd;
	private TextView tvUser,tvPwd;
	private Button btnToLogin;
	private Button sqlTest;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        initView();
	}
	
	private void initView(){
        tvUser = (TextView)findViewById(R.id.tvUser);
        tvPwd = (TextView)findViewById(R.id.tvPwd);
        btnToLogin = (Button)findViewById(R.id.btnToLogin);
        sqlTest = (Button)findViewById(R.id.sqltest);
        sqlTest.setOnClickListener(this);
        btnToLogin.setOnClickListener(this);
        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("user");
        setText(user);
	}
	
	private void setText(User user){
		tvUser.setText(user.getName());
        tvPwd.setText(user.getPwd());
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
        
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()== R.id.sqltest){
			Intent intent = new Intent(LoginSuccessActivity.this, OperateSQLite.class);
			startActivity(intent);
			LoginSuccessActivity.this.finish();
		}else{
			Intent intent = new Intent(LoginSuccessActivity.this, LoginActivity.class);
			startActivity(intent);
			LoginSuccessActivity.this.finish();
		}
		
	}
}


