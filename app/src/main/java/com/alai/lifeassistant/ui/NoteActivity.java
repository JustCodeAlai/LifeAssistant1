package com.alai.lifeassistant.ui;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/1/5.
 */
public class NoteActivity extends Activity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {

    private ListView mListView;

    //向sqlite异步查询数据
    private BackgroundQueryHandler mQueryHandler;

    private ContentResolver mContentResolver;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}