package com.example.type;

public class VehicleFeeInfo {

	private DayObject weekday;
	private DayObject weekendAndHoliday;
	
	public VehicleFeeInfo() {
		
		weekday = new DayObject();
		weekendAndHoliday = new DayObject();
	}
	public VehicleFeeInfo(DayObject _weekday, DayObject _weekendAndHoliday) {
		// TODO Auto-generated constructor stub
		setWeekday(_weekday);
		setWeekendAndHoliday(_weekendAndHoliday);
	}

	public DayObject getWeekday() {
		return weekday;
	}

	public void setWeekday(DayObject weekday) {
		this.weekday = weekday;
	}

	public DayObject getWeekendAndHoliday() {
		return weekendAndHoliday;
	}

	public void setWeekendAndHoliday(DayObject weekendAndHoliday) {
		this.weekendAndHoliday = weekendAndHoliday;
	}

}
