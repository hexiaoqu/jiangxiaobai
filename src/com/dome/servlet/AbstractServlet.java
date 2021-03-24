package com.dome.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dome.utils.EmptyUtils;
import com.dome.utils.PrintUtil;
import com.dome.utils.ReturnResult;




/**
 * 源码分析�?
 * 首先想个问题：为�?么要定义�?个抽象类�?
 * 首先我们要知道，要使用Servlet必须继承javax.servlet.http.HttpServlet�?.因为抽象类可以定义抽象方法的同时也能实现
 * 自定义的方法，所以我们为了有良好的扩展�?�，在这里我们则定义�?个抽象类AbstractServlet继承javax.servlet.http.HttpServlet
 * 类�?�因为抽象类是可以被继承，抽象类里已实现的方法可以被重写，未实现的抽象方法也可以被实现，�?以我们其他的Servlet类则可继�?
 * AbstractServlet类�??
 * 
 * 抽象方法：getServletClass()
 *     返回Class类类型，Class类十分特殊�?�它和其他类�?样继承自Object类，Class对象包含了与类相关的信息。事实上，Class对象
 * 就是用来创建类的�?有的"普�??"对象的�?�类是程序的�?部分，每个类都有�?个Class对象。换�?之，每当编写并编译了�?个新类时，就会产生一�?
 * Class对象（恰当的说，是被保存在一个同名的.class文件�?)。在运行时，当我们想生成这个类的对象时，运行这个程序的Java虚拟机（JVM�?
 * 首先�?查这个类的Class对象是否已经加载。如果尚未加载，JVM就会根据类名查找.class文件，并将其载入。一旦某个类的Class对象被载�?
 * 内存，它就被用来创建这个类的对象。这个抽象方法的定义主要意义实在其他的Servlet类中被重写，其返回的是当前类的Class对象�?
 * 
 * Servlet中的方法�?
 * doGet()�?般来说我们是用不到doGet()方法的，doGet方法在提交表单的时�?�会在url后面显示提交的内容，�?以不安全。�?�且doGet方法只能
 * 提交256个字�?(1024字节）， 而doPost方法没有限制，因为get方式数据的传输载体是url,而form方式提交的话，POST方式是HTTP头键值对�?
 * 通常我们使用的都是doPost()方法，你只要在servlet中让这两个方法互相调用就行了。例如在doGet方法中这样写�?
 * 
 * @Override
 *�?	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 *		// TODO Auto-generated method stub
 *		doPost(req,resp);  //如果是get请求转发给doPost处理，如果是POST请求依旧doPost处理
 *	}
 * 
 * 在把业务逻辑直接写在doPost方法中，Servlet碰到doGet方法调用直接就会去调用doPost()方法，因为他们的参数都一样�?��?�且doGet方法
 * 处理中文问题很困难，要写过滤器之类的�?
 * 
 * 
 * 页面显示逻辑方法：toView()
 * 入参result不为�?(封装的EmptyUtils类的isEmpty方法实现 ),则有两种显示方法�?
 * 第一种直接跳转到相应的jsp页面，请求的信息也一起转移不会丢失，这里使用的转发取代了重定向实现了页面跳转（重定向是：
 * response.sendRedirect("welcom.jsp")，转发的作用�? 是在多个页面交互过程中实现请求数据的共享，转发的实现:
 * RequestDispatcher对象的forward()方法：如下：
 * 
 * <%
 *      RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
 *      rd.forword(request,response);
 * %>
 * 
 * 第二种如果前端请求是用的JQuery包的ajax异步请求，则返回json格式对象字符串至前端，不�?要渲染jsp页面�?
 * 入参result利用instanceof运算符用来在运行时指出对象是否是特定类的�?个实例，instanceof通过返回�?�?
 * 布尔值来指出，这个对象是否是这个特定类或者它的子类的�?个实例�?�如果是String类的实例则返回某个jsp页面�?
 * 如果不是String，用fastjson jar包中JSONObject.toJSONString方法将这个对象转成json字符串，返回
 * 能够向一个客户机发�?�字符的PrintWriter对象，其实就是I/O系统的文本输出流打印对象，最后刷新该流的缓冲区�??
 * （封装的工具类PrintUtil类的write方法实现�?
 * 
 * doPost()方法�?
 * 这里如果action入参不为空，这里主要是使用反射机制动态调用类的方法，�?要使�?
 * java.lang.reflect.Method中的Object.invoke(Object obj,Object[] args)方法，第�?个参�?
 * 是指�?要调用的那个方法的隐式参数，也就是那个方法所属的对象。第二个参数是指调用那个方法的显示参数，因为�?�?
 * 方法可有很多参数。所以这里是result=method.invoke(this,req,resp);由于我们�?要用的Method类的对象
 * ，和invoke方法返回的是Object对象。所以事先声明定义为null
 * 
 * Method method = null;
 * Object result =null;
 * 然�?�，要使用Method类的invoke方法之前，要使用Class类的getDeclaredMethod方法，返回和指定方法名匹配的Method对象�?
 * 
 * 
 */



