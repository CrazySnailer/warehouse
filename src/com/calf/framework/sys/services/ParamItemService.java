package com.calf.framework.sys.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.um.entity.TbSysParamItem;
import com.calf.framework.um.qry.ParamItemQry;
import com.calf.framework.vo.Page;

/**
 * 参数设置 service 接口
 **/
public interface ParamItemService extends BaseService {
	/**
	 * 获取对象
	 */
	public TbSysParamItem get(Long recId);

	/**
	 * 新增
	 **/
	public String add(TbSysParamItem entity);

	/**
	 * 修改
	 **/
	public String update(TbSysParamItem entity);

	/**
	 * 删除
	 */
	public String delete(TbSysParamItem entity);

	/**
	 * 查找分页信息
	 */
	public Page findParamItemPage(ParamItemQry qry);
	/**
	 * 查找所有参数
	 * @return
	 */
	public List<TbSysParamItem> findAllParamItem();
}