package com.example.med;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ViewSicknessList extends Activity implements OnClickListener{
	
	TextView s1,s2,s3,s4,s5;
	String sickName1,sickName2,sickName3,sickName4,sickName5;
	String sickName;
	int rowId;
	
	static String filename="MySickData";
	SharedPreferences sick;
	String data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sick_list);
		
		sick=getSharedPreferences(filename,0);
		data=sick.getString("sharedString","empty");
		
		s1=(TextView) findViewById(R.id.textSick1);
		s2=(TextView) findViewById(R.id.textSick2);
		s3=(TextView) findViewById(R.id.textSick3);
		s4=(TextView) findViewById(R.id.textSick4);
		s5=(TextView) findViewById(R.id.textSick5);
		
		s1.setOnClickListener(this);
		s2.setOnClickListener(this);
		s3.setOnClickListener(this);
		s4.setOnClickListener(this);
		s5.setOnClickListener(this);
		
		toShowSicknessName();
	}

	private void toShowSicknessName() {
		// TODO Auto-generated method stub
		if(data.equals("empty")){
			s1.setText("Sickness 1");
			s2.setText("Sickness 2");
			s3.setText("Sickness 3");
			s4.setText("Sickness 4");
			s5.setText("Sickness 5");
		}
		else{
		PrescriptionInfo presInfo1 = new PrescriptionInfo(this);
		presInfo1.open();
		sickName=presInfo1.getSicknessName(1);
		
		if(!sickName.equals("no")){
			
			for(int i=1;i<=5;i++){
				sickName=presInfo1.getSicknessName(i);
				if(sickName.equals("no")){
					rowId=i-1;
					break;
				}
			}
			
			if(rowId == 1){
				sickName1=presInfo1.getSicknessName(1);
				
				s1.setText(sickName1);
			}
			else if(rowId == 2){
				sickName1=presInfo1.getSicknessName(1);
				sickName2=presInfo1.getSicknessName(2);
				
				s1.setText(sickName1);
				s2.setText(sickName2);
			}
			else if(rowId == 3){
				sickName1=presInfo1.getSicknessName(1);
				sickName2=presInfo1.getSicknessName(2);
				sickName3=presInfo1.getSicknessName(3);
				
				s1.setText(sickName1);
				s2.setText(sickName2);
				s3.setText(sickName3);
			}
			else if(rowId == 4){
				sickName1=presInfo1.getSicknessName(1);
				sickName2=presInfo1.getSicknessName(2);
				sickName3=presInfo1.getSicknessName(3);
				sickName4=presInfo1.getSicknessName(4);
				
				s1.setText(sickName1);
				s2.setText(sickName2);
				s3.setText(sickName3);
				s4.setText(sickName4);
			}
			else if(rowId == 5){
				sickName1=presInfo1.getSicknessName(1);
				sickName2=presInfo1.getSicknessName(2);
				sickName3=presInfo1.getSicknessName(3);
				sickName4=presInfo1.getSicknessName(4);
				sickName5=presInfo1.getSicknessName(5);
				
				s1.setText(sickName1);
				s2.setText(sickName2);
				s3.setText(sickName3);
				s4.setText(sickName4);
				s5.setText(sickName5);
			}
			
			presInfo1.close();
		}
		
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	switch(v.getId()){
		
		case R.id.textSick1:
			toWriteSickRowId(1);
			toWriteRowId(1);
			break;
			
		case R.id.textSick2:
			toWriteSickRowId(2);
			toWriteRowId(2);
			break;
			
       case R.id.textSick3:
    	   toWriteSickRowId(3);
    	   toWriteRowId(3);
			break;
			
	   case R.id.textSick4:
		   toWriteSickRowId(4);
		   toWriteRowId(4);
			break;
			
       case R.id.textSick5:
    	   toWriteSickRowId(5);
    	   toWriteRowId(5);
			break;
		}
		
	}

	private void toWriteSickRowId(int i) {
		// TODO Auto-generated method stub
		SickRowIdInfo rowInfo1 = new SickRowIdInfo(this);
		rowInfo1.open();
		rowInfo1.createEntry(1, i);
		rowInfo1.close();
	}

	private void toWriteRowId(int row) {
		// TODO Auto-generated method stub
		if(data.equals("empty")){
			toWarningDialog();
		}
		else{
		String data=Integer.toString(row);
		SharedPreferences.Editor ed=sick.edit();
  		ed.putString("sharedString", data);	
  		ed.commit();
  		
  		Intent iVP=new Intent("com.example.med.VIEWPRESCRIPTION");
		startActivity(iVP);
		}
	}
	
	private void toWarningDialog() {
		// TODO Auto-generated method stub
		Dialog d=new Dialog(this);
		d.setTitle("Warning");
		TextView tv=new TextView(this);
		tv.setText("Please Add Prescription First");
		tv.setTextSize(20);
		d.setContentView(tv);
		d.show();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent i=new Intent("com.example.med.PROFILE");
		startActivity(i);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
