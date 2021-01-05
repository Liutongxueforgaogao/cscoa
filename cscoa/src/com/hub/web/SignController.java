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
import com.hub.entity.DetailsInformation;
import com.hub.entity.InformationExm00101;
import com.hub.entity.InformationFpm001;
import com.hub.entity.InfromationOfExm001Addition;
import com.hub.entity.SignPage;
import com.hub.entity.UserAndSuggest;
import com.hub.service.impl.AuditPageServiceImpl;
import com.hub.service.impl.DetailsPageServiceImpl;
import com.hub.service.impl.SignAuditPageServiceImpl;
import com.hub.service.impl.SignAuditQueryServiceImpl;
import com.hub.service.impl.UserServiceImpl;
import com.hub.tools.ChangeTime;
import com.hub.tools.SplitStr;

import net.sf.json.JSONObject;

/**
 * ���¿���������ҳ��main.jsp�����ѡ���ݸ������뵥�����ͻ���뵽����������е�������
 * @author winv87
 *
 */
@Controller
public class SignController {
	
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
	@Qualifier("SignAuditQueryServiceImpl")
	private SignAuditQueryServiceImpl signAuditQueryService;
	
	//��DAOֱ�ӵ���ȡ���ݣ�û�о���Service�㣬�ӽṹ����˵����ѧ�����Ǻ�ֱ��
	@Autowired(required = false) 
	private DetailsInformationDao detailsInformationDao;
	
	@Autowired(required = false)
	@Qualifier("SignAuditPageServiceImpl")
	private SignAuditPageServiceImpl signAuditPageService;
	
