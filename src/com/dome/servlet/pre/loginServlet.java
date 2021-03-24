package com.dome.servlet.pre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dome.dao.user.UserDao;
import com.dome.dao.user.UserDaoImpl;
import com.dome.entity.Login;
import com.dome.entity.User;
import com.dome.param.EmailParams;
import com.dome.param.UserParams;
import com.dome.service.login.loginService;
import com.dome.service.login.loginServiceImpl;
import com.dome.service.user.UserService;
import com.dome.service.user.Impl.UserServiceImpl;
import com.dome.servlet.AbstractServlet;
import com.dome.utils.PrintUtil;
import com.dome.utils.ReturnResult;
import com.dome.utils.SecurityUtils;
import com.dome.utils.SendMail;
import com.dome.utils.VerifyUtil;

@WebServlet(urlPatterns = {"/login"}, name = "login" ,asyncSupported=true)
public class loginServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	private EmailParams ep;
	private loginService ls;
	
	
	//修改密码
	public EmailParams updatepwd(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession ses = request.getSession();
        request.setCharacterEncoding("utf-8");		
        User user = null;
		String url = request.getParameter("url");
		String code = request.getParameter("code");
		String pwd1 = request.getParameter("pwd1");
		int id= Integer.parseInt(url);
		user = userService.FinderUserAll(id);
		ep = new EmailParams();	
		Map<String, Object> map = VerifyUtil.createImage();
		if(ses.getAttribute("email") != null) {
			if(code.equals(user.getCode())) {
				String mpwd = SecurityUtils.md5Hex(pwd1);
				int states = userService.updateUserPwd(id, mpwd);				
				String code1 = VerifyUtil.code;	
				String newCode = SecurityUtils.md5Hex(code1);	
				userService.updateUserCode(user.getuId(), newCode);
				ep.setStates(states);
				ep.setDate(user);
				ses.removeAttribute("email");
			}else {
				ep.setCode("验证码错误");
				ep.setStates(1);
			}	
		}else {
			System.out.println("session没有");
			String code1 = VerifyUtil.vuCode();
			String newCode = SecurityUtils.md5Hex(code1);
			userService.updateUserCode(user.getuId(), newCode);
		}	
		return ep;		
	}
	
	//发送邮箱
	public EmailParams email(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession ses = request.getSession();
        request.setCharacterEncoding("utf-8");
				User user=null;
				String name = request.getParameter("username");
				String email = request.getParameter("email");				
				ep = new EmailParams();
				user= userService.FinderUserEmail(name, email);						
		if(user != null) {
			Map<String, Object> map = VerifyUtil.createImage();
			String code = VerifyUtil.code;	
			
			ep.setDate(user);
			ep.setStates(0);
			ep.setCode(code);
			userService.updateUserCode(user.getuId(), code);
			ses.setMaxInactiveInterval(1*60);
			ses.setAttribute("email", user);

			
			AsyncContext context = request.startAsync(request, response);
	      	   context.setTimeout(0); 
	      	   context.complete();  
	      	   request = (HttpServletRequest) context.getRequest();
	      	   response = (HttpServletResponse) context.getResponse(); 
	      	   Thread t = new Thread(new ExEmail(context,user,code));
	      	   t.start();

			
		}else {
			ep.setStates(1);
			System.out.println("meiyou");
		}		
		return ep;

	}

	public UserParams index(HttpServletRequest request,HttpServletResponse response) throws Exception{
        	HttpSession ses = request.getSession();
        	request.setCharacterEncoding("utf-8");		
        	String name = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			String rember = request.getParameter("rember");
			User user= null;
			String mpwd = SecurityUtils.md5Hex(pwd);
			user = userService.FinderUser(name, mpwd);
			UserParams up = new UserParams();
			if(ses.getAttribute("user")==null) {
				if(user != null) {	 
					user.setStatus(0);
					ses.setMaxInactiveInterval(10*60);
					ses.setAttribute("user", user);
					 ReturnResult result = new ReturnResult();
					up.setStates(0);					
					up.setUser(user);	
					if("y".equals(rember)) {
						up.setRem(0);
						Cookie cookie1= new Cookie("name",name);
						Cookie cookie2= new Cookie("pwd",pwd);					
						cookie1.setMaxAge(1*60);
						cookie2.setMaxAge(1*60);					
						response.addCookie(cookie1);
						response.addCookie(cookie2);					
					}							  
				}else {
					up.setStates(1);
				}			
			}else {
				user.setStatus(1);
				up.setStates(1);
			}
		
			
			AsyncContext context = request.startAsync(request, response);
	      	   context.setTimeout(0); 
	      	   context.complete();  
	      	   request = (HttpServletRequest) context.getRequest();
	      	   response = (HttpServletResponse) context.getResponse(); 
	      	   Thread t = new Thread(new Excutor(context,user,name));
	      	   t.start();
			return up;
	}
	
	
	
	
	
    public class ExEmail implements Runnable{
    	private AsyncContext context;
    	private User user;
    	private String aaa;
		public ExEmail(AsyncContext context,User user,String aaa) {
			this.context = context;
			this.user = user;
			this.aaa= aaa;
		}
		@Override
		public void run() {
			SendMail mySendMail = new SendMail();	
			mySendMail.sendMail(user.getuEmail(),"/login?action=ccc#uid="+user.getuId()+" "+"："+aaa);
		}
    }
	
    //开启一个线程类 （内部类)
    public class Excutor implements Runnable{
    	private AsyncContext context;
    	private User user;
    	private String name;
		public Excutor(AsyncContext context,User user,String name) {
			this.context = context;
			this.user = user;
			this.name = name;
		}
		@Override
		public void run() {
			int aa = 1;			
			String remark;
			Login login = new Login();
			if(user !=null) {
				remark="登录成功";
				aa=0;	
				login.setUserId(user.getuId());
			}else {
				remark="用户:"+ name +"登录失败";
			}
				login.setLoginStatus(aa);
				login.setLoginRemark(remark);
				ls =  new  loginServiceImpl();	
				ls.saveLogin(login);
		}
    }
    
    
    
	public String bbb(HttpServletRequest request,HttpServletResponse response)throws Exception{
        return "/pre/text";
    }
	public String ccc(HttpServletRequest request,HttpServletResponse response)throws Exception{
        return "/pre/step";
    }
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userService = new UserServiceImpl();		
	}
		
	@Override
	public Class getServletClass() {	
		return loginServlet.class;
	}
}
