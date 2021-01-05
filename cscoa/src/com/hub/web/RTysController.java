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
import com.hub.entity.Rtm001;
import com.hub.entity.Rtm00101;
import com.hub.entity.UserAndSuggest;
import com.hub.service.impl.AuditLevelQueryServiceImpl;
import com.hub.service.impl.AuditPageServiceImpl;
import com.hub.service.impl.DetailsPageServiceImpl;
import com.hub.service.impl.RTysAuditPageServiceImpl;
import com.hub.service.impl.RTysAuditQueryServiceImpl;
import com.hub.service.impl.UserServiceImpl;
import com.hub.tools.ChangeTime;
import com.hub.tools.GetEncoding;

import net.sf.json.JSONObject;
@Controller
public class RTysController {
	@Autowired(required = false) 
	@Qualifier("UserServiceImpl")
	private UserServiceImpl userService;
	
	@Autowired(required = false) 
	@Qualifier("RTysAuditPageServiceImpl")
	private RTysAuditPageServiceImpl rTysAuditPageServiceImpl;
	
	@Autowired(required = false) 
	@Qualifier("DetailsPageServiceImpl")
	private DetailsPageServiceImpl detailsPageService;
	
	@Autowired(required = false)
	@Qualifier("RTysAuditQueryServiceImpl")
	private RTysAuditQueryServiceImpl rTysAuditQueryServiceImpl;
	
	//此DAO直接调用取数据，没有经过Service层，从结构上来说不科学，但是很直观
	@Autowired(required = false) 
	private DetailsInformationDao detailsInformationDao;
	@RequestMapping("/RTysAudit")
	private String auditPage(Map<String, Object> map,HttpServletRequest request){
		String username="";
		try {
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
			List<AuditPage> allAudit = rTysAuditPageServiceImpl.getAllAudit(username, pagenum);
			map.put("audits", allAudit);
			map.put("pagenum", pagestr);
			//计算未审核的个数
			int auditSize = rTysAuditPageServiceImpl.getAuditSize(username);
			int maxpage = auditSize/10;
			String maxpagestr = maxpage+"";
			map.put("maxpage", maxpage);
		} catch (Exception e) {
			map.put("error", "列表数据获取异常");
			return "rtys_audit";
		}
		return "rtys_audit";
	}
	
	
	@RequestMapping("/detailsRTYS")
	private String detailsPage(HttpServletRequest request,Map<String, Object> map){
		String	checkid = request.getParameter("checkid");	
		if(checkid!=""){
			//取主表详细信息
			Rtm001 detailsInformation = detailsPageService.getRtm001DetailsInformation(checkid);
			map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//获取到申请人姓名
			
			if(detailsInformation!=null){
				detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
				detailsInformation.setImage(UserController.imgbase+detailsInformation.getImage());
				detailsInformation.setImagetwo(UserController.imgbase+detailsInformation.getImagetwo());
				detailsInformation.setImagethree(UserController.imgbase+detailsInformation.getImagethree());
				detailsInformation.setImagefour(UserController.imgbase+detailsInformation.getImagefour());
				detailsInformation.setImagefive(UserController.imgbase+detailsInformation.getImagefive());
				//根据公司代码revcompany查找公司名称
				String revcompanyname = detailsInformationDao.getRevcompanyname(detailsInformation.getRevcompany(),detailsInformation.getFeecompany());
				detailsInformation.setRevcompanyname(revcompanyname);
				//根据代码查支付方式
				if(detailsInformation.getPaytype()!=null) {
					String paytype = detailsInformationDao.getPaytype(detailsInformation.getPaytype());
					detailsInformation.setPaytype(paytype);
				}
				map.put("details", detailsInformation);
			}
			//取exm00101详细信息
			List<Rtm00101> allInformationByRtm00101 = detailsInformationDao.getAllInformationByRtm00101(checkid);
			for (Rtm00101 rtm00101item : allInformationByRtm00101) {
				String expensename = detailsInformationDao.getExpensename(rtm00101item.getDeptno());
				rtm00101item.setDeptname(expensename);
				//新增银行和卡号
				String bankAndBankno = detailsInformationDao.getBankAndBankno(detailsInformation.getFeecompany(),rtm00101item.getFeecompany());
				if(bankAndBankno!=null&&!bankAndBankno.equals("")) {
					String[] split = bankAndBankno.split(";");
					if(split.length>1) {
						//invoiceno
						rtm00101item.setBank(split[0]);
						//借用remark字段作为银行
						//rtm00101item.setRemark(split[0]);
						//借用Invoiceno字段作为银行账号
						rtm00101item.setInvoiceno((split[1]));
					}
				}
			}
			//
			if(detailsInformation.getFeekink()!=null) {
				String expensename = detailsInformationDao.getExpensename(detailsInformation.getFeekink());
				detailsInformation.setFeekink(expensename);
			}
			if(allInformationByRtm00101!=null){
				map.put("rtm00101", allInformationByRtm00101);
			}
			
		}
		
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = rTysAuditQueryServiceImpl.getAllPeopleSuggest(checkid);
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
		String status = rTysAuditQueryServiceImpl.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		return "rtys_details";
	}
	
	
	
