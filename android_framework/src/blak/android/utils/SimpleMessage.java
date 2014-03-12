package blak.android.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class SimpleMessage {
    public static void show(Context context, int stringId) {
        showShort(context, stringId);
    }

    public static void show(Context context, Object... objects) {
        showShort(context, objects);
    }

    public static void showShort(Context context, int stringId) {
        showShort(context, context.getString(stringId));
    }

    public static void showShort(Context context, CharSequence text) {
        showWithDuration(context, Toast.LENGTH_SHORT, text);
    }

    public static void showShort(Context context, Object... objects) {
        showWithDuration(context, Toast.LENGTH_SHORT, objects);
    }

    public static void showLong(Context context, int stringId) {
        showLong(context, context.getString(stringId));
    }

    public static void showLong(Context context, CharSequence text) {
        showWithDuration(context, Toast.LENGTH_LONG, text);
    }

    public static void showLong(Context context, Object... objects) {
        showWithDuration(context, Toast.LENGTH_LONG, objects);
    }

    public static void showWithDuration(Context context, int duration, Object... objects) {
        String message = TextUtils.join(" ", objects);
        Toast.makeText(context, message, duration).show();
    }
}
