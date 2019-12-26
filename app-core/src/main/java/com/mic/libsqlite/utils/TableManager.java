package com.mic.libsqlite.utils;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mic.libsqlite.annotation.DbTable;

import java.lang.reflect.Field;

public class TableManager {

    private static final String TAG ="table";
    private static SQLiteDatabase mDatabase;
    private static  Class mClazz;
    private static TableManager tableManager = new TableManager();

    private TableManager(){
    }

    public static<T> TableManager init(Class<T> clazz){
        mClazz= clazz;
        return tableManager;
    }

    public static<T> TableManager init(SQLiteDatabase sqLiteDatabase,Class<T> clazz){
        mDatabase =sqLiteDatabase;
        mClazz= clazz;
        return tableManager;
    }


    /**
     * 获取表名
     * @return tableName
     */
    public  static<T> String getTableName(Class<T> clazz){
        String tableName=null;
        if(clazz.getAnnotation(DbTable.class)==null){
            tableName = clazz.getClass().getSimpleName();
        }else{
            tableName =clazz.getAnnotation(DbTable.class).value();

        }
        return tableName;
    }


    /**
     *  create table if not exists Persion(
     *  "id integer primary key autoincreament,
     *  "+"name text,
     *  +"age integer"
     *  +"flag boolean")
     */

    /**
     * 创建表
     */
    public static  void createTable() {

        StringBuffer sb = new StringBuffer();
        sb.append("create table if not exists ")
                .append(getTableName(mClazz))
                .append("(id integer primary key autoincrement, ");
        sb.append(getColumnSql());
        String createTableSql =sb.toString();
        Log.d(TAG,"创建表语句: "+createTableSql);

        mDatabase.execSQL(createTableSql);
    }


    private static StringBuffer getColumnSql(){
        StringBuffer sb = new StringBuffer();
        Field[] fields = mClazz.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            String name = field.getName();
            String type = field.getType().getSimpleName(); //返回 int string boolean
            //type 转换 int-->integer string-->text
            sb.append(name).append(DaoTools.getColumnType(type)).append(", ");
        }
        sb.replace(sb.length()-2,sb.length(),")");
        return  sb;
    }


    public String getColumnMethodName(Class<?> fieldType) {
        String typeName;
        if (fieldType.isPrimitive()) {
            typeName = DaoTools.capitalize(fieldType.getName());
        } else {
            typeName = fieldType.getSimpleName();
        }
        String methodName = "get" + typeName;
        if ("getBoolean".equals(methodName)) {
            methodName = "getInt";
        } else if ("getChar".equals(methodName) || "getCharacter".equals(methodName)) {
            methodName = "getString";
        } else if ("getDate".equals(methodName)) {
            methodName = "getLong";
        } else if ("getInteger".equals(methodName)) {
            methodName = "getInt";
        }
        return methodName;
    }
}
