package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hub.entity.DetailsInformation;
import com.hub.entity.InformationExm00101;
import com.hub.entity.InformationExm00102;
import com.hub.entity.InformationExm00103;
import com.hub.entity.InformationFpm001;
import com.hub.entity.InfromationOfExm001Addition;
import com.hub.entity.Rtm001;
import com.hub.entity.Rtm00101;
import com.hub.entity.Rtm002;
import com.hub.entity.Rtm00201;

/**
 * 单子的详细信息获取的DAO
	private String checkid;				//验收单号:checkid
	private String inputdate;			//申请日期：[inputdate]
	private String staff;				//申请人：[staff]
	private String companyno;			//所属公司编号：[companyno]
	private String feeunitno;			//部分编码：[feeunitno]
	private String feeunit;				//部门名称：[feeunit]
	private String payee;				//受款人：[payee]
	private String payeename;			//受款人姓名：[payeename]
	private String proceedingname;		//费用事项名称：[proceedingname]
	private String revcompany;			//收款单位：revcompany
	private String revcompanyname;		//收款单位名称;[revcompanyname]
 * @author hub
 *
 */
@Repository
public interface DetailsInformationDao {
	//根据编号，获取姓名
	String getName(String empid);
	//根据公司编号，获取公司名称
	String getCompany(String companyid);
	//查询各个表的信息DAO
	//主表exm001
	DetailsInformation getAllDetailsInformation(String checkid);
	//exm00101单部门，
	List<InformationExm00101> getAllInformationByExm00101(String checkid);
	//exm00102交通费明细
	List<InformationExm00102> getAllInformationByExm00102(String checkid);
	//exm00103差旅费
	List<InformationExm00103> getAllInformationByExm00103(String checkid);
	//exm001的补充部分
	InfromationOfExm001Addition getAllInformationOfExm001Addition(String checkid);
	//fpm001详细信息
	InformationFpm001 getAllInformationFpm001(String checkid);
	//Rtm001
	Rtm001 getRtm001DetailsInformation(String checkid);
	//Rtm00101
	List<Rtm00101> getAllInformationByRtm00101(String checkid);
	
	//2018-03-13新增
	//Rtm002
	Rtm002 getRtm002DetailsInformation(String checkid);
	//Rtm00201
	List<Rtm00201> getAllInformationByRtm00201(String checkid);
	
	
	//根据收款单位代码，查找单位名称
	String getRevcompanyname(@Param("revcompany")String revcompany,@Param("feecompany")String feecompany);
	
	//根据类别代码，查找所属类别
	String getExpensename(String expensetype);
	//根据代码查支付方式
	
	String getPaytype(String drawmannerno);
	//根据收款单位代码，查找单位名称
	String getBankAndBankno(@Param("applycode")String applycode,@Param("companyid")String companyid);
	
}
