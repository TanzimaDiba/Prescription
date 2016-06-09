package com.example.med;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedicineInfo {
	
	public static final String KEY_ROWID="_id";
	public static final String KEY_MED="med_name";
	public static final String KEY_DAY="day_range";
	public static final String KEY_QUAN="med_quantity";
	public static final String KEY_BEFORE="meal_before";
	public static final String KEY_AFTER="meal_after";
	public static final String KEY_ALARM1="alarm1";
	public static final String KEY_ALARM2="alarm2";
	public static final String KEY_ALARM3="alarm3";
	
	private static final String DB_NAME="MedicineInformation";
	private static final String DB_TABLE="medicineTable";
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
					   KEY_MED + " TEXT NOT NULL, " + 
					   KEY_DAY + " INTEGER, " + 
					   KEY_QUAN + " INTEGER, " + 
					   KEY_BEFORE + " TEXT NOT NULL, " +
					   KEY_AFTER + " TEXT NOT NULL, " +
					   KEY_ALARM1 + " TEXT NOT NULL, " +
					   KEY_ALARM2 + " TEXT NOT NULL, " +
					   KEY_ALARM3 + " TEXT NOT NULL);" 				
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
			onCreate(db);
		}
		
	}
	
	public MedicineInfo(Context c){
		ourContext =c;
	}
	
	public MedicineInfo open()throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
		
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long initialEntry(String medName, int dayDuration, int quanRange, String before,
			String after, String alarm1,String alarm2,String alarm3) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_MED, medName);
		cv.put(KEY_DAY, dayDuration);
		cv.put(KEY_QUAN, quanRange);
		cv.put(KEY_BEFORE, before);
		cv.put(KEY_AFTER, after);
		cv.put(KEY_ALARM1, alarm1);
		cv.put(KEY_ALARM2, alarm2);
		cv.put(KEY_ALARM3, alarm3);
		
		return ourDatabase.insert(DB_TABLE, null, cv);
		
	}
	

	public void createEntry(int rowId, String medName, int dayDuration, int quanRange, String before,
			String after,  String alarm1,String alarm2,String alarm3) {
		// TODO Auto-generated method stub
		
		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(KEY_MED, medName);
		cvUpdate.put(KEY_DAY, dayDuration);
		cvUpdate.put(KEY_QUAN, quanRange);
		cvUpdate.put(KEY_BEFORE, before);
		cvUpdate.put(KEY_AFTER, after);
		cvUpdate.put(KEY_ALARM1, alarm1);
		cvUpdate.put(KEY_ALARM2, alarm2);
		cvUpdate.put(KEY_ALARM3, alarm3);
		
		ourDatabase.update(DB_TABLE, cvUpdate, KEY_ROWID + "=" + rowId, null);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_MED, KEY_DAY,KEY_QUAN, 
				KEY_BEFORE, KEY_AFTER, KEY_ALARM1, KEY_ALARM2, KEY_ALARM3};
		Cursor c= ourDatabase.query(DB_TABLE, columns, null, null, null, null,null, null);
		String result="";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iMed = c.getColumnIndex(KEY_MED);
		int iDay = c.getColumnIndex(KEY_DAY);
		int iQuan = c.getColumnIndex(KEY_QUAN);
		int iBefore = c.getColumnIndex(KEY_BEFORE);
		int iAfter = c.getColumnIndex(KEY_AFTER);
		int iAlarm1 = c.getColumnIndex(KEY_ALARM1);
		int iAlarm2 = c.getColumnIndex(KEY_ALARM2);
		int iAlarm3 = c.getColumnIndex(KEY_ALARM3);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result=result + c.getString(iRow) + " "  + c.getString(iMed) + " " + c.getString(iDay) + " " + c.getString(iQuan) + " " 
		    + c.getString(iBefore) + " "+ c.getString(iAfter) + " " + c.getString(iAlarm1) + " " + c.getString(iAlarm2) +
		    " " + c.getString(iAlarm3) + " " + "\n";
		}
		return result;
	}

	public String getMedicineName(int i) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_MED, KEY_DAY,KEY_QUAN, 
				KEY_BEFORE, KEY_AFTER, KEY_ALARM1, KEY_ALARM2, KEY_ALARM3};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + i,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String medName=c.getString(1);
			return medName;
		}
		
		return null;
	}

	public String getDay(int medRowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_MED, KEY_DAY,KEY_QUAN, 
				KEY_BEFORE, KEY_AFTER, KEY_ALARM1, KEY_ALARM2, KEY_ALARM3};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + medRowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String day=c.getString(2);
			return day;
		}
		
		return null;
	}

	public String getQuantity(int medRowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_MED, KEY_DAY,KEY_QUAN, 
				KEY_BEFORE, KEY_AFTER, KEY_ALARM1, KEY_ALARM2, KEY_ALARM3};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + medRowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String quantity=c.getString(3);
			return quantity;
		}
		
		return null;
	}

	public String getBefore(int medRowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_MED, KEY_DAY,KEY_QUAN, 
				KEY_BEFORE, KEY_AFTER, KEY_ALARM1, KEY_ALARM2, KEY_ALARM3};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + medRowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String before=c.getString(4);
			return before;
		}
		
		return null;
	}

	public String getAfter(int medRowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_MED, KEY_DAY,KEY_QUAN, 
				KEY_BEFORE, KEY_AFTER, KEY_ALARM1, KEY_ALARM2, KEY_ALARM3};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + medRowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String after=c.getString(5);
			return after;
		}
		
		return null;
	}

	public String getAlarm1(int medRowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_MED, KEY_DAY,KEY_QUAN, 
				KEY_BEFORE, KEY_AFTER, KEY_ALARM1, KEY_ALARM2, KEY_ALARM3};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + medRowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String alarm1=c.getString(6);
			return alarm1;
		}
		
		return null;
	}

	public String getAlarm2(int medRowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_MED, KEY_DAY,KEY_QUAN, 
				KEY_BEFORE, KEY_AFTER, KEY_ALARM1, KEY_ALARM2, KEY_ALARM3};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + medRowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String alarm2=c.getString(7);
			return alarm2;
		}
		
		return null;
	}

	public String getAlarm3(int medRowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_MED, KEY_DAY,KEY_QUAN, 
				KEY_BEFORE, KEY_AFTER, KEY_ALARM1, KEY_ALARM2, KEY_ALARM3};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + medRowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String alarm3=c.getString(8);
			return alarm3;
		}
		
		return null;
	}

}
