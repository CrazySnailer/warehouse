package com.calf.framework.warehouse.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhOrderInD;
import com.calf.framework.warehouse.entity.TbWhOrderInM;
import com.calf.framework.warehouse.entity.TbWhProduct;
import com.calf.framework.warehouse.entity.TbWhWarehouse;
import com.calf.framework.warehouse.qry.OrderinQry;
import com.calf.framework.warehouse.services.OrderinService;
import com.calf.framework.warehouse.services.WarehouseService;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;

public class OrderinAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3499001652235764477L;	
	/**
	 * 模块临时SESSION
	 */
	private static final String MOD_SESSION_NAME = "com.calf.framework.warehouse.web.OrderinAction";

	OrderinService orderinService;
	
	WarehouseService warehouseService;

	OrderinQry qry;

	Page page;

	TbWhOrderInM entity;
	
	List<TbWhWarehouse> warehouseList;
	
	List<TbWhOrderInD> detailList;
	
	TbWhOrderInD detail;

	/**
	 * 列表
	 */
	@RequiresPermissions(value = "wh:orderin:view", requiresUser = true)
	public String list() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if (StringUtils.isNotBlank(super.qryHex)) {
			qry = (OrderinQry) ObjectUtils.getObjectFromHex(qryHex);
		}
		if (qry == null) {
			qry = new OrderinQry();
			qry.setOrderCol("createDate");
			qry.setOrderType(Constants.DESC);
		}

		// 设置默认排序号
		if (StringUtils.isBlank(qry.getOrderCol())) {
			qry.setOrderCol("createDate");
			qry.setOrderType(Constants.DESC);
		}
		
		this.warehouseList = this.warehouseService.findAllPhysicsWarehouse(userInfo);

		qry.setUserInfo(userInfo);
		page = orderinService.findOrderinPage(qry);
		return "list";
	}

	/**
	 * 新增
	 **/
	@RequiresPermissions(value = "wh:orderin:edit", requiresUser = true)
	public String toAdd() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		removeSession();
		entity = new TbWhOrderInM();
		entity.setOrderDate(new Date());
		this.warehouseList = this.warehouseService.findAllPhysicsWarehouse(userInfo);
		super.event = "ADD";
		super.title = "新增入库订单";
		return "edit";
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "wh:orderin:edit", requiresUser = true)
	public String toEdit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		removeSession();
		entity = orderinService.get(TbWhOrderInM.class, entity.getOrderId());		
		this.warehouseList = this.warehouseService.findAllPhysicsWarehouse(userInfo);
		this.detailList = this.orderinService.findOrderDetail(entity);
		getSession().setAttribute(MOD_SESSION_NAME, detailList);//将明细放置到SESSION中
		super.event = "EDIT";
		super.title = "修改入库订单";
		return "edit";
	}

	/**
	 * 修改动作
	 **/
	@RequiresPermissions(value = "wh:orderin:edit", requiresUser = true)
	public String edit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if ("ADD".equals(super.event)) {
			// 新增操作
			entity.setOrderId(null);
			entity.setDataStatus(Constants.YES);
			entity.setCreateUser(userInfo.getUserId());
			entity.setCreateDate(new Date());
			orderinService.addOrderin(entity);
			super.addAttribute("qry.orderCol", "createDate");
			super.addAttribute("qry.orderType", "0");
			super.saveMessage("入库订单新增成功");
		} else if ("EDIT".equals(super.event)) {
			// 修改操作
			TbWhOrderInM db = orderinService.get(TbWhOrderInM.class,
					entity.getOrderId());

			db.setOrderId(entity.getOrderId());
			db.setOrderNo(entity.getOrderNo());
			db.setOrderType(entity.getOrderType());
			db.setOrderDate(entity.getOrderDate());
			db.setLimitHours(entity.getLimitHours());
			db.setCustOrderNo(entity.getCustOrderNo());
			db.setWhId(entity.getWhId());
			db.setVendorId(entity.getVendorId());
			db.setTrustId(entity.getTrustId());
			db.setLinker(entity.getLinker());
			db.setLinkerTel(entity.getLinkerTel());
			db.setRemarks(entity.getRemarks());
			db.setHandleId(entity.getHandleId());
			db.setHandleDept(entity.getHandleDept());
			db.setOrderSource(entity.getOrderSource());
			db.setAuditDate(entity.getAuditDate());
			db.setAuditId(entity.getAuditId());

			db.setUpdateUser(userInfo.getUserId());
			db.setUpdateDate(new Date());

			orderinService.saveOrderin(db);
			super.saveMessage("入库订单保存成功");
			super.addAttribute("qryHex", super.qryHex);
		}
		
		this.removeSession();
		
		super.redirectUrl = "/warehouse/orderin_list.action";
		return super.GLOBAL_SUCCESS;
	}
	
	/**
	 * 移除SESSION
	 */
	private void removeSession(){
		getSession().removeAttribute(MOD_SESSION_NAME);
	}

	/**
	 * 删除
	 **/
	@RequiresPermissions(value = "wh:orderin:edit", requiresUser = true)
	public String delete() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = orderinService.get(TbWhOrderInM.class, entity.getOrderId());
		if (entity.isCanDelete()) {
			this.orderinService.deleteOrderin(entity);
			super.renderJsonSuccess("删除成功!");
		} else {
			super.renderJsonError("该记录已被删除!");
		}
		return null;
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "wh:orderin:view", requiresUser = true)
	public String toView() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = orderinService.get(TbWhOrderInM.class, entity.getOrderId());
		super.title = "入库订单详细信息";
		return "view";
	}

	/**
	 * 判断是否唯一
	 * @return
	 */
	@RequiresPermissions(value = "wh:orderin:view", requiresUser = true)
	public String checkUnique() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		boolean isCorrect = orderinService.isUnique(entity.getOrderId());
		super.rendText(String.valueOf(isCorrect));
		return null;
	}
	
	/**
	 * 子表列表
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = "wh:orderin:view", requiresUser = true)
	public String listDetail()throws Exception {
		detailList = (List<TbWhOrderInD>)super.getSession().getAttribute(MOD_SESSION_NAME);
		return "listDetail";
	}
	
	/**
	 * 新增明细
	 **/
	@RequiresPermissions(value = "wh:orderin:edit", requiresUser = true)
	public String toAddDetail() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		detail = new TbWhOrderInD();
		super.event = "ADD";
		super.title = "新增订单明细";
		return "editDetail";
	}
	
	/**
	 * 修改明细
	 **/
	@RequiresPermissions(value = "wh:orderin:edit", requiresUser = true)
	public String toEditDetail() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		detailList = (List<TbWhOrderInD>)super.getSession().getAttribute(MOD_SESSION_NAME);
		if(detailList!=null){
			int index = Integer.parseInt(super.getRequest().getParameter("index"));
			detail = detailList.get(index);
		}
		super.event = "EDIT";
		super.title = "修改订单明细";
		return "editDetail";
	}
	
	/**
	 * 添加主记录
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = "wh:orderin:edit", requiresUser = true)
	public String editDetail()throws Exception {
		detailList = (List<TbWhOrderInD>)super.getSession().getAttribute(MOD_SESSION_NAME);
		
		if ("ADD".equals(super.event)) {		
			if(detailList==null){
				detailList = new ArrayList<TbWhOrderInD>();			
			}
			TbWhProduct tproduct = this.orderinService.get(TbWhProduct.class, detail.getProduct().getProductId());
			detail.setProduct(tproduct);
			detail.setProductValue(tproduct.getProductCost());
			detailList.add(detail);
		}else{
			int index = Integer.parseInt(super.getRequest().getParameter("index"));
			TbWhProduct tproduct = this.orderinService.get(TbWhProduct.class, detail.getProduct().getProductId());
			detail.setProduct(tproduct);
			detail.setProductValue(tproduct.getProductCost());
			detailList.set(index, detail);
		}
		super.getSession().setAttribute(MOD_SESSION_NAME, detailList);
		super.saveMessage("订单明细保存成功");
		super.getRequest().setAttribute("autoclose", true);
		return super.GLOBAL_SUCCESS;
	}
	/**
	 * 删除记录
	 */
	@RequiresPermissions(value = "wh:orderin:edit", requiresUser = true)
	public String deleteDetail()throws Exception {
		detailList = (List<TbWhOrderInD>)super.getSession().getAttribute(MOD_SESSION_NAME);
		if(detailList!=null){
			int index = Integer.parseInt(super.getRequest().getParameter("index"));
			detailList.remove(index);
		}
		super.rendText("OK");
		return null;
	}
	/**
	 * 查看明细数据
	 */
	@RequiresPermissions(value = "wh:orderin:view", requiresUser = true)
	public String viewDetail()throws Exception{
		detailList = (List<TbWhOrderInD>)super.getSession().getAttribute(MOD_SESSION_NAME);
		if(detailList!=null){
			int index = Integer.parseInt(super.getRequest().getParameter("index"));
			detail = detailList.get(index);
		}
		return "viewDetail";
	}

	public OrderinQry getQry() {
		return this.qry;
	}

	public void setQry(OrderinQry qry) {
		this.qry = qry;
	}

	public OrderinService getOrderinService() {
		return this.orderinService;
	}

	public void setOrderinService(OrderinService orderinService) {
		this.orderinService = orderinService;
	}

	public WarehouseService getWarehouseService() {
		return warehouseService;
	}

	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	public List<TbWhWarehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<TbWhWarehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public TbWhOrderInM getEntity() {
		return entity;
	}

	public void setEntity(TbWhOrderInM entity) {
		this.entity = entity;
	}

	public List<TbWhOrderInD> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<TbWhOrderInD> detailList) {
		this.detailList = detailList;
	}

	public TbWhOrderInD getDetail() {
		return detail;
	}

	public void setDetail(TbWhOrderInD detail) {
		this.detail = detail;
	}
	
	
}