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

	/**
	 * Set the message of the dialog.
	 * @param msg The message.
	 */
	public void setMessage (String msg) {
		mMessage.setText(msg);
	}

	/**
	 * Set the message from resources.
	 * @param resId The resource id.
	 */
	public void setMessage (int resId) {
		mMessage.setText(resId);
	}

}
