package com.example.praktikosdarbas41;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ListView listViewNotes;
    private ArrayList<String> notesList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNotes = findViewById(R.id.listViewNotes);
        notesList = new ArrayList<>();
        loadNotes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listViewNotes.setAdapter(adapter);
    }

    private void loadNotes() {
        Set<String> notes = getSharedPreferences("Notes", MODE_PRIVATE).getStringSet("notes", new HashSet<>());
        notesList.clear();
        notesList.addAll(notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int itemId = item.getItemId();

        if (itemId == R.id.create_note) {
            intent = new Intent(this, AddNoteActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.delete_note) {
            intent = new Intent(this, DeleteNoteActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
        adapter.notifyDataSetChanged();
    }
}
