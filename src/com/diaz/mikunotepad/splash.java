package com.diaz.mikunotepad;
import com.diaz.miku.notepad.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class splash extends Activity {
	
	private boolean bckbtnpress;
	private static final int SPLASHSCREEN_DURATION = 3000;
	public Handler myHandler;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        
        myHandler = new Handler();
        
        myHandler.postDelayed(new Runnable(){
        	
        	@Override
        	public void run(){
        		finish();
        		
        		if (!bckbtnpress){
        			Intent intent = new Intent(splash.this,MainActivity.class);
        			splash.this.startActivity(intent);
        		}
        	}
        }, SPLASHSCREEN_DURATION);
    }
	
	public void onBackpress(){
		super.onBackPressed();
		bckbtnpress = true;
	}
}
