package com.hub.entity;
/**
 * 交通费明细:数据库表为exm00102
 * 
 * @author winv87
 *
 */
public class InformationExm00102 {

	private int id;						//id
	private String payoutno;			//费用代码
	private String transportdate1;		//日期
	private String checkid;				//验收单号
	private String transportdate;		//日期
	private String hourarea;			//时间段
	private String selocation;			//行程起止地点
	private float distance;				//公里数
	private int mans;					//人数
	private String subject;				//事由
	private String amount;				//金额
	private String currtype;			//币种
	private String memo;				//辅助
	private String remark;				//备注
	public InformationExm00102() {
		super();
	}
	


	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	

	public InformationExm00102(int id, String payoutno, String transportdate1, String checkid, String transportdate,
			String hourarea, String selocation, float distance, int mans, String subject, String amount,
			String currtype, String memo, String remark) {
		super();
		this.id = id;
		this.payoutno = payoutno;
		this.transportdate1 = transportdate1;
		this.checkid = checkid;
		this.transportdate = transportdate;
		this.hourarea = hourarea;
		this.selocation = selocation;
		this.distance = distance;
		this.mans = mans;
		this.subject = subject;
		this.amount = amount;
		this.currtype = currtype;
		this.memo = memo;
		this.remark = remark;
	}



	public float getDistance() {
		return distance;
	}



	public void setDistance(float distance) {
		this.distance = distance;
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
	public String getTransportdate1() {
		return transportdate1;
	}
	public void setTransportdate1(String transportdate1) {
		this.transportdate1 = transportdate1;
	}
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	public String getTransportdate() {
		return transportdate;
	}
	public void setTransportdate(String transportdate) {
		this.transportdate = transportdate;
	}
	public String getHourarea() {
		return hourarea;
	}
	public void setHourarea(String hourarea) {
		this.hourarea = hourarea;
	}
	public String getSelocation() {
		return selocation;
	}
	public void setSelocation(String selocation) {
		this.selocation = selocation;
	}
	
	public int getMans() {
		return mans;
	}
	public void setMans(int mans) {
		this.mans = mans;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	@Override
	public String toString() {
		return "InformationExm00102 [id=" + id + ", payoutno=" + payoutno + ", transportdate1=" + transportdate1
				+ ", checkid=" + checkid + ", transportdate=" + transportdate + ", hourarea=" + hourarea
				+ ", selocation=" + selocation + ", distance=" + distance + ", mans=" + mans + ", subject=" + subject
				+ ", amount=" + amount + ", currtype=" + currtype + ", memo=" + memo + ", remark=" + remark + "]";
	}
	
}
