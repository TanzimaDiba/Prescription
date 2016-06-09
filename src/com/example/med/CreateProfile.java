package com.example.med;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateProfile extends Activity implements View.OnClickListener{
	
	EditText sqlName,sqlAge,sqlPas,sqlConfirm;
	Button save;
	
	String nameArray[]=new String[10];
	int row;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_profile);
		
		sqlName=(EditText) findViewById(R.id.editName);
		sqlAge=(EditText) findViewById(R.id.editAge);
		sqlPas=(EditText) findViewById(R.id.editPass);
		sqlConfirm=(EditText) findViewById(R.id.editConfirm);
		
        save=(Button) findViewById(R.id.button_save);
		save.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String name=sqlName.getText().toString();
		String age=sqlAge.getText().toString();
		String pas=sqlPas.getText().toString();
		String confirm=sqlConfirm.getText().toString();
		
		int ag=Integer.parseInt(age);
		
		if(name.isEmpty() || age.isEmpty() || pas.isEmpty() || confirm.isEmpty())
			toProvideWarning();
		
		else{
		
		if(pas.equals(confirm)){
			
		   Information entry = new Information(this);
		   entry.open();
		   entry.createEntry(1,name,ag, pas);
		   entry.close();
		   
		   finish();
		}
		else
			toWarningDialog();
		
		}
				
    }

	private void toProvideWarning() {
		// TODO Auto-generated method stub
		Dialog d=new Dialog(this);
		d.setTitle("Warning");
		TextView tv=new TextView(this);
		tv.setText("Please Provide All Information");
		tv.setTextSize(20);
		d.setContentView(tv);
		d.show();
	}

	private void toWarningDialog() {
		// TODO Auto-generated method stub
		Dialog d=new Dialog(this);
		d.setTitle("Warning");
		TextView tv=new TextView(this);
		tv.setText("Password Doesn't Match\nPlease Check Again");
		tv.setTextSize(20);
		d.setContentView(tv);
		d.show();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
