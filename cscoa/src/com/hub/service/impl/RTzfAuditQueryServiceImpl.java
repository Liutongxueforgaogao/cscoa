package com.hub.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.AuditLevelQueryDao;
import com.hub.dao.RTyanshouAuditLevelQueryDao;
import com.hub.dao.RTyanshouAuditPageDao;
import com.hub.dao.RTzfAuditLevelQueryDao;
import com.hub.entity.AuditLevelQuery;
import com.hub.entity.Suggestion;
import com.hub.entity.UserAndSuggest;
import com.hub.service.AuditLevelQueryService;
import com.hub.tools.SplitStr;

@Service("RTzfAuditQueryServiceImpl")
public class RTzfAuditQueryServiceImpl implements AuditLevelQueryService {

	@Autowired(required = false) 
	private RTzfAuditLevelQueryDao rTzfAuditLevelQueryDao;
	
	/**
	 * ����һ��������ʾ������Ϣ������Ҫ�������������Ѿ���˹��������˵Ľ��鶼ȡ����
	 * itemType=2,��Ӧ���Ǳ�taskdsh��itemtype�ֶ�
	 */
	public ArrayList<UserAndSuggest> getAllPeopleSuggest(String systemcode){
		ArrayList<UserAndSuggest> allPeopleSuggest = rTzfAuditLevelQueryDao.getAllPeopleSuggest(systemcode);
		ArrayList<UserAndSuggest> output = new ArrayList<UserAndSuggest>();
		if(allPeopleSuggest!=null){
			for (UserAndSuggest item : allPeopleSuggest) {
				if(item.getSuggest()!=""&&item.getSuggest()!=null){
					//System.out.println("b:"+item.getSuggest()+"==="+item.getUser());
					String[] hfgroup = item.getSuggest().split(";");
					//�޸Ĵ����ݿ�ȡ�����Ļظ���Ϣ
					for (int i = 0; i < hfgroup.length; i++) {
						//System.out.println("a:"+hfgroup[i]);
						hfgroup[i]=SplitStr.splitSuggest(hfgroup[i]);	
						//System.out.println("6:"+hfgroup[i]);
						UserAndSuggest userSuggest = new UserAndSuggest();
						userSuggest.setUser(item.getUser());
						userSuggest.setSuggest(hfgroup[i]);
						output.add(userSuggest);
					}					
				}else{
//					��δ�ظ���Ϣ��������ʾ����
//					UserAndSuggest userSuggest = new UserAndSuggest();
//					userSuggest.setUser(item.getUser());
//					userSuggest.setSuggest("<font color='grey'>���޻ظ�</font>");
//					output.add(userSuggest);
				}
			}			
		}
		
		//System.out.println("7:"+output);
		Collections.reverse(output);
		return output;
	}
	
	
	public void insertSuggest(String checkid,String shr,String message,String message1) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String date = df.format(new Date());
		rTzfAuditLevelQueryDao.insertSuggest(checkid,date,shr,message, message1);
	}
	
	public AuditLevelQuery getAuditLevelQuery(String stepremarks,
			String systemcode) {
		AuditLevelQuery auditLevelQuery = rTzfAuditLevelQueryDao.getAuditLevelQuery(stepremarks, systemcode);
		return auditLevelQuery;
	}
	/**
	 * �����taskdsh���еõ��Ľ���message���������
	 */

	public Suggestion getSuggest(String checkid, String systemcode) {
		ArrayList<String> str = new ArrayList<String>();
		AuditLevelQuery auditLevelQuery = rTzfAuditLevelQueryDao.getAuditLevelQuery(checkid, systemcode);
		Suggestion suggestion = new Suggestion();
		//System.out.println("4:"+auditLevelQuery.getStepremarks());
		//��������˵�����
		suggestion.setUserLevelname(auditLevelQuery.getStepremarks());
		//��������˵Ļظ���Ϣ
		String suggestionAndTime = auditLevelQuery.getSuggestionAndTime();
		if(suggestionAndTime!=null){
			String[] hfgroup = suggestionAndTime.split(";");
			//�޸Ĵ����ݿ�ȡ�����Ļظ���Ϣ
			for (int i = 0; i < hfgroup.length; i++) {
				hfgroup[i]=SplitStr.splitSuggest(hfgroup[i]);	
				//System.out.println(hfgroup[i]);
			}
			ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(hfgroup));
			suggestion.setSuggest(arrayList);
		}
		//System.out.println(suggestion);
		return suggestion;
	}

	/**
	 * ��غ�ͬ�����
	 */
	
	public void disagree(String userLevel, String systemcode) {
		rTzfAuditLevelQueryDao.disagree(userLevel, systemcode);
	}

	
	public void agree(String userLevel, String systemcode) {
		rTzfAuditLevelQueryDao.agree(userLevel, systemcode);
	}


	
	public String getStatus(String username, String systemcode) {
		String status = rTzfAuditLevelQueryDao.getStatus(username, systemcode);
		return status;
	}

	//�������뽨��
	public void insertSuggestBatch(String checkids, String username, String replyMessage, String replyMessage1) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String date = df.format(new Date());
		//ȥ�����һ����
		String clearLastSpot = clearLastSpot(checkids);
		//��ÿһ�����Ŷ����ϵ�����
		String[] split = clearLastSpot.split(",");
		String result = "";
		for (int i = 0; i < split.length; i++) {
			result=result + "'"+split[0]+"',";
		}
		result = clearLastSpot(result);
		checkids = "("+result+")";
		rTzfAuditLevelQueryDao.insertSuggestBatch(checkids,date,username,replyMessage, replyMessage1);
	}
	
	//String[] checkidsarr = clearLastSpot.split(",");
	//������ͬ��
	public void disagrees(String userLevel, String systemcode) {
		String clearLastSpot = clearLastSpot(systemcode);
		String[] checkidsarr = clearLastSpot.split(",");
		rTzfAuditLevelQueryDao.disagrees(userLevel, checkidsarr);
	}

	//����ͬ��
	public void agrees(String userLevel, String systemcode) {
		String clearLastSpot = clearLastSpot(systemcode);
		String[] checkidsarr = clearLastSpot.split(",");
		rTzfAuditLevelQueryDao.agrees(userLevel, checkidsarr);
	}
	//ȥ�����һ������
	public String clearLastSpot(String checkids) {
		char[] charArray = checkids.toCharArray();
		String result = "";
		if(charArray.length>0) {
			result = checkids.substring(0, charArray.length-1);
		}
		return result;
	}
}
