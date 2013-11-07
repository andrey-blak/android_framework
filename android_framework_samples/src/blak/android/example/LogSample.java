package blak.android.example;

import blak.android.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;

public class LogSample extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyLog.v("asd", null, 3);
        MyLog.vn("asd", null, 3);
    }
}
