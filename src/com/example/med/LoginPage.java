package com.example.med;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;


public class LoginPage extends Activity implements View.OnClickListener{
	
	EditText editName,editPas;
	Button login,register,delete;
	
	String nameArray[]=new String[10];
	String pasArray[]=new String[10];
	
	int m;
	int rowId;
	
	static String filename="MySickData";
	SharedPreferences sick;
	String data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sick=getSharedPreferences(filename,0);
		data=sick.getString("sharedString","empty");
		
		if(data.equals("empty")){
			toInitializeDatabase();
		}
		
		editName=(EditText) findViewById(R.id.editName);
		editPas=(EditText) findViewById(R.id.editPass);
		login=(Button) findViewById(R.id.button_login);
		register=(Button) findViewById(R.id.button_register);
		
		
		login.setOnClickListener(this);
		register.setOnClickListener(this);
		delete.setOnClickListener(this);
		
	}

	private void toInitializeDatabase() {
		// TODO Auto-generated method stub
		Information entry = new Information(this);
		   entry.open();
		   entry.initialEntry("no",0, "no");
		   entry.close();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
    switch(v.getId()){
		
		case R.id.button_login:
			
			Information info=new Information(this);
			info.open();
			String n=info.getName(1);
			info.close();
			
			if(!n.equals("no")){
			
			
			String name=editName.getText().toString();
			String pas=editPas.getText().toString();
			
			Information info1=new Information(this);
			info1.open();
			String sqlName=info1.getName(1);
			String sqlPas=info1.getPas(1);
			info1.close();
			
			if(sqlName.equals(name) && sqlPas.equals(pas)){
				Intent i=new Intent("com.example.med.PROFILE");
			    startActivity(i);
			}
			else if(sqlName.equals(name) && !sqlPas.equals(pas))
				toWarningDialog();
			else if(!sqlName.equals(name) && sqlPas.equals(pas))
				toWarningDialog();
			else if(!sqlName.equals(name) && !sqlPas.equals(pas))
				toRegisterDialog();
			
			}
			
			break;
			
		case R.id.button_register:
			
			Information info2=new Information(this);
			info2.open();
			String n2=info2.getName(1);
			info2.close();
			
			if(n2.equals("no")){
			Intent i1= new Intent("com.example.med.CREATEPROFILE");
			startActivity(i1);
			}
			
			else
				toProvideWarning();
			
			break;
			
		
			
    }
		
	}

	private void toProvideWarning() {
		// TODO Auto-generated method stub
		Dialog d=new Dialog(this);
		d.setTitle("Warning");
		TextView tv=new TextView(this);
		tv.setText("You Can Not Register\nAn User Already Exists");
		tv.setTextSize(20);
		d.setContentView(tv);
		d.show();
	}

	private void toWarningDialog() {
		// TODO Auto-generated method stub
		Dialog d=new Dialog(this);
		d.setTitle("Warning");
		TextView tv=new TextView(this);
		tv.setText("Name or Password Mismatch");
		tv.setTextSize(20);
		d.setContentView(tv);
		d.show();
	}

	private void toRegisterDialog() {
		// TODO Auto-generated method stub
		Dialog d=new Dialog(this);
		d.setTitle("Register Member");
		TextView tv=new TextView(this);
		tv.setText("Please Register First");
		tv.setTextSize(20);
		d.setContentView(tv);
		d.show();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	
	

}
