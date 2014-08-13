package com.jeremypacabis.statusbarmods;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AppLauncherOnSystemUISettings extends LinearLayout {

	private static final int CAPACITY = 10;
	private static final String KEY_LIST[] = { "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "10" };
	private static final String TEXT_SIZE_INTENT_ACTION = "com.jeremypacabis.statusbarmods.TEXT_SIZE";
	private static final String TEXT_SIZE_INTENT_EXTRA_KEY = "com.jeremypacabis.statusbarmods.TEXT_SIZE_INTENT_EXTRA";
	private static final String ICON_SIZE = "com.jeremypacabis.statusbarmods.ICON_SIZE";
	private static final String ICON_SIZE_KEY = "com.jeremypacabis.statusbarmods.ICON_SIZE_SETTINGS";
	private static final String KEY_SETTINGS = "com.jeremypacabis.statusbarmods.SETTINGS";
	private static final String VIEW_MODE_KEY = "com.jeremypacabis.statusbarmods.VIEW_MODE_KEY";
	private static final String HORIZONTAL_KEY = "com.jeremypacabis.statusbarmods.HORIZONTAL";
	private static final String HENABLED_KEY = "com.jeremypacabis.statusbarmods.HENABLED";
	// private static final String KEY_TEXTSIZE_KEY[] = { "STSAPP1", "STSAPP2",
	// "STSAPP3", "STSAPP4", "STSAPP5", "STSAPP6", "STSAPP7", "STSAPP8",
	// "STSAPP9", "STSAPP10" };
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
	private static final String KEY_APPNAME[] = { "ZAPP1", "ZAPP2", "ZAPP3",
			"ZAPP4", "ZAPP5", "ZAPP6", "ZAPP7", "ZAPP8", "ZAPP9", "ZAPP10" };
	private static final String KEY_APPINTENT[] = { "XAPP1", "XAPP2", "XAPP3",
			"XAPP4", "XAPP5", "XAPP6", "XAPP7", "XAPP8", "XAPP9", "XAPP10" };
	private static final String KEY_INTENT_ACTION[] = { "IAPP1", "IAPP2",
			"IAPP3", "IAPP4", "IAPP5", "IAPP6", "IAPP7", "IAPP8", "IAPP9",
			"IAPP10" };
	private ArrayList<String> INSTALLED_PACKAGE = new ArrayList<String>();
	private ArrayList<String> APP_NAME = new ArrayList<String>();
	private ArrayList<Intent> LAUNCH_ACTIVITY = new ArrayList<Intent>();
	private ArrayList<Drawable> APP_ICONS = new ArrayList<Drawable>();
	private ArrayList<String> APPS_SHORTCUT = new ArrayList<String>(CAPACITY);
	private ArrayAdapter<String> APPS_SHORTCUT_ADAPTER;
	String OPTIONS[] = { "Remove shortcut" };
	String MODES[] = { "Icon + Label", "Icon only", "Label only" };
	String APP_NAMES[] = new String[CAPACITY];
	String OLD_LIST_NAME, NEW_LIST_NAME, INTENT;
	ArrayAdapter<String> mSpinnerAdapter;
	Intent mIntent;
	int mSpinnerPos, iconSize, defaultIconSize;
	float textSize, defaultTextSize;
	boolean horizontal, henabled;

	SharedPreferences sp;
	SharedPreferences.Editor spe;
	private ListView APPSLISTVIEW;
	private Spinner VIEW_MODES;
	private TextView ViewSettingsLabel, AppsListLabel, IconSize, TextSize,
			CurrentIconSize, CurrentTextSize;
	private CheckBox HorizontalScroll;
	private Context mContext;
	private SeekBar mIconSize, mTextSize;
	private Button mSettings, mDoneSettings;
	private LinearLayout ICONSIZELL, TEXTSIZELL, THESETTINGS;
	private ProgressDialog dialog;
	private ScrollView mSettingsView;
	private Dialog mSettingsDialog;
	private WindowManager.LayoutParams WMLP;

	public AppLauncherOnSystemUISettings(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		dialog = new ProgressDialog(mContext);
		dialog.setTitle("Please wait...");
		dialog.setMessage("Loading installed applications...");
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		defaultIconSize = (int) ((int) mContext.getResources()
				.getDisplayMetrics().densityDpi / 3.3333333333333);
		defaultTextSize = (float) ((float) mContext.getResources()
				.getDisplayMetrics().densityDpi / 3.3333333333333);
		iconSize = getIconSize();
		textSize = getTextSize();
		mSpinnerPos = getSpinnerPos();
		horizontal = getHorizontal();
		henabled = getCBState();
		setOrientation(LinearLayout.VERTICAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		LinearLayout.LayoutParams TVLP = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		TVLP.gravity = Gravity.CENTER_VERTICAL;

		mSettings = new Button(mContext);
		mSettings.setText("Settings");
		mSettings.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		mDoneSettings = new Button(mContext);
		mDoneSettings.setText("Done!");
		mDoneSettings.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		THESETTINGS = new LinearLayout(mContext);
		THESETTINGS.setOrientation(LinearLayout.VERTICAL);
		THESETTINGS.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		mSettingsView = new ScrollView(mContext);
		mSettingsView.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		ViewSettingsLabel = new TextView(mContext);
		AppsListLabel = new TextView(mContext);
		ViewSettingsLabel.setLayoutParams(TVLP);
		AppsListLabel.setLayoutParams(TVLP);
		ViewSettingsLabel.setText("Display Options");
		AppsListLabel.setText("App Shortcuts List: ");
		ViewSettingsLabel.setBackgroundResource(android.R.drawable.title_bar);
		AppsListLabel.setBackgroundResource(android.R.drawable.title_bar);
		ViewSettingsLabel.setTextColor(Color.parseColor("#ffffff00"));
		AppsListLabel.setTextColor(Color.parseColor("#ffffff00"));
		ViewSettingsLabel.setGravity(Gravity.CENTER);
		HorizontalScroll = new CheckBox(mContext);
		HorizontalScroll.setLayoutParams(TVLP);
		HorizontalScroll.setText("Horizontal Scroll (Icon only)");
		HorizontalScroll.setTextColor(Color.YELLOW);
		HorizontalScroll.setEnabled(horizontal);
		HorizontalScroll.setChecked(henabled);
		IconSize = new TextView(mContext);
		IconSize.setLayoutParams(TVLP);
		IconSize.setText("Icon Size");
		IconSize.setBackgroundResource(android.R.drawable.title_bar);
		IconSize.setTextColor(Color.parseColor("#ffffff00"));
		IconSize.setGravity(Gravity.CENTER);

		TextSize = new TextView(mContext);
		TextSize.setLayoutParams(TVLP);
		TextSize.setText("Text Size");
		TextSize.setBackgroundResource(android.R.drawable.title_bar);
		TextSize.setTextColor(Color.parseColor("#ffffff00"));
		TextSize.setGravity(Gravity.CENTER);

		CurrentIconSize = new TextView(mContext);
		CurrentIconSize.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.05f));
		CurrentIconSize.setText(String.valueOf(iconSize) + " px");
		CurrentIconSize.setBackgroundResource(android.R.drawable.toast_frame);
		CurrentIconSize.setTextColor(Color.parseColor("#ffffff00"));
		CurrentIconSize.setGravity(Gravity.CENTER);

		CurrentTextSize = new TextView(mContext);
		CurrentTextSize.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.05f));
		CurrentTextSize.setText(String.valueOf(textSize) + " px");
		CurrentTextSize.setBackgroundResource(android.R.drawable.toast_frame);
		CurrentTextSize.setTextColor(Color.parseColor("#ffffff00"));
		CurrentTextSize.setGravity(Gravity.CENTER);

		mIconSize = new SeekBar(mContext);
		mIconSize.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.95f));
		mIconSize.setMax(defaultIconSize * 2);
		mIconSize.setProgress(iconSize);

		mTextSize = new SeekBar(mContext);
		mTextSize.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.95f));
		mTextSize.setMax((int) defaultTextSize);
		mTextSize.setProgress((int) textSize);

		ICONSIZELL = new LinearLayout(mContext);
		ICONSIZELL.setLayoutParams(TVLP);
		ICONSIZELL.setOrientation(LinearLayout.HORIZONTAL);
		ICONSIZELL.setWeightSum(1.0f);
		ICONSIZELL.addView(mIconSize);
		ICONSIZELL.addView(CurrentIconSize);

		TEXTSIZELL = new LinearLayout(mContext);
		TEXTSIZELL.setLayoutParams(TVLP);
		TEXTSIZELL.setOrientation(LinearLayout.HORIZONTAL);
		TEXTSIZELL.setWeightSum(1.0f);
		TEXTSIZELL.addView(mTextSize);
		TEXTSIZELL.addView(CurrentTextSize);

		APPSLISTVIEW = new ListView(mContext);
		APPSLISTVIEW.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		VIEW_MODES = new Spinner(mContext);
		VIEW_MODES.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		mSpinnerAdapter = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_spinner_item, MODES);
		mSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		VIEW_MODES.setAdapter(mSpinnerAdapter);
		VIEW_MODES.setSelection(mSpinnerPos);
		addView(mSettings);
		THESETTINGS.addView(ViewSettingsLabel);
		THESETTINGS.addView(VIEW_MODES);
		THESETTINGS.addView(HorizontalScroll);
		THESETTINGS.addView(IconSize);
		THESETTINGS.addView(ICONSIZELL);
		THESETTINGS.addView(TextSize);
		THESETTINGS.addView(TEXTSIZELL);
		THESETTINGS.addView(mDoneSettings);
		mSettingsView.addView(THESETTINGS);
		addView(AppsListLabel);
		addView(APPSLISTVIEW);

		mSettingsDialog = new Dialog(mContext);
		mSettingsDialog.setTitle("Settings");
		mSettingsDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		mSettingsDialog.setContentView(mSettingsView);
		mSettingsDialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				android.R.drawable.ic_menu_manage);

		WMLP = new WindowManager.LayoutParams();
		WMLP.copyFrom(mSettingsDialog.getWindow().getAttributes());
		WMLP.width = WindowManager.LayoutParams.MATCH_PARENT;
		WMLP.height = WindowManager.LayoutParams.WRAP_CONTENT;

		for (int i = 1; i <= CAPACITY; i++) {
			APPS_SHORTCUT.add("Application " + String.valueOf(i));
		}
		APPS_SHORTCUT_ADAPTER = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, APPS_SHORTCUT);

		dialog.show();
		preloadInstalledApplications Preload = new preloadInstalledApplications();
		Preload.execute(02, 23, 1997);

		setHorizontalScrollListener();
		setmIconSizeListener();
		setmTextSizeListener();
		setViewModeOnItemSelectedListener();
		setAppListViewOnClickListener();
		setApplistViewOnLongClickListener();
		setmCurrentIconSizeOnClickListener();
		setmCurrentTextSizeOnClickListener();
		setmSettingsOnClickListener();
		setmDoneSettingsOnClickListener();
		loadList();
		refreshList();
	}

	private void setmDoneSettingsOnClickListener() {
		// TODO Auto-generated method stub
		mDoneSettings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mSettingsDialog.isShowing()) {
					mSettingsDialog.dismiss();
				}
			}
		});
	}

	private void setmSettingsOnClickListener() {
		// TODO Auto-generated method stub
		mSettings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mSettingsDialog.show();
				mSettingsDialog.getWindow().setAttributes(WMLP);
			}
		});
	}

	private void setmCurrentIconSizeOnClickListener() {
		// TODO Auto-generated method stub
		CurrentIconSize.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iconSize = defaultIconSize;
				mIconSize.setProgress(iconSize);
				CurrentIconSize.setText(String.valueOf(iconSize) + " px");
				saveIconSize();
				sendIconSizeSettings();
			}
		});
	}

	private void setmCurrentTextSizeOnClickListener() {
		// TODO Auto-generated method stub
		CurrentTextSize.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textSize = defaultTextSize / 2;
				mTextSize.setProgress((int) textSize);
				CurrentTextSize.setText(String.valueOf((int) textSize) + " px");
				saveTextSize(textSize);
				sendTextSizeSettings();
			}
		});
	}

	private void sendIconSizeSettings() {
		mIntent = new Intent();
		mIntent.setAction(ICON_SIZE_KEY);
		mIntent.putExtra(ICON_SIZE, iconSize);
		mContext.sendBroadcast(mIntent);
	}

	private void sendTextSizeSettings() {
		mIntent = new Intent();
		mIntent.setAction(TEXT_SIZE_INTENT_ACTION);
		mIntent.putExtra(TEXT_SIZE_INTENT_EXTRA_KEY, textSize);
		mContext.sendBroadcast(mIntent);
	}

	private void setmTextSizeListener() {
		mTextSize.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				saveTextSize(textSize);
				sendTextSizeSettings();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textSize = (float) progress;
				mTextSize.setProgress(progress);
				CurrentTextSize.setText(String.valueOf((int) textSize) + " px");
			}
		});
	}

	private void setmIconSizeListener() {
		// TODO Auto-generated method stub
		mIconSize.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				saveIconSize();
				sendIconSizeSettings();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				iconSize = progress;
				mIconSize.setProgress(progress);
				CurrentIconSize.setText(String.valueOf(iconSize) + " px");
			}
		});
	}

	private boolean getCBState() {
		sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getBoolean(HENABLED_KEY, false);
	}

	private void saveCBState(boolean state) {
		openSPE();
		spe.putBoolean(HENABLED_KEY, state);
		commitSPE();
	}

	private void setHorizontalScrollListener() {
		// TODO Auto-generated method stub
		HorizontalScroll
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (HorizontalScroll.isChecked()) {
							sendViewModeSettings(3);
							saveCBState(HorizontalScroll.isChecked());
						} else {
							sendViewModeSettings(1);
							saveCBState(HorizontalScroll.isChecked());
						}
					}
				});
	}

	private boolean getHorizontal() {
		sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getBoolean(HORIZONTAL_KEY, false);
	}

	private void setViewModeOnItemSelectedListener() {
		// TODO Auto-generated method stub
		VIEW_MODES.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				mSpinnerPos = arg2;
				int MODE = mSpinnerPos;
				VIEW_MODES.setSelection(mSpinnerPos);
				saveSpinnerPos(mSpinnerPos);
				if (mSpinnerPos == 1) {
					horizontal = true;
					henabled = HorizontalScroll.isChecked();
					if (henabled) {
						MODE = 3;
					} else {
						MODE = mSpinnerPos;
					}
				} else {
					horizontal = false;
					henabled = false;
				}
				sendViewModeSettings(MODE);
				HorizontalScroll.setEnabled(horizontal);
				openSPE();
				spe.putBoolean(HORIZONTAL_KEY, horizontal);
				commitSPE();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				VIEW_MODES.setSelection(mSpinnerPos);
			}
		});
	}

	private void sendViewModeSettings(int mode) {
		mIntent = new Intent();
		mIntent.setAction(KEY_SETTINGS);
		mIntent.putExtra(VIEW_MODE_KEY, mode);
		mContext.sendBroadcast(mIntent);
	}

	private void saveSpinnerPos(int pos) {
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		spe = sp.edit();
		spe.putInt(VIEW_MODE_KEY, pos);
		spe.commit();
	}

	private int getSpinnerPos() {
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		return sp.getInt(VIEW_MODE_KEY, 0);
	}

	private void setApplistViewOnLongClickListener() {
		// TODO Auto-generated method stub
		APPSLISTVIEW.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				// TODO Auto-generated method stub
				final ListAdapter OPTIONSMENU = new ArrayAdapter<String>(
						mContext, android.R.layout.simple_list_item_1, OPTIONS);
				new AlertDialog.Builder(mContext)
						.setTitle("Options")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setAdapter(OPTIONSMENU,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										switch (which) {
										case 0:
											String NEW_ITEM = "Application "
													+ String.valueOf(arg2 + 1)
													+ " (Not on SystemUI)";
											APPS_SHORTCUT.remove(arg2);
											APPS_SHORTCUT.add(arg2, NEW_ITEM);
											sendDeleteCommand(arg2);
											APP_NAMES[arg2] = APPS_SHORTCUT
													.get(arg2);
											saveList(arg2);
											refreshList();
											break;
										}
									}
								}).show();
				return false;
			}
		});
	}

	private void setAppListViewOnClickListener() {
		// TODO Auto-generated method stub
		APPSLISTVIEW.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				// TODO Auto-generated method stub

				final ListAdapter ADAPTER = new ArrayAdapter<String>(mContext,
						android.R.layout.simple_list_item_1, APP_NAME);
				new AlertDialog.Builder(mContext)
						.setTitle("Select Application")
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setAdapter(ADAPTER,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										Toast.makeText(
												getContext(),
												"Application "
														+ String.valueOf(arg2 + 1)
														+ " selected: "
														+ ADAPTER
																.getItem(which)
																.toString(),
												Toast.LENGTH_SHORT).show();
										OLD_LIST_NAME = APPS_SHORTCUT.get(arg2);
										NEW_LIST_NAME = ADAPTER.getItem(which)
												.toString();
										if (OLD_LIST_NAME != NEW_LIST_NAME) {
											APPS_SHORTCUT.remove(arg2);
											String NEW_ITEM = ADAPTER.getItem(
													which).toString();
											APPS_SHORTCUT.add(arg2, NEW_ITEM);
											if (LAUNCH_ACTIVITY.get(which) != null) {
												INTENT = INSTALLED_PACKAGE
														.get(which);
											} else {
												INTENT = "null";
											}
											refreshToBePassed(arg2, NEW_ITEM,
													INTENT);
										}
										saveList(arg2);
										refreshList();
									}
								}).show();
			}
		});
	}

	private void refreshList() {
		APPS_SHORTCUT_ADAPTER = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1, APPS_SHORTCUT);
		APPSLISTVIEW.setAdapter(APPS_SHORTCUT_ADAPTER);
	}

	private void refreshToBePassed(int position, String APP, String mINTENT) {
		APP_NAMES[position] = APP;
		KEY_APPNAME[position] = APPS_SHORTCUT_ADAPTER.getItem(position);
		KEY_APPINTENT[position] = mINTENT;
		mIntent = new Intent();
		mIntent.setAction(KEY_INTENT_ACTION[position]);
		mIntent.putExtra(KEY_APPNAME_KEY[position], KEY_APPNAME[position]);
		mIntent.putExtra(KEY_INTENTNAME_KEY[position], KEY_APPINTENT[position]);
		mContext.sendBroadcast(mIntent);
	}

	private void saveList(int position) {
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		spe = sp.edit();
		spe.putString(KEY_LIST[position], APP_NAMES[position]);
		spe.commit();
	}

	private void loadList() {
		// TODO Auto-generated method stub
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		APPS_SHORTCUT.clear();
		for (int x = 0; x < CAPACITY; x++) {
			APP_NAMES[x] = sp.getString(KEY_LIST[x],
					"Application " + String.valueOf((x + 1)));
			APPS_SHORTCUT.add(APP_NAMES[x]);
		}
	}

	private void sendDeleteCommand(int position) {
		KEY_APPNAME[position] = "DELETE";
		KEY_APPINTENT[position] = "DELETE";
		mIntent = new Intent();
		mIntent.setAction(KEY_INTENT_ACTION[position]);
		mIntent.putExtra(KEY_APPNAME_KEY[position], KEY_APPNAME[position]);
		mIntent.putExtra(KEY_INTENTNAME_KEY[position], KEY_APPINTENT[position]);
		mContext.sendBroadcast(mIntent);
	}

	private float getTextSize() {
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		return sp.getFloat(TEXT_SIZE_INTENT_EXTRA_KEY, defaultTextSize);
	}

	private void saveTextSize(float textSize) {
		openSPE();
		spe.putFloat(TEXT_SIZE_INTENT_EXTRA_KEY, textSize);
		commitSPE();
	}

	private int getIconSize() {
		sp = PreferenceManager.getDefaultSharedPreferences(getContext());
		return sp.getInt(ICON_SIZE_KEY, defaultIconSize);
	}

	private void saveIconSize() {
		openSPE();
		spe.putInt(ICON_SIZE_KEY, iconSize);
		commitSPE();
	}

	private void openSPE() {
		sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		spe = sp.edit();
	}

	private void commitSPE() {
		spe.commit();
	}

	private class preloadInstalledApplications extends
			AsyncTask<Integer, Void, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			final PackageManager PM = mContext.getPackageManager();
			List<ApplicationInfo> PACKAGES = PM
					.getInstalledApplications(PackageManager.GET_META_DATA);
			for (ApplicationInfo PACKAGE_INFO : PACKAGES) {
				INSTALLED_PACKAGE.add(PACKAGE_INFO.packageName);
				LAUNCH_ACTIVITY.add(PM
						.getLaunchIntentForPackage(PACKAGE_INFO.packageName));
				APP_ICONS.add(PM.getApplicationIcon(PACKAGE_INFO));
				APP_NAME.add(PM.getApplicationLabel(PACKAGE_INFO).toString());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
		}
	}
}
