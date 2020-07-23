package com.dmb.notes;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmb.notes.models.Note;


public class NoteActivity extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener,
        View.OnClickListener
{

    private static final String TAG = "NoteActivity";
    private static final int EDIT_MODE_ENABLED = 1;
    private static final int EDIT_MODE_DISABLED = 0;

    // ui components
    private LinedEditText linedEditText;
    private EditText editTitle;
    private TextView viewTitle;
    private RelativeLayout checkContainer, backArrorContainer;
    private ImageButton check, backArror;

    // vars
    private boolean isNewNote;
    private Note noteInitial;
    private GestureDetector gestureDetector;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        linedEditText = findViewById(R.id.note_text);
        editTitle = findViewById(R.id.note_edit_title);
        viewTitle = findViewById(R.id.note_text_title);
        checkContainer = findViewById(R.id.check_container);
        backArrorContainer = findViewById(R.id.back_arrow_container);
        check = findViewById(R.id.toolbar_check);
        backArror = findViewById(R.id.toolbar_back_arrow);

        if (getIncomingIntent()) {
            // this is a new note, (EDIT MODE)
            setNewNoteProperties();
            enableEditMode();
        } else {
            // this is NOT a new note, (VIEW MODE)
            setNoteProperties();
            disableContentInteraction();
        }

        setListeners();
    }

    private void setListeners() {
        linedEditText.setOnTouchListener(this);
        gestureDetector = new GestureDetector(this, this);
        viewTitle.setOnClickListener(this);
        check.setOnClickListener(this);
    }

    private boolean getIncomingIntent() {
        if (getIntent().hasExtra("selected_note")) {
            noteInitial = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: " + noteInitial.toString());

            mode = EDIT_MODE_DISABLED;
            isNewNote = false;
            return false;
        }
        mode = EDIT_MODE_ENABLED;
        isNewNote = true;
        return true;
    }

    private void disableContentInteraction(){
        linedEditText.setKeyListener(null);
        linedEditText.setFocusable(false);
        linedEditText.setFocusableInTouchMode(false);
        linedEditText.setCursorVisible(false);
        linedEditText.clearFocus();
    }

    private void enableContentInteraction(){
        linedEditText.setKeyListener(new EditText(this).getKeyListener());
        linedEditText.setFocusable(true);
        linedEditText.setFocusableInTouchMode(true);
        linedEditText.setCursorVisible(true);
        linedEditText.requestFocus();
    }

    private void enableEditMode() {
        backArrorContainer.setVisibility(View.GONE);
        checkContainer.setVisibility(View.VISIBLE);

        viewTitle.setVisibility(View.GONE);
        editTitle.setVisibility(View.VISIBLE);

        mode = EDIT_MODE_ENABLED;

        enableContentInteraction();
    }

    private void disableEditMode() {
        backArrorContainer.setVisibility(View.VISIBLE);
        checkContainer.setVisibility(View.GONE);

        viewTitle.setVisibility(View.VISIBLE);
        editTitle.setVisibility(View.GONE);

        mode = EDIT_MODE_DISABLED;

        disableContentInteraction();
    }

    private void hideSoftKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null){
            view = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG, "onDoubleTap: double tapped!");
        enableEditMode();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_check: {
                hideSoftKeyboard();
                disableEditMode();
                break;
            }
            case R.id.note_text_title: {
                enableEditMode();
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mode == EDIT_MODE_ENABLED) {
            onClick(check);
        } else {
            super.onBackPressed();
        }
    }
}