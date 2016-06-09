package com.example.med;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DeleteProfile extends Activity{
	
	TextView show;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_profile);
		
		show=(TextView) findViewById(R.id.textView1);
		
		MedicineInfo info=new MedicineInfo(this);
		info.open();
		String s=info.getData();
		show.setText(s);
		info.close();
	}

}
