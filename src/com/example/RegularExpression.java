package com.example;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.type.IntervalInfoObject;

public class RegularExpression {
	private String testPreviousRuler = "[(](\\d+)[-|~](\\d+)時?[)]"; // previousHoursInfo
	private String testFeeRuler = "(\\d+)[元]+[/]?[時|次]?"; // FeeInfo
	private String testFeeCycleRuler = "[未滿1小時以1小時|停車逾1小時以上]"; // feeCycleFeeInfo
	private ArrayList<IntervalInfoObject> intervalInfo;

	Pattern previousRuler;
	Pattern feeRuler;
	Pattern feeCycleRuler;

	int testYES;
	int testAll;

	public RegularExpression() {
		// Create a Pattern object
		previousRuler = Pattern.compile(testPreviousRuler);
		feeRuler = Pattern.compile(testFeeRuler);
		feeCycleRuler = Pattern.compile(testFeeCycleRuler);
		intervalInfo = new ArrayList<IntervalInfoObject>();

		testYES = 0;
		testAll = 0;
	}

	public ArrayList<IntervalInfoObject> getIntervalInfo(String eachJsonInfo) {
		intervalInfo.clear(); //清除上一個得資料時的內容
		previousRuler(eachJsonInfo);
		return intervalInfo;
	}

	public void previousRuler(String eachJsonInfo) { // 計算收費區間
		System.out.println(eachJsonInfo);
		Matcher m = previousRuler.matcher(eachJsonInfo);

		IntervalInfoObject info = new IntervalInfoObject();
		if (m.find()) {
			// m.group(1) 開始時間 m.group(2) 結束時間
			System.out.println("Found startPrevious: " + m.group(1));
			System.out.println("Found endPrevious: " + m.group(2));
			feeRuler(eachJsonInfo);

			System.out.println("Found fee: " + feeRuler(eachJsonInfo));
			++testYES;
		} else {
			info.setinterval("0~24"); // 如果非多時段 設定區間為0~24小時
			info.setweekdayFee(feeRuler(eachJsonInfo)); // 設定weekdayFee
			System.out.println("Found fee: " + feeRuler(eachJsonInfo));
		}
		info.setfeeCycle(feeCycleRuler(eachJsonInfo)); // 設定feeCycle
		System.out.println("cycle： "+feeCycleRuler(eachJsonInfo));

		intervalInfo.add(info);
		++testAll;

	}

	public String feeRuler(String eachJsonInfo) {
		Matcher m2 = feeRuler.matcher(eachJsonInfo);
		if (m2.find()) {
			return m2.group(1);
		}
		return null;
	}

	public String feeCycleRuler(String eachJsonInfo) {
		Matcher m3 = feeRuler.matcher(eachJsonInfo);
		String result = null;

		if (eachJsonInfo.contains("全程")) {
			// 台北市含"全程"資料皆以半小時為周期
			// 全程半小時
			// set "previousHoursFeeCycle": 30
			result = "30";
		} else if (m3.find()) {
			// 第一小時三十 後面以半小時計
			if (eachJsonInfo.contains("半小時")) {
				// set "previousHoursFeeCycle": 30
				result = "30";
				// set "previousHoursInfo": {
				// "startCharging": 60,
				// "previousHoursAmountFee": ,
				// "previousHoursFeeCycle": 60
				// },

				//
				// 待處理
				//
				// System.out.println(eachJsonInfo);
			} else {
				// a few information(Fee Cycle),therefore I want to process
				// handily.
			}
			// System.out.println(eachJsonInfo);
		} else {
			// only one information(Fee Cycle),therefore I want to process
			// handily.
			// System.out.println(eachJsonInfo);
			result = "60";
		}
		return result;
	}

}
