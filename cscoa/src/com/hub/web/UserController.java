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
	//��̬�����������ֻ����һ�Σ���ȡ�����ļ�,�����ļ���λ�÷���
	static String imgbase;
	static{
		StringBuilder result = new StringBuilder();
        try{
        	//static�������������κ���/���������޴ӻ�ȡ
//        	InputStream resourceAsStream = UserController.class.getClassLoader().getResourceAsStream("imgpath.txt"); 
//        	BufferedReader br=new BufferedReader(new InputStreamReader(resourceAsStream));
        	//���ز���·����
        	//BufferedReader br = new BufferedReader(new FileReader("D:/Tomcat6.0/webapps/config_cscoa/imgpath.txt"));//����һ��BufferedReader������ȡ�ļ�
        	BufferedReader br = new BufferedReader(new FileReader("E:/Tomcat6.0/webapps/config_cscoa/imgpath.txt"));//����һ��BufferedReader������ȡ�ļ�
        	
        	//�ͻ�������ͼƬ��ַ
//        	File file = new File("./imgpath.txt");
//            BufferedReader br = new BufferedReader(new FileReader(file));//����һ��BufferedReader������ȡ�ļ�
            
//        	BufferedReader br = new BufferedReader(new FileReader("C:/Tomcat6.0/webapps/config_cscoa/imgpath.txt"));//����һ��BufferedReader������ȡ�ļ�
            //��˾������ͼƬ��ַ
        	//BufferedReader br = new BufferedReader(new FileReader("D:/Program Files/Tomcat6.0/webapps/config_cscoa/imgpath.txt"));//����һ��BufferedReader������ȡ�ļ�
        	//
        	//BufferedReader br = new BufferedReader(new FileReader("imgpath.txt"));//����һ��BufferedReader������ȡ�ļ�
        	
            String s = null;
            while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
                if(s.contains("imgbasepath")){
                	String[] split = s.split("=");
                	if(split.length>1){
                		imgbase=split[1];
                		System.out.println("ͼƬ·����"+imgbase);
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
	
	//��DAOֱ�ӵ���ȡ���ݣ�û�о���Service�㣬�ӽṹ����˵����ѧ�����Ǻ�ֱ��
	@Autowired(required = false) 
	private DetailsInformationDao detailsInformationDao;
	/**
	 * �״η��ʽ����¼���棺
	 * ������ַhttp://localhost:8080/cscoa_ssm
	 * �������ʣ�http://xyfastbi.vicp.io/cscoa/
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
	 * �ɵ�¼������뵽�����棬�ж��û����Ƿ���ȷ��
	 * �����ȷ����������棬
	 * ����ȷ�򷵻ص�¼���沢��ʾ����
	 * @param userLevel
	 * @param request
	 * @return
	 */
	@RequestMapping("/mainPage")
	private String mainPage(Map<String, Object> map,UserLevel userLevel,HttpServletRequest request,HttpServletResponse response){
		int userCount = userService.login(userLevel);
		if(userCount==1){
			request.setAttribute("error", null);

			//�û�����������֤�ɹ��󣬽��û�������cookie���棬�Ժ���ã�ע��Ҫ��response���뵽�������
			Cookie cookie = new Cookie("userLevel", userLevel.getUsername()); 
			response.addCookie(cookie);

			//��ȡ�������ĸ���
			//������
			int singleCount = auditPageService.getAuditSize(userLevel.getUsername());
			map.put("single_count", singleCount);
			//�ಿ��
			int multiCount = multiAuditPageService.getAuditSize(userLevel.getUsername());
			map.put("multi_count", multiCount);
			//��ͨ
			int trafficCount = trafficAuditPageService.getAuditSize(userLevel.getUsername());;
			map.put("traffic_count", trafficCount);
			//����
			int travelCount = travelAuditPageService.getAuditSize(userLevel.getUsername());;
			map.put("travel_count", travelCount);
			//�ݸ���
			int tempCount = tempAuditPageService.getAuditSize(userLevel.getUsername());
			map.put("temp_count", tempCount);
			//��ӡ����
			int signCount = signAuditPageService.getAuditSize(userLevel.getUsername());
			map.put("sign_count", signCount);
			//������
			int ksmCount = ksmAuditPageService.getAuditSize(userLevel.getUsername());
			map.put("ksm_count", ksmCount);
			//�������յ�
			int rtysCount = rTysAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("rtys_count", rtysCount);
			
			//�ݸ��
			int rtzfCount = rTzfAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("rtzf_count", rtzfCount);
			
			//Ԥ���
			int rtyfCount = rTyfAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("rtyf_count", rtyfCount);
			
			//2018-03-13�����ĸ�ģ��
			//1.�����������2.ˮ����������3.����������ݸ���4.ˮ���������ݸ�
			int zjjk_count = zjjkAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("zjjk_count", zjjk_count);
			
			int sdjk_count = sdjkAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("sdjk_count", sdjk_count);
			
			int zjzf_count = zjzfAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("zjzf_count", zjzf_count);
			
			int sdzf_count = sdzfAuditPageServiceImpl.getAuditSize(userLevel.getUsername());
			map.put("sdzf_count", sdzf_count);
			
			//���ص�������
			return "main";
		}else{
			request.setAttribute("error", "��������˺Ż���������������������");
//			return "forward:/login.jsp";
			return "login";
		}
	}
	/*��д���󣬷���main.jsp��Ҫ���²�ѯ���ݿ⣬���и���*/
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
		//��ȡ�������ĸ���
		//������
		int singleCount = auditPageService.getAuditSize(username);
		map.put("single_count", singleCount);
		//�ಿ��
		int multiCount = multiAuditPageService.getAuditSize(username);
		map.put("multi_count", multiCount);
		//��ͨ
		int trafficCount = trafficAuditPageService.getAuditSize(username);;
		map.put("traffic_count", trafficCount);
		//����
		int travelCount = travelAuditPageService.getAuditSize(username);;
		map.put("travel_count", travelCount);
		//�ݸ���
		int tempCount = tempAuditPageService.getAuditSize(username);
		map.put("temp_count", tempCount);
		//��ӡ����
		int signCount = signAuditPageService.getAuditSize(username);
		map.put("sign_count", signCount);
		//����������
		int ksmCount = ksmAuditPageService.getAuditSize(username);
		map.put("ksm_count", ksmCount);
		//�������յ�
		int rtysCount = rTysAuditPageServiceImpl.getAuditSize(username);
		map.put("rtys_count", rtysCount);
		//�ݸ��
		int rtzfCount = rTzfAuditPageServiceImpl.getAuditSize(username);
		map.put("rtzf_count", rtzfCount);
		
		//Ԥ���
		int rtyfCount = rTyfAuditPageServiceImpl.getAuditSize(username);
		map.put("rtyf_count", rtyfCount);
		
		//2018-03-13�����ĸ�ģ��
		//1.�����������2.ˮ����������3.����������ݸ���4.ˮ���������ݸ�
		int zjjk_count = zjjkAuditPageServiceImpl.getAuditSize(username);
		map.put("zjjk_count", zjjk_count);
		
		int sdjk_count = sdjkAuditPageServiceImpl.getAuditSize(username);
		map.put("sdjk_count", sdjk_count);
		
		int zjzf_count = zjzfAuditPageServiceImpl.getAuditSize(username);
		map.put("zjzf_count", zjzf_count);
		
		int sdzf_count = sdzfAuditPageServiceImpl.getAuditSize(username);
		map.put("sdzf_count", sdzf_count);
		
		
		//���ص�������
		return "main";
	}
	/**
	 * ����ҳ��������ѯ�����еĴ������б�
	 * @param map
	 * @return	���ص��������б����
	 */
	@RequestMapping("/audit")
	private String auditPage(Map<String, Object> map,HttpServletRequest request){
		String username="";
		String checkid = request.getParameter("checkid");
		String pagestr = request.getParameter("pagenum");
		//System.out.println(pagestr);
		int pagenum = Integer.parseInt(pagestr);//stringתint�� ����20��һҳ��ʾ20��
		//System.out.println(pagenum);
		//int pagenum = Integer.parseInt(pagestr)*20;
		Cookie[] userCookie = request.getCookies();
		for(Cookie cookie : userCookie){
	        if(cookie.getName().equals("userLevel")){
	            username = cookie.getValue();
	        }
	    }
		List<AuditPage> allAudit = auditPageService.getAllAudit(username,pagenum);
		//System.out.println("����������"+allAudit.size());
		//����ÿһ��������ݣ����������޸ġ�20170504��
/*[20170505�޸ģ�����Ҫ��ʾ������]
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
		//��ȡ�������ܸ���
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
		System.out.println("�޸����뿪ʼ");
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
			//˵�������������ȷ
			System.out.println("˵�������������ȷ");
			if(newpwd.equals(confirm)){
				//˵���������������һ��
				System.out.println("˵���������������һ��");
				userService.setPasswod(username, newpwd);
				System.out.println("�ɹ�");
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



