package com.example;

import java.util.ArrayList;

import com.example.save.FileDataManager;
import com.example.test.FeeCalculation;
import com.example.type.FeeInfo;
import com.example.type.ParkerDataObject;
import com.google.gson.Gson;

public class MainTest {

	
	public static void main(String[] args) {
		TaipeiData data = new TaipeiData();
		DealFeeInfo dealFeeInfo = new DealFeeInfo();
		ArrayList<ParkerDataObject> parkerDataObjects = new ArrayList<ParkerDataObject>();
		// FileDataManager fileDataManger = new FileDataManager("data");

		// deal.dealFareInfo(data.getAllData());
		dealFeeInfo.deal(data.getAllData());

		ArrayList<FeeInfo> allDataFeeInfo = dealFeeInfo.getAllDataFeeInfo();
		ParkerDataObject object ;
		
		for(int i = 0;i<data.getAllData().size();++i){
		}
		/*
		Gson g = new Gson();
		for (int i = 0; i < allDataFeeInfo.size(); ++i) {
			System.out.println(data.getAllData().get(i).getPayex());
			caculationTest(g.toJson(allDataFeeInfo.get(i)).toString());
		}
		*/

	}
	
	public static void caculationTest(String  testJson){

		System.out.println(testJson);
		FeeCalculation feeCaculation = new FeeCalculation(testJson);
	}
}
