package com.calf.framework.sys.services;

import com.calf.framework.services.BaseService;
import com.calf.framework.um.entity.TbSysParamKind;
import com.calf.framework.um.qry.ParamKindQry;
import com.calf.framework.vo.Page;

/**
 * 调拨下发 service interface
 **/
public interface ParamKindService extends BaseService{
	/**
	 * 获取对象
	 */
	public TbSysParamKind get(String kindCode);
	/**
	 * 新增
	 **/
	public TbSysParamKind add(TbSysParamKind entity);
	/**
	 * 修改 
	 **/
	public String update(TbSysParamKind entity);
	/**
	 * 删除
	 */
	public String delete(TbSysParamKind entity);
	/**
	 * 查找分页信息
	 */
	public Page findParamKindPage(ParamKindQry qry);
}