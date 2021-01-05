package com.hub.entity;

/**
 * ��20170512���������ݱ�Ϊexm001
 * 
 * ��ϸ��Ϣ��ʾҳ���У������һ���������ӵ���ϸ��Ϣ������Щ��ϸ��Ϣ��װ��һ������
 * ��������԰��������յ��ţ��������ƣ������ˣ�����ʱ�䣬�տλ���ƣ�����˵����
 * 				����ˣ����ʱ�䣬ȷ���ˣ�ȷ��ʱ�䣬�����ˣ�����ʱ��
 * ���ݿ⣺��exm001��Ӧ�����е���Ϣ
 * 
 * [20170504�޸���ϸ��Ϣ��]
 * 
 * @author hub
 *
 */
public class Rtm001Information {
	
	private String checkid;			   //���յ���1
	private String checkdate;		   //��������1
	private String staff;			   //������1
	private String feecompany;		   //���ù�����˾1
	private String feecompanyname;		//���ù�����˾����



	private String feeunitno;		   //���ű���1
	private String feeunit;			   //��������1
	private int revtype;			   //�ܿ�������
	private String payee;			   //�ܿ���
	private String payeename;		   //�ܿ�������
	private String proceedingname;	   //������������
	private String revcompany;		   //�տλ
	private String revcompanyname;	   //�տλ����,�������������1
	private String proceedingno;	   //��������
	private String remark;			   //����˵��
	private String routid;			   //����id
	private int status;			   	   //����״̬                                     
	private String prepayamt;		   //�ݸ����1
	private String amount;			   //ʵ�����1
	private String totalamount;		   //���úϼ�
	private String backamount;		   //�ɻؽ��
	private String backprepayno;	   //�ɻص���
	private String prepayno;		   //�ݸ�����
	private String paytype;			   //��ʽ
	private String affirmestate;	   //����ȷ��״̬
	private String affirmtime;		   //ȷ��ʱ��
	private String affirmexplain;	   //ȷ��˵��
	private String payestate;		   //����״̬
	private String paytime;			   //����ʱ��
	private String prepaydate;		   //Ԥ�ڸ�������
	private String inputer;		   	   //������
	private String inputdate;		   //����ʱ��                                      
	private String approvedstaff;	   //�����
	private int isapproved;		   	   //���״̬
	private String closedstaff;		   //������
	private int isclosed;		   	   //����״̬
	private String closedtime;		   //����ʱ��
	private String approvedtime;		//���ʱ��
	private String bank;				//����----������ģ�������ֶ�2017-10-18
	private String bankcode;			//�����˺�
	private String billtype;			//��������
	public Rtm001Information() {
		super();
	}



	public Rtm001Information(String checkid, String checkdate, String staff, String feecompany, String feecompanyname,
			String feeunitno, String feeunit, int revtype, String payee, String payeename, String proceedingname,
			String revcompany, String revcompanyname, String proceedingno, String remark, String routid, int status,
			String prepayamt, String amount, String totalamount, String backamount, String backprepayno,
			String prepayno, String paytype, String affirmestate, String affirmtime, String affirmexplain,
			String payestate, String paytime, String prepaydate, String inputer, String inputdate, String approvedstaff,
			int isapproved, String closedstaff, int isclosed, String closedtime, String approvedtime, String bank,
			String bankcode, String billtype) {
		super();
		this.checkid = checkid;
		this.checkdate = checkdate;
		this.staff = staff;
		this.feecompany = feecompany;
		this.feecompanyname = feecompanyname;
		this.feeunitno = feeunitno;
		this.feeunit = feeunit;
		this.revtype = revtype;
		this.payee = payee;
		this.payeename = payeename;
		this.proceedingname = proceedingname;
		this.revcompany = revcompany;
		this.revcompanyname = revcompanyname;
		this.proceedingno = proceedingno;
		this.remark = remark;
		this.routid = routid;
		this.status = status;
		this.prepayamt = prepayamt;
		this.amount = amount;
		this.totalamount = totalamount;
		this.backamount = backamount;
		this.backprepayno = backprepayno;
		this.prepayno = prepayno;
		this.paytype = paytype;
		this.affirmestate = affirmestate;
		this.affirmtime = affirmtime;
		this.affirmexplain = affirmexplain;
		this.payestate = payestate;
		this.paytime = paytime;
		this.prepaydate = prepaydate;
		this.inputer = inputer;
		this.inputdate = inputdate;
		this.approvedstaff = approvedstaff;
		this.isapproved = isapproved;
		this.closedstaff = closedstaff;
		this.isclosed = isclosed;
		this.closedtime = closedtime;
		this.approvedtime = approvedtime;
		this.bank = bank;
		this.bankcode = bankcode;
		this.billtype = billtype;
	}



