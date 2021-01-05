package com.hub.web;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hub.dao.DetailsInformationDao;
import com.hub.entity.AuditPage;
import com.hub.entity.UserLevel;
import com.hub.service.impl.AuditLevelQueryServiceImpl;
import com.hub.service.impl.AuditPageServiceImpl;
import com.hub.service.impl.DetailsPageServiceImpl;
import com.hub.service.impl.KsmAuditPageServiceImpl;
import com.hub.service.impl.MultiAuditPageServiceImpl;
import com.hub.service.impl.RTyfAuditPageServiceImpl;
import com.hub.service.impl.RTyfAuditQueryServiceImpl;
import com.hub.service.impl.RTysAuditPageServiceImpl;
import com.hub.service.impl.RTysAuditQueryServiceImpl;
import com.hub.service.impl.RTzfAuditPageServiceImpl;
import com.hub.service.impl.RTzfAuditQueryServiceImpl;
import com.hub.service.impl.SdjkAuditPageServiceImpl;
import com.hub.service.impl.SdjkAuditQueryServiceImpl;
import com.hub.service.impl.SdzfAuditPageServiceImpl;
import com.hub.service.impl.SdzfAuditQueryServiceImpl;
import com.hub.service.impl.SignAuditPageServiceImpl;
import com.hub.service.impl.TempAuditPageServiceImpl;
import com.hub.service.impl.TrafficAuditPageServiceImpl;
import com.hub.service.impl.TravelAuditPageServiceImpl;
import com.hub.service.impl.UserServiceImpl;
import com.hub.service.impl.ZjjkAuditPageServiceImpl;
import com.hub.service.impl.ZjjkAuditQueryServiceImpl;
import com.hub.service.impl.ZjzfAuditPageServiceImpl;
import com.hub.service.impl.ZjzfAuditQueryServiceImpl;
@Controller
public class UserController {
	//静态变量，在这个只运行一次，读取配置文件,配置文件的位置放在
	static String imgbase;
	static{
		StringBuilder result = new StringBuilder();
        try{
        	//static方法不隶属于任何类/对象，所以无从获取
//        	InputStream resourceAsStream = UserController.class.getClassLoader().getResourceAsStream("imgpath.txt"); 
//        	BufferedReader br=new BufferedReader(new InputStreamReader(resourceAsStream));
        	//本地测试路径：
        	//BufferedReader br = new BufferedReader(new FileReader("D:/Tomcat6.0/webapps/config_cscoa/imgpath.txt"));//构造一个BufferedReader类来读取文件
        	BufferedReader br = new BufferedReader(new FileReader("E:/Tomcat6.0/webapps/config_cscoa/imgpath.txt"));//构造一个BufferedReader类来读取文件
        	
        	//客户服务器图片地址
//        	File file = new File("./imgpath.txt");
//            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            
//        	BufferedReader br = new BufferedReader(new FileReader("C:/Tomcat6.0/webapps/config_cscoa/imgpath.txt"));//构造一个BufferedReader类来读取文件
            //公司服务器图片地址
        	//BufferedReader br = new BufferedReader(new FileReader("D:/Program Files/Tomcat6.0/webapps/config_cscoa/imgpath.txt"));//构造一个BufferedReader类来读取文件
        	//
        	//BufferedReader br = new BufferedReader(new FileReader("imgpath.txt"));//构造一个BufferedReader类来读取文件
        	
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(s.contains("imgbasepath")){
                	String[] split = s.split("=");
                	if(split.length>1){
                		imgbase=split[1];
                		System.out.println("图片路径："+imgbase);
                	}
                }
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	@Autowired(required = false) 
	@Qualifier("MultiAuditPageServiceImpl")
	private MultiAuditPageServiceImpl multiAuditPageService;
	
	@Autowired(required = false) 
	@Qualifier("TrafficAuditPageServiceImpl")
	private TrafficAuditPageServiceImpl trafficAuditPageService;
	
	@Autowired(required = false) 
	@Qualifier("TravelAuditPageServiceImpl")
	private TravelAuditPageServiceImpl travelAuditPageService;
	
	@Autowired(required = false) 
	@Qualifier("TempAuditPageServiceImpl")
	private TempAuditPageServiceImpl tempAuditPageService;
	
	@Autowired(required = false) 
	@Qualifier("KsmAuditPageServiceImpl")
	private KsmAuditPageServiceImpl ksmAuditPageService;
	
	
	@Autowired(required = false) 
	@Qualifier("SignAuditPageServiceImpl")
	private SignAuditPageServiceImpl signAuditPageService;
	
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
	@Qualifier("RTysAuditPageServiceImpl")
	private RTysAuditPageServiceImpl rTysAuditPageServiceImpl;
	
	@Autowired(required = false)
	@Qualifier("RTysAuditQueryServiceImpl")
	private RTysAuditQueryServiceImpl rTysAuditQueryServiceImpl;
	
	@Autowired(required = false) 
	@Qualifier("RTyfAuditPageServiceImpl")
	private RTyfAuditPageServiceImpl rTyfAuditPageServiceImpl;
	
	@Autowired(required = false)
	@Qualifier("RTyfAuditQueryServiceImpl")
	private RTyfAuditQueryServiceImpl rTyfAuditQueryServiceImpl;
	
	@Autowired(required = false) 
	@Qualifier("RTzfAuditPageServiceImpl")
	private RTzfAuditPageServiceImpl rTzfAuditPageServiceImpl;
	
	@Autowired(required = false)
	@Qualifier("RTzfAuditQueryServiceImpl")
	private RTzfAuditQueryServiceImpl rTzfAuditQueryServiceImpl;
	
	@Autowired(required = false)
	@Qualifier("ZjzfAuditPageServiceImpl")
	private ZjzfAuditPageServiceImpl zjzfAuditPageServiceImpl;
	
	@Autowired(required = false) 
	@Qualifier("ZjzfAuditQueryServiceImpl")
	private ZjzfAuditQueryServiceImpl zjzfAuditQueryServiceImpl;
	
	
	
	@Autowired(required = false)
	@Qualifier("ZjjkAuditPageServiceImpl")
	private ZjjkAuditPageServiceImpl zjjkAuditPageServiceImpl;
	
	@Autowired(required = false) 
	@Qualifier("ZjjkAuditQueryServiceImpl")
	private ZjjkAuditQueryServiceImpl zjjkAuditQueryServiceImpl;
	
	@Autowired(required = false)
	@Qualifier("SdjkAuditPageServiceImpl")
	private SdjkAuditPageServiceImpl sdjkAuditPageServiceImpl;
	
	@Autowired(required = false) 
	@Qualifier("SdjkAuditQueryServiceImpl")
	private SdjkAuditQueryServiceImpl sdjkAuditQueryServiceImpl;
	
	@Autowired(required = false)
	@Qualifier("SdzfAuditPageServiceImpl")
	private SdzfAuditPageServiceImpl sdzfAuditPageServiceImpl;
	
	@Autowired(required = false) 
	@Qualifier("SdjkAuditQueryServiceImpl")
	private SdzfAuditQueryServiceImpl sdzfAuditQueryServiceImpl;
	
	//此DAO直接调用取数据，没有经过Service层，从结构上来说不科学，但是很直观
	@Autowired(required = false) 
	private DetailsInformationDao detailsInformationDao;
	/**
	 * 首次访问进入登录界面：
	 * 本地网址http://localhost:8080/cscoa_ssm
	 * 外网访问：http://xyfastbi.vicp.io/cscoa/
	 * @return
	 */

	@RequestMapping("")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("login")
	public String toLogin1(){
		return "login";
	}
	/**
	 * 由登录界面进入到主界面，判断用户名是否正确，
	 * 如果正确则进入主界面，
	 * 不正确则返回登录界面并提示错误
	 * @param userLevel
	 * @param request
	 * @return
	 */
	@RequestMapping("/mainPage")
	private String mainPage(Map<String, Object> map,UserLevel userLevel,HttpServletRequest request,HttpServletResponse response){
		int userCount = userService.login(userLevel);
		if(userCount==1){
			request.setAttribute("error", null);

			//用户名和密码验证成功后，将用户名存在cookie里面，以后调用，注意要用response加入到浏览器中
			Cookie cookie = new Cookie("userLevel", userLevel.getUsername()); 
			response.addCookie(cookie);

			//获取待审批的个数
			//单部门
			int singleCount = auditPageService.getAuditSize(userLevel.getUsername());
			map.put("single_count", singleCount);
			//多部门
			int multiCount = multiAuditPageService.getAuditSize(userLevel.getUsername());
			map.put("multi_count", multiCount);
			//交通
			int trafficCount = trafficAuditPageService.getAuditSize(userLevel.getUsername());;
			map.put("traffic_count", trafficCount);
			//差旅
			int travelCount = travelAuditPageService.getAuditSize(userLevel.getUsername());;
			map.put("travel_count", travelCount);
			//暂付款
			int tempCount = tempAuditPageService.getAuditSize(userLevel.getUsername());
			map.put("temp_count", tempCount);
			//用印申请
			int signCount = signAuditPageService.getAuditSize(userLevel.getUsername());
			map.put("sign_count", signCount);
			//康是美
			int ksmCount = ksmAuditPageService.getAuditSize(userLevel.getUsername());
			map.put("ksm_count", ksmCount);
			//付款验收单
			int rtysCount = rTysAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("rtys_count", rtysCount);
			
			//暂付款单
			int rtzfCount = rTzfAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("rtzf_count", rtzfCount);
			
			//预付款单
			int rtyfCount = rTyfAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("rtyf_count", rtyfCount);
			
			//2018-03-13新增四个模块
			//1.租金类批量借款，2.水电类批量借款，3.租金类批量暂付，4.水电类批量暂付
			int zjjk_count = zjjkAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("zjjk_count", zjjk_count);
			
			int sdjk_count = sdjkAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("sdjk_count", sdjk_count);
			
			int zjzf_count = zjzfAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("zjzf_count", zjzf_count);
			
			int sdzf_count = sdzfAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("sdzf_count", sdzf_count);
			
			//返回到主界面
			return "main";
		}else{
			request.setAttribute("error", "您输入的账号或者密码有误，请重新输入");
//			return "forward:/login.jsp";
			return "login";
		}
	}
	/*填写表单后，返回main.jsp，要重新查询数据库，进行更新*/
	@RequestMapping("/main")
	public String getMain(Map<String, Object> map,HttpServletRequest request){
		String username="";
		String checkid = request.getParameter("checkid");
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
			if(cookie.getName().equals("userLevel")){
				username = cookie.getValue();
				map.put("user",username);
			}
		} 
		//获取待审批的个数
		//单部门
		int singleCount = auditPageService.getAuditSize(username);
		map.put("single_count", singleCount);
		//多部门
		int multiCount = multiAuditPageService.getAuditSize(username);
		map.put("multi_count", multiCount);
		//交通
		int trafficCount = trafficAuditPageService.getAuditSize(username);;
		map.put("traffic_count", trafficCount);
		//差旅
		int travelCount = travelAuditPageService.getAuditSize(username);;
		map.put("travel_count", travelCount);
		//暂付款
		int tempCount = tempAuditPageService.getAuditSize(username);
		map.put("temp_count", tempCount);
		//用印申请
		int signCount = signAuditPageService.getAuditSize(username);
		map.put("sign_count", signCount);
		//康是美申请
		int ksmCount = ksmAuditPageService.getAuditSize(username);
		map.put("ksm_count", ksmCount);
		//付款验收单
		int rtysCount = rTysAuditPageServiceImpl.getAuditSize(username);
		map.put("rtys_count", rtysCount);
		//暂付款单
		int rtzfCount = rTzfAuditPageServiceImpl.getAuditSize(username);
		map.put("rtzf_count", rtzfCount);
		
		//预付款单
		int rtyfCount = rTyfAuditPageServiceImpl.getAuditSize(username);
		map.put("rtyf_count", rtyfCount);
		
		//2018-03-13新增四个模块
		//1.租金类批量借款，2.水电类批量借款，3.租金类批量暂付，4.水电类批量暂付
		int zjjk_count = zjjkAuditPageServiceImpl.getAuditSize(username);
		map.put("zjjk_count", zjjk_count);
		
		int sdjk_count = sdjkAuditPageServiceImpl.getAuditSize(username);
		map.put("sdjk_count", sdjk_count);
		
		int zjzf_count = zjzfAuditPageServiceImpl.getAuditSize(username);
		map.put("zjzf_count", zjzf_count);
		
		int sdzf_count = sdzfAuditPageServiceImpl.getAuditSize(username);
		map.put("sdzf_count", sdzf_count);
		
		
		//返回到主界面
		return "main";
	}
	/**
	 * 由首页过来，查询出所有的待审批列表
	 * @param map
	 * @return	返回到待审批列表界面
	 */
	@RequestMapping("/audit")
	private String auditPage(Map<String, Object> map,HttpServletRequest request){
		String username="";
		String checkid = request.getParameter("checkid");
		String pagestr = request.getParameter("pagenum");
		//System.out.println(pagestr);
		int pagenum = Integer.parseInt(pagestr);//string转int后， 乘以20，一页显示20个
		//System.out.println(pagenum);
		//int pagenum = Integer.parseInt(pagestr)*20;
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	            username = cookie.getValue();
	        }
	    }
		List<AuditPage> allAudit = auditPageService.getAllAudit(username,pagenum);
		//System.out.println("还有数据吗："+allAudit.size());
		//遍历每一个审核数据，并将日期修改【20170504】
/*[20170505修改，不需要显示日期了]
 * 		Iterator<AuditPage> it = allAudit.iterator();
		while(it.hasNext()){
			AuditPage auditPage = it.next();
			String datestr = auditPage.getDate();
			String[] datasplit = datestr.split(" ");
			auditPage.setDate(datasplit[0]);
		}*/
		
