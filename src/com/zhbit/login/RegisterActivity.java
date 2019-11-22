package com.zhbit.login;

import com.zhbit.hellowelcome.R;

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
	
	private EditText tvUser,tvPwd;
	private Button btnResetting,btnSubmit,btnBack;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvUser = (EditText)findViewById(R.id.etRUser);
        tvPwd = (EditText)findViewById(R.id.etRPwd);
        btnResetting = (Button)findViewById(R.id.imageBtnResetting);
        btnSubmit = (Button)findViewById(R.id.imageBtnSubmit);
        btnBack = (Button)findViewById(R.id.btn_reg_back);
        btnResetting.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnBack.setOnClickListener(this);
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
		tvUser.setText("");
		tvPwd.setText("");
	}
	
	private void submit(){
		String userStr="",pwdStr="";
		userStr = tvUser.getText().toString();
		pwdStr = tvPwd.getText().toString();
		if(userStr.equals("")){
			Toast toast=Toast.makeText(getApplicationContext(),"µÇÂ½Ê§°Ü£¬ÓÃ»§ÃûÎª¿Õ", Toast.LENGTH_SHORT);
			toast.show();			
		}else if(pwdStr.equals("")){
			Toast toast=Toast.makeText(getApplicationContext(),"µÇÂ½Ê§°Ü£¬ÃÜÂëÎª¿Õ", Toast.LENGTH_SHORT);
			toast.show();
		}else{
			Toast toast=Toast.makeText(getApplicationContext(),"×¢²á³É¹¦", Toast.LENGTH_SHORT);
			toast.show();
			Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
			startActivity(intent);
			RegisterActivity.this.finish();
		}
	}
	
	private void back(){
		Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(intent);
		RegisterActivity.this.finish();
	}
}


