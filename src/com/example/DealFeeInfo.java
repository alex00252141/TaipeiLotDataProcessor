package com.example;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.type.FeeInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DealFeeInfo {
	
	private FeeInfo eachFeeInfo;

	public void dealFareInfo(ArrayList<EachData> data) {
		eachFeeInfo = new FeeInfo();
		for (int i = 0; i < data.size(); ++i) {
			if (data.get(i).getFareInfo() != null) {
				JsonObject jobject = new JsonParser().parse(data.get(i).getFareInfo()).getAsJsonObject();
				if (jobject.has("WorkingDay")) {
					JsonElement previousHoursInfo = jobject.get("WorkingDay");
					for (int i1 = 0; i1 < previousHoursInfo.getAsJsonArray().size(); i1++) {
						// System.out.println(previousHoursInfo.getAsJsonArray().get(i1));
						
						
						String Period = previousHoursInfo.getAsJsonArray().get(i1).getAsJsonObject().get("Period")
								.getAsString();
						String Fee = previousHoursInfo.getAsJsonArray().get(i1).getAsJsonObject().get("Fare")
								.getAsString();
						
						//eachFeeInfo.getcarFeeInfo().getWeekday().setIntervalInfo(Period,Fee);
						//eachFeeInfo

						//System.out.println(Period + Fee);
					}
				}
				if (jobject.has("Holiday")) {
					JsonElement previousHoursInfo = jobject.get("Holiday");
					for (int i1 = 0; i1 < previousHoursInfo.getAsJsonArray().size(); i1++) {
						// System.out.println(previousHoursInfo.getAsJsonArray().get(i1));

						String Period = previousHoursInfo.getAsJsonArray().get(i1).getAsJsonObject().get("Period")
								.toString();
						String Fare = previousHoursInfo.getAsJsonArray().get(i1).getAsJsonObject().get("Fare")
								.toString();

						System.out.println(Period + Fare);
					}
				}
			}
		}

	}

	public void dealpayex(ArrayList<EachData> data) {
		int number = 0;
		int a = 0,b = 0,c = 0,d = 0,e = 0,f = 0,g = 0;
		
		RegularExpression r = new RegularExpression();
		
		for (int i = 0; i < data.size(); ++i) {
			if (data.get(i).getPayex().contains("計時")|data.get(i).getPayex().contains("時")) {
				
				r.previousRuler(data.get(i).getPayex());
				r.feeRuler(data.get(i).getPayex());
				//r.feeCycleRuler(data.get(i).getPayex());
				
				++a;
			} else if (data.get(i).getPayex().contains("計次")) {
				//System.out.println(data.get(i).getPayex());
				//data.get(i).getInfo().getcarFeeInfo().getWeekday().setTimesFee(r.feeRuler(data.get(i).getPayex())) ;
				++b;
			} else if (data.get(i).getPayex().contains("不收費")) {
				// 暫時不處理
				++c;
			} else if (data.get(i).getPayex().contains("月租")) {
				// 暫時不處理
				++d;
			} else if (data.get(i).getPayex().contains("詳現場公告")) {
				// 暫時不處理
				++e;
			} else if (data.get(i).getPayex().contains("以停車場公告為準。")) {
				// 暫時不處理
				++f;
			} else {

				//System.out.println(data.get(i).getPayex());
				//r.feeRuler(data.get(i).getPayex());
				++g;
			}
		}
		// System.out.println(number);
		//System.out.println("計時:"+a+";計次:"+b+";不收費:"+c+";月租:"+d+
		//";詳現場公告:"+e+";以停車場公告為準。:"+f+";其他:"+g);
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