package com.mic.libsqlite.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

@SuppressWarnings("unused")
public class DaoFactory {

    private String dbName="joke.db";
    private SQLiteDatabase database;


    private static class  FactoryHolder{
        private static final DaoFactory instance = new DaoFactory();
    }

    private DaoFactory() {

        //TODO
        /**
         * 1.把数据库放到内存卡里面 判断是否有存储卡
         * 2. 6.0要动态申请权限
         */
        File dbRoot = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"AAAAAdatabase");

        if (!dbRoot.exists()){
            dbRoot.mkdirs();
        }
        File dbFile = new File(dbRoot,dbName);
        database = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
    }

    public static DaoFactory getFactory(){
        return FactoryHolder.instance;
    }


   public <T>IDao getDao(Class<T> clazz){

        IDao dao = new BaseDao();
        dao.init(database,clazz);
        return dao;
   }


}
