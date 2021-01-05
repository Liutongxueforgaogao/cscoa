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
 * 用章控制器，主页面main.jsp进入后选择暂付款申请单审批就会进入到这个控制器中的内容中
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
	
	//此DAO直接调用取数据，没有经过Service层，从结构上来说不科学，但是很直观
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
	            map.put("username", detailsInformationDao.getName(username));//获取到姓名
	        }
	    }
		}else {
			return "login";
		}
		List<SignPage> allAudit = signAuditPageService.getAllAudit(username, pagenum);
		//批量转换申请人的名字
		Iterator<SignPage> it = allAudit.iterator();
		while(it.hasNext()) {
			SignPage next = it.next();
			next.setStaff(detailsInformationDao.getName(next.getStaff()));
			String filetype = next.getFiletype();
			if(filetype!=null&&filetype.equals("1")) {
				next.setFiletype("合同");
			}else if(filetype!=null&&filetype.equals("2")) {
				next.setFiletype("申请书");
			}else if(filetype!=null&&filetype.equals("3")) {
				next.setFiletype("证明书");
			}else if(filetype!=null&&filetype.equals("4")) {
				next.setFiletype("公函");
			}else if(filetype!=null&&filetype.equals("5")) {
				next.setFiletype("公告事项");
			}else if(filetype!=null&&filetype.equals("6")) {
				next.setFiletype("其他");
			}else{
				next.setFiletype("错误");
			}
	
			//批量转换编号
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
			//取fpm001的信息
			InformationFpm001 allInformationOffpm001 = detailsInformationDao.getAllInformationFpm001(checkid);
			//转换图片信息
			transImage(allInformationOffpm001,map);
			//转换申请人的名字
			allInformationOffpm001.setStaff(detailsInformationDao.getName(allInformationOffpm001.getStaff()));
			//转换公司名字
			allInformationOffpm001.setFeecompany(detailsInformationDao.getCompany(allInformationOffpm001.getFeecompany()));
			
			//获取文件类型，转换成文字，字段filetype
			//1,合同;2,申请书;3,证明书;4,公函;5,公告事项;6,其他  
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
		            map.put("username", detailsInformationDao.getName(username));//获取到姓名
		        }
		    }
		}else {
			return "login";
		}
		
		map.put("user", username);
		
		//获取审核状态
		String status = signAuditQueryService.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = signAuditQueryService.getAllPeopleSuggest(checkid);
		Iterator<UserAndSuggest> it = allPeopleSuggest.iterator();
		while(it.hasNext()){
			System.out.println("........:"+it.next().toString());
		}
		
		map.put("allPeopleSuggest", allPeopleSuggest);
		return "sign_details";
	}
	//插入意见
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
	            map.put("username", detailsInformationDao.getName(username));//获取到姓名
	        }
	     } 
		}else {
			return "login";
		}
		//取当前时间并转化为String
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		if(replyMessage!=null&&username!=""){
			signAuditQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);		
		}
		
		
		if(checkid!=""){
			//取fpm001的信息
			InformationFpm001 allInformationOffpm001 = detailsInformationDao.getAllInformationFpm001(checkid);
			//转换图片地址，具体到ip地址
			//转换图片信息
			transImage(allInformationOffpm001,map);
			//转换申请人的名字
			allInformationOffpm001.setStaff(detailsInformationDao.getName(allInformationOffpm001.getStaff()));
			//转换公司名字
			allInformationOffpm001.setFeecompany(detailsInformationDao.getCompany(allInformationOffpm001.getFeecompany()));
			
			//获取文件类型，转换成文字，字段filetype
			//1,合同;2,申请书;3,证明书;4,公函;5,公告事项;6,其他  
			updatefile(allInformationOffpm001);
			//1,公章;2,法人章;3,合同章;4,总经理章;5,财务章;6,人事章;7,其他 这是所属印章下拉框的内容

			
			if(allInformationOffpm001!=null){
				map.put("details", allInformationOffpm001);
			}
		}
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = signAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		
		//获取审核状态
		String status = signAuditQueryService.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		
		return "sign_details";
	}
	/**
	 * 打回按钮点击后来这个控制器
	 * @return
	 */
	@RequestMapping("/sign_disagree")
	public String disagree(Map<String, Object> map,HttpServletRequest request){
		System.out.println("进入打回控制器");
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
	        	map.put("username", detailsInformationDao.getName(username));//获取到姓名
	        }
	    } 
		}else {
			return "login";
			
		}
		//打回后，需要加上打回信息
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(已打回):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(已打回)";
		}	
		//获取审核状态
		String status = signAuditQueryService.getStatus(username, checkid);
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		String date = df.format(new Date());
		if(username!=null&&!username.equals("")&&status==null){
			
			signAuditQueryService.insertSuggest(checkid, username, replyMessage,replyMessage1);
			signAuditQueryService.disagree(username, checkid);
			//status="0";
		}
		//查询详细信息
		if(checkid!=""){
			//取fpm001的信息
			InformationFpm001 allInformationOffpm001 = detailsInformationDao.getAllInformationFpm001(checkid);
			//转换图片信息
			transImage(allInformationOffpm001,map);
			//转换申请人的名字
			allInformationOffpm001.setStaff(detailsInformationDao.getName(allInformationOffpm001.getStaff()));
			//转换公司名字
			allInformationOffpm001.setFeecompany(detailsInformationDao.getCompany(allInformationOffpm001.getFeecompany()));
			
			//获取文件类型，转换成文字，字段filetype
			//1,合同;2,申请书;3,证明书;4,公函;5,公告事项;6,其他  
			updatefile(allInformationOffpm001);
			
			if(allInformationOffpm001!=null){
				map.put("details", allInformationOffpm001);
			}
		}
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = signAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		//获取审核状态
		status = signAuditQueryService.getStatus(username, checkid);
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		
		return "sign_details";
	}
	
	/**
	 * 同意按钮点击后来这个控制器
	 * @return
	 */
	@RequestMapping("/sign_agree")
	public String agree(Map<String, Object> map,HttpServletRequest request){
		System.out.println("进入同意控制器");
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
				map.put("username", detailsInformationDao.getName(username));//获取到姓名
			}
		} 
		}else {
			return "login";
		}
		//调试输出获取到的值
		System.out.println("replyMessage:"+replyMessage);
		System.out.println("checkid:"+checkid);
		
		
		//同意后，需要加上打回信息
		if(replyMessage!=null||!replyMessage.equals("")){
			replyMessage = detailsInformationDao.getName(username)+"(已同意):"+replyMessage;
		}else{
			replyMessage = detailsInformationDao.getName(username)+"(已同意)";
		}
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		String date = df.format(new Date());
		//获取审核状态
		String status = signAuditQueryService.getStatus(username, checkid);
		System.out.println("获取状态通过测试："+status);
		if(username!=null&&status==null){
			try {
				signAuditQueryService.insertSuggest(checkid,username, replyMessage,replyMessage1);	
				signAuditQueryService.agree(username, checkid);
			} catch (Exception e) {
				System.out.println("同意失败");
				map.put("error", "同意失败");
				e.printStackTrace();
			}
			//status="1";
		}
		System.out.println("插入信息通过测试");
		//查询详细信息
		if(checkid!=""){
			//取fpm001的信息
			InformationFpm001 allInformationOffpm001 = detailsInformationDao.getAllInformationFpm001(checkid);
			System.out.println("输出详细信息："+allInformationOffpm001.toString());
			transImage(allInformationOffpm001,map);
			System.out.println("转换信息成功");
			
			//转换申请人的名字
			allInformationOffpm001.setStaff(detailsInformationDao.getName(allInformationOffpm001.getStaff()));
			System.out.println("转换申请人成功");
			//转换公司名字
			allInformationOffpm001.setFeecompany(detailsInformationDao.getCompany(allInformationOffpm001.getFeecompany()));
			System.out.println("转换公司成功");
			//获取文件类型，转换成文字，字段filetype
			//1,合同;2,申请书;3,证明书;4,公函;5,公告事项;6,其他  
			String filetype = allInformationOffpm001.getFiletype();
			System.out.println("查询文件类型："+filetype);
			//hub
			updatefile(allInformationOffpm001);
			if(allInformationOffpm001!=null){
				map.put("details", allInformationOffpm001);
			}
		}
		//取所有流程中人的建议
		ArrayList<UserAndSuggest> allPeopleSuggest = signAuditQueryService.getAllPeopleSuggest(checkid);
		map.put("allPeopleSuggest", allPeopleSuggest);
		//获取审核状态
		status = signAuditQueryService.getStatus(username, checkid);
		
		//将审核状态传给前端判断
		if(status==null){
			map.put("atag", "0");//表示未审核			
		}else if(status.equals("1")){
			map.put("atag", "1");//表示已通过						
		}else if(status.equals("0")){
			map.put("atag", "-1");//表示未通过
		}
		return "sign_details";
	}
	
	@RequestMapping("/sign_searchto")
	public String search(HttpServletResponse response){
		return "sign_search";
	}
	
	@RequestMapping("/sign_searchByCondition")
	public String searchByCondition (Map<String, Object> map,HttpServletRequest request){
		System.out.println("########进入");
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
			if(userCookie.length!=0) {
			for(Cookie cookie : userCookie){
			    if(cookie.getName().equals("userLevel")){
			        username = cookie.getValue();
			        map.put("user", username);
			        map.put("username", detailsInformationDao.getName(username));//获取到姓名
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

		//批量转换申请人的名字，和文件类型
		Iterator<SignPage> it = allAudit.iterator();
		while(it.hasNext()) {
			SignPage next = it.next();
			next.setStaff(detailsInformationDao.getName(next.getStaff()));
			String filetype = next.getFiletype();
			if(filetype!=null&&filetype.equals("1")) {
				next.setFiletype("合同");
			}else if(filetype!=null&&filetype.equals("2")) {
				next.setFiletype("申请书");
			}else if(filetype!=null&&filetype.equals("3")) {
				next.setFiletype("证明书");
			}else if(filetype!=null&&filetype.equals("4")) {
				next.setFiletype("公函");
			}else if(filetype!=null&&filetype.equals("5")) {
				next.setFiletype("公告事项");
			}else if(filetype!=null&&filetype.equals("6")) {
				next.setFiletype("其他");
			}else{
				next.setFiletype("错误");
			}
			//批量转换编号
			next.setOnlyshowcheckid(SplitStr.getTenStr(next.getCheckid()));
		}
		map.put("audits", allAudit);
		return "sign_search";
	}
	//修改文件类型和公章
	public void updatefile(InformationFpm001 inputSignPage) {
		//修改文件类型
		String filetype = inputSignPage.getFiletype();
		if(filetype!=null) {
			if(filetype!=null&&filetype.equals("1")) {
				inputSignPage.setFiletype("合同");
			}else if(filetype!=null&&filetype.equals("2")) {
				inputSignPage.setFiletype("申请书");
			}else if(filetype!=null&&filetype.equals("3")) {
				inputSignPage.setFiletype("证明书");
			}else if(filetype!=null&&filetype.equals("4")) {
				inputSignPage.setFiletype("公函");
			}else if(filetype!=null&&filetype.equals("5")) {
				inputSignPage.setFiletype("公告事项");
			}else if(filetype!=null&&filetype.equals("6")) {
				inputSignPage.setFiletype("其他");
			}else{
				inputSignPage.setFiletype("错误");
			}
		}
		//修改文件类型
		// 1,公章;2,法人章;3,合同章;4,总经理章;5,财务章;6,人事章;7,其他
		
		String stampertype = inputSignPage.getStampertype();
		if(stampertype!=null) {
			if(stampertype!=null&&stampertype.equals("1")) {
				inputSignPage.setStampertype("公章");
			}else if(stampertype!=null&&stampertype.equals("2")) {
				inputSignPage.setStampertype("法人章");
			}else if(stampertype!=null&&stampertype.equals("3")) {
				inputSignPage.setStampertype("合同章");
			}else if(stampertype!=null&&stampertype.equals("4")) {
				inputSignPage.setStampertype("总经理章");
			}else if(stampertype!=null&&stampertype.equals("5")) {
				inputSignPage.setStampertype("财务章");
			}else if(stampertype!=null&&stampertype.equals("6")) {
				inputSignPage.setStampertype("人事章");
			}else if(stampertype!=null&&stampertype.equals("7")){
				inputSignPage.setStampertype("其他");
			}
		}
		//修改文件类型
		// 1,公章;2,法人章;3,合同章;4,总经理章;5,财务章;6,人事章;7,其他
		
		String stampertype1 = inputSignPage.getStampertype1();
		if(stampertype1!=null) {
			if(stampertype1!=null&&stampertype1.equals("1")) {
				inputSignPage.setStampertype1("公章");
			}else if(stampertype1!=null&&stampertype1.equals("2")) {
				inputSignPage.setStampertype1("法人章");
			}else if(stampertype1!=null&&stampertype1.equals("3")) {
				inputSignPage.setStampertype1("合同章");
			}else if(stampertype1!=null&&stampertype1.equals("4")) {
				inputSignPage.setStampertype1("总经理章");
			}else if(stampertype1!=null&&stampertype1.equals("5")) {
				inputSignPage.setStampertype1("财务章");
			}else if(stampertype1!=null&&stampertype1.equals("6")) {
				inputSignPage.setStampertype1("人事章");
			}else if(stampertype1!=null&&stampertype1.equals("7")){
				inputSignPage.setStampertype1("其他");
			}
		}
		//修改文件类型
		// 1,公章;2,法人章;3,合同章;4,总经理章;5,财务章;6,人事章;7,其他
		
		String stampertype2 = inputSignPage.getStampertype2();
		if(stampertype2!=null) {
			if(stampertype2!=null&&stampertype2.equals("1")) {
				inputSignPage.setStampertype2("公章");
			}else if(stampertype2!=null&&stampertype2.equals("2")) {
				inputSignPage.setStampertype2("法人章");
			}else if(stampertype2!=null&&stampertype2.equals("3")) {
				inputSignPage.setStampertype2("合同章");
			}else if(stampertype2!=null&&stampertype2.equals("4")) {
				inputSignPage.setStampertype2("总经理章");
			}else if(stampertype2!=null&&stampertype2.equals("5")) {
				inputSignPage.setStampertype2("财务章");
			}else if(stampertype2!=null&&stampertype2.equals("6")) {
				inputSignPage.setStampertype2("人事章");
			}else if(stampertype2!=null&&stampertype2.equals("7")){
				inputSignPage.setStampertype2("其他");
			}
		}
	}
	public void transImage(InformationFpm001 allInformationOffpm001,Map<String, Object> map) {
		//如果是pdf文件，文件类型是1
		//如果是jpg文件，文件类型是0
		//图片1
		if(allInformationOffpm001.getImage()!=null) {
			String fileType1 = SplitStr.getFileType(allInformationOffpm001.getImage());
			System.out.println("图片1的状态："+fileType1);
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
		//图片2
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
		//图片3
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
		//图片4
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
		//图片5
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
