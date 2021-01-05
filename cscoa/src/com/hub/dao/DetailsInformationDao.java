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
 * ���ӵ���ϸ��Ϣ��ȡ��DAO
	private String checkid;				//���յ���:checkid
	private String inputdate;			//�������ڣ�[inputdate]
	private String staff;				//�����ˣ�[staff]
	private String companyno;			//������˾��ţ�[companyno]
	private String feeunitno;			//���ֱ��룺[feeunitno]
	private String feeunit;				//�������ƣ�[feeunit]
	private String payee;				//�ܿ��ˣ�[payee]
	private String payeename;			//�ܿ���������[payeename]
	private String proceedingname;		//�����������ƣ�[proceedingname]
	private String revcompany;			//�տλ��revcompany
	private String revcompanyname;		//�տλ����;[revcompanyname]
 * @author hub
 *
 */
@Repository
public interface DetailsInformationDao {
	//���ݱ�ţ���ȡ����
	String getName(String empid);
	//���ݹ�˾��ţ���ȡ��˾����
	String getCompany(String companyid);
	//��ѯ���������ϢDAO
	//����exm001
	DetailsInformation getAllDetailsInformation(String checkid);
	//exm00101�����ţ�
	List<InformationExm00101> getAllInformationByExm00101(String checkid);
	//exm00102��ͨ����ϸ
	List<InformationExm00102> getAllInformationByExm00102(String checkid);
	//exm00103���÷�
	List<InformationExm00103> getAllInformationByExm00103(String checkid);
	//exm001�Ĳ��䲿��
	InfromationOfExm001Addition getAllInformationOfExm001Addition(String checkid);
	//fpm001��ϸ��Ϣ
	InformationFpm001 getAllInformationFpm001(String checkid);
	//Rtm001
	Rtm001 getRtm001DetailsInformation(String checkid);
	//Rtm00101
	List<Rtm00101> getAllInformationByRtm00101(String checkid);
	
	//2018-03-13����
	//Rtm002
	Rtm002 getRtm002DetailsInformation(String checkid);
	//Rtm00201
	List<Rtm00201> getAllInformationByRtm00201(String checkid);
	
	
	//�����տλ���룬���ҵ�λ����
	String getRevcompanyname(@Param("revcompany")String revcompany,@Param("feecompany")String feecompany);
	
	//���������룬�����������
	String getExpensename(String expensetype);
	//���ݴ����֧����ʽ
	
	String getPaytype(String drawmannerno);
	//�����տλ���룬���ҵ�λ����
	String getBankAndBankno(@Param("applycode")String applycode,@Param("companyid")String companyid);
	
}
