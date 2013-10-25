package blak.android.example.adapter_simple;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import blak.android.example.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afs__listview);

        initList();
    }

    private void initList() {
        List<SimpleItem> attachments = new ArrayList<SimpleItem>();

        attachments.add(new SimpleItem("1", "asd"));
        attachments.add(new SimpleItem("2", "mcv jkd"));
        attachments.add(new SimpleItem("3", "ueirnm,"));
        attachments.add(new SimpleItem("4", "itv nm,"));
        attachments.add(new SimpleItem("5", "rejk"));
        attachments.add(new SimpleItem("6", "mnd,f"));
        attachments.add(new SimpleItem("7", "ierm"));
        attachments.add(new SimpleItem("8", "erijv"));
        attachments.add(new SimpleItem("9", "asdwer"));
        attachments.add(new SimpleItem("10", "sdrujh"));

        ListView listView = (ListView) findViewById(R.id.afs__listview);
        ListAdapter adapter = new SimpleHolderAdapter(this, attachments);
        listView.setAdapter(adapter);
    }
}
