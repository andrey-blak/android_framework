package blak.android.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import blak.android.utils.MyLog;

public class LogSample extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = new Bundle();
        bundle.putString("name", "trinity");
        Intent intent = new Intent();
        intent.putExtra("BUNDLE", bundle);
        bundle = intent.getParcelableExtra("BUNDLE");

        MyLog.v("asd", null, 3);
        MyLog.vn("asd", null, 3);
        MyLog.vn(bundle);
    }
}
