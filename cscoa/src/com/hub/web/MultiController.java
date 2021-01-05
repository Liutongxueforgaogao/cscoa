package com.hub.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hub.dao.DetailsInformationDao;
import com.hub.dao.MultiAuditQueryDao;
import com.hub.entity.AuditPage;
import com.hub.entity.DetailsInformation;
import com.hub.entity.InformationExm00101;
import com.hub.entity.InfromationOfExm001Addition;
import com.hub.entity.UserAndSuggest;
import com.hub.service.impl.AuditLevelQueryServiceImpl;
import com.hub.service.impl.AuditPageServiceImpl;
import com.hub.service.impl.DetailsPageServiceImpl;
import com.hub.service.impl.MultiAuditPageServiceImpl;
import com.hub.service.impl.MultiAuditQueryServiceImpl;
import com.hub.service.impl.UserServiceImpl;
import com.hub.tools.ChangeTime;

import net.sf.json.JSONObject;
/**
 * �ಿ����˿���������ҳ��main.jsp�����ѡ�����յ����ಿ�ţ��������ͻ���뵽����������е�������
 * @author winv87
 *
 */
@Controller
public class MultiController {
	
	@Autowired(required = false) 
	@Qualifier("UserServiceImpl")
	private UserServiceImpl userService;
	
	@Autowired(required = false) 
	@Qualifier("AuditPageServiceImpl")
	private AuditPageServiceImpl auditPageService;
	
	@Autowired(required = false) 
	@Qualifier("DetailsPageServiceImpl")
	private DetailsPageServiceImpl detailsPageService;
	
	@Autowired(required = false)
	@Qualifier("AuditLevelQueryServiceImpl")
	private AuditLevelQueryServiceImpl auditLevelQueryService;
	
	@Autowired(required = false)
	@Qualifier("MultiAuditQueryServiceImpl")
	private MultiAuditQueryServiceImpl multiAuditQueryService;
	
	//��DAOֱ�ӵ���ȡ���ݣ�û�о���Service�㣬�ӽṹ����˵����ѧ�����Ǻ�ֱ��
	@Autowired(required = false) 
	private DetailsInformationDao detailsInformationDao;
	
	@Autowired() 
	@Qualifier("MultiAuditPageServiceImpl")
	private MultiAuditPageServiceImpl multiAuditPageService;
	
	@RequestMapping("/MultiAudit")
	private String auditPage(Map<String, Object> map,HttpServletRequest request){
		String username="";
		try {
			String checkid = request.getParameter("checkid");
			String pagestr = request.getParameter("pagenum");
			int pagenum = Integer.parseInt(pagestr);
			Cookie[] userCookie = request.getCookies();
			for(Cookie cookie : userCookie){
			    if(cookie.getName().equals("userLevel")){
			        username = cookie.getValue();
			        map.put("user", username);
			        map.put("username", detailsInformationDao.getName(username));//��ȡ������
			    }
			}
			List<AuditPage> allAudit = multiAuditPageService.getAllAudit(username, pagenum);
			map.put("audits", allAudit);
			map.put("pagenum", pagestr);
			//����δ��˵ĸ���
			int auditSize = multiAuditPageService.getAuditSize(username);
			int maxpage = auditSize/10;
			String maxpagestr = maxpage+"";
			map.put("maxpage", maxpage);
		} catch (Exception e) {
			map.put("error", "�б����ݻ�ȡ�쳣");
			return "multi_audit";
		}
		return "multi_audit";
	}
	
	/*
	 * ��ϸҳ������������Ϣ��
	 * 		������ϸ��Ϣ��checkidΨһ����
	 * 		�ӱ���ϸ��Ϣ��chencid��Ψһ����
	 * 		����������Ϣ������ͼƬ����
	 * 		�û���user��
	 * 		���״̬��
	 * 		���еĽ��顣
	 */
	
