package com.example.type;

import java.util.ArrayList;

import com.example.EachData;
import com.example.tansfer.CoordinateTransform;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParkerDataObject {

	private String originalId;
	private String taiwanCity;
	private String taiwanArea;
	private String name;
	private String type;
	private String summary;
	private String address;
	private double xAxis;
	private double yAxis;
	private String tel;
	private String payex;
	private int totalCar;
	private int totalMotor;
	private String serviceTime;
	private String feeInfo;
	private String simpleFeeType;
	private int simpleFee;
	
	public ParkerDataObject(EachData data, FeeInfo feeInfo ){
		CoordinateTransform coordinateTransform = new CoordinateTransform();
		coordinateTransform.TWD97_To_lonlat(Double.valueOf(data.getXAxis()), Double.valueOf(data.getYAxis()));
		//tw97座標轉換成WGS84 度分秒字串的class 放入座標X、Y 再從裡面呼叫getX、Y
		Gson gson = new Gson();

		setOriginalId(data.getOriginalId());
		setTaiwanCity(data.getTaiwanCity());
		setTaiwanArea(data.getTaiwanArea());
		setName(data.getName());
		setType(data.getType());
		setSummary(data.getSummary());
		setAddress(data.getAddress());
		setXAxis(coordinateTransform.getLatX());
		setYAxis(coordinateTransform.getLonY());
		setTel(data.getTel());
		setPayex(data.getPayex());
		setTotalCar(Integer.valueOf(data.getTotalCar()));
		setTotalMotor(Integer.valueOf(data.getTotalMotor()));
		setServiceTime(data.getServiceTime());
		setfeeInfo(gson.toJson(feeInfo));
		
		if(feeInfo.getcarFeeInfo().getWeekday().getTimesFee()!=null){
			setSimpleFeeType("times");
			setSimpleFee(Integer.valueOf(feeInfo.getcarFeeInfo().getWeekday().getTimesFee()));
		}
		else if(feeInfo.getcarFeeInfo().getWeekday().getIntervalInfo()!=null){
			setSimpleFeeType("hours");
			setSimpleFee(Integer.valueOf(feeInfo.getcarFeeInfo().getWeekday().getIntervalInfo().get(0).getTimeChargeParkingFee()));
		}else{
			setSimpleFeeType(null);
			setSimpleFee(-1);
		}
			
		
	}
	
	public ParkerDataObject(String originalId, String taiwanCity, String taiwanArea, String name, String type,
			String summary, String address, double xAxis, double yAxis, String tel, String payex, int totalCar,
			int totalMotor, String serviceTime, String feeInfo ,String simpleFeeType ,int simpleFee) {
		System.out.println(originalId);
		setOriginalId(originalId);
		setTaiwanCity(taiwanCity);
		setTaiwanArea(taiwanArea);
		setName(name);
		setType(type);
		setSummary(summary);
		setAddress(address);
		setXAxis(xAxis);
		setYAxis(yAxis);
		setTel(tel);
		setPayex(payex);
		setTotalCar(totalCar);
		setTotalMotor(totalMotor);
		setServiceTime(serviceTime);
		setfeeInfo(feeInfo);
		setSimpleFeeType(simpleFeeType);
		setSimpleFee(simpleFee);

	}

	public String getOriginalId() {
		return this.originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public String getTaiwanArea() {
		return this.taiwanArea;
	}

	public void setTaiwanArea(String taiwanArea) {
		this.taiwanArea = taiwanArea;
	}

	public String getTaiwanCity() {
		return this.taiwanCity;
	}

	public void setTaiwanCity(String taiwanCity) {
		this.taiwanCity = taiwanCity;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getXAxis() {
		return this.xAxis;
	}

	public void setXAxis(double xAxis) {
		this.xAxis = xAxis;
	}

	public double getYAxis() {
		return this.yAxis;
	}

	public void setYAxis(double yAxis) {
		this.yAxis = yAxis;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPayex() {
		return this.payex;
	}

	public void setPayex(String payex) {
		this.payex = payex;
	}

	public int getTotalCar() {
		return this.totalCar;
	}

	public void setTotalCar(int totalCar) {
		this.totalCar = totalCar;
	}

	public int getTotalMotor() {
		return this.totalMotor;
	}

	public void setTotalMotor(int totalMotor) {
		this.totalMotor = totalMotor;
	}

	public String getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getfeeInfo() {
		return this.feeInfo;
	}

	public void setfeeInfo(String feeInfo) {
		this.feeInfo = feeInfo;
	}

	public String toString() {
		return name + " - " + tel;
	}

	public String getSimpleFeeType() {
		return simpleFeeType;
	}

	public void setSimpleFeeType(String simpleFeeType) {
		this.simpleFeeType = simpleFeeType;
	}

	public int getSimpleFee() {
		return simpleFee;
	}

	public void setSimpleFee(int simpleFee) {
		this.simpleFee = simpleFee;
	}

}
