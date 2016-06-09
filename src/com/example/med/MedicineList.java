package com.example.med;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MedicineList extends Activity implements OnClickListener{
	
	TextView med1,med2,med3,med4,med5;
	String medName1,medName2,medName3,medName4,medName5;
	String medName;
	int rowId;
	
	static String filename="MyData";
	SharedPreferences some;
	String data;
	
	int sickRowId;
	int passRowId;
	
	int beg,end;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.med_list);
		
		some=getSharedPreferences(filename,0);
		data=some.getString("sharedString","empty");
	    
		med1=(TextView) findViewById(R.id.textMed1);
		med2=(TextView) findViewById(R.id.textMed2);
		med3=(TextView) findViewById(R.id.textMed3);
		med4=(TextView) findViewById(R.id.textMed4);
		med5=(TextView) findViewById(R.id.textMed5);
		
		toFindSickRowId();
		toShowMedicineName();
		
		med1.setOnClickListener(this);
		med2.setOnClickListener(this);
		med3.setOnClickListener(this);
		med4.setOnClickListener(this);
		med5.setOnClickListener(this);
		
	}

	private void toFindSickRowId() {
		// TODO Auto-generated method stub
		SickRowIdInfo rowInfo = new SickRowIdInfo(this);
		rowInfo.open();
		String temp=rowInfo.getData();
		sickRowId=Integer.parseInt(temp);
		rowInfo.close();
		
		if(sickRowId == 1){
			beg=1;
			end=5;
		}
		else if(sickRowId == 2){
			beg=6;
			end=10;
		}
		
		else if(sickRowId == 3){
			beg=11;
			end=15;
		}
		else if(sickRowId == 4){
			beg=16;
			end=20;
		}
		else if(sickRowId == 5){
			beg=21;
			end=25;
		}
	}

	private void toShowMedicineName() {
		// TODO Auto-generated method stub
		MedicineInfo medInfo1 = new MedicineInfo(this);
		medInfo1.open();
		medName=medInfo1.getMedicineName(beg);
		
		if(!medName.equals("no")){
			
			for(int i=beg;i<=end;i++){
				medName=medInfo1.getMedicineName(i);
				if(medName.equals("no")){
					rowId=i-1;
					break;
				}
			}
			
			if(rowId == beg){
				medName1=medInfo1.getMedicineName(beg);
				med1.setText(medName1);
			}
			else if(rowId == (beg+1)){
				medName1=medInfo1.getMedicineName(beg);
				medName2=medInfo1.getMedicineName(beg+1);
				med1.setText(medName1);
				med2.setText(medName2);
			}
			else if(rowId == (beg+2)){
				medName1=medInfo1.getMedicineName(beg);
				medName2=medInfo1.getMedicineName(beg+1);
				medName3=medInfo1.getMedicineName(beg+2);
				med1.setText(medName1);
				med2.setText(medName2);
				med3.setText(medName3);
			}
			else if(rowId == (beg+3)){
				medName1=medInfo1.getMedicineName(beg);
				medName2=medInfo1.getMedicineName(beg+1);
				medName3=medInfo1.getMedicineName(beg+2);
				medName4=medInfo1.getMedicineName(beg+3);
				med1.setText(medName1);
				med2.setText(medName2);
				med3.setText(medName3);
				med4.setText(medName4);
			}
			
			else {
				medName1=medInfo1.getMedicineName(beg);
				medName2=medInfo1.getMedicineName(beg+1);
				medName3=medInfo1.getMedicineName(beg+2);
				medName4=medInfo1.getMedicineName(beg+3);
				medName5=medInfo1.getMedicineName(beg+4);
				med1.setText(medName1);
				med2.setText(medName2);
				med3.setText(medName3);
				med4.setText(medName4);
				med5.setText(medName5);
			}
		}
		
		medInfo1.close();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.textMed1:
			
			if(sickRowId == 1)
			   passRowId=1;
			else if(sickRowId == 2)
				passRowId=6;
			else if(sickRowId == 3)
				passRowId=11;
			else if(sickRowId == 4)
				passRowId=16;
			else if(sickRowId == 5)
				passRowId=21;
			
			toWriteRowId(passRowId);
			
			break;
			
        case R.id.textMed2:
        	
        	if(sickRowId == 1)
 			   passRowId=2;
 			else if(sickRowId == 2)
 				passRowId=7;
 			else if(sickRowId == 3)
 				passRowId=12;
 			else if(sickRowId == 4)
 				passRowId=17;
 			else if(sickRowId == 5)
 				passRowId=22;
 			
 			toWriteRowId(passRowId);
 			
			break;
			
			
        case R.id.textMed3:
        	
        	if(sickRowId == 1)
  			   passRowId=3;
  			else if(sickRowId == 2)
  				passRowId=8;
  			else if(sickRowId == 3)
  				passRowId=13;
  			else if(sickRowId == 4)
  				passRowId=18;
  			else if(sickRowId == 5)
  				passRowId=23;
  			
  			toWriteRowId(passRowId);
  			
	        break;
	
         case R.id.textMed4:
        	 	if(sickRowId == 1)
       			   passRowId=4;
       			else if(sickRowId == 2)
       				passRowId=9;
       			else if(sickRowId == 3)
       				passRowId=14;
       			else if(sickRowId == 4)
       				passRowId=19;
       			else if(sickRowId == 5)
       				passRowId=24;
       			
       			toWriteRowId(passRowId);
	         break;
	     
         case R.id.textMed5:
        	 if(sickRowId == 1)
     			   passRowId=5;
     			else if(sickRowId == 2)
     				passRowId=10;
     			else if(sickRowId == 3)
     				passRowId=15;
     			else if(sickRowId == 4)
     				passRowId=20;
     			else if(sickRowId == 5)
     				passRowId=25;
     			
     			toWriteRowId(passRowId);
	     break;
	
		
		}
		
	}

	private void toWriteRowId(int row) {
		// TODO Auto-generated method stub
		String data=Integer.toString(row);
		SharedPreferences.Editor ed=some.edit();
  		ed.putString("sharedString", data);	
  		ed.commit();
		
		Intent i=new Intent("com.example.med.CREATEMEDICINE");
		startActivity(i);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent iSL=new Intent("com.example.med.SICKNESSLIST");
		startActivity(iSL);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
