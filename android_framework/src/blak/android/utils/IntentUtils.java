package blak.android.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class IntentUtils {
    public static void browseUrl(Activity activity, String url) {
        Uri uri = Uri.parse(url);
        browseUrl(activity, uri);
    }

    public static void browseUrl(Activity activity, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        activity.startActivity(intent);
    }
}
