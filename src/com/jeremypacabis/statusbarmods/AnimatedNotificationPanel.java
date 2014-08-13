package com.jeremypacabis.statusbarmods;

import java.io.File;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;

public class AnimatedNotificationPanel extends View {

	private CountDownTimer timer;
	private View mView;
	private static final File SDCARD = Environment
			.getExternalStorageDirectory();
	private static final File DIRECTORY = new File(SDCARD.getAbsolutePath()
			+ File.separator + "AnimatedUI");
	private String item;
	private static final String thumbs = "thumbs.db";
	private static final File thumb = new File(DIRECTORY, thumbs);
	private File file = null;
	private Bitmap bmp;
	private int nOfFiles, fps, interval, counter;
	long millisInFuture;
	long countDownInterval;

	public AnimatedNotificationPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mView = findViewById(R.id.animatednotifbg);
		nOfFiles = 0;
		deleteThumbs();
		if (DIRECTORY.listFiles().length == 0) {
			setDefault();
		} else {
			setBackground();
		}
	}

	private void deleteThumbs() {
		// TODO Auto-generated method stub
		if (thumb.exists()) {
			thumb.delete();
		}
	}

	private void setBackground() {
		if (Environment.getExternalStorageState().contentEquals(
				Environment.MEDIA_MOUNTED)
				|| Environment.getExternalStorageState().contentEquals(
						Environment.MEDIA_MOUNTED_READ_ONLY)) {
			readDataFromSD();
		} else {
			setDefault();
		}
	}

	private void setDefault() {
		mView.setBackgroundColor(getResources().getColor(
				android.R.color.darker_gray));
	}

	private void readDataFromSD() {
		// TODO Auto-generated method stub
		fps = 60;
		interval = 1000 / fps;
		counter = 1;
		nOfFiles = DIRECTORY.listFiles().length;

		millisInFuture = (long) nOfFiles * interval;
		countDownInterval = (long) interval;

		timer = new CountDownTimer(millisInFuture, countDownInterval) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				if (millisUntilFinished % interval == 0) {
					item = String.valueOf(counter) + ".png";
					file = new File(DIRECTORY, item);
					if (file.exists()) {
						changeImage(file);
					} else {
						setDefault();
					}
					if (counter < nOfFiles) {
						counter++;
					} else {
						counter = 1;
					}
				}
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				timer.start();
			}
		}.start();
	}

	@SuppressWarnings("deprecation")
	private void changeImage(File nextImage) {
		if (nextImage.exists()) {
			bmp = BitmapFactory.decodeFile(nextImage.getAbsolutePath());
			if (bmp != null) {
				mView.setBackgroundDrawable(new BitmapDrawable(getResources(),
						bmp));
			}
		}
	}
}