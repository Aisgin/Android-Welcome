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
        // ����id�ҵ�Ԫ��
        bt1 = (Button)findViewById(R.id.bt1);	// ��ȡ����绰��ť���
        et1 = (EditText)findViewById(R.id.et1);	// ��ȡ�绰���������ı���
        bt2 = (Button)findViewById(R.id.bt2);	// ��ȡ���Ͷ�Ѷ��ť���
        et2 = (EditText)findViewById(R.id.et2);	// ��ȡ��Ѷ���������ı���
        bt1.setOnClickListener(this);			//���õ������
        bt2.setOnClickListener(this);
//        onPhone();								// ʵ�ֲ��Ź���
//        onSMS();								// ʵ�ֶ�Ѷ����  
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
//	����һ ��ǩ��onClick������ʵ�ַ���
//	button��ǩ��onClick, ����д����, ���봫һ��View��ͼ
//	public void onPhone(View v){
//		String num = et1.getText().toString();	// ��ȡ�绰�������������
//    	Intent intent = new Intent();			// ����һ����ͼ	
//    	intent.setAction(Intent.ACTION_DIAL);	// ����绰
//    	intent.setData(Uri.parse("tel:"+num));	// ��������е��ı��Զ���д
//    	startActivity(intent);					// ��������
//	}
	
//  ������ �����ڲ���
//	private void onPhone(){
//    	bt1.setOnClickListener(new View.OnClickListener() {
//    		@Override
//    	        public void onClick(View v) {
//    			String num = et1.getText().toString();	// ��ȡ�绰�������������
//    	    	Intent intent = new Intent();			// ����һ����ͼ	
//    	    	intent.setAction(Intent.ACTION_CALL);	// ����绰
//    	    	intent.setData(Uri.parse("tel:"+num));	// ��������е��ı��Զ���д
//    	    	startActivity(intent);					// ��������
//    	        }
//    	    });
//    }
	
//	������  MainActivityʵ��OnClickListener�ӿ�
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		String num = et1.getText().toString();	// ��ȡ�绰�������������
//    	Intent intent = new Intent();			// ����һ����ͼ	
//    	intent.setAction(Intent.ACTION_CALL);	// ����绰
//    	intent.setData(Uri.parse("tel:"+num));	// ��������е��ı��Զ���д
//    	startActivity(intent);					// ��������
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
		String num = et1.getText().toString();	// ��ȡ�绰�������������
		Intent intent = new Intent();			// ����һ����ͼ	
		intent.setAction(Intent.ACTION_DIAL);	// ����绰
		intent.setData(Uri.parse("tel:"+num));	// ��������е��ı��Զ���д
		startActivity(intent);					// ��������
	}
	
	private void onSMS() {
    	String num = et1.getText().toString();		// ��ȡ�绰�����������������
    	String massage = et2.getText().toString();	// ��ȡ��Ѷ�����������������
    	Intent intent = new Intent();				// ����һ����ͼ
    	intent.setAction(Intent.ACTION_SENDTO);		// ���Ͷ�Ѷ
    	intent.setData(Uri.parse("smsto:"+num));	//	��Ѷ��
    	intent.putExtra("sms_body", massage);		// ��Ѷ����
    	startActivity(intent);						// ��������
	}
    
//	private void onSMS() {
//    	bt2.setOnClickListener(new View.OnClickListener() {
//    		@Override
//    	        public void onClick(View v) {
//    			String num = et1.getText().toString();		// ��ȡ�绰�����������������
//    			String massage = et2.getText().toString();	// ��ȡ��Ѷ�����������������
//    	    	Intent intent = new Intent();				// ����һ����ͼ
//    	    	intent.setAction(Intent.ACTION_SENDTO);		// ���Ͷ�Ѷ
//    	    	intent.setData(Uri.parse("smsto:"+num));	//	��Ѷ��
//    	    	intent.putExtra("sms_body", massage);		// ��Ѷ����
//    	    	startActivity(intent);						// ��������
//    	        }
//    	    });
//	}

}


