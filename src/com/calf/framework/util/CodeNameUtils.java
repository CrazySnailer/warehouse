package com.calf.framework.util;

import java.util.List;

public class CodeNameUtils {
	private static CodeNameUtils instance;

	public static CodeNameUtils getInstance() {
		if (instance == null) {
			instance = new CodeNameUtils();
		}
		return instance;
	}

	/**
	 * 生成选择复选选择框
	 * 
	 * @return
	 */

	public String generateCheckBox(String name, String id,String paramType) {
		List<CodeName> codes = (List<CodeName>) Constants.params.get(paramType);
		
		StringBuffer buf = new StringBuffer();
		for (CodeName next : codes) {
			buf.append("<input type=\"checkbox\" name=\"");
			buf.append(name);
			buf.append("\" id=\"");
			buf.append(id);
			buf.append(next.getCode());
			buf.append("\" value=\"");
			buf.append(next.getCode());
			buf.append("\"/>");
			buf.append(next.getName());
			buf.append("&nbsp;&nbsp;");
		}
		return buf.toString();
	}

	/**
	 * 生成选择下拉框
	 * 
	 * @return
	 */
	public String generateSelect(String selectStr, String defaultName,
			String paramType) {
		@SuppressWarnings("unchecked")
		List<CodeName> codes = (List<CodeName>) Constants.params.get(paramType);
		StringBuffer buf = new StringBuffer(selectStr);

		buf.append("<option value=\"\">");
		buf.append(defaultName);
		buf.append("</option>");

		for (CodeName next : codes) {
			buf.append("<option value=\"");
			buf.append(next.getCode());
			buf.append("\">");
			buf.append(next.getName());
			buf.append("</option>");
		}

		buf.append("</select>");
		return buf.toString();
	}

	/**
	 * 生成特定选择下拉框
	 * 
	 * @return
	 */
	public String generateSelect(String selectStr, String defaultName,
			String paramType, String[] names) {
		List<CodeName> codes = (List<CodeName>) Constants.params.get(paramType);
		StringBuffer buf = new StringBuffer(selectStr);

		buf.append("<option value=\"\">");
		buf.append(defaultName);
		buf.append("</option>");

		for (CodeName next : codes) {
			if (isIn(next.getCode(), names)) {
				buf.append("<option value=\"");
				buf.append(next.getCode());
				buf.append("\">");
				buf.append(next.getName());
				buf.append("</option>");
			}
		}

		buf.append("</select>");
		return buf.toString();
	}

	/**
	 * 判断给定的字符串是否在给定的字符串数组里面
	 */
	public boolean isIn(String value, String[] values) {
		boolean result = false;
		for (int i = 0; i < values.length; i++) {
			if (value.equals(values[i])) {
				result = true;
				break;
			}
		}
		return result;
	}
}
