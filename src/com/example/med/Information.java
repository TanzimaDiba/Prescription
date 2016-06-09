package com.example.med;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Information {
	
	public static final String KEY_ROWID="_id";
	public static final String KEY_NAME="person_name";
	public static final String KEY_AGE="person_age";
	public static final String KEY_PAS="person_pas";
	
	private static final String DB_NAME="Information";
	private static final String DB_TABLE="peopleTable";
	private static final int DB_VERSION=1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DB_TABLE + " (" +
					   KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					   KEY_NAME + " TEXT NOT NULL, " + 
					   KEY_AGE + " INTEGER, " + 
					   KEY_PAS + " TEXT NOT NULL);" 				
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
			onCreate(db);
		}
		
	}
	
	public Information(Context c){
		ourContext =c;
	}
	
	public Information open()throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
		
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long initialEntry(String name, int age, String pas) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_AGE, age);
		cv.put(KEY_PAS, pas);
		
		return ourDatabase.insert(DB_TABLE, null, cv);
	}

	public void createEntry(int rowId, String name, int age, String pas) {
		// TODO Auto-generated method stub
		
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, name);
		cvUpdate.put(KEY_AGE, age);
		cvUpdate.put(KEY_PAS, pas);
	
		ourDatabase.update(DB_TABLE, cvUpdate, KEY_ROWID + "=" + rowId, null);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_NAME,KEY_AGE, KEY_PAS};
		Cursor c= ourDatabase.query(DB_TABLE, columns, null, null, null, null,null, null);
		String result="";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iAge = c.getColumnIndex(KEY_AGE);
		int iPas = c.getColumnIndex(KEY_PAS);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result=result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iAge) + " " + c.getString(iPas) + "\n";
		}
		return result;
	}

	public String getName(int p) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_NAME,KEY_AGE, KEY_PAS};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + p,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String rowid=c.getString(1);
			return rowid;
		}
		return null;
	}

	public String getAge(int p) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_NAME,KEY_AGE, KEY_PAS};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + p,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String rowid=c.getString(2);
			return rowid;
		}
		return null;
	}
	
	public String getPas(int p) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_NAME,KEY_AGE, KEY_PAS};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + p,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String rowid=c.getString(3);
			return rowid;
		}
		return null;
	}
	
}
