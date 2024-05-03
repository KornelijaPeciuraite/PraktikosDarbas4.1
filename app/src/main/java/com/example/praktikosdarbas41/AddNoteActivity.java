package com.example.praktikosdarbas41;

import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    EditText editTextNoteName, editTextNoteContent;
    Button buttonSaveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextNoteName = findViewById(R.id.editTextNoteName);
        editTextNoteContent = findViewById(R.id.editTextNoteContent);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);

        buttonSaveNote.setOnClickListener(view -> {
            saveNote();
            finish();
        });
    }

    private void saveNote() {
        String noteName = editTextNoteName.getText().toString();
        String noteContent = editTextNoteContent.getText().toString();
        String note = noteName + " - " + noteContent;

        SharedPreferences preferences = getSharedPreferences("Notes", MODE_PRIVATE);
        Set<String> notes = new HashSet<>(preferences.getStringSet("notes", new HashSet<>()));
        notes.add(note);
        preferences.edit().putStringSet("notes", notes).apply();
    }
}