	@RequestMapping("/searchtoRTYS")
	public String search(HttpServletResponse response){
/*		try {
			response.sendRedirect("search.jsp");//重定向会导致传递的参数丢失
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return "rtys_search";
	}
	
	@RequestMapping("/searchByConditionRTYS")
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
			JSONObject jb = new JSONObject();
			pddh = (String) jb.fromObject(str).get("pddh").toString();
			pdrqstart = (String) jb.fromObject(str).get("pdrqstart").toString();
			pdrqend = (String) jb.fromObject(str).get("pdrqend").toString();
			
			pdrqstart=pdrqstart+" 00:00:00";//处理审核日期开始
			pdrqend=pdrqend+" 23:59:59";//处理审核日期结束
//			pdzt = (String) jb.fromObject(str).get("pdkwv");
			pdzt = (String) jb.fromObject(stuname).get("pdkwv");//服务器上需要改为此条语句
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

		List<AuditPage> allAudit=rTysAuditPageServiceImpl.getAuditBycondition(username, pddh, pdrqstart,pdrqend, pdzt);
		int size = allAudit.size();
		map.put("rtys_size",size);
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
		
		return "rtys_search";
	}
	
	
	
	/**
	 * 打回按钮点击后来这个控制器
	 * @return
	 */
	@RequestMapping("/disagreeRTYS")
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
		String status = rTysAuditQueryServiceImpl.getStatus(username, checkid);
		
		//打回后，需要加上打回信息
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(已打回):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(已打回)";
		}
		
