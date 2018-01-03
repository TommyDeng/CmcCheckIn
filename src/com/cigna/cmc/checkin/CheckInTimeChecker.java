package com.cigna.cmc.checkin;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckInTimeChecker {

	public static String check(Calendar calendarForcheck) {
		int amOrPm = calendarForcheck.get(Calendar.AM_PM);
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss a");
		System.out
				.println(simpleDateFormat1.format(calendarForcheck.getTime()));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr = simpleDateFormat.format(calendarForcheck
				.getTime());

		// 今天是否已经checkin 或者checkout
		if (currentDateStr.equals(SystemConfigUtils
				.getValue(Calendar.AM == amOrPm ? Constants.LAST_CHECKIN_DATE
						: Constants.LAST_CHECKOUT_DATE))) {
			System.out.println("Already Check In Today...");
			return Constants.ITS_TIME_HOVER;
		}

		// 过滤黑名单
		String blackDatesStr = SystemConfigUtils
				.getValue(Constants.BLACK_DATES);
		String[] blackDates = blackDatesStr.split(",");
		for (String blackDate : blackDates) {
			if (currentDateStr.equals(blackDate.trim())) {
				System.out.println("Black List...");
				return Constants.ITS_TIME_HOVER;
			}
		}

		// 是否在白名单
		String whiteDatesStr = SystemConfigUtils
				.getValue(Constants.WHITE_DATES);
		String[] whiteDates = whiteDatesStr.split(",");
		for (String whiteDate : whiteDates) {
			if (currentDateStr.equals(whiteDate.trim())) {
				System.out.println("White List!!!");
				return checkTime(calendarForcheck);
			}
		}

		// 是否是周末
		if (Calendar.SATURDAY == calendarForcheck.get(Calendar.DAY_OF_WEEK)
				|| Calendar.SUNDAY == calendarForcheck
						.get(Calendar.DAY_OF_WEEK)) {
			System.out.println("Weekend...");
			return Constants.ITS_TIME_HOVER;
		}

		// 是否到达时间点
		return checkTime(calendarForcheck);

	}

	private static String checkTime(Calendar calendarForcheck) {
		int amOrPm = calendarForcheck.get(Calendar.AM_PM);
		if (Calendar.AM == amOrPm) {
			String startCheckInTimeStr = SystemConfigUtils
					.getValue(Constants.START_CHECKIN_TIME);

			String[] startCheckInTime = startCheckInTimeStr.split(":");

			String startCheckInHour = startCheckInTime[0];
			String startCheckInMinute = startCheckInTime[1];

			if (calendarForcheck.get(Calendar.HOUR_OF_DAY) == Integer
					.valueOf(startCheckInHour)
					&& calendarForcheck.get(Calendar.MINUTE) >= Integer
							.valueOf(startCheckInMinute)) {
				System.out.println("Bingo!!! Check In!");
				return Constants.ITS_TIME_CHECKIN;
			}
		} else {
			String startCheckOutTimeStr = SystemConfigUtils
					.getValue(Constants.START_CHECKOUT_TIME);

			String[] startCheckOutTime = startCheckOutTimeStr.split(":");

			String startCheckOutHour = startCheckOutTime[0];
			String startCheckOutMinute = startCheckOutTime[1];

			if ((calendarForcheck.get(Calendar.HOUR_OF_DAY) == Integer
					.valueOf(startCheckOutHour) && calendarForcheck
					.get(Calendar.MINUTE) >= Integer
					.valueOf(startCheckOutMinute))
					|| calendarForcheck.get(Calendar.HOUR_OF_DAY) > Integer
							.valueOf(startCheckOutHour)) {
				System.out.println("Bingo!!! Check Out!");
				return Constants.ITS_TIME_CHECKOUT;
			}
		}

		System.out.println("Time not Matched...");
		return Constants.ITS_TIME_HOVER;
	}
}
