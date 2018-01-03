package com.cigna.cmc.checkin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class SystemConfigUtils {

	private static String configFileRelativePath = "system.properties";

	private static Properties systemProperties = null;

	public static String getValue(String key) {
		return systemProperties.getProperty(key);
	}

	private static void setValue(String key, String value) {
		systemProperties.setProperty(key, value);
	}

	public static void load() {
		InputStream inMain = null;
		try {
			inMain = new FileInputStream(configFileRelativePath);
			systemProperties = new Properties();
			systemProperties.load(inMain);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inMain != null) {
					inMain.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void save() {
		OutputStream outMain = null;
		try {
			outMain = new FileOutputStream(configFileRelativePath);
			systemProperties.store(outMain, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (outMain != null) {
					outMain.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void saveCheck(String keyStr) {
		// 更新最近一次签到日期
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr = simpleDateFormat.format(Calendar.getInstance()
				.getTime());
		SystemConfigUtils.setValue(keyStr, currentDateStr);
		SystemConfigUtils.save();
	}

}
