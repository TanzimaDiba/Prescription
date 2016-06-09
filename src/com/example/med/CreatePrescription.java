package com.example.med;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


public class CreatePrescription extends Activity implements OnClickListener{
	
	EditText sickName,docName,clinicAdrs,clinicNo,docNo;
	TextView clickLast,clickNext,showLast,showNext;
	TextView medicine;
	
	int year,month,day;
	String last,next;
	String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	static String filename="MySickData";
	SharedPreferences sick;
	String data;
	int rowId;
	
	int ok=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_pres);
		
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
		
		medicine=(TextView) findViewById(R.id.textMed);
		
		clickLast.setOnClickListener(this);
		clickNext.setOnClickListener(this);
		
		 medicine.setOnClickListener(this);
	
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
			
		case R.id.textMed:
			
			String searchSick;
			
			String sqlSick=sickName.getText().toString();
			String sqlDoc=docName.getText().toString();
			String sqlAdrs=clinicAdrs.getText().toString();
			String sqlClinicNo=clinicNo.getText().toString();
			String sqlDocNo=docNo.getText().toString();
			
			
			PrescriptionInfo presInfo=new PrescriptionInfo(this);
			presInfo.open();
			for(int i=1;i<=5;i++){
				searchSick=presInfo.getSicknessName(i);
				if(searchSick.equals(sqlSick)){
					ok=0;
					break;
				}
			}
			presInfo.close();
			
			if(ok == 0){
				toSameSickWarning();
			}
			
			else{
			
			if(sqlSick.isEmpty() || sqlDoc.isEmpty() || sqlAdrs.isEmpty() || sqlClinicNo.isEmpty() 
				|| sqlDocNo.isEmpty() || last.isEmpty() || next.isEmpty()){
				toWarningDialog();
			}
			else{
			
			PrescriptionInfo presInfo1=new PrescriptionInfo(this);
			presInfo1.open();
			presInfo1.createEntry(rowId,sqlSick, sqlDoc, sqlAdrs, sqlClinicNo, sqlDocNo, last, next);
			presInfo1.close();
			
			Intent i2= new Intent("com.example.med.MEDICINELIST");
			startActivity(i2);
			}
			
			}
			break;
		
		}
		
	}

	private void toSameSickWarning() {
		// TODO Auto-generated method stub
		Dialog d=new Dialog(this);
		d.setTitle("Warning");
		TextView tv=new TextView(this);
		tv.setText("You Have Already Saved This Sickness");
		tv.setTextSize(20);
		d.setContentView(tv);
		d.show();
	}

	private void toWarningDialog() {
		// TODO Auto-generated method stub
		Dialog d=new Dialog(this);
		d.setTitle("Warning");
		TextView tv=new TextView(this);
		tv.setText("Please Provide All Information");
		tv.setTextSize(20);
		d.setContentView(tv);
		d.show();
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
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
