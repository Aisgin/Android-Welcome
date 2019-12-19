package com.zhbit.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库表管理 MySQLiteOpenHelperStudent （负责 生成打开据库 / 生成打开表 / 升级表 / 完成此数据库表的增删改查逻辑处理
 * / 还有其他表处理功能增加等等）
 */
public class DBHelper extends SQLiteOpenHelper {

	// 得到类的简写名称
	// private final static String TAG = DBHelper.class.getSimpleName();

	// 数据库名称
	private final static String DB_NAME = "androidlogin.db";

	// 数据库版本
	private static final int version = 1;

	// 表名
	private static final String USER_NAME = "user";

	// 定义单例模式，可以被多次地方多次调用
	private static DBHelper mySQLiteOpenHelperStudent;

	// 获取唯一可用对象
	public static synchronized DBHelper getInstance(Context mcontext) {
		if (null == mySQLiteOpenHelperStudent) {
			mySQLiteOpenHelperStudent = new DBHelper(mcontext, DB_NAME, null,
					version);
		}
		return mySQLiteOpenHelperStudent;
	}

	public DBHelper(Context mcontext, String name,
			SQLiteDatabase.CursorFactory factory, int version) {
		super(mcontext, name, factory, version);
	}

	/**
	 * 继承SQLiteOpenHelper抽象类 重写的创表方法，此SQLiteDatabase db 不能关闭, 没有数据库存在才会执行
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Log", "没有数据库,创建数据库");
		// 创建User表
		String sql_message = "CREATE TABLE IF NOT EXISTS "
				+ USER_NAME
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NOT NULL, pwd VARCHAR(100) NOT NULL);";
		db.execSQL(sql_message);
	}

	/**
	 * 继承SQLiteOpenHelper抽象类 重写的升级方法, 数据库更新才会执行
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("updateLog", "数据库更新了！");
	}

}
