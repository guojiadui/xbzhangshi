package com.xbzhangshi.chat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // 数据库文件名
    public static final String DB_NAME = "chat.db";
    // 数据库表名
    public static final String TABLE_NAME = "history";
    // 数据库版本号
    public static final int DB_VERSION = 1;

    public static final String  SESSION = "session";
    public static final String CONTENT = "content";
    public static final String TIME = "time";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // 当数据库文件创建时，执行初始化操作，并且只执行一次
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建表
        String sql = "create table " +
                TABLE_NAME +
                "(_id integer primary key autoincrement, " +
                SESSION + " TEXT, " +
                CONTENT + " TEXT,"+
                TIME + " integer"+
                ")";

        // 创建表SQL语句
        String stu_table="CREATE TABLE IF NOT EXISTS usertable(_id integer primary key autoincrement,sname text,snumber text)";
        // 执行SQL语句
        db.execSQL(stu_table);
    }

    // 当数据库版本更新执行该方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
