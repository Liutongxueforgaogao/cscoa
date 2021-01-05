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
 * 暂付款控制器，主页面main.jsp进入后选择暂付款申请单审批就会进入到这个控制器中的内容中
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
	
	//此DAO直接调用取数据，没有经过Service层，从结构上来说不科学，但是很直观
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
	            map.put("username", detailsInformationDao.getName(username));//获取到姓名
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
			//取主表详细信息
			DetailsInformation detailsInformation = detailsPageService.getAllDetailsInformation(checkid);
			map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//获取到申请人姓名
			if(detailsInformation!=null){
				detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
				map.put("details", detailsInformation);
			}
			//取exm00101详细信息
			List<InformationExm00101> allInformationByExm00101 = detailsInformationDao.getAllInformationByExm00101(checkid);
			if(allInformationByExm00101!=null){
				map.put("exm00103", allInformationByExm00101);
			}
			//取exm001的附加信息【20170512添加】
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
	            map.put("username", detailsInformationDao.getName(username));//获取到姓名
	        }
	    }
		map.put("user", username);
		
		//获取审核状态
		String status = tempAuditQueryService.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = tempAuditQueryService.getAllPeopleSuggest(checkid);
		Iterator<UserAndSuggest> it = allPeopleSuggest.iterator();
		while(it.hasNext()){
			System.out.println("........:"+it.next().toString());
		}
		
		map.put("allPeopleSuggest", allPeopleSuggest);
		return "temp_details";
	}
	//插入意见
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
	            map.put("username", detailsInformationDao.getName(username));//获取到姓名
	        }
	     } 
		//取当前时间并转化为String
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		if(replyMessage!=null&&username!=""){
			tempAuditQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);		
		}
		
		
		if(checkid!=""){
			//取主表详细信息
			DetailsInformation detailsInformation = detailsPageService.getAllDetailsInformation(checkid);
			map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//获取到申请人姓名
			if(detailsInformation!=null){
				detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
				map.put("details", detailsInformation);
			}
			//取exm00101详细信息
			List<InformationExm00101> allInformationByExm00101 = detailsInformationDao.getAllInformationByExm00101(checkid);
			if(allInformationByExm00101!=null){
				map.put("exm00101", allInformationByExm00101);
			}
			//取exm001的附加信息【20170512添加】
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
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = tempAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		
		//获取审核状态
		String status = tempAuditQueryService.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		
		return "temp_details";
	}
	/**
	 * 打回按钮点击后来这个控制器
	 * @return
	 */
	@RequestMapping("/temp_disagree")
	public String disagree(Map<String, Object> map,HttpServletRequest request){
		System.out.println("进入打回控制器");
		String username="";
		String replyMessage = request.getParameter("replyMessage");
		String replyMessage1 = request.getParameter("replyMessage");
		String checkid = request.getParameter("checkid");
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	        	username = cookie.getValue();
	        	map.put("user", username);
	        	map.put("username", detailsInformationDao.getName(username));//获取到姓名
	        }
	    } 
		//打回后，需要加上打回信息
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(已打回):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(已打回)";
		}	
		//获取审核状态
		String status = tempAuditQueryService.getStatus(username, checkid);
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		String date = df.format(new Date());
		if(username!=null&&!username.equals("")&&status==null){
			
			tempAuditQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);
			tempAuditQueryService.disagree(username, checkid);
			//status="0";
		}
		//查询详细信息
		if(checkid!=""){
			//取主表详细信息
			DetailsInformation detailsInformation = detailsPageService.getAllDetailsInformation(checkid);
			map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//获取到申请人姓名
			if(detailsInformation!=null){
				detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
				map.put("details", detailsInformation);
			}
			//取exm00101详细信息
			List<InformationExm00101> allInformationByExm00101 = detailsInformationDao.getAllInformationByExm00101(checkid);
			if(allInformationByExm00101!=null){
				map.put("exm00101", allInformationByExm00101);
			}
			//取exm001的附加信息【20170512添加】
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
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = tempAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		//获取审核状态
		status = tempAuditQueryService.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		
		return "temp_details";
	}
	
	/**
	 * 同意按钮点击后来这个控制器
	 * @return
	 */
	@RequestMapping("/temp_agree")
	public String agree(Map<String, Object> map,HttpServletRequest request){
		System.out.println("进入同意控制器");
		String username="";
		String replyMessage = request.getParameter("replyMessage");
		String replyMessage1 = request.getParameter("replyMessage");
		String checkid = request.getParameter("checkid");
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
			if(cookie.getName().equals("userLevel")){
				username = cookie.getValue();
				map.put("user", username);
				map.put("username", detailsInformationDao.getName(username));//获取到姓名
			}
		} 

		//同意后，需要加上打回信息
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(已同意):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(已同意)";
		}
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		String date = df.format(new Date());
		//获取审核状态
		String status = tempAuditQueryService.getStatus(username, checkid);
		
		if(username!=null&&status==null){
			try {
				tempAuditQueryService.insertSuggest(checkid,username, replyMessage,replyMessage1);	
				tempAuditQueryService.agree(username, checkid);
			} catch (Exception e) {
				System.out.println("同意失败");
				map.put("error", "同意失败");
				e.printStackTrace();
			}
			//status="1";
		}
		//查询详细信息
		if(checkid!=""){
			//取主表详细信息
			DetailsInformation detailsInformation = detailsPageService.getAllDetailsInformation(checkid);
			map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//获取到申请人姓名
			if(detailsInformation!=null){
				detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
				map.put("details", detailsInformation);
			}
			//取exm00101详细信息
			List<InformationExm00101> allInformationByExm00101 = detailsInformationDao.getAllInformationByExm00101(checkid);
			if(allInformationByExm00101!=null){
				map.put("exm00101", allInformationByExm00101);
			}
			//取exm001的附加信息【20170512添加】
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
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = tempAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		//获取审核状态
		status = tempAuditQueryService.getStatus(username, checkid);
		
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		return "temp_details";
	}
	
	@RequestMapping("/temp_searchto")
	public String search(HttpServletResponse response){
		return "temp_search";
	}
	
	@RequestMapping("/temp_searchByCondition")
	public String searchByCondition (Map<String, Object> map,HttpServletRequest request){
		
		String pddh = null;//审核单号
		String pdrqstart = null;//审核日期开始
		String pdrqend = null;//审核日期开始
		String pdzt = null;//审核状态
		String username = "";//用户名
		try {
			String  stuname = request.getParameter("condition");
			System.out.println(stuname);
			String str = new String(stuname.getBytes("ISO-8859-1"),"utf-8");
			JSONObject jb = new JSONObject();
			pddh = (String) jb.fromObject(str).get("pddh");
			pdrqstart = (String) jb.fromObject(str).get("pdrqstart");
			pdrqend = (String) jb.fromObject(str).get("pdrqend");
			pdrqstart=pdrqstart+" 00:00:00";//处理审核日期开始
			pdrqend=pdrqend+" 23:59:59";//处理审核日期结束
//			pdzt = (String) jb.fromObject(str).get("pdkwv");
			pdzt = (String) jb.fromObject(stuname).get("pdkwv");
		/*	if(pdzt==null){//不知道为什么，数据库查不出来
				pdzt="未通过";
			}*/
			pdzt=pdzt+"";
			Cookie[] userCookie = request.getCookies();
			for(Cookie cookie : userCookie){
			    if(cookie.getName().equals("userLevel")){
			        username = cookie.getValue();
			        map.put("user", username);
			        map.put("username", detailsInformationDao.getName(username));//获取到姓名
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
			System.out.println("暂付款："+auditPage.getName());//标记输出
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
