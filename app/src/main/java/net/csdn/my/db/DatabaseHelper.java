package net.csdn.my.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库操作，保存用户登陆的信息
 * Created by lenovo on 2016/5/11.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper mInstance = null;// 单例
    // 数据库的版本号
    private static final int DATABASE_VERSION = 1;
    // 数据库的名称
    private static final String DATABASE_NAME = "Test.db";
    // 数据库sql语句 添加一个表
    private static final String TABLE_CREATE_LOGIN = "create table user" +
            "(_id integer primary key autoincrement,username varchar(20),password varchar(20))";

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 单例模式
    static synchronized DatabaseHelper getInstance(Context context) {
        if (null == mInstance) {
            mInstance = new DatabaseHelper(context);
        }
        return mInstance;
    }

    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(TABLE_CREATE_LOGIN);// 向数据库中添加一个表
    }

    // 拿到当前数据库版本和上个版本的进行比较 更新数据库
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 删除数据库
    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }

}