/**
 * 公共的Servlet抽象�?
 * @author 10947
 *
 */
public abstract class AbstractServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	/**
	 * 定义获取Servlet类的抽象方法
	 * @return
	 */
	public abstract Class getServletClass();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);  //如果是get请求转发给doPost处理，如果是POST请求依旧doPost处理
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String actionIndicator = req.getParameter("action");
		Method method = null;
		Object result = null;
		try {
			if(EmptyUtils.isEmpty(actionIndicator)) {
				result = execute(req,resp);
			}else {
				/**
				 * 反射第一步拿到Class类的对象，但是在这里不是拿到AbstractServlet类的Class对象�?
				 * 因为Action的方法是写在其他的Servlet里，而不是写在父类AbstractServlet
				 * �?以我�?要拿到其他类的Class类的对象，所以才有了getServletClass这个抽象方法的存在，
				 * 是为了让子类去实现，这样就可以间接拿到子�? Class对象
				 * �?以只要调�? getServletClass()就可以获取子�? Class对象
				 */
				method = getServletClass().getDeclaredMethod(actionIndicator, HttpServletRequest.class,HttpServletResponse.class);
				result = method.invoke(this, req,resp);
			}
			
			toView(req,resp,result);
			
		}catch(NoSuchMethodException e) {
			String viewName = "404.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
			if(!EmptyUtils.isEmpty(result)) {
				if(result instanceof String) {
					String viewName = "500.jsp";
					req.getRequestDispatcher(viewName).forward(req, resp);
				}else {
					ReturnResult returnResult = new ReturnResult();
					returnResult.returnFail("系统错误");
					PrintUtil.write(returnResult, resp);
				}
			}else {
				String viewName = "500.jsp";
				req.getRequestDispatcher(viewName).forward(req, resp);
			}
		}
	}

	
	/**
	 * MVC：因为一个Servlet�?终是要渲染到页面，会�? req和resp重定向转发到某个jsp页面�? 这种方法叫做 显示1.
	 * Ajax: 返回的数据，是�?�过IO流将JSON字符串打印给浏览器作为输出�?�不�?要JSP页面。也不需要req,resp
	 * 
	 * Object result: 如果是String类型�? 这个result的�?�就是某个jsp页面的文件名！如果不是String类型，�?�是对象那就是Ajax
	 */
	
	/**
	 * 显示方法  �? MVC+Ajax
	 * @param req
	 * @param resp
	 * @param result
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void toView(HttpServletRequest req,HttpServletResponse resp,Object result) throws IOException,ServletException {
		if(!EmptyUtils.isEmpty(result)) {
			    //MVC 返回
			if(result instanceof String) {
				String viewName = result.toString() + ".jsp";
				req.getRequestDispatcher(viewName).forward(req, resp);
			}else {
				//Ajax返回
				PrintUtil.write(result, resp);
			}
		}
	}
	
	
	/**
	 * Ĭ�Ϸ���
	 * @param req
	 * @param resp
	 * @return
	 */
	public Object execute(HttpServletRequest req,HttpServletResponse resp) {
		return "pre/dl";
	}
	
	
}
