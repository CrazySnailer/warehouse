package com.calf.framework.um.services.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Criteria;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.um.entity.TbSysMenu;
import com.calf.framework.um.entity.TbSysUdr;
import com.calf.framework.um.entity.TbSysUser;
import com.calf.framework.um.qry.UserQry;
import com.calf.framework.um.services.UserService;
import com.calf.framework.util.CryptUtils;
import com.calf.framework.vo.Page;

/**
 * 人员管理 service 实现
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	/*@Resource
	UserDao userDao;
	
	@Resource
	SysudrDao sysudrDao;*/

	/**
	 * 查找分页信息
	 */
	public Page findUserPage(UserQry qry) {
		/*return userDao.pagedQuery("page_select", qry, qry.getPageNo(),
				qry.getPageSize());*/
		Criteria criteria = super.hibernateDao.createCriteria(TbSysUser.class);
		criteria.createAlias("dept", "dept");
		CriteriaUtils.addEq(criteria, "dept.deptId", qry.getDeptId());
		CriteriaUtils.addFullLike(criteria, "userCode", qry.getUserCode());
		CriteriaUtils.addFullLike(criteria, "userName", qry.getUserName());
		CriteriaUtils.addFullLike(criteria, "mobile", qry.getMobile());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}

	/**
	 * 获取对象
	 */
	public TbSysUser get(Long userId) {
		return (TbSysUser) super.get(TbSysUser.class, userId);
	}

	/**
	 * 保存
	 **/
	public String add(TbSysUser entity) {
		entity.setLoginPwd(CryptUtils.md5(entity.getLoginPwd()).toUpperCase());
		super.add(entity);
		return null;
	}

	public String update(TbSysUser entity) {
		entity.setLoginPwd(CryptUtils.md5(entity.getLoginPwd()).toUpperCase());
		super.save(entity);
		return null;
	}

	/**
	 * 删除
	 */
	public String delete(TbSysUser entity) {
		super.remove(entity);
		return null;
	}
	
	/**
	 * 用户登录验证
	 */
	@Override
	public TbSysUser loginCheck(String userCode, String pwd) {
		String cryptPwd = CryptUtils.md5(pwd).toUpperCase();
		List<TbSysUser> tmpList = (List<TbSysUser>)super.hibernateDao.find("from TbSysUser t where (t.userCode = ? or t.userName = ?) and t.loginPwd = ?", new Object[]{userCode,userCode,cryptPwd});
		if(CollectionUtils.isNotEmpty(tmpList)){
			return tmpList.get(0); 
		}else{
			return null;
		}
	}
	
	/**
	 * 根据用户查找角色列表
	 */
	@Override
	public List findUserRoles(Long userId) {
		return super.hibernateDao.find("from TbSysUdr t where t.userId = ?", userId);
	}
	
	/**
	 * 保存用户授权信息
	 */
	@Override
	public int saveAssignRoles(Long userId, Long[] roleIds,Long createUser) {
		super.hibernateDao.bulkUpdate("delete from TbSysUdr t where t.userId = ?", userId);
		if(ArrayUtils.isEmpty(roleIds)){
			for(Long roleId:roleIds){
				TbSysUdr udr = new TbSysUdr();
				udr.setCreateUser(createUser);
				udr.setCreateDate(new Date());
				udr.setUserId(userId);
				udr.setRoleId(roleId);
				super.add(udr);				
			}
		}
		return 0;
	}
	
	/**
	 * 查找用户菜单
	 * 根据角色的不同而查询不同菜单
	 * @param userId
	 * @param rootVal
	 * @return
	 */
	public List findUserMenu(Long userId,String rootId){
		Map params = new HashMap();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M.MENU_ID,                                   ");
		sql.append("       M.P_MENU_ID,                                 ");
		sql.append("       M.NAME,                                      ");
		sql.append("       M.URL,                                       ");
		sql.append("       M.ID_VAL,                                    ");
		sql.append("       M.NEED_BUY,                                  ");
		sql.append("       M.IS_LEAF                                    ");
		sql.append("  FROM (select t.MENU_ID,                           ");
		sql.append("               t.P_MENU_ID,                         ");
		sql.append("               t.NAME,                              ");
		sql.append("               t.URL,                               ");
		sql.append("               t.ID_VAL,                            ");
		sql.append("               t.NEED_BUY,                          ");
		sql.append("               t.IS_LEAF,                           ");
		sql.append("               t.TREE_NO                            ");
		sql.append("          from TB_SYS_MENU t                        ");
		sql.append("         where t.data_status = '1'                  ");
		sql.append("           and t.MENU_TYPE = 'M'                    ");
		sql.append("         start with t.id_val = :rootId              ");
		sql.append("        connect by prior t.menu_id = t.p_menu_id) m,");
		sql.append("       TB_SYS_ROLE_PRIV R                           ");
		sql.append(" WHERE M.MENU_ID = R.MENU_ID                        ");
		sql.append("   AND R.ROLE_ID IN                                 ");
		sql.append("       (SELECT UDR.ROLE_ID                          ");
		sql.append("          FROM TB_SYS_UDR UDR                       ");
		sql.append("         WHERE UDR.USER_ID = :userId)               ");
		sql.append("   AND R.REL_TYPE = 'R'                             ");
		sql.append(" group by M.MENU_ID,                                ");
		sql.append("          M.P_MENU_ID,                              ");
		sql.append("          M.NAME,                                   ");
		sql.append("          M.URL,                                    ");
		sql.append("          M.ID_VAL,                                 ");
		sql.append("          M.NEED_BUY,                               ");
		sql.append("          M.TREE_NO,                                ");
		sql.append("          M.IS_LEAF                                 ");
		sql.append(" ORDER BY M.TREE_NO                                 ");
		params.put("rootId", rootId);
		params.put("userId", userId);
		return super.jdbcDao.queryForList(sql.toString(), params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int idx) throws SQLException {
				TbSysMenu vo = new TbSysMenu();
				vo.setMenuId(rs.getLong("MENU_ID"));
				vo.setParentId(rs.getLong("P_MENU_ID"));
				vo.setName(rs.getString("NAME"));
				vo.setUrl(rs.getString("URL"));
				vo.setIdVal(rs.getString("ID_VAL"));
				vo.setNeedBuy(rs.getString("NEED_BUY"));
				vo.setIsLeaf(rs.getString("IS_LEAF"));
				return vo;
			}});
	}
	
	/**
	 * 查找用户所拥有的资源
	 * @param userId
	 * @return
	 */
	public List findUserRes(Long userId){
		/*return this.userDao.findUserRes(userId);*/
		Map params = new HashMap();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M.MENU_ID,						");
		sql.append("       M.P_MENU_ID,                     ");
		sql.append("       M.NAME,                          ");
		sql.append("       M.URL,                           ");
		sql.append("       M.ID_VAL,                        ");
		sql.append("       M.NEED_BUY,                      ");
		sql.append("       M.IS_LEAF                        ");
		sql.append("  FROM (select t.MENU_ID,               ");
		sql.append("               t.P_MENU_ID,             ");
		sql.append("               t.NAME,                  ");
		sql.append("               t.URL,                   ");
		sql.append("               t.ID_VAL,                ");
		sql.append("               t.NEED_BUY,              ");
		sql.append("               t.IS_LEAF,               ");
		sql.append("               t.TREE_NO                ");
		sql.append("          from TB_SYS_MENU t            ");
		sql.append("         where t.data_status = '1'      ");
		sql.append("           and t.MENU_TYPE = 'R') m,    ");
		sql.append("       TB_SYS_ROLE_PRIV R               ");
		sql.append(" WHERE M.MENU_ID = R.MENU_ID            ");
		sql.append("   AND R.ROLE_ID IN                     ");
		sql.append("       (SELECT UDR.ROLE_ID              ");
		sql.append("          FROM TB_SYS_UDR UDR           ");
		sql.append("         WHERE UDR.USER_ID = :userId)   ");
		sql.append("   AND R.REL_TYPE = 'R'                 ");
		sql.append(" group by M.MENU_ID,                    ");
		sql.append("          M.P_MENU_ID,                  ");
		sql.append("          M.NAME,                       ");
		sql.append("          M.URL,                        ");
		sql.append("          M.ID_VAL,                     ");
		sql.append("          M.NEED_BUY,                   ");
		sql.append("          M.TREE_NO,                    ");
		sql.append("          M.IS_LEAF                     ");
		sql.append(" ORDER BY M.TREE_NO                     ");
		
		params.put("userId", userId);
		return super.jdbcDao.queryForList(sql.toString(), params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int idx) throws SQLException {
				TbSysMenu vo = new TbSysMenu();
				vo.setMenuId(rs.getLong("MENU_ID"));
				vo.setParentId(rs.getLong("P_MENU_ID"));
				vo.setName(rs.getString("NAME"));
				vo.setUrl(rs.getString("URL"));
				vo.setIdVal(rs.getString("ID_VAL"));
				vo.setNeedBuy(rs.getString("NEED_BUY"));
				vo.setIsLeaf(rs.getString("IS_LEAF"));
				return vo;
			}});
	}
}