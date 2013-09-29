package com.gzaas.android.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.gzaas.android.style.Style;
import com.gzaas.android.util.Utils;

/**
 *	API Conector.
 */
public class Connector {
	
	private static final String METHOD_KEY 		= "http://gzaas.com/getapikey?contact=android";
	private static final String METHOD_WRITE 	= "http://gzaas.com/api/v1/write";
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
	public String apikey() throws URISyntaxException, ClientProtocolException, IOException, JSONException {
		HttpGet httpget = new HttpGet(new URI(METHOD_KEY));
		HttpResponse response = mHttpclient.execute(httpget);
		HttpEntity resEntity = response.getEntity();
		InputStream is = resEntity.getContent();
		JSONObject json = new JSONObject(Utils.streamToString(is));
		resEntity.consumeContent();
		return json.getString(KEY_GET_APIKEY);
	}
	
	/**
	 * Post a new gzaas message.
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
		ArrayList<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair(KEY_APIKEY, apikey));
		parameters.add(new BasicNameValuePair(KEY_MESSAGE, message));
		parameters.add(new BasicNameValuePair(KEY_FONT, style.getFont()));
		parameters.add(new BasicNameValuePair(KEY_COLOR, style.getColorString()));
		parameters.add(new BasicNameValuePair(KEY_BACKCOLOR, style.getBackgroundColorString()));
		
		UrlEncodedFormEntity data = new UrlEncodedFormEntity(parameters);
		HttpPost httppost = new HttpPost(new URI(METHOD_WRITE));
		httppost.setEntity(data);
		HttpResponse response = mHttpclient.execute(httppost);

		HttpEntity resEntity = response.getEntity();
		InputStream is = resEntity.getContent();
		JSONObject json = new JSONObject(Utils.streamToString(is));
		resEntity.consumeContent();
		return json.getString(KEY_URL);
	}

}
