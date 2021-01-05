package com.hub.entity;
/**
 * 除了交通费明细和差旅费两个表，其他的表都是exm00101
 * //18个属性，对应着exm00101的18个字段，taxamtbak未取
 * @author winv87
 *
 */
public class Rtm00101Information {
	private int id;					//id
	private String currtype;		//币种
	private String invoiceno;		//发票号码
	private String isdichong;		//是否可抵冲
	private String taxamt;			//税额
	//private float taxamtbak;		//税额
	private String totalamount;		//去税金额
	private String checkid;			//验收单号
	private String subjectcode;		//会计科目
	private String subjectname;		//科目名称
	private String item;			//事项
	private String itemname;		//事项名称
	private String amount;			//金额	
	private String deptno;			//部门
	private String deptname;		//部门名称
	private String feecompany;		//费用归属公司
	private String feecompanyname;	//归属公司名称
	private String memo;			//辅助
	private String remark;			//事项
	
	
	
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
