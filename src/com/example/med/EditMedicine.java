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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class EditMedicine extends Activity implements OnClickListener, OnCheckedChangeListener{
	
	EditText med,day,quan;
	CheckBox before,after;
	
	TextView alarm1,alarm2,alarm3;
	Button save;
	
	static String filename="MyData";
	SharedPreferences some;
	String data;
	
	int medRowId;
	
	int hour1,min1,hour2,min2,hour3,min3;
	String sqlAlarm1=" ",sqlAlarm2=" ",sqlAlarm3=" ";
	
	int presentHour,presentMin;
	
	String medName,dayDuration,quantity,sbefore,safter,sqla1,sqla2,sqla3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_med);
		
		some=getSharedPreferences(filename,0);
		data=some.getString("sharedString","empty");
		medRowId=Integer.parseInt(data);
		
		med=(EditText) findViewById(R.id.editMedicine);
		day=(EditText) findViewById(R.id.editDay);
		quan=(EditText) findViewById(R.id.editQuantity);
		
		before=(CheckBox) findViewById(R.id.checkBefore);
		after=(CheckBox) findViewById(R.id.checkAfter);
		
		before.setOnCheckedChangeListener(this);
		after.setOnCheckedChangeListener(this);
		
		alarm1=(TextView) findViewById(R.id.textAlarm1);
		alarm2=(TextView) findViewById(R.id.textAlarm2);
		alarm3=(TextView) findViewById(R.id.textAlarm3);
		
		alarm1.setOnClickListener(this);
		alarm2.setOnClickListener(this);
		alarm3.setOnClickListener(this);
		
		save=(Button) findViewById(R.id.button_save);
		save.setOnClickListener(this);
		
		toShowMedicineInfo();
		
		
	}

	private void toShowMedicineInfo() {
		// TODO Auto-generated method stub
		MedicineInfo medInfo=new MedicineInfo(this);
		medInfo.open();
	    medName=medInfo.getMedicineName(medRowId);
		dayDuration=medInfo.getDay(medRowId);
		quantity=medInfo.getQuantity(medRowId);
		sbefore=medInfo.getBefore(medRowId);
		safter=medInfo.getAfter(medRowId);
	    sqla1=medInfo.getAlarm1(medRowId);
	    sqla2=medInfo.getAlarm2(medRowId);
	    sqla3=medInfo.getAlarm3(medRowId);
		medInfo.close();
		
		if(sbefore.equals("no"))
			sbefore="Before Meal Not Selected";
		if(safter.equals("no"))
			safter="After Meal Not Selected";
		
		med.setText(medName);
		day.setText(dayDuration);
		quan.setText(quantity);
		
		before.setText(sbefore);
		after.setText(safter);
		
		alarm1.setText(sqla1);
		alarm2.setText(sqla2);
		alarm3.setText(sqla3);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
	case R.id.textAlarm1:
		toTimePick1();	
		break;
		
	case R.id.textAlarm2:
		toTimePick2();	
		break;
		
	case R.id.textAlarm3:
		toTimePick3();	
		break;
		
	case R.id.button_save:
		
	    toWriteDatabase();
	    Intent i=new Intent("com.example.med.VIEWMEDICINE");
		startActivity(i);
		
		break;
		
		}
		
	}

	private void toWriteDatabase() {
		// TODO Auto-generated method stub
		medName=med.getText().toString();
		dayDuration=day.getText().toString();
		quantity=quan.getText().toString();
		
		int d=Integer.parseInt(dayDuration);
		int q=Integer.parseInt(quantity);
		
		MedicineInfo m1=new MedicineInfo(this);
		m1.open();
		m1.createEntry(medRowId,medName, d, q, sbefore, safter, sqlAlarm1,sqlAlarm2,sqlAlarm3);
		m1.close();
		
	}

	private void toTimePick1() {
		// TODO Auto-generated method stub
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
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		
		case R.id.checkAfter:
			String a=after.getText().toString();
			
			if(a.equals("After Meal Not Selected"))
				safter="After Meal";
			else
				safter="After Meal Not Selected";
			
			after.setText(safter);
			
			break;
			
		case R.id.checkBefore:
				String b=before.getText().toString();
				
				if(b.equals("After Meal Not Selected"))
					sbefore="Before Meal";
				else
					sbefore="Before Meal Not Selected";
				
				before.setText(sbefore);
				
				break;
		}
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
