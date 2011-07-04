package com.gzaas.android;


public class Style {

	private String name;
	private String font;
	private String color;
	private String backcolor;
	private String pattern;
	private String shadow;

    public String getShadow() {
		return shadow;
	}

	public void setShadow(String shadow) {
		this.shadow = shadow;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackcolor() {
		return backcolor;
	}

	public void setBackcolor(String backcolor) {
		this.backcolor = backcolor;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	// Default constructor
    public Style() {

    }
    
    public Style getRandomStyle(){
    	//private String font;
    	return this;
    }
    
    
	
}
