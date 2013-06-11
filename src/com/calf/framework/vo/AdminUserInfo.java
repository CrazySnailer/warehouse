package com.calf.framework.vo;

import java.util.List;

import com.calf.framework.um.entity.TbSysDept;
import com.calf.framework.um.entity.TbSysMenu;
import com.calf.framework.um.entity.TbSysUser;

public class AdminUserInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 123204815303575035L;
	/**
	 * 用户
	 */
	TbSysUser user;
	/**
	 * 所在机构
	 */
	TbSysDept dept;
	/**
	 * 资源列表
	 */
	List resList;
	/**
	 * 菜单集合
	 */
	List menuList;
	/**
	 * 菜单STR
	 */
	String menuStr;
	/**
	 * 所属省
	 */
	TbSysDept prov;
	/**
	 * 所属地市
	 */
	TbSysDept city;
	/**
	 * 所属区县
	 **/
	TbSysDept county;
	/**
	 * 1查看所有数据 2查看全省数据 3 查看全市数据 4查看全县数据 5查看本部门及以下部门数据 6查看本部门数据 7查看本人数据
	 **/
	String dataRanage;

	/**
	 * 查找某一资源在用户授权中是否存在
	 * @param resItemId
	 * @return
	 */
	public boolean findResources(String resItemId) {
		boolean isExist = false;

		if (resList != null) {
			for (int i = 0, size = this.resList.size(); i < size; i++) {
				TbSysMenu menu = (TbSysMenu) resList.get(i);
				if (resItemId.equals(menu.getIdVal())) {
					isExist = true;
					break;
				}
			}
		}

		return isExist;
	}
	
	/**
	 * 查找某一个菜单是否在用户菜单中出现
	 * @param resItemId
	 * @return
	 */
	public boolean findMenu(String resItemId) {
		boolean isExist = false;

		if (resList != null) {
			for (int i = 0, size = this.menuList.size(); i < size; i++) {
				TbSysMenu menu = (TbSysMenu) menuList.get(i);
				if (resItemId.equals(menu.getIdVal())) {
					isExist = true;
					break;
				}
			}
		}
		return false;
	}

	public TbSysUser getUser() {
		return user;
	}

	public void setUser(TbSysUser user) {
		this.user = user;
	}

	public TbSysDept getDept() {
		return dept;
	}

	public void setDept(TbSysDept dept) {
		this.dept = dept;
	}

	public List getResList() {
		return resList;
	}

	public void setResList(List resList) {
		this.resList = resList;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public String getMenuStr() {
		return menuStr;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}

	public Long getUserId() {
		return this.user.getUserId();
	}

	public Long getDeptId() {
		return this.dept.getDeptId();
	}

	public TbSysDept getProv() {
		return prov;
	}

	public void setProv(TbSysDept prov) {
		this.prov = prov;
	}

	public TbSysDept getCity() {
		return city;
	}

	public void setCity(TbSysDept city) {
		this.city = city;
	}

	public TbSysDept getCounty() {
		return county;
	}

	public void setCounty(TbSysDept county) {
		this.county = county;
	}

	public String getDataRanage() {
		return dataRanage;
	}

	public void setDataRanage(String dataRanage) {
		this.dataRanage = dataRanage;
	}
	/**
	 * 获取学校ID
	 * @return
	 */
	public Long getSchoolId() {
		return this.dept.getDeptId();
	}

}
