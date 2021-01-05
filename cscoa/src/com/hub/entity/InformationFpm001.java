package com.hub.entity;
/**
 * 除了交通费明细和差旅费两个表，其他的表都是exm00101
 * //18个属性，对应着exm00101的18个字段，taxamtbak未取
 * @author winv87
 *
 */
public class InformationFpm001 {
	
	public String checkdate;//申请日期
	public int counts;//份数
	public String enddate;//有效期止
	public String header;//文件标题
	public int itemtype;//项目类型
	public String remarks;//用印目的
	public String revtype;//文件对象
	public int routid;//审批流程
	public String staff;//申请人
	public String startdate;//有效期起
	public String status;//审批状态
	public int id;//id
	public String checkid;//单号
	public String filetype;//文件类型
	public String stampertype;//所属印章1
	public String stampertype1;//所属印章2,2017/10/24新增字段
	public String stampertype2;//所属印章3,2017/10/24新增字段
	
	public String feecompany;//公司
	public String inputer;//输入人
	public String inputdate;//输入时间
	public String deptapprovedstaff;//审核人
	public String isdeptapproved;//审核状态
	public String deptapprovedtime;//审核时间
	public String huiwen1;//会文单位1
	public String huiwen2;//会文单位2
	public String huiwen3;//会文单位3
	public String image;
	public String imageTwo;
	public String imageThree;
	public String imageFour;
	public String imageFive;
	
	
	public InformationFpm001(String checkdate, int counts, String enddate, String header, int itemtype, String remarks,
			String revtype, int routid, String staff, String startdate, String status, int id, String checkid,
			String filetype, String stampertype, String stampertype1, String stampertype2, String feecompany,
			String inputer, String inputdate, String deptapprovedstaff, String isdeptapproved, String deptapprovedtime,
			String huiwen1, String huiwen2, String huiwen3, String image, String imageTwo, String imageThree,
			String imageFour, String imageFive) {
		super();
		this.checkdate = checkdate;
		this.counts = counts;
		this.enddate = enddate;
		this.header = header;
		this.itemtype = itemtype;
		this.remarks = remarks;
		this.revtype = revtype;
		this.routid = routid;
		this.staff = staff;
		this.startdate = startdate;
		this.status = status;
		this.id = id;
		this.checkid = checkid;
		this.filetype = filetype;
		this.stampertype = stampertype;
		this.stampertype1 = stampertype1;
		this.stampertype2 = stampertype2;
		this.feecompany = feecompany;
		this.inputer = inputer;
		this.inputdate = inputdate;
		this.deptapprovedstaff = deptapprovedstaff;
		this.isdeptapproved = isdeptapproved;
		this.deptapprovedtime = deptapprovedtime;
		this.huiwen1 = huiwen1;
		this.huiwen2 = huiwen2;
		this.huiwen3 = huiwen3;
		this.image = image;
		this.imageTwo = imageTwo;
		this.imageThree = imageThree;
		this.imageFour = imageFour;
		this.imageFive = imageFive;
	}

	public String getStampertype1() {
		return stampertype1;
	}

	public void setStampertype1(String stampertype1) {
		this.stampertype1 = stampertype1;
	}

	public String getStampertype2() {
		return stampertype2;
	}

	public void setStampertype2(String stampertype2) {
		this.stampertype2 = stampertype2;
	}

	public InformationFpm001() {
		super();
	}
	public String getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public int getItemtype() {
		return itemtype;
	}
	public void setItemtype(int itemtype) {
		this.itemtype = itemtype;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRevtype() {
		return revtype;
	}
	public void setRevtype(String revtype) {
		this.revtype = revtype;
	}
	public int getRoutid() {
		return routid;
	}
	public void setRoutid(int routid) {
		this.routid = routid;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getStampertype() {
		return stampertype;
	}
	public void setStampertype(String stampertype) {
		this.stampertype = stampertype;
	}
	public String getFeecompany() {
		return feecompany;
	}
	public void setFeecompany(String feecompany) {
		this.feecompany = feecompany;
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
	public String getDeptapprovedstaff() {
		return deptapprovedstaff;
	}
	public void setDeptapprovedstaff(String deptapprovedstaff) {
		this.deptapprovedstaff = deptapprovedstaff;
	}
	public String getIsdeptapproved() {
		return isdeptapproved;
	}
	public void setIsdeptapproved(String isdeptapproved) {
		this.isdeptapproved = isdeptapproved;
	}
	public String getDeptapprovedtime() {
		return deptapprovedtime;
	}
	public void setDeptapprovedtime(String deptapprovedtime) {
		this.deptapprovedtime = deptapprovedtime;
	}
	public String getHuiwen1() {
		return huiwen1;
	}
	public void setHuiwen1(String huiwen1) {
		this.huiwen1 = huiwen1;
	}
	public String getHuiwen2() {
		return huiwen2;
	}
	public void setHuiwen2(String huiwen2) {
		this.huiwen2 = huiwen2;
	}
	public String getHuiwen3() {
		return huiwen3;
	}
	public void setHuiwen3(String huiwen3) {
		this.huiwen3 = huiwen3;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageTwo() {
		return imageTwo;
	}
	public void setImageTwo(String imageTwo) {
		this.imageTwo = imageTwo;
	}
	public String getImageThree() {
		return imageThree;
	}
	public void setImageThree(String imageThree) {
		this.imageThree = imageThree;
	}
	public String getImageFour() {
		return imageFour;
	}
	public void setImageFour(String imageFour) {
		this.imageFour = imageFour;
	}
	public String getImageFive() {
		return imageFive;
	}
	public void setImageFive(String imageFive) {
		this.imageFive = imageFive;
	}

	@Override
	public String toString() {
		return "InformationFpm001 [checkdate=" + checkdate + ", counts=" + counts + ", enddate=" + enddate + ", header="
				+ header + ", itemtype=" + itemtype + ", remarks=" + remarks + ", revtype=" + revtype + ", routid="
				+ routid + ", staff=" + staff + ", startdate=" + startdate + ", status=" + status + ", id=" + id
				+ ", checkid=" + checkid + ", filetype=" + filetype + ", stampertype=" + stampertype + ", stampertype1="
				+ stampertype1 + ", stampertype2=" + stampertype2 + ", feecompany=" + feecompany + ", inputer="
				+ inputer + ", inputdate=" + inputdate + ", deptapprovedstaff=" + deptapprovedstaff
				+ ", isdeptapproved=" + isdeptapproved + ", deptapprovedtime=" + deptapprovedtime + ", huiwen1="
				+ huiwen1 + ", huiwen2=" + huiwen2 + ", huiwen3=" + huiwen3 + ", image=" + image + ", imageTwo="
				+ imageTwo + ", imageThree=" + imageThree + ", imageFour=" + imageFour + ", imageFive=" + imageFive
				+ "]";
	}
	
	
	
	
}
