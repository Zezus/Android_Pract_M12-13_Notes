package com.example.pract_m12_13_notes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NoteDAO {

    private final SQLiteDatabase database;

    public NoteDAO(DatabaseHelper databaseHelper) {
        database = databaseHelper.getWritableDatabase();
    }

    public List<Note> all() {
        List<Note> notes = new ArrayList<>();

        try {
            database.beginTransaction();

            Cursor cursor = database.query(DatabaseHelper.NOTES_TABLE, new String[]{DatabaseHelper.ID_COLUMN, DatabaseHelper.NOTE_TEXT_COLUMN}, null, null, null, null, null);
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Note note = new Note();
                int idColumnIndex = cursor.getColumnIndex(DatabaseHelper.ID_COLUMN);
                int noteTextColumnIndex = cursor.getColumnIndex(DatabaseHelper.NOTE_TEXT_COLUMN);
                note.setId(cursor.getInt(idColumnIndex));
                note.setNoteText(cursor.getString(noteTextColumnIndex));
                notes.add(note);
            }
            cursor.close();

            database.setTransactionSuccessful();
        } catch (SQLiteException ex) {
            Log.d(MainActivity.class.getSimpleName(), ex.getMessage());
        } finally {
            database.endTransaction();
            database.close();
        }

        return notes;
    }

    public void save(Note note) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NOTE_TEXT_COLUMN, note.getNoteText());

        try {
            database.beginTransaction();

            database.insert(DatabaseHelper.NOTES_TABLE, null, contentValues);

            database.setTransactionSuccessful();
        } catch (SQLiteException ex) {
            Log.d(MainActivity.class.getSimpleName(), ex.getMessage());
        } finally {
            database.endTransaction();
            database.close();
        }
    }
}
