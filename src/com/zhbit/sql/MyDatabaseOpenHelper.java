package com.zhbit.sql;

import com.zhbit.dao.User;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表管理 MySQLiteOpenHelperStudent （负责 生成打开据库 / 生成打开表 / 升级表 / 完成此数据库表的增删改查逻辑处理
 * / 还有其他表处理功能增加等等）
 */
public class MyDatabaseOpenHelper extends SQLiteOpenHelper {

	//得到类的简写名称
	private final static String TAG = MyDatabaseOpenHelper.class.getSimpleName();

	//数据库名称
	private final static String DB_NAME = "androidlogin.db";
	
	//数据库版本
	private static final int version = 1;

	//表名
	private static final String TABLE_NAME = "user";
	
	private Context mcontext;

	//定义单例模式，可以被多次地方多次调用
	private static MyDatabaseOpenHelper mySQLiteOpenHelperStudent;

	// 获取唯一可用对象
	public static MyDatabaseOpenHelper getInstance(Context mcontext) {
		if (null == mySQLiteOpenHelperStudent) {
			synchronized (MyDatabaseOpenHelper.class) {
				if (null == mySQLiteOpenHelperStudent) {
					mySQLiteOpenHelperStudent = new MyDatabaseOpenHelper(mcontext,DB_NAME,null,1);
				}
			}
		}
		return mySQLiteOpenHelperStudent;
	}

	public MyDatabaseOpenHelper(Context mcontext, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(mcontext, name, factory, version);
		this.mcontext = mcontext;
	}

	/**
	 * 继承SQLiteOpenHelper抽象类 重写的创表方法，此SQLiteDatabase db 不能关闭, 没有数据库存在才会执行
	 * @param db
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Log","没有数据库,创建数据库");
		String sql_message = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NOT NULL, pwd VARCHAR(100) NOT NULL);";
		db.execSQL(sql_message);
	}

	/**
	 * 继承SQLiteOpenHelper抽象类 重写的升级方法, 数据库更新才会执行
	 * 
	 * @param db
	 * @param oldVersion
	 * @param newVersion
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("updateLog","数据库更新了！");
	}

	/**
	 * 插入单条信息
	 * 
	 * @param user
	 *            传递User实例
	 */
	public void insertData(User user) {
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getWritableDatabase();
		try {
			System.out.println("11111111111111"+user.getName());
			System.out.println("222222222222222"+user.getPwd());
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", user.getName());
			contentValues.put("pwd", user.getPwd());
			database.insert(TABLE_NAME, "_id", contentValues);
			message("插入成功");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "insert单条异常：" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}

	/**
	 * 插入多条数据
	 * 
	 * @param users
	 *            传递User集合
	 */
	public void insertData(List<User> users) {
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getWritableDatabase();
		try {
			for (User user : users) {
				ContentValues contentValues = new ContentValues();
				contentValues.clear();
				contentValues.put("name", user.getName());
				contentValues.put("pwd", user.getPwd());
				database.insert(TABLE_NAME, null, contentValues);
				message("插入成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "insert多条异常：" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}

	/**
	 * 删除单条数据
	 * 
	 * @param user
	 *            传递User实例； username唯一
	 */
	public int deleteData(User user) {
		int number = -1;
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getWritableDatabase();
		try {
			String[] names = { user.getName() };
			number = database.delete(TABLE_NAME, "name=?", names);
			message("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "delete单条异常：" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
		return number;
	}

	/**
	 * 删除多条数据
	 * 
	 * @param users
	 *            传递User集合
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
			// delete()是否可一次删除多条？
			number = database.delete(TABLE_NAME, "name=?", names);
			message("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "delete多条异常：" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
		return number;
	}

	/**
	 * 修改单条数据
	 * 
	 * @param user
	 *            传递User实例； username唯一
	 */
	public int updateData(User user) {
		int number = -1;
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getWritableDatabase();
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", user.getName());
			contentValues.put("pwd", user.getPwd());
			String[] names = { user.getName() };
			number = database.update(TABLE_NAME, contentValues, "name=?", names);
			message("更改成功");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "update单条异常：" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
		return number;
	}
	
	/**
	 * 查找数据
	 * 
	 * @param user
	 * 
	 * query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
	 * table 表名,
	 * columns 想要显示的字段,
	 * selection 条件,
	 * selectionArgs selection的值,
	 * groupBy 分组,
	 * having 分组条件,
	 * orderBy 排序,
	 * limit 分页
	 */
	public String queryData (User user){
		String pwd = "";
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getReadableDatabase();
		try {
			String[] names = { user.getName() };
			Cursor cursor = database.query(TABLE_NAME, null, "name=?", names, null, null, null);
			if(cursor.moveToNext()){
				int pwdIndex = cursor.getColumnIndex("pwd");
				pwd = cursor.getString(pwdIndex);
			}
			message(pwd);
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "query单条异常：" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
		return pwd;
	}
	
	public ArrayList<User> queryUsers(){
		ArrayList<User> users = new ArrayList<User>();
		SQLiteDatabase database = mySQLiteOpenHelperStudent.getReadableDatabase();
		try {
			Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
			if(cursor.moveToNext()){
				User user = new User();
				int nameIndex = cursor.getColumnIndex("name");
				int pwdIndex = cursor.getColumnIndex("pwd");
				user.setName(cursor.getString(nameIndex));
				user.setPwd(cursor.getString(pwdIndex));
				users.add(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "query单条异常：" + e.toString());
		} finally {
			if (null != database) {
				database.close();
			}
		}
		
		return users;
		
	}
	private void message(String mes) {
		Toast toast = Toast.makeText(mcontext, mes,
				Toast.LENGTH_SHORT);
		toast.show();
	}
	
}
