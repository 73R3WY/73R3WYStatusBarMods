package com.jeremypacabis.statusbarmods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private static final String SET_BACKGROUND = "com.jeremypacabis.statusbarmods.SET_BACKGROUND";
	private static final String SET_DEFAULT = "com.jeremypacabis.statusbarmods.SET_DEFAULT";
	private static final String SET_KEY = "com.jeremypacabis.statusbarmods.NotificationPanelChanged";

	private Button btnSetBG, btnSetDef, btnShow;
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSetBG = (Button) findViewById(R.id.btnSetBackground);
		btnSetDef = (Button) findViewById(R.id.btnSetDefault);
		btnShow = (Button) findViewById(R.id.btnShow);
		btnSetBG.setOnClickListener(this);
		btnSetDef.setOnClickListener(this);
		btnShow.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSetBackground:
			mIntent = new Intent();
			mIntent.setAction(SET_BACKGROUND);
			mIntent.putExtra(SET_KEY, true);
			sendBroadcast(mIntent);
			break;
		case R.id.btnSetDefault:
			mIntent = new Intent();
			mIntent.setAction(SET_DEFAULT);
			mIntent.putExtra(SET_KEY, false);
			sendBroadcast(mIntent);
			break;
		case R.id.btnShow:
			mIntent = new Intent(this, Results.class);
			startActivity(mIntent);
			break;
		}
	}
}