	public String getBilltype() {
		return billtype;
	}



	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}



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



	public String getPrepayamt() {
		return prepayamt;
	}



	public void setPrepayamt(String prepayamt) {
		this.prepayamt = prepayamt;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public String getTotalamount() {
		return totalamount;
	}



	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}



	public String getBackamount() {
		return backamount;
	}



	public void setBackamount(String backamount) {
		this.backamount = backamount;
	}



	public Rtm001Information(String checkid, String checkdate, String staff,
			String feecompany, String feecompanyname, String feeunitno,
			String feeunit, int revtype, String payee, String payeename,
			String proceedingname, String revcompany, String revcompanyname,
			String proceedingno, String remark, String routid, int status,
			String prepayamt, String amount, String totalamount,
			String backamount, String backprepayno, String prepayno,
			String paytype, String affirmestate, String affirmtime,
			String affirmexplain, String payestate, String paytime,
			String prepaydate, String inputer, String inputdate,
			String approvedstaff, int isapproved, String closedstaff,
			int isclosed, String closedtime, String approvedtime) {
		super();
		this.checkid = checkid;
		this.checkdate = checkdate;
		this.staff = staff;
		this.feecompany = feecompany;
		this.feecompanyname = feecompanyname;
		this.feeunitno = feeunitno;
		this.feeunit = feeunit;
		this.revtype = revtype;
		this.payee = payee;
		this.payeename = payeename;
		this.proceedingname = proceedingname;
		this.revcompany = revcompany;
		this.revcompanyname = revcompanyname;
		this.proceedingno = proceedingno;
		this.remark = remark;
		this.routid = routid;
		this.status = status;
		this.prepayamt = prepayamt;
		this.amount = amount;
		this.totalamount = totalamount;
		this.backamount = backamount;
		this.backprepayno = backprepayno;
		this.prepayno = prepayno;
		this.paytype = paytype;
		this.affirmestate = affirmestate;
		this.affirmtime = affirmtime;
		this.affirmexplain = affirmexplain;
		this.payestate = payestate;
		this.paytime = paytime;
		this.prepaydate = prepaydate;
		this.inputer = inputer;
		this.inputdate = inputdate;
		this.approvedstaff = approvedstaff;
		this.isapproved = isapproved;
		this.closedstaff = closedstaff;
		this.isclosed = isclosed;
		this.closedtime = closedtime;
		this.approvedtime = approvedtime;
	}



	public String getFeecompanyname() {
		return feecompanyname;
	}
	
	public void setFeecompanyname(String feecompanyname) {
		this.feecompanyname = feecompanyname;
	}

	public String getApprovedtime() {
		return approvedtime;
	}

	public void setApprovedtime(String approvedtime) {
		this.approvedtime = approvedtime;
	}

	public String getCheckid() {
		return checkid;
	}

	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}

	public String getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getFeecompany() {
		return feecompany;
	}

	public void setFeecompany(String feecompany) {
		this.feecompany = feecompany;
	}

	public String getFeeunitno() {
		return feeunitno;
	}

	public void setFeeunitno(String feeunitno) {
		this.feeunitno = feeunitno;
	}

	public String getFeeunit() {
		return feeunit;
	}

	public void setFeeunit(String feeunit) {
		this.feeunit = feeunit;
	}

	public int getRevtype() {
		return revtype;
	}

	public void setRevtype(int revtype) {
		this.revtype = revtype;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getPayeename() {
		return payeename;
	}

	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}

	public String getProceedingname() {
		return proceedingname;
	}

	public void setProceedingname(String proceedingname) {
		this.proceedingname = proceedingname;
	}

	public String getRevcompany() {
		return revcompany;
	}

	public void setRevcompany(String revcompany) {
		this.revcompany = revcompany;
	}

	public String getRevcompanyname() {
		return revcompanyname;
	}

	public void setRevcompanyname(String revcompanyname) {
		this.revcompanyname = revcompanyname;
	}

	public String getProceedingno() {
		return proceedingno;
	}

	public void setProceedingno(String proceedingno) {
		this.proceedingno = proceedingno;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoutid() {
		return routid;
	}

	public void setRoutid(String routid) {
		this.routid = routid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	public String getBackprepayno() {
		return backprepayno;
	}

	public void setBackprepayno(String backprepayno) {
		this.backprepayno = backprepayno;
	}

	public String getPrepayno() {
		return prepayno;
	}

	public void setPrepayno(String prepayno) {
		this.prepayno = prepayno;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getAffirmestate() {
		return affirmestate;
	}

	public void setAffirmestate(String affirmestate) {
		this.affirmestate = affirmestate;
	}

	public String getAffirmtime() {
		return affirmtime;
	}

	public void setAffirmtime(String affirmtime) {
		this.affirmtime = affirmtime;
	}

	public String getAffirmexplain() {
		return affirmexplain;
	}

	public void setAffirmexplain(String affirmexplain) {
		this.affirmexplain = affirmexplain;
	}

	public String getPayestate() {
		return payestate;
	}

	public void setPayestate(String payestate) {
		this.payestate = payestate;
	}

	public String getPaytime() {
		return paytime;
	}

	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}

	public String getPrepaydate() {
		return prepaydate;
	}

	public void setPrepaydate(String prepaydate) {
		this.prepaydate = prepaydate;
	}

	public String getInputer() {
		return inputer;
	}

	public void setInputer(String inputer) {
		this.inputer = inputer;
	}

	public String getInputdate() {
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	public String getApprovedstaff() {
		return approvedstaff;
	}

	public void setApprovedstaff(String approvedstaff) {
		this.approvedstaff = approvedstaff;
	}

	public int getIsapproved() {
		return isapproved;
	}

	public void setIsapproved(int isapproved) {
		this.isapproved = isapproved;
	}


	public String getClosedstaff() {
		return closedstaff;
	}

	public void setClosedstaff(String closedstaff) {
		this.closedstaff = closedstaff;
	}

	public int getIsclosed() {
		return isclosed;
	}

	public void setIsclosed(int isclosed) {
		this.isclosed = isclosed;
	}

	public String getClosedtime() {
		return closedtime;
	}

	public void setClosedtime(String closedtime) {
		this.closedtime = closedtime;
	}



	@Override
	public String toString() {
		return "DetailsInformation [checkid=" + checkid + ", checkdate=" + checkdate + ", staff=" + staff
				+ ", feecompany=" + feecompany + ", feecompanyname=" + feecompanyname + ", feeunitno=" + feeunitno
				+ ", feeunit=" + feeunit + ", revtype=" + revtype + ", payee=" + payee + ", payeename=" + payeename
				+ ", proceedingname=" + proceedingname + ", revcompany=" + revcompany + ", revcompanyname="
				+ revcompanyname + ", proceedingno=" + proceedingno + ", remark=" + remark + ", routid=" + routid
				+ ", status=" + status + ", prepayamt=" + prepayamt + ", amount=" + amount + ", totalamount="
				+ totalamount + ", backamount=" + backamount + ", backprepayno=" + backprepayno + ", prepayno="
				+ prepayno + ", paytype=" + paytype + ", affirmestate=" + affirmestate + ", affirmtime=" + affirmtime
				+ ", affirmexplain=" + affirmexplain + ", payestate=" + payestate + ", paytime=" + paytime
				+ ", prepaydate=" + prepaydate + ", inputer=" + inputer + ", inputdate=" + inputdate
				+ ", approvedstaff=" + approvedstaff + ", isapproved=" + isapproved + ", closedstaff=" + closedstaff
				+ ", isclosed=" + isclosed + ", closedtime=" + closedtime + ", approvedtime=" + approvedtime + ", bank="
				+ bank + ", bankcode=" + bankcode + ", billtype=" + billtype + ", getBilltype()=" + getBilltype()
				+ ", getBank()=" + getBank() + ", getBankcode()=" + getBankcode() + ", getPrepayamt()=" + getPrepayamt()
				+ ", getAmount()=" + getAmount() + ", getTotalamount()=" + getTotalamount() + ", getBackamount()="
				+ getBackamount() + ", getFeecompanyname()=" + getFeecompanyname() + ", getApprovedtime()="
				+ getApprovedtime() + ", getCheckid()=" + getCheckid() + ", getCheckdate()=" + getCheckdate()
				+ ", getStaff()=" + getStaff() + ", getFeecompany()=" + getFeecompany() + ", getFeeunitno()="
				+ getFeeunitno() + ", getFeeunit()=" + getFeeunit() + ", getRevtype()=" + getRevtype() + ", getPayee()="
				+ getPayee() + ", getPayeename()=" + getPayeename() + ", getProceedingname()=" + getProceedingname()
				+ ", getRevcompany()=" + getRevcompany() + ", getRevcompanyname()=" + getRevcompanyname()
				+ ", getProceedingno()=" + getProceedingno() + ", getRemark()=" + getRemark() + ", getRoutid()="
				+ getRoutid() + ", getStatus()=" + getStatus() + ", getBackprepayno()=" + getBackprepayno()
				+ ", getPrepayno()=" + getPrepayno() + ", getPaytype()=" + getPaytype() + ", getAffirmestate()="
				+ getAffirmestate() + ", getAffirmtime()=" + getAffirmtime() + ", getAffirmexplain()="
				+ getAffirmexplain() + ", getPayestate()=" + getPayestate() + ", getPaytime()=" + getPaytime()
				+ ", getPrepaydate()=" + getPrepaydate() + ", getInputer()=" + getInputer() + ", getInputdate()="
				+ getInputdate() + ", getApprovedstaff()=" + getApprovedstaff() + ", getIsapproved()=" + getIsapproved()
				+ ", getClosedstaff()=" + getClosedstaff() + ", getIsclosed()=" + getIsclosed() + ", getClosedtime()="
				+ getClosedtime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}



	



	
	
	
}
