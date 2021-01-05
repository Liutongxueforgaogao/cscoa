package com.hub.entity;

public class Rtm00101 {
    private String currtype;

    private Long id;

    private String checkid;

    private String subjectcode;

    private String subjectname;

    private String item;

    private String itemname;

    private Double amount;

    private String deptno;

    private String deptname;

    private String feecompany;

    private String feecompanyname;

    private String memo;

    private String remark;

    private String invoiceno;

    private Double taxamt;

    private Double taxamtbak;

    private String isdichong;

    private Double totalamount;

    private String bank;
    
    private String bankcode;
    
    
    public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getCurrtype() {
        return currtype;
    }

    public void setCurrtype(String currtype) {
        this.currtype = currtype == null ? null : currtype.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckid() {
        return checkid;
    }

    public void setCheckid(String checkid) {
        this.checkid = checkid == null ? null : checkid.trim();
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode == null ? null : subjectcode.trim();
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname == null ? null : subjectname.trim();
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname == null ? null : itemname.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno == null ? null : deptno.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getFeecompany() {
        return feecompany;
    }

    public void setFeecompany(String feecompany) {
        this.feecompany = feecompany == null ? null : feecompany.trim();
    }

    public String getFeecompanyname() {
        return feecompanyname;
    }

    public void setFeecompanyname(String feecompanyname) {
        this.feecompanyname = feecompanyname == null ? null : feecompanyname.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno == null ? null : invoiceno.trim();
    }

    public Double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(Double taxamt) {
        this.taxamt = taxamt;
    }

    public Double getTaxamtbak() {
        return taxamtbak;
    }

    public void setTaxamtbak(Double taxamtbak) {
        this.taxamtbak = taxamtbak;
    }

    public String getIsdichong() {
        return isdichong;
    }

    public void setIsdichong(String isdichong) {
        this.isdichong = isdichong == null ? null : isdichong.trim();
    }

    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }
}