	@RequestMapping("/multi_details")
	private String detailsPage(HttpServletRequest request,Map<String, Object> map){
		String	checkid = request.getParameter("checkid");	
		if(checkid!=""){
			//��ȡ�ಿ�ŵĵ��ӵ���ϸ��Ϣ��������׳��쳣
			try {
				//ȡ������ϸ��Ϣ
				DetailsInformation detailsInformation = detailsPageService.getAllDetailsInformation(checkid);
				map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//��ȡ������������
				if(detailsInformation!=null){
					detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
					map.put("details", detailsInformation);
				}
				//ȡexm00101��ϸ��Ϣ
				List<InformationExm00101> allInformationByExm00101 = detailsInformationDao.getAllInformationByExm00101(checkid);
				if(allInformationByExm00101!=null){
					map.put("exm00101", allInformationByExm00101);
				}
				//ȡexm001�ĸ�����Ϣ��20170512��ӡ�
				InfromationOfExm001Addition allInformationOfExm001Addition = detailsInformationDao.getAllInformationOfExm001Addition(checkid);
				allInformationOfExm001Addition.setImage(UserController.imgbase+allInformationOfExm001Addition.getImage());
				allInformationOfExm001Addition.setImageTwo(UserController.imgbase+allInformationOfExm001Addition.getImageTwo());
				allInformationOfExm001Addition.setImageThree(UserController.imgbase+allInformationOfExm001Addition.getImageThree());
				allInformationOfExm001Addition.setImageFour(UserController.imgbase+allInformationOfExm001Addition.getImageFour());
				allInformationOfExm001Addition.setImageFive(UserController.imgbase+allInformationOfExm001Addition.getImageFive());
				if(allInformationOfExm001Addition!=null){
					map.put("exm001add", allInformationOfExm001Addition);
				}
			} catch (Exception e) {
				map.put("error", "��ϸ���ݻ�ȡ�쳣");
				return "multi_details";
			}
		}
		
		//ȡ��cookie�е��û���
		Cookie[] userCookie = request.getCookies();
		String username="";
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	            username = cookie.getValue();
	            map.put("user", username);
	            map.put("username", detailsInformationDao.getName(username));//��ȡ������
	        }
	    }
		map.put("user", username);
		//��ȡ�ಿ�ŵĵ��ӵ����״̬��������׳��쳣
		try {
			//��ȡ���״̬
			String status = multiAuditQueryService.getStatus(username, checkid);
			//�����״̬����ǰ���ж�
			if(status==null){
				map.put("atag", "0");//��ʾδ���			
			}else if(status.equals("1")){
				map.put("atag", "1");//��ʾ��ͨ��						
			}else if(status.equals("0")){
				map.put("atag", "-1");//��ʾδͨ��
			}
		} catch (Exception e) {
			map.put("error", "״̬��ȡ�쳣");
			return "multi_details";
		}
		//��ȡ�ಿ�ŵĵ��ӵ����̽��飬������׳��쳣
		try {
			//ȡ�����������˵Ľ���
			ArrayList<UserAndSuggest> allPeopleSuggest = multiAuditQueryService.getAllPeopleSuggest(checkid);
			map.put("allPeopleSuggest", allPeopleSuggest);
		} catch (Exception e) {
			map.put("error", "���̽����ȡ�쳣");
			return "multi_details";
		}
		return "multi_details";
	}
	
	//�������
	@RequestMapping("/multi_insertSuggest")
	public String insertSuggest(Map<String, Object> map,HttpServletRequest request){
		//ȡ��ǰ̨���ݵĻظ���Ϣ�͵���
		String replyMessage = request.getParameter("replyMessage");
		String replyMessage1 = request.getParameter("replyMessage");
		String checkid = request.getParameter("checkid");
		
		//ȡ��cookie�е��û���
		String username="";
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	            username = cookie.getValue();
	            map.put("user", username);
	            map.put("username", detailsInformationDao.getName(username));//��ȡ������
	        }
	     } 
		if(replyMessage!=null&&!replyMessage.equals("")&&username!=""){
			multiAuditQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);			
		}else{
			//����ظ������ǿ��ַ��������ش�����Ϣ
			map.put("error","���벻����Ϊ��");
		}
		
		
		if(checkid!=""){
			
			try {
				//ȡ������ϸ��Ϣ
				DetailsInformation detailsInformation = detailsPageService.getAllDetailsInformation(checkid);
				map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//��ȡ������������
				if(detailsInformation!=null){
					detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
					map.put("details", detailsInformation);
				}
				//ȡexm00101��ϸ��Ϣ
				List<InformationExm00101> allInformationByExm00101 = detailsInformationDao.getAllInformationByExm00101(checkid);
				if(allInformationByExm00101!=null){
					map.put("exm00101", allInformationByExm00101);
				}
				//ȡexm001�ĸ�����Ϣ��20170512��ӡ�
				InfromationOfExm001Addition allInformationOfExm001Addition = detailsInformationDao.getAllInformationOfExm001Addition(checkid);
				allInformationOfExm001Addition.setImage(UserController.imgbase+allInformationOfExm001Addition.getImage());
				allInformationOfExm001Addition.setImageTwo(UserController.imgbase+allInformationOfExm001Addition.getImageTwo());
				allInformationOfExm001Addition.setImageThree(UserController.imgbase+allInformationOfExm001Addition.getImageThree());
				allInformationOfExm001Addition.setImageFour(UserController.imgbase+allInformationOfExm001Addition.getImageFour());
				allInformationOfExm001Addition.setImageFive(UserController.imgbase+allInformationOfExm001Addition.getImageFive());
				if(allInformationOfExm001Addition!=null){
					map.put("exm001add", allInformationOfExm001Addition);
				}
			} catch (Exception e) {
				map.put("error", "��ϸ���ݻ�ȡ�쳣");
				return "multi_details";
			}
		}
		
		try {
			//ȡ�����������˵Ľ���
			ArrayList<UserAndSuggest> allPeopleSuggest = multiAuditQueryService.getAllPeopleSuggest(checkid);
			map.put("allPeopleSuggest", allPeopleSuggest);
		} catch (Exception e) {
			map.put("error", "���̽����ȡ�쳣");
			return "multi_details";
		}
		
		try {
			//��ȡ���״̬
			String status = multiAuditQueryService.getStatus(username, checkid);
			//�����״̬����ǰ���ж�
			if(status==null){
				map.put("atag", "0");//��ʾδ���			
			}else if(status.equals("1")){
				map.put("atag", "1");//��ʾ��ͨ��						
			}else if(status.equals("0")){
				map.put("atag", "-1");//��ʾδͨ��
			}
		} catch (Exception e) {
			map.put("error", "״̬��ȡ�쳣");
			return "multi_details";
		}
		
		return "multi_details";
	}
	/**
	 * ��ذ�ť����������������
	 * @return
	 */
	@RequestMapping("/multi_disagree")
	public String disagree(Map<String, Object> map,HttpServletRequest request){
		System.out.println("�����ؿ�����");
		String username="";
		String replyMessage = request.getParameter("replyMessage");
		String replyMessage1 = request.getParameter("replyMessage");
		String checkid = request.getParameter("checkid");
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	        	username = cookie.getValue();
	        	map.put("user", username);
	        	map.put("username", detailsInformationDao.getName(username));//��ȡ������
	        }
	    } 
		//��ȡ���״̬
		String status = multiAuditQueryService.getStatus(username, checkid);
		
		//��غ���Ҫ���ϴ����Ϣ
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(�Ѵ��):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(�Ѵ��)";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String date = df.format(new Date());
		if(username!=null&&!username.equals("")&&status==null){
			try {
				multiAuditQueryService.insertSuggest(checkid,username, replyMessage,replyMessage1);
				multiAuditQueryService.disagree(username, checkid);
			} catch (Exception e) {
				map.put("error", "���ʧ��");
				e.printStackTrace();
			}	
			//status="0";
		}
		//��ѯ��ϸ��Ϣ
		if(checkid!=""){
			try {
				//ȡ������ϸ��Ϣ
				DetailsInformation detailsInformation = detailsPageService.getAllDetailsInformation(checkid);
				map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//��ȡ������������
				if(detailsInformation!=null){
					detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
					map.put("details", detailsInformation);
				}
				//ȡexm00101��ϸ��Ϣ
				List<InformationExm00101> allInformationByExm00101 = detailsInformationDao.getAllInformationByExm00101(checkid);
				if(allInformationByExm00101!=null){
					map.put("exm00101", allInformationByExm00101);
				}
				//ȡexm001�ĸ�����Ϣ��20170512��ӡ�
				InfromationOfExm001Addition allInformationOfExm001Addition = detailsInformationDao.getAllInformationOfExm001Addition(checkid);
				allInformationOfExm001Addition.setImage(UserController.imgbase+allInformationOfExm001Addition.getImage());
				allInformationOfExm001Addition.setImageTwo(UserController.imgbase+allInformationOfExm001Addition.getImageTwo());
				allInformationOfExm001Addition.setImageThree(UserController.imgbase+allInformationOfExm001Addition.getImageThree());
				allInformationOfExm001Addition.setImageFour(UserController.imgbase+allInformationOfExm001Addition.getImageFour());
				allInformationOfExm001Addition.setImageFive(UserController.imgbase+allInformationOfExm001Addition.getImageFive());
				if(allInformationOfExm001Addition!=null){
					map.put("exm001add", allInformationOfExm001Addition);
				}
			} catch (Exception e) {
				map.put("error", "��ϸ���ݻ�ȡ�쳣");
				return "multi_details";
			}
		}
		try {
			//ȡ�����������˵Ľ���
			ArrayList<UserAndSuggest> allPeopleSuggest = multiAuditQueryService.getAllPeopleSuggest(checkid);
			map.put("allPeopleSuggest", allPeopleSuggest);
		} catch (Exception e) {
			map.put("error", "���̽����ȡ�쳣");
			return "multi_details";
		}
		//��ȡ���״̬
		status = multiAuditQueryService.getStatus(username, checkid);
		try {
			//�����״̬����ǰ���ж�
			if(status==null){
				map.put("atag", "0");//��ʾδ���			
			}else if(status.equals("1")){
				map.put("atag", "1");//��ʾ��ͨ��						
			}else if(status.equals("0")){
				map.put("atag", "-1");//��ʾδͨ��
			}
		} catch (Exception e) {
			map.put("error", "״̬��ȡ�쳣");
			return "multi_details";
		}
		
		return "multi_details";
	}
	
	/**
	 * ͬ�ⰴť����������������
	 * @return
	 */
	@RequestMapping("/multi_agree")
	public String agree(Map<String, Object> map,HttpServletRequest request){
		System.out.println("����ͬ�������");
		String username="";
		String replyMessage = request.getParameter("replyMessage");
		String replyMessage1 = request.getParameter("replyMessage");
		String checkid = request.getParameter("checkid");
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
			if(cookie.getName().equals("userLevel")){
				username = cookie.getValue();
				map.put("user", username);
				map.put("username", detailsInformationDao.getName(username));//��ȡ������
			}
		} 
		//��ȡ���״̬
		String status = multiAuditQueryService.getStatus(username, checkid);

		//ͬ�����Ҫ���ϴ����Ϣ
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(��ͬ��):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(��ͬ��)";
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String date = df.format(new Date());
		if(username!=null&&status==null){
			try {
				multiAuditQueryService.insertSuggest(checkid,username, replyMessage,replyMessage1);
				multiAuditQueryService.agree(username, checkid);
			} catch (Exception e) {
				System.out.println("ͬ��ʧ��");
				map.put("error", "ͬ��ʧ��");
				e.printStackTrace();
			}
			//status="1";
		}
		//��ѯ��ϸ��Ϣ
		if(checkid!=""){
			try {
				//ȡ������ϸ��Ϣ
				DetailsInformation detailsInformation = detailsPageService.getAllDetailsInformation(checkid);
				map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//��ȡ������������
				if(detailsInformation!=null){
					detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
					map.put("details", detailsInformation);
				}
				//ȡexm00101��ϸ��Ϣ
				List<InformationExm00101> allInformationByExm00101 = detailsInformationDao.getAllInformationByExm00101(checkid);
				if(allInformationByExm00101!=null){
					map.put("exm00101", allInformationByExm00101);
				}
				//ȡexm001�ĸ�����Ϣ��20170512��ӡ�
				InfromationOfExm001Addition allInformationOfExm001Addition = detailsInformationDao.getAllInformationOfExm001Addition(checkid);
				allInformationOfExm001Addition.setImage(UserController.imgbase+allInformationOfExm001Addition.getImage());
				allInformationOfExm001Addition.setImageTwo(UserController.imgbase+allInformationOfExm001Addition.getImageTwo());
				allInformationOfExm001Addition.setImageThree(UserController.imgbase+allInformationOfExm001Addition.getImageThree());
				allInformationOfExm001Addition.setImageFour(UserController.imgbase+allInformationOfExm001Addition.getImageFour());
				allInformationOfExm001Addition.setImageFive(UserController.imgbase+allInformationOfExm001Addition.getImageFive());
				if(allInformationOfExm001Addition!=null){
					map.put("exm001add", allInformationOfExm001Addition);
				}
			} catch (Exception e) {
				map.put("error", "��ϸ���ݻ�ȡ�쳣");
				return "multi_details";
			}
		}
		try {
			//ȡ�����������˵Ľ���
			ArrayList<UserAndSuggest> allPeopleSuggest = multiAuditQueryService.getAllPeopleSuggest(checkid);
			map.put("allPeopleSuggest", allPeopleSuggest);
		} catch (Exception e) {
			map.put("error", "���̽����ȡ�쳣");
			return "multi_details";
		}
		//��ȡ���״̬
		status = multiAuditQueryService.getStatus(username, checkid);
		try {
			//�����״̬����ǰ���ж�
			if(status==null){
				map.put("atag", "0");//��ʾδ���			
			}else if(status.equals("1")){
				map.put("atag", "1");//��ʾ��ͨ��						
			}else if(status.equals("0")){
				map.put("atag", "-1");//��ʾδͨ��
			}
		} catch (Exception e) {
			map.put("error", "״̬��ȡ�쳣");
			return "multi_details";
		}
		return "multi_details";
	}
	
	@RequestMapping("/multi_searchto")
	public String search(HttpServletResponse response){
/*		try {
			response.sendRedirect("search.jsp");//�ض���ᵼ�´��ݵĲ�����ʧ
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return "multi_search";
	}
	
	@RequestMapping("/multi_searchByCondition")
	public String searchByCondition (Map<String, Object> map,HttpServletRequest request){
		
		String pddh = null;//��˵���
		String pdrqstart = null;//������ڿ�ʼ
		String pdrqend = null;//������ڿ�ʼ
		String pdzt = null;//���״̬
		String username = "";//�û���
		try {
			String  stuname = request.getParameter("condition");
			System.out.println(stuname);
			String str = new String(stuname.getBytes("ISO-8859-1"),"utf-8");
			JSONObject jb = new JSONObject();
			pddh = (String) jb.fromObject(str).get("pddh");
			pdrqstart = (String) jb.fromObject(str).get("pdrqstart");
			pdrqend = (String) jb.fromObject(str).get("pdrqend");
			pdrqstart=pdrqstart+" 00:00:00";//����������ڿ�ʼ
			pdrqend=pdrqend+" 23:59:59";//����������ڽ���
//			pdzt = (String) jb.fromObject(str).get("pdkwv");
			pdzt = (String) jb.fromObject(stuname).get("pdkwv");
		/*	if(pdzt==null){//��֪��Ϊʲô�����ݿ�鲻����
				pdzt="δͨ��";
			}*/
			pdzt=pdzt+"";
			Cookie[] userCookie = request.getCookies();
			for(Cookie cookie : userCookie){
			    if(cookie.getName().equals("userLevel")){
			        username = cookie.getValue();
			        map.put("user", username);
			        map.put("username", detailsInformationDao.getName(username));//��ȡ������
			    }
			}
		} catch (Exception e) {
			map.put("error", "��ѯ����");
			return "multi_search";
		}
		List<AuditPage> allAudit = multiAuditPageService.getAuditBycondition(username, pddh, pdrqstart, pdrqend, pdzt);
		int size = allAudit.size();
		map.put("multi_size",size);
//		System.out.println(allAudit.size());
//		Iterator<AuditPage> it = allAudit.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next().toString());
//		}
		Iterator<AuditPage> it = allAudit.iterator();
		while(it.hasNext()){
			AuditPage auditPage = it.next();
			System.out.println("�ಿ�ţ�"+auditPage.getName());//������
			String datestr = auditPage.getDate();
			if(datestr!=null) {
				String[] datasplit = datestr.split(" ");
				auditPage.setDate(datasplit[0]);
				
			}
		}
		map.put("audits", allAudit);
		
		return "multi_search";
	}
	
}
	

