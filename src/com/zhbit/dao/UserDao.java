package com.zhbit.dao;

import com.zhbit.bean.User;
import com.zhbit.utils.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	DBHelper dbHepler;
	SQLiteDatabase db;

	public UserDao(Context context) {
		dbHepler = DBHelper.getInstance(context);;
	}

	/**
	 * �û���½��֤
	 * @param name
	 * @param pwd
	 * @return
	 * ���ô�����û���������������ݿ⣬�������򷵻ض�ӦUser���󣬷���UserΪ��
	 */
	public User login(String name, String pwd) {
		// ����DBHelper ��ҪContext
		User user = null;
		db = dbHepler.getReadableDatabase();
		String sql = "select * from info where name=? and phone =?";
		// db.execSQL(sql, new String[]{name,pwd});
		Cursor cursor = db.rawQuery(sql, new String[] { name, pwd });
		if (cursor != null && cursor.getCount() > 0) {
			int id = cursor.getInt(0);
			String cname = cursor.getString(1);
			String cpwd = cursor.getString(2);
			user = new User(cname, cpwd);
		}
		return user;
	}
}
