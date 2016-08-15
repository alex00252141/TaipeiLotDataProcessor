package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
	private String testPreviousRuler = "[(](\\d+)[-|~](\\d+)時?[)]"; // previousHoursInfo
	private String testFeeRuler = "(\\d+)[元]+[/]?[時|次]?"; // FeeInfo
	private String testFeeCycleRuler = "[未滿1小時以1小時|停車逾1小時以上]"; // feeCycleFeeInfo
	private String testFeepreHour;

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

	public void previousRuler(String eachJsonInfo) {
		System.out.println(eachJsonInfo);
		Matcher m = previousRuler.matcher(eachJsonInfo);
		if (m.find()) {

			// set "timesFee": "(計次收費金額)",

			// System.out.println(m.groupCount()+":"+ m.start()+":"+m.end());;
			
			System.out.println("Found startPrevious: " + m.group(1));
			System.out.println("Found endPrevious: " + m.group(2));

			++testYES;
		}

		++testAll;
		// System.out.println("Y:"+testYES+"N:"+testAll+";"+testYES/testAll+"%");
	}

	public void feeRuler(String eachJsonInfo) {
		Matcher m2 = feeRuler.matcher(eachJsonInfo);
		if (m2.find()) {
			// System.out.println("Found value2: " + m2.group(0) );
			System.out.println("Found fee: " + m2.group(1));
			// System.out.println("Found value2: " + m2.group(2) );
			
		// System.out.println("Y:"+testYES+"N:"+testAll+";"+testYES/testAll);
		}
		++testYES;
	}

	public void feeCycleRuler(String eachJsonInfo) {
		Matcher m3 = feeRuler.matcher(eachJsonInfo);

		if (eachJsonInfo.contains("全程")) {
			// 全程半小時
			// set "previousHoursFeeCycle": 30
			// System.out.println(eachJsonInfo);
		} else if (m3.find()) {
			// 第一小時三十 後面以半小時計
			if (eachJsonInfo.contains("半小時")) {
				// set "previousHoursFeeCycle": 30
				// set "previousHoursInfo": {
				// 				"startCharging": 60,
				// 				"previousHoursAmountFee": ,
				// 				"previousHoursFeeCycle": 60 
				// 		},

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
		}
	}

}
