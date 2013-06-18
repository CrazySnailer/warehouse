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
	 * @param refreshTreeNo true 证明需要更新排序
	 **/
	public String update(TbSysMenu entity,boolean refreshTreeNo);
	/**
	 * 批量保存
	 * @return
	 */
	public String saveList(TbSysMenu[] entitys);
	/**
	 * 删除
	 */
	public String delete(TbSysMenu entity);
	/**
	 * 重建treeno编码
	 */
	public String saveRebuild(TbSysMenu entity);
	/**
	 * 根据上级查找子菜单
	 */
	public List findMenuListByParent(Long parentId);	
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