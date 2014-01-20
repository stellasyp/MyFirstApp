package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class askPPassActivity extends Activity{
	
	public final static String EXTRA_MESSAGE1 = "com.example.myfirstapp.MESSAGE1";
	 public final static String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";
	 
	 private SharedPreferences sharedPreferences;//保存用户名和密码  
	 private SharedPreferences.Editor editor;  
	 
@SuppressWarnings("deprecation")
@SuppressLint("NewApi")
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.askfornameandpassword);

	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    // If your minSdkVersion is 11 or higher, instead use:
	    // getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    sharedPreferences = this.getSharedPreferences("test",MODE_PRIVATE);  
        editor = sharedPreferences.edit();
        
        EditText editName = (EditText) findViewById(R.id.edit_Name);
        EditText editPassword = (EditText) findViewById(R.id.edit_Password);
  	
        String namesave = sharedPreferences.getString("name", "");  
        String passwordsave = sharedPreferences.getString("password","");  
        
        editPassword.setText(String.valueOf(passwordsave));  
        editName.setText(String.valueOf(namesave));  
        //Log.d("asd.ksjhnl", namesave);
        
        
	}
	public void log_in(View view) {
    // Do something in response to button
	Intent intent = new Intent(this, log_in.class);
	
	EditText editName = (EditText) findViewById(R.id.edit_Name);
    EditText editPassword = (EditText) findViewById(R.id.edit_Password);
	
	String name = editName.getText().toString();
	String password = editPassword.getText().toString();	
//	Log.d("enter the name ", name);
//	Log.d("enter the password ",password);
	
	if(((CheckBox)findViewById(R.id.radioButton1)).isChecked()==true)//.Checked==true)
		{
		   	//editor.clear();
			editor.putString("password",password);  
			editor.putString("name",name);  
			editor.commit(); 
			Log.d("enter the name ", name);
			Log.d("enter the password ",password);
		}
     
     
	intent.putExtra(EXTRA_MESSAGE1,name);
	intent.putExtra(EXTRA_MESSAGE2,password);
	startActivity(intent);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EditText editName = (EditText) findViewById(R.id.edit_Name);
	    EditText editPassword = (EditText) findViewById(R.id.edit_Password);
		
		String name = editName.getText().toString();
		String password = editPassword.getText().toString();	
		if(findViewById(R.id.radioButton1).isSelected()==true)//.Checked==true)
		{
		   	//editor.clear();
			editor.putString("password",password);  
			editor.putString("name",name);  
			editor.commit();  
		}
	}

	


}

