package com.calf.framework.um.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.um.entity.TbSysRole;
import com.calf.framework.um.qry.RoleQry;
import com.calf.framework.vo.Page;

/**
 * 角色管理 service 接口
 **/
public interface RoleService extends BaseService {
	/**
	 * 获取对象
	 */
	public TbSysRole get(Long roleId);

	/**
	 * 新增
	 **/
	public String add(TbSysRole entity);

	/**
	 * 修改
	 **/
	public String update(TbSysRole entity);

	/**
	 * 删除
	 */
	public String delete(TbSysRole entity);

	/**
	 * 查找分页信息
	 */
	public Page findRolePage(RoleQry qry);
	
	/**
	 * 保存角色授权信息
	 * @param roleId 角色
	 * @param chkIds 已选中的菜单
	 * @param operUserId 操作用户ID
	 * @return
	 */
	public List saveRolePriv(Long roleId,String[] chkIds,Long operUserId);
	
	/**
	 * 根据级别查询角色
	 * @param level
	 * @return
	 */
	public List findRolesByLevel(String level);
	
}