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
import com.hub.dao.TempAuditQueryDao;
import com.hub.entity.AuditPage;
import com.hub.entity.DetailsInformation;
import com.hub.entity.InformationExm00101;
import com.hub.entity.InfromationOfExm001Addition;
import com.hub.entity.UserAndSuggest;
import com.hub.service.impl.AuditPageServiceImpl;
import com.hub.service.impl.DetailsPageServiceImpl;
import com.hub.service.impl.MultiAuditQueryServiceImpl;
import com.hub.service.impl.TempAuditPageServiceImpl;
import com.hub.service.impl.TempAuditQueryServiceImpl;
import com.hub.service.impl.UserServiceImpl;
import com.hub.tools.ChangeTime;

import net.sf.json.JSONObject;

/**
 * �ݸ������������ҳ��main.jsp�����ѡ���ݸ������뵥�����ͻ���뵽����������е�������
 * @author winv87
 *
 */
@Controller
public class TempController {
	
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
	@Qualifier("TempAuditQueryServiceImpl")
	private TempAuditQueryServiceImpl tempAuditQueryService;
	
	//��DAOֱ�ӵ���ȡ���ݣ�û�о���Service�㣬�ӽṹ����˵����ѧ�����Ǻ�ֱ��
	@Autowired(required = false) 
	private DetailsInformationDao detailsInformationDao;
	
	@Autowired(required = false)
	@Qualifier("TempAuditPageServiceImpl")
	private TempAuditPageServiceImpl tempAuditPageService;
	
	@RequestMapping("/TempAudit")
	private String auditPage(Map<String, Object> map,HttpServletRequest request){
		String username="";
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
		List<AuditPage> allAudit = tempAuditPageService.getAllAudit(username, pagenum);
		map.put("audits", allAudit);
		map.put("pagenum", pagestr);
		int auditSize = tempAuditPageService.getAuditSize(username);
		int maxpage = auditSize/10;
		String maxpagestr = maxpage+"";
		map.put("maxpage", maxpage);
		return "temp_audit";
	}
	
	@RequestMapping("/temp_details")
	private String detailsPage(HttpServletRequest request,Map<String, Object> map){
		 String	checkid = request.getParameter("checkid");	
		if(checkid!=""){
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
				map.put("exm00103", allInformationByExm00101);
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
		}
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
		
		//��ȡ���״̬
		String status = tempAuditQueryService.getStatus(username, checkid);
		//�����״̬����ǰ���ж�
		if(status==null){
			map.put("atag", "0");//��ʾδ���			
		}else if(status.equals("1")){
			map.put("atag", "1");//��ʾ��ͨ��						
		}else if(status.equals("0")){
			map.put("atag", "-1");//��ʾδͨ��
		}
		//ȡ�����������˵Ľ���
		ArrayList<UserAndSuggest> allPeopleSuggest = tempAuditQueryService.getAllPeopleSuggest(checkid);
		Iterator<UserAndSuggest> it = allPeopleSuggest.iterator();
		while(it.hasNext()){
			System.out.println("........:"+it.next().toString());
		}
		
		map.put("allPeopleSuggest", allPeopleSuggest);
		return "temp_details";
	}
	//�������
	@RequestMapping("/temp_insertSuggest")
	public String insertSuggest(Map<String, Object> map,HttpServletRequest request){
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
		//ȡ��ǰʱ�䲢ת��ΪString
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String date = df.format(new Date());
		if(replyMessage!=null&&username!=""){
			tempAuditQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);		
		}
		
		
		if(checkid!=""){
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
		}
		//ȡ�����������˵Ľ���
		ArrayList<UserAndSuggest> allPeopleSuggest = tempAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		
		//��ȡ���״̬
		String status = tempAuditQueryService.getStatus(username, checkid);
		//�����״̬����ǰ���ж�
		if(status==null){
			map.put("atag", "0");//��ʾδ���			
		}else if(status.equals("1")){
			map.put("atag", "1");//��ʾ��ͨ��						
		}else if(status.equals("0")){
			map.put("atag", "-1");//��ʾδͨ��
		}
		
		return "temp_details";
	}
	/**
	 * ��ذ�ť����������������
	 * @return
	 */
	@RequestMapping("/temp_disagree")
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
		//��غ���Ҫ���ϴ����Ϣ
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(�Ѵ��):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(�Ѵ��)";
		}	
		//��ȡ���״̬
		String status = tempAuditQueryService.getStatus(username, checkid);
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//		String date = df.format(new Date());
		if(username!=null&&!username.equals("")&&status==null){
			
			tempAuditQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);
			tempAuditQueryService.disagree(username, checkid);
			//status="0";
		}
		//��ѯ��ϸ��Ϣ
		if(checkid!=""){
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
		}
		//ȡ�����������˵Ľ���
		ArrayList<UserAndSuggest> allPeopleSuggest = tempAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		//��ȡ���״̬
		status = tempAuditQueryService.getStatus(username, checkid);
		//�����״̬����ǰ���ж�
		if(status==null){
			map.put("atag", "0");//��ʾδ���			
		}else if(status.equals("1")){
			map.put("atag", "1");//��ʾ��ͨ��						
		}else if(status.equals("0")){
			map.put("atag", "-1");//��ʾδͨ��
		}
		
		return "temp_details";
	}
	
	/**
	 * ͬ�ⰴť����������������
	 * @return
	 */
	@RequestMapping("/temp_agree")
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

		//ͬ�����Ҫ���ϴ����Ϣ
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(��ͬ��):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(��ͬ��)";
		}
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//		String date = df.format(new Date());
		//��ȡ���״̬
		String status = tempAuditQueryService.getStatus(username, checkid);
		
		if(username!=null&&status==null){
			try {
				tempAuditQueryService.insertSuggest(checkid,username, replyMessage,replyMessage1);	
				tempAuditQueryService.agree(username, checkid);
			} catch (Exception e) {
				System.out.println("ͬ��ʧ��");
				map.put("error", "ͬ��ʧ��");
				e.printStackTrace();
			}
			//status="1";
		}
		//��ѯ��ϸ��Ϣ
		if(checkid!=""){
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
		}
		//ȡ�����������˵Ľ���
		ArrayList<UserAndSuggest> allPeopleSuggest = tempAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		//��ȡ���״̬
		status = tempAuditQueryService.getStatus(username, checkid);
		
		//�����״̬����ǰ���ж�
		if(status==null){
			map.put("atag", "0");//��ʾδ���			
		}else if(status.equals("1")){
			map.put("atag", "1");//��ʾ��ͨ��						
		}else if(status.equals("0")){
			map.put("atag", "-1");//��ʾδͨ��
		}
		return "temp_details";
	}
	
	@RequestMapping("/temp_searchto")
	public String search(HttpServletResponse response){
		return "temp_search";
	}
	
	@RequestMapping("/temp_searchByCondition")
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
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<AuditPage> allAudit = tempAuditPageService.getAuditBycondition(username, pddh, pdrqstart, pdrqend, pdzt);
		int size = allAudit.size();
		map.put("temp_size",size);
		Iterator<AuditPage> it = allAudit.iterator();
		while(it.hasNext()){
			AuditPage auditPage = it.next();
			System.out.println("�ݸ��"+auditPage.getName());//������
			String datestr = auditPage.getDate();
			if(datestr!=null) {
				
				String[] datasplit = datestr.split(" ");
				auditPage.setDate(datasplit[0]);
			}
		}
		map.put("audits", allAudit);
		
		return "temp_search";
	}
}
