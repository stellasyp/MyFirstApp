package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	 public final static String EXTRA_MESSAGE1 = "com.example.myfirstapp.MESSAGE1";
	 public final static String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    /** Called when the user clicks the Send button */
    public void askPnjupPassword(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, askPPassActivity.class);
    	/*EditText editName = (EditText) findViewById(R.id.edit_Name);
      	EditText editPassword = (EditText) findViewById(R.id.edit_Password);
    	
    	String name = editName.getText().toString();
    	String password = editPassword.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE1, name);
    	intent.putExtra(EXTRA_MESSAGE2, password);*/
    	startActivity(intent);
    }
    public void log_in(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, log_in.class);
    	EditText editName = (EditText) findViewById(R.id.edit_Name);
      	EditText editPassword = (EditText) findViewById(R.id.edit_Password);
    	
    	String name = editName.getText().toString();
    	String password = editPassword.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE1,name);
    	intent.putExtra(EXTRA_MESSAGE2,password);
    	startActivity(intent);
    }
    
}
