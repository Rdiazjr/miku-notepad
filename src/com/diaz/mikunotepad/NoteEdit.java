package com.diaz.mikunotepad;
import com.diaz.miku.notepad.R;
import java.util.HashSet;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class NoteEdit extends Activity {
	int noteId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_edit);
		
EditText editText = (EditText) findViewById(R.id.editText1);
		
		Intent intent = getIntent();
		noteId = intent.getIntExtra("noteId",-1);
		if(noteId!=-1){
			editText.setText(MainActivity.notes.get(noteId));
			
		}
		else{
			MainActivity.notes.add("");
			noteId = MainActivity.notes.size()-1;
			MainActivity.arrayAdapter.notifyDataSetChanged();
		}
		
		editText.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
				// TODO Auto-generated method stub
				
			}

			@SuppressLint("NewApi")
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				MainActivity.notes.set(noteId, String.valueOf(arg0));
				MainActivity.arrayAdapter.notifyDataSetChanged();
				
				SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.androidcycle",Context.MODE_PRIVATE);
				HashSet<String> set = new HashSet<String>(MainActivity.notes);
				sharedPreferences.edit().putStringSet("notes",set).apply();
			}
		});
	}

}
