package com.dmb.notes.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.dmb.notes.models.Note;

import java.util.List;

public class NoteRepository {

    private NoteDatabase noteDatabase;

    public NoteRepository(Context context){
        noteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Note note){

    }

    public void updateNote(Note note){

    }

    public LiveData<List<Note>> retrieveNotesTask(){
        return null;
    }

    public void deleteNote(Note note) {

    }
}
