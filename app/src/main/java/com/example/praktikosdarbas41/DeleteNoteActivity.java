package com.example.praktikosdarbas41;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner spinnerNotes;
    Button buttonDeleteNote;
    ArrayList<String> notesList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spinnerNotes = findViewById(R.id.spinnerNotes);
        buttonDeleteNote = findViewById(R.id.buttonDeleteNote);
        loadNotes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, notesList);
        spinnerNotes.setAdapter(adapter);

        buttonDeleteNote.setOnClickListener(view -> {
            deleteNote();
            finish();
        });
    }

    private void loadNotes() {
        Set<String> notes = getSharedPreferences("Notes", MODE_PRIVATE).getStringSet("notes", new HashSet<>());
        notesList.clear();
        notesList.addAll(notes);
    }

    private void deleteNote() {
        String note = (String) spinnerNotes.getSelectedItem();
        SharedPreferences preferences = getSharedPreferences("Notes", MODE_PRIVATE);
        Set<String> notes = new HashSet<>(preferences.getStringSet("notes", new HashSet<>()));
        notes.remove(note);
        preferences.edit().putStringSet("notes", notes).apply();
    }
}