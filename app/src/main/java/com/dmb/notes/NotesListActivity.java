package com.dmb.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dmb.notes.adapters.NotesRecyclerAdapter;
import com.dmb.notes.models.Note;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity {

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
        recyclerView.setLayoutManager(linearLayoutManager);
        notesRecyclerAdapter = new NotesRecyclerAdapter(notes);
        recyclerView.setAdapter(notesRecyclerAdapter);
    }
}