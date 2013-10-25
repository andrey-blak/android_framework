package blak.android.utils;

import android.content.Context;
import android.widget.Toast;

public class SimpleMessage {
    public static void show(Context context, int stringId) {
        show(context, context.getString(stringId));
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void showShort(Context context, int stringId) {
        showShort(context, context.getString(stringId));
    }

    public static void showShort(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void showLong(Context context, int stringId) {
        showLong(context, context.getString(stringId));
    }

    public static void showLong(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_LONG);
    }

    public static void show(Context context, CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

    public static void show(Context context, Object... params) {
        StringBuilder buff = new StringBuilder();
        for (Object object : params) {
            buff.append(object);
            buff.append(" ");
        }
        Toast.makeText(context, buff.toString(), Toast.LENGTH_SHORT).show();
    }
}
