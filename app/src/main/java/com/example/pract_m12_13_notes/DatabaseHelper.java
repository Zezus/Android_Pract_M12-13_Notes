package com.example.pract_m12_13_notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String NOTES_TABLE = "notes";
    public static final String ID_COLUMN = "_id";
    public static final String NOTE_TEXT_COLUMN = "note_text";

    private static final String CREATE_NOTES_TABLE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT NOT NULL);", NOTES_TABLE, ID_COLUMN, NOTE_TEXT_COLUMN);

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, "notes.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
