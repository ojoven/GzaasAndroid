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
	private String mBackgroundColor;

	void setDefault(DefaultStyle style) {
		mName = style.name;
		mFont = style.font;
		mColor = style.color;
		mBackgroundColor = style.backcolor;
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
	
	public int getBackgroundColor() {
		return Color.parseColor(mBackgroundColor);
	}

	public String getBackgroundColorString() {
		return mBackgroundColor;
	}
	
	public void setColor(int color) {
		mColor = SHARP + Integer.toHexString(color);
	}
	
	public void setBackgroundColor(int color) {
		mBackgroundColor = SHARP + Integer.toHexString(color);
	}
	
	public void setFont(String font) {
		mFont = font;
	}

	@Override
	public String toString() {
		return "Style [name=" + mName + ", font=" + mFont + ", color="
				+ mColor + ", backgroundColor=" + mBackgroundColor + "]";
	}

	/**
	 * @return A predefined random style. 
	 */
	public static Style random() {
		int index = (int) (Math.random() * DefaultStyle.values().length);
		return DefaultStyle.values()[index].style();
	}

}
