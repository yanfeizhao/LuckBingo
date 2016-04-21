package com.my.luckbingofly.utils;

import android.text.format.Time;

public class GetSystemDateTimeUtils {

	public static String GetDateAndTime() {
		String dateandtime = "";

		// Time time = new Time("GMT+8");
		Time time = new Time();
		time.setToNow();
		int year = time.year;
		int month = time.month + 1; // 咦，为什么会少一个月？？
		int day = time.monthDay;
		int minute = time.minute;
		int hour = time.hour;
		int sec = time.second;
		
		
		
		dateandtime = year + "-" + getStandardNum(month) + "-"
				+ getStandardNum(day) + "  " + getStandardNum(hour)
				+ ":"+getStandardNum(minute)+":" + getStandardNum(sec);
		
		return dateandtime;
	}

	public static String getStandardNum(int num) {
		String standardnum = "";
		if (num < 10) {
			standardnum = "0" + num;
		} else {
			standardnum = num + "";
		}
		return standardnum;

	}
	
	

}
