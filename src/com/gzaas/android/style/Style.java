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
	
	/**
	 * @return The name of the {@link DefaultStyle} that is based.
	 */
	public String getName() {
		return mName;
	}

	/**
	 * @return The font.
	 */
	public String getFont() {
		return mFont;
	}
	
	/**
	 * @return The color.
	 */
	public int getColor() {
		return Color.parseColor(mColor);
	}

	/**
	 * @return The color in string format: #1a1a1a.
	 */
	public String getColorString() {
		return mColor;
	}
	
	/**
	 * @return The background color.
	 */
	public int getBackgroundColor() {
		return Color.parseColor(mBackgroundColor);
	}

	/**
	 * @return The background color in string format: #1a1a1a.
	 */
	public String getBackgroundColorString() {
		return mBackgroundColor;
	}
	
	/**
	 * Set the color.
	 * @param color The color.
	 */
	public void setColor(int color) {
		mColor = SHARP + Integer.toHexString(color);
	}
	
	/**
	 * Set the background color.
	 * @param color The color.
	 */
	public void setBackgroundColor(int color) {
		mBackgroundColor = SHARP + Integer.toHexString(color);
	}
	
	/**
	 * Set the font.
	 * @param font The font.
	 */
	public void setFont(String font) {
		mFont = font;
	}

	@Override
	public String toString() {
		return "Style [name=" + mName + ", font=" + mFont + ", color="
				+ mColor + ", backgroundColor=" + mBackgroundColor + "]";
	}

	/**
	 * @return A predefined random style from {@link DefaultStyle}. 
	 */
	public static Style random() {
		int index = (int) (Math.random() * DefaultStyle.values().length);
		return DefaultStyle.values()[index].style();
	}

}
