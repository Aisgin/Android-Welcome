package com.zhbit.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import com.zhbit.bean.User;

/**
 * ���ݿ����� MySQLiteOpenHelperStudent ������ ���ɴ򿪾ݿ� / ���ɴ򿪱� / ������ / ��ɴ����ݿ�����ɾ�Ĳ��߼�����
 * / �����������������ӵȵȣ�
 */
public class DBHelper extends SQLiteOpenHelper {

	//�õ���ļ�д����
	private final static String TAG = DBHelper.class.getSimpleName();

	//���ݿ�����
	private final static String DB_NAME = "androidlogin.db";
	
	//���ݿ�汾
	private static final int version = 1;

	//����
	private static final String USER_NAME = "user";
	
	private Context mcontext;

	//���嵥��ģʽ�����Ա���εط���ε���
	private static DBHelper mySQLiteOpenHelperStudent;

	// ��ȡΨһ���ö���
	public static DBHelper getInstance(Context mcontext) {
		if (null == mySQLiteOpenHelperStudent) {
			synchronized (DBHelper.class) {
				if (null == mySQLiteOpenHelperStudent) {
					mySQLiteOpenHelperStudent = new DBHelper(mcontext,DB_NAME,null,1);
				}
			}
		}
		return mySQLiteOpenHelperStudent;
	}

	public DBHelper(Context mcontext, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(mcontext, name, factory, version);
		this.mcontext = mcontext;
	}

	/**
	 * �̳�SQLiteOpenHelper������ ��д�Ĵ���������SQLiteDatabase db ���ܹر�, û�����ݿ���ڲŻ�ִ��
	 * @param db
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Log","û�����ݿ�,�������ݿ�");
		// ����User��
		String sql_message = "CREATE TABLE IF NOT EXISTS " + USER_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NOT NULL, pwd VARCHAR(100) NOT NULL);";
		db.execSQL(sql_message);
	}

	/**
	 * �̳�SQLiteOpenHelper������ ��д����������, ���ݿ���²Ż�ִ��
	 * 
	 * @param db
	 * @param oldVersion
	 * @param newVersion
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("updateLog","���ݿ�����ˣ�");
	}

	/**
	 * ���뵥����Ϣ
	 * 
	 * @param user
	 *            ����Userʵ��
	 */
	public void insertData(User user) {
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getWritableDatabase();
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", user.getName());
			contentValues.put("pwd", user.getPwd());
			database.insert(USER_NAME, "_id", contentValues);
			message("����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "insert�����쳣��" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}

	/**
	 * �����������
	 * 
	 * @param users
	 *            ����User����
	 */
	public void insertData(List<User> users) {
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getWritableDatabase();
		try {
			for (User user : users) {
				ContentValues contentValues = new ContentValues();
				contentValues.clear();
				contentValues.put("name", user.getName());
				contentValues.put("pwd", user.getPwd());
				database.insert(USER_NAME, null, contentValues);
				message("����ɹ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "insert�����쳣��" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}

	/**
	 * ɾ����������
	 * 
	 * @param user
	 *            ����Userʵ���� usernameΨһ
	 */
	public int deleteData(User user) {
		int number = -1;
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getWritableDatabase();
		try {
			String[] names = { user.getName() };
			number = database.delete(USER_NAME, "name=?", names);
			message("ɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "delete�����쳣��" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
		return number;
	}

	/**
	 * ɾ����������
	 * 
	 * @param users
	 *            ����User����
	 */
	public int deleteData(List<User> users) {
		int number = -1;
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getWritableDatabase();
		try {
			ArrayList<String> nameList = new ArrayList<String>();
			for (User user : users) {
				nameList.add(user.getName());
			}
			String[] names = (String[]) nameList.toArray();
			// delete()�Ƿ��һ��ɾ��������
			number = database.delete(USER_NAME, "name=?", names);
			message("ɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "delete�����쳣��" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
		return number;
	}

	/**
	 * �޸ĵ�������
	 * 
	 * @param user
	 *            ����Userʵ���� usernameΨһ
	 */
	public int updateData(User user) {
		int number = -1;
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getWritableDatabase();
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", user.getName());
			contentValues.put("pwd", user.getPwd());
			String[] names = { user.getName() };
			number = database.update(USER_NAME, contentValues, "name=?", names);
			message("���ĳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "update�����쳣��" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
		return number;
	}
	
	/**
	 * ��������
	 * 
	 * @param user
	 * 
	 * query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
	 * table ����,
	 * columns ��Ҫ��ʾ���ֶ�,
	 * selection ����,
	 * selectionArgs selection��ֵ,
	 * groupBy ����,
	 * having ��������,
	 * orderBy ����,
	 * limit ��ҳ
	 */
	public String queryData (User user){
		String pwd = "";
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getReadableDatabase();
		try {
			String[] names = { user.getName() };
			Cursor cursor = database.query(USER_NAME, null, "name=?", names, null, null, null);
			if(cursor.moveToNext()){
				int pwdIndex = cursor.getColumnIndex("pwd");
				pwd = cursor.getString(pwdIndex);
			}
			message(pwd);
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "query�����쳣��" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
		return pwd;
	}
	
	/**
	 * ��ӡ user ��ȫ������
	 * @return
	 */
	
	public List<User> queryUsers(){
		System.out.println("SQLite of queryUsers");
		List<User> users = new ArrayList<User>();
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getReadableDatabase();
		try {
//			String[] names = { null };
			Cursor cursor = database.rawQuery("select * from user",null);
			while(cursor.moveToNext()){
				User user = new User();
				int nameIndex = cursor.getColumnIndex("name");
				int pwdIndex = cursor.getColumnIndex("pwd");
//				if (cursor.getString(nameIndex) == null
//						|| cursor.getString(pwdIndex) == null) {
//					continue;
//				}
				user.setName(cursor.getString(nameIndex));
				user.setPwd(cursor.getString(pwdIndex));
				users.add(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "query�����쳣��" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
		
		for(int i=0;i<users.size();i++){
			System.out.println(users.get(i).getName());
			System.out.println(users.get(i).getPwd());
		}
		
		return users;
		
	}
	private void message(String mes) {
		Toast toast = Toast.makeText(mcontext, mes,
				Toast.LENGTH_SHORT);
		toast.show();
	}
	
}
