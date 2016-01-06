package com.alai.lifeassistant.data;

import android.net.Uri;

/**
 * Created by Administrator on 2016/1/5.
 */
public class Notes {
    public static final Uri NOTE_URI = Uri.parse("content://alai/note");
    public static final String AUTHORITY = "alai";
    public static final class NoteColumn {
        public static final String ID = "_id";
        public static final String TITLE = "title";
        public static final String MODIFIED_TIME = "modified_time";
        public static final String CONTENT = "content";
    }
}
