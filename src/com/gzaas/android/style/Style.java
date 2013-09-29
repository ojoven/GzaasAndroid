package com.gzaas.android.style;

import java.io.Serializable;

import android.graphics.Color;

/**
 * The style of the message.
 */
public class Style implements Serializable {

	private static final long serialVersionUID = 7397617962458923837L;
	private static final char SHARP = '#';

	private String mName;
	private String mFont;
	private String mColor;
	private String mBackcolor;

	void setDefault(DefaultStyle style) {
		mName = style.name;
		mFont = style.font;
		mColor = style.color;
		mBackcolor = style.backcolor;
	}
	
	public String getName() {
		return mName;
	}

	public String getFont() {
		return mFont;
	}
	
	public int getColor() {
		return Color.parseColor(mColor);
	}

	public String getColorString() {
		return mColor;
	}
	
	public int getBackcolor() {
		return Color.parseColor(mBackcolor);
	}

	public String getBackcolorString() {
		return mBackcolor;
	}
	
	public void setColor(int color) {
		mColor = SHARP + Integer.toHexString(color);
	}
	
	public void setBackgroundColor(int color) {
		mBackcolor = SHARP + Integer.toHexString(color);
	}
	
	public void setFont(String font) {
		mFont = font;
	}

	/**
	 * @return A predefined random style. 
	 */
	public static Style random() {
		int index = (int) (Math.random() * DefaultStyle.values().length);
		return DefaultStyle.values()[index].style();
	}

}
