package com.hub.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.KsmAuditQueryDao;
import com.hub.entity.AuditLevelQuery;
import com.hub.entity.Suggestion;
import com.hub.entity.UserAndSuggest;
import com.hub.tools.SplitStr;

@Service("KsmAuditQueryServiceImpl")
public class KsmAuditQueryServiceImpl {

	@Autowired(required = false) 
	private KsmAuditQueryDao ksmAuditQueryDao;
	
	/**
	 * ����һ��������ʾ������Ϣ������Ҫ�������������Ѿ���˹��������˵Ľ��鶼ȡ����
	 * itemType=2,��Ӧ���Ǳ�taskdsh��itemtype�ֶ�
	 */
	public ArrayList<UserAndSuggest> getAllPeopleSuggest(String systemcode){
		ArrayList<UserAndSuggest> allPeopleSuggest = ksmAuditQueryDao.getAllPeopleSuggestKsm(systemcode);
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
		
		Collections.reverse(output);
		return output;
	}
	
	
	public void insertSuggest(String checkid,String shr,String message,String message1) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String date = df.format(new Date());
		ksmAuditQueryDao.insertSuggestKsm(checkid,date,shr,message, message1);
	}
	
	public AuditLevelQuery getAuditLevelQuery(String stepremarks,
			String systemcode) {
		AuditLevelQuery auditLevelQuery = ksmAuditQueryDao.getAuditLevelQueryKsm(stepremarks, systemcode);
		return auditLevelQuery;
	}
	/**
	 * �����taskdsh���еõ��Ľ���message���������
	 */

	public Suggestion getSuggest(String checkid, String systemcode) {
		ArrayList<String> str = new ArrayList<String>();
		AuditLevelQuery auditLevelQuery = ksmAuditQueryDao.getAuditLevelQueryKsm(checkid, systemcode);
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
		ksmAuditQueryDao.disagreeKsm(userLevel, systemcode);
	}

	public void agree(String userLevel, String systemcode) {
		ksmAuditQueryDao.agreeKsm(userLevel, systemcode);
	}

	public String getStatus(String username, String systemcode) {
		String status = ksmAuditQueryDao.getStatusKsm(username, systemcode);
		return status;
	}

}
