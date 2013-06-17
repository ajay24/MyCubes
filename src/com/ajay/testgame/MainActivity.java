package com.ajay.testgame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	LinearLayout container;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	
        getActionBar().hide();        
        setContentView(R.layout.main);
        
        MyButton button1=(MyButton) findViewById(R.id.button1);
    	MyButton button2=(MyButton) findViewById(R.id.button2);
    	
        
    	button1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),GameActivity.class);
				//Toast.makeText(getApplicationContext(), "sd", Toast.LENGTH_SHORT).show();
				startActivity(intent);
				
			}
		});
    	
    	
    	button2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				try {
					wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					finish();
				
			}
		});
        
        
    }
       
}
