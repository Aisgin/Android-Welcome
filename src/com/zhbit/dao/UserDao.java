package com.zhbit.dao;

import com.zhbit.utils.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	DBHelper dbHepler;
	SQLiteDatabase db;

	public UserDao(Context context) {
		dbHepler = new DBHelper(context);
	}

	/**
	 * 用户登陆验证
	 * @param name
	 * @param pwd
	 * @return
	 * 利用传入的用户名、密码进行数据库，若存在则返回对应User对象，否则User为空
	 */
	public User login(String name, String pwd) {
		// 调用DBHelper 需要Context
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
