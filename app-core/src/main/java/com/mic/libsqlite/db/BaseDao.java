package com.mic.libsqlite.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.mic.libsqlite.utils.TableManager;
import com.mic.libsqlite.utils.ValuesMnager;


import java.util.List;

public class BaseDao<T> implements IDao<T> {

    private static final String TAG="basedao";
    private SQLiteDatabase database;
    private Class<T> mClazz;

    private volatile boolean isCreated = false;
    private TableManager tableManager;
    private QuerySupport querySupport;


    public void init(SQLiteDatabase database,Class<T> clazz){
        this.database =database;
        this.mClazz=clazz;
        tableManager = TableManager.init(database,clazz);
        querySupport =QuerySupport.init(database,clazz);

        if(!isCreated){
           tableManager.createTable();
        }
        isCreated=true;
    }


    @Override
    public long insert(T t) {
        ContentValues values = ValuesMnager.modelToContentValues(t,mClazz);
        long result =database.insert(tableManager.getTableName(mClazz),null,values);
        return result;
    }

    @Override
    public void insert(List<T> datas) {

        database.beginTransaction();
        for (T t :datas){
            insert(t);
        }
        database.setTransactionSuccessful();
        database.endTransaction();

    }

    @Override
    public QuerySupport<T> querySupport() {
        return null;
    }

    @Override
    public int delete(String whereClause, String... whereArgs) {
        return 0;
    }

    @Override
    public int update(T obj, String whereClause, String... whereArgs) {
        return 0;
    }

    @Override
    public List<T> queryAll() {
        return querySupport.queryAll();
    }



}
