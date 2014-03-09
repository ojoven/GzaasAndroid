package com.gzaas.android.widget;

import android.content.Context;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;

import com.gzaas.android.R;

public class EditDialog extends BaseDialog {
	
	/**
	 *	Interface definition for a callback to be invoked when the text is changed.
	 */
    public interface OnTextListener {
    	/**
    	 * Called when the has been changed.
    	 * @param text The text.
    	 */
        public void onTextChanged(String text);
    }
    
    private OnTextListener mListener;

	public EditDialog(Context context, String msg) {
		super(context);
		setContentView(R.layout.dialog_edit);
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		final EditText message = (EditText) findViewById(R.id.et_message);
		message.setText(msg);
		
		findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				if ( mListener != null )
					mListener.onTextChanged(message.getText().toString());
			}
		});
		findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
	
	/**
	 * Register a callback to be invoked when the text is changed.
	 * @param listener The callback that will run. 
	 */
	public void setOnTextChanged(OnTextListener listener) {
		mListener = listener;
	}

}
