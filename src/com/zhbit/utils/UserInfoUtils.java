package com.zhbit.utils;

import com.zhbit.dao.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.text.format.Formatter;
import android.widget.Toast;

public class UserInfoUtils {

	public boolean saveInfoForLocal(Context mcontext, User user) {
		// �������
		String path = mcontext.getFilesDir().toString();
		File file = new File(path, "info.txt");
		String info = user.getName() + "#" + user.getPwd();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(info.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public User getInfoForLocal(Context mcontext) {
		User user = new User();
		String path = mcontext.getFilesDir().toString();
		File file = new File(path, "info.txt");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String mes = br.readLine();
			// �ָ��û���������
			String[] splits = mes.split("#");
			String name = splits[0];
			String pwd = splits[1];
			user.setName(name);
			user.setPwd(pwd);
			fis.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// public User getInfoForLocal(){
	// User user = new User();
	// File file = new File("/data/data/com.zhbit.hellowelcome/","info.txt");
	// Reader r = null;
	// char[] ch=new char[(int)file.length()];
	// int temp,i=0;
	// // int len = 0;
	// try {
	// r = new FileReader(file);
	// //len = r.read(ch);//һ���Զ�ȡ���ַ�����
	// while((temp=r.read())!=-1) {//ÿ��ֻ�ܶ�ȡһ���ַ���ͨ��ѭ���ķ�ʽ���뵽�ַ�������
	// ch[i++] = (char)temp;
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally{
	// try {
	// r.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// String str = new String(ch,0,i);
	// String[] userinfo = str.split("#");
	// user.setName(userinfo[0]);
	// user.setPwd(userinfo[1]);
	// return user;
	// }

	public boolean saveInfoForSDCard(Context mcontext, User user) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File sdcard_filedir = Environment.getExternalStorageDirectory();
			long usableSpace = sdcard_filedir.getUsableSpace();
			// long totalSpace = sdcard_filedir.getTotalSpace();
			// ��һ��long���͵��ļ���С��ʽ�����û����Կ�����M��G�ַ���
			String usableSpace_str = Formatter.formatFileSize(mcontext,
					usableSpace);
			// String totalSpace_str = Formatter.formatFileSize(mcontext,
			// totalSpace);
			if (usableSpace < 1024 * 1024 * 20) {
				message(mcontext, "sdcardʣ��ռ䲻��,�޷��������أ�ʣ��ռ�Ϊ��"
						+ usableSpace_str);
			} else {
				// ʹ��sharedPreferences�洢����
				// 1.ͨ��Context���󴴽�һ��SharedPreference����
				// name:sharedpreference�ļ������� mode:�ļ��Ĳ���ģʽ
				SharedPreferences sharedPreferences = mcontext
						.getSharedPreferences("userinfo.txt",
								Context.MODE_PRIVATE);
				// 2.ͨ��sharedPreferences�����ȡһ��Editor����
				Editor editor = sharedPreferences.edit();
				// 3.��Editor���������
				editor.putString("username", user.getName());
				editor.putString("password", user.getPwd());
				// 4.�ύEditor����
				editor.commit();
				return true;
			}
		} else {
			message(mcontext, "û���ҵ�SDCardamdin");
		}
		return false;
	}

	public User getInfoForSDCard(Context mcontext) {
		User user = new User();
		// ʹ��sharedPreferences��ȡ����
		// 1.ͨ��Context���󴴽�һ��SharedPreference����
		SharedPreferences sharedPreferences = mcontext.getSharedPreferences(
				"userinfo.txt", Context.MODE_PRIVATE);
		// 2.ͨ��sharedPreference��ȡ��ŵ�����
		// key:�������ʱ��key defValue: Ĭ��ֵ,����ҵ��������д
		String username = sharedPreferences.getString("username", "");
		String password = sharedPreferences.getString("password", "");
		// ͨ��PreferenceManager���Ի�ȡһ��Ĭ�ϵ�sharepreferences����
		// SharedPreferences sharedPreferences =
		// PreferenceManager.getDefaultSharedPreferences(context);
		user.setName(username);
		user.setPwd(password);
		return user;
	}

	private void message(Context mcontext, String mes) {
		Toast toast = Toast.makeText(mcontext, mes, Toast.LENGTH_SHORT);
		toast.show();
	}
}
