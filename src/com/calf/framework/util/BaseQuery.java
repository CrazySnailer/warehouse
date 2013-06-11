package com.calf.framework.util;

public class BaseQuery implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1312137472322020552L;
	
	/**
	 * 页码
	 */
	private int pageNo = 1;
	/**
	 * 也大小
	 */
	private int pageSize = 10;
	/**
	 * 排序列
	 */
	private String orderCol;
	/**
	 * 排序类型
	 */
	private int orderType;
	/**
	 * 正式提交查询
	 */
	int qryIt;
	
	/**
	 * 开始记录数
	 */
	int end;
	/**
	 * 结束记录数
	 */
	int start;
	
	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderCol() {
		return orderCol;
	}

	public void setOrderCol(String orderCol) {
		this.orderCol = orderCol;
	}


	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getQryIt() {
		return qryIt;
	}

	public void setQryIt(int qryIt) {
		this.qryIt = qryIt;
	}
}
