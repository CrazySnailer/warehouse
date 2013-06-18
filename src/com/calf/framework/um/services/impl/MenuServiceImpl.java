package com.calf.framework.um.services.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.um.entity.TbSysMenu;
import com.calf.framework.um.qry.MenuQry;
import com.calf.framework.um.services.MenuService;
import com.calf.framework.util.Constants;
import com.calf.framework.util.FormateUtil;
import com.calf.framework.vo.Page;

/**
 * 菜单管理 service 实现
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

	/**
	 * 根据上级查找子菜单
	 */
	public List findMenuListByParent(Long parentId) {
		List list = super.hibernateDao.find("from TbSysMenu t where t.parent.menuId = ? order by orderNum asc",parentId);
		list.add(0, super.get(TbSysMenu.class, parentId));
		return list;
	}

	/**
	 * 查找菜单树形结构
	 * 
	 * @return
	 */
	public List findMenuTree(MenuQry qry) {
		Criteria criteria = super.hibernateDao.createCriteria(TbSysMenu.class);
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria, "treeNo", CriteriaUtils.ASC);
		return criteria.list();
	}

	/**
	 * 获取对象
	 */
	public TbSysMenu get(Long menuId) {
		return (TbSysMenu) hibernateDao.get(TbSysMenu.class, menuId);
	}

	/**
	 * 保存
	 **/
	public String add(TbSysMenu entity) {
		super.add(entity);		
		super.hibernateDao.flush();
		// 执行生成TREE_NO
		loopMain(entity.getParent().getMenuId());
		return null;
	}

	public String update(TbSysMenu entity,boolean refreshTreeNo) {
		super.save(entity);		
		//判断下面是否有节点
		int cnt = super.hibernateDao.count("select count(*) from TbSysMenu t where t.parent.menuId = ? and t.dataStatus = '1' ", entity.getMenuId());
		entity.setIsLeaf(cnt==0?Constants.YES:Constants.NO);
		super.hibernateDao.flush();
		
		if(refreshTreeNo){
			// 执行生成TREE_NO存储过程
			loopMain(entity.getParent().getMenuId());
		}
		
		return null;
	}
	
	public String saveList(TbSysMenu[] entitys) {
		for(TbSysMenu entity:entitys){
			TbSysMenu db = (TbSysMenu)super.hibernateDao.get(TbSysMenu.class, entity.getMenuId());
			boolean refreshTreeNo = db.getOrderNum().longValue()!=entity.getOrderNum().longValue();
			db.setOrderNum(entity.getOrderNum());
			update(db, refreshTreeNo);
		}
		return null;
	}

	private void loopMain(Long parentId){
		TbSysMenu parent = super.hibernateDao.get(TbSysMenu.class,parentId);
		loopSub(parent.getMenuId(), parent.getTreeNo());
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void loopSub(Long parentId,String treeNo){
		List<TbSysMenu> subList = (List<TbSysMenu>)super.hibernateDao.find("from TbSysMenu t where t.parent.menuId = ? and t.dataStatus = '1' order by orderNum asc",parentId);
		for (int i = 0, len = subList.size(); i < len; i++) {
			String newTreeNo = treeNo+FormateUtil.getInstance().appendZero(i);
			TbSysMenu next = subList.get(i);
			next.setTreeNo(newTreeNo);
			save(next);
			loopSub(next.getMenuId(),next.getTreeNo());			
		}
	}

	/**
	 * 删除
	 */
	public String delete(TbSysMenu entity) {
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
	public String saveRebuild(TbSysMenu entity) {
		rebuild(entity.getMenuId(),entity.getTreeNo());
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void rebuild(Long parentId,String treeNo){
		List<TbSysMenu> subList = (List<TbSysMenu>)super.hibernateDao.find("from TbSysMenu t where t.parent.menuId = ? and t.dataStatus = '1' order by orderNum asc",parentId);
		for (int i = 0, len = subList.size(); i < len; i++) {
			String newTreeNo = treeNo+FormateUtil.getInstance().appendZero(i);
			TbSysMenu next = subList.get(i);
			next.setTreeNo(newTreeNo);			
			int cnt = super.hibernateDao.count("select count(*) from TbSysMenu t where t.parent.menuId = ? and t.dataStatus = '1' ", next.getMenuId());
			next.setIsLeaf(cnt==0?Constants.YES:Constants.NO);
			super.save(next);
			if(cnt>0){
				rebuild(next.getMenuId(),next.getTreeNo());
			}
		}
	}

	/**
	 * 没有叶子节点可以删除
	 * @param entity
	 * @return
	 */
	private boolean isCanDelete(TbSysMenu entity){
		int cnt = super.hibernateDao.count("select count(*) from TbSysMenu t where t.parent = ? and t.dataStatus = '1' ", entity);
		return cnt==0;
	}

	/**
	 * 查找已经被选中的菜单
	 */
	public List findChkedMenu(Long roleId) {
		return super.hibernateDao
				.find("select m from TbSysRolePriv t join t.menu as m where t.relType = 'R' and t.roleId = ? ",
						roleId);
	}
}