package com.hub.entity;

import java.util.Date;

/**
 * 在显示所有审批的页面上，每一个审批的单子都是一个对象
 * 	存放着审批的ID，审批的日期，审批的编号，审批的名称，审批的状态
 * 数据库：表fpm001可以查询的到
 * 1,公章;2,法人章;3,合同章;4,总经理章;5,财务章;6,人事章;7,其他
 * @author hub
 *
 */
public class SignPage {
	private String checkid;			//审批的ID====id
	private String staff;			//申请人
	private String filetype;		//
	private String stampertype;		// 1,公章;2,法人章;3,合同章;4,总经理章;5,财务章;6,人事章;7,其他
	private String header;			//审批的名称====proceedingname
	private String zt;
	private String onlyshowcheckid; //仅作为显示的checkid，经过了处理

	
	public SignPage(String checkid, String staff, String filetype, String stampertype, String header, String zt,
			String onlyshowcheckid) {
		super();
		this.checkid = checkid;
		this.staff = staff;
		this.filetype = filetype;
		this.stampertype = stampertype;
		this.header = header;
		this.zt = zt;
		this.onlyshowcheckid = onlyshowcheckid;
	}

	public String getOnlyshowcheckid() {
		return onlyshowcheckid;
	}

	public String getStampertype() {
		return stampertype;
	}

	public void setStampertype(String stampertype) {
		this.stampertype = stampertype;
	}

	public void setOnlyshowcheckid(String onlyshowcheckid) {
		this.onlyshowcheckid = onlyshowcheckid;
	}

	public SignPage() {
		super();
	}
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	@Override
	public String toString() {
		return "SignPage [checkid=" + checkid + ", staff=" + staff + ", filetype=" + filetype + ", header=" + header
				+ ", getCheckid()=" + getCheckid() + ", getStaff()=" + getStaff() + ", getFiletype()=" + getFiletype()
				+ ", getHeader()=" + getHeader() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}


	
	
}
