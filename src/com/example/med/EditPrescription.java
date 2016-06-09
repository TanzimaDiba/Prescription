package com.example.med;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class EditPrescription extends Activity implements OnClickListener{
	
	EditText sickName,docName,clinicAdrs,clinicNo,docNo;
	TextView clickLast,clickNext,showLast,showNext;
	TextView medicine;
	
	Button save;
	
	int year,month,day;
	String last,next;
	String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	static String filename="MySickData";
	SharedPreferences sick;
	String data;
	int rowId;
	
	String sqlSickName,sqlDoc,sqlClinicAdrs,sqlClinicNo,sqlDocNo,sqlLast,sqlNext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_pres);
		
		sick=getSharedPreferences(filename,0);
	    data=sick.getString("sharedString","empty");
	    rowId=Integer.parseInt(data);
		
		sickName=(EditText) findViewById(R.id.editSick);
		docName=(EditText) findViewById(R.id.editDoc);
		clinicAdrs=(EditText) findViewById(R.id.editAdrs);
		clinicNo=(EditText) findViewById(R.id.editClinicNo);
		docNo=(EditText) findViewById(R.id.editDocNo);
		
		clickLast=(TextView) findViewById(R.id.textLastMeeting);
		clickNext=(TextView) findViewById(R.id.textNextMeeting);
		showLast=(TextView) findViewById(R.id.showLastMeeting);
		showNext=(TextView) findViewById(R.id.showNextMeeting);
		
		clickLast.setOnClickListener(this);
		clickNext.setOnClickListener(this);
		
		medicine=(TextView) findViewById(R.id.textMed);
		
		medicine.setOnClickListener(this);
		
		save=(Button) findViewById(R.id.button_save);
		save.setOnClickListener(this);
		
		toShowPrescriptionInfo();
		
	}

	private void toShowPrescriptionInfo() {
		// TODO Auto-generated method stub
		PrescriptionInfo presInfo=new PrescriptionInfo(this);
		presInfo.open();
		sqlSickName=presInfo.getSicknessName(rowId);
		sqlDoc=presInfo.getDocName(rowId);
		sqlClinicAdrs=presInfo.getClinicAdrs(rowId);
		sqlClinicNo=presInfo.getClinicNo(rowId);
		sqlDocNo=presInfo.getDocNo(rowId);
		sqlLast=presInfo.getLastDate(rowId);
		sqlNext=presInfo.getNextDate(rowId);
		
		sickName.setText(sqlSickName);
		docName.setText(sqlDoc);
		clinicAdrs.setText(sqlClinicAdrs);
		clinicNo.setText(sqlClinicNo);
		docNo.setText(sqlDocNo);
		showLast.setText(sqlLast);
		showNext.setText(sqlNext);
		presInfo.close();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.textLastMeeting:
			toPickLastMeetingDate();
			break;
			
		case R.id.textNextMeeting:
			toPickNextMeetingDate();
			break;
			
		case R.id.button_save:
			toWriteDatabase();
			Intent iVP= new Intent("com.example.med.VIEWPRESCRIPTION");
			startActivity(iVP);
			break;
			
		case R.id.textMed:
			
			break;
		}
	}

	private void toPickLastMeetingDate() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Last Meeting Date");
		final DatePicker date = new DatePicker(this);
		date.setCalendarViewShown(false);
		builder.setView(date);
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				year=date.getYear();
				month=date.getMonth();
				day=date.getDayOfMonth();
				if(day<10)
					 last= "0"+day +  " "+MONTH[month]+ ", " + year;
					else
						last= day +  " "+MONTH[month]+ ", " + year; 
				showLast.setText(last);
				dialog.dismiss();
			}
		});
		
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		builder.show();
	}

	private void toPickNextMeetingDate() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Next Meeting Date");
		final DatePicker date = new DatePicker(this);
		date.setCalendarViewShown(false);
		builder.setView(date);
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				year=date.getYear();
				month=date.getMonth();
				day=date.getDayOfMonth();
				if(day<10)
					 next= "0"+day +  " "+MONTH[month]+ ", " + year;
					else
					 next= day +  " "+MONTH[month]+ ", " + year; 
				
				showNext.setText(next);
				dialog.dismiss();
			}
		});
		
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		builder.show();
	}

	private void toWriteDatabase() {
		// TODO Auto-generated method stub
		sqlSickName=sickName.getText().toString();
		sqlDoc=docName.getText().toString();
		sqlClinicAdrs=clinicAdrs.getText().toString();
		sqlClinicNo=clinicNo.getText().toString();
		sqlDocNo=docNo.getText().toString();
		sqlLast=showLast.getText().toString();
		sqlNext=showNext.getText().toString();
		
		
		PrescriptionInfo presInfo1=new PrescriptionInfo(this);
		presInfo1.open();
		presInfo1.createEntry(rowId,sqlSickName, sqlDoc, sqlClinicAdrs, sqlClinicNo, sqlDocNo, sqlLast, sqlNext);
		presInfo1.close();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
}
