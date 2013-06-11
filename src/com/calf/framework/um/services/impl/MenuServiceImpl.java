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
	 * 查找分页信息
	 */
	public Page findMenuPage(MenuQry qry) {
		/*
		 * return hibernateDao.pagedQuery("page_select", qry, qry.getPageNo(),
		 * qry.getPageSize());
		 */
		return null;
	}

	/**
	 * 查找菜单树形结构
	 * 
	 * @return
	 */
	@Override
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

	public String update(TbSysMenu entity) {
		super.save(entity);		
		super.hibernateDao.flush();
		// 执行生成TREE_NO存储过程
		loopMain(entity.getParent().getMenuId());
		return null;
	}
	
	private void loopMain(Long parentId){
		TbSysMenu parent = super.hibernateDao.get(TbSysMenu.class,parentId);
		loopSub(parent.getMenuId(), parent.getTreeNo());
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void loopSub(Long parentId,String treeNo){
		List<TbSysMenu> subList = (List<TbSysMenu>)super.hibernateDao.find("from TbSysMenu t where t.parent.menuId = ? and t.dataStatus = '1'",parentId);
		for (int i = 0, len = subList.size(); i < len; i++) {
			String newTreeNo = treeNo+FormateUtil.getInstance().appendZero(i);
			int cnt = super.hibernateDao.count("select count(*) from TbSysMenu t where t.parent.menuId = ? and t.dataStatus = '1' ", parentId);
			TbSysMenu next = subList.get(i);
			next.setTreeNo(newTreeNo);
			next.setIsLeaf(cnt==0?"1":"0");
			super.save(next);
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
	@Override
	public List findChkedMenu(Long roleId) {
		return super.hibernateDao
				.find("select m from TbSysRolePriv t join t.menu as m where t.relType = 'R' and t.roleId = ? ",
						roleId);
	}
}