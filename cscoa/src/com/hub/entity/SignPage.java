package com.hub.entity;

import java.util.Date;

/**
 * ����ʾ����������ҳ���ϣ�ÿһ�������ĵ��Ӷ���һ������
 * 	�����������ID�����������ڣ������ı�ţ����������ƣ�������״̬
 * ���ݿ⣺��fpm001���Բ�ѯ�ĵ�
 * 1,����;2,������;3,��ͬ��;4,�ܾ�����;5,������;6,������;7,����
 * @author hub
 *
 */
public class SignPage {
	private String checkid;			//������ID====id
	private String staff;			//������
	private String filetype;		//
	private String stampertype;		// 1,����;2,������;3,��ͬ��;4,�ܾ�����;5,������;6,������;7,����
	private String header;			//����������====proceedingname
	private String zt;
	private String onlyshowcheckid; //����Ϊ��ʾ��checkid�������˴���

	
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
