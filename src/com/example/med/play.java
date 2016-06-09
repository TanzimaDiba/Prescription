package com.example.med;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class play extends Activity{

	
	MediaPlayer m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		m=MediaPlayer.create(this,R.raw.jal2);
		m.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		m.release();
		finish();
	}
	
	

}