	@RequestMapping("/SignAudit")
	private String auditPage(Map<String, Object> map,HttpServletRequest request){
		String username="";
		String pagestr = request.getParameter("pagenum");
		int pagenum = Integer.parseInt(pagestr);
		Cookie[] userCookie = request.getCookies();
		if(userCookie.length!=0) {
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	            username = cookie.getValue();
	            map.put("user", username);
	            map.put("username", detailsInformationDao.getName(username));//��ȡ������
	        }
	    }
		}else {
			return "login";
		}
		List<SignPage> allAudit = signAuditPageService.getAllAudit(username, pagenum);
		//����ת�������˵�����
		Iterator<SignPage> it = allAudit.iterator();
		while(it.hasNext()) {
			SignPage next = it.next();
			next.setStaff(detailsInformationDao.getName(next.getStaff()));
			String filetype = next.getFiletype();
			if(filetype!=null&&filetype.equals("1")) {
				next.setFiletype("��ͬ");
			}else if(filetype!=null&&filetype.equals("2")) {
				next.setFiletype("������");
			}else if(filetype!=null&&filetype.equals("3")) {
				next.setFiletype("֤����");
			}else if(filetype!=null&&filetype.equals("4")) {
				next.setFiletype("����");
			}else if(filetype!=null&&filetype.equals("5")) {
				next.setFiletype("��������");
			}else if(filetype!=null&&filetype.equals("6")) {
				next.setFiletype("����");
			}else{
				next.setFiletype("����");
			}
	
			//����ת�����
			next.setOnlyshowcheckid(SplitStr.getTenStr(next.getCheckid()));
		}
		
		map.put("audits", allAudit);
		map.put("pagenum", pagestr);
		int auditSize = signAuditPageService.getAuditSize(username);
		int maxpage = auditSize/10;
		String maxpagestr = maxpage+"";
		map.put("maxpage", maxpage);
		return "sign_audit";
	}
	
	@RequestMapping("/sign_details")
	private String detailsPage(HttpServletRequest request,Map<String, Object> map){
		 String	checkid = request.getParameter("checkid");	
		if(checkid!=""){
			//ȡfpm001����Ϣ
			InformationFpm001 allInformationOffpm001 = detailsInformationDao.getAllInformationFpm001(checkid);
			//ת��ͼƬ��Ϣ
			transImage(allInformationOffpm001,map);
			//ת�������˵�����
			allInformationOffpm001.setStaff(detailsInformationDao.getName(allInformationOffpm001.getStaff()));
			//ת����˾����
			allInformationOffpm001.setFeecompany(detailsInformationDao.getCompany(allInformationOffpm001.getFeecompany()));
			
			//��ȡ�ļ����ͣ�ת�������֣��ֶ�filetype
			//1,��ͬ;2,������;3,֤����;4,����;5,��������;6,����  
			updatefile(allInformationOffpm001);
			
			if(allInformationOffpm001!=null){
				map.put("details", allInformationOffpm001);
			}
		}
		Cookie[] userCookie = request.getCookies();
		String username="";
		if(userCookie.length!=0) {
			for(Cookie cookie : userCookie){
		        if(cookie.getName().equals("userLevel")){
		            username = cookie.getValue();
		            map.put("user", username);
		            map.put("username", detailsInformationDao.getName(username));//��ȡ������
		        }
		    }
		}else {
			return "login";
		}
		
		map.put("user", username);
		
		//��ȡ���״̬
		String status = signAuditQueryService.getStatus(username, checkid);
		//�����״̬����ǰ���ж�
		if(status==null){
			map.put("atag", "0");//��ʾδ���			
		}else if(status.equals("1")){
			map.put("atag", "1");//��ʾ��ͨ��						
		}else if(status.equals("0")){
			map.put("atag", "-1");//��ʾδͨ��
		}
		//ȡ�����������˵Ľ���
		ArrayList<UserAndSuggest> allPeopleSuggest = signAuditQueryService.getAllPeopleSuggest(checkid);
		Iterator<UserAndSuggest> it = allPeopleSuggest.iterator();
		while(it.hasNext()){
			System.out.println("........:"+it.next().toString());
		}
		
		map.put("allPeopleSuggest", allPeopleSuggest);
		return "sign_details";
	}
	//�������
	@RequestMapping("/sign_insertSuggest")
	public String insertSuggest(Map<String, Object> map,HttpServletRequest request){
		String username="";
		String replyMessage = request.getParameter("replyMessage");
		String replyMessage1 = request.getParameter("replyMessage");
		String checkid = request.getParameter("checkid");
		Cookie[] userCookie = request.getCookies();
		if(userCookie.length!=0) {
			
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	            username = cookie.getValue();
	            map.put("user", username);
	            map.put("username", detailsInformationDao.getName(username));//��ȡ������
	        }
	     } 
		}else {
			return "login";
		}
		//ȡ��ǰʱ�䲢ת��ΪString
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String date = df.format(new Date());
		if(replyMessage!=null&&username!=""){
			signAuditQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);		
		}
		
		
		if(checkid!=""){
			//ȡfpm001����Ϣ
			InformationFpm001 allInformationOffpm001 = detailsInformationDao.getAllInformationFpm001(checkid);
			//ת��ͼƬ��ַ�����嵽ip��ַ
			//ת��ͼƬ��Ϣ
			transImage(allInformationOffpm001,map);
			//ת�������˵�����
			allInformationOffpm001.setStaff(detailsInformationDao.getName(allInformationOffpm001.getStaff()));
			//ת����˾����
			allInformationOffpm001.setFeecompany(detailsInformationDao.getCompany(allInformationOffpm001.getFeecompany()));
			
			//��ȡ�ļ����ͣ�ת�������֣��ֶ�filetype
			//1,��ͬ;2,������;3,֤����;4,����;5,��������;6,����  
			updatefile(allInformationOffpm001);
			//1,����;2,������;3,��ͬ��;4,�ܾ�����;5,������;6,������;7,���� ��������ӡ�������������

			
			if(allInformationOffpm001!=null){
				map.put("details", allInformationOffpm001);
			}
		}
		//ȡ�����������˵Ľ���
		ArrayList<UserAndSuggest> allPeopleSuggest = signAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		
		//��ȡ���״̬
		String status = signAuditQueryService.getStatus(username, checkid);
		//�����״̬����ǰ���ж�
		if(status==null){
			map.put("atag", "0");//��ʾδ���			
		}else if(status.equals("1")){
			map.put("atag", "1");//��ʾ��ͨ��						
		}else if(status.equals("0")){
			map.put("atag", "-1");//��ʾδͨ��
		}
		
		return "sign_details";
	}
	/**
	 * ��ذ�ť����������������
	 * @return
	 */
	@RequestMapping("/sign_disagree")
	public String disagree(Map<String, Object> map,HttpServletRequest request){
		System.out.println("�����ؿ�����");
		String username="";
		String replyMessage = request.getParameter("replyMessage");
		String replyMessage1 = request.getParameter("replyMessage");
		String checkid = request.getParameter("checkid");
		Cookie[] userCookie = request.getCookies();
		if(userCookie.length!=0) {
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	        	username = cookie.getValue();
	        	map.put("user", username);
	        	map.put("username", detailsInformationDao.getName(username));//��ȡ������
	        }
	    } 
		}else {
			return "login";
			
		}
		//��غ���Ҫ���ϴ����Ϣ
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(�Ѵ��):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(�Ѵ��)";
		}	
		//��ȡ���״̬
		String status = signAuditQueryService.getStatus(username, checkid);
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//		String date = df.format(new Date());
		if(username!=null&&!username.equals("")&&status==null){
			
			signAuditQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);
			signAuditQueryService.disagree(username, checkid);
			//status="0";
		}
		//��ѯ��ϸ��Ϣ
		if(checkid!=""){
			//ȡfpm001����Ϣ
			InformationFpm001 allInformationOffpm001 = detailsInformationDao.getAllInformationFpm001(checkid);
			//ת��ͼƬ��Ϣ
			transImage(allInformationOffpm001,map);
			//ת�������˵�����
			allInformationOffpm001.setStaff(detailsInformationDao.getName(allInformationOffpm001.getStaff()));
			//ת����˾����
			allInformationOffpm001.setFeecompany(detailsInformationDao.getCompany(allInformationOffpm001.getFeecompany()));
			
			//��ȡ�ļ����ͣ�ת�������֣��ֶ�filetype
			//1,��ͬ;2,������;3,֤����;4,����;5,��������;6,����  
			updatefile(allInformationOffpm001);
			
			if(allInformationOffpm001!=null){
				map.put("details", allInformationOffpm001);
			}
		}
		//ȡ�����������˵Ľ���
		ArrayList<UserAndSuggest> allPeopleSuggest = signAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		//��ȡ���״̬
		status = signAuditQueryService.getStatus(username, checkid);
		//�����״̬����ǰ���ж�
		if(status==null){
			map.put("atag", "0");//��ʾδ���			
		}else if(status.equals("1")){
			map.put("atag", "1");//��ʾ��ͨ��						
		}else if(status.equals("0")){
			map.put("atag", "-1");//��ʾδͨ��
		}
		
		return "sign_details";
	}
	
	/**
	 * ͬ�ⰴť����������������
	 * @return
	 */
	@RequestMapping("/sign_agree")
	public String agree(Map<String, Object> map,HttpServletRequest request){
		System.out.println("����ͬ�������");
		String username="";
		String replyMessage = request.getParameter("replyMessage");
		String replyMessage1 = request.getParameter("replyMessage");
		String checkid = request.getParameter("checkid");
		Cookie[] userCookie = request.getCookies();
		if(userCookie.length!=0) {
		for(Cookie cookie : userCookie){
			if(cookie.getName().equals("userLevel")){
				username = cookie.getValue();
				map.put("user", username);
				map.put("username", detailsInformationDao.getName(username));//��ȡ������
			}
		} 
		}else {
			return "login";
		}
		//���������ȡ����ֵ
		System.out.println("replyMessage:"+replyMessage);
		System.out.println("checkid:"+checkid);
		
		
		//ͬ�����Ҫ���ϴ����Ϣ
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(��ͬ��):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(��ͬ��)";
		}
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//		String date = df.format(new Date());
		//��ȡ���״̬
		String status = signAuditQueryService.getStatus(username, checkid);
		System.out.println("��ȡ״̬ͨ�����ԣ�"+status);
		if(username!=null&&status==null){
			try {
				signAuditQueryService.insertSuggest(checkid,username, replyMessage,replyMessage1);	
				signAuditQueryService.agree(username, checkid);
			} catch (Exception e) {
				System.out.println("ͬ��ʧ��");
				map.put("error", "ͬ��ʧ��");
				e.printStackTrace();
			}
			//status="1";
		}
		System.out.println("������Ϣͨ������");
		//��ѯ��ϸ��Ϣ
		if(checkid!=""){
			//ȡfpm001����Ϣ
			InformationFpm001 allInformationOffpm001 = detailsInformationDao.getAllInformationFpm001(checkid);
			System.out.println("�����ϸ��Ϣ��"+allInformationOffpm001.toString());
			transImage(allInformationOffpm001,map);
			System.out.println("ת����Ϣ�ɹ�");
			
			//ת�������˵�����
			allInformationOffpm001.setStaff(detailsInformationDao.getName(allInformationOffpm001.getStaff()));
			System.out.println("ת�������˳ɹ�");
			//ת����˾����
			allInformationOffpm001.setFeecompany(detailsInformationDao.getCompany(allInformationOffpm001.getFeecompany()));
			System.out.println("ת����˾�ɹ�");
			//��ȡ�ļ����ͣ�ת�������֣��ֶ�filetype
			//1,��ͬ;2,������;3,֤����;4,����;5,��������;6,����  
			String filetype = allInformationOffpm001.getFiletype();
			System.out.println("��ѯ�ļ����ͣ�"+filetype);
			//hub
			updatefile(allInformationOffpm001);
			if(allInformationOffpm001!=null){
				map.put("details", allInformationOffpm001);
			}
		}
		//ȡ�����������˵Ľ���
		ArrayList<UserAndSuggest> allPeopleSuggest = signAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		//��ȡ���״̬
		status = signAuditQueryService.getStatus(username, checkid);
		
		//�����״̬����ǰ���ж�
		if(status==null){
			map.put("atag", "0");//��ʾδ���			
		}else if(status.equals("1")){
			map.put("atag", "1");//��ʾ��ͨ��						
		}else if(status.equals("0")){
			map.put("atag", "-1");//��ʾδͨ��
		}
		return "sign_details";
	}
	
	@RequestMapping("/sign_searchto")
	public String search(HttpServletResponse response){
		return "sign_search";
	}
	
	@RequestMapping("/sign_searchByCondition")
	public String searchByCondition (Map<String, Object> map,HttpServletRequest request){
		System.out.println("########����");
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
			if(userCookie.length!=0) {
			for(Cookie cookie : userCookie){
			    if(cookie.getName().equals("userLevel")){
			        username = cookie.getValue();
			        map.put("user", username);
			        map.put("username", detailsInformationDao.getName(username));//��ȡ������
			    }
			}
			}else {
				return "login";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//System.out.println(username+"#"+pddh+"#"+pdrqstart+"#"+pdrqend+"#"+pdzt);
		List<SignPage> allAudit = signAuditPageService.getAuditBycondition(username, pddh, pdrqstart, pdrqend, pdzt);
		int size = allAudit.size();
		map.put("sign_size",size);
		//System.out.println(size);

		//����ת�������˵����֣����ļ�����
		Iterator<SignPage> it = allAudit.iterator();
		while(it.hasNext()) {
			SignPage next = it.next();
			next.setStaff(detailsInformationDao.getName(next.getStaff()));
			String filetype = next.getFiletype();
			if(filetype!=null&&filetype.equals("1")) {
				next.setFiletype("��ͬ");
			}else if(filetype!=null&&filetype.equals("2")) {
				next.setFiletype("������");
			}else if(filetype!=null&&filetype.equals("3")) {
				next.setFiletype("֤����");
			}else if(filetype!=null&&filetype.equals("4")) {
				next.setFiletype("����");
			}else if(filetype!=null&&filetype.equals("5")) {
				next.setFiletype("��������");
			}else if(filetype!=null&&filetype.equals("6")) {
				next.setFiletype("����");
			}else{
				next.setFiletype("����");
			}
			//����ת�����
			next.setOnlyshowcheckid(SplitStr.getTenStr(next.getCheckid()));
		}
		map.put("audits", allAudit);
		return "sign_search";
	}
	//�޸��ļ����ͺ͹���
	public void updatefile(InformationFpm001 inputSignPage) {
		//�޸��ļ�����
		String filetype = inputSignPage.getFiletype();
		if(filetype!=null) {
			if(filetype!=null&&filetype.equals("1")) {
				inputSignPage.setFiletype("��ͬ");
			}else if(filetype!=null&&filetype.equals("2")) {
				inputSignPage.setFiletype("������");
			}else if(filetype!=null&&filetype.equals("3")) {
				inputSignPage.setFiletype("֤����");
			}else if(filetype!=null&&filetype.equals("4")) {
				inputSignPage.setFiletype("����");
			}else if(filetype!=null&&filetype.equals("5")) {
				inputSignPage.setFiletype("��������");
			}else if(filetype!=null&&filetype.equals("6")) {
				inputSignPage.setFiletype("����");
			}else{
				inputSignPage.setFiletype("����");
			}
		}
		//�޸��ļ�����
		// 1,����;2,������;3,��ͬ��;4,�ܾ�����;5,������;6,������;7,����
		
		String stampertype = inputSignPage.getStampertype();
		if(stampertype!=null) {
			if(stampertype!=null&&stampertype.equals("1")) {
				inputSignPage.setStampertype("����");
			}else if(stampertype!=null&&stampertype.equals("2")) {
				inputSignPage.setStampertype("������");
			}else if(stampertype!=null&&stampertype.equals("3")) {
				inputSignPage.setStampertype("��ͬ��");
			}else if(stampertype!=null&&stampertype.equals("4")) {
				inputSignPage.setStampertype("�ܾ�����");
			}else if(stampertype!=null&&stampertype.equals("5")) {
				inputSignPage.setStampertype("������");
			}else if(stampertype!=null&&stampertype.equals("6")) {
				inputSignPage.setStampertype("������");
			}else if(stampertype!=null&&stampertype.equals("7")){
				inputSignPage.setStampertype("����");
			}
		}
		//�޸��ļ�����
		// 1,����;2,������;3,��ͬ��;4,�ܾ�����;5,������;6,������;7,����
		
		String stampertype1 = inputSignPage.getStampertype1();
		if(stampertype1!=null) {
			if(stampertype1!=null&&stampertype1.equals("1")) {
				inputSignPage.setStampertype1("����");
			}else if(stampertype1!=null&&stampertype1.equals("2")) {
				inputSignPage.setStampertype1("������");
			}else if(stampertype1!=null&&stampertype1.equals("3")) {
				inputSignPage.setStampertype1("��ͬ��");
			}else if(stampertype1!=null&&stampertype1.equals("4")) {
				inputSignPage.setStampertype1("�ܾ�����");
			}else if(stampertype1!=null&&stampertype1.equals("5")) {
				inputSignPage.setStampertype1("������");
			}else if(stampertype1!=null&&stampertype1.equals("6")) {
				inputSignPage.setStampertype1("������");
			}else if(stampertype1!=null&&stampertype1.equals("7")){
				inputSignPage.setStampertype1("����");
			}
		}
		//�޸��ļ�����
		// 1,����;2,������;3,��ͬ��;4,�ܾ�����;5,������;6,������;7,����
		
		String stampertype2 = inputSignPage.getStampertype2();
		if(stampertype2!=null) {
			if(stampertype2!=null&&stampertype2.equals("1")) {
				inputSignPage.setStampertype2("����");
			}else if(stampertype2!=null&&stampertype2.equals("2")) {
				inputSignPage.setStampertype2("������");
			}else if(stampertype2!=null&&stampertype2.equals("3")) {
				inputSignPage.setStampertype2("��ͬ��");
			}else if(stampertype2!=null&&stampertype2.equals("4")) {
				inputSignPage.setStampertype2("�ܾ�����");
			}else if(stampertype2!=null&&stampertype2.equals("5")) {
				inputSignPage.setStampertype2("������");
			}else if(stampertype2!=null&&stampertype2.equals("6")) {
				inputSignPage.setStampertype2("������");
			}else if(stampertype2!=null&&stampertype2.equals("7")){
				inputSignPage.setStampertype2("����");
			}
		}
	}
	public void transImage(InformationFpm001 allInformationOffpm001,Map<String, Object> map) {
		//�����pdf�ļ����ļ�������1
		//�����jpg�ļ����ļ�������0
		//ͼƬ1
		if(allInformationOffpm001.getImage()!=null) {
			String fileType1 = SplitStr.getFileType(allInformationOffpm001.getImage());
			System.out.println("ͼƬ1��״̬��"+fileType1);
			if(fileType1.equals("pdf")||fileType1.equals("PDF")) {
				map.put("image1status", "1");
			}else {
				map.put("image1status", "0");
			}
			map.put("image1name", allInformationOffpm001.getImage());
			if(allInformationOffpm001.getImage()!=null) {
				allInformationOffpm001.setImage(UserController.imgbase+allInformationOffpm001.getImage());
			}else {
				allInformationOffpm001.setImage("");	
			}
		}
		//ͼƬ2
		if(allInformationOffpm001.getImageTwo()!=null) {
			String fileType2 = SplitStr.getFileType(allInformationOffpm001.getImageTwo());
			if(fileType2.equals("pdf")||fileType2.equals("PDF")) {
				map.put("image2status", "1");
			}else {
				map.put("image2status", "0");
			}
			map.put("image2name", allInformationOffpm001.getImageTwo());
			if(allInformationOffpm001.getImageTwo()!=null) {
				allInformationOffpm001.setImageTwo(UserController.imgbase+allInformationOffpm001.getImageTwo());
			}else {
				allInformationOffpm001.setImageTwo("");
			}
		}
		//ͼƬ3
		if(allInformationOffpm001.getImageThree()!=null) {
			String fileType3 = SplitStr.getFileType(allInformationOffpm001.getImageThree());
			if(fileType3.equals("pdf")||fileType3.equals("PDF")) {
				map.put("image3status", "1");
			}else {
				map.put("image3status", "0");
			}
			map.put("image3name", allInformationOffpm001.getImageThree());
			if(allInformationOffpm001.getImageThree()!=null) {
				allInformationOffpm001.setImageThree(UserController.imgbase+allInformationOffpm001.getImageThree());
			}else {
				allInformationOffpm001.setImageThree("");
			}	
		}
		//ͼƬ4
		if(allInformationOffpm001.getImageFour()!=null) {
			String fileType4 = SplitStr.getFileType(allInformationOffpm001.getImageFour());
			if(fileType4.equals("pdf")||fileType4.equals("PDF")) {
				map.put("image4status", "1");
			}else {
				map.put("image4status", "0");
			}
			map.put("image4name", allInformationOffpm001.getImageFour());
			if(allInformationOffpm001.getImageFour()!=null) {
				allInformationOffpm001.setImageFour(UserController.imgbase+allInformationOffpm001.getImageFour());
			}else {
				allInformationOffpm001.setImageFour("");
			}
		}
		//ͼƬ5
		if(allInformationOffpm001.getImageFive()!=null) {
			String fileType5 = SplitStr.getFileType(allInformationOffpm001.getImageFive());
			if(fileType5.equals("pdf")||fileType5.equals("PDF")) {
				map.put("image5status", "1");
			}else {
				map.put("image5status", "0");
			}
			map.put("image5name", allInformationOffpm001.getImageFive());
			if(allInformationOffpm001.getImageFive()!=null) {
				allInformationOffpm001.setImageFive(UserController.imgbase+allInformationOffpm001.getImageFive());
			}else {
				allInformationOffpm001.setImageFive("");
			}
		}
	}
	
}
