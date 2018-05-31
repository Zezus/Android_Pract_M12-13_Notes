package com.example.pract_m12_13_notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoteDAO noteDAO = new NoteDAO(new DatabaseHelper(getApplicationContext()));
        List<Note> notes = noteDAO.all();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new NotesAdapter(getApplicationContext(), notes));
    }

    public void addNewNote(View view) {
        startActivity(new Intent(getApplicationContext(), NewNoteActivity.class));
    }
}
