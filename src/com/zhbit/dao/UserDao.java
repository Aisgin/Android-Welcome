package com.zhbit.dao;

import com.zhbit.bean.User;
import com.zhbit.utils.DBHelper;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	DBHelper dbHepler;
	SQLiteDatabase db;
	// ����
	private static final String USER_NAME = "user";

	public UserDao(Context context) {
		dbHepler = DBHelper.getInstance(context);
	}

	/**
	 * �û���½��֤
	 * 
	 * @param name
	 * @param pwd
	 * @return ���ô�����û���������������ݿ⣬�������򷵻ض�ӦUser���󣬷���UserΪ��
	 */
	public User login(String name, String pwd) {
		// ����DBHelper ��ҪContext
		User user = null;
		db = dbHepler.getReadableDatabase();
		String sql = "select * from info where name=? and phone =?";
		// db.execSQL(sql, new String[]{name,pwd});
		Cursor cursor = db.rawQuery(sql, new String[] { name, pwd });
		if (cursor != null && cursor.getCount() > 0) {
			// int id = cursor.getInt(0);
			String cname = cursor.getString(1);
			String cpwd = cursor.getString(2);
			user = new User(cname, cpwd);
		}
		return user;
	}

	/**
	 * ���뵥����Ϣ
	 * 
	 * @param user
	 *            ����Userʵ��
	 */
	public void insertData(User user) {
		SQLiteDatabase database = dbHepler.getWritableDatabase();
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", user.getName());
			contentValues.put("pwd", user.getPwd());
			database.insert(USER_NAME, "_id", contentValues);
		} catch (Exception e) {
			e.printStackTrace();
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
		SQLiteDatabase database = dbHepler.getWritableDatabase();
		try {
			for (User user : users) {
				ContentValues contentValues = new ContentValues();
				contentValues.clear();
				contentValues.put("name", user.getName());
				contentValues.put("pwd", user.getPwd());
				database.insert(USER_NAME, null, contentValues);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		SQLiteDatabase database = dbHepler.getWritableDatabase();
		try {
			String[] names = { user.getName() };
			number = database.delete(USER_NAME, "name=?", names);
		} catch (Exception e) {
			e.printStackTrace();
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
		SQLiteDatabase database = dbHepler.getWritableDatabase();
		try {
			ArrayList<String> nameList = new ArrayList<String>();
			for (User user : users) {
				nameList.add(user.getName());
			}
			String[] names = (String[]) nameList.toArray();
			// delete()�Ƿ��һ��ɾ��������
			number = database.delete(USER_NAME, "name=?", names);
		} catch (Exception e) {
			e.printStackTrace();
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
		SQLiteDatabase database = dbHepler.getWritableDatabase();
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", user.getName());
			contentValues.put("pwd", user.getPwd());
			String[] names = { user.getName() };
			number = database.update(USER_NAME, contentValues, "name=?", names);
		} catch (Exception e) {
			e.printStackTrace();
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
	 *            query(String table, String[] columns, String selection,
	 *            String[] selectionArgs, String groupBy, String having, String
	 *            orderBy, String limit); table ����, columns ��Ҫ��ʾ���ֶ�, selection
	 *            ����, selectionArgs selection��ֵ, groupBy ����, having ��������,
	 *            orderBy ����, limit ��ҳ
	 */
	public String queryData(User user) {
		String pwd = "";
		SQLiteDatabase database = dbHepler.getReadableDatabase();
		try {
			String[] names = { user.getName() };
			Cursor cursor = database.query(USER_NAME, null, "name=?", names,
					null, null, null);
			if (cursor.moveToNext()) {
				int pwdIndex = cursor.getColumnIndex("pwd");
				pwd = cursor.getString(pwdIndex);
			}
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (null != database) {
				database.close();
			}
		}
		return pwd;
	}

	/**
	 * ��ӡ user ��ȫ������
	 * 
	 * @return
	 */

	public List<User> queryUsers() {
		System.out.println("SQLite of queryUsers");
		List<User> users = new ArrayList<User>();
		SQLiteDatabase database = dbHepler.getReadableDatabase();
		try {
			// String[] names = { null };
			Cursor cursor = database.rawQuery("select * from user", null);
			while (cursor.moveToNext()) {
				User user = new User();
				int nameIndex = cursor.getColumnIndex("name");
				int pwdIndex = cursor.getColumnIndex("pwd");
				// if (cursor.getString(nameIndex) == null
				// || cursor.getString(pwdIndex) == null) {
				// continue;
				// }
				user.setName(cursor.getString(nameIndex));
				user.setPwd(cursor.getString(pwdIndex));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (null != database) {
				database.close();
			}
		}

		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getName());
			System.out.println(users.get(i).getPwd());
		}

		return users;

	}
}
