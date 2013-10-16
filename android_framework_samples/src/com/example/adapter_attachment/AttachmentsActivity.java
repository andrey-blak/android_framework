package com.example.adapter_attachment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.R;

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
        ListAdapter adapter = new AttachmentsAdapter(this, attachments);
        listView.setAdapter(adapter);
    }
}
