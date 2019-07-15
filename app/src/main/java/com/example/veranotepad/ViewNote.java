package com.example.veranotepad;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.veranotepad.database.DatabaseHelper;
import com.example.veranotepad.database.Note;

public class ViewNote extends AppCompatActivity {
    int noteId;

    TextView tvtitle;
    TextView tvNoteText;
    Button btnDelete;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getNoteId();
        tvtitle = findViewById(R.id.title);
        tvNoteText = findViewById(R.id.NoteText);
        btnDelete = findViewById(R.id.delete);
        btnEdit = findViewById(R.id.edit);
        displayNotes();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper =new DatabaseHelper(getBaseContext(),"Delete",null,1);
                databaseHelper.deleteNote(6);
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),EditNoteActivity.class);
                intent.putExtra("Note_ID",noteId);
                startActivity(intent);
            }
        });


        }





    public void getNoteId() {
        Bundle bundle = getIntent().getExtras();
        if (bundle !=null) {
            noteId = bundle.getInt("NOTE_ID",0);
        }
    }
    public void displayNotes () {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(), "notes", null, 1);
        Note note = databaseHelper.getNoteById(noteId);
        tvtitle.setText(note.getTitle());
        tvNoteText.setText(note.getNoteText());
    }
}
