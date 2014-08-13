/*
 * Change the layout when testing here
 * 
 * 
 * */
package com.jeremypacabis.statusbarmods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class UniversalTester extends Activity {

	private static final int groupID = 1;
	private int aboutID = Menu.FIRST;
	private int exitID = Menu.FIRST + 1;
	private AlertDialog.Builder ADB;
	private TextView mAbout;
	private ScrollView mSV;
	private LinearLayout mLL;

	Spanned mSpan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.applaunchertest);
		initialize();
	}

	private void initialize() {
		mSpan = Html.fromHtml(getString(R.string.html_aboutapp));
		mAbout = new TextView(this);
		mAbout.setMovementMethod(LinkMovementMethod.getInstance());
		mAbout.setClickable(true);
		mAbout.setText(mSpan);
		mAbout.setBackgroundColor(Color.BLACK);
		mAbout.setTextColor(Color.WHITE);
		mAbout.setPadding(10, 10, 10, 10);
		mLL = new LinearLayout(this);
		mLL.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		mLL.setOrientation(LinearLayout.VERTICAL);
		mSV = new ScrollView(this);
		mSV.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		mSV.setPadding(10, 10, 10, 10);
		mSV.setOverScrollMode(ScrollView.OVER_SCROLL_IF_CONTENT_SCROLLS);
		mSV.setBackgroundColor(Color.DKGRAY);
		mLL.addView(mAbout);
		mSV.addView(mLL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(groupID, aboutID, aboutID, "About").setIcon(
				android.R.drawable.ic_menu_info_details);
		menu.add(groupID, exitID, exitID, "Exit").setIcon(
				android.R.drawable.ic_menu_revert);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			ADB = new AlertDialog.Builder(this);
			if (mAbout.getParent() == null) {
				ADB.setView(mSV);
			} else {
				mAbout = null;
				mLL = null;
				mSV = null;
				initialize();
				ADB.setView(mSV);
			}
			ADB.setTitle("About");
			ADB.setIcon(android.R.drawable.ic_dialog_info);
			ADB.setNeutralButton("Okay", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			});
			ADB.create();
			ADB.show();
			break;
		case 2:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
