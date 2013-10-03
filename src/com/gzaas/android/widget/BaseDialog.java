package com.gzaas.android.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

/**
 * Empty base dialog.
 */
public abstract class BaseDialog extends Dialog {

	public BaseDialog(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
	}
	
}