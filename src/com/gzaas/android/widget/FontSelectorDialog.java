package com.gzaas.android.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gzaas.android.R;
import com.gzaas.android.style.DefaultStyle;
import com.gzaas.android.style.Style;

/**
 *	Font selector dialog.
 */
public class FontSelectorDialog extends BaseDialog {
	
	/**
	 *	Interface definition for a callback to be invoked when a font is selected.
	 */
    public interface OnFontChangedListener {
    	/**
    	 * Called when a font has been selected.
    	 * @param font The font.
    	 */
        void onFontChanged(String font);
    }
    
    private OnFontChangedListener mListener;

	public FontSelectorDialog(Context context) {
		super(context);
		ListView lv = new ListView(getContext());
		lv.setBackgroundColor(getContext().getResources().getColor(R.color.translucent_black));
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
				TextView _convertView = (TextView) convertView;
				if ( _convertView == null )
					_convertView = new TextView(getContext());
				
				final Style style = ((DefaultStyle) getItem(position)).style();
				Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/"+ style.getFont() +".ttf");
				int width = parent.getWidth();
				
				_convertView.setWidth(width);
				_convertView.setText(style.getName());
				_convertView.setTag(position);
				_convertView.setTextSize(24);
				_convertView.setGravity(Gravity.CENTER);
				_convertView.setPadding(0, 20, 0, 20);
				_convertView.setClickable(true);        
				_convertView.setTextColor(Color.WHITE);
	            _convertView.setBackgroundResource(R.drawable.bg_selected);       
	            _convertView.setTypeface(tf);
	            _convertView.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dismiss();
						if (mListener != null)
							mListener.onFontChanged(style.getFont());
					}
				});
				return _convertView;
			}
		});
		setContentView(lv);
	}
	
	/**
	 * Register a callback to be invoked when a font is selected.
	 * @param listener The callback that will run. 
	 */
	public void setOnFontChanged(OnFontChangedListener listener) {
		mListener = listener;
	}

}
