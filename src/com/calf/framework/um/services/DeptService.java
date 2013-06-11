package com.calf.framework.um.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.um.entity.TbSysDept;
import com.calf.framework.um.qry.DeptQry;
import com.calf.framework.vo.Page;

/**
 * 机构管理 service 接口
 **/
public interface DeptService extends BaseService {
	/**
	 * 获取对象
	 */
	public TbSysDept get(Long deptId);

	/**
	 * 新增
	 **/
	public String add(TbSysDept entity);

	/**
	 * 修改
	 **/
	public String update(TbSysDept entity);

	/**
	 * 删除
	 */
	public String delete(TbSysDept entity);

	/**
	 * 查找分页信息
	 */
	public Page findDeptPage(DeptQry qry);

	/**
	 * 查找机构树形结构
	 * 
	 * @return
	 */
	public List findDeptTree(DeptQry qry);

	public TbSysDept findParentByDeptLevel(TbSysDept dept, String level);
}