package com.gzaas.android.widget;

import android.content.Context;
import android.widget.TextView;

import com.gzaas.android.R;

/**
 * Simple progress dialog.
 */
public class BaseProgressDialog extends BaseDialog {

	private TextView		mMessage;

	public BaseProgressDialog (Context context) {
		super (context);
		setContentView(R.layout.layout_base_progress_dialog);
		setCancelable(false);
		mMessage = (TextView) findViewById(R.id.message);
	}

	public void setMessage (String msg) {
		mMessage.setText(msg);
	}

	public void setMessage (int resId) {
		mMessage.setText(resId);
	}

}
