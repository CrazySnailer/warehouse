package com.calf.framework.sys.services.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.sys.services.ParamItemService;
import com.calf.framework.um.entity.TbSysParamItem;
import com.calf.framework.um.qry.ParamItemQry;
import com.calf.framework.util.Constants;
import com.calf.framework.vo.Page;

/**
 * 参数设置 service 实现
 */
@Service("paramItemService")
public class ParamItemServiceImpl extends BaseServiceImpl implements
		ParamItemService {

	/**
	 * 查找分页信息
	 */
	public Page findParamItemPage(ParamItemQry qry) {
		Criteria criteria = super.hibernateDao.createCriteria(TbSysParamItem.class);
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addEq(criteria, "paramKindCode", qry.getParamKindCode());
		CriteriaUtils.addEq(criteria, "paramCode", qry.getParamCode());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}

	/**
	 * 获取对象
	 */
	public TbSysParamItem get(Long recId) {
		return (TbSysParamItem) super.get(TbSysParamItem.class, recId);
	}

	/**
	 * 保存
	 **/
	public String add(TbSysParamItem entity) {
		super.add(entity);
		return null;
	}

	public String update(TbSysParamItem entity) {
		super.save(entity);
		return null;
	}

	/**
	 * 删除
	 */
	public String delete(TbSysParamItem entity) {
		super.remove(entity);
		return null;
	}
	
	/**
	 * 查找所有参数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbSysParamItem> findAllParamItem() {
		return (List<TbSysParamItem>)hibernateDao.find("from TbSysParamItem");
	}
}