package com.calf.framework.um.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.um.entity.TbSysDept;
import com.calf.framework.um.qry.DeptQry;
import com.calf.framework.um.services.DeptService;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;

public class DeptAction extends BaseAction {
	/**
	 * 
	 */
	DeptService deptService;
	/**
	 * 查询对象
	 */
	DeptQry qry;
	/**
	 * 分页对象
	 **/
	Page page;
	/**
	 * 实体类
	 */
	TbSysDept entity;
	/**
	 * list 对象
	 */
	List list;
	
	@RequiresPermissions(value = "um:dept:view",requiresUser=true)
	public String index()throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		return "index";
	}
	
	/**
	 * 用于机构维护
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = "um:dept:view",requiresUser=true)
	public String tree()throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		
		if (qry == null) {
			qry = new DeptQry();
		}
		qry.setUserInfo(userInfo);
		
		list = this.deptService.findDeptTree(qry);
		return "tree";
	}
	
	/**
	 * 用于其他信息维护
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = "um:dept:view",requiresUser=true)
	public String deptTree()throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		String uri = super.getStringFromRequest("uri", false);
		if (qry == null) {
			qry = new DeptQry();
		}
		qry.setUserInfo(userInfo);
		
		list = this.deptService.findDeptTree(qry);
		return uri;
	}
	
	/**
	 * 列表
	 */
	@RequiresPermissions(value = "um:dept:view",requiresUser=true)
	public String list() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if (StringUtils.isNotBlank(super.qryHex)) {
			qry = (DeptQry) ObjectUtils.getObjectFromHex(qryHex);
		}
		if (qry == null) {
			qry = new DeptQry();
		}
		qry.setUserInfo(userInfo);
		page = deptService.findDeptPage(qry);
		return "list";
	}

	/**
	 * 新增
	 **/
	@RequiresPermissions(value = "um:dept:edit",requiresUser=true)
	public String toAdd() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = new TbSysDept();
		super.event = "ADD";
		super.title = "新增机构管理";
		return "edit";
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "um:dept:edit",requiresUser=true)
	public String toEdit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = deptService.get(entity.getDeptId());
		super.event = "EDIT";
		super.title = "修改机构管理";
		return "edit";
	}

	/**
	 * 修改动作
	 **/
	@RequiresPermissions(value = "um:dept:edit",requiresUser=true)
	public String edit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if ("ADD".equals(super.event)) {
			// 新增操作
			entity.setDeptId(null);
			entity.setDataStatus("1");
			entity.setCreateUser(userInfo.getUserId());
			entity.setCreateDate(new Date());
			deptService.add(entity);
			super.saveMessage("机构管理新增成功");
		} else if ("EDIT".equals(super.event)) {
			// 修改操作
			TbSysDept db = deptService.get(entity.getDeptId());

			db.setDeptCode(entity.getDeptCode());
			db.setAreaCode(entity.getAreaCode());
			db.setParentDeptId(entity.getParentDeptId());
			db.setDeptName(entity.getDeptName());
			db.setDeptKind(entity.getDeptKind());
			db.setDeptLevel(entity.getDeptLevel());
			db.setIsLeaf(entity.getIsLeaf());
			db.setFullName(entity.getFullName());
			db.setOrderNum(entity.getOrderNum());
			db.setLinkMan(entity.getLinkMan());
			db.setLinkTel(entity.getLinkTel());
			db.setLinkAddr(entity.getLinkAddr());
			db.setLinkEmail(entity.getLinkEmail());
			db.setNote(entity.getNote());

			entity.setUpdateUser(userInfo.getUserId());
			entity.setUpdateDate(new Date());

			deptService.update(db);
			super.saveMessage("机构管理修改成功");
			super.addAttribute("qryHex", super.qryHex);
		}
		super.redirectUrl = "/admin/um/dept_list.action";
		return super.GLOBAL_SUCCESS;
	}

	/**
	 * 删除
	 **/
	@RequiresPermissions(value = "um:dept:edit",requiresUser=true)
	public String delete() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = deptService.get(entity.getDeptId());
		this.deptService.delete(entity);
		super.renderJsonSuccess("删除成功!");
		return null;
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "um:dept:view",requiresUser=true)
	public String toView() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = deptService.get(entity.getDeptId());
		super.title = "机构管理详细信息";
		return "view";
	}

	public DeptQry getQry() {
		return this.qry;
	}

	public void setQry(DeptQry qry) {
		this.qry = qry;
	}

	public DeptService getDeptService() {
		return this.deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public TbSysDept getEntity() {
		return entity;
	}

	public void setEntity(TbSysDept entity) {
		this.entity = entity;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}