package com.gzaas.android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ApiDataHelper {

   private static final String DATABASE_NAME = "gzaasDB.db";
   private static final int DATABASE_VERSION = 1;
   private static final String TABLE_NAME = "apis";

   private SQLiteDatabase db;

   private SQLiteStatement insertStmt;
   private static final String INSERT = "insert into " 
      + TABLE_NAME + "(apiKey) values (?)";

   public ApiDataHelper(Context context) {
      OpenHelper openHelper = new OpenHelper(context);
      db = openHelper.getWritableDatabase();
      insertStmt = db.compileStatement(INSERT);
   }

   public long insert(String apiKey) {
      insertStmt.bindString(1, apiKey);
      return insertStmt.executeInsert();
   }
   
   public void insertDefaultApiKey(String apiKey) {
	      this.insert(apiKey);    
	   }   

   public void deleteAll() {
      db.delete(TABLE_NAME, null, null);
   }

   
   public List<String> selectAll() {
	      List<String> list = new ArrayList<String>();
	      Cursor cursor = db.query(TABLE_NAME, new String[] { "apiKey" }, 
	        null, null, null, null, null);
	      if (cursor.moveToFirst()) {
	         do {
	            list.add(cursor.getString(0)); 
	         } while (cursor.moveToNext());
	      }
	      if (cursor != null && !cursor.isClosed()) {
	         cursor.close();
	      }
	      return list;
	   }

   
   public String selectOne(int position) {
	      Cursor cursor = db.query(TABLE_NAME, new String[] { "apiKey" }, 
	    	        null, null, null, null, null);
	      cursor.moveToPosition(position);
      	String apiKey = cursor.getString(0);
	   return apiKey;
   }

   private static class OpenHelper extends SQLiteOpenHelper {

      OpenHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
      }

      @Override
      public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE " + TABLE_NAME + 
          "(id INTEGER PRIMARY KEY, apiKey TEXT)");
      }

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         Log.w("Example", "Upgrading database, this will drop tables and recreate.");
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         onCreate(db);
      }
   }
}
