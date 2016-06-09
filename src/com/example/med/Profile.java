package com.example.med;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends Activity implements OnClickListener{
	
	TextView name,age;
	Button add,view;
	
	String presName;
	int rowId;
	
	String sqlName,sqlAge;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		
		name=(TextView) findViewById(R.id.textName);
		age=(TextView) findViewById(R.id.textAge);
		
		add=(Button) findViewById(R.id.button_add);
		view=(Button) findViewById(R.id.button_view);
		add.setOnClickListener(this);
		view.setOnClickListener(this);
		
		toDataCollection();
		
	}

	private void toDataCollection() {
		// TODO Auto-generated method stub
		Information info= new Information(this);
		info.open();
		sqlName = info.getName(1);
		sqlAge = info.getAge(1);
		info.close();
		
		name.setText(sqlName);
		age.setText(sqlAge);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.button_add:
			Intent iSL=new Intent("com.example.med.SICKNESSLIST");
			startActivity(iSL);
			break;
			
		case R.id.button_view:
			Intent iVSL=new Intent("com.example.med.VIEWSICKNESSLIST");
			startActivity(iVSL);
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
