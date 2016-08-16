package com.example;

import java.util.ArrayList;

import com.example.save.FileDataManager;
import com.example.test.FeeCalculation;
import com.example.type.FeeInfo;
import com.google.gson.Gson;

public class MainTest {

	private static FeeCalculation feeCaculation ;
	
	public static void main(String[] args) {
		TaipeiData data = new TaipeiData();
		DealFeeInfo dealFeeInfo = new DealFeeInfo();
		
		// FileDataManager fileDataManger = new FileDataManager("data");

		// deal.dealFareInfo(data.getAllData());
		dealFeeInfo.deal(data.getAllData());

		ArrayList<FeeInfo> allDataFeeInfo = dealFeeInfo.getAllDataFeeInfo();

		Gson g = new Gson();
		for (int i = 0; i < allDataFeeInfo.size(); ++i) {
			System.out.println(data.getAllData().get(i).getPayex());
			caculationTest(g.toJson(allDataFeeInfo.get(i)).toString());
		}

	}
	
	public static void caculationTest(String  testJson){

		System.out.println(testJson);
		feeCaculation = new FeeCalculation(testJson);
	}
}
