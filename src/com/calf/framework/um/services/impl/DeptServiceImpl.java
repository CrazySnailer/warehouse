package com.calf.framework.um.services.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.um.entity.TbSysDept;
import com.calf.framework.um.qry.DeptQry;
import com.calf.framework.um.services.DeptService;
import com.calf.framework.util.Constants;
import com.calf.framework.vo.Page;

@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl implements DeptService {

	/**
	 * 查找分页信息
	 */
	public Page findDeptPage(DeptQry qry) {
		Criteria criteria = super.hibernateDao.createCriteria(TbSysDept.class);
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}

	/**
	 * 获取对象
	 */
	public TbSysDept get(Long deptId) {
		return (TbSysDept) hibernateDao.get(TbSysDept.class, deptId);
	}

	/**
	 * 保存
	 **/
	public String add(TbSysDept entity) {
		hibernateDao.add(entity);
		return null;
	}

	public String update(TbSysDept entity) {
		hibernateDao.save(entity);
		return null;
	}

	/**
	 * 删除
	 */
	public String delete(TbSysDept entity) {
		hibernateDao.remove(entity);
		return null;
	}

	/**
	 * 查找机构树形结构
	 * 
	 * @return
	 */
	@Override
	public List findDeptTree(DeptQry qry) {
		Criteria criteria = super.hibernateDao.createCriteria(TbSysDept.class);
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria, "treeNo", CriteriaUtils.ASC);
		return criteria.list();
	}

	/**
	 * 使用递归获取指定的上级机构
	 */
	@Override
	public TbSysDept findParentByDeptLevel(TbSysDept dept, String level) {
		if (level.equals(dept.getDeptLevel())) {
			return dept;
		} else {
			if (dept.getParentDeptId() != null) {
				TbSysDept parent = this.get(dept.getParentDeptId());
				return findParentByDeptLevel(parent, level);
			} else {
				return null;
			}
		}
	}
}