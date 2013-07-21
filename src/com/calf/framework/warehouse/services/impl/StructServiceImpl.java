package com.calf.framework.warehouse.services.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import com.calf.framework.util.FormateUtil;
import com.calf.framework.warehouse.entity.TbWhStruct;
import com.calf.framework.warehouse.qry.StructQry;
import com.calf.framework.warehouse.services.StructService;

@Service("structService")
public class StructServiceImpl extends BaseServiceImpl implements StructService{
	
	/**
	 * 获取对象
	 */
	public TbWhStruct get(Long structId) {
		return (TbWhStruct) hibernateDao.get(TbWhStruct.class, structId);
	}
	
	/**
	 * 查找分页信息
	 */
	public List findListByParent(Long parentId){
		List list = super.hibernateDao.find("from TbWhStruct t where t.parent.structId = ? order by orderNum asc",parentId);
		return list;
	}
	
	/**
	 * 查找菜单树形结构
	 * 
	 * @return
	 */
	public List findTree(StructQry qry) {
		Criteria criteria = super.hibernateDao.createCriteria(TbWhStruct.class);
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria, "treeNo", CriteriaUtils.ASC);
		return criteria.list();
	}
	
	/**
	 * 保存
	 **/
	public String add(TbWhStruct entity) {
		super.add(entity);		
		super.hibernateDao.flush();
		// 执行生成TREE_NO
		loopMain(entity.getParent().getStructId());
		return null;
	}
	
	public String saveStruct(TbWhStruct entity,boolean refreshTreeNo) {
		super.save(entity);		
		//判断下面是否有节点
		int cnt = super.hibernateDao.count("select count(*) from TbWhStruct t where t.parent.structId = ? and t.dataStatus = '1' ", entity.getStructId());
		//entity.setIsLeaf(cnt==0?Constants.YES:Constants.NO);
		super.hibernateDao.flush();
		
		if(refreshTreeNo){
			// 执行生成TREE_NO存储过程
			loopMain(entity.getParent().getStructId());
		}
		
		return null;
	}

	private void loopMain(Long parentId){
		TbWhStruct parent = super.hibernateDao.get(TbWhStruct.class,parentId);
		loopSub(parent.getStructId(), parent.getTreeNo());
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void loopSub(Long parentId,String treeNo){
		List<TbWhStruct> subList = (List<TbWhStruct>)super.hibernateDao.find("from TbWhStruct t where t.parent.structId = ? and t.dataStatus = '1' order by orderNum asc",parentId);
		for (int i = 0, len = subList.size(); i < len; i++) {
			String newTreeNo = treeNo+FormateUtil.getInstance().appendZero(i);
			TbWhStruct next = subList.get(i);
			next.setTreeNo(newTreeNo);
			save(next);
			loopSub(next.getStructId(),next.getTreeNo());
		}
	}
	
	/**
	 * 删除
	 */
	public String delete(TbWhStruct entity) {
		//判断能否进行删除
		if(this.isCanDelete(entity)){
			super.remove(entity);
			return "OK";//删除成功
		}else{
			return "ERR01";//删除失败，不是叶子节点，不能进行删除！
		}
	}
	
	/**
	 * 重建treeno编码
	 */
	public String saveRebuild(TbWhStruct entity) {
		rebuild(entity.getStructId(),entity.getTreeNo());
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void rebuild(Long parentId,String treeNo){
		List<TbWhStruct> subList = (List<TbWhStruct>)super.hibernateDao.find("from TbWhStruct t where t.parent.structId = ? and t.dataStatus = '1' order by orderNum asc",parentId);
		for (int i = 0, len = subList.size(); i < len; i++) {
			String newTreeNo = treeNo+FormateUtil.getInstance().appendZero(i);
			TbWhStruct next = subList.get(i);
			next.setTreeNo(newTreeNo);			
			int cnt = super.hibernateDao.count("select count(*) from TbWhStruct t where t.parent.structId = ? and t.dataStatus = '1' ", next.getStructId());
			//next.setIsLeaf(cnt==0?Constants.YES:Constants.NO);
			super.save(next);
			if(cnt>0){
				rebuild(next.getStructId(),next.getTreeNo());
			}
		}
	}
	
	/**
	 * 没有叶子节点可以删除
	 * @param entity
	 * @return
	 */
	private boolean isCanDelete(TbWhStruct entity){
		int cnt = super.hibernateDao.count("select count(*) from TbWhStruct t where t.parent = ? and t.dataStatus = '1' ", entity);
		return cnt==0;
	}
}