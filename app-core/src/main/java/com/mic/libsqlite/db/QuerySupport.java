package com.mic.libsqlite.db;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mic.libsqlite.utils.CursorManager;
import com.mic.libsqlite.utils.TableManager;

import java.util.List;

public class QuerySupport<T> {

    private static final QuerySupport querySupport = new QuerySupport();
    private static  CursorManager cursorManager;

    // 查询的列
    private String[] mQueryColumns;
    // 查询的条件
    private String mQuerySelection;
    // 查询的参数
    private String[] mQuerySelectionArgs;
    // 查询分组
    private String mQueryGroupBy;
    // 查询对结果集进行过滤
    private String mQueryHaving;
    // 查询排序
    private String mQueryOrderBy;
    // 查询可用于分页
    private String mQueryLimit;

    private static SQLiteDatabase mDatabase;

    private static Class mClazz;

    private QuerySupport(){}

    public static QuerySupport init(SQLiteDatabase database,Class clazz){
        mDatabase = database;
        mClazz =clazz;
        cursorManager=CursorManager.init(mClazz);
        return querySupport;
    }


    public QuerySupport columns(String... columns) {
        this.mQueryColumns = columns;
        return this;
    }

    public QuerySupport selectionArgs(String... selectionArgs) {
        this.mQuerySelectionArgs = selectionArgs;
        return this;
    }

    public QuerySupport having(String having) {
        this.mQueryHaving = having;
        return this;
    }

    public QuerySupport orderBy(String orderBy) {
        this.mQueryOrderBy = orderBy;
        return this;
    }

    public QuerySupport limit(String limit) {
        this.mQueryLimit = limit;
        return this;
    }

    public QuerySupport groupBy(String groupBy) {
        this.mQueryGroupBy = groupBy;
        return this;
    }

    public QuerySupport selection(String selection) {
        this.mQuerySelection = selection;
        return this;
    }


    public List<T> queryAll() {
        Cursor cursor = mDatabase.query(TableManager.getTableName(mClazz),
                null,null,null,null,null,null);
        return cursorManager.cursorToList(cursor);
    }


    public List<T> query() {
        Cursor cursor = mDatabase.query(TableManager.getTableName(mClazz), mQueryColumns, mQuerySelection,
                mQuerySelectionArgs, mQueryGroupBy, mQueryHaving, mQueryOrderBy, mQueryLimit);
        clearQueryParams();
        return cursorManager.cursorToList(cursor);
    }


    /**
     * 清空参数
     */
    private void clearQueryParams() {
        mQueryColumns = null;
        mQuerySelection = null;
        mQuerySelectionArgs = null;
        mQueryGroupBy = null;
        mQueryHaving = null;
        mQueryOrderBy = null;
        mQueryLimit = null;
    }

}
