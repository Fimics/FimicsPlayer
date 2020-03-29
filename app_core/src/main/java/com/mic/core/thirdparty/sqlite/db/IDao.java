package com.mic.core.thirdparty.sqlite.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface IDao<T> {

    void init(SQLiteDatabase database, Class<T> clazz);

    long insert(T t);

    void insert(List<T> datas);

    List<T> queryAll();

    // 获取专门查询的支持类
    QuerySupport<T> querySupport();

    // 按照语句查询



    int delete(String whereClause, String... whereArgs);

    int update(T obj, String whereClause, String... whereArgs);



}
