package com.calf.framework.um.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.calf.framework.um.entity.TbSysDept;
import com.calf.framework.um.entity.TbSysUser;
import com.calf.framework.um.qry.UserQry;
import com.calf.framework.um.services.DeptService;
import com.calf.framework.um.services.RoleService;
import com.calf.framework.um.services.UserService;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;

public class UserAction extends BaseAction {
	/**
	 * 
	 */
	UserService userService;
	/**
	 * 
	 */
	DeptService deptService;
	
	RoleService roleService;
	/**
	 * 查询对象
	 */
	UserQry qry;
	/**
	 * 分页对象
	 **/
	Page page;
	
	List list;
	/**
	 * 实体类
	 */
	TbSysUser entity;
	/**
	 * 角色ID
	 */
	Long[] roleIds;
	
	/**
	 * 首页
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = "um:user:view",requiresUser=true)
	public String index()throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		return "index";
	}

	/**
	 * 列表
	 */
	@RequiresPermissions(value = "um:user:view",requiresUser=true)
	public String list() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if (StringUtils.isNotBlank(super.qryHex)) {
			qry = (UserQry) ObjectUtils.getObjectFromHex(qryHex);
		}
		if (qry == null) {
			qry = new UserQry();
		}
		
		if(qry.getDeptId()!=null){
			TbSysDept dept = this.deptService.get(qry.getDeptId());
			qry.setDeptName(dept.getDeptName());
		}
		
		qry.setUserInfo(userInfo);
		page = userService.findUserPage(qry);
		return "list";
	}

	/**
	 * 新增
	 **/
	@RequiresPermissions(value = "um:user:edit",requiresUser=true)
	public String toAdd() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		
		if(entity.getDept()!=null&&entity.getDept().getDeptId()!=null){
			TbSysDept dept = this.deptService.get(entity.getDept().getDeptId());
			entity.setDept(dept);
		}
		
		super.event = "ADD";
		super.title = "新增人员";
		return "edit";
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "um:user:edit",requiresUser=true)
	public String toEdit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = userService.get(entity.getUserId());
		super.event = "EDIT";
		super.title = "修改人员";
		return "edit";
	}

	/**
	 * 修改动作
	 **/
	@RequiresPermissions(value = "um:user:edit",requiresUser=true)
	public String edit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if ("ADD".equals(super.event)) {
			// 新增操作
			entity.setUserId(null);
			entity.setDataStatus("1");
			entity.setCreateUser(userInfo.getUserId());
			entity.setCreateDate(new Date());
			userService.add(entity);
			super.saveMessage("人员新增成功");
		} else if ("EDIT".equals(super.event)) {
			// 修改操作
			TbSysUser db = userService.get(entity.getUserId());

			db.setUserId(entity.getUserId());
			db.setUserCode(entity.getUserCode());
			db.setUserName(entity.getUserName());
			db.setLoginPwd(entity.getLoginPwd());
			db.setIsAdmin(entity.getIsAdmin());
			db.setDept(this.deptService.get(entity.getDept().getDeptId()));
			db.setSex(entity.getSex());
			db.setSchool(entity.getSchool());
			db.setBirthday(entity.getBirthday());
			db.setMarry(entity.getMarry());
			db.setJoinDate(entity.getJoinDate());
			db.setEmail(entity.getEmail());
			db.setTel(entity.getTel());
			db.setMobile(entity.getMobile());
			db.setNote(entity.getNote());

			db.setUpdateUser(userInfo.getUserId());
			db.setUpdateDate(new Date());

			userService.update(db);
			super.saveMessage("人员修改成功");
			super.addAttribute("qryHex", super.qryHex);
		}
		super.redirectUrl = "/admin/um/teacher_list.action";
		return super.GLOBAL_SUCCESS;
	}

	/**
	 * 删除
	 **/
	@RequiresPermissions(value = "um:user:edit",requiresUser=true)
	public String delete() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = userService.get(entity.getUserId());
		this.userService.delete(entity);
		super.renderJsonSuccess("删除成功!");
		return null;
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "um:user:view",requiresUser=true)
	public String toView() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = userService.get(entity.getUserId());
		super.title = "人员管理详细信息";
		return "view";
	}
	
	/**
	 * 分配角色
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = "um:user:edit",requiresUser=true)
	public String toAssignRole()throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		Assert.notNull(entity.getUserId());
		entity = this.userService.get(entity.getUserId());
		TbSysDept dept = this.deptService.get(entity.getDept().getDeptId());
		
		//已授权的角色
		List existList = this.userService.findUserRoles(entity.getUserId());
		super.getRequest().setAttribute("existlist", existList);
		
		//可授权的角色
		List roleList = this.roleService.findRolesByLevel(dept.getDeptLevel());
		super.getRequest().setAttribute("roleList", roleList);
		
		return "assignRole";
	}
	
	/**
	 * 保存角色分配信息
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = "um:user:edit",requiresUser=true)
	public String assignRole()throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		Assert.notNull(entity.getUserId());
		this.userService.saveAssignRoles(entity.getUserId(), roleIds, userInfo.getUserId());
		
		super.saveMessage("角色授权保存成功!");
		super.redirectUrl = "/admin/um/user_toAssignRole.action?entity.userId="+entity.getUserId()+"&data="+Math.random();
		return super.GLOBAL_SUCCESS;
	}

	public UserQry getQry() {
		return this.qry;
	}

	public void setQry(UserQry qry) {
		this.qry = qry;
	}

	public UserService getUserService() {
		return this.userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public TbSysUser getEntity() {
		return entity;
	}

	public void setEntity(TbSysUser entity) {
		this.entity = entity;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	
}