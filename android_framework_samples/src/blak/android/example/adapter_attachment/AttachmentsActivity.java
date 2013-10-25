package blak.android.example.adapter_attachment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import blak.android.adapters.ItemClickListener;
import blak.android.example.R;
import blak.android.utils.SimpleMessage;

import java.util.ArrayList;
import java.util.List;

public class AttachmentsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afs__listview);

        initList();
    }

    private void initList() {
        List<Attachment> attachments = new ArrayList<Attachment>();

        attachments.add(new Attachment("1.prc", "asd"));
        attachments.add(new Attachment("2.prc", "mcv jkd"));
        attachments.add(new Attachment("3.prc", "ueirnm,"));
        attachments.add(new Attachment("4.prc", "itv nm,"));
        attachments.add(new Attachment("5.prc", "rejk"));
        attachments.add(new Attachment("6.prc", "mnd,f"));
        attachments.add(new Attachment("7.prc", "ierm"));
        attachments.add(new Attachment("8.prc", "erijv"));
        attachments.add(new Attachment("9.prc", "asdwer"));
        attachments.add(new Attachment("10.prc", "sdrujh"));

        ListView listView = (ListView) findViewById(R.id.afs__listview);
        AttachmentsAdapter adapter = new AttachmentsAdapter(this, attachments);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SimpleMessage.show(getApplication(), "Click");
            }
        });

        adapter.setEditListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                SimpleMessage.show(getApplication(), "Edit", position);
            }
        });
    }
}
