package com.gzaas.android;

import com.gzaas.android.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewActivity extends Activity {
    /** Called when the activity is first created. */
	
    private StyleDataHelper dh;
    private String message;
    private int stylePos;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);
        
        // We take the parameters from the Activity which called PreviewActivity
        Bundle extras = getIntent().getExtras();
        message = extras.getString("MESSAGE");
        stylePos = extras.getInt("STYLE_POS");
        
        // Escribimos el mensaje en el layout
        TextView text = (TextView)findViewById(R.id.message);
        text.setText(message);
        text.setTextSize(70);
        
        // Recogemos un estilo al azar desde la BD
        dh = new StyleDataHelper(this);
        dh.deleteAll();
        dh.insertDefault();
        Style style = dh.selectOne(stylePos);        
        
        // Tipografía del estilo
        String font = style.getFont();
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/"+font+".ttf");
        text.setTypeface(tf);
        
        // Color de fuente del estilo
        String color = style.getColor();
        text.setTextColor(Color.parseColor(color));
        
        // Color de fondo del estilo
        LinearLayout background = (LinearLayout)findViewById(R.id.preview_background);
        String backcolor = style.getBackcolor();
        background.setBackgroundColor(Color.parseColor(backcolor));

    }
    
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     * Creamos un menú
     */
    
    public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.styles:
			Intent intentStyles = new Intent(PreviewActivity.this, StylesActivity.class);
			intentStyles.putExtra("MESSAGE", message);
			startActivity(intentStyles);
			return true;
		/*
		case R.id.retype:
			Intent intentKeyboard = new Intent(PreviewActivity.this, KeyboardActivity.class);*/
		case R.id.send: 
			Intent intentSend = new Intent(PreviewActivity.this, SendActivity.class);
			intentSend.putExtra("MESSAGE", message);
			intentSend.putExtra("STYLES_POS", message);
			startActivity(intentSend);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}  
    
}