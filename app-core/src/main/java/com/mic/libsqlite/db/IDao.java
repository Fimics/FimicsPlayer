package com.mic.libsqlite.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface IDao<T> {

    void init(SQLiteDatabase database, Class<T> clazz);

    public long insert(T t);

    public void insert(List<T> datas);

    public List<T> queryAll();

    // 获取专门查询的支持类
    QuerySupport<T> querySupport();

    // 按照语句查询



    int delete(String whereClause, String... whereArgs);

    int update(T obj, String whereClause, String... whereArgs);



}
