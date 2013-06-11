package com.calf.framework.util;

public class CodeName {
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;

	
	public CodeName() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CodeName(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
