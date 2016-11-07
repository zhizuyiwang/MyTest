package com.hsf.myListViewLink.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.hsf.myListViewLink.R;
import com.hsf.myListViewLink.view.RelationListView;

public class MyActivity2 extends AppCompatActivity {

    private RelationListView mListView1;
    private RelationListView mListView2;

    private String[] mData1 = new String[] { "listView1", "listView1",
            "listView1", "listView1", "listView1", "listView1", "listView1",
            "listView1", "listView1", "listView1", "listView1", "listView1",
            "listView1", "listView1", "listView1", "listView1", "listView1",
            "listView1", "listView1", "listView1", "listView1", "listView1",
            "listView1", "listView1" };
    private String[] mData2 = new String[] { "ListView2", "ListView2",
            "ListView2", "ListView2", "ListView2", "ListView2", "ListView2",
            "ListView2", "ListView2", "ListView2", "ListView2", "ListView2",
            "ListView2", "ListView2", "ListView2", "ListView2", "ListView2",
            "ListView2", "ListView2", "ListView2", "ListView2", "ListView2",
            "ListView2", "ListView2", "ListView2", "ListView2" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);

        mListView1 = (RelationListView) findViewById(R.id.listView1);
        mListView2 = (RelationListView) findViewById(R.id.listView2);

        mListView1.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mData1));
        mListView2.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mData2));
        mListView1.setRelatedListView(mListView2);
        mListView2.setRelatedListView(mListView1);
    }
}
