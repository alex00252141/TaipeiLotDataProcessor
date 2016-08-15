package com.example;

import com.google.gson.Gson;

public class MainTest {
	public static void main(String[] args) {
		TaipeiData data = new TaipeiData();
		DealFeeInfo deal = new DealFeeInfo();
		
		//deal.dealFareInfo(data.getAllData());
		deal.dealpayex(data.getAllData());
		
		FeeInfo test = new FeeInfo();
		Gson g = new Gson();
		System.out.println(g.toJson(test).toString());
		
	}
}
