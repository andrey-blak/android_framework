package blak.android.utils;

import android.view.View;

public class ViewUtils {
    public static void show(View view, boolean show) {
        int visibility = (show) ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }
}
