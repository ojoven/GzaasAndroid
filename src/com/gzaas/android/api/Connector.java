package com.gzaas.android.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.gzaas.android.style.Style;
import com.gzaas.android.util.Utils;

/**
 *	API Conector.
 */
public class Connector {
	
	private static final String METHOD_KEY 		= "http://gzaas.com/getapikey?contact=android-";
	private static final String METHOD_WRITE 	= "http://gzaas.com/api/v1/write?";
	private static final String KEY_GET_APIKEY	= "apiKey";
	private static final String KEY_APIKEY		= "apikey";
	private static final String KEY_MESSAGE		= "message";
	private static final String KEY_FONT		= "font";
	private static final String KEY_COLOR		= "color";
	private static final String KEY_BACKCOLOR	= "backcolor";
	private static final String KEY_URL			= "urlGzaas";
	
	private static Connector	sInstance = null;
	private DefaultHttpClient 	mHttpclient;
	
	private Connector () {	 
		mHttpclient = new DefaultHttpClient();
	}
	
	/**
	 * @return The unique instance of Connector.
	 */
	public static Connector get() {
		if ( sInstance == null )
			sInstance = new Connector();
		return sInstance;
	}
	
	/**
	 * @return The API Key to make calls to the API.
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws JSONException 
	 */
	public String apikey(String deviceId) throws URISyntaxException, ClientProtocolException, IOException, JSONException {
		HttpGet httpget = new HttpGet(new URI(METHOD_KEY +deviceId));
		HttpResponse response = mHttpclient.execute(httpget);
		HttpEntity resEntity = response.getEntity();
		InputStream is = resEntity.getContent();
		JSONObject json = new JSONObject(Utils.streamToString(is));
		resEntity.consumeContent();
		return json.getString(KEY_GET_APIKEY);
	}
	
	/**
	 * Write a new gzaas message.
	 * @param apikey The API Key.
	 * @param message The message.
	 * @param style The style of the message.
	 * @return The url of the new message.
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws JSONException 
	 * @throws URISyntaxException 
	 */
	public String write(String apikey, String message, Style style) throws ClientProtocolException, IOException, JSONException, URISyntaxException {
		final String EQUAL = "=";
		final String APPEND = "&";
		StringBuilder urlBuilder = new StringBuilder()
			.append(METHOD_WRITE)
			.append(KEY_APIKEY).append(EQUAL).append(apikey).append(APPEND)
			.append(KEY_MESSAGE).append(EQUAL).append(message).append(APPEND)
			.append(KEY_FONT).append(EQUAL).append(style.getFont()).append(APPEND)
			.append(KEY_COLOR).append(EQUAL).append(style.getColorURI()).append(APPEND)
			.append(KEY_BACKCOLOR).append(EQUAL).append(style.getBackgroundColorURI());

		Log.i("", "URL=" + urlBuilder.toString());
		
		HttpGet httpget = new HttpGet(URI.create(urlBuilder.toString()));
		HttpResponse response = mHttpclient.execute(httpget);

		HttpEntity resEntity = response.getEntity();
		InputStream is = resEntity.getContent();
		JSONObject json = new JSONObject(Utils.streamToString(is));
		resEntity.consumeContent();
		return json.getString(KEY_URL);
	}

}
