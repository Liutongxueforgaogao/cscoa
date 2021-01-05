package com.hub.entity;
/**
 * ���÷�:���ݿ��Ϊexm00103
 * 
 * @author winv87
 *
 */
public class InformationExm00103 {
	private int id;					//id
	private String payoutno;		//���ô���	
	private String checkid;			//���յ���
	private String traveldate;		//����
	private String hourarea;		//�����ص�
	private String reason;			//����֧��
	private String amount;			//���
	private String currtype;		//����
	private String memo;			//����
	private String remark;			//��ע(�������ֻ���)
	private String exm00103;		//
	public InformationExm00103() {
		super();
	}
	

	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}



	public InformationExm00103(int id, String payoutno, String checkid,
			String traveldate, String hourarea, String reason, String amount,
			String currtype, String memo, String remark, String exm00103) {
		super();
		this.id = id;
		this.payoutno = payoutno;
		this.checkid = checkid;
		this.traveldate = traveldate;
		this.hourarea = hourarea;
		this.reason = reason;
		this.amount = amount;
		this.currtype = currtype;
		this.memo = memo;
		this.remark = remark;
		this.exm00103 = exm00103;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPayoutno() {
		return payoutno;
	}
	public void setPayoutno(String payoutno) {
		this.payoutno = payoutno;
	}
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	public String getTraveldate() {
		return traveldate;
	}
	public void setTraveldate(String traveldate) {
		this.traveldate = traveldate;
	}
	public String getHourarea() {
		return hourarea;
	}
	public void setHourarea(String hourarea) {
		this.hourarea = hourarea;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getCurrtype() {
		return currtype;
	}
	public void setCurrtype(String currtype) {
		this.currtype = currtype;
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
	
	public String getExm00103() {
		return exm00103;
	}

	public void setExm00103(String exm00103) {
		this.exm00103 = exm00103;
	}

	@Override
	public String toString() {
		return "InformationExm00103 [id=" + id + ", payoutno=" + payoutno + ", checkid=" + checkid + ", traveldate="
				+ traveldate + ", hourarea=" + hourarea + ", reason=" + reason + ", amount=" + amount + ", currtype="
				+ currtype + ", memo=" + memo + ", remark=" + remark + ", exm00103=" + exm00103 + "]";
	}

	
	
	
}
