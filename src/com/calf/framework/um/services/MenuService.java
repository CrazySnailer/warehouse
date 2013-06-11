package com.calf.framework.um.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.um.entity.TbSysMenu;
import com.calf.framework.um.qry.MenuQry;
import com.calf.framework.vo.Page;

/**
 * 菜单管理 service 接口
 **/
public interface MenuService extends BaseService {
	/**
	 * 获取对象
	 */
	public TbSysMenu get(Long menuId);

	/**
	 * 新增
	 **/
	public String add(TbSysMenu entity);

	/**
	 * 修改
	 **/
	public String update(TbSysMenu entity);

	/**
	 * 删除
	 */
	public String delete(TbSysMenu entity);

	/**
	 * 查找分页信息
	 */
	public Page findMenuPage(MenuQry qry);
	
	/**
	 * 查找菜单树形结构
	 * @return
	 */
	public List findMenuTree(MenuQry qry);
	
	/**
	 * 查找已经选中的菜单
	 */
	public List findChkedMenu(Long roleId);
}