package com.dmb.notes;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.dmb.notes.adapters.NotesRecyclerAdapter;
import com.dmb.notes.models.Note;
import com.dmb.notes.persistence.NoteRepository;
import com.dmb.notes.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class NotesListActivity extends AppCompatActivity implements
        NotesRecyclerAdapter.OnNoteListener,
        View.OnClickListener
{

    private static final String TAG = "NotesListActivity";

    // ui components
    private RecyclerView recyclerView;

    // vars
    private ArrayList<Note> notes = new ArrayList<>();
    private NotesRecyclerAdapter notesRecyclerAdapter;
    private NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        recyclerView = findViewById(R.id.recyclerView);

        findViewById(R.id.fab).setOnClickListener(this);

        noteRepository = new NoteRepository(this);

        initRecyclerView();
        retrieveNotes();
//        insertFakeNotes();

        setSupportActionBar((Toolbar)findViewById(R.id.notes_toolbar));
        setTitle("Notes");
    }

    private void retrieveNotes(){
        noteRepository.retrieveNotesTask().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                if(notes.size() > 0){
                    notes.clear();
                }
                if(notes != null){
                    notes.addAll(notes);
                }
                notesRecyclerAdapter.notifyDataSetChanged();
            }
        });
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
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        notesRecyclerAdapter = new NotesRecyclerAdapter(notes, this);
        recyclerView.setAdapter(notesRecyclerAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked." + position);

        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("selected_note", notes.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }

    private void deleteNote(Note note) {
        notes.remove(note);
        notesRecyclerAdapter.notifyDataSetChanged();
    }

    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            deleteNote(notes.get(viewHolder.getAdapterPosition()));
        }
    };
}