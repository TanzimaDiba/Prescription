package com.example.med;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewPrescription extends Activity implements OnClickListener{
	
	EditText sickName,docName,clinicAdrs,clinicNo,docNo;
	TextView showLast,showNext;
	TextView medicine;
	
	Button edit;
	
	static String filename="MySickData";
	SharedPreferences sick;
	String data;
	int rowId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pres);
		
		sick=getSharedPreferences(filename,0);
	    data=sick.getString("sharedString","empty");
	    rowId=Integer.parseInt(data);
		
		sickName=(EditText) findViewById(R.id.editSick);
		docName=(EditText) findViewById(R.id.editDoc);
		clinicAdrs=(EditText) findViewById(R.id.editAdrs);
		clinicNo=(EditText) findViewById(R.id.editClinicNo);
		docNo=(EditText) findViewById(R.id.editDocNo);
		
		showLast=(TextView) findViewById(R.id.showLastMeeting);
		showNext=(TextView) findViewById(R.id.showNextMeeting);
		
		medicine=(TextView) findViewById(R.id.textMed);
		
		medicine.setOnClickListener(this);
		
		edit=(Button) findViewById(R.id.button_edit);
		edit.setOnClickListener(this);
		
		toShowPrescriptionInfo();
		
	}

	private void toShowPrescriptionInfo() {
		// TODO Auto-generated method stub
		PrescriptionInfo presInfo=new PrescriptionInfo(this);
		presInfo.open();
		String sqlSickName=presInfo.getSicknessName(rowId);
		String sqlDoc=presInfo.getDocName(rowId);
		String sqlClinicAdrs=presInfo.getClinicAdrs(rowId);
		String sqlClinicNo=presInfo.getClinicNo(rowId);
		String sqlDocNo=presInfo.getDocNo(rowId);
		String sqlLast=presInfo.getLastDate(rowId);
		String sqlNext=presInfo.getNextDate(rowId);
		
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
		case R.id.button_edit:
			Intent iEP=new Intent("com.example.med.EDITPRESCRIPTION");
			startActivity(iEP);
			break;
			
		case R.id.textMed:
			
			Intent iML=new Intent("com.example.med.VIEWMEDICINELIST");
			startActivity(iML);
			
			break;
		}
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent iSL=new Intent("com.example.med.VIEWSICKNESSLIST");
		startActivity(iSL);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
