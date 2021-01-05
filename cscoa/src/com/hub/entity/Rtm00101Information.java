package com.hub.entity;
/**
 * ���˽�ͨ����ϸ�Ͳ��÷������������ı���exm00101
 * //18�����ԣ���Ӧ��exm00101��18���ֶΣ�taxamtbakδȡ
 * @author winv87
 *
 */
public class Rtm00101Information {
	private int id;					//id
	private String currtype;		//����
	private String invoiceno;		//��Ʊ����
	private String isdichong;		//�Ƿ�ɵֳ�
	private String taxamt;			//˰��
	//private float taxamtbak;		//˰��
	private String totalamount;		//ȥ˰���
	private String checkid;			//���յ���
	private String subjectcode;		//��ƿ�Ŀ
	private String subjectname;		//��Ŀ����
	private String item;			//����
	private String itemname;		//��������
	private String amount;			//���	
	private String deptno;			//����
	private String deptname;		//��������
	private String feecompany;		//���ù�����˾
	private String feecompanyname;	//������˾����
	private String memo;			//����
	private String remark;			//����
	
	
	
	public Rtm00101Information() {
		super();
	}



	public String getTaxamt() {
		return taxamt;
	}



	public void setTaxamt(String taxamt) {
		this.taxamt = taxamt;
	}



	public String getTotalamount() {
		return totalamount;
	}



	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public Rtm00101Information(int id, String currtype, String invoiceno,
			String isdichong, String taxamt, String totalamount,
			String checkid, String subjectcode, String subjectname,
			String item, String itemname, String amount, String deptno,
			String deptname, String feecompany, String feecompanyname,
			String memo, String remark) {
		super();
		this.id = id;
		this.currtype = currtype;
		this.invoiceno = invoiceno;
		this.isdichong = isdichong;
		this.taxamt = taxamt;
		this.totalamount = totalamount;
		this.checkid = checkid;
		this.subjectcode = subjectcode;
		this.subjectname = subjectname;
		this.item = item;
		this.itemname = itemname;
		this.amount = amount;
		this.deptno = deptno;
		this.deptname = deptname;
		this.feecompany = feecompany;
		this.feecompanyname = feecompanyname;
		this.memo = memo;
		this.remark = remark;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCurrtype() {
		return currtype;
	}



	public void setCurrtype(String currtype) {
		this.currtype = currtype;
	}



	public String getInvoiceno() {
		return invoiceno;
	}



	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}



	public String getIsdichong() {
		return isdichong;
	}



	public void setIsdichong(String isdichong) {
		this.isdichong = isdichong;
	}



	public String getCheckid() {
		return checkid;
	}



	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}



	public String getSubjectcode() {
		return subjectcode;
	}



	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}



	public String getSubjectname() {
		return subjectname;
	}



	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}



	public String getItem() {
		return item;
	}



	public void setItem(String item) {
		this.item = item;
	}



	public String getItemname() {
		return itemname;
	}



	public void setItemname(String itemname) {
		this.itemname = itemname;
	}



	public String getDeptno() {
		return deptno;
	}



	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}



	public String getDeptname() {
		return deptname;
	}



	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}



	public String getFeecompany() {
		return feecompany;
	}



	public void setFeecompany(String feecompany) {
		this.feecompany = feecompany;
	}



	public String getFeecompanyname() {
		return feecompanyname;
	}



	public void setFeecompanyname(String feecompanyname) {
		this.feecompanyname = feecompanyname;
	}



	public String getMemo() {
		return memo;
	}



	public void setMemo(String memo) {
		this.memo = memo;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	@Override
	public String toString() {
		return "InformationExm00101 [id=" + id + ", currtype=" + currtype + ", invoiceno=" + invoiceno + ", isdichong="
				+ isdichong + ", taxamt=" + taxamt + ", totalamount=" + totalamount + ", checkid=" + checkid
				+ ", subjectcode=" + subjectcode + ", subjectname=" + subjectname + ", item=" + item + ", itemname="
				+ itemname + ", amount=" + amount + ", deptno=" + deptno + ", deptname=" + deptname + ", feecompany="
				+ feecompany + ", feecompanyname=" + feecompanyname + ", memo=" + memo + ", remark=" + remark + "]";
	}
	
	
}
