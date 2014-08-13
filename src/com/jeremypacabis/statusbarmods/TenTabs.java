package com.jeremypacabis.statusbarmods;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class TenTabs extends ViewFlipper {

	ViewFlipper mViewFlipper;
	private static final String INTENT_FILTER_TAB_1 = "com.jeremypacabis.statusbarmods,TenTabs.Tab1";
	private static final String INTENT_FILTER_TAB_2 = "com.jeremypacabis.statusbarmods,TenTabs.Tab2";
	private static final String INTENT_FILTER_TAB_3 = "com.jeremypacabis.statusbarmods,TenTabs.Tab3";
	private static final String INTENT_FILTER_TAB_4 = "com.jeremypacabis.statusbarmods,TenTabs.Tab4";
	private static final String INTENT_FILTER_TAB_5 = "com.jeremypacabis.statusbarmods,TenTabs.Tab5";
	private static final String INTENT_FILTER_TAB_6 = "com.jeremypacabis.statusbarmods,TenTabs.Tab6";
	private static final String INTENT_FILTER_TAB_7 = "com.jeremypacabis.statusbarmods,TenTabs.Tab7";
	private static final String INTENT_FILTER_TAB_8 = "com.jeremypacabis.statusbarmods,TenTabs.Tab8";
	private static final String INTENT_FILTER_TAB_9 = "com.jeremypacabis.statusbarmods,TenTabs.Tab9";
	private static final String INTENT_FILTER_TAB_10 = "com.jeremypacabis.statusbarmods,TenTabs.Tab10";

	public TenTabs(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mViewFlipper = (ViewFlipper) findViewById(R.id.tentabs);
		BroadcastReceiver mTab1 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				mViewFlipper.setDisplayedChild(0);

			}
		};
		BroadcastReceiver mTab2 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				mViewFlipper.setDisplayedChild(1);

			}
		};
		BroadcastReceiver mTab3 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				mViewFlipper.setDisplayedChild(2);

			}
		};
		BroadcastReceiver mTab4 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				mViewFlipper.setDisplayedChild(3);

			}
		};
		BroadcastReceiver mTab5 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				mViewFlipper.setDisplayedChild(4);

			}
		};
		BroadcastReceiver mTab6 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				mViewFlipper.setDisplayedChild(5);

			}
		};
		BroadcastReceiver mTab7 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				mViewFlipper.setDisplayedChild(6);

			}
		};
		BroadcastReceiver mTab8 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				mViewFlipper.setDisplayedChild(7);

			}
		};
		BroadcastReceiver mTab9 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				mViewFlipper.setDisplayedChild(8);

			}
		};
		BroadcastReceiver mTab10 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				mViewFlipper.setDisplayedChild(9);

			}
		};

		context.registerReceiver(mTab1, new IntentFilter(INTENT_FILTER_TAB_1));
		context.registerReceiver(mTab2, new IntentFilter(INTENT_FILTER_TAB_2));
		context.registerReceiver(mTab3, new IntentFilter(INTENT_FILTER_TAB_3));
		context.registerReceiver(mTab4, new IntentFilter(INTENT_FILTER_TAB_4));
		context.registerReceiver(mTab5, new IntentFilter(INTENT_FILTER_TAB_5));
		context.registerReceiver(mTab6, new IntentFilter(INTENT_FILTER_TAB_6));
		context.registerReceiver(mTab7, new IntentFilter(INTENT_FILTER_TAB_7));
		context.registerReceiver(mTab8, new IntentFilter(INTENT_FILTER_TAB_8));
		context.registerReceiver(mTab9, new IntentFilter(INTENT_FILTER_TAB_9));
		context.registerReceiver(mTab10, new IntentFilter(INTENT_FILTER_TAB_10));
	}

}
