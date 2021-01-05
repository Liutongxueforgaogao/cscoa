package com.hub.entity;

import java.util.Date;

/**
 * 在显示所有审批的页面上，每一个审批的单子都是一个对象
 * 	存放着审批的ID，审批的日期，审批的编号，审批的名称，审批的状态
 * 数据库：表exm001可以查询的到
 * 
 * @author hub
 *
 */
public class AuditPage {
	private int id;			//审批的ID====id
	private String date;	//审批的日期====inputdate
	private String number;	//审批的编号====checkid
	private String name;	//审批的名称====proceedingname
	private String zt;		//审批的状态====isapproved【20170425：应该用taskdsh表中的isdeptapproved】
	private String totalamount;	//报销单的金额，【20170505修改，金额使用的是exm001的totalamount】
	public AuditPage() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}


	public AuditPage(int id, String date, String number, String name,
			String zt, String totalamount) {
		super();
		this.id = id;
		this.date = date;
		this.number = number;
		this.name = name;
		this.zt = zt;
		this.totalamount = totalamount;
	}


	public String getTotalamount() {
		return totalamount;
	}


	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}


	
	
	
}
