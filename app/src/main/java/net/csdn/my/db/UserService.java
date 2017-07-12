package net.csdn.my.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.csdn.my.bean.RegisterUser;
import net.csdn.my.bean.User;

/**
 * 用户登陆和注册的数据服务类
 * Created by neijiang on 2016/5/11.
 */
public class UserService {
    /**
     * sqlite数据库
     */
    private SQLiteDatabase mdb = null;
    /**
     * sqlite数据库操作辅助类
     */
    private DatabaseHelper dbHelper = null;
    /**
     * 数据库操作语句
     */
    String sql = null;
    private Context context;
    private static final String TAG = "UserService";

    /**
     * 新建服务类时，获取数据库辅助类的单例
     *
     * @param context 上下文
     */
    public UserService(Context context) {
        this.context = context;
        dbHelper = DatabaseHelper.getInstance(context);
    }


    /**
     * 查询数据库中的用户信息，判断用户是否能够登陆
     *
     * @param user 用户登陆信息
     * @return 能够登陆 false 登陆失败
     */
    public boolean login(User user) {
        // 通过辅助对象调用getReadableDatabase（）方法 如果数据库不存在就创建 否则直接打开
        mdb = dbHelper.getReadableDatabase();
        Log.d(TAG, "登陆数据库地址:" + mdb.getPath());
        sql = "select * from user where username=? and password=?";
        Cursor cursor = mdb.rawQuery(sql, new String[]{user.getUserName(), user.getPassword()});
        if (cursor.moveToNext() == true) {
            Log.d(TAG, "登陆名：" + cursor.getString(1) + "密码：" + cursor.getString(2));
            cursor.close();
            // 关闭数据库辅助类和数据库操作类
            dbHelper.close();
            mdb.close();
            return true;
        }
//        sql = "select * from user ";
//        Cursor cursor = mdb.rawQuery(sql, null);
//        while (cursor.moveToNext()) {
//            Log.d(TAG, "登陆名：" + cursor.getString(1) + "密码：" + cursor.getString(2));
//        }
        // 关闭数据库辅助类和数据库操作类
        dbHelper.close();
        mdb.close();
        return false;
    }

    /**
     * 将用户的注册信息插入数据库
     *
     * @param registerUser 用户注册信息
     * @return true 注册成功 false 注册失败
     */
    public boolean register(RegisterUser registerUser) {
        mdb = dbHelper.getReadableDatabase();
        Log.d(TAG, "注册数据库地址" + mdb.getPath());
        sql = "insert into user(username,password) values(?,?)";
        Object obj[] = {registerUser.getUserName(), registerUser.getPassword()};
//        if (TextUtils.isEmpty(registerUser.getUserName()) || TextUtils.isEmpty(registerUser.getPassword())) {
//            Log.d(TAG, "注册信息不完整");
//            return false;
//        }
        try {
            mdb.execSQL(sql, obj);
            Log.d(TAG, registerUser.getUserName() + "注册成功");
            //关闭数据库辅助类和数据库操作类
            dbHelper.close();
            mdb.close();
            return true;
        } catch (SQLException e) {
            Log.d(TAG, "数据库插入失败");
        }
        return false;
    }

//    /**
//     * 判断字符串是否为空或者包含转义字符
//     *
//     * @param s 字符串
//     * @return false 表示字符串不为空 true 表示字符串为空
//     */
//    public static boolean isEmpty(String s) {
//        if (s == null || s.equals("")) {
//            return true;
//        }
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
//                return false;
//            }
//        }
//        return true;
//    }


}
