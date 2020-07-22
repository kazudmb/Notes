package com.dmb.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.dmb.notes.adapters.NotesRecyclerAdapter;
import com.dmb.notes.models.Note;
import com.dmb.notes.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener{

    private static final String TAG = "NotesListActivity";

    // ui components
    private RecyclerView recyclerView;

    // vars
    private ArrayList<Note> notes = new ArrayList<>();
    private NotesRecyclerAdapter notesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        recyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();
        insertFakeNotes();

        setSupportActionBar((Toolbar)findViewById(R.id.notes_toolbar));
        setTitle("Notes");
    }

    private void insertFakeNotes(){
        for (int i = 0; i < 1000; i++) {
            Note note = new Note();
            note.setTitle("title #" + i);
            note.setContent("content #" + i);
            note.setTimestampe("Jan 2019");
            notes.add(note);
        }
        notesRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        VerticalSpacingItemDecorator verticalSpacingItemDecorator = new VerticalSpacingItemDecorator(10);
        recyclerView.addItemDecoration(verticalSpacingItemDecorator);
        recyclerView.setLayoutManager(linearLayoutManager);
        notesRecyclerAdapter = new NotesRecyclerAdapter(notes, this);
        recyclerView.setAdapter(notesRecyclerAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked." + position);

        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }
}