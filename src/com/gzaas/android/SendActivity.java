package com.gzaas.android;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gzaas.android.api.ApiKeyManager;
import com.gzaas.android.api.Connector;
import com.gzaas.android.style.Style;
import com.gzaas.android.widget.AlertDialogHelper;

public class SendActivity extends ApiActivity implements OnClickListener {
	
	private static final String KEY_URL = "url";
	private String				mURL;
	private EditText 			mUrlEditText;
	private ProgressBar 		mProgress;
    
    @Override    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        
        mUrlEditText = (EditText) findViewById(R.id.et_url);
        mProgress = (ProgressBar) findViewById(R.id.pb_loading);
        
        /* Call server */
        if ( savedInstanceState == null ) {
        	setLoading(true);
        	Bundle extras = getIntent().getExtras();
            String message = extras.getString(PreviewActivity.KEY_MESSAGE);
            Style style = (Style) extras.getSerializable(PreviewActivity.KEY_STYLE);
            callServer(message, style);
        }
        /* Show URL */
        else {
        	mURL = savedInstanceState.getString(KEY_URL);
        	setURLView();
        }
        
        /* Buttons */
        findViewById(R.id.bt_copy).setOnClickListener(this);
        findViewById(R.id.bt_mail).setOnClickListener(this);
        findViewById(R.id.bt_gnew).setOnClickListener(this);
    }
    
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_copy:
	    	ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
	    	clipboard.setText(mURL);
	    	Toast.makeText(this, "copy to clipboard",  Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.bt_mail:
			Intent email = new Intent(android.content.Intent.ACTION_SEND);
	        email.setType("plain/text");
	        email.putExtra(android.content.Intent.EXTRA_TEXT, mURL);
	        startActivity(Intent.createChooser(email, "Send mail..."));
			break;
			
		case R.id.bt_gnew:
			Intent home = new Intent(this, HomeActivity.class);
	    	startActivity(home);
	    	finish(); 	
			break;
		}
	}
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	outState.putString(KEY_URL, mURL);
    	super.onSaveInstanceState(outState);
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
    
    @Override
    protected void handleMessage(Message msg) {
    	setURLView();
    }
    
    /**
     * Call to the server to write the message.
     * @param message The message.
     * @param style The style of the message.
     */
    private void callServer(final String message, final Style style) {
    	Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					String apikey = ApiKeyManager.get(getApplicationContext()).read();
					Log.d("", "callServer() apikey=" + apikey);
					mURL = Connector.get().write(apikey, message, style);
					getHandler().sendEmptyMessage(0);
				} catch (ClientProtocolException e) {
				} catch (IOException e) {
				} catch (JSONException e) {
				} catch (URISyntaxException e) {
				} //TODO
			}
		});
    	thread.start();
    }
    
    private void setLoading(boolean loading) {
    	mUrlEditText.setVisibility(loading ? View.GONE : View.VISIBLE);
    	mProgress.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
    
    private void setURLView() {
    	setLoading(false);
    	mUrlEditText.setText(mURL);
    }

}