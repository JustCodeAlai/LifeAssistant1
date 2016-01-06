package com.alai.lifeassistant.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by Administrator on 2016/1/5.
 */
public class NoteProvider extends ContentProvider {

    private static final UriMatcher mMatcher;

    private static final String TABLE_NAME = "note";

    private NoteDatabaseHelper mHelper;

    private static final int URI_NOTE = 1;
    private static final int URI_NOTE_ITEM = 2;

    private static final String TAG = "NoteProvider";

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(Notes.AUTHORITY, "note", URI_NOTE);
        mMatcher.addURI(Notes.AUTHORITY, "note/#", URI_NOTE_ITEM);

    }

    @Override
    public boolean onCreate() {
        mHelper = NoteDatabaseHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String id = null;
        switch (mMatcher.match(uri)) {
            //查出所有
            case URI_NOTE:
                cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            //查出某一项
            case URI_NOTE_ITEM:
                id = uri.getPathSegments().get(1);
                cursor = db.query(TABLE_NAME, projection, Notes.NoteColumn.ID +
                        "=" + id + parseSelection(selection), selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    private String parseSelection(String selection) {
        return !TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : "";
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        long insertedId = 0;
        switch (mMatcher.match(uri)) {
            case URI_NOTE:
                insertedId = db.insert(TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (insertedId > 0) {
            getContext().getContentResolver().notifyChange(ContentUris.withAppendedId(Notes.NOTE_URI, insertedId),
                    null);
        }
        return ContentUris.withAppendedId(uri, insertedId);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        String id = null;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        switch (mMatcher.match(uri)) {
            case URI_NOTE:
                selection = "(" + selection + ") AND " + Notes.NoteColumn.ID + ">0 ";
                db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case URI_NOTE_ITEM:
                id = uri.getPathSegments().get(1);
                long noteId = Long.valueOf(id);
                if (noteId < 0) {
                    break;
                }
                count = db.delete(TABLE_NAME, Notes.NoteColumn.ID + "=" + id +
                        parseSelection(selection), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        String id = null;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        switch (mMatcher.match(uri)) {
            case URI_NOTE:
                count = db.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            case URI_NOTE_ITEM:
                id = uri.getPathSegments().get(1);
                count = db.update(TABLE_NAME, values, Notes.NoteColumn.ID +
                        "=" + id + parseSelection(selection), selectionArgs);
                break;
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }
}
