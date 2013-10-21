package com.gzaas.android.widget;

import android.content.Context;

import com.gzaas.android.R;

/**
 * Simple progress dialog.
 */
public class ProgressDialog extends BaseDialog {

	public ProgressDialog (Context context) {
		super (context);
		setContentView(R.layout.dialog_progress);
		setCancelable(false);
	}
}
