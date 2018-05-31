package com.example.pract_m12_13_notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewNoteActivity extends AppCompatActivity {

    private EditText editText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        editText = findViewById(R.id.edit_text);
        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    public void addNote(View view) {
        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Note cen not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        NoteDAO noteDAO = new NoteDAO(databaseHelper);
        Note note = new Note();
        note.setNoteText(editText.getText().toString());
        noteDAO.save(note);

        Toast.makeText(this, "Note created", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