		//Collections.reverse(allAudit);
		map.put("audits", allAudit);
		map.put("pagenum", pagestr);
		//获取审批的总个数
		int auditSize = auditPageService.getAuditSize(username);
		int maxpage = auditSize/10;
		//System.out.println(maxpage);
		String maxpagestr = maxpage+"";
		map.put("maxpage", maxpage);
		return "audit";
	}
	@RequestMapping("/updataPassword")
	public String updataPassword(){
		
		return "setpwd";
	}
	
	@RequestMapping("/setPassword")
	public String setPassword(Map<String, Object> map , HttpServletRequest request){
		System.out.println("修改密码开始");
		String username="";
		
		String oldpwd = request.getParameter("oldpwd");
		System.out.println("oldpwd:"+oldpwd);
		String newpwd = request.getParameter("newpwd");
		System.out.println("newpwd:"+newpwd);
		String confirm = request.getParameter("confirm");
		System.out.println("confirm:"+confirm);
		
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
			if(cookie.getName().equals("userLevel")){
				username = cookie.getValue();
			}
		}
		UserLevel userLevel =  new UserLevel(username,oldpwd);
		if(userService.login(userLevel)==1){
			//说明输入的密码正确
			System.out.println("说明输入的密码正确");
			if(newpwd.equals(confirm)){
				//说明两次输入的密码一样
				System.out.println("说明两次输入的密码一样");
				userService.setPasswod(username, newpwd);
				System.out.println("成功");
			}else{
				map.put("setpwd", "no");
				return "setpwd";
			}
		}else{
			map.put("setpwd", "no");
			return "setpwd";
		}
		map.put("setpwd", "ok");
		return "forward:/main";
	}
	
}



