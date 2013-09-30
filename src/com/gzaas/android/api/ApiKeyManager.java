package com.gzaas.android.api;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Store the API Key.
 */
public class ApiKeyManager {
	
	private static final String			PREFS_TAG = "data";
	private static ApiKeyManager		sInstance = null;
	private SharedPreferences 			mPrefs;
	
	private ApiKeyManager(Context context) {
		mPrefs = context.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
	}
	
	/**
	 * @param context The aplication context.
	 * @return The unique instance of ApiKeyManager.
	 */
	public static ApiKeyManager get(Context context) {
		if ( sInstance == null )
			sInstance = new ApiKeyManager(context);
		return sInstance;
	}
	
	/**
	 * @return The API Key stored.
	 */
	public String read() {
		return mPrefs.getString(PREFS_TAG, "");
	}
	
	/**
	 * Store the API key.
	 * @param apikey The API key.
	 */
	public void write(String apikey) {
		mPrefs.edit().putString(PREFS_TAG, apikey).commit();
	}
	
}
