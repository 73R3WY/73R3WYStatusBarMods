package com.jeremypacabis.statusbarmods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class AnimatedStatusBar extends View {

	private View mView;

	public AnimatedStatusBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mView = findViewById(R.id.animatedsb);
		mView.setBackgroundColor(getResources().getColor(android.R.color.white));
	}

}