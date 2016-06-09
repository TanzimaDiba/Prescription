package com.example.med;

import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class CreateMedicine extends Activity implements OnClickListener, OnCheckedChangeListener{
	
	EditText med,day,quan;
	CheckBox before,after;
	CheckBox select1,select2,select3;
	TextView alarm1,alarm2,alarm3;
	Button save;
	
	int a=0,b=0;
	String sBefore,sAfter;
	int hour1,min1,hour2,min2,hour3,min3;
	String sqlAlarm1=" ",sqlAlarm2=" ",sqlAlarm3=" ";
	
	long time1,time2,time3;
	
	static String filename="MyData";
	SharedPreferences some;
	String data;
	int rowId;
	
	int presentHour,presentMin;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_med);
		
		some=getSharedPreferences(filename,0);
	    data=some.getString("sharedString","empty");
	    rowId=Integer.parseInt(data);
		
		save=(Button) findViewById(R.id.button_save);
		save.setOnClickListener(this);
		
		med=(EditText) findViewById(R.id.editMedicine);
		day=(EditText) findViewById(R.id.editDay);
		quan=(EditText) findViewById(R.id.editQuantity);
		
		
		before=(CheckBox) findViewById(R.id.checkBefore);
		after=(CheckBox) findViewById(R.id.checkAfter);
		
		before.setOnCheckedChangeListener(this);
		after.setOnCheckedChangeListener(this);
		
		select1=(CheckBox) findViewById(R.id.checkBox1);
		select2=(CheckBox) findViewById(R.id.checkBox2);
		select3=(CheckBox) findViewById(R.id.checkBox3);
		
		select1.setOnCheckedChangeListener(this);
		select2.setOnCheckedChangeListener(this);
		select3.setOnCheckedChangeListener(this);
		
		alarm1=(TextView) findViewById(R.id.textAlarm1);
		alarm2=(TextView) findViewById(R.id.textAlarm2);
		alarm3=(TextView) findViewById(R.id.textAlarm3);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String medName=med.getText().toString();
		String dayDuration=day.getText().toString();
		String quanRange=quan.getText().toString();
		
		int d=Integer.parseInt(dayDuration);
		int q=Integer.parseInt(quanRange);
		
		if(a==0 && b==0){
			sBefore="no";
			sAfter="no";
		}
		else if(a==0 && b==1)
			sAfter="no";
		else if(a==1 && b==0)
			sBefore="no";
		
		if(medName.isEmpty() || dayDuration.isEmpty() || quanRange.isEmpty())
			toWarningDialog();
		else{
	
		MedicineInfo m1=new MedicineInfo(this);
		m1.open();
		m1.createEntry(rowId,medName, d, q, sBefore, sAfter, sqlAlarm1,sqlAlarm2,sqlAlarm3);
		m1.close();
	
		
		Intent iSL=new Intent("com.example.med.MEDICINELIST");
		startActivity(iSL);
		
		}
		
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

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch(buttonView.getId()){
		
		case R.id.checkBefore:
			sBefore="Before Meal";	
			b=1;
			break;
			
		case R.id.checkAfter:
			sAfter="After Meal";
			a=1;
			break;
		
		case R.id.checkBox1:
			toTimePick1();	
			toSetAlarm();
			break;
			
		case R.id.checkBox2:
			toTimePick2();	
			break;
			
		case R.id.checkBox3:
			toTimePick3();	
			break;
		}
		
	}

	private void toSetAlarm() {
		// TODO Auto-generated method stub    
        Long time = new GregorianCalendar().getTimeInMillis()+2*60*1000;
        Intent intentAlarm = new Intent(this, AlarmReciever.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1, 
        		intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(this, "Alarm Scheduled", Toast.LENGTH_LONG).show();
	}

	private void toTimePick1() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Set Alarm");
	    final TimePicker time=new TimePicker(this);
	    time.setIs24HourView(true);
	    presentHour=time.getCurrentHour();
	    presentMin=time.getCurrentMinute();
	    builder.setView(time);
	    
	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				hour1=time.getCurrentHour();
				min1=time.getCurrentMinute();
				
				if(hour1<10 && min1<10)
					alarm1.setText("0" + hour1 + ":" + "0" + min1);
				else if(hour1<10 && min1>10)
					alarm1.setText("0" + hour1 + ":" + min1);
				else if(hour1>10 && min1<10)
					alarm1.setText(hour1 + ":" + "0" + min1);
				else
					alarm1.setText(hour1 + ":" + min1);
				
				sqlAlarm1=alarm1.getText().toString();
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
	
	private void toTimePick2() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Set Alarm");
	    final TimePicker time=new TimePicker(this);
	    time.setIs24HourView(true);
	    builder.setView(time);
	    
	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				hour2=time.getCurrentHour();
				min2=time.getCurrentMinute();
				
				if(hour2<10 && min2<10)
					alarm2.setText("0" + hour2 + ":" + "0" + min2);
				else if(hour2<10 && min2>10)
					alarm2.setText("0" + hour2 + ":" + min2);
				else if(hour2>10 && min2<10)
					alarm2.setText(hour2 + ":" + "0" + min2);
				else
					alarm2.setText(hour2 + ":" + min2);
				sqlAlarm2=alarm2.getText().toString();
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
	
	private void toTimePick3() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Set Alarm");
	    final TimePicker time=new TimePicker(this);
	    time.setIs24HourView(true);
	    builder.setView(time);
	    
	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				hour3=time.getCurrentHour();
				min3=time.getCurrentMinute();
				
				if(hour3<10 && min3<10)
					alarm3.setText("0" + hour3 + ":" + "0" + min3);
				else if(hour3<10 && min3>10)
					alarm3.setText("0" + hour3 + ":" + min3);
				else if(hour3>10 && min3<10)
					alarm3.setText(hour3 + ":" + "0" + min3);
				else
					alarm3.setText(hour3 + ":" + min3);
				
				
				sqlAlarm3=alarm3.getText().toString();
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
