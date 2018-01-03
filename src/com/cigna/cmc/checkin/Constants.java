package com.cigna.cmc.checkin;

public class Constants {
	// ##最近一次签到日期
	public static final String LAST_CHECKIN_DATE = "last.checkin.date";

	// ##最近一次签退日期
	public static final String LAST_CHECKOUT_DATE = "last.checkout.date";

	// ##签到时间
	public static final String START_CHECKIN_TIME = "start.checkin.time";

	// ##签退时间
	public static final String START_CHECKOUT_TIME = "start.checkout.time";

	// ##扫描间隔，单位秒
	public static final String CHECK_GAP = "check.gap";

	// ##是否执行随机签到签退
	public static final String RANDOM_ENABLE = "random.enable";

	// ##随机签到阈值,单位秒
	public static final String CHECKIN_RANDOM_THRESHOLD = "checkin.random.threshold";

	// ##随机签退阈值,单位秒
	public static final String CHECKOUT_RANDOM_THRESHOLD = "checkout.random.threshold";

	// ##签到用户名
	public static final String USER_NAME = "user.name";

	// ##签到密码
	public static final String USER_PASSWORD = "user.password";

	// ##黑名单
	public static final String BLACK_DATES = "black.dates";
	
	// ##白名单
	public static final String WHITE_DATES = "white.dates";

	
	
	public static final String ITS_TIME_CHECKIN = "ITS_TIME_CHECKIN";
	public static final String ITS_TIME_CHECKOUT = "ITS_TIME_CHECKOUT";
	public static final String ITS_TIME_HOVER = "ITS_TIME_HOVER";
}
