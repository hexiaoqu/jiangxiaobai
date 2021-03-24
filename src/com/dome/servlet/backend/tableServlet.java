package com.dome.servlet.backend;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dome.entity.Picture;
import com.dome.entity.UserGift;
import com.dome.param.EmailParams;
import com.dome.param.pictureParams;
import com.dome.service.gift.giftService;
import com.dome.service.gift.giftServiceImpl;
import com.dome.service.picture.pictureService;
import com.dome.service.picture.pictureServiceImpl;
import com.dome.service.user.Impl.UserServiceImpl;
import com.dome.servlet.AbstractServlet;
import com.dome.utils.UploadUtils;
import com.dome.utils.UploadUtils1;


@WebServlet(urlPatterns = {"/table"}, name = "table")
public class tableServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	
	private pictureService pc;
	private giftService gs;
	
	
	@Override
	public void init() throws ServletException {
		pc = new pictureServiceImpl();
		gs= new giftServiceImpl();
		
	}
	public void tableAdd(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String content=request.getParameter("content");		
	}
	public String giftAdd(HttpServletRequest request,HttpServletResponse response)throws Exception{
        return "/backend/giftAdd";
    }
//	String giftname = request.getParameter("giftname");
//	String baidutext = request.getParameter("baidutext");
//	String giftpirce= request.getParameter("giftprice");
//	String hot = request.getParameter("hot");
//	String fen = request.getParameter("file");
//	String giftfen = request.getParameter("giftfen");
	public EmailParams giftText(HttpServletRequest request,HttpServletResponse response) throws Exception {
		EmailParams ep = new EmailParams();		
		//方式一：不用工具类
//		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String username = "";
		String password = "";
		UserGift ug = new UserGift();
		Double prioc=0.0;
		int fen=0;
		int hot=0;
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item:items) {
				if(item.isFormField()) {
					if("giftname".equals(item.getFieldName())) {
						ug.setLname(item.getString("UTF-8"));
					}else if("giftprice".equals(item.getFieldName())) {
						prioc=Double.parseDouble(item.getString("UTF-8"));
						ug.setLprice(prioc);
					}else if("giftfen".equals(item.getFieldName())) {
						fen= Integer.parseInt(item.getString("UTF-8"));
						ug.setLintegral(fen);
					}else if("baiduText".equals(item.getFieldName())) {
						ug.setLbaiduread(item.getString("UTF-8"));
					}else if("re".equals(item.getFieldName())) {
						if("y".equals(item.getString("UTF-8"))) {
							hot=2;
						}else {
							hot=1;
						}
						ug.setDelete(hot);
					}else if("myFile".equals(item.getFieldName())) {
						username=item.getString("UTF-8");
					}else if("textfile".equals(item.getFieldName())) {
						ug.setLread(item.getString("UTF-8"));
					}
				}else {					
					System.out.println(111);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		//UserGift ug = null;		
		//ug.setLname(giftname);
		//ug.setLprice(giftpirce);
		//ug.setLintegral(fen);
		ep.setCode("1");
		
		return ep;
		
	}
	
	
	public pictureParams test(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		  //方式二：使用工具类 、实现Ajax文件上传
		
//		  UploadUtils upl = new UploadUtils();
//		  upl.setSavePath("D:/working1/login3/WebRoot/" + upl.getBasePath() + "/");
//		  String[] result = upl.uploadFile(req); 
//		  Map<String,String> fields =upl.getFoList(); 
//		  System.out.println("用户名：" + fields.get("username")); 
//		  System.out.println("密码：" +fields.get("password"));
		String aa="";
		pictureParams pp= new pictureParams();
		request.setCharacterEncoding("utf-8");
		UploadUtils upload = new UploadUtils();
		String[] result = upload.uploadFile(request);
		if(result != null) {
			pp.setCode(0);
			pc.savePicture(1, result[4], result[3]);
			pp.setUrl(result[4]);
			aa=upload.getUrl();
			System.out.println(aa);
		}		
		return pp;	
	}
	
	@Override
	public Class getServletClass() {
		return tableServlet.class;
	}

}
