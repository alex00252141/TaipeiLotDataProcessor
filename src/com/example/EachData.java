package com.example;

import com.google.gson.annotations.SerializedName;

public class EachData {
	@SerializedName("id")
	private String originalId;
	
	private String taiwanCity = "台北市";//Preset
	
	@SerializedName("area")
	private String taiwanArea;
	@SerializedName("name")
	private String name;
	@SerializedName("type")
	private String type;
	@SerializedName("summary")
	private String summary;
	@SerializedName("address")
	private String address;
	@SerializedName("tw97x")
	private String xAxis;
	@SerializedName("tw97y")
	private String yAxis;
	@SerializedName("tel")
	private String tel;
	@SerializedName("payex")
	private String payex;
	@SerializedName("totalcar")
	private String totalCar;
	@SerializedName("totalmotor")
	private String totalMotor;
	@SerializedName("servicetime")
	private String serviceTime;
	
	private String fareInfo;
	private FeeInfo info;
	
	public EachData( String originalId, String taiwanArea, String name ,
			String type,String summary,String address,String xAxis,String yAxis,
			String payex,String totalCar,String totalMotor,String serviceTime) {
		
		setOriginalId(originalId);
		setTaiwanArea(taiwanArea);
		setName(name);
		setType(type);
		setSummary(summary);
		setAddress(address);
		setXAxis(xAxis);
		setYAxis(yAxis);
		setPayex(payex);
		setTotalCar(totalCar);
		setTotalMotor(totalMotor);
		setServiceTime(serviceTime);
		
		info = new FeeInfo();
	}
	
	public String getOriginalId(){
		return this.originalId ;
	}
	public void setOriginalId(String originalId){
		this.originalId =originalId;
	}
	
	public String getTaiwanArea(){
		return this.taiwanArea;
	}
	public void setTaiwanArea(String taiwanArea){
		this.taiwanArea=taiwanArea;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type=type;
	}
	
	public String getSummary(){
		return this.summary;
	}
	public void setSummary(String summary){
		this.summary=summary;
	}
	
	public String getAddress(){
		return this.address;
	}
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getXAxis(){
		return this.xAxis;
	}
	public void setXAxis(String xAxis){
		this.xAxis=xAxis;
	}
	
	public String getYAxis(){
		return this.yAxis;
	}
	public void setYAxis(String yAxis){
		this.yAxis=yAxis;
	}
	
	public String getPayex(){
		return this.payex;
	}
	public void setPayex(String payex){
		this.payex=payex;
	}
	
	public String getTotalCar(){
		return this.totalCar;
	}
	public void setTotalCar(String totalCar){
		 this.totalCar =totalCar;
	}
	
	public String getTotalMotor(){
		return this.totalMotor;
	}
	public void setTotalMotor(String totalMotor){
		 this.totalMotor=totalMotor;
	}
	
	public String getServiceTime(){
		return this.serviceTime;
	}
	public void setServiceTime(String serviceTime){
		this.serviceTime=serviceTime;
	}
	
	public String getFareInfo(){
		return this.fareInfo ;
	}
	public void setFareInfo(String FareInfo){
		this.fareInfo =FareInfo;
	}
	
	public FeeInfo getInfo(){
		return this.info ;
	}
	public void setFeeInfo(FeeInfo info){
		this.info =info;
	}
	
	
    public String toString() {
        return name + " - " + tel;
    }
}
