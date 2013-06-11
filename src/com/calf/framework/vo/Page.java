/* 
 * Copyright (c) 1994-2006 Teamsun
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Finalist IT Group, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Teamsun.
 * 
 */
package com.calf.framework.vo;

import java.util.List;



public class Page implements java.io.Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4548097669106499025L;

	public static int DEFAULT_PAGE_SIZE=20;

	private List results;

	private int pageNo;
	
	private int pageAmount;

	private int pageSize;

	private int recordSize;
	
	private long start;
	
	private String funcName ="$.gotoPage";

	public Page(List results, int pageNo, int pageSize, int recordSize) {
		this.results = results;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.recordSize = recordSize;
	}

	/**
	 * 默认构造方法.
	 *
	 * @param start	 本页数据在数据库中的起始位置
	 * @param totalSize 数据库中总记录条数
	 * @param pageSize  本页容量
	 * @param data	  本页包含的数据
	 */
	public Page(int start, int totalSize, int pageSize, List data) {
		this.pageSize = pageSize;
		this.start = start;
		this.pageAmount = totalSize;
		this.results = data;
	}
	
	public Page() {
		// TODO Auto-generated constructor stub
	}

    /**

     */
    public void setResults(List results) {
        this.results = results;
    }
	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Page#getResults()
	 */
	public List getResults() {
		return this.results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Page#getPageNo()
	 */
	public int getPageNo() {
		return this.pageNo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Page#getPageSize()
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Page#hasNext()
	 */
	public boolean hasNext() {
		return this.pageNo < this.getPageAmount()? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Page#hasPrevious()
	 */
	public boolean hasPrevious() {
		return this.pageNo > 1 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Page#getPageAmount()
	 */
	public int getPageAmount() {
		return (int) Math.ceil((double) this.recordSize / this.pageSize);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Page#getRecordSize()
	 */
	public int getRecordSize() {
		return this.recordSize;
	}

	public void setPageAmount(int pageAmount) {
		this.pageAmount = pageAmount;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}
	
	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 *
	 * @see #getStartOfPage(int,int)
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 获取任一页第一条数据在数据集的位置.
	 *
	 * @param pageNo   从1开始的页号
	 * @param pageSize 每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
	
	public String getPagination(){
		StringBuilder sb = new StringBuilder();
		this.pageAmount = getPageAmount();
		if (pageNo == 1) {// 如果是首页
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:"+funcName+"("+(pageNo-1)+","+pageSize+");\">&#171; 上一页</a></li>\n");
		}
		if(pageAmount<=10){
			for (int i = 1; i <= pageAmount; i++) {
				if(i==this.pageNo){
					sb.append("<li class=\"active\"><a href=\"javascript:"+funcName+"("+i+","+pageSize+");\">" + i + "</a></li>\n");
				}else{
					sb.append("<li><a href=\"javascript:"+funcName+"("+i+","+pageSize+");\">" + i + "</a></li>\n");
				}
			}
		}else{
			int from = this.pageNo-3;
			int to = this.pageNo+2;
			
			if(this.pageNo==1){
				sb.append("<li class=\"active\"><a href=\"javascript:"+funcName+"("+1+","+pageSize+");\">" + 1 + "</a></li>\n");
			}else{
				sb.append("<li><a href=\"javascript:"+funcName+"("+1+","+pageSize+");\">" + 1 + "</a></li>\n");	
			}
			if((this.pageNo-4)>1){
				sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			}else{
				from = 2;
				to = from+5;
			}			
			
			if (to > pageAmount) {
				to = pageAmount;
				from = to -5;
			}
			
			for (int i = from; i < to; i++) {
				if(i==this.pageNo){
					sb.append("<li class=\"active\"><a href=\"javascript:"+funcName+"("+i+","+pageSize+");\">" + i + "</a></li>\n");
				}else{
					sb.append("<li><a href=\"javascript:"+funcName+"("+i+","+pageSize+");\">" + i + "</a></li>\n");
				}
			}
			if((this.pageNo+3)<pageAmount){
				sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			}
			
			if(pageNo==pageAmount){
				sb.append("<li class=\"active\"><a href=\"javascript:"+funcName+"("+pageAmount+","+pageSize+");\">" + pageAmount + "</a></li>\n");
			}else{
				sb.append("<li><a href=\"javascript:"+funcName+"("+pageAmount+","+pageSize+");\">" + pageAmount + "</a></li>\n");
			}
		}

		if (pageNo == this.pageAmount) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:"+funcName+"("+(pageNo+1)+","+pageSize+");\">"
					+ "下一页 &#187;</a></li>\n");
		}

		sb.append("<li class=\"disabled\"><a href=\"javascript:\" style=\"border:0;padding-top:1px;_padding-top:7px;\">第");
		sb.append("<input type=\"text\" class=\"formText\" value=\""+pageNo+"\" style=\"width:30px;padding:0;margin:0 2px 3px 2px;text-align:center;\" ");
		sb.append("onkeypress=\"if(window.event.keyCode==13) "+funcName+"(this.value,"+pageSize+");\" onclick=\"this.select();\"/>页/");
		sb.append("<input type=\"text\" class=\"formText\" value=\""+pageSize+"\" style=\"width:30px;padding:0;margin:0 2px 3px 2px;text-align:center;\" ");
		sb.append("onkeypress=\"if(window.event.keyCode==13) "+funcName+"("+pageNo+",this.value);\" onclick=\"this.select();\"/>条每页，");
		sb.append("共" + this.getRecordSize() + "条</a><li>\n");

		sb.insert(0,"<ul>\n").append("</ul>\n");
		
		sb.append("<div style=\"clear:both;\"></div>");

		
		return sb.toString();
	}
	
	public String getPaginationSimple(){
		StringBuilder sb = new StringBuilder();
		this.pageAmount = getPageAmount();
		if (pageNo == 1) {// 如果是首页
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:"+funcName+"("+(pageNo-1)+","+pageSize+");\">&#171; 上一页</a></li>\n");
		}
		
		if (pageNo == this.pageAmount) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:"+funcName+"("+(pageNo+1)+","+pageSize+");\">"
					+ "下一页 &#187;</a></li>\n");
		}

		sb.insert(0,"<ul>\n").append("</ul>\n");
		
		sb.append("<div style=\"clear:both;\"></div>");

		
		return sb.toString();
	}
}