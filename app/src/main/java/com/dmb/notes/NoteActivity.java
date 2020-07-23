package com.dmb.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.dmb.notes.models.Note;


public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";

    // ui components
    private LinedEditText linedEditText;
    private EditText editTitle;
    private TextView viewTitle;

    // vars
    private boolean isNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        if (getIncomingIntent()) {
            // this is a new note, (EDIT MODE)

        } else {
            // this is NOT a new note, (VIEW MODE)

        }
    }

    private boolean getIncomingIntent() {
        if (getIntent().hasExtra("selected_note")) {
            Note incomingNote = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: " + incomingNote.toString());

            isNewNote = false;
            return false;
        }
        isNewNote = true;
        return true;
    }
}