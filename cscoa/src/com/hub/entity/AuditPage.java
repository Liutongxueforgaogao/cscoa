package com.hub.entity;

import java.util.Date;

/**
 * ����ʾ����������ҳ���ϣ�ÿһ�������ĵ��Ӷ���һ������
 * 	�����������ID�����������ڣ������ı�ţ����������ƣ�������״̬
 * ���ݿ⣺��exm001���Բ�ѯ�ĵ�
 * 
 * @author hub
 *
 */
public class AuditPage {
	private int id;			//������ID====id
	private String date;	//����������====inputdate
	private String number;	//�����ı��====checkid
	private String name;	//����������====proceedingname
	private String zt;		//������״̬====isapproved��20170425��Ӧ����taskdsh���е�isdeptapproved��
	private String totalamount;	//�������Ľ���20170505�޸ģ����ʹ�õ���exm001��totalamount��
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
