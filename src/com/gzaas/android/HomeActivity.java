package com.gzaas.android;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gzaas.android.api.ApiKeyManager;
import com.gzaas.android.api.Connector;
import com.gzaas.android.style.Style;
import com.gzaas.android.widget.AlertDialogHelper;
import com.gzaas.android.widget.BaseProgressDialog;

public class HomeActivity extends ApiActivity {
	
	private static final String		TAG = HomeActivity.class.getName();
	private BaseProgressDialog		mProgressDialog;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        final EditText messageEditText = (EditText) findViewById(R.id.et_message);
        Button startBtn = (Button) findViewById(R.id.btn_start);
        TextView sloganTextView = (TextView) findViewById(R.id.tv_slogan);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/chewy.ttf");
        sloganTextView.setTypeface(tf);
        startBtn.setTypeface(tf);
        startBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String text = messageEditText.getText().toString();
				if ( text.length() > 0 ) {
					Intent intent = new Intent(HomeActivity.this, PreviewActivity.class);
					intent.putExtra(PreviewActivity.KEY_MESSAGE, text);
					intent.putExtra(PreviewActivity.KEY_STYLE, Style.random());
					startActivity(intent);
					finish();
				}
			}
		});
        
        /* If no have an API Key call to server to obtain one */
        if ( ApiKeyManager.get(this).read().length() == 0 )
        	callServer();
    }
    
    @Override
	public boolean onCreateOptionsMenu (Menu opcion) {
		opcion.add(0, 1, 0, R.string.menu_info).setIcon(android.R.drawable.ic_menu_info_details);
    	return true;
	}
	   
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   	switch ( item.getItemId() ) {
	   	case 1:
	   		AlertDialog dialog = AlertDialogHelper.create(this, R.string.info_title, R.string.info_message);
	   		dialog.setButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {}
	   		});
	   		dialog.show();
	   	}
	    return super.onOptionsItemSelected(item);
	}
	
	protected void handleMessage(Message msg) {
		mProgressDialog.dismiss();
	}
	
	/**
	 * Call to the server to obain the API Key.
	 */
	private void callServer() {
		mProgressDialog = new BaseProgressDialog(this);
		mProgressDialog = new BaseProgressDialog(this);
		mProgressDialog.setMessage(R.string.loading);
		mProgressDialog.show();
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					String apikey = Connector.get().apikey();
					Log.d(TAG, "apikey=" + apikey);
					ApiKeyManager.get(getApplicationContext()).write(apikey);
					getHandler().sendEmptyMessage(0);
				} catch (ClientProtocolException e) {
				} catch (URISyntaxException e) {
				} catch (IOException e) {
				} catch (JSONException e) {
				} //TODO
			}
		});
		thread.start();
	}

}