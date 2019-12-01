package com.mic.libsqlite.utils;

import android.content.ContentValues;
import android.util.ArrayMap;

import com.mic.libsqlite.annotation.DbFiled;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ValuesMnager {

    //key value
    private static final Object [] mPutMethodArgs = new Object[2];

    private static final Map<String,Method> mPutMethods = new ArrayMap<String, Method>();


    /**
     * obj 转成ContentValues
     * @param t
     * @param clazz
     * @return
     */
    public static<T> ContentValues modelToContentValues(T t,Class clazz){

        ContentValues values = new ContentValues();

        Field[] fields = clazz.getDeclaredFields();

        for(Field field:fields){

            field.setAccessible(true);

            if(field.getAnnotation(DbFiled.class)!=null){
                String key = field.getAnnotation(DbFiled.class).value();
                Object value = null;
                try {
                    value = field.get(t);


                    mPutMethodArgs[0]=key;
                    mPutMethodArgs[1]=value;

                    String fileTypeName = field.getType().getName();
                    Method putMethod = mPutMethods.get(fileTypeName);
                    if(putMethod==null){
                        putMethod =ContentValues.class.getDeclaredMethod("put",String.class,value.getClass());
                        mPutMethods.put(fileTypeName,putMethod);
                    }

                    putMethod.invoke(values,key,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }finally {
                    mPutMethodArgs[0]=null;
                    mPutMethodArgs[1]=null;
                }

            }
        }

        return values;
    }

}
