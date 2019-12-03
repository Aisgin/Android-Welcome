package com.zhbit.login;

import com.zhbit.hellowelcome.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

	private int total_time = 3;
	private TextView tv_overtime;
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				tv_overtime.setText((total_time--) + "s");
				if (total_time > 0) {
					Message message = handler.obtainMessage(1);
					handler.sendMessageDelayed(message, 1000); // send message
				} else {
					// ��ת��������
					Intent intent = new Intent(WelcomeActivity.this,
							ListActivity.class);
					startActivity(intent);
					WelcomeActivity.this.finish();
				}
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȥ������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_welcome);
		tv_overtime = (TextView) findViewById(R.id.tv_overtime);
		Message message = handler.obtainMessage(1);
		handler.sendMessageDelayed(message, 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
