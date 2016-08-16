package com.example;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.type.*;

public class RegularExpression {
	private String testPreviousRuler = "[(](\\d+)[-|~](\\d+)時?[)]"; // previousHoursInfo
	private String testFeeRuler = "(\\d+)[元]+[/]?[時|次]?"; // FeeInfo
	private String testFeeCycleRuler = "[未滿1小時以1小時|停車逾1小時以上]"; // feeCycleFeeInfo
	//  關鍵字 逾1小時以上者 共770筆數
	
	private ArrayList<IntervalInfoObject> intervalInfo;
	private PreviousHoursInfoObject previousHoursInfo;

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

		testYES = 0;
		testAll = 0;
	}

	public ArrayList<IntervalInfoObject> getIntervalInfo(String payex) {
		intervalInfo = new ArrayList<IntervalInfoObject>(); // 清除上一個得資料時的內容
		previousRuler(payex);
		return intervalInfo;
	}
	
	public PreviousHoursInfoObject getPreviousHoursInfo(String payex){
		previousHoursInfo = new PreviousHoursInfoObject();
		feeCycleRuler(payex);
		return previousHoursInfo;
	}

	public void previousRuler(String payex) { // 計算收費區間
		Matcher m = previousRuler.matcher(payex);

		IntervalInfoObject info = new IntervalInfoObject();
		if (m.find()) {
			// m.group(1) 開始時間 m.group(2) 結束時間
			System.out.println("Found startPrevious: " + m.group(1));
			System.out.println("Found endPrevious: " + m.group(2));
			//還需要再調整
			info.setTimeChargeParkingFee(feeRuler(payex));
			System.out.println("Found fee: " + feeRuler(payex));
			++testYES;
		} else {
			info.setinterval("0~24"); // 如果非多時段 設定區間為0~24小時
			info.setTimeChargeParkingFee(feeRuler(payex)); // 設定TimeChargeParkingFee
			System.out.println("Found fee: " + feeRuler(payex));
		}
		info.setfeeCycle(feeCycleRuler(payex)); // 設定feeCycle
		System.out.println("cycle： " + feeCycleRuler(payex));

		intervalInfo.add(info);
		++testAll;

	}

	public String feeRuler(String payex) {
		Matcher m2 = feeRuler.matcher(payex);
		if (m2.find()) {
			return m2.group(1);
		}
		return null;
	}

	public String feeCycleRuler(String payex) {
		Matcher m3 = feeRuler.matcher(payex);
		String result = null;

		if (payex.contains("全程")) {
			// 台北市含"全程"資料皆以半小時為周期
			// 全程半小時
			// set "previousHoursFeeCycle": 30
			result = "30";
		} else if (payex.contains("逾1小時以上者")) {
			
			String[] payexSub = payex.split("逾1小時以上者",2);
			//example: 停車未滿1小時以1小時計，逾1小時以上者，未滿半小時以半小時計
			//payexSub[0]:停車未滿1小時以1小時計，
			//payexSub[1]:，未滿半小時以半小時計
			
			// 第一小時以小時計算
			if (payexSub[0].contains("未滿1小時以1小時計")) {
				// set "previousHoursInfo": {
				// "startCharging": 60,
				// "previousHoursAmountFee": ,
				// "previousHoursFeeCycle": 60
				// },
				previousHoursInfo.setStartCharging("60");
				previousHoursInfo.setPreviousHoursAmountFee(feeRuler(payex));
				previousHoursInfo.setPreviousHoursFeeCycle("60");

				// System.out.println(payex);
			} else {
				// 第一小時以半小時計算
				previousHoursInfo.setStartCharging("60");
				previousHoursInfo.setPreviousHoursAmountFee(feeRuler(payex));
				previousHoursInfo.setPreviousHoursFeeCycle("30");
			}
			
			if(payexSub[1].contains("未滿半小時以半小時計")){
				result = "30";
			}else{
				result = "60";
			}
			
		} else {
			// only one information(Fee Cycle),therefore I want to process
			// handily.
			// System.out.println(payex);
			result = "60";
		}
		return result;
	}

}
