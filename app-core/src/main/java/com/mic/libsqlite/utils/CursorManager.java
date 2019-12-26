package com.mic.libsqlite.utils;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CursorManager<T> {

    private static Class mClazz;
    private static TableManager tableManager;
    private static CursorManager cursorManager = new CursorManager();
    private CursorManager(){}

    public static CursorManager init(Class clazz){
        mClazz =clazz;
        tableManager=TableManager.init(mClazz);
        return cursorManager;
    }

    public List<T> cursorToList(Cursor cursor) {
        List<T> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            // 不断的从游标里面获取数据
            do {
                try {
                    // 通过反射new对象
                    T instance = (T) mClazz.newInstance();
                    Field[] fields = mClazz.getDeclaredFields();


                    for (Field field : fields) {
                        // 遍历属性
                        field.setAccessible(true);
                        String name = field.getName();
                        // 获取角标  获取在第几列
                        int index = cursor.getColumnIndex(name);
                        if (index == -1) {
                            continue;
                        }

                        // 通过反射获取 游标的方法  field.getType() -> 获取的类型
                        Method cursorMethod = cursorMethod(field.getType());
                        if (cursorMethod != null) {
                            // 通过反射获取了 value
                            Object value = cursorMethod.invoke(cursor, index);
                            if (value == null) {
                                continue;
                            }

                            // 处理一些特殊的部分
                            if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                                if ("0".equals(String.valueOf(value))) {
                                    value = false;
                                } else if ("1".equals(String.valueOf(value))) {
                                    value = true;
                                }
                            } else if (field.getType() == char.class || field.getType() == Character.class) {
                                value = ((String) value).charAt(0);
                            } else if (field.getType() == Date.class) {
                                long date = (Long) value;
                                if (date <= 0) {
                                    value = null;
                                } else {
                                    value = new Date(date);
                                }
                            }

                            // 通过反射注入
                            field.set(instance, value);
                        }
                    }
                    // 加入集合
                    list.add(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // 获取游标的方法
    private Method cursorMethod(Class<?> type) throws Exception {
        String methodName = tableManager.getColumnMethodName(type);
        // type String getString(index); int getInt; boolean getBoolean
        Method method = Cursor.class.getMethod(methodName, int.class);
        return method;
    }

}