		if(username!=null&&!username.equals("")&&status==null){
			try {
				rTysAuditQueryServiceImpl.insertSuggest(checkid, username, replyMessage,replyMessage1);
				rTysAuditQueryServiceImpl.disagree(username, checkid);
			} catch (Exception e) {
				map.put("error", "打回失败");
			}	
			//status="0";
		}
		//查询详细信息
		if(checkid!=""){
			//取主表详细信息
			Rtm001 detailsInformation = detailsPageService.getRtm001DetailsInformation(checkid);
			map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//获取到申请人姓名
			if(detailsInformation!=null){
				detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
				detailsInformation.setImage(UserController.imgbase+detailsInformation.getImage());
				detailsInformation.setImagetwo(UserController.imgbase+detailsInformation.getImagetwo());
				detailsInformation.setImagethree(UserController.imgbase+detailsInformation.getImagethree());
				detailsInformation.setImagefour(UserController.imgbase+detailsInformation.getImagefour());
				detailsInformation.setImagefive(UserController.imgbase+detailsInformation.getImagefive());
				//根据公司代码revcompany查找公司名称
				String revcompanyname = detailsInformationDao.getRevcompanyname(detailsInformation.getRevcompany(),detailsInformation.getFeecompany());
				detailsInformation.setRevcompanyname(revcompanyname);
				//根据代码查支付方式
				if(detailsInformation.getPaytype()!=null) {
					String paytype = detailsInformationDao.getPaytype(detailsInformation.getPaytype());
					detailsInformation.setPaytype(paytype);
				}
				//
				if(detailsInformation.getFeekink()!=null) {
					String expensename = detailsInformationDao.getExpensename(detailsInformation.getFeekink());
					detailsInformation.setFeekink(expensename);
				}
				map.put("details", detailsInformation);
			}
			//取exm00101详细信息
			List<Rtm00101> allInformationByRtm00101 = detailsInformationDao.getAllInformationByRtm00101(checkid);
			for (Rtm00101 rtm00101item : allInformationByRtm00101) {
				String expensename = detailsInformationDao.getExpensename(rtm00101item.getDeptno());
				rtm00101item.setDeptname(expensename);
				//新增银行和卡号
				String bankAndBankno = detailsInformationDao.getBankAndBankno(detailsInformation.getFeecompany(),rtm00101item.getFeecompany());
				if(bankAndBankno!=null&&!bankAndBankno.equals("")) {
					String[] split = bankAndBankno.split(";");
					if(split.length>1) {
						//借用remark字段作为银行
						rtm00101item.setBank(split[0]);
						//借用Invoiceno字段作为银行账号
						rtm00101item.setBankcode((split[1]));
					}
				}
			}
			if(allInformationByRtm00101!=null){
				map.put("rtm00101", allInformationByRtm00101);
			}
		}
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = rTysAuditQueryServiceImpl.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		//获取审核状态
		status = rTysAuditQueryServiceImpl.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		
		return "rtys_details";
	}
	
	/**
	 * 同意按钮点击后来这个控制器
	 * @return
	 */
	@RequestMapping("/agreeRTYS")
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
		String status = rTysAuditQueryServiceImpl.getStatus(username, checkid);
		//System.out.println("状态1："+status);
		//同意后，需要显示同意【20170517】同意成功才可以插入"已同意"建议
		
		if(username!=null&&status==null){
			try {
				rTysAuditQueryServiceImpl.insertSuggest(checkid, username, replyMessage,replyMessage1);	
				rTysAuditQueryServiceImpl.agree(username, checkid);
			} catch (Exception e) {
				map.put("error", "同意失败");
			}
			//status="1";
		}
		//查询详细信息
		if(checkid!=""){
			//取主表详细信息
			Rtm001 detailsInformation = detailsPageService.getRtm001DetailsInformation(checkid);
			map.put("sqrname", detailsInformationDao.getName(detailsInformation.getStaff()));//获取到申请人姓名
			if(detailsInformation!=null){
				detailsInformation.setInputdate(ChangeTime.formatTime(detailsInformation.getInputdate()));
				detailsInformation.setImage(UserController.imgbase+detailsInformation.getImage());
				detailsInformation.setImagetwo(UserController.imgbase+detailsInformation.getImagetwo());
				detailsInformation.setImagethree(UserController.imgbase+detailsInformation.getImagethree());
				detailsInformation.setImagefour(UserController.imgbase+detailsInformation.getImagefour());
				detailsInformation.setImagefive(UserController.imgbase+detailsInformation.getImagefive());
				//根据公司代码revcompany查找公司名称
				String revcompanyname = detailsInformationDao.getRevcompanyname(detailsInformation.getRevcompany(),detailsInformation.getFeecompany());
				detailsInformation.setRevcompanyname(revcompanyname);
				//根据代码查支付方式
				if(detailsInformation.getPaytype()!=null) {
					String paytype = detailsInformationDao.getPaytype(detailsInformation.getPaytype());
					detailsInformation.setPaytype(paytype);
				}
				//
				if(detailsInformation.getFeekink()!=null) {
					String expensename = detailsInformationDao.getExpensename(detailsInformation.getFeekink());
					detailsInformation.setFeekink(expensename);
				}
				map.put("details", detailsInformation);
			}
			//取exm00101详细信息
			List<Rtm00101> allInformationByRtm00101 = detailsInformationDao.getAllInformationByRtm00101(checkid);
			for (Rtm00101 rtm00101item : allInformationByRtm00101) {
				String expensename = detailsInformationDao.getExpensename(rtm00101item.getDeptno());
				rtm00101item.setDeptname(expensename);
				//新增银行和卡号
				String bankAndBankno = detailsInformationDao.getBankAndBankno(detailsInformation.getFeecompany(),rtm00101item.getFeecompany());
				if(bankAndBankno!=null&&!bankAndBankno.equals("")) {
					String[] split = bankAndBankno.split(";");
					if(split.length>1) {
						//借用remark字段作为银行
						rtm00101item.setBank(split[0]);
						//借用Invoiceno字段作为银行账号
						rtm00101item.setBankcode((split[1]));
					}
				}
			}
			if(allInformationByRtm00101!=null){
				map.put("rtm00101", allInformationByRtm00101);
			}
			//取exm001的附加信息【20170512添加】
			
		}
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = rTysAuditQueryServiceImpl.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		
		//获取审核状态
		status = rTysAuditQueryServiceImpl.getStatus(username, checkid);
		System.out.println("状态2："+status);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核	
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		return "rtys_details";
	}
}



