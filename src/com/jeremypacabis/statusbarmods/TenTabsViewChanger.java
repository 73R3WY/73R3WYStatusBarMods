package com.jeremypacabis.statusbarmods;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TenTabsViewChanger extends HorizontalScrollView {

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
	ImageView mIV1, mIV2, mIV3, mIV4, mIV5, mIV6, mIV7, mIV8, mIV9, mIV10;
	LinearLayout.LayoutParams mIVLP, mLLLP;
	LinearLayout mLL;
	int MARGIN;
	private Intent mIntent;
	private Context mContext;

	public TenTabsViewChanger(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		MARGIN = (int) (0.5f + (8.0f * getContext().getResources()
				.getDisplayMetrics().density));
		mLL = new LinearLayout(context);
		mLL.setOrientation(LinearLayout.HORIZONTAL);
		mLLLP = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		mLL.setLayoutParams(mLLLP);
		mIVLP = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		mIVLP.topMargin = MARGIN;
		mIVLP.bottomMargin = MARGIN;

		mIV1 = new ImageView(context);
		mIV1.setImageResource(R.drawable.stat_sys_battery_1);
		mIV1.setLayoutParams(mIVLP);
		mLL.addView(mIV1);
		mIV1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIV1.setSelected(true);
				mIV2.setSelected(false);
				mIV3.setSelected(false);
				mIV4.setSelected(false);
				mIV5.setSelected(false);
				mIV6.setSelected(false);
				mIV7.setSelected(false);
				mIV8.setSelected(false);
				mIV9.setSelected(false);
				mIV10.setSelected(false);
				mIntent = new Intent();
				mIntent.setAction(INTENT_FILTER_TAB_1);
				mContext.sendBroadcast(mIntent);
			}
		});

		mIV2 = new ImageView(context);
		mIV2.setImageResource(R.drawable.stat_sys_battery_2);
		mIV2.setLayoutParams(mIVLP);
		mLL.addView(mIV2);
		mIV2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIV1.setSelected(false);
				mIV2.setSelected(true);
				mIV3.setSelected(false);
				mIV4.setSelected(false);
				mIV5.setSelected(false);
				mIV6.setSelected(false);
				mIV7.setSelected(false);
				mIV8.setSelected(false);
				mIV9.setSelected(false);
				mIV10.setSelected(false);
				mIntent = new Intent();
				mIntent.setAction(INTENT_FILTER_TAB_2);
				mContext.sendBroadcast(mIntent);
			}
		});

		mIV3 = new ImageView(context);
		mIV3.setImageResource(R.drawable.stat_sys_battery_3);
		mIV3.setLayoutParams(mIVLP);
		mLL.addView(mIV3);
		mIV3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIV1.setSelected(false);
				mIV2.setSelected(false);
				mIV3.setSelected(true);
				mIV4.setSelected(false);
				mIV5.setSelected(false);
				mIV6.setSelected(false);
				mIV7.setSelected(false);
				mIV8.setSelected(false);
				mIV9.setSelected(false);
				mIV10.setSelected(false);
				mIntent = new Intent();
				mIntent.setAction(INTENT_FILTER_TAB_3);
				mContext.sendBroadcast(mIntent);
			}
		});

		mIV4 = new ImageView(context);
		mIV4.setImageResource(R.drawable.stat_sys_battery_4);
		mIV4.setLayoutParams(mIVLP);
		mLL.addView(mIV4);
		mIV4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIV1.setSelected(false);
				mIV2.setSelected(false);
				mIV3.setSelected(false);
				mIV4.setSelected(true);
				mIV5.setSelected(false);
				mIV6.setSelected(false);
				mIV7.setSelected(false);
				mIV8.setSelected(false);
				mIV9.setSelected(false);
				mIV10.setSelected(false);
				mIntent = new Intent();
				mIntent.setAction(INTENT_FILTER_TAB_4);
				mContext.sendBroadcast(mIntent);
			}
		});

		mIV5 = new ImageView(context);
		mIV5.setImageResource(R.drawable.stat_sys_battery_5);
		mIV5.setLayoutParams(mIVLP);
		mLL.addView(mIV5);
		mIV5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIV1.setSelected(false);
				mIV2.setSelected(false);
				mIV3.setSelected(false);
				mIV4.setSelected(false);
				mIV5.setSelected(true);
				mIV6.setSelected(false);
				mIV7.setSelected(false);
				mIV8.setSelected(false);
				mIV9.setSelected(false);
				mIV10.setSelected(false);
				mIntent = new Intent();
				mIntent.setAction(INTENT_FILTER_TAB_5);
				mContext.sendBroadcast(mIntent);
			}
		});

		mIV6 = new ImageView(context);
		mIV6.setImageResource(R.drawable.stat_sys_battery_6);
		mIV6.setLayoutParams(mIVLP);
		mLL.addView(mIV6);
		mIV6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIV1.setSelected(false);
				mIV2.setSelected(false);
				mIV3.setSelected(false);
				mIV4.setSelected(false);
				mIV5.setSelected(false);
				mIV6.setSelected(true);
				mIV7.setSelected(false);
				mIV8.setSelected(false);
				mIV9.setSelected(false);
				mIV10.setSelected(false);
				mIntent = new Intent();
				mIntent.setAction(INTENT_FILTER_TAB_6);
				mContext.sendBroadcast(mIntent);
			}
		});

		mIV7 = new ImageView(context);
		mIV7.setImageResource(R.drawable.stat_sys_battery_7);
		mIV7.setLayoutParams(mIVLP);
		mLL.addView(mIV7);
		mIV7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIV1.setSelected(false);
				mIV2.setSelected(false);
				mIV3.setSelected(false);
				mIV4.setSelected(false);
				mIV5.setSelected(false);
				mIV6.setSelected(false);
				mIV7.setSelected(true);
				mIV8.setSelected(false);
				mIV9.setSelected(false);
				mIV10.setSelected(false);
				mIntent = new Intent();
				mIntent.setAction(INTENT_FILTER_TAB_7);
				mContext.sendBroadcast(mIntent);
			}
		});

		mIV8 = new ImageView(context);
		mIV8.setImageResource(R.drawable.stat_sys_battery_8);
		mIV8.setLayoutParams(mIVLP);
		mLL.addView(mIV8);
		mIV8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIV1.setSelected(false);
				mIV2.setSelected(false);
				mIV3.setSelected(false);
				mIV4.setSelected(false);
				mIV5.setSelected(false);
				mIV6.setSelected(false);
				mIV7.setSelected(false);
				mIV8.setSelected(true);
				mIV9.setSelected(false);
				mIV10.setSelected(false);
				mIntent = new Intent();
				mIntent.setAction(INTENT_FILTER_TAB_8);
				mContext.sendBroadcast(mIntent);
			}
		});

		mIV9 = new ImageView(context);
		mIV9.setImageResource(R.drawable.stat_sys_battery_9);
		mIV9.setLayoutParams(mIVLP);
		mLL.addView(mIV9);
		mIV9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIV1.setSelected(false);
				mIV2.setSelected(false);
				mIV3.setSelected(false);
				mIV4.setSelected(false);
				mIV5.setSelected(false);
				mIV6.setSelected(false);
				mIV7.setSelected(false);
				mIV8.setSelected(false);
				mIV9.setSelected(true);
				mIV10.setSelected(false);
				mIntent = new Intent();
				mIntent.setAction(INTENT_FILTER_TAB_9);
				mContext.sendBroadcast(mIntent);
			}
		});

		mIV10 = new ImageView(context);
		mIV10.setImageResource(R.drawable.stat_sys_battery_10);
		mIV10.setLayoutParams(mIVLP);
		mLL.addView(mIV10);
		mIV10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIV1.setSelected(false);
				mIV2.setSelected(false);
				mIV3.setSelected(false);
				mIV4.setSelected(false);
				mIV5.setSelected(false);
				mIV6.setSelected(false);
				mIV7.setSelected(false);
				mIV8.setSelected(false);
				mIV9.setSelected(false);
				mIV10.setSelected(true);
				mIntent = new Intent();
				mIntent.setAction(INTENT_FILTER_TAB_10);
				mContext.sendBroadcast(mIntent);
			}
		});

		addView(mLL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
		setHorizontalScrollBarEnabled(false);
	}
}
