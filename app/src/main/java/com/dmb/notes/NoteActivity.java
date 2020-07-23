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
    private Note noteInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        linedEditText = findViewById(R.id.note_text);
        editTitle = findViewById(R.id.note_edit_title);
        viewTitle = findViewById(R.id.note_text_title);

        if (getIncomingIntent()) {
            // this is a new note, (EDIT MODE)
            setNewNoteProperties();
        } else {
            // this is NOT a new note, (VIEW MODE)
            setNoteProperties();
        }
    }

    private boolean getIncomingIntent() {
        if (getIntent().hasExtra("selected_note")) {
            noteInitial = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: " + noteInitial.toString());

            isNewNote = false;
            return false;
        }
        isNewNote = true;
        return true;
    }

    private void setNewNoteProperties() {
        viewTitle.setText("Note Title");
        editTitle.setText("Note Title");
    }

    private void setNoteProperties() {
        viewTitle.setText(noteInitial.getTitle());
        editTitle.setText(noteInitial.getTitle());
        linedEditText.setText(noteInitial.getContent());
    }
}