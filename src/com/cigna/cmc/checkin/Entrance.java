package com.cigna.cmc.checkin;

import java.util.Calendar;
import java.util.Random;

/**
 * 
 * @author Tommy Deng
 * 
 */
public class Entrance {

	public static void main(String[] args) throws Exception {
		SystemConfigUtils.load();
		// final Robot robot = new Robot();
		final Random random = new Random();
		final String isRandom = SystemConfigUtils
				.getValue(Constants.RANDOM_ENABLE);
		String itsTimeWhat = Constants.ITS_TIME_HOVER;
		while (true) {
			// Time Check
			itsTimeWhat = CheckInTimeChecker.check(Calendar.getInstance());
			if (Constants.ITS_TIME_CHECKIN.equals(itsTimeWhat)) {
				if ("Y".equals(isRandom)) {
					int sleepSecondInt = random
							.nextInt(Integer.valueOf(SystemConfigUtils
									.getValue(Constants.CHECKIN_RANDOM_THRESHOLD)));
					System.out.println("Sleep " + sleepSecondInt + "Seconds");
					Thread.sleep(sleepSecondInt * 1000);
				}

				CheckInSender.sendCheckIn();

				SystemConfigUtils.saveCheck(Constants.LAST_CHECKIN_DATE);

			} else if (Constants.ITS_TIME_CHECKOUT.equals(itsTimeWhat)) {
				if ("Y".equals(isRandom)) {
					int sleepSecondInt = random
							.nextInt(Integer.valueOf(SystemConfigUtils
									.getValue(Constants.CHECKOUT_RANDOM_THRESHOLD)));
					System.out.println("Sleep " + sleepSecondInt + "Seconds");
					Thread.sleep(sleepSecondInt * 1000);
				}

				CheckInSender.sendCheckOut();

				SystemConfigUtils.saveCheck(Constants.LAST_CHECKOUT_DATE);

			} else {
				String checkGapStr = SystemConfigUtils
						.getValue(Constants.CHECK_GAP);
				Thread.sleep(Integer.valueOf(checkGapStr) * 1000);
			}

		}
	}
}
