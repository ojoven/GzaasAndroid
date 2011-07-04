package com.gzaas.android;

//import com.gzaas.android.R;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class SendActivity extends Activity {
    /** Called when the activity is first created. */
	
	private String message;
	private int stylePos;
	private StyleDataHelper dh;
	private Style style;
	private String urlApiCall;
    
    @Override    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        
        // Recogemos los parámetros pasados desde el Activity que ha llamado a Preview
        Bundle extras = getIntent().getExtras();
        message = extras.getString("MESSAGE");
        stylePos = extras.getInt("STYLE_POS");
        
        dh = new StyleDataHelper(this);
        Style style = dh.selectOne(stylePos);
        String font = style.getFont();
        String color = style.getColor();
        String backcolor = style.getBackcolor();
        String pattern = style.getPattern();
        String shadow = style.getShadow();
        
        // API KEY!!
        
        urlApiCall = "http://gzaas.com/api/v1/write";
        urlApiCall = urlApiCall+"?"+message;
        urlApiCall = urlApiCall+"&font="+font;
        urlApiCall = urlApiCall+"&color="+color;
        if (backcolor!="null") {
        	urlApiCall = urlApiCall+"&backcolor="+backcolor;		
        }
        else if (pattern!=null) {
            urlApiCall = urlApiCall+"&pattern="+pattern;        	
        }
        if (shadow!="null") {        
        	urlApiCall = urlApiCall+"&shadow="+shadow;
        }
        
        Toast.makeText(SendActivity.this, urlApiCall, Toast.LENGTH_SHORT).show();
        
    }  
    
}