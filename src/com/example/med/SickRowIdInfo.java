package com.example.med;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SickRowIdInfo {
	
	public static final String KEY_ROWID="_id";
	public static final String KEY_SICKROWID="sick_id";
	
	private static final String DB_NAME="SickRowIdInformation";
	private static final String DB_TABLE="sickRowIdTable";
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
					   KEY_SICKROWID + " INTEGER);" 				
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
			onCreate(db);
		}
		
	}
	
	public SickRowIdInfo(Context c){
		ourContext =c;
	}
	
	public SickRowIdInfo open()throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
		
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long initialEntry(int sickRowID) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_SICKROWID, sickRowID);
		
		return ourDatabase.insert(DB_TABLE, null, cv);
		
	}
	

	public void createEntry(int rowId, int sickRowId) {
		// TODO Auto-generated method stub
		
		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(KEY_SICKROWID, sickRowId);
		
		ourDatabase.update(DB_TABLE, cvUpdate, KEY_ROWID + "=" + rowId, null);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_SICKROWID};
		Cursor c= ourDatabase.query(DB_TABLE, columns, null, null, null, null,null, null);
		String result="";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iSickRow = c.getColumnIndex(KEY_SICKROWID);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result=c.getString(iSickRow) ;
		}
		return result;
	}

	public String getRowId(int i) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_SICKROWID};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + i,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String medName=c.getString(1);
			return medName;
		}
		
		return null;
	}

}
