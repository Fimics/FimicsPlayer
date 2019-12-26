package com.mic.libsqlite.utils;

import android.text.TextUtils;

import java.util.Locale;

@SuppressWarnings("unused")
public class DaoTools {



    /**
     * java字段类型转sqlite column类型
     * @param type
     * @return
     */
    public static String getColumnType(String type) {
        String value = null;
        if (type.contains("String")) {
            value = " text";
        } else if (type.contains("int")) {
            value = " integer";
        } else if (type.contains("boolean")) {
            value = " boolean";
        } else if (type.contains("float")) {
            value = " float";
        } else if (type.contains("double")) {
            value = " double";
        } else if (type.contains("char")) {
            value = " varchar";
        } else if (type.contains("long")) {
            value = " long";
        }
        return value;
    }

    public static String capitalize(String string) {
        if (!TextUtils.isEmpty(string)) {
            return string.substring(0, 1).toUpperCase(Locale.US) + string.substring(1);
        }
        return string == null ? null : "";
    }
}
