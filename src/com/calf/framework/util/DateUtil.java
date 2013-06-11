package com.calf.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

	/**
	 * @param yyyy
	 *            -MM-dd
	 * @return
	 */
	public static final String patternA = "yyyy-MM-dd";
	/**
	 * @param yyyyMMdd
	 * @return
	 */
	public static final String patternB = "yyyyMMdd";
	/**
	 * @param yyyy
	 *            -MM-dd HH-mm-ss
	 * @return
	 */
	public static final String patternC = "yyyy-MM-dd HH-mm-ss";
	/**
	 * @param yyyy
	 *            :MM:dd HH:mm:ss
	 * @return
	 */
	public static final String patternD = "yyyy:MM:dd HH:mm";
	/**
	 * @param yyyy
	 *            -MM-dd HH:mm:ss
	 * @return
	 */
	public static final String patternE = "yyyy-MM-dd HH:mm";

	/**
	 * @param yyyyMMddHHmmss
	 * @return
	 */
	public static final String patternF = "yyyyMMddHHmmss";

	public static final String patternG = "yyyy";

	private static DateUtil instance;

	public static DateUtil getInstance() {
		if (instance == null) {
			instance = new DateUtil();
		}
		return instance;
	}

	/**
	 * 格式化日期为yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public String formateDate(Date date) {
		return dateToString(date, patternA);
	}

	/**
	 * @param 将指定日期
	 *            ,以指定pattern格式,输出String值
	 * @return
	 */
	public String dateToString(Date date, String pattern) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.format(date);
		}
	}

	/**
	 * @param 将指定字符型日期转为日期型
	 *            ,,格式为指定的pattern
	 * @return
	 */
	public Date stringToDate(String string, String pattern) {
		SimpleDateFormat format = (SimpleDateFormat) DateFormat
				.getDateInstance();
		format.applyPattern(pattern);
		try {
			return format.parse(string);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @param 将指定字符型日期转为日期型
	 *            ,指定格式为yyyy-MM-dd
	 * @return
	 */
	public Date stringToDate(String string) {
		return stringToDate(string, patternA);
	}

}