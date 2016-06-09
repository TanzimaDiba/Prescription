package com.example.med;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class ViewMedicine extends Activity implements OnClickListener{
	
	EditText med,day,quan;
	TextView before,after;
	TextView calender;
	
	TextView alarm1,alarm2,alarm3;
	Button edit;
	
	static String filename="MyData";
	SharedPreferences some;
	String data;
	
	int medRowId;
	int d;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_med);
		
		some=getSharedPreferences(filename,0);
		data=some.getString("sharedString","empty");
		medRowId=Integer.parseInt(data);
		
		med=(EditText) findViewById(R.id.editMedicine);
		day=(EditText) findViewById(R.id.editDay);
		quan=(EditText) findViewById(R.id.editQuantity);
		
		before=(TextView) findViewById(R.id.textBefore);
		after=(TextView) findViewById(R.id.textAfter);
		calender=(TextView) findViewById(R.id.textCalender);
		calender.setOnClickListener(this);
		
		alarm1=(TextView) findViewById(R.id.textAlarm1);
		alarm2=(TextView) findViewById(R.id.textAlarm2);
		alarm3=(TextView) findViewById(R.id.textAlarm3);
		
		edit=(Button) findViewById(R.id.button_edit);
		edit.setOnClickListener(this);
		
		toShowMedicineInfo();
	}

	private void toShowMedicineInfo() {
		// TODO Auto-generated method stub
		MedicineInfo medInfo=new MedicineInfo(this);
		medInfo.open();
		String medName=medInfo.getMedicineName(medRowId);
		String dayDuration=medInfo.getDay(medRowId);
		String quantity=medInfo.getQuantity(medRowId);
		String sbefore=medInfo.getBefore(medRowId);
		String safter=medInfo.getAfter(medRowId);
	    String sqlalarm1=medInfo.getAlarm1(medRowId);
	    String sqlalarm2=medInfo.getAlarm2(medRowId);
	    String sqlalarm3=medInfo.getAlarm3(medRowId);
		medInfo.close();
		
		if(sbefore.equals("no"))
			sbefore=" ";
		if(safter.equals("no"))
			safter=" ";
		
		med.setText(medName);
		day.setText(dayDuration);
		quan.setText(quantity);
		before.setText(sbefore);
		after.setText(safter);
		
		alarm1.setText(sqlalarm1);
		alarm2.setText(sqlalarm2);
		alarm3.setText(sqlalarm3);
		
		d=Integer.parseInt(dayDuration);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_edit:
		Intent iVM=new Intent("com.example.med.EDITMEDICINE");
		startActivity(iVM);
		break;
		
		case R.id.textCalender:
			
			Dialog d=new Dialog(this);
			d.setTitle("Calender View");
			CalendarView cl=new CalendarView(this);
			long currentDay=cl.getDate();
			
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent iSL=new Intent("com.example.med.VIEWMEDICINELIST");
		startActivity(iSL);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
