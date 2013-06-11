package com.calf.framework.um.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.um.entity.TbSysUser;
import com.calf.framework.um.qry.UserQry;
import com.calf.framework.vo.Page;

/**
 * 人员管理 service 接口
 **/
public interface UserService extends BaseService {
	/**
	 * 获取对象
	 */
	public TbSysUser get(Long userId);

	/**
	 * 新增
	 **/
	public String add(TbSysUser entity);

	/**
	 * 修改
	 **/
	public String update(TbSysUser entity);

	/**
	 * 删除
	 */
	public String delete(TbSysUser entity);

	/**
	 * 查找分页信息
	 */
	public Page findUserPage(UserQry qry);
	/**
	 * 用户登录验证
	 * @param userCode
	 * @param pwd
	 * @return
	 */
	public TbSysUser loginCheck(String userCode, String pwd);
	/**
	 * 根据用户查找角色列表
	 * @param userId
	 * @return
	 */
	public List findUserRoles(Long userId);
	/**
	 * 保存用户授权信息
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	public int saveAssignRoles(Long userId,Long[] roleIds,Long createUser);
	
	/**
	 * 查找用户菜单
	 * 根据角色的不同而查询不同菜单
	 * @param userId
	 * @param rootVal
	 * @return
	 */
	public List findUserMenu(Long userId,String rootId);
	
	/**
	 * 查找用户所拥有的资源
	 * @param userId
	 * @return
	 */
	public List findUserRes(Long userId);
}