package com.zhbit.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * ���ݿ����� MySQLiteOpenHelperStudent ������ ���ɴ򿪾ݿ� / ���ɴ򿪱� / ������ / ��ɴ����ݿ�����ɾ�Ĳ��߼�����
 * / �����������������ӵȵȣ�
 */
public class DBHelper extends SQLiteOpenHelper {

	// �õ���ļ�д����
	// private final static String TAG = DBHelper.class.getSimpleName();

	// ���ݿ�����
	private final static String DB_NAME = "androidlogin.db";

	// ���ݿ�汾
	private static final int version = 1;

	// ����
	private static final String USER_NAME = "user";

	// ���嵥��ģʽ�����Ա���εط���ε���
	private static DBHelper mySQLiteOpenHelperStudent;

	// ��ȡΨһ���ö���
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
	 * �̳�SQLiteOpenHelper������ ��д�Ĵ���������SQLiteDatabase db ���ܹر�, û�����ݿ���ڲŻ�ִ��
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Log", "û�����ݿ�,�������ݿ�");
		// ����User��
		String sql_message = "CREATE TABLE IF NOT EXISTS "
				+ USER_NAME
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NOT NULL, pwd VARCHAR(100) NOT NULL);";
		db.execSQL(sql_message);
	}

	/**
	 * �̳�SQLiteOpenHelper������ ��д����������, ���ݿ���²Ż�ִ��
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("updateLog", "���ݿ�����ˣ�");
	}

}
