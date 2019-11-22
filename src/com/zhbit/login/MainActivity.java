package com.zhbit.login;

import com.zhbit.hellowelcome.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements OnClickListener {
	
	public Button bt1,bt2;
	public EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 根据id找到元素
        bt1 = (Button)findViewById(R.id.bt1);	// 获取拨打电话按钮组件
        et1 = (EditText)findViewById(R.id.et1);	// 获取电话号码输入文本框
        bt2 = (Button)findViewById(R.id.bt2);	// 获取发送短讯按钮组件
        et2 = (EditText)findViewById(R.id.et2);	// 获取短讯内容输入文本框
        bt1.setOnClickListener(this);			//设置点击监听
        bt2.setOnClickListener(this);
//        onPhone();								// 实现拨号功能
//        onSMS();								// 实现短讯功能  
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
//	方法一 标签绑定onClick监听，实现方法
//	button标签绑定onClick, 不用写监听, 必须传一个View视图
//	public void onPhone(View v){
//		String num = et1.getText().toString();	// 获取电话号码输入框内容
//    	Intent intent = new Intent();			// 创建一个意图	
//    	intent.setAction(Intent.ACTION_DIAL);	// 拨打电话
//    	intent.setData(Uri.parse("tel:"+num));	// 将输入框中的文本自动填写
//    	startActivity(intent);					// 启动界面
//	}
	
//  方法二 匿名内部类
//	private void onPhone(){
//    	bt1.setOnClickListener(new View.OnClickListener() {
//    		@Override
//    	        public void onClick(View v) {
//    			String num = et1.getText().toString();	// 获取电话号码输入框内容
//    	    	Intent intent = new Intent();			// 创建一个意图	
//    	    	intent.setAction(Intent.ACTION_CALL);	// 拨打电话
//    	    	intent.setData(Uri.parse("tel:"+num));	// 将输入框中的文本自动填写
//    	    	startActivity(intent);					// 启动界面
//    	        }
//    	    });
//    }
	
//	方法三  MainActivity实现OnClickListener接口
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		String num = et1.getText().toString();	// 获取电话号码输入框内容
//    	Intent intent = new Intent();			// 创建一个意图	
//    	intent.setAction(Intent.ACTION_CALL);	// 拨打电话
//    	intent.setData(Uri.parse("tel:"+num));	// 将输入框中的文本自动填写
//    	startActivity(intent);					// 启动界面
//	}
	
	@Override
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.bt1:
			onPhone();
			break;
		case R.id.bt2:
			onSMS();
			break;
		default:
			break;
		}
	}
    
	public void onPhone() {
		String num = et1.getText().toString();	// 获取电话号码输入框内容
		Intent intent = new Intent();			// 创建一个意图	
		intent.setAction(Intent.ACTION_DIAL);	// 拨打电话
		intent.setData(Uri.parse("tel:"+num));	// 将输入框中的文本自动填写
		startActivity(intent);					// 启动界面
	}
	
	private void onSMS() {
    	String num = et1.getText().toString();		// 获取电话号码输入框内容内容
    	String massage = et2.getText().toString();	// 获取短讯内容输入框内容内容
    	Intent intent = new Intent();				// 创建一个意图
    	intent.setAction(Intent.ACTION_SENDTO);		// 发送短讯
    	intent.setData(Uri.parse("smsto:"+num));	//	收讯人
    	intent.putExtra("sms_body", massage);		// 短讯内容
    	startActivity(intent);						// 启动界面
	}
    
//	private void onSMS() {
//    	bt2.setOnClickListener(new View.OnClickListener() {
//    		@Override
//    	        public void onClick(View v) {
//    			String num = et1.getText().toString();		// 获取电话号码输入框内容内容
//    			String massage = et2.getText().toString();	// 获取短讯内容输入框内容内容
//    	    	Intent intent = new Intent();				// 创建一个意图
//    	    	intent.setAction(Intent.ACTION_SENDTO);		// 发送短讯
//    	    	intent.setData(Uri.parse("smsto:"+num));	//	收讯人
//    	    	intent.putExtra("sms_body", massage);		// 短讯内容
//    	    	startActivity(intent);						// 启动界面
//    	        }
//    	    });
//	}

}


