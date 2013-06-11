package com.calf.framework.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class FormateUtil {
	private static FormateUtil instance;
	public static final NumberFormat MONEY_FORMATE = new DecimalFormat("##0.00");

	public static final NumberFormat MONEY_FORMATE_B = new DecimalFormat(
			"##0.0000");

	public static final NumberFormat MONEY_FORMATE_LONG = new DecimalFormat(
			"##0");

	public static FormateUtil getInstance() {
		if (instance == null) {
			instance = new FormateUtil();
		}
		return instance;
	}

	public String formateDouble(Double value) {
		return this.formateDouble(value, "");
	}

	public String formateDouble(Double value, String defaultValue) {
		if (value == null) {
			return defaultValue;
		} else {
			return MONEY_FORMATE.format(value);
		}
	}

	public String formateDouble(Double value, String defaultValue,
			NumberFormat format) {
		if (value == null) {
			return defaultValue;
		} else {
			return format.format(value);
		}
	}

	/**
	 * 得到参数中文名
	 * 
	 * @param cns
	 * @param val
	 * @return
	 */
	public String getNameByCode(String paramType, String paramCode) {
		List<CodeName> codes = (List<CodeName>) Constants.params.get(paramType);
		String ret = "";
		if (paramCode != null && codes != null) {
			for (CodeName next : codes) {
				if (paramCode.equals(next.getCode())) {
					ret = next.getName();
					break;
				}
			}
		}
		return ret;
	}

	/**
	 * 每3位补零
	 * @param i
	 * @return
	 */
	public String appendZero(int i) {
		String pattern = "000";
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(i);
	}
}
