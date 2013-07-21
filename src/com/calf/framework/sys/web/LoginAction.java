package com.calf.framework.sys.web;

import java.util.List;

import org.springframework.util.Assert;

import com.calf.framework.um.entity.TbSysDept;
import com.calf.framework.um.entity.TbSysUser;
import com.calf.framework.um.services.DeptService;
import com.calf.framework.um.services.UserService;
import com.calf.framework.util.Constants;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.web.BaseAction;

public class LoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -395303941044886507L;	
	/**
	 * 用户服务
	 */
	UserService userService;
	/**
	 * 机构服务
	 */
	DeptService deptService;
	/**
	 * 用户名称
	 */
	String userCode;
	/**
	 * 密码
	 */
	String pwd;
	/**
	 * 登录类型
	 */
	String loginType;
	
	/**
	 * 登录操作
	 * @return
	 */
	public String login(){
		Assert.notNull(this.loginType);//loginType不能为空
		Assert.notNull(this.userCode);
		Assert.notNull(this.pwd);
		
		TbSysUser user = userService.loginCheck(userCode, pwd);
		if(user!=null){
			//获取用户可操作菜单和资源
			List userMenuList = this.userService.findUserMenu(user.getUserId(), this.loginType);
			List userResList = this.userService.findUserRes(user.getUserId());
			
			//创建session
			AdminUserInfo userInfo = new AdminUserInfo();
			userInfo.setUser(user);
			userInfo.setDept(deptService.get(user.getDept().getDeptId()));
			userInfo.setMenuList(userMenuList);
			userInfo.setResList(userResList);
			
			//初始化数据权限，查询附加信息,主要用于数据权限统一过滤
			/*if(userInfo.findResources(Constants.DATA_PRIV_ALL)){
				userInfo.setDataRanage("PRIV_ALL");
			}else if(userInfo.findResources(Constants.DATA_PRIV_SELF_AND_BELOW_DEPT)){
				userInfo.setDataRanage("PRIV_SELF_BELOW_DEPT");
			}else if(userInfo.findResources(Constants.DATA_PRIV_SELF_DEPT)){
				userInfo.setDataRanage("PRIV_SELF_DEPT");
			}else if(userInfo.findResources(Constants.DATA_PRIV_SELF)){
				userInfo.setDataRanage("PRIV_SELF");
			}*/
			
			getRequest().getSession().setAttribute(Constants.ADMIN_SESSION_USER_INFO,userInfo);
		}else{
			addActionError("登录失败，用户名不存在或密码错误！");
			return LOGIN;
		}
		
		return "success";
	}
	
	/**
	 * 登出操作
	 * @return
	 */
	public String logout(){
		super.getSession().invalidate();
		return LOGIN;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	
}
