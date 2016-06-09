package com.example.med;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PrescriptionInfo {
	
	public static final String KEY_ROWID="_id";
	public static final String KEY_SICK="sick_name";
	public static final String KEY_DOC="doctor_name";
	public static final String KEY_CLINIC_ADRS="clinic_address";
	public static final String KEY_CLINIC_NO="clinic_no";
	public static final String KEY_DOC_NO="doc_no";
	public static final String KEY_LAST="last_metting";
	public static final String KEY_NEXT="next_metting";
	
	private static final String DB_NAME="PrescriptionInformation";
	private static final String DB_TABLE="prescriptionTable";
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
					   KEY_SICK + " TEXT NOT NULL, " + 
					   KEY_DOC + " TEXT NOT NULL, " + 
					   KEY_CLINIC_ADRS + " TEXT NOT NULL, " + 
					   KEY_CLINIC_NO + " TEXT NOT NULL, " +
					   KEY_DOC_NO + " TEXT NOT NULL, " +
					   KEY_LAST + " TEXT NOT NULL, " +
					   KEY_NEXT + " TEXT NOT NULL);" 				
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
			onCreate(db);
		}
		
	}
	
	public PrescriptionInfo(Context c){
		ourContext =c;
	}
	
	public PrescriptionInfo open()throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
		
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long initialEntry(String sickName, String docName, String clinicAdrs,
			String clinicNo, String docNo, String last, String next) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_SICK, sickName);
		cv.put(KEY_DOC, docName);
		cv.put(KEY_CLINIC_ADRS, clinicAdrs);
		cv.put(KEY_CLINIC_NO, clinicNo);
		cv.put(KEY_DOC_NO, docNo);
		cv.put(KEY_LAST, last);
		cv.put(KEY_NEXT, next);
		
		return ourDatabase.insert(DB_TABLE, null, cv);
		
	}

	public void createEntry(int rowId, String sickName, String docName, String clinicAdrs, String clinicNo,
			String docNo, String last, String next) {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_SICK, sickName);
		cvUpdate.put(KEY_DOC, docName);
		cvUpdate.put(KEY_CLINIC_ADRS, clinicAdrs);
		cvUpdate.put(KEY_CLINIC_NO, clinicNo);
		cvUpdate.put(KEY_DOC_NO, docNo);
		cvUpdate.put(KEY_LAST, last);
		cvUpdate.put(KEY_NEXT, next);
		
		ourDatabase.update(DB_TABLE, cvUpdate, KEY_ROWID + "=" + rowId, null);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID,KEY_SICK, KEY_DOC,KEY_CLINIC_ADRS, KEY_CLINIC_NO, 
				KEY_DOC_NO, KEY_LAST, KEY_NEXT};
		Cursor c= ourDatabase.query( DB_TABLE, columns, null, null, null, null,null, null);
		String result="";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iSick = c.getColumnIndex(KEY_SICK);
		int iDoc = c.getColumnIndex(KEY_DOC);
		int iAdrs = c.getColumnIndex(KEY_CLINIC_ADRS);
		int iClinicNo = c.getColumnIndex(KEY_CLINIC_NO);
		int iDocNo = c.getColumnIndex(KEY_DOC_NO);
		int iLast = c.getColumnIndex(KEY_LAST);
		int iNext = c.getColumnIndex(KEY_NEXT);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result=result + c.getString(iRow) + " " + c.getString(iSick) + " " + c.getString(iDoc) + " " + c.getString(iAdrs) + " " 
		    + c.getString(iClinicNo) + " "+ c.getString(iDocNo) + " " + c.getString(iLast) + " " + c.getString(iNext) + "\n";
		}
		return result;
	}

	public String getSicknessName(int p) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_SICK, KEY_DOC,KEY_CLINIC_ADRS, KEY_CLINIC_NO, 
				KEY_DOC_NO, KEY_LAST, KEY_NEXT};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + p,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String rowid=c.getString(1);
			return rowid;
		}
		return null;
	}

	public String getDocName(int rowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_SICK, KEY_DOC,KEY_CLINIC_ADRS, KEY_CLINIC_NO, 
				KEY_DOC_NO, KEY_LAST, KEY_NEXT};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + rowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String rowid=c.getString(2);
			return rowid;
		}
		return null;
	}

	public String getClinicAdrs(int rowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_SICK, KEY_DOC,KEY_CLINIC_ADRS, KEY_CLINIC_NO, 
				KEY_DOC_NO, KEY_LAST, KEY_NEXT};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + rowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String rowid=c.getString(3);
			return rowid;
		}
		return null;
	}

	public String getClinicNo(int rowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_SICK, KEY_DOC,KEY_CLINIC_ADRS, KEY_CLINIC_NO, 
				KEY_DOC_NO, KEY_LAST, KEY_NEXT};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + rowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String rowid=c.getString(4);
			return rowid;
		}
		return null;
	}

	public String getDocNo(int rowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_SICK, KEY_DOC,KEY_CLINIC_ADRS, KEY_CLINIC_NO, 
				KEY_DOC_NO, KEY_LAST, KEY_NEXT};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + rowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String rowid=c.getString(5);
			return rowid;
		}
		return null;
	}

	public String getLastDate(int rowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_SICK, KEY_DOC,KEY_CLINIC_ADRS, KEY_CLINIC_NO, 
				KEY_DOC_NO, KEY_LAST, KEY_NEXT};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + rowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String rowid=c.getString(6);
			return rowid;
		}
		return null;
	}

	public String getNextDate(int rowId) {
		// TODO Auto-generated method stub
		String[] columns =new String[]{KEY_ROWID, KEY_SICK, KEY_DOC,KEY_CLINIC_ADRS, KEY_CLINIC_NO, 
				KEY_DOC_NO, KEY_LAST, KEY_NEXT};
		Cursor c=ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + rowId,  null, null,  null,  null);
		
		if(c!=null)
		{
			c.moveToFirst();
			String rowid=c.getString(7);
			return rowid;
		}
		return null;
	}
}
