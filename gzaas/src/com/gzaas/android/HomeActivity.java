package com.gzaas.android;

import com.gzaas.android.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;
import android.view.View.OnClickListener;

public class HomeActivity extends Activity {
	
	public Intent intentPreview;
	public Button bt;
	
	private int MaxStyles = 7;
	private int RandStylePosition;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // We say to the Activity which View (layout/*.xml) to render
        setContentView(R.layout.main);
        
        /* Use the typeface Chewy */
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/chewy.ttf");
        TextView tv = (TextView) findViewById(R.id.slogan);
        bt = (Button) findViewById(R.id.button1);
		tv.setTypeface(tf);
		bt.setTypeface(tf);	
		
		// We create a new Intent, to use it when button is clicked.
		intentPreview = new Intent(this, PreviewActivity.class);
		
		// We get the button from the View by its ID
		Button button = (Button)findViewById(R.id.button1);		
    	
	    // Register the onClick listener with the implementation above
	    button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Get the message from the Input (EditText is called in Android)
				String message;
				EditText messageForm = (EditText) findViewById(R.id.editText1);
				message = messageForm.getText().toString();
				
				// Random style rendering
		        RandStylePosition = (int) (MaxStyles * Math.random());
		        
		        // We pass the parameters to the Intent
				intentPreview.putExtra("MESSAGE", message);
				intentPreview.putExtra("STYLE_POS", RandStylePosition);
				
				// We call the activity by its assigned intent
		    	startActivity(intentPreview);
			}
		});

    }
  
    
}