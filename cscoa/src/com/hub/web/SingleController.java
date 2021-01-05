package com.hub.web;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.hub.entity.AuditPage;
import com.hub.entity.DetailsInformation;
import com.hub.entity.InformationExm00101;
import com.hub.entity.InfromationOfExm001Addition;
import com.hub.entity.UserAndSuggest;
import com.hub.service.impl.AuditLevelQueryServiceImpl;
import com.hub.service.impl.AuditPageServiceImpl;
import com.hub.service.impl.DetailsPageServiceImpl;
import com.hub.service.impl.UserServiceImpl;
import com.hub.tools.ChangeTime;
import com.hub.tools.GetEncoding;

import net.sf.json.JSONObject;
@Controller
public class SingleController {
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
	
	//此DAO直接调用取数据，没有经过Service层，从结构上来说不科学，但是很直观
	@Autowired(required = false) 
	private DetailsInformationDao detailsInformationDao;
	
	@RequestMapping("/detailsCol")
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
		ArrayList<UserAndSuggest> allPeopleSuggest = auditLevelQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		
		//获取审核状态
		String username ="";
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	            username = cookie.getValue();
	            map.put("user", username);
	            map.put("username", detailsInformationDao.getName(username));//获取到姓名
	        }
	    }
		String status = auditLevelQueryService.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		return "details";
	}
	
	
	
	@RequestMapping("/searchto")
	public String search(HttpServletResponse response){
/*		try {
			response.sendRedirect("search.jsp");//重定向会导致传递的参数丢失
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return "search";
	}
	
	@RequestMapping("/searchByCondition")
	public String searchByCondition (Map<String, Object> map,HttpServletRequest request){
		String pddh = null;//审核单号
		String pdrqstart = null;//审核日期开始
		String pdrqend = null;//审核日期开始
		String pdzt = null;//审核状态
		String username = "";//用户名
		try {
			String  stuname = request.getParameter("condition");
			System.out.println("所要查询的状态："+stuname);
			String str = new String(stuname.getBytes("ISO-8859-1"),"utf-8");

			System.out.println("转换后的状态："+str);
			JSONObject jb = new JSONObject();
			pddh = (String) jb.fromObject(str).get("pddh").toString();
			pdrqstart = (String) jb.fromObject(str).get("pdrqstart").toString();
			pdrqend = (String) jb.fromObject(str).get("pdrqend").toString();
			
			pdrqstart=pdrqstart+" 00:00:00";//处理审核日期开始
			pdrqend=pdrqend+" 23:59:59";//处理审核日期结束
//			pdzt = (String) jb.fromObject(str).get("pdkwv");
			pdzt = (String) jb.fromObject(str).get("pdkwv");//服务器上需要改为此条语句
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("状态："+pdzt);

		List<AuditPage> allAudit=auditPageService.getAuditBycondition(username, pddh, pdrqstart,pdrqend, pdzt);
		int size = allAudit.size();
		map.put("size",size);
//		System.out.println(allAudit.size());
//		Iterator<AuditPage> it = allAudit.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next().toString());
//		}
		Iterator<AuditPage> it = allAudit.iterator();
		while(it.hasNext()){
			AuditPage auditPage = it.next();
			System.out.println("AAA:"+auditPage.getTotalamount());
			String datestr = auditPage.getDate();
			if(datestr!=null) {
				String[] datasplit = datestr.split(" ");
				auditPage.setDate(datasplit[0]);
			}
		}
		map.put("audits", allAudit);
		
		return "search";
	}
	
	
	/**
	 * 由详情显示界面，点击回复，输入回复意见并点击确定后，
	 * 来到这个控制器，成功后转发到detailsCol控制器
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertSuggest")
	public String insertSuggest(Map<String, Object> map,HttpServletRequest request){
		String username="";
		String replyMessage = request.getParameter("replyMessage");
		String replyMessage1 = request.getParameter("replyMessage");
		String checkid = request.getParameter("checkid");
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	            username = cookie.getValue();
	            map.put("user",username);
	            map.put("username", detailsInformationDao.getName(username));//获取到姓名
	        }
	     } 
		if(replyMessage!=null&&username!=""){
			auditLevelQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);			
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
		ArrayList<UserAndSuggest> allPeopleSuggest = auditLevelQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		
		//获取审核状态
		String status = auditLevelQueryService.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		
		return "details";
	}
	/**
	 * 打回按钮点击后来这个控制器
	 * @return
	 */
	@RequestMapping("/disagree")
	public String disagree(Map<String, Object> map,HttpServletRequest request){
		//System.out.println("进入打回控制器");
		String username="";
		String replyMessage = request.getParameter("replyMessage");
		String replyMessage1 = request.getParameter("replyMessage");

		String checkid = request.getParameter("checkid");
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	        	username = cookie.getValue();
	        	map.put("user",username);
	        	map.put("username", detailsInformationDao.getName(username));//获取到姓名
	        }
	    } 
		//获取审核状态
		String status = auditLevelQueryService.getStatus(username, checkid);
		
		//打回后，需要加上打回信息
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(已打回):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(已打回)";
		}
		
		if(username!=null&&!username.equals("")&&status==null){
			try {
				auditLevelQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);
				auditLevelQueryService.disagree(username, checkid);
			} catch (Exception e) {
				map.put("error", "打回失败");
			}	
			//status="0";
		}
		//查询详细信息
		if(checkid!=""){
			//取主表详细信息
			DetailsInformation detailsInformation = detailsPageService.getAllDetailsInformation(checkid);
			map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//获取到申请人姓名
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
		ArrayList<UserAndSuggest> allPeopleSuggest = auditLevelQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		//获取审核状态
		status = auditLevelQueryService.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		
		return "details";
	}
	
	/**
	 * 同意按钮点击后来这个控制器
	 * @return
	 */
	@RequestMapping("/agree")
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
				map.put("user",username);
				map.put("username", detailsInformationDao.getName(username));//获取到姓名
			}
		} 

		//同意后，需要加上打回信息
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(已同意):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(已同意)";
		}
				
		//获取审核状态
		String status = auditLevelQueryService.getStatus(username, checkid);
		//System.out.println("状态1："+status);
		//同意后，需要显示同意【20170517】同意成功才可以插入"已同意"建议
		
		if(username!=null&&status==null){
			try {
				auditLevelQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);	
				auditLevelQueryService.agree(username, checkid);
			} catch (Exception e) {
				map.put("error", "同意失败");
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
		ArrayList<UserAndSuggest> allPeopleSuggest = auditLevelQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		
		//获取审核状态
		status = auditLevelQueryService.getStatus(username, checkid);
		System.out.println("状态2："+status);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核	
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		return "details";
	}
}



