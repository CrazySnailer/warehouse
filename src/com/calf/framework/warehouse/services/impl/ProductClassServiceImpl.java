package com.calf.framework.warehouse.services.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import com.calf.framework.warehouse.entity.TbWhProductClass;
import com.calf.framework.warehouse.qry.ProductClassQry;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.services.ProductClassService;
import com.calf.framework.util.FormateUtil;

@Service("productClassService")
public class ProductClassServiceImpl extends BaseServiceImpl implements ProductClassService{
	
	/**
	 * 获取对象
	 */
	public TbWhProductClass get(Long classId) {
		return (TbWhProductClass) hibernateDao.get(TbWhProductClass.class, classId);
	}
	
	/**
	 * 查找分页信息
	 */
	public List findListByParent(Long parentId){
		List list = super.hibernateDao.find("from TbWhProductClass t where t.parent.classId = ? order by orderNum asc",parentId);
		return list;
	}
	
	/**
	 * 查找菜单树形结构
	 * 
	 * @return
	 */
	public List findTree(ProductClassQry qry) {
		Criteria criteria = super.hibernateDao.createCriteria(TbWhProductClass.class);
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria, "treeNo", CriteriaUtils.ASC);
		return criteria.list();
	}
	
	/**
	 * 保存
	 **/
	public String add(TbWhProductClass entity) {
		super.add(entity);		
		super.hibernateDao.flush();
		// 执行生成TREE_NO
		loopMain(entity.getParent().getClassId());
		return null;
	}
	
	public String saveProductClass(TbWhProductClass entity,boolean refreshTreeNo) {
		super.save(entity);		
		//判断下面是否有节点
		int cnt = super.hibernateDao.count("select count(*) from TbWhProductClass t where t.parent.classId = ? and t.dataStatus = '1' ", entity.getClassId());
		entity.setIsLeaf(cnt==0?Constants.YES:Constants.NO);
		super.hibernateDao.flush();
		
		if(refreshTreeNo){
			// 执行生成TREE_NO存储过程
			loopMain(entity.getParent().getClassId());
		}
		
		return null;
	}

	private void loopMain(Long parentId){
		TbWhProductClass parent = super.hibernateDao.get(TbWhProductClass.class,parentId);
		loopSub(parent.getClassId(), parent.getTreeNo());
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void loopSub(Long parentId,String treeNo){
		List<TbWhProductClass> subList = (List<TbWhProductClass>)super.hibernateDao.find("from TbWhProductClass t where t.parent.classId = ? and t.dataStatus = '1' order by orderNum asc",parentId);
		for (int i = 0, len = subList.size(); i < len; i++) {
			String newTreeNo = treeNo+FormateUtil.getInstance().appendZero(i);
			TbWhProductClass next = subList.get(i);
			next.setTreeNo(newTreeNo);
			save(next);
			loopSub(next.getClassId(),next.getTreeNo());
		}
	}
	
	/**
	 * 删除
	 */
	public String delete(TbWhProductClass entity) {
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
	public String saveRebuild(TbWhProductClass entity) {
		rebuild(entity.getClassId(),entity.getTreeNo());
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void rebuild(Long parentId,String treeNo){
		List<TbWhProductClass> subList = (List<TbWhProductClass>)super.hibernateDao.find("from TbWhProductClass t where t.parent.classId = ? and t.dataStatus = '1' order by orderNum asc",parentId);
		for (int i = 0, len = subList.size(); i < len; i++) {
			String newTreeNo = treeNo+FormateUtil.getInstance().appendZero(i);
			TbWhProductClass next = subList.get(i);
			next.setTreeNo(newTreeNo);			
			int cnt = super.hibernateDao.count("select count(*) from TbWhProductClass t where t.parent.classId = ? and t.dataStatus = '1' ", next.getClassId());
			next.setIsLeaf(cnt==0?Constants.YES:Constants.NO);
			super.save(next);
			if(cnt>0){
				rebuild(next.getClassId(),next.getTreeNo());
			}
		}
	}
	
	/**
	 * 没有叶子节点可以删除
	 * @param entity
	 * @return
	 */
	private boolean isCanDelete(TbWhProductClass entity){
		int cnt = super.hibernateDao.count("select count(*) from TbWhProductClass t where t.parent = ? and t.dataStatus = '1' ", entity);
		return cnt==0;
	}
}