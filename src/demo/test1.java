package demo;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class test1 {
	public static void main(String[] args) {
		Set<Object> s;
		String str;
		Properties c = new Properties();
		c.put("中国", "北京");
		c.put("俄罗斯", "莫斯科");
		c.put("日本", "东京");
		c.put("法国", "巴黎");
		c.put("英国", "伦敦");
		
		s = c.keySet();//返回一个键的Set集合
		Iterator<Object> i = s.iterator();
		
		while(i.hasNext()){
			str = (String)i.next();
			System.out.println(str + c.getProperty(str));
		}
		System.out.println();
		
		//如果“美国”该键不存在，则返回默认值
		str = c.getProperty("美国", "没有发现");
		System.out.println(str);
	}
}
