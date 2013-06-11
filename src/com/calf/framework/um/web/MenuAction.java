package com.calf.framework.um.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.calf.framework.um.entity.TbSysMenu;
import com.calf.framework.um.qry.MenuQry;
import com.calf.framework.um.services.MenuService;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;

public class MenuAction extends BaseAction {
	/**
	 * 
	 */
	MenuService menuService;
	/**
	 * 查询对象
	 */
	MenuQry qry;
	/**
	 * 分页对象
	 **/
	Page page;
	/**
	 * 列表对象
	 */
	List list;
	/**
	 * 
	 */
	List chkMenu;
	/**
	 * 实体类
	 */
	TbSysMenu entity;
	
	@RequiresPermissions(value = "um:menu:view",requiresUser=true)
	public String index()throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		return "index";
	}
	
	@RequiresPermissions(value = "um:menu:view",requiresUser=true)
	public String tree()throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		
		if (qry == null) {
			qry = new MenuQry();
		}
		qry.setUserInfo(userInfo);
		
		list = this.menuService.findMenuTree(qry);
		return "tree";
	}
	
	@RequiresPermissions(value = "um:menu:view",requiresUser=true)
	public String select()throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		
		if (qry == null) {
			qry = new MenuQry();
		}
		qry.setUserInfo(userInfo);
		
		list = this.menuService.findMenuTree(qry);
		return "select";
	}
	
	/**
	 * 列表
	 */
	@RequiresPermissions(value = "um:menu:view",requiresUser=true)
	public String list() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if (StringUtils.isNotBlank(super.qryHex)) {
			qry = (MenuQry) ObjectUtils.getObjectFromHex(qryHex);
		}
		if (qry == null) {
			qry = new MenuQry();
		}
		qry.setUserInfo(userInfo);
		page = menuService.findMenuPage(qry);
		return "list";
	}

	/**
	 * 新增
	 **/
	@RequiresPermissions(value = "um:menu:edit",requiresUser=true)
	public String toAdd() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		Assert.notNull(entity.getParentId());//父菜单不能为空
		entity.setParent(this.menuService.get(entity.getParentId()));
		super.event = "ADD";
		super.title = "新增菜单";
		return "edit";
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "um:menu:edit",requiresUser=true)
	public String toEdit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = menuService.get(entity.getMenuId());
		super.event = "EDIT";
		super.title = "修改菜单";
		return "edit";
	}

	/**
	 * 修改动作
	 **/
	@RequiresPermissions(value = "um:menu:edit",requiresUser=true)
	public String edit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if ("ADD".equals(super.event)) {
			// 新增操作
			entity.setMenuId(null);
			entity.setParent(this.menuService.get(entity.getParentId()));
			entity.setDataStatus("1");
			entity.setCreateUser(userInfo.getUserId());
			entity.setCreateDate(new Date());
			entity.setNeedBuy("0");
			menuService.add(entity);
			super.saveMessage("菜单管理新增成功");
			super.addAttribute("refresh", "true");
			super.redirectUrl = "/admin/um/menu_toEdit.action?entity.menuId="+entity.getMenuId();
		} else if ("EDIT".equals(super.event)) {
			// 修改操作
			TbSysMenu db = menuService.get(entity.getMenuId());

			db.setParent(this.menuService.get(entity.getParentId()));
			db.setName(entity.getName());
			db.setUrl(entity.getUrl());
			db.setIdVal(entity.getIdVal());
			db.setIsLeaf(entity.getIsLeaf());
			db.setOrderNum(entity.getOrderNum());
			db.setMenuType(entity.getMenuType());
			
			db.setUpdateUser(userInfo.getUserId());
			db.setUpdateDate(new Date());

			menuService.update(db);
			super.saveMessage("菜单管理修改成功");
			super.addAttribute("refresh", "true");
			super.redirectUrl = "/admin/um/menu_toEdit.action?entity.menuId="+entity.getMenuId();
		}
		super.addAttribute("refresh", "true");
		return super.GLOBAL_SUCCESS;
	}

	/**
	 * 删除
	 **/
	@RequiresPermissions(value = "um:menu:edit",requiresUser=true)
	public String delete() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = menuService.get(entity.getMenuId());
		Long parentId = entity.getParent().getMenuId();
		String result = this.menuService.delete(entity);
		if("OK".equals(result)){
			super.saveMessage("菜单管理删除成功");
			super.redirectUrl = "/admin/um/menu_toEdit.action?entity.menuId="+parentId;
			super.addAttribute("refresh", "true");
			return super.GLOBAL_SUCCESS;
		}else if("ERR01".equals(result)){
			super.saveMessage("删除失败，不是叶子节点，不能进行删除,请先删除叶子节点！");
			return super.GLOBAL_ERROR;
		}else{
			super.saveMessage("未知错误!");
			return super.GLOBAL_ERROR;
		}
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "um:menu:view",requiresUser=true)
	public String toView() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = menuService.get(entity.getMenuId());
		super.title = "菜单管理详细信息";
		return "view";
	}
	
	
	@RequiresPermissions(value = "um:menu:view",requiresUser=true)
	public String chkTree()throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		Long roleId = super.getLongFromRequest("roleId", false);
		list = this.menuService.findMenuTree(qry);		
		chkMenu = this.menuService.findChkedMenu(roleId);
		if(CollectionUtils.isNotEmpty(chkMenu)){
			this.fillCheck();
		}
		return "chkTree";
	}
	
	/**
	 * 填充树形结构
	 */
	private void fillCheck(){
		for(int i = 0,len = list.size();i<len;i++){
			TbSysMenu tree = (TbSysMenu)list.get(i);
			if(idInChkMenu(tree.getMenuId())){
				tree.setCheck(true);
			}
		}
	}
	
	/**
	 * ID是否在chkMenu中出现
	 */
	private boolean idInChkMenu(Long treeId){
		for(int i = 0,len = chkMenu.size();i<len;i++){
			TbSysMenu menu = (TbSysMenu)chkMenu.get(i);
			if(menu.getMenuId().longValue()==treeId.longValue()){
				return true;
			}			
		}
		return false;
	}

	public MenuQry getQry() {
		return this.qry;
	}

	public void setQry(MenuQry qry) {
		this.qry = qry;
	}

	public MenuService getMenuService() {
		return this.menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public TbSysMenu getEntity() {
		return entity;
	}

	public void setEntity(TbSysMenu entity) {
		this.entity = entity;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getChkMenu() {
		return chkMenu;
	}

	public void setChkMenu(List chkMenu) {
		this.chkMenu = chkMenu;
	}
	
}