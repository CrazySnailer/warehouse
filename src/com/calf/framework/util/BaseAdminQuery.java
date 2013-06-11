package com.calf.framework.util;

import com.calf.framework.vo.AdminUserInfo;

public class BaseAdminQuery extends BaseQuery {
	/**
	 * 用户对象
	 */
	private AdminUserInfo userInfo;
	/**
	 * 机构别名
	 */
	private String deptAlias;
	/**
	 * 用户别名
	 */
	private String userAlias;
	
	/**
	 * 获取查询16进值
	 * 
	 * @return
	 */
	public String getQryHex() {
		try {
			this.userInfo = null;
			// 只序列化String long Date 类型
			byte b[] = ObjectUtils.getBytesFromObject(this);
			StringBuffer sb = new StringBuffer();
			sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				String s = Integer.toHexString(b[i]);
				if (s.length() < 2)
					sb.append("0");
				else if (s.length() > 2)
					s = s.substring(s.length() - 2);
				sb.append(s);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	

	public AdminUserInfo getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(AdminUserInfo userInfo) {
		this.userInfo = userInfo;
	}


	public String getDeptAlias() {
		return deptAlias;
	}


	public void setDeptAlias(String deptAlias) {
		this.deptAlias = deptAlias;
	}


	public String getUserAlias() {
		return userAlias;
	}


	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	
}
