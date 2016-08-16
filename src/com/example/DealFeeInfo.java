package com.example;

import java.util.ArrayList;

import com.example.type.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DealFeeInfo {

	private FeeInfo eachFeeInfo;
	private VehicleFeeInfo carFeeInfo;
	private DayObject weekday;
	private DayObject weekendAndHoliday;
	private PreviousHoursInfoObject previousHoursInfo;
	private ArrayList<IntervalInfoObject> intervalInfo;
	private ArrayList<EachData> allData;
	private ArrayList<FeeInfo> allDataFeeInfo;

	public DealFeeInfo() {
		announce();
		allDataFeeInfo = new ArrayList<FeeInfo>();
	}

	public void announce() {
		eachFeeInfo = new FeeInfo();
		carFeeInfo = new VehicleFeeInfo();
		weekday = new DayObject();
		weekendAndHoliday = new DayObject();
		previousHoursInfo = new PreviousHoursInfoObject();
		intervalInfo = new ArrayList<IntervalInfoObject>();
	}
	public ArrayList<FeeInfo> getAllDataFeeInfo(){
		return allDataFeeInfo;
	}

	public void deal(ArrayList<EachData> allData) {
		this.allData = allData;
		

		for (int i = 0; i < allData.size(); ++i) {
			System.out.println("---");
			System.out.println("ID: " + allData.get(i).getOriginalId());
			System.out.println(allData.get(i).getPayex());
			announce(); // 每筆資料都是一個新的feeInfo 故重新宣告
			if (allData.get(i).getFareInfo() != null) // 如果有提供FareInfo 使用此資料去做判斷
				dealFareInfo(allData.get(i).getFareInfo());
			else
				dealPayex(allData.get(i).getPayex()); // 如果沒有，則使用payex去做字串分析

			// 將整個feeInfo組合起來
			carFeeInfo.setWeekday(weekday);
			carFeeInfo.setWeekendAndHoliday(weekendAndHoliday);
			eachFeeInfo.setcarFeeInfo(carFeeInfo);
			// 把每筆資料加入到arrayList
			allDataFeeInfo.add(eachFeeInfo);
		}
	}

	public void dealFareInfo(String fareInfo) {

		JsonObject jobject = new JsonParser().parse(fareInfo).getAsJsonObject();
		String Period;
		String Fee;
		IntervalInfoObject info = new IntervalInfoObject();

		if (jobject.has("WorkingDay")) {
			JsonElement previousHoursInfo = jobject.get("WorkingDay");
			for (int i1 = 0; i1 < previousHoursInfo.getAsJsonArray().size(); i1++) {
				// System.out.println(previousHoursInfo.getAsJsonArray().get(i1));

				Period = previousHoursInfo.getAsJsonArray().get(i1).getAsJsonObject().get("Period").getAsString();
				Fee = previousHoursInfo.getAsJsonArray().get(i1).getAsJsonObject().get("Fare").getAsString();

				// eachFeeInfo.getcarFeeInfo().getWeekday().setIntervalInfo(Period,Fee);
				// eachFeeInfo
				info.setinterval(Period);
				info.setTimeChargeParkingFee(Fee);
				//
				info.setfeeCycle("30"); // 計費周期 還需要確認是一小時還是半小時
				//

				intervalInfo.add(info);

				System.out.println("fareInfo ：" + Period + "," + Fee);
			}
			weekday.setIntervalInfo(intervalInfo);
		}

		if (jobject.has("Holiday")) {
			JsonElement previousHoursInfo = jobject.get("Holiday");
			for (int i1 = 0; i1 < previousHoursInfo.getAsJsonArray().size(); i1++) {
				// System.out.println(previousHoursInfo.getAsJsonArray().get(i1));

				Period = previousHoursInfo.getAsJsonArray().get(i1).getAsJsonObject().get("Period").getAsString();
				Fee = previousHoursInfo.getAsJsonArray().get(i1).getAsJsonObject().get("Fare").getAsString();

				info.setinterval(Period);
				info.setTimeChargeParkingFee(Fee);
				//
				info.setfeeCycle("30"); // 計費周期 還需要確認是一小時還是半小時
				//

				intervalInfo.add(info);

				System.out.println("fareInfo ：" + Period + "," + Fee);
			}
			weekendAndHoliday.setIntervalInfo(intervalInfo);
		}
	}

	public void dealPayex(String payex) {
		int number = 0;
		int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0;

		RegularExpression r = new RegularExpression();

		if (payex.contains("計時：")) {
			weekday.setPreviousHoursInfo(r.getPreviousHoursInfo(payex));
			weekday.setIntervalInfo(r.getIntervalInfo(payex));

		} else if (payex.contains("計次") && payex.contains("機車計次") == false) {

			System.out.println("計次： " + r.feeRuler(payex));
			weekday.setTimesFee(r.feeRuler(payex)); // 給入計次金額的錢
			++a;
		} else if (payex.contains("計時") | payex.contains("時")) {

			weekday.setPreviousHoursInfo(r.getPreviousHoursInfo(payex));
			weekday.setIntervalInfo(r.getIntervalInfo(payex));
			// r.getIntervalInfo(payex) 得到每筆資料的intervalInfo

		} else if (payex.contains("不收費")) {
			// 暫時不處理
			++c;
		} else if (payex.contains("月租")) {
			// 暫時不處理
			++d;
		} else if (payex.contains("詳現場公告")) {
			// 暫時不處理
			++e;
		} else if (payex.contains("以停車場公告為準。")) {
			// 暫時不處理
			++f;
		} else {
			// 暫時不處理
			// System.out.println(data.get(i).getPayex());
			// r.feeRuler(data.get(i).getPayex());
			++g;
		}
		// System.out.println(number);
		// System.out.println("計時:"+a+";計次:"+b+";不收費:"+c+";月租:"+d+
		// ";詳現場公告:"+e+";以停車場公告為準。:"+f+";其他:"+g);
	}

}

//
/*
 * if(data.get(i).getPayex().contains("半小時")){
 * if(data.get(i).getPayex().contains("全程以半小時計費")){
 * 
 * }else if(data.get(i).getPayex().contains("全程以半小時收費")){
 * 
 * }else if(data.get(i).getPayex().contains("以半小時計價")){
 * 
 * }else if(data.get(i).getPayex().contains("全程以半小時")){
 * 
 * }else if(data.get(i).getPayex().contains("停車未滿半小時以半小時計，逾半小時以上")){
 * 
 * }else if(data.get(i).getPayex().contains("停車未滿1小時以1小時計")){
 * 
 * }else if(data.get(i).getPayex().contains("未滿半小時以半小時計")){
 * 
 * }else if(data.get(i).getPayex().contains("停車未滿1小時以1小時計費，逾1小時以上者")){
 * 
 * }else if(data.get(i).getPayex().contains("停車逾1小時以上")){
 * 
 * }else if(data.get(i).getPayex().contains("逾1小時以上")){
 * 
 * }else if(data.get(i).getPayex().contains("逾1小時以上，未滿半小時以半小時計")){
 * 
 * }else if(data.get(i).getPayex().contains("全程")){
 * 
 * }else{ //System.out.print(i+":");
 * //System.out.println(data.get(i).getPayex()); number++; } }else
 */