package com.alai.lifeassistant.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2016/1/5.
 */
public class NoteDatabaseHelper extends SQLiteOpenHelper {

    private final static String TAG = "NoteDatabaseHelper";

    private final static String DB_NAME = "note.db";

    private final static String TABLE_NAME = "note";

    private final static int DB_VERSION = 4;

    private static NoteDatabaseHelper mInstance;

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    Notes.NoteColumn.ID + " INTEGER PRIMARY KEY," +
                    Notes.NoteColumn.MODIFIED_TIME + " INTEGER NOT NULL DEFAULT (strftime('%s','now') * 1000)," +
                    Notes.NoteColumn.TITLE + " TEXT NOT NULL DEFAULT ''," +
                    Notes.NoteColumn.CONTENT + " TEXT NOT NULL DEFAULT ''";

    public NoteDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized NoteDatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NoteDatabaseHelper(context);
        }
        return mInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
        ContentValues values = new ContentValues();
        values.put(Notes.NoteColumn.ID, 1);
        values.put(Notes.NoteColumn.MODIFIED_TIME, System.currentTimeMillis());
        values.put(Notes.NoteColumn.TITLE, "欢迎使用记事本");
        values.put(Notes.NoteColumn.CONTENT, "欢迎使用记事本" + "\n" + "国超是傻逼！！！");
        db.insert(TABLE_NAME, null, values);
        Log.d(TAG, "table note has been created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS note");
        onCreate(db);
        Log.d(TAG, "database has upgrade!");
    }
}