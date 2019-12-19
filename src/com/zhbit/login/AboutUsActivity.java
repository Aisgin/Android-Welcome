package com.zhbit.login;

import com.zhbit.hellowelcome.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * 个人中心 - 关于我们界面activity
 * 显示作者、联系方式、老师
 * @author lenat
 *
 */
public class AboutUsActivity extends Activity implements OnClickListener {
	private Button btnBack;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutus);
		initView();
	}

	private void initView() {
		btnBack = (Button) findViewById(R.id.btn_aboutus_back);
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
		Intent intent = new Intent(AboutUsActivity.this, ListActivity.class);
		startActivity(intent);
		AboutUsActivity.this.finish();
	}
}
