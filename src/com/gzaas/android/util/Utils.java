package com.gzaas.android.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 *	Utils.
 */
public class Utils {
    
	/**
	 * Convert a inputstream to a string.
	 * @param is The inputstream.
	 * @return Thew string.
	 * @throws IOException
	 */
	public static String streamToString(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    while ( (line = reader.readLine()) != null ) {
	    	sb.append(line + "\n");
	    }
	    is.close();
	    return sb.toString();
	}
    
	/**
	 * @param context The aplication context.
	 * @return True if the device is online.
	 */
    public static boolean isOnline(Context context) {
    	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
    
}
