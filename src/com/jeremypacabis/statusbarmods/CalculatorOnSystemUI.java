package com.jeremypacabis.statusbarmods;

import java.text.DecimalFormat;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class CalculatorOnSystemUI extends LinearLayout {

	LinearLayout.LayoutParams MAIN_PARAMS, BUTTON_ROW_PARAMS,
			DISPLAY_BKSPC_CE_ROW_PARAMS, BUTTON_PARAMS;
	LinearLayout DISPLAY_BKSPC_CE, NUM123DIV, NUM456MUL, NUM789SUB,
			NUM0DECEQPLUS, BASICFCN, ADVFCN, TRIGOFCN, ADVFCN_ROW1;
	EditText DISPLAY;
	Button BKSPC, CE, NUM1, NUM2, NUM3, NUM4, NUM5, NUM6, NUM7, NUM8, NUM9,
			NUM0, OPDIV, OPMUL, OPSUB, OPADD, OPDEC, OPEQ, ADVPNLTGL, SIN, COS,
			TAN, SQUARED, CUBE, SQRROOT, PI;
	ViewFlipper BASICADV;

	private static final float WEIGHT_SUM = 1.0f;
	private static final String PI_VALUE = "3.14159265";
	private static final String CE_LBL = "CE";
	private static final String BKSPC_LBL = "\u21D0";
	private static final String ADVTGL_LBL = "Advanced";
	private static final String NUMS_LBL[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9" };
	private static final String OPERATIONS[] = { "÷", "×", "-", "+", "=", ".",
			"sin", "cos", "tan", "X\u00B2", "X\u00B3", "\u221a¯¯", "\u03C0" };
	private String result = "0";
	private String SUM = "";
	private char PRESS = 'Z';
	private float RESULT = 0f;
	private float RESULT_MUL = 1f;
	private float RESULT_DIV = 1f;
	private float RESULT_DISPLAY = 0f;
	private int C = 0;
	private int VFDisplayedChild = 0;
	private static final DecimalFormat FORMAT = new DecimalFormat(
			"#0.0000000000");
	int OP_BUTTON_BG, NUM_BUTTON_BG, ADV_BUTTON_BG, DISPLAY_BG;
	Double INITIAL, AFTER_SIN, AFTER_COS, AFTER_TAN, INITIAL_TORADIAN,
			AFTER_SQUARED, AFTER_CUBE, AFTER_ROOT;

	public CalculatorOnSystemUI(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		NUM_BUTTON_BG = android.R.drawable.btn_default;
		OP_BUTTON_BG = android.R.drawable.btn_default_small;
		ADV_BUTTON_BG = android.R.drawable.gallery_thumb;
		DISPLAY_BG = android.R.drawable.edit_text;
		MAIN_PARAMS = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		BUTTON_ROW_PARAMS = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		DISPLAY_BKSPC_CE_ROW_PARAMS = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		BUTTON_PARAMS = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.25f);

		setLayoutParams(MAIN_PARAMS);
		setOrientation(LinearLayout.VERTICAL);

		BASICADV = new ViewFlipper(context);
		DISPLAY_BKSPC_CE = new LinearLayout(context);
		NUM123DIV = new LinearLayout(context);
		NUM456MUL = new LinearLayout(context);
		NUM789SUB = new LinearLayout(context);
		NUM0DECEQPLUS = new LinearLayout(context);
		BASICFCN = new LinearLayout(context);
		ADVFCN = new LinearLayout(context);
		TRIGOFCN = new LinearLayout(context);
		ADVFCN_ROW1 = new LinearLayout(context);

		BASICADV.setLayoutParams(MAIN_PARAMS);

		BASICFCN.setOrientation(LinearLayout.VERTICAL);
		ADVFCN.setOrientation(LinearLayout.VERTICAL);
		TRIGOFCN.setOrientation(LinearLayout.HORIZONTAL);
		ADVFCN_ROW1.setOrientation(LinearLayout.HORIZONTAL);
		BASICFCN.setLayoutParams(MAIN_PARAMS);
		ADVFCN.setLayoutParams(MAIN_PARAMS);
		TRIGOFCN.setLayoutParams(MAIN_PARAMS);
		ADVFCN_ROW1.setLayoutParams(MAIN_PARAMS);

		DISPLAY_BKSPC_CE.setWeightSum(WEIGHT_SUM);
		NUM123DIV.setWeightSum(WEIGHT_SUM);
		NUM456MUL.setWeightSum(WEIGHT_SUM);
		NUM789SUB.setWeightSum(WEIGHT_SUM);
		NUM0DECEQPLUS.setWeightSum(WEIGHT_SUM);
		TRIGOFCN.setWeightSum(0.90f);
		ADVFCN_ROW1.setWeightSum(WEIGHT_SUM);

		DISPLAY_BKSPC_CE.setLayoutParams(DISPLAY_BKSPC_CE_ROW_PARAMS);
		NUM123DIV.setLayoutParams(BUTTON_ROW_PARAMS);
		NUM456MUL.setLayoutParams(BUTTON_ROW_PARAMS);
		NUM789SUB.setLayoutParams(BUTTON_ROW_PARAMS);
		NUM0DECEQPLUS.setLayoutParams(BUTTON_ROW_PARAMS);

		// Add first row which consists of display, ce, bkspc
		DISPLAY = new EditText(context);
		DISPLAY.setEnabled(false);
		DISPLAY.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		DISPLAY.setGravity(Gravity.RIGHT);
		DISPLAY.setText(result);
		DISPLAY.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30.0f);
		DISPLAY.setSingleLine();
		DISPLAY.setBackgroundResource(DISPLAY_BG);

		addView(DISPLAY);

		CE = new Button(context);
		CE.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.400f));
		CE.setText(CE_LBL);
		CE.setBackgroundResource(OP_BUTTON_BG);
		CE.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		BKSPC = new Button(context);
		BKSPC.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.40f));
		BKSPC.setText(BKSPC_LBL);
		BKSPC.setBackgroundResource(OP_BUTTON_BG);
		BKSPC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		ADVPNLTGL = new Button(context);
		ADVPNLTGL.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.20f));
		ADVPNLTGL.setText(ADVTGL_LBL);
		ADVPNLTGL.setBackgroundResource(OP_BUTTON_BG);
		ADVPNLTGL.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		DISPLAY_BKSPC_CE.addView(CE);
		DISPLAY_BKSPC_CE.addView(BKSPC);
		DISPLAY_BKSPC_CE.addView(ADVPNLTGL);
		addView(DISPLAY_BKSPC_CE);

		// Add second row which consists of 1, 2, 3 and /
		NUM1 = new Button(context);
		NUM1.setLayoutParams(BUTTON_PARAMS);
		NUM1.setText(NUMS_LBL[1]);
		NUM1.setBackgroundResource(NUM_BUTTON_BG);
		NUM1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		NUM2 = new Button(context);
		NUM2.setLayoutParams(BUTTON_PARAMS);
		NUM2.setText(NUMS_LBL[2]);
		NUM2.setBackgroundResource(NUM_BUTTON_BG);
		NUM2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		NUM3 = new Button(context);
		NUM3.setLayoutParams(BUTTON_PARAMS);
		NUM3.setText(NUMS_LBL[3]);
		NUM3.setBackgroundResource(NUM_BUTTON_BG);
		NUM3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		OPDIV = new Button(context);
		OPDIV.setLayoutParams(BUTTON_PARAMS);
		OPDIV.setText(OPERATIONS[0]);
		OPDIV.setBackgroundResource(OP_BUTTON_BG);
		OPDIV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		NUM123DIV.addView(NUM1);
		NUM123DIV.addView(NUM2);
		NUM123DIV.addView(NUM3);
		NUM123DIV.addView(OPDIV);
		BASICFCN.addView(NUM123DIV);

		// Add third row which consists of 4, 5, 6 and x
		NUM4 = new Button(context);
		NUM4.setLayoutParams(BUTTON_PARAMS);
		NUM4.setText(NUMS_LBL[4]);
		NUM4.setBackgroundResource(NUM_BUTTON_BG);
		NUM4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		NUM5 = new Button(context);
		NUM5.setLayoutParams(BUTTON_PARAMS);
		NUM5.setText(NUMS_LBL[5]);
		NUM5.setBackgroundResource(NUM_BUTTON_BG);
		NUM5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		NUM6 = new Button(context);
		NUM6.setLayoutParams(BUTTON_PARAMS);
		NUM6.setText(NUMS_LBL[6]);
		NUM6.setBackgroundResource(NUM_BUTTON_BG);
		NUM6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		OPMUL = new Button(context);
		OPMUL.setLayoutParams(BUTTON_PARAMS);
		OPMUL.setText(OPERATIONS[1]);
		OPMUL.setBackgroundResource(OP_BUTTON_BG);
		OPMUL.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		NUM456MUL.addView(NUM4);
		NUM456MUL.addView(NUM5);
		NUM456MUL.addView(NUM6);
		NUM456MUL.addView(OPMUL);
		BASICFCN.addView(NUM456MUL);

		// Add fourth row which consists of 7, 8, 9 and -
		NUM7 = new Button(context);
		NUM7.setLayoutParams(BUTTON_PARAMS);
		NUM7.setText(NUMS_LBL[7]);
		NUM7.setBackgroundResource(NUM_BUTTON_BG);
		NUM7.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		NUM8 = new Button(context);
		NUM8.setLayoutParams(BUTTON_PARAMS);
		NUM8.setText(NUMS_LBL[8]);
		NUM8.setBackgroundResource(NUM_BUTTON_BG);
		NUM8.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		NUM9 = new Button(context);
		NUM9.setLayoutParams(BUTTON_PARAMS);
		NUM9.setText(NUMS_LBL[9]);
		NUM9.setBackgroundResource(NUM_BUTTON_BG);
		NUM9.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		OPSUB = new Button(context);
		OPSUB.setLayoutParams(BUTTON_PARAMS);
		OPSUB.setText(OPERATIONS[2]);
		OPSUB.setBackgroundResource(OP_BUTTON_BG);
		OPSUB.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		NUM789SUB.addView(NUM7);
		NUM789SUB.addView(NUM8);
		NUM789SUB.addView(NUM9);
		NUM789SUB.addView(OPSUB);
		BASICFCN.addView(NUM789SUB);

		// Add fifth row which consists of 0, ., = and +
		NUM0 = new Button(context);
		NUM0.setLayoutParams(BUTTON_PARAMS);
		NUM0.setText(NUMS_LBL[0]);
		NUM0.setBackgroundResource(NUM_BUTTON_BG);
		NUM0.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		OPDEC = new Button(context);
		OPDEC.setLayoutParams(BUTTON_PARAMS);
		OPDEC.setText(OPERATIONS[5]);
		OPDEC.setBackgroundResource(OP_BUTTON_BG);
		OPDEC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		OPEQ = new Button(context);
		OPEQ.setLayoutParams(BUTTON_PARAMS);
		OPEQ.setText(OPERATIONS[4]);
		OPEQ.setBackgroundResource(OP_BUTTON_BG);
		OPEQ.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		OPADD = new Button(context);
		OPADD.setLayoutParams(BUTTON_PARAMS);
		OPADD.setText(OPERATIONS[3]);
		OPADD.setBackgroundResource(OP_BUTTON_BG);
		OPADD.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		NUM0DECEQPLUS.addView(NUM0);
		NUM0DECEQPLUS.addView(OPDEC);
		NUM0DECEQPLUS.addView(OPEQ);
		NUM0DECEQPLUS.addView(OPADD);
		BASICFCN.addView(NUM0DECEQPLUS);

		BASICADV.addView(BASICFCN);

		SIN = new Button(context);
		SIN.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.30f));
		SIN.setText(OPERATIONS[6]);
		SIN.setBackgroundResource(ADV_BUTTON_BG);
		SIN.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		COS = new Button(context);
		COS.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.30f));
		COS.setText(OPERATIONS[7]);
		COS.setBackgroundResource(ADV_BUTTON_BG);
		COS.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		TAN = new Button(context);
		TAN.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.30f));
		TAN.setText(OPERATIONS[8]);
		TAN.setBackgroundResource(ADV_BUTTON_BG);
		TAN.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		TRIGOFCN.addView(SIN);
		TRIGOFCN.addView(COS);
		TRIGOFCN.addView(TAN);
		ADVFCN.addView(TRIGOFCN);

		SQUARED = new Button(context);
		SQUARED.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.25f));
		SQUARED.setText(OPERATIONS[9]);
		SQUARED.setBackgroundResource(ADV_BUTTON_BG);
		SQUARED.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		CUBE = new Button(context);
		CUBE.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.25f));
		CUBE.setText(OPERATIONS[10]);
		CUBE.setBackgroundResource(ADV_BUTTON_BG);
		CUBE.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		SQRROOT = new Button(context);
		SQRROOT.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.25f));
		SQRROOT.setText(OPERATIONS[11]);
		SQRROOT.setBackgroundResource(ADV_BUTTON_BG);
		SQRROOT.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		PI = new Button(context);
		PI.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.25f));
		PI.setText(OPERATIONS[12]);
		PI.setBackgroundResource(ADV_BUTTON_BG);
		PI.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f);

		ADVFCN_ROW1.addView(SQUARED);
		ADVFCN_ROW1.addView(CUBE);
		ADVFCN_ROW1.addView(SQRROOT);
		ADVFCN_ROW1.addView(PI);
		ADVFCN.addView(ADVFCN_ROW1);

		BASICADV.addView(ADVFCN);
		addView(BASICADV);
		BASICADV.setDisplayedChild(VFDisplayedChild);

		SIN.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				INITIAL = Double.parseDouble(getDISPLAYTEXT());
				INITIAL_TORADIAN = Math.toRadians(INITIAL);
				AFTER_SIN = Math.sin(INITIAL_TORADIAN);
				updateDisplay(FORMAT.format(AFTER_SIN));
				RESULT = Float.parseFloat(getDISPLAYTEXT());
				RESULT_MUL = Float.parseFloat(getDISPLAYTEXT());
				RESULT_DIV = Float.parseFloat(getDISPLAYTEXT());
				SUM = "";
			}
		});

		COS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				INITIAL = Double.parseDouble(getDISPLAYTEXT());
				INITIAL_TORADIAN = Math.toRadians(INITIAL);
				AFTER_COS = Math.cos(INITIAL_TORADIAN);
				updateDisplay(FORMAT.format(AFTER_COS));
				RESULT = Float.parseFloat(getDISPLAYTEXT());
				RESULT_MUL = Float.parseFloat(getDISPLAYTEXT());
				RESULT_DIV = Float.parseFloat(getDISPLAYTEXT());
				SUM = "";
			}
		});

		TAN.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				INITIAL = Double.parseDouble(getDISPLAYTEXT());
				INITIAL_TORADIAN = Math.toRadians(INITIAL);
				AFTER_TAN = Math.tan(INITIAL_TORADIAN);
				updateDisplay(FORMAT.format(AFTER_TAN));
				RESULT = Float.parseFloat(getDISPLAYTEXT());
				RESULT_MUL = Float.parseFloat(getDISPLAYTEXT());
				RESULT_DIV = Float.parseFloat(getDISPLAYTEXT());
				SUM = "";
			}
		});

		SQUARED.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				INITIAL = Double.parseDouble(getDISPLAYTEXT());
				AFTER_SQUARED = Math.pow(INITIAL, 2);
				updateDisplay(String.valueOf(AFTER_SQUARED));
				RESULT = Float.parseFloat(getDISPLAYTEXT());
				RESULT_MUL = Float.parseFloat(getDISPLAYTEXT());
				RESULT_DIV = Float.parseFloat(getDISPLAYTEXT());
				SUM = "";
			}
		});

		CUBE.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				INITIAL = Double.parseDouble(getDISPLAYTEXT());
				AFTER_CUBE = Math.pow(INITIAL, 3);
				updateDisplay(String.valueOf(AFTER_CUBE));
				RESULT = Float.parseFloat(getDISPLAYTEXT());
				RESULT_MUL = Float.parseFloat(getDISPLAYTEXT());
				RESULT_DIV = Float.parseFloat(getDISPLAYTEXT());
				SUM = "";
			}
		});

		SQRROOT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				INITIAL = Double.parseDouble(getDISPLAYTEXT());
				AFTER_ROOT = Math.sqrt(INITIAL);
				updateDisplay(String.valueOf(AFTER_ROOT));
				RESULT = Float.parseFloat(getDISPLAYTEXT());
				RESULT_MUL = Float.parseFloat(getDISPLAYTEXT());
				RESULT_DIV = Float.parseFloat(getDISPLAYTEXT());
				SUM = "";
			}
		});

		PI.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				SUM = PI_VALUE;
				updateDisplay(SUM);
			}
		});

		ADVPNLTGL.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (VFDisplayedChild == 0) {
					VFDisplayedChild = 1;
					BASICADV.setDisplayedChild(VFDisplayedChild);
					ADVPNLTGL.setText("Basic");
				} else {
					VFDisplayedChild = 0;
					BASICADV.setDisplayedChild(VFDisplayedChild);
					ADVPNLTGL.setText(ADVTGL_LBL);
				}
			}
		});

		BKSPC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (getDISPLAYTEXT().length() != 0) {
					StringBuilder sb = new StringBuilder();
					sb.append(getDISPLAYTEXT());
					SUM = sb.deleteCharAt(sb.length() - 1).toString();
					if (SUM == "") {
						updateDisplay("0");
					} else {
						updateDisplay(SUM);
					}
				} else {
					updateDisplay("0");
				}
			}
		});

		CE.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				resetAll();
			}
		});

		NUM1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				if (SUM != "" && SUM != NUMS_LBL[0]) {
					SUM = SUM + NUMS_LBL[1];
				} else {
					SUM = NUMS_LBL[1];
				}
				updateDisplay(SUM);
			}
		});

		NUM2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				if (SUM != "" && SUM != NUMS_LBL[0]) {
					SUM = SUM + NUMS_LBL[2];
				} else {
					SUM = NUMS_LBL[2];
				}
				updateDisplay(SUM);
			}
		});

		NUM3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				if (SUM != "" && SUM != NUMS_LBL[0]) {
					SUM = SUM + NUMS_LBL[3];
				} else {
					SUM = NUMS_LBL[3];
				}
				updateDisplay(SUM);
			}
		});

		NUM4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				if (SUM != "" && SUM != NUMS_LBL[0]) {
					SUM = SUM + NUMS_LBL[4];
				} else {
					SUM = NUMS_LBL[4];
				}
				updateDisplay(SUM);
			}
		});

		NUM5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				if (SUM != "" && SUM != NUMS_LBL[0]) {
					SUM = SUM + NUMS_LBL[5];
				} else {
					SUM = NUMS_LBL[5];
				}
				updateDisplay(SUM);
			}
		});

		NUM6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				if (SUM != "" && SUM != NUMS_LBL[0]) {
					SUM = SUM + NUMS_LBL[6];
				} else {
					SUM = NUMS_LBL[6];
				}
				updateDisplay(SUM);
			}
		});

		NUM7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				if (SUM != "" && SUM != NUMS_LBL[0]) {
					SUM = SUM + NUMS_LBL[7];
				} else {
					SUM = NUMS_LBL[7];
				}
				updateDisplay(SUM);
			}
		});

		NUM8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				if (SUM != "" && SUM != NUMS_LBL[0]) {
					SUM = SUM + NUMS_LBL[8];
				} else {
					SUM = NUMS_LBL[8];
				}
				updateDisplay(SUM);
			}
		});

		NUM9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				if (SUM != "" && SUM != NUMS_LBL[0]) {
					SUM = SUM + NUMS_LBL[9];
				} else {
					SUM = NUMS_LBL[9];
				}
				updateDisplay(SUM);
			}
		});

		NUM0.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (PRESS == '=') {
					resetAll();
				}
				if (SUM != "" && SUM != NUMS_LBL[0]) {
					SUM = SUM + NUMS_LBL[0];
				} else {
					SUM = NUMS_LBL[0];
				}
				updateDisplay(SUM);
			}
		});

		OPDIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				divide();
			}
		});

		OPMUL.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				multiply();
			}
		});

		OPSUB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				subtract();
			}
		});

		OPADD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				add();
			}
		});

		OPEQ.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				equalButton(v);
			}
		});

		OPDEC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int error = 0;
				if (SUM != null) {
					for (int i = 0; i < SUM.length(); i++) {
						if (SUM.charAt(i) == '.') {
							error = 1;
							break;
						}
					}
				}
				if (error == 0) {
					if (SUM == null) {
						SUM = SUM + "0.";
					} else {
						SUM = SUM + ".";
					}
				}
				updateDisplay(String.valueOf(SUM));
			}
		});
	}

	public void equalButton(View v) {
		if (PRESS == '+') {
			add();
		} else if (PRESS == '-') {
			subtract();
		} else if (PRESS == '*') {
			multiply();
		} else if (PRESS == '/') {
			divide();
		}
		PRESS = '=';
	}

	private void divide() {
		// TODO Auto-generated method stub
		if (PRESS == '-') {
			equalButton(OPSUB);
		} else if (PRESS == '*') {
			equalButton(OPMUL);
		} else if (PRESS == '+') {
			equalButton(OPADD);
		}
		PRESS = '/';
		RESULT_DISPLAY = Float.parseFloat(getDISPLAYTEXT());
		if (SUM != "" && RESULT_DIV == 1) {
			if (C == 0) {
				RESULT_DIV = RESULT_DISPLAY / RESULT_DIV;
				C++;
			} else {
				RESULT_DIV = RESULT_DIV / RESULT_DISPLAY;
			}
			RESULT = RESULT_DIV;
			RESULT_MUL = RESULT_DIV;
			updateDisplay(String.valueOf(RESULT_DIV));
			SUM = "";
		} else if (SUM != "" && RESULT_DIV != 1) {
			RESULT_DIV = RESULT_DIV / RESULT_DISPLAY;
			RESULT = RESULT_DIV;
			RESULT_MUL = RESULT_DIV;
			updateDisplay(String.valueOf(RESULT_DIV));
			SUM = "";
		} else {
			updateDisplay(String.valueOf(RESULT_DISPLAY));
			SUM = "";
		}
	}

	private void multiply() {
		// TODO Auto-generated method stub
		if (PRESS == '+') {
			equalButton(OPADD);
		} else if (PRESS == '-') {
			equalButton(OPSUB);
		} else if (PRESS == '/') {
			equalButton(OPDIV);
		}
		PRESS = '*';
		RESULT_DISPLAY = Float.parseFloat(getDISPLAYTEXT());
		if (SUM != "") {
			RESULT_MUL = RESULT_MUL * RESULT_DISPLAY;
			RESULT = RESULT_MUL;
			RESULT_DIV = RESULT_MUL;
			updateDisplay(String.valueOf(RESULT_MUL));
			SUM = "";
		} else {
			updateDisplay(String.valueOf(RESULT_DISPLAY));
			SUM = "";
		}
	}

	private void subtract() {
		// TODO Auto-generated method stub
		if (PRESS == '+') {
			equalButton(OPADD);
		} else if (PRESS == '*') {
			equalButton(OPMUL);
		} else if (PRESS == '/') {
			equalButton(OPDIV);
		}
		PRESS = '-';
		RESULT_DISPLAY = Float.parseFloat(getDISPLAYTEXT());
		if (SUM == "" && RESULT == 0) {
			SUM = SUM + '-';
		} else if (SUM != "") {
			if (RESULT == 0) {
				RESULT = Float.parseFloat(SUM) - RESULT;
				updateDisplay(String.valueOf(RESULT));
				RESULT_MUL = RESULT;
				RESULT_DIV = RESULT;
				SUM = "";
			} else {
				RESULT = RESULT - Float.parseFloat(SUM);
				updateDisplay(String.valueOf(RESULT));
				RESULT_MUL = RESULT;
				RESULT_DIV = RESULT;
				SUM = "";
			}
		}
	}

	private void add() {
		// TODO Auto-generated method stub
		if (PRESS == '-') {
			equalButton(OPSUB);
		} else if (PRESS == '*') {
			equalButton(OPMUL);
		} else if (PRESS == '/') {
			equalButton(OPDIV);
		}
		PRESS = '+';

		if (SUM != "") {
			RESULT = RESULT + Float.parseFloat(getDISPLAYTEXT());
			updateDisplay(String.valueOf(RESULT));
			RESULT_MUL = RESULT;
			RESULT_DIV = RESULT;
			SUM = "";
		} else {
			updateDisplay(String.valueOf(RESULT));
			RESULT_MUL = RESULT;
			RESULT_DIV = RESULT;
			SUM = "";
		}
	}

	protected void resetAll() {
		// TODO Auto-generated method stub
		SUM = "";
		PRESS = 'Z';
		RESULT = 0f;
		RESULT_DISPLAY = 0f;
		RESULT_MUL = 1f;
		RESULT_DIV = 1f;
		C = 0;
		result = String.valueOf(RESULT);
		updateDisplay(result);
	}

	protected void updateDisplay(String display) {
		// TODO Auto-generated method stub
		DISPLAY.setText(display);
	}

	private String getDISPLAYTEXT() {
		return DISPLAY.getText().toString();
	}
}
