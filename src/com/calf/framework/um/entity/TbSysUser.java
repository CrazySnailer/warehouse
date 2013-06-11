package com.calf.framework.um.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbSysUser implements java.io.Serializable {
	private static final long serialVersionUID = -1456344858545667243L;
	/**
	 * pk 用户ID
	 **/
	Long userId;
	/**
	 * 登录名
	 **/
	String userCode;
	/**
	 * 用户名
	 **/
	String userName;
	/**
	 * 密码
	 **/
	String loginPwd;
	/**
	 * 是否管理员
	 **/
	String isAdmin;
	/**
	 * 所属机构
	 **/
	TbSysDept dept;
	/**
	 * 机构管理员
	 */
	String deptName;
	/**
	 * 性别
	 **/
	String sex;
	/**
	 * 学历
	 **/
	String edu;
	/**
	 * 毕业学校
	 **/
	String school;
	/**
	 * 出生日期
	 **/
	Date birthday;
	/**
	 * 婚姻状况
	 **/
	String marry;
	/**
	 * 入职日期
	 **/
	Date joinDate;
	/**
	 * 电子邮件
	 **/
	String email;
	/**
	 * 固定电话
	 **/
	String tel;
	/**
	 * 手机
	 **/
	String mobile;
	/**
	 * 备注
	 **/
	String note;
	/**
	 * 是否是后台用户 ，后端用户可以在管理界面授权
	 **/
	String isBackUser;
	/**
	 * 数据状态
	 **/
	String dataStatus;
	/**
	 * 创建人
	 **/
	Long createUser;
	/**
	 * 创建时间
	 **/
	Date createDate;
	/**
	 * 更新人
	 **/
	Long updateUser;
	/**
	 * 更新时间
	 **/
	Date updateDate;

	public TbSysUser() {
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public TbSysDept getDept() {
		return dept;
	}

	public void setDept(TbSysDept dept) {
		this.dept = dept;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEdu() {
		return this.edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMarry() {
		return this.marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIsBackUser() {
		return this.isBackUser;
	}

	public void setIsBackUser(String isBackUser) {
		this.isBackUser = isBackUser;
	}

	public String getDataStatus() {
		return this.dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Long getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getBirthdayStr() {
		return DateUtil.getInstance().dateToString(this.birthday,
				DateUtil.patternA);
	}

	public String getJoinDateStr() {
		return DateUtil.getInstance().dateToString(this.joinDate,
				DateUtil.patternA);
	}

	public String getCreateDateStr() {
		return DateUtil.getInstance().dateToString(this.createDate,
				DateUtil.patternA);
	}

	public String getUpdateDateStr() {
		return DateUtil.getInstance().dateToString(this.updateDate,
				DateUtil.patternA);
	}

	public String getIsAdminStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_data_stauts",
				isAdmin);
	}

	public String getSexStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_sex", sex);
	}

	public String getEduStr() {
		return FormateUtil.getInstance().getNameByCode("g_um_edu_level", edu);
	}

	public String getIsBackUserStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_data_stauts",
				isBackUser);
	}

	public String getDataStatusStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_data_stauts",
				dataStatus);
	}
}