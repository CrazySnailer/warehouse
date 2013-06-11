package com.calf.framework.um.services.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.um.entity.TbSysRole;
import com.calf.framework.um.entity.TbSysRolePriv;
import com.calf.framework.um.qry.RoleQry;
import com.calf.framework.um.services.MenuService;
import com.calf.framework.um.services.RoleService;
import com.calf.framework.util.Constants;
import com.calf.framework.vo.Page;

/**
 * 角色管理 service 实现
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
	@Resource
	MenuService menuService;

	/**
	 * 查找分页信息
	 */
	public Page findRolePage(RoleQry qry) {
		Criteria criteria = super.hibernateDao.createCriteria(TbSysRole.class);
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria, "roleName", CriteriaUtils.ASC);
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(),
				qry.getPageSize());
	}

	/**
	 * 获取对象
	 */
	public TbSysRole get(Long roleId) {
		return (TbSysRole) super.get(TbSysRole.class, roleId);
	}

	/**
	 * 保存
	 **/
	public String add(TbSysRole entity) {
		super.add(entity);
		return null;
	}

	public String update(TbSysRole entity) {
		super.save(entity);
		return null;
	}

	/**
	 * 删除
	 */
	public String delete(TbSysRole entity) {
		//super.remove(entity);
		entity.setDataStatus(Constants.NO);
		super.save(entity);
		return null;
	}

	/**
	 * 保存角色授权信息
	 * 
	 * @param roleId
	 *            角色
	 * @param chkIds
	 *            已选中的菜单
	 * @param operUserId
	 *            操作用户ID
	 * @return
	 */
	@Override
	public List saveRolePriv(Long roleId, String[] chkIds, Long operUserId) {
		super.hibernateDao.bulkUpdate(
				"delete from TbSysRolePriv t where t.relType = 'R' and t.roleId = ? ",
				roleId);

		if (chkIds != null) {
			for (int i = 0, len = chkIds.length; i < len; i++) {
				TbSysRolePriv rel = new TbSysRolePriv();
				rel.setRelType(TbSysRolePriv.ROLE_TYPE_ROLE);
				rel.setRoleId(roleId);
				rel.setMenu(this.menuService.get(Long.parseLong(chkIds[i])));
				rel.setCreateDate(new Date());
				rel.setCreateUser(operUserId);
				super.add(rel);
			}
		}

		return null;
	}

	@Override
	public List findRolesByLevel(String level) {
		return super.hibernateDao.find(" from TbSysRole t where t.roleLevel = ?", level);
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	
}