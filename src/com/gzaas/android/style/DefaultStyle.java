package com.gzaas.android.style;

/**
 * The predefined styles.
 */
public enum DefaultStyle {
	
   CHEWY_PINK		("Chewy Pink",		"chewy",				"#ffffff",	"#FE28A2"),
   COMING_BLUE		("Coming Blue",		"comingsoon", 			"#ffffff",	"#00287B"),
   LUCKY_YELLOW		("Lucky Yellow",	"luckiestguy",			"#000000",	"#FFE303"),
   KRANKY_SIMPLE	("Kranky Simple",	"kranky", 				"#000000",	"#ffffff"),
   CHRISTAMS		("It's Christmas!",	"mountainsofchristmas", "#ffffff",	"#EF0401"),
   FAIL				("You failed!",		"impactlabel", 			"#000000",	"#EF0401"),  
   GARDEN_MARKER	("Garden Marker",	"permanentmarker", 		"#4C2B11",	"#F8FF0D"),
   TRIBECA			("Tribeca",			"tribeca",				"#eeee00",	"#cc9933"),		
   DISTANCT_GALAY	("Distant Galaxy",	"distantgalaxy",		"#eebb22",	"#221122"),			
   COMIC_ZINE		("Comic Zine",		"comiczine",			"#aa11dd",	"#ffffff"),
   DUMB				("3Dumb",			"3dumb",				"#000000",	"#a58f82"),
   BLODY_SCARY		("Bloody & Scary",	"bloody",				"#cc0011",	"#000000"),	
   SLACKEY			("Slackey",			"slackey",				"#ffffff",	"#99c9cc"),
   ORBITRON			("Orbitron",		"orbitron",				"#000000",	"#55ccff"),
   REENIE_BEANIE	("Reenie Beanie",	"reeniebeanie",			"#77aaff",	"#116614");
   
   String name;
   String font;
   String color;
   String backcolor;
   
   private DefaultStyle(String name, String font, String color, String backcolor) {
	   this.name = name;
	   this.font = font;
	   this.color = color;
	   this.backcolor = backcolor;
   }
   
   public Style style() {
	   Style style = new Style();
	   style.setDefault(this);
	   return style;
   }

}
