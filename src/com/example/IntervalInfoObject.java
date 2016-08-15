package com.example;

public class IntervalInfoObject {
	private String interval;
	private String weekdayFee;
	private String feeCycle;
	
	public IntervalInfoObject() {
		// TODO Auto-generated constructor stub
		interval = null;
		weekdayFee = null;
		feeCycle = null;
	}
	public IntervalInfoObject(String _interval,String _weekdayFee, String _feeCycle) {
		// TODO Auto-generated constructor stub
		interval = _interval;
		weekdayFee = _weekdayFee;
		feeCycle = _feeCycle;
	}


	public String getinterval() {
		return interval;
	}

	public void setinterval(String interval) {
		this.interval = interval;
	}

	public String getweekdayFee() {
		return weekdayFee;
	}

	public void setweekdayFee(String weekdayFee) {
		this.weekdayFee = weekdayFee;
	}

	public String getfeeCycle() {
		return feeCycle;
	}

	public void setfeeCycle(String feeCycle) {
		this.feeCycle = feeCycle;
	}

}
