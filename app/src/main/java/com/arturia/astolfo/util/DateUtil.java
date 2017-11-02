package com.arturia.astolfo.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Author: lybeat
 * Date: 2015/12/20
 */
public class DateUtil {

	private DateUtil() {
		throw new UnsupportedOperationException("Cannot be instantiated");
	}
	
	private final static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormat2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		}
	};
	
	public static boolean isInEasternEightZones() {
		boolean defaultValue;
		defaultValue = TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08");
		return defaultValue;
	}

	/**
	 * 转换不同时区的日期
	 * @param date 待转换日期
	 * @param oldZone 旧时区
	 * @param newZone 新时区
	 * @return 转换后的日期
	 */
	public static Date transformTimeWithTimeZone(Date date, TimeZone oldZone,
                                                 TimeZone newZone) {
		Date finalDate = null;
		if (date != null) {
			int timeOffset = oldZone.getOffset(date.getTime())
					- newZone.getOffset(date.getTime());
			finalDate = new Date(date.getTime() - timeOffset);
		}
		return finalDate;

	}

	public static String getCurrentTime(String format) {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
		return sdf.format(date);
	}

	/**
	 * @param date 待格式化日期
	 * @param format 日期格式
	 * @return 格式化后的日期
	 */
	public static String getChangeDateFormat(Date date, String format) {
		String str = null;
		if (date != null && !"".equals(format)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			str = sdf.format(date);
		}

		return str;
	}

	/**
	 * 将日期转换为星期
	 * @param date 待转换日期
	 * @return 星期
	 */
	public static String getWeekWithDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_WEEK);
		String str = null;
		if (day == 1) {
			str = "周日";
		} else if (day == 2) {
			str = "周一";
		} else if (day == 3) {
			str = "周二";
		} else if (day == 4) {
			str = "周三";
		} else if (day == 5) {
			str = "周四";
		} else if (day == 6) {
			str = "周五";
		} else if (day == 7) {
			str = "周六";
		}
		return str;
	}

	public static int getDayOfMonth() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfYear() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.DAY_OF_YEAR);
	}

	public static int getTomorrowOfMonth() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int getHourOfDay24() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 判断两个日期是否是同一天
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return true: 同一天, false: 不是同一天
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		
		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
			if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
				if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * String to date
	 * 
	 * @param sdate 字串日期
	 * @return 转换后的日期
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormat.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static long dateToStamp(String time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		try {
			Date date = simpleDateFormat.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static long dateToStamp2(String time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
		try {
			Date date = simpleDateFormat.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String secondToString(long stamp) {
        stamp = stamp * 1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(Long.parseLong(String.valueOf(stamp)));
	}

	public static String stampToString(long stamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		return sdf.format(Long.parseLong(String.valueOf(stamp)));
	}

	@SuppressLint("DefaultLocale")
	public static String formatDuration(int duration) {
		duration /= 1000; // milliseconds into seconds
		int minute = duration / 60;
		int hour = minute / 60;
		minute %= 60;
		int second = duration % 60;
		if (hour != 0) {
			return String.format("%2d:%02d:%02d", hour, minute, second);
		} else {
			return String.format("%02d:%02d", minute, second);
		}
	}
	
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormat2.get().format(today);
			String timeDate = dateFormat2.get().format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}

	public static String msToString(long ms) {
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss", Locale.getDefault());
		return sdf.format(ms);
	}

	/**
	 * 得到友好的时间格式
	 * @param sdate  yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getFriendlyTime(String sdate) {
		Date time;
		if (isInEasternEightZones()) {
			time = toDate(sdate);
		} else {
			time = transformTimeWithTimeZone(toDate(sdate),
					TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
		}
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		String curDate = dateFormat2.get().format(cal.getTime());
		String paramDate = dateFormat2.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormat2.get().format(time);
		}
		return ftime;
	}
}
