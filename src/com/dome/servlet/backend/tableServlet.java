package com.dome.servlet.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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
import com.dome.utils.PrintUtil;
import com.dome.utils.ReturnResult;
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
	public String giftList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		return "/backend/gifttable";
	}
	public String giftupdateList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		return "/backend/giftlist";
	}
	
	public ReturnResult likeGift(HttpServletRequest request,HttpServletResponse response) throws Exception{		
		String lname =request.getParameter("lname");
		String hot =request.getParameter("hot");
		Integer intHot = Integer.parseInt(hot);
		Map<String,Object> map=gs.likeGift(lname, intHot);
		  Set<Entry<String, Object>> setResult = map.entrySet();
		  Iterator<Entry<String,Object>> itr = setResult.iterator();
		  List<UserGift> list = new ArrayList<>();
		  Object list2 = null;
		  int size = 0;
		  while(itr.hasNext()){
			    Entry<String,Object> entry = itr.next();
			    if(entry.getKey().equals("list")){
			    	list2 = entry.getValue();
			    }
			    if(entry.getKey().equals("conut")){
			    	size = Integer.parseInt((String)entry.getValue());
			    }
		  }		    
		  if(list2 instanceof ArrayList<?>) {
				for(Object o:(List<?>)list2) {
					list.add(UserGift.class.cast(o));
				}
		  }
		  for(Object l:list) {
			  System.out.println(l);
		  }
		  System.out.println(size);
		ReturnResult result = new ReturnResult();
		result.setData(list);
		result.returnSuccess("成功");
		result.setCount(size);
		result.setCode(0);
		return result;		
	}
	
	public ReturnResult giftDeletes(HttpServletRequest request,HttpServletResponse response) throws Exception{		
		String lids =request.getParameter("ids");
		String a[] = lids.split(",");
		for(String i:a) {
			Integer id =Integer.parseInt(i);
			//System.out.println(id.equals("java.lang.String"));
			gs.delectGiftList(id);
		}	
		ReturnResult result = new ReturnResult();
		result.setCode(0);
		return result;		
	}
	
	
	public ReturnResult giftDelete(HttpServletRequest request,HttpServletResponse response) throws Exception{		
		String lid =request.getParameter("id");		
		Integer id =Integer.parseInt(lid);
		gs.delectGiftList(id);		
		ReturnResult result = new ReturnResult();
		result.setStatus(0);
		return result;		
	}
	
	public ReturnResult giftFirstTable(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		String page = request.getParameter("page");
		String pageSize = request.getParameter("limit");
		giftService gs = new giftServiceImpl();		
		Map<String,Object> map=gs.queryGiftList(Integer.parseInt(page), Integer.parseInt(pageSize));		
		  Set<Entry<String, Object>> setResult = map.entrySet();
		  Iterator<Entry<String,Object>> itr = setResult.iterator();
		  List<UserGift> list = new ArrayList<>();
		  Object list2 = null;
		  int size = 0;
		  while(itr.hasNext()){
			    Entry<String,Object> entry = itr.next();
			    if(entry.getKey().equals("list")){
			    	list2 = entry.getValue();
			    }
			    if(entry.getKey().equals("conut")){
			    	size = Integer.parseInt((String)entry.getValue());
			    }
		  }
		    
		  if(list2 instanceof ArrayList<?>) {
				for(Object o:(List<?>)list2) {
					list.add(UserGift.class.cast(o));
				}
		  }
	  ReturnResult result = new ReturnResult();
	  result.returnSuccess("成功");
	  result.setData(list);
	  result.setCount(size);
	  result.setCode(0);		
		return result;
	}
	
	public EmailParams giftText(HttpServletRequest request,HttpServletResponse response) throws Exception {
		EmailParams ep = new EmailParams();		
		int fen;
		int hot;
		Double prioc;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		UserGift ug = new UserGift();
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
							hot=1;
						}else {
							hot=0;
						}
						ug.setHot(hot);
					}else if("textfile".equals(item.getFieldName())) {
						ug.setLread(item.getString("UTF-8"));
					}
				}
			}	
		gs.saveGift(ug);
		return ep;
		
	}		
	public pictureParams test(HttpServletRequest request,HttpServletResponse response) throws Exception {
		pictureParams pp= new pictureParams();
		String aa="";
		UploadUtils upload = new UploadUtils();
		String[] result = upload.uploadFile(request);		
		if(result != null) {
			pp.setCode(0);
			//pc.savePicture(1, result[4], result[3]);
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
