package com.jeremypacabis.statusbarmods;

import java.lang.reflect.Method;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class AppLauncherOnSystemUI extends ScrollView {

	private Context mContext;
	private LinearLayout LL, HLL;
	private TextView APP1, APP2, APP3, APP4, APP5, APP6, APP7, APP8, APP9,
			APP10;
	private Drawable APPICON1, APPICON2, APPICON3, APPICON4, APPICON5,
			APPICON6, APPICON7, APPICON8, APPICON9, APPICON10;
	Intent mIntent;
	private static final int CAPACITY = 10;
	private static final String TEXT_SIZE_INTENT_ACTION = "com.jeremypacabis.statusbarmods.TEXT_SIZE";
	private static final String TEXT_SIZE_INTENT_EXTRA_KEY = "com.jeremypacabis.statusbarmods.TEXT_SIZE_INTENT_EXTRA";
	private static final String KEY_SETTINGS = "com.jeremypacabis.statusbarmods.SETTINGS";
	private static final String VIEW_MODE = "com.jeremypacabis.statusbarmods.VIEW_MODE";
	private static final String ICON_SIZE = "com.jeremypacabis.statusbarmods.ICON_SIZE";
	private static final String ICON_SIZE_FILTER = "com.jeremypacabis.statusbarmods.ICON_SIZE_SETTINGS";
	private static final String VIEW_MODE_KEY = "com.jeremypacabis.statusbarmods.VIEW_MODE_KEY";
	private static final String KEY_INTENT_ACTION[] = { "IAPP1", "IAPP2",
			"IAPP3", "IAPP4", "IAPP5", "IAPP6", "IAPP7", "IAPP8", "IAPP9",
			"IAPP10" };
	private static final String KEY_TEXTSIZE_KEY[] = { "TSAPP1", "TSAPP2",
			"TSAPP3", "TSAPP4", "TSAPP5", "TSAPP6", "TSAPP7", "TSAPP8",
			"TSAPP9", "TSAPP10" };
	private static final String KEY_APPNAME_KEY[] = {
			"com.jeremypacabis.statusbarmods.APP1",
			"com.jeremypacabis.statusbarmods.APP2",
			"com.jeremypacabis.statusbarmods.APP3",
			"com.jeremypacabis.statusbarmods.APP4",
			"com.jeremypacabis.statusbarmods.APP5",
			"com.jeremypacabis.statusbarmods.APP6",
			"com.jeremypacabis.statusbarmods.APP7",
			"com.jeremypacabis.statusbarmods.APP8",
			"com.jeremypacabis.statusbarmods.APP9",
			"com.jeremypacabis.statusbarmods.APP10" };
	private static final String KEY_INTENTNAME_KEY[] = {
			"com.jeremypacabis.statusbarmods.INTENT1",
			"com.jeremypacabis.statusbarmods.INTENT2",
			"com.jeremypacabis.statusbarmods.INTENT3",
			"com.jeremypacabis.statusbarmods.INTENT4",
			"com.jeremypacabis.statusbarmods.INTENT5",
			"com.jeremypacabis.statusbarmods.INTENT6",
			"com.jeremypacabis.statusbarmods.INTENT7",
			"com.jeremypacabis.statusbarmods.INTENT8",
			"com.jeremypacabis.statusbarmods.INTENT9",
			"com.jeremypacabis.statusbarmods.INTENT10" };
	private String[] KEY_APPNAME = new String[CAPACITY];
	private float[] TEXT_SIZES = new float[CAPACITY];
	private static final String PACKAGE_NAMES[] = { "PAPK1", "PAPK2", "PAPK3",
			"PAPK4", "PAPK5", "PAPK6", "PAPK7", "PAPK8", "PAPK9", "PAPK10" };
	private static final String KEY_APPINTENT[] = { "XAPP1", "XAPP2", "XAPP3",
			"XAPP4", "XAPP5", "XAPP6", "XAPP7", "XAPP8", "XAPP9", "XAPP10" };
	private static final String KEY_LIST_KEY[] = { "LIST1", "LIST2", "LIST3",
			"LIST4", "LIST5", "LIST6", "LIST7", "LIST8", "LIST9", "LIST10" };
	private BroadcastReceiver BRAPP1, BRAPP2, BRAPP3, BRAPP4, BRAPP5, BRAPP6,
			BRAPP7, BRAPP8, BRAPP9, BRAPP10, SETTINGS, ICONSIZE, TEXTSIZE;
	private static final int BACKGROUND = android.R.drawable.alert_light_frame;
	int ViewMode, iconSize, defaultIconSize;

	HorizontalScrollView HSV;
	LinearLayout.LayoutParams HLLP;
	LinearLayout.LayoutParams LLP;
	LinearLayout.LayoutParams TVLP;
	SharedPreferences sp;
	SharedPreferences.Editor spe;

	@SuppressWarnings("deprecation")
	public AppLauncherOnSystemUI(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		this.mContext = context;
		this.mIntent = new Intent();
		setOverScrollMode(OVER_SCROLL_NEVER);
		HLLP = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
		LLP = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
				android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
		TVLP = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
				android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
		LL = new LinearLayout(mContext);
		LL.setLayoutParams(LLP);
		LL.setOrientation(LinearLayout.VERTICAL);
		HLL = new LinearLayout(mContext);
		HLL.setLayoutParams(HLLP);
		HLL.setOrientation(LinearLayout.HORIZONTAL);
		HSV = new HorizontalScrollView(mContext);
		HSV.setHorizontalScrollBarEnabled(false);
		HSV.setLayoutParams(LLP);
		APP1 = new TextView(mContext);
		APP2 = new TextView(mContext);
		APP3 = new TextView(mContext);
		APP4 = new TextView(mContext);
		APP5 = new TextView(mContext);
		APP6 = new TextView(mContext);
		APP7 = new TextView(mContext);
		APP8 = new TextView(mContext);
		APP9 = new TextView(mContext);
		APP10 = new TextView(mContext);
		setBroadcastReceivers();
		setAPP1OnClickListener();
		setAPP2OnClickListener();
		setAPP3OnClickListener();
		setAPP4OnClickListener();
		setAPP5OnClickListener();
		setAPP6OnClickListener();
		setAPP7OnClickListener();
		setAPP8OnClickListener();
		setAPP9OnClickListener();
		setAPP10OnClickListener();
		ViewMode = getViewMode();
		setViewMode(ViewMode);
	}

	private void setViewMode(int vm) {
		saveViewMode();
		LL.removeAllViews();
		HLL.removeAllViews();
		HSV.removeAllViews();
		removeAllViews();
		addTextViewToLL();
		addView(LL);
		setTextViewParams();
		loadList();
		switch (vm) {
		case 0:

			break;
		case 1:
			iconsOnly();
			break;
		case 2:
			APP1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			APP2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			APP3.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			APP4.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			APP5.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			APP6.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			APP7.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			APP8.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			APP9.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			APP10.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
					null);
			break;
		case 3:
			iconsOnly();
			setHorizontalLayout();
			break;
		}
	}

	private void setHorizontalLayout() {
		LL.removeAllViews();
		removeAllViews();
		HLL.addView(APP1);
		HLL.addView(APP2);
		HLL.addView(APP3);
		HLL.addView(APP4);
		HLL.addView(APP5);
		HLL.addView(APP6);
		HLL.addView(APP7);
		HLL.addView(APP8);
		HLL.addView(APP9);
		HLL.addView(APP10);
		HSV.addView(HLL);
		removeView(LL);
		addView(HSV);
	}

	private void iconsOnly() {
		APP1.setText("");
		APP2.setText("");
		APP3.setText("");
		APP4.setText("");
		APP5.setText("");
		APP6.setText("");
		APP7.setText("");
		APP8.setText("");
		APP9.setText("");
		APP10.setText("");
	}

	private void saveViewMode() {
		openSPE();
		spe.putInt(VIEW_MODE, ViewMode);
		commitSPE();
	}

	private void saveTextSize(int position, float textSizeInPX) {
		openSPE();
		spe.putFloat(KEY_TEXTSIZE_KEY[position], textSizeInPX);
		commitSPE();
	}

	private float getTextSize(int position) {
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		return sp.getFloat(KEY_TEXTSIZE_KEY[position], 24.0f);
	}

	private int getViewMode() {
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		return sp.getInt(VIEW_MODE, 0);
	}

	private int getIconSize() {
		defaultIconSize = (int) ((int) mContext.getResources()
				.getDisplayMetrics().densityDpi / 3.3333333333333);
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		return sp.getInt(ICON_SIZE, defaultIconSize);
	}

	private void saveIconSize() {
		openSPE();
		spe.putInt(ICON_SIZE, iconSize);
		commitSPE();
	}

	private void setTextViewLabel(TextView v, int position) {
		if (ViewMode != 1 && ViewMode != 3) {
			v.setText(KEY_APPNAME[position]);
		}
	}

	private void setTextViewIcon(TextView v, int position, Drawable AppIcon) {
		if (ViewMode != 2) {
			try {
				AppIcon = mContext.getPackageManager().getApplicationIcon(
						KEY_APPINTENT[position]);
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			AppIcon.setBounds(0, 0, iconSize, iconSize);
			v.setText("");
			v.setCompoundDrawables(AppIcon, null, null, null);
		}
	}

	private void setBroadcastReceivers() {
		// TODO Auto-generated method stub

		SETTINGS = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				ViewMode = intent.getIntExtra(VIEW_MODE_KEY, 0);
				setViewMode(ViewMode);
				saveViewMode();
			}
		};

		ICONSIZE = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				iconSize = intent.getIntExtra(ICON_SIZE, defaultIconSize);
				saveIconSize();
				setViewMode(getViewMode());
			}
		};

		TEXTSIZE = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				for (int x = 0; x < CAPACITY; x++) {
					TEXT_SIZES[x] = intent.getFloatExtra(
							TEXT_SIZE_INTENT_EXTRA_KEY, 24.0f);
					saveTextSize(x, TEXT_SIZES[x]);
				}
				setViewMode(getViewMode());
			}
		};

		BRAPP1 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				KEY_APPNAME[0] = intent.getStringExtra(KEY_APPNAME_KEY[0]);
				KEY_APPINTENT[0] = intent.getStringExtra(KEY_INTENTNAME_KEY[0]);
				saveReceivedData(0);
				if (KEY_APPNAME[0].contains("DELETE")
						|| KEY_APPINTENT[0].contains("DELETE")) {
					setViewGone(APP1);
				} else {
					setViewVisible(APP1);
					setTextViewLabel(APP1, 0);
					setTextViewIcon(APP1, 0, APPICON1);
					setViewMode(getViewMode());
					setAPP10OnClickListener();
				}
			}
		};
		BRAPP2 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				KEY_APPNAME[1] = intent.getStringExtra(KEY_APPNAME_KEY[1]);
				KEY_APPINTENT[1] = intent.getStringExtra(KEY_INTENTNAME_KEY[1]);
				saveReceivedData(1);
				if (KEY_APPNAME[1].contains("DELETE")
						|| KEY_APPINTENT[1].contains("DELETE")) {
					setViewGone(APP2);
				} else {
					setViewVisible(APP2);
					setTextViewLabel(APP2, 1);
					setTextViewIcon(APP2, 1, APPICON2);
					setViewMode(getViewMode());
					setAPP2OnClickListener();
				}
			}
		};
		BRAPP3 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				KEY_APPNAME[2] = intent.getStringExtra(KEY_APPNAME_KEY[2]);
				KEY_APPINTENT[2] = intent.getStringExtra(KEY_INTENTNAME_KEY[2]);
				saveReceivedData(2);
				if (KEY_APPNAME[2].contains("DELETE")
						|| KEY_APPINTENT[2].contains("DELETE")) {
					setViewGone(APP3);
				} else {
					setViewVisible(APP3);
					setTextViewLabel(APP3, 2);
					setTextViewIcon(APP3, 2, APPICON3);
					setViewMode(getViewMode());
					setAPP3OnClickListener();
				}
			}
		};
		BRAPP4 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				KEY_APPNAME[3] = intent.getStringExtra(KEY_APPNAME_KEY[3]);
				KEY_APPINTENT[3] = intent.getStringExtra(KEY_INTENTNAME_KEY[3]);
				saveReceivedData(3);
				if (KEY_APPNAME[3].contains("DELETE")
						|| KEY_APPINTENT[3].contains("DELETE")) {
					setViewGone(APP4);
				} else {
					setViewVisible(APP4);
					setTextViewLabel(APP4, 3);
					setTextViewIcon(APP4, 3, APPICON4);
					setViewMode(getViewMode());
					setAPP4OnClickListener();
				}
			}
		};
		BRAPP5 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				KEY_APPNAME[4] = intent.getStringExtra(KEY_APPNAME_KEY[4]);
				KEY_APPINTENT[4] = intent.getStringExtra(KEY_INTENTNAME_KEY[4]);
				saveReceivedData(4);
				if (KEY_APPNAME[4].contains("DELETE")
						|| KEY_APPINTENT[4].contains("DELETE")) {
					setViewGone(APP5);
				} else {
					setViewVisible(APP5);
					setTextViewLabel(APP5, 4);
					setTextViewIcon(APP5, 4, APPICON5);
					setViewMode(getViewMode());
					setAPP5OnClickListener();

				}
			}
		};
		BRAPP6 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				KEY_APPNAME[5] = intent.getStringExtra(KEY_APPNAME_KEY[5]);
				KEY_APPINTENT[5] = intent.getStringExtra(KEY_INTENTNAME_KEY[5]);
				saveReceivedData(5);
				if (KEY_APPNAME[5].contains("DELETE")
						|| KEY_APPINTENT[5].contains("DELETE")) {
					setViewGone(APP6);
				} else {
					setViewVisible(APP6);
					setTextViewLabel(APP6, 5);
					setTextViewIcon(APP6, 5, APPICON6);
					setViewMode(getViewMode());
					setAPP6OnClickListener();
				}
			}
		};
		BRAPP7 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				KEY_APPNAME[6] = intent.getStringExtra(KEY_APPNAME_KEY[6]);
				KEY_APPINTENT[6] = intent.getStringExtra(KEY_INTENTNAME_KEY[6]);
				saveReceivedData(6);
				if (KEY_APPNAME[6].contains("DELETE")
						|| KEY_APPINTENT[6].contains("DELETE")) {
					setViewGone(APP7);
				} else {
					setViewVisible(APP7);
					setTextViewLabel(APP7, 6);
					setTextViewIcon(APP7, 6, APPICON7);
					setViewMode(getViewMode());
					setAPP7OnClickListener();
				}
			}
		};
		BRAPP8 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				KEY_APPNAME[7] = intent.getStringExtra(KEY_APPNAME_KEY[7]);
				KEY_APPINTENT[7] = intent.getStringExtra(KEY_INTENTNAME_KEY[7]);
				saveReceivedData(7);
				if (KEY_APPNAME[7].contains("DELETE")
						|| KEY_APPINTENT[7].contains("DELETE")) {
					setViewGone(APP8);
				} else {
					setViewVisible(APP8);
					setTextViewLabel(APP8, 7);
					setTextViewIcon(APP8, 7, APPICON8);
					setViewMode(getViewMode());
					setAPP8OnClickListener();
				}
			}
		};
		BRAPP9 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				KEY_APPNAME[8] = intent.getStringExtra(KEY_APPNAME_KEY[8]);
				KEY_APPINTENT[8] = intent.getStringExtra(KEY_INTENTNAME_KEY[8]);
				saveReceivedData(8);
				if (KEY_APPNAME[8].contains("DELETE")
						|| KEY_APPINTENT[8].contains("DELETE")) {
					setViewGone(APP9);
				} else {
					setViewVisible(APP9);
					setTextViewLabel(APP9, 8);
					setTextViewIcon(APP9, 8, APPICON9);
					setViewMode(getViewMode());
					setAPP9OnClickListener();
				}
			}
		};
		BRAPP10 = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				KEY_APPNAME[9] = intent.getStringExtra(KEY_APPNAME_KEY[9]);
				KEY_APPINTENT[9] = intent.getStringExtra(KEY_INTENTNAME_KEY[9]);
				saveReceivedData(9);
				if (KEY_APPNAME[9].contains("DELETE")
						|| KEY_APPINTENT[9].contains("DELETE")) {
					setViewGone(APP10);
				} else {
					setViewVisible(APP10);
					setTextViewLabel(APP10, 9);
					setTextViewIcon(APP10, 9, APPICON10);
					setViewMode(getViewMode());
					setAPP10OnClickListener();
				}
			}
		};
		mContext.registerReceiver(TEXTSIZE, new IntentFilter(
				TEXT_SIZE_INTENT_ACTION));
		mContext.registerReceiver(ICONSIZE, new IntentFilter(ICON_SIZE_FILTER));
		mContext.registerReceiver(BRAPP1,
				new IntentFilter(KEY_INTENT_ACTION[0]));
		mContext.registerReceiver(BRAPP2,
				new IntentFilter(KEY_INTENT_ACTION[1]));
		mContext.registerReceiver(BRAPP3,
				new IntentFilter(KEY_INTENT_ACTION[2]));
		mContext.registerReceiver(BRAPP4,
				new IntentFilter(KEY_INTENT_ACTION[3]));
		mContext.registerReceiver(BRAPP5,
				new IntentFilter(KEY_INTENT_ACTION[4]));
		mContext.registerReceiver(BRAPP6,
				new IntentFilter(KEY_INTENT_ACTION[5]));
		mContext.registerReceiver(BRAPP7,
				new IntentFilter(KEY_INTENT_ACTION[6]));
		mContext.registerReceiver(BRAPP8,
				new IntentFilter(KEY_INTENT_ACTION[7]));
		mContext.registerReceiver(BRAPP9,
				new IntentFilter(KEY_INTENT_ACTION[8]));
		mContext.registerReceiver(BRAPP10, new IntentFilter(
				KEY_INTENT_ACTION[9]));
		mContext.registerReceiver(SETTINGS, new IntentFilter(KEY_SETTINGS));
	}

	private void addTextViewToLL() {
		// TODO Auto-generated method stub
		LL.addView(APP1);
		LL.addView(APP2);
		LL.addView(APP3);
		LL.addView(APP4);
		LL.addView(APP5);
		LL.addView(APP6);
		LL.addView(APP7);
		LL.addView(APP8);
		LL.addView(APP9);
		LL.addView(APP10);
	}

	private void setTextViewParams() {
		// TODO Auto-generated method stub
		APP1.setLayoutParams(TVLP);
		APP2.setLayoutParams(TVLP);
		APP3.setLayoutParams(TVLP);
		APP4.setLayoutParams(TVLP);
		APP5.setLayoutParams(TVLP);
		APP6.setLayoutParams(TVLP);
		APP7.setLayoutParams(TVLP);
		APP8.setLayoutParams(TVLP);
		APP9.setLayoutParams(TVLP);
		APP10.setLayoutParams(TVLP);
		APP1.setSingleLine();
		APP2.setSingleLine();
		APP3.setSingleLine();
		APP4.setSingleLine();
		APP5.setSingleLine();
		APP6.setSingleLine();
		APP7.setSingleLine();
		APP8.setSingleLine();
		APP9.setSingleLine();
		APP10.setSingleLine();
		APP1.setEllipsize(TruncateAt.MARQUEE);
		APP2.setEllipsize(TruncateAt.MARQUEE);
		APP3.setEllipsize(TruncateAt.MARQUEE);
		APP4.setEllipsize(TruncateAt.MARQUEE);
		APP5.setEllipsize(TruncateAt.MARQUEE);
		APP6.setEllipsize(TruncateAt.MARQUEE);
		APP7.setEllipsize(TruncateAt.MARQUEE);
		APP8.setEllipsize(TruncateAt.MARQUEE);
		APP9.setEllipsize(TruncateAt.MARQUEE);
		APP10.setEllipsize(TruncateAt.MARQUEE);
		APP1.setMarqueeRepeatLimit(-1);
		APP2.setMarqueeRepeatLimit(-1);
		APP3.setMarqueeRepeatLimit(-1);
		APP4.setMarqueeRepeatLimit(-1);
		APP5.setMarqueeRepeatLimit(-1);
		APP6.setMarqueeRepeatLimit(-1);
		APP7.setMarqueeRepeatLimit(-1);
		APP8.setMarqueeRepeatLimit(-1);
		APP9.setMarqueeRepeatLimit(-1);
		APP10.setMarqueeRepeatLimit(-1);
		for (int x = 0; x < CAPACITY; x++) {
			TEXT_SIZES[x] = getTextSize(x);
		}
		setTheTextSizes(APP1, TEXT_SIZES[0]);
		setTheTextSizes(APP2, TEXT_SIZES[1]);
		setTheTextSizes(APP3, TEXT_SIZES[2]);
		setTheTextSizes(APP4, TEXT_SIZES[3]);
		setTheTextSizes(APP5, TEXT_SIZES[4]);
		setTheTextSizes(APP6, TEXT_SIZES[5]);
		setTheTextSizes(APP7, TEXT_SIZES[6]);
		setTheTextSizes(APP8, TEXT_SIZES[7]);
		setTheTextSizes(APP9, TEXT_SIZES[8]);
		setTheTextSizes(APP10, TEXT_SIZES[9]);
		APP1.setFocusable(true);
		APP2.setFocusable(true);
		APP3.setFocusable(true);
		APP4.setFocusable(true);
		APP5.setFocusable(true);
		APP6.setFocusable(true);
		APP7.setFocusable(true);
		APP8.setFocusable(true);
		APP9.setFocusable(true);
		APP10.setFocusable(true);
		APP1.setFocusableInTouchMode(true);
		APP2.setFocusableInTouchMode(true);
		APP3.setFocusableInTouchMode(true);
		APP4.setFocusableInTouchMode(true);
		APP5.setFocusableInTouchMode(true);
		APP6.setFocusableInTouchMode(true);
		APP7.setFocusableInTouchMode(true);
		APP8.setFocusableInTouchMode(true);
		APP9.setFocusableInTouchMode(true);
		APP10.setFocusableInTouchMode(true);
		APP1.setGravity(Gravity.CENTER_VERTICAL);
		APP2.setGravity(Gravity.CENTER_VERTICAL);
		APP3.setGravity(Gravity.CENTER_VERTICAL);
		APP4.setGravity(Gravity.CENTER_VERTICAL);
		APP5.setGravity(Gravity.CENTER_VERTICAL);
		APP6.setGravity(Gravity.CENTER_VERTICAL);
		APP7.setGravity(Gravity.CENTER_VERTICAL);
		APP8.setGravity(Gravity.CENTER_VERTICAL);
		APP9.setGravity(Gravity.CENTER_VERTICAL);
		APP10.setGravity(Gravity.CENTER_VERTICAL);
		APP1.setBackgroundResource(BACKGROUND);
		APP2.setBackgroundResource(BACKGROUND);
		APP3.setBackgroundResource(BACKGROUND);
		APP4.setBackgroundResource(BACKGROUND);
		APP5.setBackgroundResource(BACKGROUND);
		APP6.setBackgroundResource(BACKGROUND);
		APP7.setBackgroundResource(BACKGROUND);
		APP8.setBackgroundResource(BACKGROUND);
		APP9.setBackgroundResource(BACKGROUND);
		APP10.setBackgroundResource(BACKGROUND);
	}

	private void setTheTextSizes(TextView v, float size) {
		v.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
	}

	private void openSPE() {
		spe = sp.edit();
	}

	private void commitSPE() {
		spe.commit();
	}

	private void loadList() {
		// TODO Auto-generated method stub
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		APP1.setText(sp.getString(KEY_LIST_KEY[0], "Application 1"));
		APP2.setText(sp.getString(KEY_LIST_KEY[1], "Application 2"));
		APP3.setText(sp.getString(KEY_LIST_KEY[2], "Application 3"));
		APP4.setText(sp.getString(KEY_LIST_KEY[3], "Application 4"));
		APP5.setText(sp.getString(KEY_LIST_KEY[4], "Application 5"));
		APP6.setText(sp.getString(KEY_LIST_KEY[5], "Application 6"));
		APP7.setText(sp.getString(KEY_LIST_KEY[6], "Application 7"));
		APP8.setText(sp.getString(KEY_LIST_KEY[7], "Application 8"));
		APP9.setText(sp.getString(KEY_LIST_KEY[8], "Application 9"));
		APP10.setText(sp.getString(KEY_LIST_KEY[9], "Application 10"));
		KEY_APPINTENT[0] = sp.getString(PACKAGE_NAMES[0],
				"com.jeremypacabis.statusbarmods");
		KEY_APPINTENT[1] = sp.getString(PACKAGE_NAMES[1],
				"com.jeremypacabis.statusbarmods");
		KEY_APPINTENT[2] = sp.getString(PACKAGE_NAMES[2],
				"com.jeremypacabis.statusbarmods");
		KEY_APPINTENT[3] = sp.getString(PACKAGE_NAMES[3],
				"com.jeremypacabis.statusbarmods");
		KEY_APPINTENT[4] = sp.getString(PACKAGE_NAMES[4],
				"com.jeremypacabis.statusbarmods");
		KEY_APPINTENT[5] = sp.getString(PACKAGE_NAMES[5],
				"com.jeremypacabis.statusbarmods");
		KEY_APPINTENT[6] = sp.getString(PACKAGE_NAMES[6],
				"com.jeremypacabis.statusbarmods");
		KEY_APPINTENT[7] = sp.getString(PACKAGE_NAMES[7],
				"com.jeremypacabis.statusbarmods");
		KEY_APPINTENT[8] = sp.getString(PACKAGE_NAMES[8],
				"com.jeremypacabis.statusbarmods");
		KEY_APPINTENT[9] = sp.getString(PACKAGE_NAMES[9],
				"com.jeremypacabis.statusbarmods");
		APPICON1 = getAppIcon(KEY_APPINTENT[0]);
		APPICON2 = getAppIcon(KEY_APPINTENT[1]);
		APPICON3 = getAppIcon(KEY_APPINTENT[2]);
		APPICON4 = getAppIcon(KEY_APPINTENT[3]);
		APPICON5 = getAppIcon(KEY_APPINTENT[4]);
		APPICON6 = getAppIcon(KEY_APPINTENT[5]);
		APPICON7 = getAppIcon(KEY_APPINTENT[6]);
		APPICON8 = getAppIcon(KEY_APPINTENT[7]);
		APPICON9 = getAppIcon(KEY_APPINTENT[8]);
		APPICON10 = getAppIcon(KEY_APPINTENT[9]);
		loadViewVisibility();
		setTheBounds();
		setTheCompoundDrawables();
	}

	private void setTheBounds() {
		iconSize = getIconSize();
		APPICON1.setBounds(0, 0, iconSize, iconSize);
		APPICON2.setBounds(0, 0, iconSize, iconSize);
		APPICON3.setBounds(0, 0, iconSize, iconSize);
		APPICON4.setBounds(0, 0, iconSize, iconSize);
		APPICON5.setBounds(0, 0, iconSize, iconSize);
		APPICON6.setBounds(0, 0, iconSize, iconSize);
		APPICON7.setBounds(0, 0, iconSize, iconSize);
		APPICON8.setBounds(0, 0, iconSize, iconSize);
		APPICON9.setBounds(0, 0, iconSize, iconSize);
		APPICON10.setBounds(0, 0, iconSize, iconSize);
	}

	private void setTheCompoundDrawables() {
		APP1.setCompoundDrawables(APPICON1, null, null, null);
		APP2.setCompoundDrawables(APPICON2, null, null, null);
		APP3.setCompoundDrawables(APPICON3, null, null, null);
		APP4.setCompoundDrawables(APPICON4, null, null, null);
		APP5.setCompoundDrawables(APPICON5, null, null, null);
		APP6.setCompoundDrawables(APPICON6, null, null, null);
		APP7.setCompoundDrawables(APPICON7, null, null, null);
		APP8.setCompoundDrawables(APPICON8, null, null, null);
		APP9.setCompoundDrawables(APPICON9, null, null, null);
		APP10.setCompoundDrawables(APPICON10, null, null, null);

	}

	private void showToastWarning() {
		collapseStatusBar();
		Toast.makeText(
				mContext,
				"Application cannot be launched because of the following reasons:\n\n1. It is an Android System Application.\n2. It is a Widget.\n3. It has no launcher activity.\n4. It is frozen or disabled.\n\nPlease choose another application.",
				Toast.LENGTH_SHORT).show();
	}

	private void setAPP1OnClickListener() {
		APP1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (KEY_APPINTENT[0] != null) {
					mIntent = mContext.getPackageManager()
							.getLaunchIntentForPackage(KEY_APPINTENT[0]);
					if (mIntent != null) {
						launchApp(mIntent);
					} else {
						showToastWarning();
					}
				}
			}
		});
	}

	private void setAPP2OnClickListener() {
		APP2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (KEY_APPINTENT[1] != null) {
					mIntent = mContext.getPackageManager()
							.getLaunchIntentForPackage(KEY_APPINTENT[1]);
					if (mIntent != null) {
						launchApp(mIntent);
					} else {
						showToastWarning();
					}
				}
			}
		});
	}

	private void setAPP3OnClickListener() {
		APP3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (KEY_APPINTENT[2] != null) {
					mIntent = mContext.getPackageManager()
							.getLaunchIntentForPackage(KEY_APPINTENT[2]);
					if (mIntent != null) {
						launchApp(mIntent);
					} else {
						showToastWarning();
					}
				}
			}
		});
	}

	private void setAPP4OnClickListener() {
		APP4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (KEY_APPINTENT[3] != null) {
					mIntent = mContext.getPackageManager()
							.getLaunchIntentForPackage(KEY_APPINTENT[3]);
					if (mIntent != null) {
						launchApp(mIntent);
					} else {
						showToastWarning();
					}
				}
			}
		});
	}

	private void setAPP5OnClickListener() {
		APP5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (KEY_APPINTENT[4] != null) {
					mIntent = mContext.getPackageManager()
							.getLaunchIntentForPackage(KEY_APPINTENT[4]);
					if (mIntent != null) {
						launchApp(mIntent);
					} else {
						showToastWarning();
					}
				}
			}
		});
	}

	private void setAPP6OnClickListener() {
		APP6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (KEY_APPINTENT[5] != null) {
					mIntent = mContext.getPackageManager()
							.getLaunchIntentForPackage(KEY_APPINTENT[5]);
					if (mIntent != null) {
						launchApp(mIntent);
					} else {
						showToastWarning();
					}
				}
			}
		});
	}

	private void setAPP7OnClickListener() {
		APP7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (KEY_APPINTENT[6] != null) {
					mIntent = mContext.getPackageManager()
							.getLaunchIntentForPackage(KEY_APPINTENT[6]);
					if (mIntent != null) {
						launchApp(mIntent);
					} else {
						showToastWarning();
					}
				}
			}
		});
	}

	private void setAPP8OnClickListener() {
		APP8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (KEY_APPINTENT[7] != null) {
					mIntent = mContext.getPackageManager()
							.getLaunchIntentForPackage(KEY_APPINTENT[7]);
					if (mIntent != null) {
						launchApp(mIntent);
					} else {
						showToastWarning();
					}
				}
			}
		});
	}

	private void setAPP9OnClickListener() {
		APP9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (KEY_APPINTENT[8] != null) {
					mIntent = mContext.getPackageManager()
							.getLaunchIntentForPackage(KEY_APPINTENT[8]);
					if (mIntent != null) {
						launchApp(mIntent);
					} else {
						showToastWarning();
					}
				}
			}
		});
	}

	private void setAPP10OnClickListener() {
		APP10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (KEY_APPINTENT[9] != null) {
					mIntent = mContext.getPackageManager()
							.getLaunchIntentForPackage(KEY_APPINTENT[9]);
					if (mIntent != null) {
						launchApp(mIntent);
					} else {
						showToastWarning();
					}
				}
			}
		});
	}

	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		super.onDetachedFromWindow();
		mContext.unregisterReceiver(BRAPP1);
		mContext.unregisterReceiver(BRAPP2);
		mContext.unregisterReceiver(BRAPP3);
		mContext.unregisterReceiver(BRAPP4);
		mContext.unregisterReceiver(BRAPP5);
		mContext.unregisterReceiver(BRAPP6);
		mContext.unregisterReceiver(BRAPP7);
		mContext.unregisterReceiver(BRAPP8);
		mContext.unregisterReceiver(BRAPP9);
		mContext.unregisterReceiver(BRAPP10);
		mContext.unregisterReceiver(SETTINGS);
		mContext.unregisterReceiver(ICONSIZE);
		mContext.unregisterReceiver(TEXTSIZE);
	}

	protected void launchApp(Intent thisIntent) {
		// TODO Auto-generated method stub
		try {
			mContext.startActivity(thisIntent);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
			showToastWarning();
		}
		collapseStatusBar();
	}

	protected int getCurrentAPIVersion() {
		return android.os.Build.VERSION.SDK_INT;
	}

	protected void collapseStatusBar() {
		// TODO Auto-generated method stub
		try {
			Object service = mContext.getSystemService("statusbar");
			Class<?> statusBarManager = Class
					.forName("android.app.StatusBarManager");
			if (getCurrentAPIVersion() <= 16) {
				Method collapse = statusBarManager.getMethod("collapse");
				collapse.setAccessible(true);
				collapse.invoke(service);
			} else {
				Method collapse = statusBarManager.getMethod("collapsePanels");
				collapse.setAccessible(true);
				collapse.invoke(service);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadViewVisibility() {
		if (KEY_APPINTENT[0].contains("DELETE")) {
			setViewGone(APP1);
		} else {
			setViewVisible(APP1);
		}
		if (KEY_APPINTENT[1].contains("DELETE")) {
			setViewGone(APP2);
		} else {
			setViewVisible(APP2);
		}
		if (KEY_APPINTENT[2].contains("DELETE")) {
			setViewGone(APP3);
		} else {
			setViewVisible(APP3);
		}
		if (KEY_APPINTENT[3].contains("DELETE")) {
			setViewGone(APP4);
		} else {
			setViewVisible(APP4);
		}
		if (KEY_APPINTENT[4].contains("DELETE")) {
			setViewGone(APP5);
		} else {
			setViewVisible(APP5);
		}
		if (KEY_APPINTENT[5].contains("DELETE")) {
			setViewGone(APP6);
		} else {
			setViewVisible(APP6);
		}
		if (KEY_APPINTENT[6].contains("DELETE")) {
			setViewGone(APP7);
		} else {
			setViewVisible(APP7);
		}
		if (KEY_APPINTENT[7].contains("DELETE")) {
			setViewGone(APP8);
		} else {
			setViewVisible(APP8);
		}
		if (KEY_APPINTENT[8].contains("DELETE")) {
			setViewGone(APP9);
		} else {
			setViewVisible(APP9);
		}
		if (KEY_APPINTENT[9].contains("DELETE")) {
			setViewGone(APP10);
		} else {
			setViewVisible(APP10);
		}
	}

	private void setViewVisible(View v) {
		if (v.getVisibility() != View.VISIBLE) {
			v.setVisibility(View.VISIBLE);
		}
	}

	private void setViewGone(View v) {
		if (v.getVisibility() != View.GONE) {
			v.setVisibility(View.GONE);
		}
	}

	private void saveReceivedData(int position) {
		openSPE();
		spe.putString(KEY_LIST_KEY[position], KEY_APPNAME[position]);
		spe.putString(PACKAGE_NAMES[position], KEY_APPINTENT[position]);
		commitSPE();
	}

	private Drawable getAppIcon(String packageName) {
		Drawable icon = null;
		try {
			icon = mContext.getPackageManager().getApplicationIcon(packageName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			icon = mContext.getResources().getDrawable(
					android.R.drawable.ic_input_add);
			e.printStackTrace();
		} finally {
			if (icon != null) {
				return icon;
			} else {
				icon = mContext.getResources().getDrawable(
						android.R.drawable.ic_input_add);
			}
		}
		return null;
	}
}