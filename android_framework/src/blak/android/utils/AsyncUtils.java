package blak.android.utils;

import android.os.AsyncTask;
import android.os.Build;

public class AsyncUtils {
    // http://developer.android.com/reference/android/os/AsyncTask.html, Order of execution
    public static void execute(AsyncTask task, Object... args) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, args);
        } else {
            task.execute(args);
        }
    }
}
