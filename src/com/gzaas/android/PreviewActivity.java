package com.gzaas.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.gzaas.android.style.Style;
import com.gzaas.android.widget.ColorPickerDialog;
import com.gzaas.android.widget.ColorPickerDialog.OnColorChangedListener;
import com.gzaas.android.widget.EditDialog;
import com.gzaas.android.widget.EditDialog.OnTextListener;
import com.gzaas.android.widget.FontSelectorDialog;
import com.gzaas.android.widget.FontSelectorDialog.OnFontChangedListener;

/**
 *	Preview the message with selected style.
 */
public class PreviewActivity extends Activity {

	public static final String	KEY_MESSAGE = "text";
	public static final String	KEY_STYLE 	= "style";
	
	private String				mMessage;
	private Style				mStyle;
	private TextView 			mText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        
        if ( savedInstanceState == null ) {
	        Bundle extras = getIntent().getExtras();
	        mMessage = extras.getString(KEY_MESSAGE);
	        mStyle = (Style) extras.getSerializable(KEY_STYLE);
        }
        else {
        	mMessage = savedInstanceState.getString(KEY_MESSAGE);
        	mStyle = (Style) savedInstanceState.getSerializable(KEY_STYLE);
        }
        
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/"+mStyle.getFont()+".ttf");
        
        mText = (TextView) findViewById(R.id.message);
        mText.setText(mMessage);
        mText.setTextSize(70);
        mText.setTextColor(mStyle.getColor());
        mText.setTypeface(tf);
        mText.setBackgroundColor(mStyle.getBackgroundColor());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.preview, menu);
		return true;
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_styles:
			start(StylesActivity.class);
			return true;
			
		case R.id.menu_send:
			start(SendActivity.class);
			return true;
		
		case R.id.menu_color_background:
	        new ColorPickerDialog(this, new OnColorChangedListener() {
				
				@Override
				public void colorChanged(int color) {
					mText.setBackgroundColor(color);
					mStyle.setBackgroundColor(color);
				}
			}, mStyle.getBackgroundColor()).show();
			return true;
			
		case R.id.menu_color_text:
	        new ColorPickerDialog(this, new OnColorChangedListener() {
				
				@Override
				public void colorChanged(int color) {
					mText.setTextColor(color);
					mStyle.setColor(color);
				}
			}, mStyle.getColor()).show();
			return true;
			
		case R.id.menu_font:
			FontSelectorDialog dialog = new FontSelectorDialog(this);
			dialog.setOnFontChanged(new OnFontChangedListener() {
				
				@Override
				public void onFontChanged(String font) {
					Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/"+font+".ttf");
					mText.setTypeface(tf);
					mStyle.setFont(font);
				}
			});
			dialog.show();
			return true;
			
		case R.id.menu_text:
			EditDialog editdialog = new EditDialog(this, mMessage);
			editdialog.setOnTextChanged(new OnTextListener() {
				
				@Override
				public void onTextChanged(String text) {
					mText.setText(text);
					mMessage = text;
				}
			});
			editdialog.show();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if ( keyCode == KeyEvent.KEYCODE_BACK ) {
    		Intent intent = new Intent(this, HomeActivity.class);
    		startActivity(intent);
    		finish();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	outState.putString(KEY_MESSAGE, mMessage);
    	outState.putSerializable(KEY_STYLE, mStyle);
    	super.onSaveInstanceState(outState);
    }
    
	private void start(Class<? extends Activity> clazz) {
		Intent intent = new Intent(this, clazz);
		intent.putExtra(KEY_MESSAGE, mMessage);
		intent.putExtra(KEY_STYLE, mStyle);
		startActivity(intent);
		finish();
	}
	
}