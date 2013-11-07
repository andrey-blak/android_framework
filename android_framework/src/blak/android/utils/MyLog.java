package blak.android.utils;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

public class MyLog {
    public static String sTag = "@@@";

    /**
     * Log with default tag
     */
    public static void v(Object... params) {
        vt(sTag, params);
    }

    /**
     * Log with custom tag
     */
    public static void vt(String tag, Object... params) {
        String output = TextUtils.join(" ", params);
        Log.v(tag, output);
    }

    /**
     * Log with default tag without nulls
     */
    public static void vn(Object... params) {
        vtn(sTag, params);
    }

    /**
     * Log with custom tag without nulls
     */
    public static void vtn(String tag, Object... params) {
        StringBuilder buff = new StringBuilder();
        for (Object object : params) {
            if (object != null) {
                buff.append(object.toString());
                buff.append(" ");
            }
        }
        Log.v(tag, buff.toString());
    }

    public static void logCurrent(String tag, Cursor cursor) {
        logColumns(tag, cursor);

        Collection<String> values = new ArrayList<String>();
        int len = cursor.getColumnCount();
        for (int i = 0; i < len; i++) {
            String value = cursor.getString(i);
            values.add(value);
        }
        vt(tag, TextUtils.join(", ", values));
    }

    public static void logAll(String tag, Cursor cursor) {
        logColumns(tag, cursor);

        int position = cursor.getPosition();

        cursor.moveToFirst();
        cursor.moveToPrevious();
        Collection<String> values = new ArrayList<String>();
        while (cursor.moveToNext()) {
            int len = cursor.getColumnCount();
            for (int i = 0; i < len; i++) {
                String value = cursor.getString(i);
                values.add(value);
            }
            vt(tag, TextUtils.join(", ", values));
            values.clear();
        }
        cursor.moveToPosition(position);
    }

    private static void logColumns(String tag, Cursor cursor) {
        String[] columns = cursor.getColumnNames();
        String colNames = TextUtils.join(", ", columns);
        vt(tag, colNames);
    }
}
