package com.gzaas.android;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

/**
 * Activity that mamage the API calls.
 */
public abstract class ApiActivity extends Activity {

	/**
     *	Api call handler.
     */
	private static class ApiHandler extends Handler {
            
        private final WeakReference<ApiActivity> mActivity;
        
        public ApiHandler(ApiActivity activity) {
                mActivity = new WeakReference<ApiActivity>(activity);
        }
        
        @Override
        public void handleMessage(Message msg) {
                mActivity.get().handleMessage(msg);
        }
            
    }
    
    private Handler mHandler = new ApiHandler(this);
    
    /**
     * @return The API handler.
     */
    protected Handler getHandler() {
    	return mHandler;
    }
    
    /**
     * Handle the messages.
     * @param msg The incoming message.
     */
	protected abstract void handleMessage(Message msg);
}
