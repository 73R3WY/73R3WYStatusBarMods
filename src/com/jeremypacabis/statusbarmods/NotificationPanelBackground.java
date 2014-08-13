package com.jeremypacabis.statusbarmods;

import java.io.File;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

public class NotificationPanelBackground extends View {

	private static final String SET_BACKGROUND = "com.jeremypacabis.statusbarmods.SET_BACKGROUND";
	private static final String SET_DEFAULT = "com.jeremypacabis.statusbarmods.SET_DEFAULT";
	private static final String PREFERENCE_KEY = "com.jeremypacabis.statusbarmods";
	private static final String SET_KEY = "com.jeremypacabis.statusbarmods.NotificationPanelChanged";
	private View mView;
	SharedPreferences sharedPreferences;
	boolean set;
	BroadcastReceiver mReceiver;

	public NotificationPanelBackground(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mView = (View) findViewById(R.id.animatednotifbg);
		sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY,
				Context.MODE_PRIVATE);
		set = sharedPreferences.getBoolean(SET_KEY, false);
		setBackground();
		mReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), intent.getAction(),
						Toast.LENGTH_LONG).show();
				set = intent.getBooleanExtra(SET_KEY, false);
				sharedPreferences.edit().putBoolean(SET_KEY, set).commit();
				setBackground();
			}
		};
		context.registerReceiver(mReceiver, new IntentFilter(SET_BACKGROUND));
		context.registerReceiver(mReceiver, new IntentFilter(SET_DEFAULT));
	}

	@SuppressWarnings("deprecation")
	private void setBackground() {
		// TODO Auto-generated method stub
		if (set) {
			mView.setBackgroundDrawable(new BitmapDrawable(getResources(),
					BitmapFactory.decodeFile(Environment
							.getExternalStorageDirectory().getAbsolutePath()
							+ File.separator
							+ "AnimatedUI"
							+ File.separator
							+ "43.png")));
			Toast.makeText(getContext(), "set is TRUE", Toast.LENGTH_LONG)
					.show();
		} else {
			setDefaultBackground();
			Toast.makeText(getContext(), "set is FALSE", Toast.LENGTH_LONG)
					.show();
		}
	}

	private void setDefaultBackground() {
		// TODO Auto-generated method stub
		mView.setBackgroundColor(getResources().getColor(android.R.color.black));
	}
}
