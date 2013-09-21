package com.gzaas.android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StyleDataHelper {

   private static final String DATABASE_NAME = "gzaasDB.db";
   private static final int DATABASE_VERSION = 1;
   private static final String TABLE_NAME = "styles";

   private SQLiteDatabase db;

   private SQLiteStatement insertStmt;
   private static final String INSERT = "insert into " 
      + TABLE_NAME + "(name,font,color,backcolor,pattern,shadow) values (?,?,?,?,?,?)";

   public StyleDataHelper(Context context) {
      OpenHelper openHelper = new OpenHelper(context);
      db = openHelper.getWritableDatabase();
      insertStmt = db.compileStatement(INSERT);
   }

   public long insert(String name, String font, String color, String backcolor, String pattern, String shadow) {
      insertStmt.bindString(1, name);
      insertStmt.bindString(2, font);
      insertStmt.bindString(3, color);
      if (backcolor==null) {
    	  backcolor = "null";
      }
      insertStmt.bindString(4, backcolor);
      if (pattern==null) {
    	  pattern = "null";
      }      
      insertStmt.bindString(5, pattern);
      if (shadow==null) {
    	  shadow = "null";
      }      
      insertStmt.bindString(6, shadow);      
      
      return insertStmt.executeInsert();
   }
   
   public void insertDefault() {
	      // Insertamos datos en BD
	      this.insert("Chewy Pink","chewy","#ffffff", "#FE28A2", null, null);
	      this.insert("Coming Blue","comingsoon", "#ffffff", "#00287B", null, null);
	      this.insert("Lucky Yellow","luckiestguy", "#000000", "#FFE303", null, null);
	        
	      this.insert("Kranky Simple","kranky", "#000000", "#ffffff", null, null);
	      this.insert("It's Christmas!","mountainsofchristmas", "#ffffff", "#EF0401", null, null);
	      this.insert("You failed!","impactlabel", "#000000", "#EF0401", null, null);
	        
	      this.insert("Garden Marker","permanentmarker", "#4C2B11", "#F8FF0D", null, null);	      
	   }   

   public void deleteAll() {
      db.delete(TABLE_NAME, null, null);
   }

   public List<Style> selectAll() {
	  List<Style> list = new ArrayList<Style>();
      Cursor cursor = db.query(TABLE_NAME, new String[] { "name, font, color, backcolor, pattern, shadow" }, 
        null, null, null, null, "name desc");
      if (cursor.moveToFirst()) {
         do {
        	Style newStyle = new Style();
        	newStyle.setName(cursor.getString(0));
        	newStyle.setFont(cursor.getString(1));
        	newStyle.setColor(cursor.getString(2));
        	newStyle.setBackcolor(cursor.getString(3));
        	newStyle.setPattern(cursor.getString(4));
        	newStyle.setShadow(cursor.getString(5));        	
        	list.add(newStyle); 
         } while (cursor.moveToNext());
      }
      if (cursor != null && !cursor.isClosed()) {
         cursor.close();
      }
      return list;
   }
   
   public Style selectOne(int position) {
	      Cursor cursor = db.query(TABLE_NAME, new String[] { "name, font, color, backcolor, pattern, shadow" }, 
	    	        null, null, null, null, "name desc");
	      cursor.moveToPosition(position);
      	Style newStyle = new Style();
    	newStyle.setName(cursor.getString(0));
    	newStyle.setFont(cursor.getString(1));
    	newStyle.setColor(cursor.getString(2));
    	newStyle.setBackcolor(cursor.getString(3));
    	newStyle.setPattern(cursor.getString(4));
    	newStyle.setShadow(cursor.getString(5));   
	   return newStyle;
   }

   private static class OpenHelper extends SQLiteOpenHelper {

      OpenHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
      }

      @Override
      public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE " + TABLE_NAME + 
          "(id INTEGER PRIMARY KEY, name TEXT, font TEXT, color TEXT, backcolor TEXT NULL, pattern TEXT NULL, shadow TEXT NULL)");
      }

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         Log.w("Example", "Upgrading database, this will drop tables and recreate.");
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         onCreate(db);
      }
   }
}
