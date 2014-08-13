//package com.jeremypacabis.statusbarmods;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//@SuppressWarnings("deprecation")
//public class SlidingDrawer extends LinearLayout {
//
//	private static final int SLIDING_DRAWER_ID = R.id.SlidingDrawer;
//	private static final int SLIDING_DRAWER_HANDLE_ID = R.id.SlidingDrawerHandle;
//	private static final int SLIDING_DRAWER_CONTENT_ID = R.id.SlidingDrawerContent;
//	private static final int SD_OPEN_DRAWABLE_ID = R.drawable.drawer_open;
//	private static final int SD_CLOSE_DRAWABLE_ID = R.drawable.drawer_close;
//	private Context mContext;
//	private Button mHandle;
//	private android.widget.SlidingDrawer mSlidingDrawer;
//
//	public SlidingDrawer(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		// TODO Auto-generated constructor stub
//		mContext = context;
//		setOrientation(LinearLayout.VERTICAL);
//		mSlidingDrawer = (android.widget.SlidingDrawer) findViewById(SLIDING_DRAWER_ID);
//		// mHandle = (Button)
//		// mHandle.setBackgroundResource(SD_CLOSE_DRAWABLE_ID);
//		// mHandle.setOnClickListener(new OnClickListener() {
//		//
//		// @Override
//		// public void onClick(View v) {
//		// // TODO Auto-generated method stub
//		// if (mSlidingDrawer.isOpened()) {
//		// mSlidingDrawer.close();
//		// mHandle.setBackgroundResource(SD_CLOSE_DRAWABLE_ID);
//		// } else {
//		// mSlidingDrawer.open();
//		// mHandle.setBackgroundResource(SD_OPEN_DRAWABLE_ID);
//		// }
//		// }
//		// });
//	}
//}
