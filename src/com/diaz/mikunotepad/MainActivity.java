package com.diaz.mikunotepad;
import com.diaz.miku.notepad.R;
import java.util.ArrayList;
import java.util.HashSet;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	static ArrayList<String> notes = new ArrayList<String>();
	static ArrayAdapter<String> arrayAdapter;
	
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ListView listview = (ListView) findViewById(R.id.listView1);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.androidcycle",Context.MODE_PRIVATE); 
        HashSet<String> set =(HashSet<String>) sharedPreferences.getStringSet("notes", null);
        
        if(set == null){
        	notes.add("Tap here to edit...");
        }
        else{
        	notes = new ArrayList(set);
        }
        
        notes.add("Tap here to edit...");
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.rows, notes);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,int i, long l){
			Intent intent = new Intent(getApplicationContext(),NoteEdit.class);
			intent.putExtra("noteId",i);
			startActivity(intent);
		}
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        	@Override
        	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2,long arg3) {
        		// TODO Auto-generated method stub
        		new AlertDialog.Builder(MainActivity.this)
        			.setTitle("Delete note")
        			.setMessage("Do you want to delete this note?")
        			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							notes.remove(arg2);
							arrayAdapter.notifyDataSetChanged();
							SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.androidcycle",Context.MODE_PRIVATE);
							HashSet<String> set = new HashSet<String>(MainActivity.notes);
							sharedPreferences.edit().putStringSet("notes",set).apply();
						}
					})
        			.setNegativeButton("No",null)
        			.show();
        			
        		return true;
        	}
		});
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(getApplicationContext(),NoteEdit.class);
        		startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
    	super.onOptionsItemSelected(item);
    	if(item.getItemId()==R.id.add_note){
    		Intent intent = new Intent(getApplicationContext(),NoteEdit.class);
    		startActivity(intent);
    		return true;
    	}
    	else if(item.getItemId()==R.id.about_me){
    		Intent intent = new Intent(getApplicationContext(),AboutMe.class);
    		startActivity(intent);
    		return true;
    	}
    	return false;
    }
    


    
}
