package com.dome.servlet;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.dome.entity.Login;
import com.dome.entity.User;
import com.dome.entity.UserGift;
import com.dome.param.UserParams;
import com.dome.service.gift.giftService;
import com.dome.service.gift.giftServiceImpl;
import com.dome.service.login.loginService;
import com.dome.service.login.loginServiceImpl;
import com.dome.service.user.UserService;
import com.dome.service.user.Impl.UserServiceImpl;
import com.dome.utils.SecurityUtils;
import com.dome.utils.SendMail;
import com.dome.utils.VerifyUtil;

public class test1 {
	public static void main(String[] args) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(new Date()));
		//String SendTime=df.format(new Date());
		//System.out.println(SendTime);
//		UserGift ug = new UserGift();
//		UserGift uug = new UserGift();
//		giftService gs = new giftServiceImpl();		
//		Map<String,Object> map=gs.queryGiftList(2, 10);		
//		  Set<Entry<String, Object>> setResult = map.entrySet();
//		  Iterator<Entry<String,Object>> itr = setResult.iterator();
//		  List<UserGift> list = new ArrayList<>();
//		  Object list2 = null;
//		  int size = 0;
//		  while(itr.hasNext()){
//			    Entry<String,Object> entry = itr.next();
//			    if(entry.getKey().equals("list")){
//			    	list2 = entry.getValue();
//			    }
//			    if(entry.getKey().equals("conut")){
//			    	size = Integer.parseInt((String)entry.getValue());
//			    }
//		  }
//		    
//		  if(list2 instanceof ArrayList<?>) {
//				for(Object o:(List<?>)list2) {
//					list.add(UserGift.class.cast(o));
//				}
//		  }
//		for(Object l:list) {
//			System.out.println(l.toString());
//		}
//		System.out.println(size);
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date currnetDate = new Date();
//
//		Calendar calendar = Calendar.getInstance();
//
//		Test test = new Test();
//
//		Date dateResult = test.dayAddAndSub(Calendar.DATE, -1); //一个月中的某天

		//Date dateResult = test.dayAddAndSub(Calendar.HOUR , -4); //小时

		//Date dateResult = test.dayAddAndSub(Calendar.MINUTE , -60); //分钟

		//Date dateResult = test.dayAddAndSub(Calendar.MONTH , -1); //月

		//System.out.println(sdf.format(dateResult));
//		String lid="1";
//		Integer id =Integer.parseInt(lid);
//		
//		System.out.println(id+1);
		
//		ug.setLbaiduread("123133312313");
//		ug.setLintegral(111);
//		ug.setLname("礼品一号");
//		Double prioc=Double.parseDouble("110.1");
//		System.out.println(prioc);
//		ug.setLprice(prioc);
//		ug.setLread("aaaa");
//		gs.saveGift(ug);
//		uug=gs.getGiftID("礼品一号");
//		System.out.println(uug.getLid());
//		Map<String, Object> map = VerifyUtil.createImage();
//		String aaa = VerifyUtil.code;	
//		UserService us = new UserServiceImpl();
//		us.updateUserCode(1, aaa);
//		System.out.println(aaa);
//		Login login = new Login();
//		login.setUserId(1);
//		login.setLoginStatus(1);
//		login.setLoginRemark("yes");
//		loginService ls =  new  loginServiceImpl();	
//		UserService us= new UserServiceImpl();	
//		ls.saveLogin(login);
		//Login lo = login.saveLogin(login);
//		User user = null;
//		String name="sa";
//		String pwd="123";
//		String email="hxq1137793660@163.com";
//		int a =1111;
//		user=us.FinderUserEmail(name, email);
//		System.out.println(user.getuId());
		
//		String mpwd = SecurityUtils.md5Hex(pwd);
//		String aa="0";
//		int a= 2;
//		int aaaa=1;
//		Map<String, Object> map = VerifyUtil.createImage();
//		
//		String code = VerifyUtil.code;
//		String code1 = VerifyUtil.code;
//		System.out.println(code);
//		System.out.println(code1);
//		String aa = VerifyUtil.vuCode();
//		System.out.println(aa);
//		us.updateUserCode(aaaa, code);		
//		System.out.println("��֤�룺"+code);
//		System.out.println();
//		
//		User user = us.FinderUser(name, mpwd);
//		System.out.println(user.getuId());
//
//		us.updateUserCode(a, "0");
		
		//577044984
		//"����lsp+��king"<591108369@qq.com>;"���ຣ"<2962344181@qq.com>;"java1811 ����¶ 17775"<1438313697@qq.com>;
		//UserParams up = new UserParams();	
		//String email = "hxq1137793660@qq.com";
		//SendMail mySendMail = new SendMail();
		//mySendMail.sendMail(email,"/login?action=aaa"+"#"+code+" "+"yzm"+code);
//		mySendMail.sendMail(email, "��֤�룺"+code);
//		String aa = SecurityUtils.md5Hex(code);
//		System.out.println(aa);
//		String name="user";
//		String pwd="123";
//		String aa= SecurityUtils.md5Hex(pwd);
//		User user = us.FinderUser(name, pwd);
//		System.out.println(user.getuName());
//		System.out.println(user.getuPwd());
//		System.out.println(user.getCode());
		
		
	}
}
