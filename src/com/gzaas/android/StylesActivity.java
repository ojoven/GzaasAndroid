package com.gzaas.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gzaas.android.style.DefaultStyle;
import com.gzaas.android.style.Style;

public class StylesActivity extends Activity {

    @Override    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styles);
        
        Bundle extras = getIntent().getExtras();
        final String message = extras.getString(PreviewActivity.KEY_MESSAGE);
        
        ListView lv = (ListView) findViewById(R.id.lv_styles);
        lv.setAdapter(new BaseAdapter() {
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return DefaultStyle.values()[position];
			}
			
			@Override
			public int getCount() {
				return DefaultStyle.values().length;
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView _covertView = (TextView) convertView;
				if ( _covertView == null )
					_covertView = new TextView(getApplicationContext());
				
				final Style style = ((DefaultStyle) getItem(position)).style();
				Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/"+ style.getFont() +".ttf");
				
				_covertView.setText(style.getName());
				_covertView.setTag(position);
				_covertView.setTextSize(24);
				_covertView.setGravity(Gravity.CENTER);
				_covertView.setPadding(0, 20, 0, 20);
				_covertView.setClickable(true);        
				_covertView.setTextColor(style.getColor());
	            _covertView.setBackgroundColor(style.getBackcolor());       
	            _covertView.setTypeface(tf);
	            _covertView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getApplicationContext(), PreviewActivity.class);
						intent.putExtra(PreviewActivity.KEY_MESSAGE, message);
						intent.putExtra(PreviewActivity.KEY_STYLE, style);
						startActivity(intent);
						finish();
					}
				});
				return _covertView;
			}
		});
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if ( keyCode == KeyEvent.KEYCODE_BACK ) {
    		Intent intent = new Intent(this, PreviewActivity.class);
    		intent.putExtras(getIntent().getExtras());
    		startActivity(intent);
    		finish();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    
}