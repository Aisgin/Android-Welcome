package com.zhbit.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zhbit.bean.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.text.format.Formatter;
import android.widget.Toast;

public class UserInfoUtils {

	public boolean saveInfoForLocal(Context mcontext, User user) {
		// 减少耦合
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
			// 分割用户名、密码
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
	// //len = r.read(ch);//一次性读取到字符数组
	// while((temp=r.read())!=-1) {//每次只能读取一个字符，通过循环的方式放入到字符数组中
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
			// 将一个long类型的文件大小格式化成用户可以看懂的M，G字符串
			String usableSpace_str = Formatter.formatFileSize(mcontext,
					usableSpace);
			// String totalSpace_str = Formatter.formatFileSize(mcontext,
			// totalSpace);
			if (usableSpace < 1024 * 1024 * 20) {
				message(mcontext, "sdcard剩余空间不足,无法满足下载；剩余空间为："
						+ usableSpace_str);
			} else {
				// 使用sharedPreferences存储数据
				// 1.通过Context对象创建一个SharedPreference对象
				// name:sharedpreference文件的名称 mode:文件的操作模式
				SharedPreferences sharedPreferences = mcontext
						.getSharedPreferences("userinfo.txt",
								Context.MODE_PRIVATE);
				// 2.通过sharedPreferences对象获取一个Editor对象
				Editor editor = sharedPreferences.edit();
				// 3.往Editor中添加数据
				editor.putString("username", user.getName());
				editor.putString("password", user.getPwd());
				// 4.提交Editor对象
				editor.commit();
				return true;
			}
		} else {
			message(mcontext, "没有找到SDCardamdin");
		}
		return false;
	}

	public User getInfoForSDCard(Context mcontext) {
		User user = new User();
		// 使用sharedPreferences读取数据
		// 1.通过Context对象创建一个SharedPreference对象
		SharedPreferences sharedPreferences = mcontext.getSharedPreferences(
				"userinfo.txt", Context.MODE_PRIVATE);
		// 2.通过sharedPreference获取存放的数据
		// key:存放数据时的key defValue: 默认值,根据业务需求来写
		String username = sharedPreferences.getString("username", "");
		String password = sharedPreferences.getString("password", "");
		// 通过PreferenceManager可以获取一个默认的sharepreferences对象
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
